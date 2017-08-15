package myextend;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Log;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.core.CloudSimTags;
import org.cloudbus.cloudsim.core.SimEvent;
import org.cloudbus.cloudsim.lists.VmList;
import org.jfree.ui.RefineryUtilities;

public class MyBroker extends DatacenterBroker {
	private List<Vm> vmListForSendRequest = new ArrayList<>();
         public static List<Map<Integer, MyLogCreateVM>>  listMap = new ArrayList<>(); ;
	public static Map<Integer, MyLogCreateVM> map;
	int numVmFail, numVmNoneCreate;
	// for test
	boolean isRandom;
	Random random;

	public MyBroker(String name, boolean isRandom) throws Exception {
		super(name);
		this.isRandom = isRandom;
		if (isRandom)
			random = new Random();
	}

	@Override
	protected void processResourceCharacteristics(SimEvent ev) {
		DatacenterCharacteristics characteristics = (DatacenterCharacteristics) ev
				.getData();
		getDatacenterCharacteristicsList().put(characteristics.getId(),
				characteristics);

		if (getDatacenterCharacteristicsList().size() == getDatacenterIdsList()
				.size() && getDatacenterIdsList().size() != 0) {
			setDatacenterRequestedIdsList(new ArrayList<Integer>());
			map = new TreeMap<Integer, MyLogCreateVM>();
			listMap.add(map);
			createVmsInDatacenter();
		}
	}

	@Override
	protected void processVmCreate(SimEvent ev) {
		int[] data = (int[]) ev.getData();
		int datacenterId = data[0];
		int vmId = data[1];
		int result = data[2];

		if (result == CloudSimTags.TRUE) {
			getVmsToDatacentersMap().put(vmId, datacenterId);
			getVmsCreatedList().add(VmList.getById(getVmList(), vmId));
			Log.printConcatLine(CloudSim.clock(), ": ", getName(), ": VM #",
					vmId, " has been created in Datacenter #", datacenterId,
					", Host #", VmList.getById(getVmsCreatedList(), vmId)
							.getHost().getId());
		} else {
			Log.printConcatLine(CloudSim.clock(), ": ", getName(),
					": Creation of VM #", vmId, " failed in Datacenter #",
					datacenterId);
			numVmFail++;
		}
		if (isHasNextVmNotCreate())
			createVmsInDatacenter();
		else {
			submitCloudlets();
			System.err.println("summit cloudlet");
		}
	}

	/**
	 * tìm và tạo VM trên Datacenter
	 */
	public void createVmsInDatacenter() {
		Vm vm = getNextVmNotCreateed();
                System.out.println("aaaaaaa: " + vm.getId());
		if (vm == null)
			return;
                // ghi Log
		map.put(vm.getId(), new MyLogCreateVM(getVmList().size(),
				getVmsCreatedList().size(), numVmFail, numVmNoneCreate,
				getDatacenterIdsList().size()));
		if (!isRandom) {
                    
			DatacenterCharacteristics cTmp = null;
			// tìm danh sách DatacenterCharacteristics available cho Vm
			List<DatacenterCharacteristics> listCharaterisAvailible = new ArrayList<DatacenterCharacteristics>();
			for (Entry<Integer, DatacenterCharacteristics> entry : datacenterCharacteristicsList
					.entrySet()) {
				cTmp = entry.getValue();
				if (checkCharateristicAvailableForVm(cTmp, vm)) {
					listCharaterisAvailible.add(cTmp);
				}
			}
                        
			if (listCharaterisAvailible.isEmpty()) { // not found Datacenter available
				Log.printLine(CloudSim.clock() + ": " + getName()
						+ ": not found Datacenter available for VM #"
						+ vm.getId());
				vmListForSendRequest.remove(vm);
				numVmNoneCreate++;
				if (isHasNextVmNotCreate())
					createVmsInDatacenter();
				else
					submitCloudlets();

			} else { // found Datacenter available
				cTmp = findCheapDatacenterCharacteristics(listCharaterisAvailible);
				String datacenterName = CloudSim
						.getEntityName(cTmp.getId());
				Log.printLine(CloudSim.clock() + ": " + getName()
						+ ": Trying to Create VM #" + vm.getId() + " in "
						+ datacenterName);
				sendNow(cTmp.getId(),
						CloudSimTags.VM_CREATE_ACK, vm);
			}
		} else {
			sendNow(datacenterIdsList.get(random.nextInt(datacenterIdsList
					.size())), CloudSimTags.VM_CREATE_ACK, vm);
			vmListForSendRequest.remove(vm);
		}
	}

	/**
	 * tìm Vm tiếp theo chưa đc tạo trên Datacenter
	 * 
	 * @return next Vm, otherwise null
	 */
	private Vm getNextVmNotCreateed() {
		for (Vm v : vmListForSendRequest) {
			if (!getVmsToDatacentersMap().containsKey(v.getId())) {
				return v;
			}
		}
		return null;
	}

	/**
	 * kiểm tra xem có còn Vm chưa tạo không?
	 * 
	 * @return true if còn Vm chưa tạo.
	 */
	private boolean isHasNextVmNotCreate() {
		return getNextVmNotCreateed() != null;
	}

	/**
	 * 
	 * @param listHost
	 * @param vm
	 * @return
	 */
	private boolean checkHasHostAvailableForVm(List<Host> listHost, Vm vm) {
		for (Host host : listHost) {
			if (checkStoreOfHost(host, vm) && checkRamOfHost(host, vm)
					&& checkBWOfHost(host, vm) && checkPeOfHost(host, vm))
				return true;
		}
		return false;
	}
        private boolean checkCharateristicAvailableForVm(DatacenterCharacteristics characteric, Vm vm) {
		for (Host host : characteric.getHostList()) {
			if (checkStoreOfHost(host, vm) && checkRamOfHost(host, vm)
					&& checkBWOfHost(host, vm) && checkPeOfHost(host, vm))
				return true;
		}
		return false;
	}
	private boolean checkStoreOfHost(Host host, Vm vm) {
		return host.getStorage() >= vm.getSize();
	}
	private boolean checkRamOfHost(Host host, Vm vm) {
		return host.getRamProvisioner().getAvailableRam() >= vm.getRam();
	}
	private boolean checkBWOfHost(Host host, Vm vm) {
		return host.getBwProvisioner().getAvailableBw() >= vm.getBw();
	}
	private boolean checkPeOfHost(Host host, Vm vm) {
		double totalRequestedMips = 0;
		double peMips = host.getVmScheduler().getPeCapacity();
		for (Double mips : vm.getCurrentRequestedMips()) {
			if (mips > peMips) {
				return false;
			}
			totalRequestedMips += mips;
		}
		if (host.getVmScheduler().getAvailableMips() < totalRequestedMips) {
			return false;
		}
		return true;
	}

	/**
	 * Tìm DatacenterCharacteristics rẻ nhất
	 * 
	 * @param list
	 * @return
	 */
	private DatacenterCharacteristics findCheapDatacenterCharacteristics(
			List<DatacenterCharacteristics> list) {
		// chưa làm
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	@Override
	public void submitVmList(List<? extends Vm> list) {
		super.submitVmList(list);
		vmListForSendRequest.addAll(list);
	}

	@Override
	public void shutdownEntity() {

		super.shutdownEntity();
//		try {
//			printToFile("G:\\cloudsim.txt");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}

	private void printToFile(String fileName) throws FileNotFoundException {
		PrintWriter br = new PrintWriter(new FileOutputStream(fileName), false);
		for (Map<Integer, MyLogCreateVM> map : listMap) {
			for (Integer i : map.keySet()) {
				br.println(i + " : " + map.get(i));
			}
		}
		br.flush();
		br.close();
	}

	// @Override
	// protected void submitCloudlets() {
	// if (index != numDc.length) {
	// clearDatacenters();
	// index++;
	// vmListForSendRequest.clear();
	// vmListForSendRequest.addAll(vmList);
	// MyExample.createDatacenter("Datacenter_" + index, 50, 2);
	// // processResourceCharacteristicsRequest(null);
	// schedule(getId(), 0, CloudSimTags.RESOURCE_CHARACTERISTICS_REQUEST);
	// createVmsInDatacenter();
	// } else {
	// }
	// }
	@Override
	protected void clearDatacenters() {
		super.clearDatacenters();
		getVmsToDatacentersMap().clear(); // nen them vi code
		numVmFail = 0;
		numVmNoneCreate = 0;
	}

	@Override
	protected void processResourceCharacteristicsRequest(SimEvent ev) {
		super.processResourceCharacteristicsRequest(ev);
		// them cho demo
		clearDatacenters();
		vmListForSendRequest.clear();
		vmListForSendRequest.addAll(vmList);
	}

}
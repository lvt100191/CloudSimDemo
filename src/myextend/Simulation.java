package myextend;

import Database.db_Datacenter;
import Database.db_Host;
import Database.db_HostType;
import Database.db_User;
import Database.db_Vm;
import Database.db_VmType;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import oracle.jrockit.jfr.JFR;

import org.cloudbus.cloudsim.CloudletSchedulerTimeShared;
import org.cloudbus.cloudsim.Datacenter;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.DatacenterCharacteristics;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.Pe;
import org.cloudbus.cloudsim.Storage;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmAllocationPolicySimple;
import org.cloudbus.cloudsim.VmSchedulerTimeShared;
import org.cloudbus.cloudsim.core.CloudSim;
import org.cloudbus.cloudsim.power.PowerHost;
import org.cloudbus.cloudsim.power.PowerHostUtilizationHistory;
import org.cloudbus.cloudsim.power.PowerVm;
import org.cloudbus.cloudsim.power.models.PowerModel;
import org.cloudbus.cloudsim.provisioners.BwProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.PeProvisionerSimple;
import org.cloudbus.cloudsim.provisioners.RamProvisionerSimple;
import org.jfree.ui.RefineryUtilities;

public class Simulation {
       Statement stm;
	public Simulation(boolean isRandom, int numDC,  int numUser , Statement stm) {
            try {
                //		int num_user = 1; // number of cloud users
                this.stm = stm;
                Calendar calendar = Calendar.getInstance();
                boolean trace_flag = false; // mean trace events
                CloudSim.init(numUser, calendar, trace_flag);
                
                DatacenterBroker broker = createBroker(isRandom);
                int brokerId = broker.getId();
                // VM
                LinkedList<db_User> userData = db_User.getAllUser(stm);
                broker.submitVmList(createVm(userData.get(0).ID, brokerId));
                // DC
                LinkedList<db_Datacenter> datacenterData = db_Datacenter.getAllDatacenter(stm);
                for (int i = 0; i < numDC; i++) {
                   createDatacenter(datacenterData.get(i));
                }
                CloudSim.startSimulation();
                CloudSim.stopSimulation();
            } catch (SQLException ex) {
                Logger.getLogger(Simulation.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

	private DatacenterBroker createBroker(boolean isRandom) {
		DatacenterBroker broker = null;
		try {
			// broker = new DatacenterBroker("Broker");
			broker = new MyBroker("Broker", isRandom);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return broker;
	}

	public static Datacenter createDatacenter(String name, int numHost,
			int numPes) {
		List<Host> hostList = new ArrayList<Host>();
		//
		List<Pe> peList = new ArrayList<Pe>();
		int mips = 1000;
		for (int i = 0; i < numPes; i++) {
			peList.add(new Pe(0, new PeProvisionerSimple(mips))); // need to
		}
		// 4. Create Host with its id and list of PEs and add them to the list
		// of machines
		int hostId = 0;
		int ram = 2048; // host memory (MB)
		long storage = 1000000; // host storage
		int bw = 10000;
		for (int i = 0; i < numHost; i++) {
			hostList.add(new Host(hostId++, new RamProvisionerSimple(ram),
					new BwProvisionerSimple(bw), storage, peList,
					new VmSchedulerTimeShared(peList))); // This is our first
															// machine
		}

		// 5. Create a DatacenterCharacteristics object that stores the
		// properties of a data center: architecture, OS, list of
		// Machines, allocation policy: time- or space-shared, time zone
		// and its price (G$/Pe time unit).
		String arch = "x86"; // system architecture
		String os = "Linux"; // operating system
		String vmm = "Xen";
		double time_zone = 10.0; // time zone this resource located
		double cost = 3.0; // the cost of using processing in this resource
		double costPerMem = 0.05; // the cost of using memory in this resource
		double costPerStorage = 0.001; // the cost of using storage in this
										// resource
		double costPerBw = 0.0; // the cost of using bw in this resource
		LinkedList<Storage> storageList = new LinkedList<Storage>(); // we are
		DatacenterCharacteristics characteristics = new DatacenterCharacteristics(
				arch, os, vmm, hostList, time_zone, cost, costPerMem,
				costPerStorage, costPerBw);

		// 6. Finally, we need to create a PowerDatacenter object.
		Datacenter datacenter = null;
		try {
			datacenter = new Datacenter(name, characteristics,
					new VmAllocationPolicySimple(hostList), storageList, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return datacenter;
	}

	private List<Vm> createVM(int idStart, int numVM, int numPe, int brokerId) {
		List<Vm> list = new LinkedList<Vm>();
		int vmid = idStart;
		int mips = 500;
		long size = 10000; // image size (MB)
		int ram = 512; // vm memory (MB)
		long bw = 1000;
		String vmm = "Xen";
		for (int i = 0; i < numVM; i++) {
			list.add(new Vm(vmid++, brokerId, mips, numPe, ram, bw, size, vmm,
					new CloudletSchedulerTimeShared()));
		}
		return list;
	}
        protected LinkedList<Vm> createVm(int rootID, int userID) throws SQLException{
        LinkedList<Vm> vmList = new LinkedList<>();
        LinkedList<db_Vm> listData = db_Vm.getAllVmOf1User(stm, rootID);       
        String[] number;
        int num;
        for(db_Vm dbvm : listData){
            db_VmType.Open();
            db_VmType vmType = db_VmType.getVmType(dbvm.vmType);
            db_VmType.Close();
            if(!dbvm.vmID.contains("-")){           
                vmList.add(new Vm((Integer.parseInt(dbvm.vmID)), 
                                        userID, 
                                        vmType.mips, 
                                        vmType.numPes,
                                        vmType.ram,
                                        vmType.bw,
                                        vmType.storage,
                                        dbvm.vmm,
                                        new CloudletSchedulerTimeShared()  ));
            }else{          
                number = dbvm.vmID.split("-");      
                num = Integer.parseInt(number[1]) - Integer.parseInt(number[0]) + 1;
                for(int j=0; j<num; j++){
                    vmList.add(new Vm((Integer.parseInt(number[0]) +j), 
                                        userID, 
                                        vmType.mips, 
                                        vmType.numPes,
                                        vmType.ram,
                                        vmType.bw,
                                        vmType.storage,
                                        dbvm.vmm,
                                        new CloudletSchedulerTimeShared()) );
                }
            }
        }
        return vmList;
    }
        protected List<Pe> createPe(int numPes, double mipsPerPes){
        LinkedList<Pe> peList = new LinkedList<Pe>();
        for(int i=0; i<numPes; i++){
            peList.add(new Pe(i+1, new PeProvisionerSimple(mipsPerPes)));
        }
        return peList;
    }
        protected LinkedList<Host> createHost(int rootID){
        LinkedList<Host> hostList = new LinkedList<>();
        LinkedList<db_Host> listData = db_Host.getAllHostOF1DC(stm, rootID);
        for(db_Host dbh : listData){
            db_HostType.Open();
            db_HostType hostType = db_HostType.getHostType(dbh.hostType);
            db_HostType.Close();
            List<Pe> peList = createPe(hostType.numPes, hostType.mips);
            if(!dbh.hostID.contains("-")){   
                hostList.add(new Host(Integer.parseInt(dbh.hostID), new RamProvisionerSimple(hostType.ram),
					new BwProvisionerSimple(dbh.bw), hostType.storage, peList,
					new VmSchedulerTimeShared(peList)));
            }else{ 
                String[] number = dbh.hostID.split("-");
                int num = Integer.parseInt(number[1]) - Integer.parseInt(number[0]) + 1;
                for(int j=0; j<num; j++){
                    hostList.add(new Host(j, new RamProvisionerSimple(hostType.ram),
					new BwProvisionerSimple(dbh.bw), hostType.storage, peList,
					new VmSchedulerTimeShared(peList)));
                }
            }
        }
        return hostList;
    }
        protected Datacenter createDatacenter(db_Datacenter dbdc){
        DatacenterCharacteristics characteristics;
        Datacenter powerDatacenter = null;
        List<Host> hostList = createHost(dbdc.ID);                  
        characteristics = new DatacenterCharacteristics(dbdc.arch, dbdc.os, dbdc.vmm, hostList,
                dbdc.timezone, dbdc.costPerSecond, dbdc.costPerRam, dbdc.costPerStorage, dbdc.costPerBw);
        LinkedList<Storage> storageList = new LinkedList<Storage>();
        try{
            powerDatacenter = new Datacenter(dbdc.name, characteristics,
					new VmAllocationPolicySimple(hostList), storageList, 0);
        }catch(Exception e){
            e.printStackTrace();
        }
        return powerDatacenter;
    }
        public static void start( Statement stm){
                int numUser = 1;
                boolean isRandom = true;
                int numDC = db_Datacenter.getNumberDatacenter(stm);
                List<Map<Integer, MyLogCreateVM>> listMapChuaCT , listMapDaCT;
//                int numHostDc = 50, numPesHost = 2, numVm = 2000, numPesVm = 2;
                MyBroker.listMap = new ArrayList<Map<Integer, MyLogCreateVM>>();
                for (int i = 1, j = 2, k = 0; i <= numDC; i= (i + j < numDC ? i + j : numDC + k++), j++) {
                    new Simulation(isRandom, i /*i == numDC */, numUser, stm);
                    if(numDC == 1) break;
                }
                listMapChuaCT = MyBroker.listMap;
                
		
                
		// random = false
		isRandom = false;
		MyBroker.listMap = new ArrayList<Map<Integer, MyLogCreateVM>>();
		for (int i = 1, j = 2, k = 0; i <= numDC; i= (i + j < numDC ? i + j : numDC + k++), j++) {
                    new Simulation(isRandom, i /*i == numDC */, numUser, stm);
                    if(numDC == 1) break;
                }
                listMapDaCT = MyBroker.listMap;
                // tao gui
                ChartPercentageCreateVM chart = new ChartPercentageCreateVM(
				"Broker chọn Datacenter ngẫu nhiên", listMapChuaCT);
		chart = new ChartPercentageCreateVM(
				"Broker chọn Datacenter khả dụng", listMapDaCT);
                //
                chart = new ChartPercentageCreateVM("So sánh kết quả mô phỏng", CreatePanelChart.getJPanelChartCompare3DC("So sánh kết quả mô phỏng", listMapChuaCT,listMapDaCT, numDC));
                // chart combine
                ChartTwoPanel guiChart = new ChartTwoPanel(); // chart sô sánh giứa 2 loại
                guiChart.addChart(CreatePanelChart.getJPanelChart("Broker chọn Datacenter ngẫu nhiên", listMapChuaCT));
                guiChart.addChart(CreatePanelChart.getJPanelChart("Broker chọn Datacenter khả dụng", listMapDaCT));
                guiChart.setVisible(true);
                
        }
	public static void main(String[] args) {
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.cloudbus.cloudsim.DatacenterBroker;
import org.cloudbus.cloudsim.Host;
import org.cloudbus.cloudsim.HostDynamicWorkload;
import org.cloudbus.cloudsim.HostStateHistoryEntry;
import org.cloudbus.cloudsim.Vm;
import org.cloudbus.cloudsim.VmStateHistoryEntry;
import org.cloudbus.cloudsim.power.PowerDatacenter;
//import org.cloudbus.cloudsim.power.PowerDatacenterBroker;
import org.cloudbus.cloudsim.power.PowerVmAllocationPolicyMigrationAbstract;
import org.cloudbus.cloudsim.util.MathUtil;

/**
 *
 * @author GC
 */
public class Calculation {
    
    public static double getTotalPower(PowerDatacenter[] dcList){
        double totalEnegry = 0;
        for(PowerDatacenter pd : dcList){
            totalEnegry += pd.getPower();
        }
        return (totalEnegry/(1000*3600));
    }
    
    public static int getNumberOfHost(PowerDatacenter[] dcList){
        int totalHost = 0;
        for(PowerDatacenter pd : dcList){
            totalHost += pd.getHostList().size();
        }
        return totalHost;
    }
    
    public static int getNumberOfVm(DatacenterBroker[] dbList){
        int totalVm = 0;
        for(DatacenterBroker db : dbList){
            totalVm += db.getVmList().size();
        }
        return totalVm;
    }
    
    public static int getTotalMigration(PowerDatacenter[] dcList){
        int totalMigration = 0;
        for(PowerDatacenter pd : dcList){
            totalMigration += pd.getMigrationCount();
        }
        return totalMigration;
    }
    
    
    public static ArrayList getSlaMetricsOf1User(List<Vm> vms){
	ArrayList metrics = new ArrayList();
        List<Double> slaViolation = new LinkedList<Double>();
	double totalAllocated = 0;
	double totalRequested = 0;
	double totalUnderAllocatedDueToMigration = 0;

	for(Vm vm : vms){
            double vmTotalAllocated = 0;
            double vmTotalRequested = 0;
            double vmUnderAllocatedDueToMigration = 0;
            double previousTime = -1;
            double previousAllocated = 0;
            double previousRequested = 0;
            boolean previousIsInMigration = false;

            for(VmStateHistoryEntry entry : vm.getStateHistory()){
		if(previousTime != -1){
                    double timeDiff = entry.getTime() - previousTime;
                    vmTotalAllocated += previousAllocated * timeDiff;
                    vmTotalRequested += previousRequested * timeDiff;

                    if(previousAllocated < previousRequested){
			slaViolation.add((previousRequested - previousAllocated) / previousRequested);
			if(previousIsInMigration){
                            vmUnderAllocatedDueToMigration += (previousRequested - previousAllocated) * timeDiff;
			}
                    }
		}

		previousAllocated = entry.getAllocatedMips();
		previousRequested = entry.getRequestedMips();
		previousTime = entry.getTime();
		previousIsInMigration = entry.isInMigration();
            }

            totalAllocated += vmTotalAllocated;
            totalRequested += vmTotalRequested;
            totalUnderAllocatedDueToMigration += vmUnderAllocatedDueToMigration;
	}

	metrics.add(totalAllocated);
        metrics.add(totalRequested);
        metrics.add(totalUnderAllocatedDueToMigration);
        metrics.add(slaViolation);
        return metrics;
    }
    
    public static Map<String, Double> getSlaMetrics(DatacenterBroker[] brokerList){
        Map<String, Double> slaMetrics = new HashMap<>();
        List<Double> slaViolation = new LinkedList<Double>();
        double totalAllocated = 0, totalRequested = 0, underallocated_migration = 0;
        for(DatacenterBroker broker : brokerList){
            ArrayList slaMetricsOfUser = getSlaMetricsOf1User(broker.getVmList());
            totalAllocated += (double)slaMetricsOfUser.get(0);
            totalRequested += (double)slaMetricsOfUser.get(1);
            underallocated_migration += (double)slaMetricsOfUser.get(2);
            slaViolation.addAll((List<Double>)slaMetricsOfUser.get(3));
        }
        slaMetrics.put("overall", (totalRequested - totalAllocated) / totalRequested);
        if(slaViolation.isEmpty()){
            slaMetrics.put("average", 0.);
        }else{
            slaMetrics.put("average", MathUtil.mean(slaViolation));
        }
        slaMetrics.put("underallocated_migration", underallocated_migration / totalRequested);
        return slaMetrics;
    }    
    
    public static double getSlaTimePerActiveHost(PowerDatacenter[] datacenterList){
        double slaViolationTimePerHost = 0;
	double totalTime = 0;
        for(PowerDatacenter datacenter : datacenterList){
            for(Host _host : datacenter.getHostList()){
                HostDynamicWorkload host = (HostDynamicWorkload) _host;
                double previousTime = -1;
                double previousAllocated = 0;
                double previousRequested = 0;
                boolean previousIsActive = true;

                for(HostStateHistoryEntry entry : host.getStateHistory()){
                    if(previousTime != -1 && previousIsActive){
                        double timeDiff = entry.getTime() - previousTime;
                        totalTime += timeDiff;
                        if(previousAllocated < previousRequested){
                            slaViolationTimePerHost += timeDiff;
                        }
                    }

                    previousAllocated = entry.getAllocatedMips();
                    previousRequested = entry.getRequestedMips();
                    previousTime = entry.getTime();
                    previousIsActive = entry.isActive();
                }
            }
        }
        return slaViolationTimePerHost / totalTime;
    }


    
    public static List<Double> getTimesBeforeHostShutdown(PowerDatacenter[] datacenterList){
        List<Double> timeBeforeShutdown = new LinkedList<Double>();
        for(PowerDatacenter datacenter : datacenterList){
            timeBeforeShutdown.addAll(getTimesBeforeHostShutdownOf1DC(datacenter.getHostList()));
        }
        return timeBeforeShutdown;
    }
    
    
    public static List<Double> getTimesBeforeHostShutdownOf1DC(List<Host> hosts){
	List<Double> timeBeforeShutdown = new LinkedList<Double>();
	for(Host host : hosts){
            boolean previousIsActive = true;
            double lastTimeSwitchedOn = 0;
            for(HostStateHistoryEntry entry : ((HostDynamicWorkload) host).getStateHistory()){
		if (previousIsActive == true && entry.isActive() == false){
                    timeBeforeShutdown.add(entry.getTime() - lastTimeSwitchedOn);
		}
		if (previousIsActive == false && entry.isActive() == true){
                    lastTimeSwitchedOn = entry.getTime();
		}
		previousIsActive = entry.isActive();
            }
	}
	return timeBeforeShutdown;
    }
    
    public static List<Double> getTimesBeforeVmMigration(DatacenterBroker[] brokerList){
        List<Double> timeBeforeVmMigration = new LinkedList<Double>();
        for(DatacenterBroker broker : brokerList){
            timeBeforeVmMigration.addAll(getTimesBeforeVmMigrationOf1User(broker.getVmList()));
        }
        return timeBeforeVmMigration;
    }
    
    public static List<Double> getTimesBeforeVmMigrationOf1User(List<Vm> vms){
	List<Double> timeBeforeVmMigration = new LinkedList<Double>();
	for(Vm vm : vms){
            boolean previousIsInMigration = false;
            double lastTimeMigrationFinished = 0;
            for(VmStateHistoryEntry entry : vm.getStateHistory()){
		if(previousIsInMigration == true && entry.isInMigration() == false){
                    timeBeforeVmMigration.add(entry.getTime() - lastTimeMigrationFinished);
		}
		if(previousIsInMigration == false && entry.isInMigration() == true){
                    lastTimeMigrationFinished = entry.getTime();
		}
		previousIsInMigration = entry.isInMigration();
            }
	}
	return timeBeforeVmMigration;
    }
    
    public static HashMap<String, Number> getOutput(PowerDatacenter[] datacenterList, DatacenterBroker[] brokerList){
        HashMap<String, Number> output = new HashMap<>();
        output.put("totalPower", getTotalPower(datacenterList));
        output.put("numberOfHost", getNumberOfHost(datacenterList));
        output.put("numberOfVm", getNumberOfVm(brokerList));
        output.put("totalMigration", getTotalMigration(datacenterList));
        Map<String, Double> slaMetrics = getSlaMetrics(brokerList);
        output.putAll(slaMetrics);
        double slaTimePerActiveHost = getSlaTimePerActiveHost(datacenterList);        
        output.put("slaTime", slaTimePerActiveHost);
        double sla = slaTimePerActiveHost * slaMetrics.get("underallocated_migration");
        output.put("sla", sla);
        List<Double> timeBeforeHostShutdown = getTimesBeforeHostShutdown(datacenterList);
        output.put("numberOfHostShutdown", timeBeforeHostShutdown.size());

        double meanTimeBeforeHostShutdown = Double.NaN;
        double stDevTimeBeforeHostShutdown = Double.NaN;
        if (!timeBeforeHostShutdown.isEmpty()) {
            meanTimeBeforeHostShutdown = MathUtil.mean(timeBeforeHostShutdown);
            stDevTimeBeforeHostShutdown = MathUtil.stDev(timeBeforeHostShutdown);
        }
        output.put("meanTimeBeforeHostShutdown", meanTimeBeforeHostShutdown);
        output.put("stDevTimeBeforeHostShutdown", stDevTimeBeforeHostShutdown);
        List<Double> timeBeforeVmMigration = getTimesBeforeVmMigration(brokerList);
        double meanTimeBeforeVmMigration = Double.NaN;
        double stDevTimeBeforeVmMigration = Double.NaN;
        if (!timeBeforeVmMigration.isEmpty()) {
            meanTimeBeforeVmMigration = MathUtil.mean(timeBeforeVmMigration);
            stDevTimeBeforeVmMigration = MathUtil.stDev(timeBeforeVmMigration);
        }
        output.put("meanTimeBeforeVmMigration", meanTimeBeforeVmMigration);
        output.put("stDevTimeBeforeVmMigration", stDevTimeBeforeVmMigration);
        output.putAll(getExecutionTime(datacenterList));
        
        return output;
    }
    
    
    public static HashMap<String, Number> getExecutionTime(PowerDatacenter[] datacenterList){
        HashMap<String, Number> executionTime = new HashMap<>();
        List<Double> executionTimeHistoryVmSelection = new LinkedList<Double>();
        List<Double> executionTimeHistoryHostSelection = new LinkedList<Double>();
        List<Double> executionTimeHistoryVmReallocation = new LinkedList<Double>();
        List<Double> executionTimeHistoryTotal = new LinkedList<Double>();
        for(PowerDatacenter pd : datacenterList){
            if(pd.getVmAllocationPolicy() instanceof PowerVmAllocationPolicyMigrationAbstract){
                PowerVmAllocationPolicyMigrationAbstract vmAllocationPolicy = (PowerVmAllocationPolicyMigrationAbstract) pd.getVmAllocationPolicy();
                executionTimeHistoryVmSelection.addAll(vmAllocationPolicy.getExecutionTimeHistoryVmSelection());
                executionTimeHistoryHostSelection.addAll(vmAllocationPolicy.getExecutionTimeHistoryHostSelection());
                executionTimeHistoryVmReallocation.addAll(vmAllocationPolicy.getExecutionTimeHistoryVmReallocation());
                executionTimeHistoryTotal.addAll(vmAllocationPolicy.getExecutionTimeHistoryTotal());            
            }
        }
        executionTime.put("executionTimeVmSelectionMean", MathUtil.mean(executionTimeHistoryVmSelection));
        executionTime.put("executionTimeVmSelectionStDev", MathUtil.stDev(executionTimeHistoryVmSelection));
        executionTime.put("executionTimeHostSelectionMean", MathUtil.mean(executionTimeHistoryHostSelection));
        executionTime.put("executionTimeHostSelectionStDev", MathUtil.stDev(executionTimeHistoryHostSelection));
        executionTime.put("executionTimeVmReallocationMean", MathUtil.mean(executionTimeHistoryVmReallocation));
        executionTime.put("executionTimeVmReallocationStDev", MathUtil.stDev(executionTimeHistoryVmReallocation));
        executionTime.put("executionTimeTotalMean", MathUtil.mean(executionTimeHistoryTotal));
        executionTime.put("executionTimeTotalStDev", MathUtil.stDev(executionTimeHistoryTotal));
        return executionTime;
    }

    
    
}

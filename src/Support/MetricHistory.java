/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

//import CloudSimExtend.PowerDatacenter;
import java.util.ArrayList;
import java.util.List;
import org.cloudbus.cloudsim.power.PowerHost;

/**
 *
 * @author GC
 */
public class MetricHistory {
    public ArrayList<Double> timeline;
    public ArrayList<Double> utilization;
    public ArrayList<Double> dcPower;
    public ArrayList<Integer> numberHostActive;
    public ArrayList<Integer> numberVmMigration;
    public ArrayList<Integer> numberHostUpperThreshold;
    public double lastPower;
    public int lastVmMigration;
    
    public MetricHistory(){
        timeline = new ArrayList<>();
        utilization = new ArrayList<>();
        dcPower = new ArrayList<>();
        numberHostActive = new ArrayList<>();
        numberVmMigration = new ArrayList<>();
        numberHostUpperThreshold = new ArrayList<>();
        lastPower = 0;
        lastVmMigration = 0;
    }
    
//    public void updateMetric(double currentTime, PowerDatacenter powerDC, double upperThreshold){
//        timeline.add(currentTime);
//        this.dcPower.add(powerDC.getPower() - lastPower);
//        lastPower = powerDC.getPower();
//        numberVmMigration.add(powerDC.getMigrationCount() - lastVmMigration);
//        lastVmMigration = powerDC.getMigrationCount();
//        updateUtilizationAndHost(powerDC, upperThreshold);
//    }
//    
//    protected void updateUtilizationAndHost(PowerDatacenter powerDC, double upperThreshold){
//        List<PowerHost> hostList = powerDC.getHostList();
//        int hostActive = 0, hostUpper = 0;
//        double totalUtil = 0, currentUtil;
//        for(PowerHost host : hostList){
//            currentUtil = host.getUtilizationOfCpu();
//            if(currentUtil > 0){
//                hostActive++;
//                if(host.getUtilizationOfCpu() > upperThreshold) hostUpper++;
//                totalUtil += host.getUtilizationOfCpu();
//            }         
//        }
//        utilization.add((double)(totalUtil/hostActive));
//        numberHostActive.add(hostActive);
////        numberHostUpperThreshold.add(hostUpper);
//    }
//    
//    public static ArrayList[] getMedianHistory(PowerDatacenter[] powerDCList){
//        ArrayList<Double> time = powerDCList[0].history.timeline;
//        ArrayList<Double> util = new ArrayList();
//        ArrayList<Double> power = new ArrayList<>();
//        ArrayList<Integer> hostActive = new ArrayList<>();
//        ArrayList<Integer> vmMigration = new ArrayList<>();
//        ArrayList<Integer> hostUpper = new ArrayList<>();
////        ArrayList<Double> percentHostUpper = new ArrayList<>();
//        for(int i=0; i<time.size(); i++){
//            util.add((double)0);
//            power.add((double)0);
//            hostActive.add(0);
//            vmMigration.add(0);
//            hostUpper.add(0);
//        }
//        for(PowerDatacenter powerDC : powerDCList){
//            util = Add2ListDouble(util, powerDC.history.utilization);
//            power = Add2ListDouble(power, powerDC.history.dcPower);
//            hostActive = Add2ListInteger(hostActive, powerDC.history.numberHostActive);
//            vmMigration = Add2ListInteger(vmMigration, powerDC.history.numberVmMigration);
////            hostUpper = Add2ListInteger(hostUpper, powerDC.history.numberHostUpperThreshold);
//        }
//        util = DivListForNumber(util, powerDCList.length);
//        ArrayList[] medianHistory = {time, util, power, hostActive, vmMigration, getPercentHostUpper(hostUpper, hostActive)};
//        return medianHistory;
//    }
    
    
    protected static ArrayList<Double> getPercentHostUpper(ArrayList<Integer> hostUpper, ArrayList<Integer> hostActive){
        ArrayList<Double> percenterHostUpper = new ArrayList<>();
        for(int i=0; i<hostUpper.size(); i++){
            percenterHostUpper.add((double)((double)100.0*((double)hostUpper.get(i))/((double)hostActive.get(i))));
        }
        return percenterHostUpper;
    }
    
    protected static ArrayList<Double> Add2ListDouble(ArrayList<Double> list1, ArrayList<Double> list2){
        for(int i=0; i<list1.size(); i++){
            list1.set(i, (list1.get(i) + list2.get(i)));
        }
        return list1;
    }
    
    protected static ArrayList<Integer> Add2ListInteger(ArrayList<Integer> list1, ArrayList<Integer> list2){
        for(int i=0; i<list1.size(); i++){
            list1.set(i, (list1.get(i) + list2.get(i)));
        }
        return list1;
    }
    
    protected static ArrayList<Double> DivListForNumber(ArrayList<Double> list1, int number){
        for(int i=0; i<list1.size(); i++){
            list1.set(i, (double)(list1.get(i)/number));
        }
        return list1;
    }
    
    protected static ArrayList<Double> MutilListForNumber(ArrayList<Double> list1, double number){
        for(int i=0; i<list1.size(); i++){
            list1.set(i, (double)(list1.get(i)*number));
        }
        return list1;
    }
}

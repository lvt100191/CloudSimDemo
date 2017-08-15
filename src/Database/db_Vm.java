/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author GC
 */
public class db_Vm {
//    static Connection con;
//    static Statement stm;
    
    public int ID;
    public int rootID;
    public String vmID;
    public String vmType;
    public String vmm;
    public String cloudletPolicy;
    
    public db_Vm(int rootID, String vmID, String vmType, String vmm, String cloudletPolicy){
        this.rootID = rootID;
        this.vmID = vmID;
        this.vmType = vmType;
        this.vmm = vmm;
        this.cloudletPolicy = cloudletPolicy;
    }
    
    public db_Vm(String vmID, String vmType, String vmm, String cloudletPolicy){
        this.vmID = vmID;
        this.vmType = vmType;
        this.vmm = vmm;
        this.cloudletPolicy = cloudletPolicy;
    }
   
    
    public void Insert(Statement stm){
        try{
            String sql = "INSERT INTO Vm VALUES(?," + rootID + ",'" + vmID + "','" + vmType + "','" + vmm + "','" + cloudletPolicy + "')";
            stm.executeUpdate(sql);
        }catch(Exception e){
            //System.out.println(e);
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void InsertDefault(Statement stm,int rootID){
        try{
            String sql = "INSERT INTO Vm VALUES(?," + rootID + ",'1','Demo','Xen','DynamicWorkload')";
            stm.executeUpdate(sql);
        }catch(Exception e){
            //System.out.println(e);
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void Del(Statement stm, int id){
        String sql;
        if(id == -1){
            sql = "DELETE FROM Vm";
        }else{
            sql = "DELETE FROM Vm WHERE ID=" + id;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public static void delVmsOf1User(Statement stm, int rootID){
        String sql;
        if(rootID == -1){
            sql = "DELETE FROM Vm";
        }else{
            sql = "DELETE FROM Vm WHERE rootID=" + rootID;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public void updateVm(Statement stm, int id){
        String sql = "UPDATE Vm SET 'Vm ID'='" +vmID+ "','Vm Type'='" + vmType + "','VM Monitor'='" + vmm + "','Cloudlet Scheduler Policy'='" + cloudletPolicy + "' WHERE ID=" +id;
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static LinkedList<db_Vm> getAllVmOf1User(Statement stm, int rootID){
        LinkedList listData = new LinkedList();
        String sql = "SELECT \"Vm ID\", \"Vm Type\", \"VM Monitor\", \"Cloudlet Scheduler Policy\" FROM Vm WHERE rootID=" + rootID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                listData.add(new db_Vm(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4)));            
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return listData;
    }
    
    public static int getLastID(Statement stm, int rootID){
        String sql = "SELECT MAX(ID) AS [IDENTITY] FROM Vm WHERE rootID=" + rootID;
        int ID = 0;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) ID = rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
        }     
        return ID;
    }
    
    
    
    public static String getLastVmID(Statement stm, int rootID){
        int ID = db_Vm.getLastID(stm, rootID);
        String vmID = null;
        if(ID==0) return "0";
        String sql = "SELECT \"Vm ID\" FROM Vm WHERE ID=" +ID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                vmID = rs.getString(1);
            }
            if(vmID.contains("-")){
                String[] num = vmID.split("-");
                vmID = num[1];
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return vmID;
    }
    
    public static void updateLastVmID(Statement stm, int rootID, int lastVmID, String number){
        String vmID;
        int vmID1, vmID2;
        vmID1 = lastVmID + 1;
        if(number.equals("1")){
            vmID = Integer.toString(vmID1);
        }else{
            String[] num = number.split("-");
            vmID2 = vmID1 + Integer.parseInt(num[1]) - 1;
            vmID = Integer.toString(vmID1) + "-" + Integer.toString(vmID2);
        }
        String sql = "UPDATE Vm SET 'Vm ID'='" + vmID + "' WHERE ID=" + db_Vm.getLastID(stm, rootID);
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
//    public static int getNumberOfVms(Statement stm, int rootID){
//        String sql = "SELECT COUNT(*) FROM Vm WHERE rootID=" + rootID;
//        int number = 0;
//        try{
//            ResultSet rs = stm.executeQuery(sql);
//            if(rs.next()) number = rs.getInt(1);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return number;
//    }
    
    public static void updateVmID(Statement stm, int rootID){
        ArrayList<Integer> ID = new ArrayList<Integer>();
        ArrayList<String> vmID = new ArrayList<String>();
        String sql = "SELECT ID, \"Vm ID\" FROM Vm WHERE rootID=" + rootID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                ID.add(rs.getInt(1));
                vmID.add(rs.getString(2));
            }
            int reVmID1 = 1;
            int reVmID2;
            String[] number;
            for(int j=0; j<ID.size(); j++){
                if(!vmID.get(j).contains("-")){
                    vmID.set(j, Integer.toString(reVmID1));
                    reVmID1++;
                }else{
                    number = vmID.get(j).split("-");
                    if(number[0].equals("")){
                        reVmID2 = reVmID1 + Integer.parseInt(number[1]) - 1;
                        vmID.set(j, Integer.toString(reVmID1) + "-" + Integer.toString(reVmID2));
                        reVmID1 = reVmID2 + 1;
                    }else{
                        reVmID2 = reVmID1 + Integer.parseInt(number[1]) - Integer.parseInt(number[0]);
                        vmID.set(j, Integer.toString(reVmID1) + "-" + Integer.toString(reVmID2));
                        reVmID1 = reVmID2 + 1;
                    }
                }
            }
            for(int j=0; j<ID.size(); j++){
                sql = "UPDATE Vm SET 'Vm ID'='" + vmID.get(j) + "' WHERE ID=" + ID.get(j) +"";
                stm.executeUpdate(sql);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static int getNumberOfVm(Statement stm){
        String sql = "SELECT \"Vm ID\" FROM Vm";
        int numberVm = 0;
        String temp;
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                temp = rs.getString(1);
                if(!temp.contains("-")){
                    numberVm++;
                }else{
                    String[] number = temp.split("-");
                    numberVm += Integer.parseInt(number[1]) - Integer.parseInt(number[0]) + 1;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return numberVm;
    }
}

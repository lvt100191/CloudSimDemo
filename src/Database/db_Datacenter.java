/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author GC
 */
public class db_Datacenter {
    public int ID;
    public String name;
    public String os;
    public String arch;
    public String vmm;
    public double timezone;
    public double costPerSecond;
    public double costPerRam;
    public double costPerStorage;
    public double costPerBw;
    public String vmMigration;
    
    public db_Datacenter(String name, String os, String arch, String vmm, double timezone, double costPerSecond, double costPerRam, double costPerStorage, double costPerBw,
            String vmMigration){
        this.name = name;
        this.os = os;
        this.arch = arch;
        this.vmm = vmm;
        this.timezone = timezone;
        this.costPerSecond = costPerSecond;
        this.costPerRam = costPerRam;
        this.costPerStorage = costPerStorage;
        this.costPerBw = costPerBw;
        this.vmMigration = vmMigration;
    }
    
    public db_Datacenter(int ID, String name, String os, String arch, String vmm, double timezone, double costPerSecond, double costPerRam, double costPerStorage, double costPerBw, String vmMigration){
        this.ID = ID;
        this.name = name;
        this.os = os;
        this.arch = arch;
        this.vmm = vmm;
        this.timezone = timezone;
        this.costPerSecond = costPerSecond;
        this.costPerRam = costPerRam;
        this.costPerStorage = costPerStorage;
        this.costPerBw = costPerBw;
        this.vmMigration = vmMigration;
    }
    
    public void Insert(Statement stm){       
        String sql = "INSERT INTO Datacenter VALUES(?,'" + name + "','" + os + "','" + arch + "','" + vmm + "'," + timezone + "," + costPerSecond + "," + costPerRam + "," + costPerStorage + "," + costPerBw + ",'" + vmMigration + "')";
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void Del(Statement stm, int id){
        String sql;
        db_Host.delHostsOf1DC(stm, id);
        db_SAN.delSANsOf1DC(stm, id);
        if(id == -1){
            sql = "DELETE FROM Datacenter";
        }else{
            sql = "DELETE FROM Datacenter WHERE ID=" + id;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public void updateDatacenter(Statement stm, int id){
        String sql = "UPDATE Datacenter SET Name='" + name + "', 'Operating System'='" + os + "', Architecture='" + arch + "', 'VM Monitor'='" + vmm + "', 'Time zone'=" + timezone + ", 'Cost per Second'=" + costPerSecond + ", 'Cost per Ram'=" + costPerRam + ", 'Cost per Storage'=" + costPerStorage + ", 'Cost per Bandwidth'=" + costPerBw 
                                                    + ",'Vm Migration'='" + vmMigration + "' WHERE ID=" +id;
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static String[] getCost(Statement stm, int id){
        String[] data = new String[4];
        String sql = "SELECT \"Cost per Second\", \"Cost per Ram\", \"Cost per Storage\", \"Cost per Bandwidth\" FROM Datacenter WHERE ID=" +id;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                for(int i=0; i<4; i++){
                    data[i] = rs.getString(i+1);
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
        return data;
    }
    
    public static int getNumberDatacenter(Statement stm){
        String sql = "SELECT COUNT(*) FROM Datacenter";
        int number = 0;
        try{
            ResultSet rs = stm.executeQuery(sql);    
            if(rs.next()) number = rs.getInt(1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
        return number;
    }
    
    public static LinkedList<db_Datacenter> getAllDatacenter(Statement stm){
        LinkedList listData = new LinkedList();
        String sql = "SELECT * FROM Datacenter";
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                listData.add(new db_Datacenter(rs.getInt(1),rs.getString(2),rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8), rs.getDouble(9), rs.getDouble(10), rs.getString(11)));            
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return listData;
    }
    
    public static int getLastID(Statement stm){
        String sql = "SELECT MAX(ID) AS [IDENTITY] FROM Datacenter";
        int ID=0;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) ID = rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
        }
        return ID;
    }
    
    public static boolean checkName(Statement stm, String name, int ID){
        String sql;
        int count = 0;
        if(ID == -1) sql = "SELECT COUNT(*) FROM Datacenter WHERE Name='" + name + "'";
        else sql = "SELECT COUNT(*) FROM Datacenter WHERE Name='" + name + "' AND ID!="+ID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) count = rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
        }
        if(count == 0) return false;
        else return true;
    }
}

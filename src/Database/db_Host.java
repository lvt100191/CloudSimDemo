/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author GC
 */
public class db_Host {
//    static Connection con;
//    static Statement stm;
    
    public int ID;
    public int rootID;
    public String hostID;
    public String hostType;
    public long bw;
    public String vmPolicy;
    
    public db_Host(int rootID, String hostID, String hostType, long bw, String vmPolicy){
        this.rootID = rootID;
        this.hostID = hostID;
        this.hostType = hostType;
        this.bw = bw;
        this.vmPolicy = vmPolicy;
    }
    
    public db_Host(String hostID, String hostType, long bw, String vmPolicy){
        this.hostID = hostID;
        this.hostType = hostType;
        this.bw = bw;
        this.vmPolicy = vmPolicy;
    }   
    
    public void Insert(Statement stm){
        try{
            String sql = "INSERT INTO Host VALUES(?," + rootID + ",'" + hostID + "','" + hostType + "'," + bw + ",'" + vmPolicy + "')";
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void InsertDefault(Statement stm,int rootID){
        try{
            String sql = "INSERT INTO Host VALUES(?," +rootID+ ",'1','Demo', 1000000,'Time-Shared')";
            stm.executeUpdate(sql);
        }catch(Exception e){
            //System.out.println(e);
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void Del(Statement stm, int id){
        String sql;
        if(id == -1){
            sql = "DELETE FROM Host";
        }else{
            sql = "DELETE FROM Host WHERE ID=" + id;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public static void delHostsOf1DC(Statement stm, int rootID){
        String sql;
        if(rootID == -1){
            sql = "DELETE FROM Host";
        }else{
            sql = "DELETE FROM Host WHERE rootID=" + rootID;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public void updateHost(Statement stm, int id){
        String sql = "UPDATE Host SET 'Host ID'='" + hostID + "','Host Type'='" + hostType + "', Bandwidth=" + bw + ",'Vm Scheduler Policy'='" + vmPolicy + "' WHERE ID=" +id;
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static LinkedList<db_Host> getAllHostOF1DC(Statement stm, int rootID){
        LinkedList listData = new LinkedList<>();
        String sql = "SELECT \"Host ID\", \"Host Type\", Bandwidth, \"Vm Scheduler Policy\" FROM Host WHERE rootID=" + rootID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                listData.add(new db_Host(rs.getString(1),rs.getString(2),rs.getLong(3), rs.getString(4)));            
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return listData;
    }
    
    public static int getLastID(Statement stm, int rootID){
        String sql = "SELECT MAX(ID) AS [IDENTITY] FROM Host WHERE rootID=" + rootID;
        int ID = 0;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) ID = rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
        }     
        return ID;
    }
    
    public static String getLastHostID(Statement stm, int rootID){
        int ID = db_Host.getLastID(stm, rootID);
        String hostID = null;
        if(ID==0) return "0";
        String sql = "SELECT \"Host ID\" FROM Host WHERE ID=" +ID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                hostID = rs.getString(1);
            }
            if(hostID.contains("-")){
                String[] num = hostID.split("-");
                hostID = num[1];
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return hostID;
    }
    
    public static void updateLastHostID(Statement stm, int rootID, int lastHostID, String number){
        String hostID;
        int hostID1, hostID2;
        hostID1 = lastHostID + 1;
        if(number.equals("1")){
            hostID = Integer.toString(hostID1);
        }else{
            String[] num = number.split("-");
            hostID2 = hostID1 + Integer.parseInt(num[1]) - 1;
            hostID = Integer.toString(hostID1) + "-" + Integer.toString(hostID2);
        }
        String sql = "UPDATE Host SET 'Host ID'='" + hostID + "' WHERE ID=" + db_Host.getLastID(stm, rootID);
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static void updateHostID(Statement stm, int rootID){
        ArrayList<Integer> ID = new ArrayList<Integer>();
        ArrayList<String> hostID = new ArrayList<String>();
        String sql = "SELECT ID, \"Host ID\" FROM Host WHERE rootID=" + rootID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                ID.add(rs.getInt(1));
                hostID.add(rs.getString(2));
            }
            int reHostID1 = 1;
            int reHostID2;
            String[] number;
            for(int j=0; j<ID.size(); j++){
                if(!hostID.get(j).contains("-")){
                    hostID.set(j, Integer.toString(reHostID1));
                    reHostID1++;
                }else{
                    number = hostID.get(j).split("-");
                    if(number[0].equals("")){
                        reHostID2 = reHostID1 + Integer.parseInt(number[1]) - 1;
                        hostID.set(j, Integer.toString(reHostID1) + "-" + Integer.toString(reHostID2));
                        reHostID1 = reHostID2 + 1;
                    }else{
                        reHostID2 = reHostID1 + Integer.parseInt(number[1]) - Integer.parseInt(number[0]);
                        hostID.set(j, Integer.toString(reHostID1) + "-" + Integer.toString(reHostID2));
                        reHostID1 = reHostID2 + 1;
                    }
                }
            }
            for(int j=0; j<ID.size(); j++){
                sql = "UPDATE Host SET 'Host ID'='" + hostID.get(j) + "' WHERE ID=" + ID.get(j) +"";
                stm.executeUpdate(sql);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public static int getNumberOfHost(Statement stm){
        String sql = "SELECT \"Host ID\" FROM Host";
        int numberHost = 0;
        String temp;
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                temp = rs.getString(1);
                if(!temp.contains("-")){
                    numberHost++;
                }else{
                    String[] number = temp.split("-");
                    numberHost += Integer.parseInt(number[1]) - Integer.parseInt(number[0]) + 1;
                }
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return numberHost;
    }
}

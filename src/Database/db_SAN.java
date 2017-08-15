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
public class db_SAN {
    public int ID;
    public int rootID;
    public String name;
    public long capacity;
    public long bw;
    public float latency;
    
    public db_SAN(int rootID, String name, long capacity, long bw, float latency){
        this.rootID = rootID;
        this.name = name;
        this.capacity = capacity;
        this.bw = bw;
        this.latency = latency;
    }
    
    public db_SAN(String name, long capacity, long bw, float latency){
        this.name = name;
        this.capacity = capacity;
        this.bw = bw;
        this.latency = latency;
    }
    
    public void Insert(Statement stm){
        try{
            String sql = "INSERT INTO SAN VALUES(?," + rootID + ",'" + name + "'," + capacity + "," + bw + "," + latency + ")";
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void Del(Statement stm, int id){
        String sql;
        if(id == -1){
            sql = "DELETE FROM SAN";
        }else{
            sql = "DELETE FROM SAN WHERE ID=" + id;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public static void delSANsOf1DC(Statement stm, int rootID){
        String sql;
        if(rootID == -1){
            sql = "DELETE FROM SAN";
        }else{
            sql = "DELETE FROM SAN WHERE rootID=" + rootID;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public void updateSAN(Statement stm,int id){
        String sql = "UPDATE SAN SET Name='" + name + "',Capacity=" + capacity + ", Bandwidth=" + bw + ",'Network Latency'=" + latency + " WHERE ID=" +id;
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static LinkedList<db_SAN> getAllSANOF1DC(Statement stm, int rootID){
        LinkedList listData = new LinkedList<>();
        String sql = "SELECT Name, Capacity, Bandwidth, \"Network Latency\" FROM SAN WHERE rootID=" + rootID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()){
                listData.add(new db_SAN(rs.getString(1),rs.getLong(2), rs.getLong(3), rs.getFloat(4)));            
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return listData;
    }
}

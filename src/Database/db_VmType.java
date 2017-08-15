/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author GC
 */
public class db_VmType {
    static Connection con;
    static Statement stm;
    
    public int ID;
    public String name;
    public int numPes;
    public double mips;
    public int ram;
    public long storage;
    public long bw;
    
    public db_VmType(String name, int numPes, double mips, int ram, long storage, long bw){
        this.name = name;
        this.numPes = numPes;
        this.mips = mips;
        this.ram = ram;
        this.storage = storage;
        this.bw = bw;
    }
    
    public static void Open(){
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:scenarioDB/TypeDatabase.db");
            stm = con.createStatement();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi kết nối Database");
            System.exit(0);
        }
    }
    
    public static void Close(){
        try{
            stm.close();
            con.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi đóng kết nối Database");
            System.exit(0);
        }
    }
    
    public void Insert(){
        try{
            String sql = "INSERT INTO VmType VALUES(?,'" + name + "'," + numPes + "," + mips + "," + ram + "," + storage + "," + bw + ")";
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public void Update(int id){
        try{
            String sql = "UPDATE VmType SET Name='" + name + "','Number of Pes'=" + numPes + ",'Mips per Pes'=" + mips + ",Ram=" + ram + ",Storage=" + storage + ",Bandwidth=" + bw + " WHERE ID=" +id; 
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static void Del(String name){
        String sql = "DELETE FROM VmType WHERE Name='" + name + "'";
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public static int getID(String name){
        String sql = "SELECT ID FROM VmType WHERE Name='" + name + "';";
        int id = -1;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) id = rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
        }
        return id;
    }
    
    public static db_VmType getVmType(String name){
        String sql = "SELECT * FROM VmType WHERE Name='" + name + "'";
        db_VmType vmType = null;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                vmType = new db_VmType(rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5), rs.getLong(6), rs.getLong(7));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
        return vmType;
    }
    
    public static ArrayList getAllVmType(){
        String sql = "Select Name FROM VmType";
        ArrayList typeList = new ArrayList();
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()) typeList.add(rs.getString(1));
        }catch(Exception e){
            System.out.println(e);
        }
        return typeList;
    }
    
    public static String[] getTypeInfo(String name){
        String[] data = new String[5];
        String sql = "SELECT \"Number of Pes\", \"Mips per Pes\", Ram, Storage, Bandwidth FROM VmType Where Name='" + name + "'";
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public static boolean checkName(String name, int ID){
        String sql;
        int count = 0;
        if(ID == -1) sql = "SELECT COUNT(*) FROM VmType WHERE Name='" + name + "'";
        else sql = "SELECT COUNT(*) FROM VmType WHERE Name='" + name + "' AND ID!="+ID;
        try{
            Open();
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) count = rs.getInt(1);
            Close();
        }catch(Exception e){
            System.out.println(e);
        }
        if(count == 0) return false;
        else return true;
    }
}

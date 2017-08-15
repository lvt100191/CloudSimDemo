/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

//import static Database.Database.con;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author GC
 */
public class db_HostType {
    static Connection con;
    static Statement stm;
    
    public int ID;
    public String name;
    public int numPes;
    public double mips;
    public int ram;
    public long storage;
    public String powerModel;
    public double maxPower;
    public double staticPower;
    
    public db_HostType(String name, int numPes, double mips, int ram, long storage, String powerModel, double maxPower, double staticPower){
        this.name = name;
        this.numPes = numPes;
        this.mips = mips;
        this.ram = ram;
        this.storage = storage;
        this.powerModel = powerModel;
        this.maxPower = maxPower;
        this.staticPower = staticPower;
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
            String sql = "INSERT INTO HostType VALUES(?,'" + name + "'," + numPes + "," + mips + "," + ram + "," + storage + ",'" + powerModel +"'," + maxPower + "," + staticPower + ")";
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public void Update(int id){
        try{
            String sql = "UPDATE HostType SET Name='" + name + "',NumberOfPes=" + numPes + ",MipsPerPes=" + mips + ",Ram=" + ram + ",Storage=" + storage + ",PowerModel='" + powerModel + "',MaxPower=" + maxPower + ",StaticPower=" + staticPower + " WHERE ID=" +id; 
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static void Del(String name){
        String sql = "DELETE FROM HostType WHERE Name='" + name + "'";
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public static int getID(String name){
        String sql = "SELECT ID FROM HostType WHERE Name='" + name + "';";
        int id = -1;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) id = rs.getInt(1);
        }catch(Exception e){
            System.out.println(e);
        }
        return id;
    }
    
    public static db_HostType getHostType(String name){
        String sql = "SELECT * FROM HostType WHERE Name='" + name + "'";
        db_HostType hostType = null;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                hostType = new db_HostType(rs.getString(2), rs.getInt(3), rs.getDouble(4), rs.getInt(5), rs.getLong(6), rs.getString(7), rs.getDouble(8), rs.getDouble(9));
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
        return hostType;
    }
    
    public static ArrayList getAllHostType(){
        String sql = "Select Name FROM HostType";
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
        String[] data = new String[7];
        String sql = "SELECT NumberofPes, MipsperPes, Ram, Storage, PowerModel, MaxPower, StaticPower FROM HostType Where Name='" + name + "'";
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) {
                data[0] = rs.getString(1);
                data[1] = rs.getString(2);
                data[2] = rs.getString(3);
                data[3] = rs.getString(4);
                data[4] = rs.getString(5);
                data[5] = rs.getString(6);
                data[6] = rs.getString(7);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public static boolean checkName(String name, int ID){
        String sql;
        int count = 0;
        if(ID == -1) sql = "SELECT COUNT(*) FROM HostType WHERE Name='" + name + "'";
        else sql = "SELECT COUNT(*) FROM HostType WHERE Name='" + name + "' AND ID!="+ID;
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

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
public class Database {
//    static Connection con;
//    static Statement stm;
        
    public static Connection Open(String dbName){
        Connection con = null;
        try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:scenarioDB/" + dbName + ".db");
            //stm = con.createStatement();
        }catch(Exception e) {
            JOptionPane.showMessageDialog(null,"Lỗi kết nối Database");
            System.exit(0);
        }
        return con;
    }
    
//    public static void Close(Connection con){
//        try{
//            //stm.close();
//            con.close();
//        }catch(Exception e){
//            JOptionPane.showMessageDialog(null,"Lỗi đóng kết nối Database");
//        }
//    }
    
    //Khởi tạo các table trong db
    public static void createAll(Statement stm){
        try{
            createSettingTable(stm);
            createDatacenterTable(stm);
            createHostTable(stm);
            createUserTable(stm);
            createVmTable(stm);
            createSANTable(stm);
            createAutoCloudletTable(stm);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());
            System.exit(0);
        }
    }
    
    
    public static void createTypeDatabase(Statement stm){
        try{
            createHostTypeTable(stm);
            createVmTypeTable(stm);           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: " + e.getMessage());
            System.exit(0);
        }
    }
    
    protected static void createSettingTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS Setting("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "simulationTime REAL NOT NULL,"
                + "vmAllocationPolicy TEXT NOT NULL,"
                + "vmSelectionPolicy TEXT NOT NULL,"
                + "upperThreshold REAL NOT NULL,"
                + "lowerThreshold REAL NOT NULL,"
                + "dcInterval REAL NOT NULL,"
                + "vmInterval REAL NOT NULL);";
        try{
            stm.executeUpdate(sql);
            sql = "INSERT INTO Setting VALUES(?,1440,'Available Cpu Capicity Best Fit','Minimum Migration Time',80,20,300,300)";
            stm.executeUpdate(sql);
        }catch(Exception e){} 
    }
    
    protected static void createDatacenterTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS Datacenter("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "Name TEXT NOT NULL,"
                + "'Operating System' TEXT NOT NULL,"
                + "Architecture TEXT NOT NULL,"
                + "'VM Monitor' TEXT NOT NULL,"
                + "'Time zone' REAL NOT NULL,"
                + "'Cost per Second' REAL NOT NULL,"
                + "'Cost per Ram' REAL NOT NULL,"
                + "'Cost per Storage' REAL NOT NULL,"
                + "'Cost Per Bandwidth' REAL NOT NULL,"
                + "'Vm Migration' TEXT NOT NULL);";       
        try{
            stm.executeUpdate(sql);
            sql = "INSERT INTO Datacenter VALUES(?,'Datacenter_1','Linux','x86','Xen', 0, 3, 0.05, 0.0001, 0,'Enable')";
            stm.executeUpdate(sql);
        }catch(Exception e){}        
    }
    
    protected static void createHostTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS Host("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "rootID INTEGER NOT NULL,"
                + "'Host ID' TEXT NOT NULL,"
                + "'Host Type' TEXT NOT NULL,"
                + "Bandwidth INTEGER NOT NULL,"
                + "'VM Scheduler Policy' TEXT NOT NULL);";
        try{
            stm.executeUpdate(sql);
            sql = "INSERT INTO Host VALUES(?," +db_Datacenter.getLastID(stm)+ ",'1','Demo', 1000000,'Time-Shared')";
            stm.executeUpdate(sql);
        }catch(Exception e){}        
    }
    
    public static void createHostTypeTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS HostType("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "Name TEXT NOT NULL,"
                + "NumberOfPes INTEGER NOT NULL,"
                + "MipsPerPes REAL NOT NULL,"
                + "Ram INTEGER NOT NULL,"
                + "Storage INTEGER NOT NULL,"
                + "PowerModel TEXT NOT NULL,"
                + "MaxPower REAL NOT NULL,"
                + "StaticPower REAL NOT NULL);";
        try{
            stm.executeUpdate(sql);
            sql = "INSERT INTO HostType VALUES(?,'Demo', 2, 2000, 4096, 10000000, 'Cubic', 170, 0.7)";
            stm.executeUpdate(sql);           
        }catch(Exception e){}        
    }
    
    protected static void createUserTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS User("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "'User ID' INTEGER NOT NULL);";
        try{
            stm.executeUpdate(sql);
            sql = "INSERT INTO User VALUES(?, 1)";
            stm.executeUpdate(sql);
        }catch(Exception e){}
    }
    
    protected static void createVmTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS Vm("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "rootID INTEGER NOT NULL,"
                + "'Vm ID' TEXT NOT NULL,"
                + "'Vm Type' TEXT NOT NULL,"
                + "'VM Monitor' TEXT NOT NULL,"
                + "'Cloudlet Scheduler Policy' TEXT NOT NULL);";
        try{
            stm.executeUpdate(sql);
            sql = "INSERT INTO Vm VALUES(?," + db_User.getLastID(stm) + ",'1','Demo','Xen','DynamicWorkload')";
            stm.executeUpdate(sql);
        }catch(Exception e){}
    }
    
    public static void createVmTypeTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS VmType("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "Name TEXT NOT NULL,"
                + "'Number of Pes' INTEGER NOT NULL,"
                + "'Mips per Pes' REAL NOT NULL,"
                + "Ram INTEGER NOT NULL,"
                + "Storage INTEGER NOT NULL,"
                + "Bandwidth INTEGER NOT NULL);";
        try{
            stm.executeUpdate(sql);
            sql = "INSERT INTO VmType VALUES(?,'Demo', 1, 1000, 1024, 1000000, 100000)";
            stm.executeUpdate(sql);
        }catch(Exception e){}
    }
    
    protected static void createSANTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS SAN("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "rootID INTEGER NOT NULL,"
                + "Name TEXT NOT NULL,"
                + "Capacity INTEGER NOT NULL,"
                + "Bandwidth INTEGER NOT NULL,"
                + "'Network Latency' REAL NOT NULL);";
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){}
    }
    
    protected static void createCloudletTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS Cloudlet("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "rootID INTEGER NOT NULL,"
                + "'Cloudlet ID' TEXT NOT NULL,"
                + "'Size of Cloudlet' INTEGER NOT NULL,"
                + "'Input File size' INTEGER NOT NULL,"
                + "'Output File size' INTEGER NOT NULL,"
                + "'Number of Pes' INTEGER NOT NULL,"
                + "'Utilization Model Cpu' TEXT NOT NULL,"
                + "'Utilization Model Ram' TEXT NOT NULL,"
                + "'Utilization Model Bw' TEXT NOT NULL);";
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){}
    }
    
    protected static void createAutoCloudletTable(Statement stm){
        String sql = "CREATE TABLE IF NOT EXISTS AutoCloudlet("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                + "rootID INTEGER NOT NULL,"
                + "minSizeOfCloudlet INTEGER NOT NULL,"
                + "maxSizeOfCloudlet INTEGER NOT NULL,"
                + "minInputFileSize INTEGER NOT NULL,"
                + "maxInputFileSize INTEGER NOT NULL,"
                + "minOutputFileSize INTEGER NOT NULL,"
                + "maxOutputFileSize INTEGER NOT NULL,"
                + "minNumberPes INTEGER NOT NULL,"
                + "maxNumberPes INTEGER NOT NULL,"
                + "utilizationModelCpu TEXT NOT NULL,"
                + "utilizationModelRam TEXT NOT NULL,"
                + "utilizationModelBw TEXT NOT NULL,"
                + "keepSeed TEXT NOT NULL);";
        try{
            stm.executeUpdate(sql);
          db_Cloudlet.InsertDefault(stm, db_User.getLastID(stm));
            stm.executeUpdate(sql);
        }catch(Exception e){}
    }
    
    public static ResultSet getDatacenterTable(Statement stm) throws SQLException{
        String sql = "SELECT ID, Name, \"Operating System\", Architecture, \"VM Monitor\", \"Time zone\", \"Vm Migration\" FROM Datacenter";
        return  stm.executeQuery(sql);
    }
    
    public static ResultSet getUserTable(Statement stm) throws SQLException{
        String sql = "SELECT ID, \"User ID\" FROM User ORDER BY \"User ID\" ASC";
        return  stm.executeQuery(sql);
    } 
    
    public static ResultSet getVmTable(Statement stm, int rootID) throws SQLException{
        String sql = "SELECT ID, \"Vm ID\", \"Vm Type\", \"VM Monitor\", \"Cloudlet Scheduler Policy\" FROM Vm WHERE rootID=" + rootID;
        return  stm.executeQuery(sql);
    }
    
    public static ResultSet getHostTable(Statement stm, int rootID) throws SQLException{
        String sql = "SELECT ID, \"Host ID\", \"Host Type\", Bandwidth, \"Vm Scheduler Policy\" FROM Host WHERE rootID=" + rootID;
        return  stm.executeQuery(sql);
    }
    
    public static ResultSet getCloudletTable(Statement stm, int rootID) throws SQLException{
        String sql = "SELECT ID, \"Cloudlet ID\", \"Size of Cloudlet\", \"Input File size\", \"Output File size\", \"Number of Pes\", \"Utilization Model Cpu\", \"Utilization Model Ram\", \"Utilization Model Bw\"  FROM Cloudlet WHERE rootID=" + rootID;
        return  stm.executeQuery(sql);
    }
    
    public static ResultSet getSANTable(Statement stm, int rootID) throws SQLException{
        String sql = "SELECT ID, Name, Capacity, Bandwidth, \"Network Latency\" FROM SAN WHERE rootID=" + rootID;
        return  stm.executeQuery(sql);
    }
    
    public static void updateSetting(Statement stm, double simulationTime, String vmAllocationPolicy, String vmSelectionPolicy, double upperThreshold, double lowerThreshold, double dcInterval, double vmInterval){
        String sql = "UPDATE Setting SET simulationTime=" + simulationTime + ",vmAllocationPolicy='" + vmAllocationPolicy + "',vmSelectionPolicy='" + vmSelectionPolicy + "',upperThreshold=" + upperThreshold + ",lowerThreshold=" + lowerThreshold + ",dcInterval=" + dcInterval + ",vmInterval=" + vmInterval;
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    //String sql = "INSERT OR REPLACE INTO Setting (simulationTime, vmAllocationPolicy, vmSelectionPolicy ,upperThreshold, lowerThreshold, dcInterval, vmInterval) values(" + simulationTime + ",'" + vmAllocationPolicy + "','" + vmSelectionPolicy + "'," + upperThreshold + "," + lowerThreshold + "," + dcInterval + "," + vmInterval + ")";
//    public static void insertSettingDefault(Statement stm){
//        String sql = "INSERT INTO Setting VALUES(?," 
//    }
    
    public static ArrayList getSetting(Statement stm){
        ArrayList setting = new ArrayList();
        String sql = "SELECT * FROM Setting";
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                setting.add(rs.getDouble(2));
                setting.add(rs.getString(3));
                setting.add(rs.getString(4));
                setting.add(rs.getDouble(5));
                setting.add(rs.getDouble(6));
                setting.add(rs.getDouble(7));
                setting.add(rs.getDouble(8));                
            }
            rs.close();     
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
        return setting;
    }
}

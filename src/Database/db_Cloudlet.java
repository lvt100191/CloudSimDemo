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
public class db_Cloudlet {
    public int ID;
    public int rootID;
    public long minSize;
    public long maxSize;
    public long minInputSize;
    public long maxInputSize;
    public long minOutputSize;
    public long maxOutputSize;
    public int minNumPes;
    public int maxNumPes;
    public String utilModelCpu;
    public String utilModelRam;
    public String utilModelBw;
    public String keepSeed;
    
    public db_Cloudlet(int rootID, long minSize, long maxSize, long minInputSize, long maxInputSize, long minOutputSize, long maxOutputSize
                    , int minNumPes, int maxNumPes, String utilModelCpu, String utilModelRam, String utilModelBw, String keepSeed){
        this.rootID = rootID;
        this.minSize = minSize;
        this.maxSize = maxSize;
        this.minInputSize = minInputSize;
        this.maxInputSize = maxInputSize;
        this.minOutputSize = minOutputSize;
        this.maxOutputSize = maxOutputSize;
        this.minNumPes = minNumPes;
        this.maxNumPes = maxNumPes;
        this.utilModelCpu = utilModelCpu;
        this.utilModelRam = utilModelRam;
        this.utilModelBw = utilModelBw;   
        this.keepSeed = keepSeed;
    }
    
    public void Insert(Statement stm){
        try{
            String sql = "INSERT INTO AutoCloudlet VALUES(?," + rootID + "," + minSize + "," + maxSize + "," + minInputSize + "," + maxInputSize + "," + minOutputSize + "," + maxOutputSize
                    + "," + minNumPes + "," + maxNumPes + ",'" + utilModelCpu + "','" + utilModelRam + "','" + utilModelBw + "','" + keepSeed + "')";
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void InsertDefault(Statement stm, int rootID){
        try{
            String sql = "INSERT INTO AutoCloudlet VALUES(?," + rootID + ", 2500, 2500,300,300,300,300,1,1,'Stochastic','Null','Null','Enable')";
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    
    public static void Del(Statement stm, int rootId){
        String sql;
        if(rootId == -1){
            sql = "DELETE FROM Cloudlet";
        }else{
            sql = "DELETE FROM AutoCloudlet WHERE rootID=" + rootId;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public void updateCloudlet(Statement stm, int id){
        String sql = "UPDATE AutoCloudlet SET minSizeOfCloudlet=" +minSize+ ",maxSizeOfCloudlet=" + maxSize + ",minInputFileSize=" + minInputSize + ",maxInputFileSize=" + maxInputSize + ",minOutputFileSize=" + minOutputSize
                + ",maxOutputFileSize=" +maxOutputSize+ ",minNumberPes=" +minNumPes+ ",maxNumberPes=" +maxNumPes+ ",utilizationModelCpu='" + utilModelCpu + "',utilizationModelRam='" + utilModelRam + "',utilizationModelBw='" + utilModelBw + "',keepSeed='" + keepSeed + "' WHERE rootID=" +id;
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static ArrayList getCloudletTable(Statement stm, int rootID){
        ArrayList data = new ArrayList();
        String sql = "SELECT minSizeOfCloudlet, maxSizeOfCloudlet, minInputFileSIze, maxInputFileSize, minOutputFileSize, maxOutputFileSize, minNumberPes, maxNumberPes, utilizationModelCpu, utilizationModelRam, utilizationModelBw, keepSeed  FROM AutoCloudlet WHERE rootID=" + rootID;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()){
                for(int i=1; i<7; i++){
                    data.add(rs.getLong(i));
                }
                data.add(rs.getInt(7));
                data.add(rs.getInt(8));
                for(int i=9; i<13; i++){
                    data.add(rs.getString(i));
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi truy xuất Database");
        }
        return  data;
    }
}

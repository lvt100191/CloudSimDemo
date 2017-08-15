/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author GC
 */
public class db_User {
    
    public int ID;
    public int userID;
    
    public db_User(int userID){
        this.userID = userID;
    }
    
    public db_User(int ID, int userID){
        this.ID = ID;
        this.userID = userID;
    }
    
    
    public static void Insert(Statement stm){
        int uID = getLastUserID(stm);
        if(uID == -1) uID = 1;
        else uID++;
        try{
            String sql = "INSERT INTO User VALUES(?," + uID + ")";
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi thêm vào Database");
        }
    }
    
    public static void Del(Statement stm, int id){
        String sql;
        db_Vm.delVmsOf1User(stm, id);
//        db_Cloudlet.delCloudletsOf1User(stm, id);
        db_Cloudlet.Del(stm, id);
        int uID = getUserID(stm, id);
        int totalUser = getTotalUser(stm);
        int lastUserID = getLastUserID(stm);
        if(uID != totalUser) updateUserID(stm, uID, lastUserID);
        if(id == -1){
            sql = "DELETE FROM User";
        }else{
            sql = "DELETE FROM User WHERE ID=" + id;
        }
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi xóa Database");
        }
    }
    
    public static void updateUserID(Statement stm, int uID1, int uID2){
        String sql = "UPDATE User SET 'User ID'=" + uID1 + " WHERE \"User ID\"=" +uID2;
        try{
            stm.executeUpdate(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi cập nhật Database");
        }
    }
    
    public static int getTotalUser(Statement stm){
        int total = 0;
        String sql = "SELECT COUNT(*) FROM User";
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) total = rs.getInt(1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi truy vấn Database");
        }
        return total;
    }
    
    public static int getUserID(Statement stm, int id){
        int uID = -1;
        String sql = "SELECT \"User ID\" FROM User WHERE ID=" + id;
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) uID = rs.getInt(1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi truy vấn Database");
        }
        return uID;
    }
    
    public static LinkedList<db_User> getAllUser(Statement stm){
        LinkedList userList = new LinkedList();
        String sql = "SELECT * FROM User";
        try{
            ResultSet rs = stm.executeQuery(sql);
            while(rs.next()) userList.add(new db_User(rs.getInt(1), rs.getInt(2)));
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi truy vấn Database");
        }
        return userList;
    }
    
    public static int getLastUserID(Statement stm){
        int uID = 0;
        String sql = "SELECT MAX(\"User ID\") FROM User";
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) uID = rs.getInt(1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi truy vấn Database");
        }
        return uID;
    }
    
    public static int getLastID(Statement stm){
        int ID = 0;
        String sql = "SELECT MAX(ID) FROM User";
        try{
            ResultSet rs = stm.executeQuery(sql);
            if(rs.next()) ID = rs.getInt(1);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Lỗi truy vấn Database");
        }
        return ID;
    }
}

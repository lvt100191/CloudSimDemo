/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import Database.db_Datacenter;
import Database.db_VmType;
import java.sql.Statement;

/**
 *
 * @author GC
 */
public class Check {
    

    public static int checkEmpty(String str){
        if(str.equals("")) return 1;
        else return 0;
    }
    
    public static int checkZero(String str){
        if(str.equals("")) return 1;
        else if(Integer.parseInt(str) == 0) return 2;
        else return 0;
    }
    
    public static int checkDatacenterName(Statement stm, String str, int ID){
        if(str.equals("")) return 1;
        else if(!db_Datacenter.checkName(stm, str, ID)) return 0;
        else return 2;
    }
    
    public static int checkVmTypeName(String str, int ID){
        if(str.equals("")) return 1;
        else if(!db_VmType.checkName(str, ID)) return 0;
        else return 2;
    }
    
    public static int checkHostTypeName(String str, int ID){
        if(str.equals("")) return 1;
        else if(!db_VmType.checkName(str, ID)) return 0;
        else return 2;
    }
    
    public static int checkMipsValue(String str){
        if(str.equals("")) return 1;
        else if(!str.equals(".") && Double.parseDouble(str) >= 0.000001) return 0;
        else return 2;
    }
    
    
    public static int checkTimezone(String str){
        if(str.equals("")) return 1;
        else if(!str.equals(".")) return 0;
        else return 2;
    }
    
    public static int checkNegative(String str){
        if(str.equals("")) return 1;
        else if(!str.equals(".") && Double.parseDouble(str) >= 0) return 0;
        else return 2;
    }
    
    public static int checkNegative0(String str){
        if(str.equals("")) return 1;
        else if(!str.equals(".") && Double.parseDouble(str) > 0) return 0;
        else return 2;
    }    
    

}

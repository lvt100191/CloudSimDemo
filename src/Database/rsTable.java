/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author GC
 */
public class rsTable extends AbstractTableModel{
    private Vector columnName;
    private Vector rData;
    
    public rsTable(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        
        columnName = new Vector(columnCount);
        rData = new Vector();
        
        for(int i=0; i<columnCount; i++){
            columnName.addElement(metaData.getColumnName(i+1));
        }
        while(rs.next()){
            Vector data = new Vector(columnCount);
            for(int i=0; i<columnCount; i++){
                data.addElement(rs.getObject(i+1));
            }
            rData.addElement(data);
        }
    }
    
    public int getColumnCount(){
        return columnName.size();
    }
    
    public int getRowCount(){
        return rData.size();
    }
    
    public Object getValueAt(int row, int col){
        Vector rowData = (Vector) rData.elementAt(row);
        return rowData.elementAt(col);
    }
    
    
    @Override
    public String getColumnName(int col){
        return (String) (columnName.elementAt(col));
    }
}

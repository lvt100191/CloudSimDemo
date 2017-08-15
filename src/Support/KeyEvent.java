/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Support;

import java.awt.event.KeyAdapter;
import javax.swing.JTextField;

/**
 *
 * @author GC
 */
public class KeyEvent extends KeyAdapter{
    private JTextField tf;
    private String option;
    private int size;
    
//    public static final int CHAR = 0;
//    public static final int NUMERIC = 1;
//    public static final int NUMERIC_DOT = 2;
//    public static final int BIND_VMID = 3;
    
    public KeyEvent(JTextField tf, String option, int size){
        this.tf = tf;
        this.option = option;
        this.size = size;
    }
    
    @Override
    public void keyTyped(java.awt.event.KeyEvent ke){
        if(size!=0 && tf.getText().length() >= size) ke.consume();
        int k = ke.getKeyChar();
        switch(option){
            case "CHAR": if ((k < '0' && k!='-' && k!='_' && k!=' ') || (k > '9' && k < 'A') || (k > 'Z' && k < 'a') || k > 'z') ke.consume();
                break;
            case "NUMERIC": if (k < '0' || k > '9') ke.consume();
                break;
            case "NUMERIC_DOT": if ((k < '0' || k > '9') && (k != '.' || tf.getText().contains("."))) ke.consume();
                break;
            default: break;    
        }
    }
}

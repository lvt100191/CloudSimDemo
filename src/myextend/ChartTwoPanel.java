/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myextend;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author NhuocQuy
 */
public class ChartTwoPanel extends  JFrame{
JPanel root;
    public ChartTwoPanel() {
        super("Demo Broker");
         root = new JPanel(new GridLayout(1,2));
        
        setContentPane(root);
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    public void addChart(JPanel chart){
        root.add(chart);
    }
}

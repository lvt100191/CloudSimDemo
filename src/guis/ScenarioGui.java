/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import java.awt.Dialog;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author GC
 */
public class ScenarioGui extends javax.swing.JDialog {

    public String scenarioName;
    /**
     * Creates new form ScenarioGui
     */
    public ScenarioGui(java.awt.Frame parent, boolean modal, String name) {
        super(parent, modal);
        try {          
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            initComponents();
            updateScenarioList(scenarioList);
            scenarioName = name;
            scenarioList.setSelectedValue(name, true);
//            bt_ChangeName.setVisible(true);
            //this.setLocationRelativeTo(null);          
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            Logger.getLogger(ScenarioGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ScenarioGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ScenarioGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        scenarioList = new javax.swing.JList();
        bt_Create = new javax.swing.JButton();
        bt_Del = new javax.swing.JButton();
        bt_Copy = new javax.swing.JButton();
        bt_ChangeName = new javax.swing.JButton();
        bt_Cancel = new javax.swing.JButton();
        bt_OK = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Quản lý kịch bản");

        jPanel1.setBackground(new java.awt.Color(51, 153, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quản lý kịch bản", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        scenarioList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách kịch bản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        scenarioList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(scenarioList);

        bt_Create.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cloudaddsmall.png"))); // NOI18N
        bt_Create.setText("Tạo mới");
        bt_Create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CreateActionPerformed(evt);
            }
        });

        bt_Del.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/clouddelsmall.png"))); // NOI18N
        bt_Del.setText(" Xóa");
        bt_Del.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_DelActionPerformed(evt);
            }
        });

        bt_Copy.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/copysmall.png"))); // NOI18N
        bt_Copy.setText("Sao chép");
        bt_Copy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CopyActionPerformed(evt);
            }
        });

        bt_ChangeName.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cloudeditsmall.png"))); // NOI18N
        bt_ChangeName.setText("Đổi tên");
        bt_ChangeName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ChangeNameActionPerformed(evt);
            }
        });

        bt_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cancelsmall.png"))); // NOI18N
        bt_Cancel.setText("Đóng");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CancelActionPerformed(evt);
            }
        });

        bt_OK.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/oksmall.png"))); // NOI18N
        bt_OK.setText("Chọn");
        bt_OK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_OKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_OK, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bt_Create, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Del, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Copy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_ChangeName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bt_Cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_Cancel)
                            .addComponent(bt_OK)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(bt_Create)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Del)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_ChangeName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_Copy)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CancelActionPerformed
        dispose();
    }//GEN-LAST:event_bt_CancelActionPerformed

    private void bt_CreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CreateActionPerformed
        NewScenario ns = new NewScenario(NewScenario.CREATE, "");
        ns.setLocationRelativeTo(this);
        ns.setVisible(true);
        if(ns.done == true) updateScenarioList(scenarioList);
    }//GEN-LAST:event_bt_CreateActionPerformed

    private void bt_DelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_DelActionPerformed
        if(getDbName().length < 2){
            JOptionPane.showMessageDialog(null, "Bạn cần ít nhất một kịch bản để thực hiện mô phỏng");
            return;
        }
        int temp = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(temp == 0){
            String dbName = scenarioList.getSelectedValue().toString();
            File db = new File("scenarioDB\\" + dbName + ".db");
            db.delete();       
            updateScenarioList(scenarioList);
        }
    }//GEN-LAST:event_bt_DelActionPerformed

    private void bt_ChangeNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ChangeNameActionPerformed
        if(scenarioList.getSelectedValue().toString().equals(scenarioName)){
            JOptionPane.showMessageDialog(this, "Kịch bản này đang được sử dụng, không thể đổi tên");
        }else{
            NewScenario ns = new NewScenario(NewScenario.RENAME, scenarioList.getSelectedValue().toString());
            ns.setLocationRelativeTo(this);
            ns.setVisible(true);
            if(ns.done == true) updateScenarioList(scenarioList);
        }
    }//GEN-LAST:event_bt_ChangeNameActionPerformed

    private void bt_CopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CopyActionPerformed
        NewScenario ns = new NewScenario(NewScenario.COPY, scenarioList.getSelectedValue().toString());
        ns.setLocationRelativeTo(this);
        ns.setVisible(true);
        if(ns.done == true) updateScenarioList(scenarioList);
    }//GEN-LAST:event_bt_CopyActionPerformed

    private void bt_OKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_OKActionPerformed
        if(scenarioList.getSelectedValue() != null){
            scenarioName = (String)scenarioList.getSelectedValue();
            
            this.setVisible(false);
        }
    }//GEN-LAST:event_bt_OKActionPerformed

    
    public static String[] getDbName(){
        File db = new File("scenarioDB");
        File[] dbFiles = db.listFiles();
        
        int numFile = 0;
        if(dbFiles == null){
            return null;
        }else{
            for(File f : dbFiles){
                if(f.isFile() && !f.getName().equals("TypeDatabase.db") && f.getName().endsWith(".db")) numFile++;
            }
            String[] dbName = new String[numFile];
            int temp = 0;
            for(File f : dbFiles){
                if(f.isFile() && !f.getName().equals("TypeDatabase.db") && f.getName().endsWith(".db")){
                    String name = f.getName().replace(".db", "");
                    dbName[temp] = name;
                    temp++;
                }
            }
            return dbName;
        }
    }
    
    public static void updateScenarioList(JList list){
        String[] name = getDbName();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        if(name != null){
            for(int i=0; i<name.length; i++){
                listModel.addElement(name[i]);
            }
        }
        list.setModel(listModel);
    }
    
    
//    public static void updateList() {
//        scenarioList.setModel(new javax.swing.AbstractListModel(){
//            String[] strings = getDbName();
//            @Override
//            public int getSize() { return strings.length; }
//            @Override
//            public Object getElementAt(int i) { return strings[i]; }
//        });
//        scenarioList.updateUI();
//    }
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ScenarioGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ScenarioGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ScenarioGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ScenarioGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ScenarioGui dialog = new ScenarioGui(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_ChangeName;
    private javax.swing.JButton bt_Copy;
    private javax.swing.JButton bt_Create;
    private javax.swing.JButton bt_Del;
    private javax.swing.JButton bt_OK;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList scenarioList;
    // End of variables declaration//GEN-END:variables
}

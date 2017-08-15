/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guis;

import Database.db_Host;
import Database.db_HostType;
import Support.Check;
import Support.KeyEvent;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JErrorProvider;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author GC
 */
public class HostGui extends javax.swing.JDialog {

    public boolean done = true;
    public boolean isEditType = false;
    public final static String[] title = {"Thêm Host", "Sửa Host"};
    public final static String[] text = {"Thêm", "Sửa"};
    public final static int ADD = 0;
    public final static int EDIT = 1;
    public static Statement stm;
    
    private boolean doneType;
    private int option;
    private int ID;
    private int rootID;
    private int hostTypeID = -1;
    JErrorProvider error = new JErrorProvider();
    /**
     * Creates new form HostGui
     */
    public HostGui(java.awt.Frame parent, boolean modal, Statement stm, int rootID) {
        super(parent, modal);
        option = ADD;
        this.rootID = rootID;
        this.stm = stm;
        try {          
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            initComponents();       
            this.setLocationRelativeTo(parent);
            this.setTitle(title[option]);
            panel_HostGui.setBorder(javax.swing.BorderFactory.createTitledBorder(null, title[option], javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12)));
            bt_Ok.setText(text[option]); 
            updateTypeList(hostTypeList);
            hostTypeList.setSelectedIndex(0);
            updateHostTypeInfo(hostTypeList.getSelectedValue().toString());
            panel_PowerModel.setVisible(true);
            panel_PowerLoad.setVisible(false);
            bt_Ok1.setVisible(false);
            bt_Cancel1.setVisible(false);
            emptyName();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            Logger.getLogger(HostGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(HostGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HostGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public HostGui(java.awt.Frame parent, boolean modal, Statement stm, int rootID, String data[]) {
        super(parent, modal);
        option = EDIT;
        this.rootID = rootID;
        this.stm = stm;
        ID = Integer.parseInt(data[0]);
        try {          
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            initComponents();       
            this.setLocationRelativeTo(parent);
            this.setTitle(title[option]);
            panel_HostGui.setBorder(javax.swing.BorderFactory.createTitledBorder(null, title[option], javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12)));
            bt_Ok.setText(text[option]);
            panel_PowerModel.setVisible(true);
            panel_PowerLoad.setVisible(false);
            bt_Ok1.setVisible(false);
            bt_Cancel1.setVisible(false);
            emptyName();
            
            String number = data[1];
            if(number.contains("-")){
                String num[] = number.split("-");
                int k = Integer.parseInt(num[1]) - Integer.parseInt(num[0]) + 1;
                number = Integer.toString(k);
            }else number = "1";
            
            tf_NumHost.setText(number);
            hostTypeList.setSelectedValue(data[2], true);
            tf_Bw.setText(data[3]);
            cb_VmPolicy.setSelectedItem(data[4]);
            updateHostTypeInfo(data[2]);
            updateTypeList(hostTypeList);
            hostTypeList.setSelectedValue(data[2], true);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            Logger.getLogger(HostGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(HostGui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(HostGui.class.getName()).log(Level.SEVERE, null, ex);
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

        btg_PowerOption = new javax.swing.ButtonGroup();
        jPanel3 = new javax.swing.JPanel();
        panel_SelectHostType = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        hostTypeList = new javax.swing.JList();
        panel_AddHostType = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        tf_Name = new javax.swing.JTextField();
        label_Name = new javax.swing.JLabel();
        label_NumPes = new javax.swing.JLabel();
        tf_NumPes = new javax.swing.JTextField();
        label_Mips = new javax.swing.JLabel();
        tf_Mips = new javax.swing.JTextField();
        label_Ram = new javax.swing.JLabel();
        tf_Ram = new javax.swing.JTextField();
        label_Storage = new javax.swing.JLabel();
        tf_Storage = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        radio_PowerModel = new javax.swing.JRadioButton();
        radio_PowerLoad = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        panel_PowerLoad = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tf_10Power = new javax.swing.JTextField();
        tf_20Power = new javax.swing.JTextField();
        tf_30Power = new javax.swing.JTextField();
        tf_40Power = new javax.swing.JTextField();
        tf_50Power = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tf_IdlePower = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tf_60Power = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tf_70Power = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        tf_80Power = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tf_90Power = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tf_100Power = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        panel_PowerModel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        cb_PowerModel = new javax.swing.JComboBox();
        jLabel16 = new javax.swing.JLabel();
        tf_MaxPower = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        tf_StaticPower = new javax.swing.JTextField();
        bt_AddHostType = new javax.swing.JButton();
        bt_Ok1 = new javax.swing.JButton();
        bt_Cancel1 = new javax.swing.JButton();
        bt_DelHostType = new javax.swing.JButton();
        panel_HostGui = new javax.swing.JPanel();
        panel_HostTypeInfo = new javax.swing.JPanel();
        label_NumPesInfo = new javax.swing.JLabel();
        label_MipsInfo = new javax.swing.JLabel();
        label_NameInfo = new javax.swing.JLabel();
        label_RamInfo = new javax.swing.JLabel();
        label_StorageInfo = new javax.swing.JLabel();
        tf_NameInfo = new javax.swing.JTextField();
        tf_NumPesInfo = new javax.swing.JTextField();
        tf_MipsInfo = new javax.swing.JTextField();
        tf_RamInfo = new javax.swing.JTextField();
        tf_StorageInfo = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        panel_PowerModelInfo = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        label_PowerModelInfo = new javax.swing.JLabel();
        label_MaxPowerInfo = new javax.swing.JLabel();
        label_StaticPowerInfo = new javax.swing.JLabel();
        panel_PowerLoadInfo = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        label_IdleInfo = new javax.swing.JLabel();
        label_10Info = new javax.swing.JLabel();
        label_20Info = new javax.swing.JLabel();
        label_30Info = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        label_40Info = new javax.swing.JLabel();
        label_50Info = new javax.swing.JLabel();
        label_60Info = new javax.swing.JLabel();
        label_70Info = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        label_80Info = new javax.swing.JLabel();
        label_90Info = new javax.swing.JLabel();
        label_100Info = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        label_Bw = new javax.swing.JLabel();
        tf_Bw = new javax.swing.JTextField();
        label_VmPolicy = new javax.swing.JLabel();
        cb_VmPolicy = new javax.swing.JComboBox();
        bt_Ok = new javax.swing.JButton();
        bt_Cancel = new javax.swing.JButton();
        tf_NumHost = new javax.swing.JTextField();
        label_NumHost = new javax.swing.JLabel();
        bt_EditHostType = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 153, 255));

        panel_SelectHostType.setBackground(new java.awt.Color(153, 204, 255));
        panel_SelectHostType.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chọn Host Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        hostTypeList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        hostTypeList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                hostTypeListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(hostTypeList);

        javax.swing.GroupLayout panel_SelectHostTypeLayout = new javax.swing.GroupLayout(panel_SelectHostType);
        panel_SelectHostType.setLayout(panel_SelectHostTypeLayout);
        panel_SelectHostTypeLayout.setHorizontalGroup(
            panel_SelectHostTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
        );
        panel_SelectHostTypeLayout.setVerticalGroup(
            panel_SelectHostTypeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        panel_AddHostType.setBackground(new java.awt.Color(153, 204, 255));
        panel_AddHostType.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm mẫu Host mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        panel_AddHostType.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tf_Name.setToolTipText("Tên của mẫu Host (tên không chứa ký tự đặc biệt)");
        tf_Name.addKeyListener(new KeyEvent(tf_Name, "CHAR", 35));
        tf_Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_NameFocusLost(evt);
            }
        });

        label_Name.setText("Name:");
        label_Name.setToolTipText("Tên của mẫu Host (tên không chứa ký tự đặc biệt)");

        label_NumPes.setText("Số Pe:");
        label_NumPes.setToolTipText("Số lượng phần tử xử lý của Host");

        tf_NumPes.setText("1");
        tf_NumPes.setToolTipText("Số lượng phần tử xử lý của Host");
        tf_NumPes.addKeyListener(new KeyEvent(tf_NumPes, "NUMERIC", 2));
        tf_NumPes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_NumPesFocusLost(evt);
            }
        });

        label_Mips.setText("Mips mỗi Pe:");
        label_Mips.setToolTipText("<html>Khả năng xử lý của mỗi thành phần xử lý<br>Đơn vị: Mips<br>Giá trị: >= 0.000001</html>");

        tf_Mips.setText("2000");
        tf_Mips.setToolTipText("<html>Khả năng xử lý của mỗi thành phần xử lý<br>Đơn vị: Mips<br>Giá trị: >= 0.000001</html>");
        tf_Mips.addKeyListener(new KeyEvent(tf_Mips, "NUMERIC_DOT", 12));
        tf_Mips.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_MipsFocusLost(evt);
            }
        });

        label_Ram.setText("Ram(MB):");
        label_Ram.setToolTipText("<html>Dung lượng Ram của Host<br>Đơn vị: MB<br>Giá trị: >0</html>");

        tf_Ram.setText("4096");
        tf_Ram.setToolTipText("<html>Dung lượng Ram của Host<br>Đơn vị: MB<br>Giá trị: >0</html>");
        tf_Ram.addKeyListener(new KeyEvent(tf_Ram, "NUMERIC", 6));
        tf_Ram.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_RamFocusLost(evt);
            }
        });

        label_Storage.setText("Storage(MB):");
        label_Storage.setToolTipText("<html>Khả năng lưu trữ của Host<br>Đơn vị: MB<br>Giá trị: >0</html>");

        tf_Storage.setText("100000");
        tf_Storage.setToolTipText("<html>Khả năng lưu trữ của Host<br>Đơn vị: MB<br>Giá trị: >0</html>");
        tf_Storage.addKeyListener(new KeyEvent(tf_Storage, "NUMERIC", 12));
        tf_Storage.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_StorageFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label_Storage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_Storage, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_Name)
                            .addComponent(label_NumPes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_Name)
                            .addComponent(tf_NumPes, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_Mips)
                            .addComponent(label_Ram))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_Ram)
                            .addComponent(tf_Mips))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Name))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_NumPes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_NumPes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Mips)
                    .addComponent(tf_Mips, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Ram, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Ram))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_Storage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label_Storage))
                .addContainerGap(76, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cấu hình", jPanel1);

        jPanel2.setLayout(null);

        btg_PowerOption.add(radio_PowerModel);
        radio_PowerModel.setSelected(true);
        radio_PowerModel.setText("Mô hình");
        radio_PowerModel.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_PowerModelItemStateChanged(evt);
            }
        });
        jPanel2.add(radio_PowerModel);
        radio_PowerModel.setBounds(44, 0, 63, 23);

        btg_PowerOption.add(radio_PowerLoad);
        radio_PowerLoad.setText("Chi tiết");
        radio_PowerLoad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                radio_PowerLoadItemStateChanged(evt);
            }
        });
        jPanel2.add(radio_PowerLoad);
        radio_PowerLoad.setBounds(140, 0, 59, 23);

        jLabel1.setText("Theo:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 4, 28, 14);

        jLabel2.setText("10%:");

        jLabel3.setText("20%:");

        jLabel4.setText("30%:");

        jLabel5.setText("40%:");

        jLabel6.setText("50%:");

        tf_10Power.setText("94");
        tf_10Power.addKeyListener(new KeyEvent(tf_10Power, "NUMERIC_DOT", 10));

        tf_20Power.setText("98");
        tf_20Power.addKeyListener(new KeyEvent(tf_20Power, "NUMERIC_DOT", 10));

        tf_30Power.setText("102");
        tf_30Power.addKeyListener(new KeyEvent(tf_30Power, "NUMERIC_DOT", 10));

        tf_40Power.setText("106");
        tf_40Power.addKeyListener(new KeyEvent(tf_40Power, "NUMERIC_DOT", 10));

        tf_50Power.setText("110");
        tf_50Power.addKeyListener(new KeyEvent(tf_50Power, "NUMERIC_DOT", 10));

        jLabel7.setText("Năng lượng tiêu thụ trung bình (W)");

        jLabel8.setText("Idle:");

        tf_IdlePower.setText("90");
        tf_IdlePower.addKeyListener(new KeyEvent(tf_IdlePower, "NUMERIC_DOT", 10));

        jLabel9.setText("60%:");

        tf_60Power.setText("114");
        tf_60Power.addKeyListener(new KeyEvent(tf_60Power, "NUMERIC_DOT", 10));

        jLabel10.setText("70%:");

        tf_70Power.setText("117");
        tf_70Power.addKeyListener(new KeyEvent(tf_70Power, "NUMERIC_DOT", 10));

        jLabel11.setText("80%:");

        tf_80Power.setText("120");
        tf_80Power.addKeyListener(new KeyEvent(tf_80Power, "NUMERIC_DOT", 10));

        jLabel12.setText("90%:");

        tf_90Power.setText("123");
        tf_90Power.addKeyListener(new KeyEvent(tf_90Power, "NUMERIC_DOT", 10));

        jLabel13.setText("100%:");

        tf_100Power.setText("126");
        tf_100Power.addKeyListener(new KeyEvent(tf_100Power, "NUMERIC_DOT", 10));

        jLabel14.setText("theo utilization (%):");

        javax.swing.GroupLayout panel_PowerLoadLayout = new javax.swing.GroupLayout(panel_PowerLoad);
        panel_PowerLoad.setLayout(panel_PowerLoadLayout);
        panel_PowerLoadLayout.setHorizontalGroup(
            panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_50Power))
                            .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_40Power, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_10Power, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_20Power, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_30Power, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tf_90Power))
                                .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tf_80Power))
                                .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tf_70Power))
                                .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tf_60Power, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_100Power, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_PowerLoadLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tf_IdlePower, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panel_PowerLoadLayout.setVerticalGroup(
            panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_PowerLoadLayout.createSequentialGroup()
                .addComponent(jLabel7)
                .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tf_IdlePower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)))
                    .addGroup(panel_PowerLoadLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(17, 17, 17)))
                .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_PowerLoadLayout.createSequentialGroup()
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tf_10Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tf_20Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(tf_30Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(tf_40Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tf_50Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_PowerLoadLayout.createSequentialGroup()
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tf_60Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(tf_70Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tf_80Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tf_90Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerLoadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(tf_100Power, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        jPanel2.add(panel_PowerLoad);
        panel_PowerLoad.setBounds(10, 25, 0, 0);

        panel_PowerModel.setPreferredSize(new java.awt.Dimension(243, 186));

        jLabel15.setText("Power Model:");

        cb_PowerModel.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Cubic", "Linear", "Sqrt", "Square" }));

        jLabel16.setText("Max Power(W):");

        tf_MaxPower.setText("135");

        jLabel17.setText("Static Power (%):");

        tf_StaticPower.setText("0.7");

        javax.swing.GroupLayout panel_PowerModelLayout = new javax.swing.GroupLayout(panel_PowerModel);
        panel_PowerModel.setLayout(panel_PowerModelLayout);
        panel_PowerModelLayout.setHorizontalGroup(
            panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PowerModelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PowerModelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cb_PowerModel, 0, 148, Short.MAX_VALUE))
                    .addGroup(panel_PowerModelLayout.createSequentialGroup()
                        .addGroup(panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf_MaxPower, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(tf_StaticPower))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panel_PowerModelLayout.setVerticalGroup(
            panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PowerModelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cb_PowerModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tf_MaxPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_PowerModelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel17)
                    .addComponent(tf_StaticPower, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jPanel2.add(panel_PowerModel);
        panel_PowerModel.setBounds(10, 24, 243, 187);

        jTabbedPane1.addTab("Điện năng", jPanel2);

        panel_AddHostType.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 17, -1, -1));

        bt_AddHostType.setBackground(new java.awt.Color(153, 204, 255));
        bt_AddHostType.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bt_AddHostType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cloudaddsmall.png"))); // NOI18N
        bt_AddHostType.setText("Thêm mẫu Host");
        bt_AddHostType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_AddHostTypeActionPerformed(evt);
            }
        });
        panel_AddHostType.add(bt_AddHostType, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, -1, -1));

        bt_Ok1.setBackground(new java.awt.Color(153, 204, 240));
        bt_Ok1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/oksmall.png"))); // NOI18N
        bt_Ok1.setText("Sửa ");
        bt_Ok1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_Ok1ActionPerformed(evt);
            }
        });
        panel_AddHostType.add(bt_Ok1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 103, -1));

        bt_Cancel1.setBackground(new java.awt.Color(153, 204, 240));
        bt_Cancel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cancelsmall.png"))); // NOI18N
        bt_Cancel1.setText("Hủy");
        bt_Cancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_Cancel1ActionPerformed(evt);
            }
        });
        panel_AddHostType.add(bt_Cancel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 260, 105, -1));

        bt_DelHostType.setBackground(new java.awt.Color(51, 153, 255));
        bt_DelHostType.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bt_DelHostType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/clouddelsmall.png"))); // NOI18N
        bt_DelHostType.setText("Xóa mẫu Host");
        bt_DelHostType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_DelHostTypeActionPerformed(evt);
            }
        });

        panel_HostGui.setBackground(new java.awt.Color(153, 204, 240));
        panel_HostGui.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm Host", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N

        panel_HostTypeInfo.setBackground(new java.awt.Color(255, 255, 255));
        panel_HostTypeInfo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Host Type Specification", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        panel_HostTypeInfo.setPreferredSize(new java.awt.Dimension(820, 200));
        panel_HostTypeInfo.setLayout(null);

        label_NumPesInfo.setText("Number of Pes:");
        label_NumPesInfo.setToolTipText("Số lượng phần tử xử lý của Host");
        panel_HostTypeInfo.add(label_NumPesInfo);
        label_NumPesInfo.setBounds(16, 50, 74, 14);

        label_MipsInfo.setText("Mips of Pes:");
        label_MipsInfo.setToolTipText("<html>Khả năng xử lý của mỗi thành phần xử lý<br>Đơn vị: Mips</html>");
        panel_HostTypeInfo.add(label_MipsInfo);
        label_MipsInfo.setBounds(16, 76, 58, 14);

        label_NameInfo.setText("Type Name:");
        label_NameInfo.setToolTipText("Tên của mẫu Host (tên không chứa ký tự đặc biệt)");
        panel_HostTypeInfo.add(label_NameInfo);
        label_NameInfo.setBounds(16, 24, 58, 14);

        label_RamInfo.setText("Ram(MB):");
        label_RamInfo.setToolTipText("<html>Dung lượng Ram của Host<br>Đơn vị: MB</html>");
        panel_HostTypeInfo.add(label_RamInfo);
        label_RamInfo.setBounds(16, 102, 47, 14);

        label_StorageInfo.setText("Storage(MB):");
        label_StorageInfo.setToolTipText("<html>Khả năng lưu trữ của Host<br>Đơn vị: MB</html>");
        panel_HostTypeInfo.add(label_StorageInfo);
        label_StorageInfo.setBounds(16, 128, 64, 14);

        tf_NameInfo.setEditable(false);
        tf_NameInfo.setToolTipText("Tên của mẫu Host (tên không chứa ký tự đặc biệt)");
        panel_HostTypeInfo.add(tf_NameInfo);
        tf_NameInfo.setBounds(100, 21, 161, 20);

        tf_NumPesInfo.setEditable(false);
        tf_NumPesInfo.setText("0");
        tf_NumPesInfo.setToolTipText("Số lượng phần tử xử lý của Host");
        panel_HostTypeInfo.add(tf_NumPesInfo);
        tf_NumPesInfo.setBounds(100, 47, 161, 20);

        tf_MipsInfo.setEditable(false);
        tf_MipsInfo.setText("0");
        tf_MipsInfo.setToolTipText("<html>Khả năng xử lý của mỗi thành phần xử lý<br>Đơn vị: Mips</html>");
        panel_HostTypeInfo.add(tf_MipsInfo);
        tf_MipsInfo.setBounds(100, 73, 161, 20);

        tf_RamInfo.setEditable(false);
        tf_RamInfo.setText("0");
        tf_RamInfo.setToolTipText("<html>Dung lượng Ram của Host<br>Đơn vị: MB</html>");
        panel_HostTypeInfo.add(tf_RamInfo);
        tf_RamInfo.setBounds(100, 99, 161, 20);

        tf_StorageInfo.setEditable(false);
        tf_StorageInfo.setText("0");
        tf_StorageInfo.setToolTipText("<html>Khả năng lưu trữ của Host<br>Đơn vị: MB</html>");
        panel_HostTypeInfo.add(tf_StorageInfo);
        tf_StorageInfo.setBounds(100, 125, 161, 20);

        jLabel18.setText("Power:");
        panel_HostTypeInfo.add(jLabel18);
        jLabel18.setBounds(16, 151, 34, 14);

        jLabel41.setText("Power Model:");

        jLabel42.setText("Max Power(W):");

        jLabel43.setText("Static Power (%):");

        label_PowerModelInfo.setText("jLabel44");

        label_MaxPowerInfo.setText("jLabel45");

        label_StaticPowerInfo.setText("jLabel46");

        javax.swing.GroupLayout panel_PowerModelInfoLayout = new javax.swing.GroupLayout(panel_PowerModelInfo);
        panel_PowerModelInfo.setLayout(panel_PowerModelInfoLayout);
        panel_PowerModelInfoLayout.setHorizontalGroup(
            panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PowerModelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_PowerModelInfoLayout.createSequentialGroup()
                        .addComponent(jLabel43)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_StaticPowerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                    .addGroup(panel_PowerModelInfoLayout.createSequentialGroup()
                        .addGroup(panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label_PowerModelInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(label_MaxPowerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        panel_PowerModelInfoLayout.setVerticalGroup(
            panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_PowerModelInfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(label_PowerModelInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(label_MaxPowerInfo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_PowerModelInfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(label_StaticPowerInfo))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        panel_HostTypeInfo.add(panel_PowerModelInfo);
        panel_PowerModelInfo.setBounds(16, 171, 245, 0);

        panel_PowerLoadInfo.setPreferredSize(new java.awt.Dimension(245, 100));
        panel_PowerLoadInfo.setLayout(null);

        jLabel19.setText("Idle:");
        panel_PowerLoadInfo.add(jLabel19);
        jLabel19.setBounds(10, 20, 22, 14);

        jLabel20.setText("10%:");
        panel_PowerLoadInfo.add(jLabel20);
        jLabel20.setBounds(10, 40, 27, 14);

        jLabel21.setText("20%:");
        panel_PowerLoadInfo.add(jLabel21);
        jLabel21.setBounds(10, 60, 27, 14);

        jLabel22.setText("30%:");
        panel_PowerLoadInfo.add(jLabel22);
        jLabel22.setBounds(10, 80, 27, 14);

        label_IdleInfo.setText("jLabel23");
        panel_PowerLoadInfo.add(label_IdleInfo);
        label_IdleInfo.setBounds(40, 20, 40, 14);

        label_10Info.setText("jLabel24");
        panel_PowerLoadInfo.add(label_10Info);
        label_10Info.setBounds(40, 40, 40, 14);

        label_20Info.setText("jLabel25");
        panel_PowerLoadInfo.add(label_20Info);
        label_20Info.setBounds(40, 60, 40, 14);

        label_30Info.setText("jLabel26");
        panel_PowerLoadInfo.add(label_30Info);
        label_30Info.setBounds(40, 80, 40, 14);

        jLabel27.setText("40%:");
        panel_PowerLoadInfo.add(jLabel27);
        jLabel27.setBounds(90, 20, 27, 14);

        jLabel28.setText("50%:");
        panel_PowerLoadInfo.add(jLabel28);
        jLabel28.setBounds(90, 40, 27, 14);

        jLabel29.setText("60%:");
        panel_PowerLoadInfo.add(jLabel29);
        jLabel29.setBounds(90, 60, 27, 14);

        jLabel30.setText("70%:");
        panel_PowerLoadInfo.add(jLabel30);
        jLabel30.setBounds(90, 80, 27, 14);

        label_40Info.setText("jLabel31");
        panel_PowerLoadInfo.add(label_40Info);
        label_40Info.setBounds(120, 20, 40, 14);

        label_50Info.setText("jLabel32");
        panel_PowerLoadInfo.add(label_50Info);
        label_50Info.setBounds(120, 40, 40, 14);

        label_60Info.setText("jLabel33");
        panel_PowerLoadInfo.add(label_60Info);
        label_60Info.setBounds(120, 60, 40, 14);

        label_70Info.setText("jLabel34");
        panel_PowerLoadInfo.add(label_70Info);
        label_70Info.setBounds(120, 80, 40, 14);

        jLabel35.setText("80%:");
        panel_PowerLoadInfo.add(jLabel35);
        jLabel35.setBounds(170, 20, 27, 14);

        jLabel36.setText("90%:");
        panel_PowerLoadInfo.add(jLabel36);
        jLabel36.setBounds(170, 40, 27, 14);

        jLabel37.setText("100%:");
        panel_PowerLoadInfo.add(jLabel37);
        jLabel37.setBounds(170, 60, 33, 14);

        label_80Info.setText("jLabel38");
        panel_PowerLoadInfo.add(label_80Info);
        label_80Info.setBounds(210, 20, 40, 14);

        label_90Info.setText("jLabel39");
        panel_PowerLoadInfo.add(label_90Info);
        label_90Info.setBounds(210, 40, 40, 14);

        label_100Info.setText("jLabel40");
        panel_PowerLoadInfo.add(label_100Info);
        label_100Info.setBounds(210, 60, 40, 14);

        jLabel23.setText("Điện năng tiêu thụ theo cpu utilization (W):");
        panel_PowerLoadInfo.add(jLabel23);
        jLabel23.setBounds(10, 0, 220, 14);

        panel_HostTypeInfo.add(panel_PowerLoadInfo);
        panel_PowerLoadInfo.setBounds(16, 171, 245, 100);

        label_Bw.setText("Bandwidth(Kbps):");
        label_Bw.setToolTipText("<html>Băng thông mạng của Host<br>Đơn vị: Mbps<br>Giá trị: >0</html>");

        tf_Bw.setText("1000");
        tf_Bw.setToolTipText("<html>Băng thông mạng của Host<br>Đơn vị: Mbps<br>Giá trị: >0</html>");
        tf_Bw.addKeyListener(new KeyEvent(tf_Bw, "NUMERIC", 12));
        tf_Bw.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_BwFocusLost(evt);
            }
        });

        label_VmPolicy.setText("VM Schedular Policy:");
        label_VmPolicy.setToolTipText("Chính sách lập lịch máy ảo của Host");

        cb_VmPolicy.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Time-Shared" }));
        cb_VmPolicy.setToolTipText("Chính sách lập lịch máy ảo của Host");
        cb_VmPolicy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_VmPolicyActionPerformed(evt);
            }
        });

        bt_Ok.setBackground(new java.awt.Color(153, 204, 240));
        bt_Ok.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/oksmall.png"))); // NOI18N
        bt_Ok.setText("Thêm");
        bt_Ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_OkActionPerformed(evt);
            }
        });

        bt_Cancel.setBackground(new java.awt.Color(153, 204, 240));
        bt_Cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cancelsmall.png"))); // NOI18N
        bt_Cancel.setText("Hủy");
        bt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_CancelActionPerformed(evt);
            }
        });

        tf_NumHost.setText("25");
        tf_NumHost.setToolTipText("Số lượng Host muốn tạo");
        tf_NumHost.addKeyListener(new KeyEvent(tf_NumHost, "NUMERIC", 7));
        tf_NumHost.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_NumHostFocusLost(evt);
            }
        });

        label_NumHost.setText("Số lượng:");
        label_NumHost.setToolTipText("Số lượng Host muốn tạo");

        javax.swing.GroupLayout panel_HostGuiLayout = new javax.swing.GroupLayout(panel_HostGui);
        panel_HostGui.setLayout(panel_HostGuiLayout);
        panel_HostGuiLayout.setHorizontalGroup(
            panel_HostGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_HostGuiLayout.createSequentialGroup()
                .addGroup(panel_HostGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_HostGuiLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panel_HostGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panel_HostTypeInfo, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addGroup(panel_HostGuiLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(label_Bw)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_Bw, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(label_VmPolicy)
                            .addComponent(cb_VmPolicy, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(panel_HostGuiLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(bt_Ok, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bt_Cancel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_HostGuiLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(label_NumHost)
                        .addGap(18, 18, 18)
                        .addComponent(tf_NumHost, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel_HostGuiLayout.setVerticalGroup(
            panel_HostGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_HostGuiLayout.createSequentialGroup()
                .addComponent(panel_HostTypeInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panel_HostGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_Bw)
                    .addComponent(tf_Bw, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_VmPolicy)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cb_VmPolicy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panel_HostGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label_NumHost)
                    .addComponent(tf_NumHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panel_HostGuiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_Cancel)
                    .addComponent(bt_Ok))
                .addContainerGap())
        );

        bt_EditHostType.setBackground(new java.awt.Color(51, 153, 255));
        bt_EditHostType.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        bt_EditHostType.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/cloudeditsmall.png"))); // NOI18N
        bt_EditHostType.setText("Sửa mẫu Host");
        bt_EditHostType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_EditHostTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(bt_EditHostType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(5, 5, 5)
                        .addComponent(bt_DelHostType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panel_SelectHostType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel_AddHostType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panel_HostGui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_HostGui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(panel_SelectHostType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bt_DelHostType)
                            .addComponent(bt_EditHostType))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panel_AddHostType, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_OkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_OkActionPerformed
        if(!done){
            JOptionPane.showMessageDialog(null,"Dữ liệu đầu vào chưa chính xác");
        }else{
            String numberHost, vmPolicy, hostType;
            long bw;
            
            numberHost = tf_NumHost.getText();
            if(!numberHost.equals("1")){
                numberHost = "-" + numberHost;
            }
            hostType = tf_NameInfo.getText();
            bw = Long.parseLong(tf_Bw.getText());
            vmPolicy = cb_VmPolicy.getSelectedItem().toString();

            db_Host dbHost = new db_Host(rootID, numberHost, hostType, bw, vmPolicy);
            try{
                switch(option){
                    case ADD:{
                        int lastHostID = Integer.parseInt(db_Host.getLastHostID(stm, rootID));
                        dbHost.Insert(stm);
                        if(lastHostID==0){
                            db_Host.updateHostID(stm, rootID);
                        }else
                            db_Host.updateLastHostID(stm, rootID, lastHostID, numberHost);
                    }
                        break;
                    case EDIT:{
                        dbHost.updateHost(stm, ID);
                        db_Host.updateHostID(stm, rootID);
                    }
                        break;
                    default: dispose();
            }
            }catch(Exception e){
                System.out.println(e);
                done = false;
            }
            dispose();
        }
    }//GEN-LAST:event_bt_OkActionPerformed

    private void bt_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_CancelActionPerformed
        done = false;
        dispose();
    }//GEN-LAST:event_bt_CancelActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        done = false;
    }//GEN-LAST:event_formWindowClosing

    private void hostTypeListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_hostTypeListValueChanged
        String name = (String)hostTypeList.getSelectedValue();
        updateHostTypeInfo(name);
    }//GEN-LAST:event_hostTypeListValueChanged

    private void bt_DelHostTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_DelHostTypeActionPerformed
        if(hostTypeList.isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"Chưa chọn Host Type");
        }else{
            int temp = JOptionPane.showConfirmDialog(this, "Bạn có chắn chắn muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(temp == 0){
                String name = hostTypeList.getSelectedValue().toString();
                if(name.equals("Demo")) JOptionPane.showMessageDialog(null,"Mẫu host mặc định không thể xóa");
                else{
                    db_HostType.Open();
                    db_HostType.Del(name);
                    db_HostType.Close();
                    updateTypeList(hostTypeList);
                }
            }
        }
    }//GEN-LAST:event_bt_DelHostTypeActionPerformed

    private void bt_AddHostTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_AddHostTypeActionPerformed
        if(!doneType){
            JOptionPane.showMessageDialog(null,"Dữ liệu đầu vào chưa chính xác");
        }else{
            String name = tf_Name.getText();
            int numPes = Integer.parseInt(tf_NumPes.getText()), ram = Integer.parseInt(tf_Ram.getText());
            double mips = Double.parseDouble(tf_Mips.getText());
            long storage = Long.parseLong(tf_Storage.getText());
            String powerModel;
            double maxPower, staticPower;
            if(radio_PowerModel.isSelected()){
                powerModel = cb_PowerModel.getSelectedItem().toString();
                maxPower = Double.parseDouble(tf_MaxPower.getText());
                staticPower = Double.parseDouble(tf_StaticPower.getText());
            }else{
                powerModel = tf_IdlePower.getText()+ "-" + tf_10Power.getText() + "-" + tf_20Power.getText() + "-" + tf_30Power.getText() + "-" + tf_40Power.getText() + "-" + tf_50Power.getText() + "-" + tf_60Power.getText() + "-" + tf_70Power.getText()
                                                            + "-" + tf_80Power.getText() + "-" + tf_90Power.getText() + "-" + tf_100Power.getText();
                maxPower = 0;
                staticPower = 0;
            }
            db_HostType hostType = new db_HostType(name, numPes, mips, ram, storage, powerModel, maxPower, staticPower);
            db_HostType.Open();
            hostType.Insert();
            db_HostType.Close();
            updateTypeList(hostTypeList);
            hostTypeList.setSelectedValue(name, true);
            emptyName();
        }
    }//GEN-LAST:event_bt_AddHostTypeActionPerformed

    private void radio_PowerModelItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_PowerModelItemStateChanged
        panel_PowerLoad.setVisible(false);
        panel_PowerModel.setVisible(true);
    }//GEN-LAST:event_radio_PowerModelItemStateChanged

    private void radio_PowerLoadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_radio_PowerLoadItemStateChanged
        panel_PowerModel.setVisible(false);
        panel_PowerLoad.setVisible(true);
    }//GEN-LAST:event_radio_PowerLoadItemStateChanged

    private void bt_EditHostTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_EditHostTypeActionPerformed
        if(hostTypeList.isSelectionEmpty()){
            JOptionPane.showMessageDialog(null,"Chưa chọn Host Type");
        }else{
            bt_EditHostType.setEnabled(false);
            bt_DelHostType.setEnabled(false);
            bt_AddHostType.setVisible(false);
            bt_Ok1.setVisible(true);
            bt_Cancel1.setVisible(true);
            panel_AddHostType.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sửa mẫu Host", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12)));
            tf_Name.setText(hostTypeList.getSelectedValue().toString());
            db_HostType.Open();
            hostTypeID = db_HostType.getID(tf_Name.getText());
            String[] data = db_HostType.getTypeInfo(tf_Name.getText());
            db_HostType.Close();
            
            tf_NumPes.setText(data[0]);
            tf_Mips.setText(data[1]);
            tf_Ram.setText(data[2]);
            tf_Storage.setText(data[3]);
            if(!data[4].contains("-")){
                radio_PowerModel.setSelected(true);
                panel_PowerLoad.setVisible(false);
                panel_PowerModel.setVisible(true);
                cb_PowerModel.setSelectedItem(data[4]);
                tf_MaxPower.setText(data[5]);
                tf_StaticPower.setText(data[6]);
            }else{
                radio_PowerLoad.setSelected(true);
                panel_PowerModel.setVisible(false);
                panel_PowerLoad.setVisible(true);
                String[] power = data[4].split("-");
                tf_IdlePower.setText(power[0]);
                tf_10Power.setText(power[1]);
                tf_20Power.setText(power[2]);
                tf_30Power.setText(power[3]);
                tf_40Power.setText(power[4]);
                tf_50Power.setText(power[5]);
                tf_60Power.setText(power[6]);
                tf_70Power.setText(power[7]);
                tf_80Power.setText(power[8]);
                tf_90Power.setText(power[9]);
                tf_100Power.setText(power[10]);
            }
        }
    }//GEN-LAST:event_bt_EditHostTypeActionPerformed

    private void bt_Ok1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_Ok1ActionPerformed
        if(!doneType){
            JOptionPane.showMessageDialog(null,"Dữ liệu đầu vào chưa chính xác");
        }else{
            String name = tf_Name.getText();
            int numPes = Integer.parseInt(tf_NumPes.getText()), ram = Integer.parseInt(tf_Ram.getText());
            double mips = Double.parseDouble(tf_Mips.getText());
            long storage = Long.parseLong(tf_Storage.getText());
            String powerModel;
            double maxPower, staticPower;
            if(radio_PowerModel.isSelected()){
                powerModel = cb_PowerModel.getSelectedItem().toString();
                maxPower = Double.parseDouble(tf_MaxPower.getText());
                staticPower = Double.parseDouble(tf_StaticPower.getText());
            }else{
                powerModel = tf_IdlePower.getText()+ "-" + tf_10Power.getText() + "-" + tf_20Power.getText() + "-" + tf_30Power.getText() + "-" + tf_40Power.getText() + "-" + tf_50Power.getText() + "-" + tf_60Power.getText() + "-" + tf_70Power.getText()
                                                            + "-" + tf_80Power.getText() + "-" + tf_90Power.getText() + "-" + tf_100Power.getText();
                maxPower = 0;
                staticPower = 0;
            }
            db_HostType hostType = new db_HostType(name, numPes, mips, ram, storage, powerModel, maxPower, staticPower);
            db_HostType.Open();
            hostType.Update(hostTypeID);
            db_HostType.Close();
            updateHostTypeInfo(name);
            
            isEditType = true;
            
            hostTypeID = -1;
            bt_EditHostType.setEnabled(true);
            bt_DelHostType.setEnabled(true);
            bt_AddHostType.setVisible(true);
            bt_Ok1.setVisible(false);
            bt_Cancel1.setVisible(false);
            emptyName();
            panel_AddHostType.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm mẫu Host mới", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12)));
        }
    }//GEN-LAST:event_bt_Ok1ActionPerformed

    private void bt_Cancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_Cancel1ActionPerformed
        bt_EditHostType.setEnabled(true);
        bt_DelHostType.setEnabled(true);
        bt_AddHostType.setVisible(true);
        bt_Ok1.setVisible(false);
        bt_Cancel1.setVisible(false);
        panel_AddHostType.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm mẫu Host mới", javax.swing.border.TitledBorder.DEFAULT_POSITION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12)));
    }//GEN-LAST:event_bt_Cancel1ActionPerformed

    private void tf_NumHostFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_NumHostFocusLost
        switch(Check.checkZero(tf_NumHost.getText())){
            case 0: error.clear(tf_NumHost);  done = true;
                    break;
            case 1: error.setError(tf_NumHost, "Chưa nhập số lượng Host"); done = false;
                    break;
            default: error.setError(tf_NumHost, "Số lượng Host phải lớn hơn 0"); done = false;
        }
    }//GEN-LAST:event_tf_NumHostFocusLost

    private void tf_BwFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_BwFocusLost
        switch(Check.checkZero(tf_Bw.getText())){
            case 0: error.clear(tf_Bw);  done = true;
                    break;
            case 1: error.setError(tf_Bw, "Chưa nhập băng thông của Host"); done = false;
                    break;
            default: error.setError(tf_Bw, "Băng thông Host phải lớn hơn 0"); done = false;
        }
    }//GEN-LAST:event_tf_BwFocusLost

    private void tf_NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_NameFocusLost
        switch(Check.checkHostTypeName(tf_Name.getText(), hostTypeID)){
            case 0: error.clear(tf_Name);  doneType = true;
                    break;
            case 1: error.setError(tf_Name, "Chưa nhập tên mẫu Host"); doneType = false;
                    break;
            default: error.setError(tf_Name, "Tên mẫu Host bị trùng hoặc chưa hợp lệ"); doneType = false;
        }
    }//GEN-LAST:event_tf_NameFocusLost

    private void tf_NumPesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_NumPesFocusLost
        switch(Check.checkZero(tf_NumPes.getText())){
            case 0: error.clear(tf_NumPes);  doneType = true;
                    break;
            case 1: error.setError(tf_NumPes, "Chưa nhập số phần tử xử lý của Host"); doneType = false;
                    break;
            default: error.setError(tf_NumPes, "Số lượng phần tử xử lý phải lớn hơn 0"); doneType = false;
        }
    }//GEN-LAST:event_tf_NumPesFocusLost

    private void tf_MipsFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_MipsFocusLost
        switch(Check.checkMipsValue(tf_Mips.getText())){
            case 0: error.clear(tf_Mips);  doneType = true;
                    break;
            case 1: error.setError(tf_Mips, "Chưa nhập khả năng xử của mỗi thành phần xử lý"); doneType = false;
                    break;
            default: error.setError(tf_Mips, "Khả năng xử lý mỗi Pe phải lớn hơn 0.000001"); doneType = false;
        }
    }//GEN-LAST:event_tf_MipsFocusLost

    private void tf_RamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_RamFocusLost
        switch(Check.checkZero(tf_Ram.getText())){
            case 0: error.clear(tf_Ram);  doneType = true;
                    break;
            case 1: error.setError(tf_Ram, "Chưa nhập dung lượng Ram của Host"); doneType = false;
                    break;
            default: error.setError(tf_Ram, "Dung lượng Ram phải lớn hơn 0"); doneType = false;
        }
    }//GEN-LAST:event_tf_RamFocusLost

    private void tf_StorageFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_StorageFocusLost
        switch(Check.checkZero(tf_Storage.getText())){
            case 0: error.clear(tf_Storage);  doneType = true;
                    break;
            case 1: error.setError(tf_Storage, "Chưa nhập khả năng lưu trữ của Host"); doneType = false;
                    break;
            default: error.setError(tf_Storage, "Khả năng lưu trữ phải lớn hơn 0"); doneType = false;
        }
    }//GEN-LAST:event_tf_StorageFocusLost

    private void cb_VmPolicyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_VmPolicyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_VmPolicyActionPerformed

    protected static void updateTypeList(JList list){
        db_HostType.Open();
        ArrayList name = db_HostType.getAllHostType();
        db_HostType.Close();
        DefaultListModel listModel = new DefaultListModel();
        listModel.clear();
        if(name != null){
            for(int i=0; i<name.size(); i++){
                listModel.addElement(name.get(i));
            }
        }
        list.setModel(listModel);
    }
    
    protected void updateHostTypeInfo(String name){
        db_HostType.Open();
        String[] data = db_HostType.getTypeInfo(name);
        db_HostType.Close();
        tf_NameInfo.setText(name);
        tf_NumPesInfo.setText(data[0]);
        tf_MipsInfo.setText(data[1]);
        tf_RamInfo.setText(data[2]);
        tf_StorageInfo.setText(data[3]);
        if(!data[4].contains("-")){
            label_PowerModelInfo.setText(data[4]);
            label_MaxPowerInfo.setText(data[5]);
            label_StaticPowerInfo.setText(data[6]);
            panel_PowerModelInfo.setVisible(true);
            panel_PowerLoadInfo.setVisible(false);
        }else{
            String[] power = data[4].split("-");
            label_IdleInfo.setText(power[0]);
            label_10Info.setText(power[1]);
            label_20Info.setText(power[2]);
            label_30Info.setText(power[3]);
            label_40Info.setText(power[4]);
            label_50Info.setText(power[5]);
            label_60Info.setText(power[6]);
            label_70Info.setText(power[7]);
            label_80Info.setText(power[8]);
            label_90Info.setText(power[9]);
            label_100Info.setText(power[10]);
            panel_PowerModelInfo.setVisible(false);
            panel_PowerLoadInfo.setVisible(true);
        }
    }
    
    protected void emptyName(){
        tf_Name.setText("");
        error.setError(tf_Name, "Chưa nhập tên mẫu Host"); 
        doneType = false;
    }
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
//            java.util.logging.Logger.getLogger(HostGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(HostGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(HostGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(HostGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                HostGui dialog = new HostGui(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bt_AddHostType;
    private javax.swing.JButton bt_Cancel;
    private javax.swing.JButton bt_Cancel1;
    private javax.swing.JButton bt_DelHostType;
    private javax.swing.JButton bt_EditHostType;
    private javax.swing.JButton bt_Ok;
    private javax.swing.JButton bt_Ok1;
    private javax.swing.ButtonGroup btg_PowerOption;
    private javax.swing.JComboBox cb_PowerModel;
    private javax.swing.JComboBox cb_VmPolicy;
    private javax.swing.JList hostTypeList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel label_100Info;
    private javax.swing.JLabel label_10Info;
    private javax.swing.JLabel label_20Info;
    private javax.swing.JLabel label_30Info;
    private javax.swing.JLabel label_40Info;
    private javax.swing.JLabel label_50Info;
    private javax.swing.JLabel label_60Info;
    private javax.swing.JLabel label_70Info;
    private javax.swing.JLabel label_80Info;
    private javax.swing.JLabel label_90Info;
    private javax.swing.JLabel label_Bw;
    private javax.swing.JLabel label_IdleInfo;
    private javax.swing.JLabel label_MaxPowerInfo;
    private javax.swing.JLabel label_Mips;
    private javax.swing.JLabel label_MipsInfo;
    private javax.swing.JLabel label_Name;
    private javax.swing.JLabel label_NameInfo;
    private javax.swing.JLabel label_NumHost;
    private javax.swing.JLabel label_NumPes;
    private javax.swing.JLabel label_NumPesInfo;
    private javax.swing.JLabel label_PowerModelInfo;
    private javax.swing.JLabel label_Ram;
    private javax.swing.JLabel label_RamInfo;
    private javax.swing.JLabel label_StaticPowerInfo;
    private javax.swing.JLabel label_Storage;
    private javax.swing.JLabel label_StorageInfo;
    private javax.swing.JLabel label_VmPolicy;
    private javax.swing.JPanel panel_AddHostType;
    private javax.swing.JPanel panel_HostGui;
    private javax.swing.JPanel panel_HostTypeInfo;
    private javax.swing.JPanel panel_PowerLoad;
    private javax.swing.JPanel panel_PowerLoadInfo;
    private javax.swing.JPanel panel_PowerModel;
    private javax.swing.JPanel panel_PowerModelInfo;
    private javax.swing.JPanel panel_SelectHostType;
    private javax.swing.JRadioButton radio_PowerLoad;
    private javax.swing.JRadioButton radio_PowerModel;
    private javax.swing.JTextField tf_100Power;
    private javax.swing.JTextField tf_10Power;
    private javax.swing.JTextField tf_20Power;
    private javax.swing.JTextField tf_30Power;
    private javax.swing.JTextField tf_40Power;
    private javax.swing.JTextField tf_50Power;
    private javax.swing.JTextField tf_60Power;
    private javax.swing.JTextField tf_70Power;
    private javax.swing.JTextField tf_80Power;
    private javax.swing.JTextField tf_90Power;
    private javax.swing.JTextField tf_Bw;
    private javax.swing.JTextField tf_IdlePower;
    private javax.swing.JTextField tf_MaxPower;
    private javax.swing.JTextField tf_Mips;
    private javax.swing.JTextField tf_MipsInfo;
    private javax.swing.JTextField tf_Name;
    private javax.swing.JTextField tf_NameInfo;
    private javax.swing.JTextField tf_NumHost;
    private javax.swing.JTextField tf_NumPes;
    private javax.swing.JTextField tf_NumPesInfo;
    private javax.swing.JTextField tf_Ram;
    private javax.swing.JTextField tf_RamInfo;
    private javax.swing.JTextField tf_StaticPower;
    private javax.swing.JTextField tf_Storage;
    private javax.swing.JTextField tf_StorageInfo;
    // End of variables declaration//GEN-END:variables
}

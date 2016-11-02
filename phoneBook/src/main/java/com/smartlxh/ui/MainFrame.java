package com.smartlxh.ui;

import com.smartlxh.dao.dataPersistence;
import com.smartlxh.domain.Record;
import java.io.*;
import java.util.ArrayList;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.*;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tim
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JButton DeleButton;
    private javax.swing.JButton ModifyjButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JList jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;//查询JText
    // End of variables declaration//GEN-END:variables
    public static ArrayList<Record> personList = new ArrayList<Record>();
    public static ArrayList main = new ArrayList();
    public static DefaultListModel m = new DefaultListModel();
    public static DefaultTableModel n;
    private String m_str;
    String[] groupname;
    public static String strname;


    public static Record[] records;


    public MainFrame() {

        String[] string = {"序号", "姓名", "电话", "邮箱"};
        this.setLocation(400, 200);
        n = new DefaultTableModel(null, string);
        initRecords();
        initComponents();
        this.setTitle("电话簿系统");

        jButton2ActionPerformed();
    }


    private void initRecords() {
        dataPersistence p = new dataPersistence();
        records = p.getFromDB();
    }

    public static void jButton2ActionPerformed() {
        removeList();
        int length = records.length;
        for (int i = 0; i < length; i++) {
            if (records[i] != null) {
                n.addRow(new String[]{Integer.toString(records[i].getId()), records[i].getUserName(), records[i].getPhoneNum(), records[i].getEmail()});
            }
        }

    }


    private static void removeList() {   //清空显示列表

        int row = n.getRowCount();
        for (int i = 0; i < row; i++) {
            n.removeRow(0);

        }
    }


    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {  //查询事件

        String str = jTextField1.getText();

        if (!str.equals("")) {//判断查询框是否为空
            removeList();
            int size = MainFrame.records.length;
            for (int i = 0; i < size; i++) {
                if (records[i] != null) {
                    // System.out.print();
                    if (records[i].getUserName().contains(str) || records[i].getPhoneNum().contains(str) || records[i].getEmail().contains(str)) {

                        n.addRow(new String[]{Integer.toString(records[i].getId()), records[i].getUserName(), records[i].getPhoneNum(), records[i].getEmail()});
                    }

                }
            }
        }


    }


    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList(m);
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        AddButton = new javax.swing.JButton();
        DeleButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        ModifyjButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();


        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {        //查询按钮添加事件
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });


        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSeparator1.setBounds(0, 100, 970, 2);
        jLayeredPane1.add(jSeparator1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jScrollPane1.setBounds(0, 140, 180, 360);
        jLayeredPane1.add(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTable1.setModel(n);
        jScrollPane2.setViewportView(jTable1);

        jScrollPane2.setBounds(190, 100, 780, 410);
        jLayeredPane1.add(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        AddButton.setText("添加联系人");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // AddButtonActionPerformed(evt);
                AddRecordFrame add = new AddRecordFrame(null);
                add.setVisible(true);
            }
        });
        AddButton.setBounds(30, 20, 93, 60);
        jLayeredPane1.add(AddButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        DeleButton.setText("删除联系人");
        DeleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // DeleButtonActionPerformed(evt);


                int j = jTable1.getSelectedRow();

                if (j != -1) {

                    int id = Integer.parseInt(n.getValueAt(j, 0).toString());
                    records[id] = null;
                    jButton2ActionPerformed();
                    dataPersistence p = new dataPersistence();
                    p.deleteFromDB(id);

                }


            }
        });
        DeleButton.setBounds(610, 20, 93, 60);
        jLayeredPane1.add(DeleButton, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 18)); // NOI18N
        jLabel1.setText("查询：");
        jLabel1.setBounds(0, 110, 60, 24);
        jLayeredPane1.add(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                //jTextField1KeyReleased(evt);
            }
        });
        jTextField1.setBounds(60, 110, 120, 30);
        jLayeredPane1.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jButton1.setText("退出");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //   jButton1ActionPerformed(evt);
                System.exit(0);
            }
        });
        jButton1.setBounds(870, 60, 81, 23);
        jLayeredPane1.add(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        ModifyjButton.setText("修改联系人");

        ModifyjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // ModifyjButtonActionPerformed(evt);

                int i = jTable1.getSelectedRow();
                Record record = new Record();
                if (i != -1) {
                    record.setId(Integer.parseInt(n.getValueAt(i, 0).toString()));
                    record.setUserName(n.getValueAt(i, 1).toString());
                    record.setPhoneNum(n.getValueAt(i, 2).toString());
                    record.setEmail(n.getValueAt(i, 3).toString());
                    AddRecordFrame addRecordFrame = new AddRecordFrame(record);
                    addRecordFrame.setVisible(true);


                }
            }

        });

        ModifyjButton.setBounds(320, 20, 100, 60);
        jLayeredPane1.add(ModifyjButton, javax.swing.JLayeredPane.DEFAULT_LAYER);


        jButton2.setText("刷新");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed();


            }
        });
        jButton2.setBounds(791, 60, 60, 23);
        jLayeredPane1.add(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jMenu1.setText("电话簿系统");

        jMenu1.setFont(new java.awt.Font("微软雅黑", 0, 14)); // NOI18N


        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        pack();
    }

}
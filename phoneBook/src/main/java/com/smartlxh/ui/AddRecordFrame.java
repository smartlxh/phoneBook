/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smartlxh.ui;

import com.smartlxh.dao.dataPersistence;
import com.smartlxh.domain.Record;
import com.smartlxh.service.Service;
import com.smartlxh.util.HashUtil;

import java.awt.*;
import java.util.Collections;
import javax.swing.*;




public class AddRecordFrame extends javax.swing.JFrame {


    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;

    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;

    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;

    private javax.swing.JTextField NameTextField;
    private javax.swing.JTextField TELTextField;
    private javax.swing.JTextField EmailTextField;



    private JPanel jp1 = new JPanel();
    private JPanel jp2 = new JPanel();
    private JPanel jp3 = new JPanel();
    private JPanel jp4 = new JPanel();
    private JLabel message = new JLabel();

    private boolean addOrModrify = false;//false 表示默认为增加联系人，true为修改联系人

    public AddRecordFrame(Record record) {


        if(record != null){
            addOrModrify = true;
        }
        initComponents(record);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        setSize(600,300);

    }



    private void initComponents(final Record record) {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        NameTextField = new javax.swing.JTextField(30);
        jLabel3 = new javax.swing.JLabel();
        TELTextField = new javax.swing.JTextField(30);
        jLabel4 = new javax.swing.JLabel();
        EmailTextField = new javax.swing.JTextField(30);


        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("微软雅黑", 1, 24));
        jLabel1.setText("基本信息");

        jLabel2.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel2.setText("姓名 ：");

        jLabel3.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel3.setText("电话 ：");

        jLabel4.setFont(new java.awt.Font("宋体", 0, 18));
        jLabel4.setText("Email：");

        NameTextField.setSize(20,10);
        //record 不是null，说明是修改，不是增加

        if(record != null){
            NameTextField.setText(record.getUserName());
            EmailTextField.setText(record.getEmail());
            TELTextField.setText(record.getPhoneNum());
            // System.out.println(record.getEmail()+record.getPhoneNum());
        }

        jButton1.setText("确定");
        jButton1.setSize(10,20);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {


                if(NameTextField.getText().equals("") || EmailTextField.equals("") || TELTextField.equals("")){
                    message.setText("请将信息填写完整!");
                    return ;
                }

                dataPersistence p = new dataPersistence();
                Record new_record = new Record();
                new_record.setUserName(NameTextField.getText());
                new_record.setPhoneNum(TELTextField.getText());
                new_record.setEmail(EmailTextField.getText());


                if(record != null){
                    MainFrame.records[record.getId()] = null;
                    p.deleteFromDB(record.getId());
                }


                Service service = new Service();
                int id = service.getKeyByPhoneNum(TELTextField.getText());
                id = HashUtil.handleSolution(id,MainFrame.records);
                new_record.setId(id);
                MainFrame.records[id] = new_record;
                p.saveToDB(new_record);
                setVisible(false);
                MainFrame.jButton2ActionPerformed();


            }
        });

        jButton2.setText("重置");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                NameTextField.setText("");
                TELTextField.setText("");
                EmailTextField.setText("");
            }
        });

        jp1.setLayout(new GridLayout(1,3));
        jp1.add(new JLabel());
        jp1.add(jLabel1);
        jp1.add(new JLabel());



        jp2.setLayout(new GridLayout(3,4));
        jp2.add(new JLabel());
        jp2.add(jLabel2);
        jp2.add(new JLabel());
        jp2.add(NameTextField);
        jp2.add(new JLabel());


        jp2.add(new JLabel());
        jp2.add(jLabel3);
        jp2.add(new JLabel());
        jp2.add(TELTextField);
        jp2.add(new JLabel());


        jp2.add(new JLabel());
        jp2.add(jLabel4);
        jp2.add(new JLabel());
        jp2.add(EmailTextField);
        jp2.add(new JLabel());


        jp3.setLayout(new FlowLayout());
        jp3.add(jButton1);
        jp3.add(jButton2);
        jp4.add(message);
        this.setLayout(new GridLayout(4,1));
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.add(jp4);
        jp4.add(message);
    }




}
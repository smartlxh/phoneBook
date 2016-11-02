package com.smartlxh.ui;

import com.smartlxh.dao.dataPersistence;
import com.smartlxh.service.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lixianhai on 26/10/2016.
 */
public class LoginFrame extends JFrame {

    JTextField jTextField ;
    JPasswordField jPasswordField;
    JLabel jLabel1,jLabel2,jLabelMessage;
    JPanel jp1,jp2,jp3,jp4;
    JButton jb1,jb2;
    public LoginFrame() {
        jTextField = new JTextField(12);
        jPasswordField = new JPasswordField(12);
        jLabel1 = new JLabel("用户名");
        jLabel2 = new JLabel("密   码");
        jLabelMessage = new JLabel();
        jb1 = new JButton("确认");
        jb2 = new JButton("取消");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp4 = new JPanel();


        jb1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                try {
                    String username = jTextField.getText();
                    String passwd = jPasswordField.getText();

                    Service service = new Service();
                    if(service.login(username,passwd)){

                        MainFrame main = new MainFrame();
                        main.setVisible(true);
                        setVisible(false);
                    }
                    else{
                        jLabelMessage.setText("密码或用户名错误！");
                    }
                }catch(Exception exception){

                    jLabelMessage.setText("登录失败");
                }
            }
        });


        jb2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                jTextField.setText("");
                jPasswordField.setText("");
            }
        });
        //设置布局
        this.setLayout(new GridLayout(4, 1));

        jp1.add(jLabel1);
        jp1.add(jTextField);//第一块面板添加用户名和文本框

        jp2.add(jLabel2);
        jp2.add(jPasswordField);//第二块面板添加密码和密码输入框

        jp3.add(jb1);
        jp3.add(jb2); //第三块面板添加确认和取消

        jp3.setLayout(new FlowLayout());
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  //将三块面板添加到登陆框上面
        this.add(jp4);

        jp4.add(jLabelMessage);
        //设置显示
        this.setSize(500, 300);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(400,200);
        this.setVisible(true);
        this.setTitle("登陆");
    }

    public static void main(String []args){
        new LoginFrame();
    }
}

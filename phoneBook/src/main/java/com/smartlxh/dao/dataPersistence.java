package com.smartlxh.dao;


import com.smartlxh.domain.Record;

import java.io.*;
import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.System.getProperty;

/**
 * Created by lixianhai on 19/10/2016.
 */
public class dataPersistence {


    String username;
    String userPasswd;
    String url;
    String driver;
    public final static int MAX = 100;

    public dataPersistence() {
        try {

            Properties prop = new Properties();
            FileInputStream fi = new FileInputStream("./db.properties");
            prop.load(fi);
            username = prop.getProperty("userName");
            userPasswd = prop.getProperty("userPasswd");
            url = prop.getProperty("url");
            driver = prop.getProperty("driver");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        Connection conn = null;
        try {

            Class.forName(driver);

            conn = DriverManager.getConnection(url, username, userPasswd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;

    }


    public boolean saveToDB(Record record){
        try {


            Connection conn = getConnection();
            Statement state = conn.createStatement();

            String sql = "insert into  phoneBook values("+record.getId()+","+"\""+
                    record.getUserName()+"\","+"\""+record.getPhoneNum()+"\""+",\""+record.getEmail()+"\""+")";
            //System.out.println(sql);
            state.execute(sql);
            conn.close();
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

    }



    public Record[] getFromDB(){
        try {


            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            String sql = "select * from phoneBook";
            ResultSet result = statement.executeQuery(sql);
            Record[] records = new Record[MAX];
            while(result.next()){
                int id = result.getInt(1);
                String phoneNum = result.getString(2);
                String userName = result.getString(3);
                String email = result.getString(4);

                Record record = new Record();
                record.setId(id);
                record.setUserName(userName);
                record.setPhoneNum(phoneNum);
                record.setEmail(email);
                records[id]=record;
            }

            return records;
        }catch(Exception e){

            e.printStackTrace();
            return null;
        }

    }

    public void deleteFromDB(int id){
        try {
            Connection conn = getConnection();
            Statement state = conn.createStatement();
            String sql = "delete from phoneBook where id = " +id;
            //System.out.print(sql);
            state.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
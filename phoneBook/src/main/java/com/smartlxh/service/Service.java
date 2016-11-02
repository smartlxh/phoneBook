package com.smartlxh.service;

import com.smartlxh.dao.dataPersistence;
import com.smartlxh.domain.Record;
import com.smartlxh.util.HashUtil;
import sun.util.resources.cldr.zh.CalendarData_zh_Hans_SG;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by lixianhai on 24/10/2016.
 */
public class Service {
    dataPersistence p = null;

    public Service(){
        p = new dataPersistence();
    }





    public boolean login(String userName,String userPasswd){
        try {

            dataPersistence p = new dataPersistence();
            Connection conn = p.getConnection();
            Statement statement = conn.createStatement();
            String sql = String.format("select * from user where userName = \"%s\" and Passwd =\"%s\" ",userName,userPasswd);
            // System.out.println(sql);
            ResultSet result = statement.executeQuery(sql);
            while(result.next()){
                return true;
            }
            return false;

        }catch(Exception e){

            e.printStackTrace();
            return false;

        }

    }

    public int getKeyByPhoneNum(String phoneNum){
        return HashUtil.mulHash(phoneNum);
    }




    public  int getKeyByUserName(String userName){

        return HashUtil.mulHash(userName);

    }
    public Record findByName(String userName,Record [] records){

        int key = getKeyByPhoneNum(userName);
        key = HashUtil.handleSolution(key,records);
        return records[key];
    }

    public Record findByPhoneNum(String phoneNum,Record [] records){

        int key = getKeyByPhoneNum(phoneNum);
        key = HashUtil.handleSolution(key,records);
        return records[key];
    }

    public void  modifyByName(String userName,Record [] records,Record record){

        int key = getKeyByUserName(userName);
        key = HashUtil.handleSolution(key,records);
        records[key] = new Record(record);

    }

    public void  modifyByphoneNum(String phoneNum,Record [] records,Record record){
        int key = getKeyByPhoneNum(phoneNum);
        key = HashUtil.handleSolution(key,records);
        records[key] = new Record(record);
    }
}

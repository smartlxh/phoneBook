package com.smartlxh.domain;

/**
 * Created by lixianhai on 24/10/2016.
 */
public class Record {

    private int id;
    private String userName;
    private String phoneNum;
    private String email;

    public Record(Record record){

        this.id = record.getId();
        this.userName = record.getUserName();
        this.phoneNum = record.getPhoneNum();
        this.email = record.getEmail();
    }

    public Record(){

    }


    public int  getId(){
        return id;
    }


    public void setId(int id){
        this.id = id;
    }
    public String  getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getPhoneNum(){
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}


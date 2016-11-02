package com.smartlxh.util;

import com.smartlxh.domain.Record;

/**
 * Created by lixianhai on 24/10/2016.
 */
public class HashUtil {

    private static int mod = 100;


    public static void setMod(int modd){
        mod = modd;
    }


    public static int mulHash(String str){//除留余数法hash
        int length = str.length();
        int key=0;
        for(int i=0;i<length;i++){
            key += str.charAt(i);
        }

        return key % mod;
    }

    public  static  int BKDRHash(String str)  //BKDR hash
    {
        int seed = 131; // 31 131 1313 13131 131313 etc..
        int hash = 0;
        int length = str.length();
        for(int i=0;i<length;i++){
            hash += hash * seed + str.charAt(i);
        }

        return (hash & 0x7FFFFFFF) % mod;
    }


    //线性探查法解决冲突
    public static int handleSolution(int key, Record[] records){
        while(records[key] != null){
            key = (key + 1) % 100;
        }

        return key;
    }

    //二次探查法解决冲突

    public static  int handleSolutionByQud(int key,Record[] records){
        int i = 0;
        while(records[key] != null){

            if(key % 2 == 0 ){
                key = key + i*i;
            }
            else {
                key = key - i*i;
            }
            key = key % mod;
            i++;
            if(i > mod){
                return -1;
            }
        }
        return key;
    }

    //双散列解决冲突
    public static  int handleSolutionByDoubleHash(int key1,int key2,Record[] records){
            int i = 0;
            while(records[key1] != null){
                key1 = (key1 + key2) % mod;
                i++;
                if(i > mod){
                    return -1;
                }
            }
            return key1;
    }

}

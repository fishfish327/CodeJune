package com.company;
import java.util.*;

public class DataBase {
    Map<String, TreeMap<Integer, Integer>> dataBaseMap;
    public DataBase(){
        dataBaseMap = new HashMap<>();
        System.out.println("initialize DataBase" );
    }
    public void set(String key, int value, int version){
        if(!dataBaseMap.containsKey(key)){
            dataBaseMap.put(key, new TreeMap<>());
            System.out.println("initialize subTreemap with key: " + key);
        }
        dataBaseMap.get(key).put(version, value);
        System.out.println("add version: " + version+ " to key: " + key + " and value :" + value);
    }
    public int get(String key, int version){
        if(!dataBaseMap.containsKey(key)){
            System.out.println("there is no key names :" + key);
            return  -1;
        }
        TreeMap<Integer, Integer> subMap = dataBaseMap.get(key);
        Map.Entry<Integer, Integer> lowerBnd = subMap.floorEntry(version);
        if(lowerBnd == null){
            System.out.println("there is no version in " + key + "'s set lower than " + version );
            return -1;
        }
        System.out.println("the cloest key in  " + key + "'s set is " + lowerBnd.getValue() );
        return lowerBnd.getValue();
    }
    public int numWithValue(int value, int byKey){//check if byKey then we might need to count each key once a time
        int count = 0;
        for(String key : dataBaseMap.keySet()){
            TreeMap<Integer, Integer> subMap = dataBaseMap.get(key);
            for(Integer subKey : subMap.keySet()){
                if(subMap.get(subKey) == value){
                    count++;
                    if(byKey == 1) break;
                }
            }
        }
        if(byKey == 1 ) System.out.println("there are " + count + " keys in db with value " + value);
        else System.out.println("there are " + count + " versioned keys in db with value " + value );
        return count;
    }

    public void unSet(String key){
        if(dataBaseMap.containsKey(key)){
            System.out.println("DB removed " + key);
            dataBaseMap.remove(key);
            return;
        }
        System.out.println("no such key in DB");
    }
}

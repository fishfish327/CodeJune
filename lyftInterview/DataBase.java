package com.company;
import java.util.*;
public class DataBase {
    int timeStep;
    Map<String, TreeMap<Integer, Integer>> allVersonMap;
    Map<String, Integer> latestVersionMap;
    public DataBase(){
        timeStep = 0;
        allVersonMap = new HashMap<>();
        latestVersionMap = new HashMap<>();
    }
    public int put(String key, int value){
        timeStep++;
        if(!allVersonMap.containsKey(key)){
            allVersonMap.put(key, new TreeMap<>());
        }
        allVersonMap.get(key).put(timeStep, value);
        latestVersionMap.put(key, value);
        return timeStep;
    }
    public Integer get(String key){
        if(!latestVersionMap.containsKey(key)){
            return null;
        }
        return latestVersionMap.get(key);
    }
    public Integer get(String key, int version){
        if(!latestVersionMap.containsKey(key)){
            return null;
        }
        TreeMap<Integer,Integer> subMap = allVersonMap.get(key);
        Map.Entry<Integer, Integer> closestVersion = subMap.floorEntry(version);
        if(closestVersion == null){
            return null;
        }
        return closestVersion.getValue();
    }
}

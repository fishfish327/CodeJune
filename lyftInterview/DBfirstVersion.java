package com.company;

import java.io.BufferedReader;
import java.io.*;
import  java.util.*;
import java.util.concurrent.DelayQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("result.txt")));
        int q = Integer.parseInt(bufferedReader.readLine().trim());
        
        List<List<String>> settings = new ArrayList<>();
        List<List<Integer>> settingNums = new ArrayList<>();

        for (int qItr = 0; qItr < q; qItr++) {
            String s = bufferedReader.readLine();
            String[] ways = s.split("\\pP");

            List<String> partList = new ArrayList<>();
            partList.add(ways[0]);

            boolean skipSecIndex = ways[0].trim().equals("get") || ways[0].trim().equals("set") || ways[0].trim().equals("unSet");
            if(skipSecIndex){
                partList.add(ways[1]);
            }
            settings.add(partList);
            List<Integer> subList = new ArrayList<>();
            for(int j = skipSecIndex ? 2 : 1; j < ways.length; j++){
                subList.add(Integer.valueOf(ways[j].trim()));
            }
            settingNums.add(subList);
            //   bufferedWriter.write(String.valueOf(result));
           // bufferedWriter.newLine();
        }

        int i = 0, value, timeStep;
        String key;
        DataBase db = null;
        Deque<List<String>> settingStack = new ArrayDeque<>();
        Deque<List<Integer>> settingNumsStack = new ArrayDeque<>();
        
        while(i < settings.size()){
            List<String> setting = settings.get(i);
            List<Integer> settingNum = settingNums.get(i);
            
      /*  }
        while(){
            List<String> setting = settingStack.pollFirst();
            List<Integer> settingNum = settingNumsStack.pollFirst();*/
            switch(setting.get(0)){
                case "DataBase" :
                    if(db == null) db = new DataBase();
                    break;
                case "get":
                    key = setting.get(1);
                    timeStep = settingNum.get(0);
                    db.get(key, timeStep);
                    break;
                case "set":
                    key = setting.get(1);
                    value = settingNum.get(0);
                    timeStep = settingNum.get(1);
                    db.set(key, value, timeStep);
                    break;
                case "numWithValue":
                    value = settingNum.get(0);
                    int byKey = settingNum.get(1);
                    db.numWithValue(value, byKey);
                    break;
                case "unSet":
                    key = setting.get(1);
                    db.unSet(key);
                    break;

            }
            i++;
        }
        bufferedReader.close();
      //  bufferedWriter.close();
    }
}

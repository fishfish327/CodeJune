package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
    public static List<String> formalInput(String s){
        String[] command = s.split("\\s");
        return Arrays.asList(command);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int inputSize= Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> commandLines = new LinkedList<>();

        for (int readCount = 0; readCount < inputSize; readCount++) {
            String s = bufferedReader.readLine();
            List<String> commandInputs = formalInput(s);
            commandLines.add(commandInputs);
        }

        DataBase db = new DataBase();
        for(int commandIndex = 0; commandIndex < commandLines.size(); commandIndex++){
            int value, version;
            String key;
            List<String> processCommand = commandLines.get(commandIndex);
            String command = processCommand.get(0);
            switch(command){
                case "GET":
                    if(processCommand.size() == 3){
                        key = processCommand.get(1);
                        version = Integer.valueOf(processCommand.get(2)); // TODO check invalid
                        Integer targetValue = db.get(key, version);
                        if(targetValue == null){
                            System.out.println("GET " + key + "(#" +version + ") = <NULL>");
                        }else{
                            System.out.println("GET " + key + "(#" +version + ") = " + targetValue);
                        }
                    }else if(processCommand.size() == 2){
                        key = processCommand.get(1);
                        Integer targetValue = db.get(key);
                        if(targetValue == null){
                            System.out.println("GET " + key + " = <NULL>");
                        }else{
                            System.out.println("GET " + key + " = " + targetValue);
                        }
                    }else{
                        System.out.println("invalid input");
                        //continue;
                    }
                    break;
                case "PUT":
                    if(processCommand.size() != 3){
                        System.out.println("invalid input");
                        //continue;
                    }
                    key = processCommand.get(1);
                    value = Integer.valueOf(processCommand.get(2)); // TODO check invalid
                    int getVersion = db.put(key, value);
                    System.out.println("PUT (#" + getVersion + ") " + key + " = " + value);
                    break;

                default:
                    System.out.println("invalid input");
                    continue;
            }

        }
        bufferedReader.close();
        //  bufferedWriter.close();
    }
}

package com.company;

import java.io.BufferedReader;
import java.io.*;
import  java.util.*;

public class Main {
    private static String[] formalInput(String s){
        String[] output = s.split("//pP");
        for(int i = 0; i < output.length; i++){
            output[i] = output[i].trim();
        }
        if(output.length > 0) output[0] = output[0].toUpperCase();
        return output;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int inputSize= Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> commandLines = new ArrayList<>();
        List<List<Integer>> commandOptions = new ArrayList<>();

        for (int readCount = 0; readCount < inputSize; readCount++) {

            String s = bufferedReader.readLine();
            String[] commandInputs = formalInput(s);
            List<String> subCommand = new ArrayList<>();
            boolean skipSecIndex = (commandInputs[0].equals("GET")
                                || commandInputs[0].equals("SET")
                                || commandInputs[0].equals("UNSET"));

            subCommand.add(commandInputs[0]);
            if(skipSecIndex){
                subCommand.add(commandInputs[1]);
            }
            commandLines.add(subCommand);

            List<Integer> subCommandOptions = new ArrayList<>();
            for(int j = skipSecIndex ? 2 : 1; j < commandInputs.length; j++){
                subCommandOptions.add(Integer.valueOf(commandInputs[j]));
            }
            commandOptions.add(subCommandOptions);
        }

        int commandPtr = 0, value, timeStep;
        String key;
        DataBase db = new DataBase();
        Deque<List<String>> commandLineQueue = new ArrayDeque<>();
        Deque<List<Integer>> commandOptionQueue = new ArrayDeque<>();

        while(commandPtr < commandLines.size()){
            List<String> commandLine = commandLines.get(commandPtr);
            List<Integer> commandLineOption = commandOptions.get(commandPtr);
            if(commandLine.get(0).equals("ROLLBACK")){
                if(commandLineQueue.peekLast().isEmpty() || !commandLineQueue.peekLast().get(0).equals("COMMIT")){
                    throw new Exception("it is an illegal rollback");
                }
                while(!commandLineQueue.peekLast().get(0).equals("BLOCK")){
                    commandLineQueue.pollLast();
                    commandOptionQueue.pollLast();
                }
            }else{
                commandLineQueue.offer(commandLine);
                commandOptionQueue.offer(commandLineOption);
            }
            commandPtr++;
        }
        while(!commandLineQueue.isEmpty()){
            List<String> processCommand = commandLineQueue.pollFirst();
            List<Integer> processCommandOption = commandOptionQueue.pollFirst();
            switch(processCommand.get(0)){
                case "GET":
                    key = processCommand.get(1);
                    timeStep = processCommandOption.get(0);
                    db.get(key, timeStep);
                    break;
                case "SET":
                    key = processCommand.get(1);
                    value = processCommandOption.get(0);
                    timeStep = processCommandOption.get(1);
                    db.set(key, value, timeStep);
                    break;
                case "NUMWITHVALUE":
                    value = processCommandOption.get(0);
                    int byKey = processCommandOption.get(1);
                    db.numWithValue(value, byKey);
                    break;
                case "UNSET":
                    key = processCommand.get(1);
                    db.unSet(key);
                    break;
                default:
                    continue;
            }


        }
        bufferedReader.close();
      //  bufferedWriter.close();
    }
}

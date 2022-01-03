package com.company;

import java.io.BufferedReader;
import java.io.*;
import  java.util.*;

public class Main {
   // static final String EXEC = "EXEC";
    static final String BLOCK = "BLOCK";
    static final String ROLLBACK = "ROLLBACK";
    static final String COMMIT = "COMMIT";
    private static List<CommandUnit> preprocessing(Queue<List<String>> commandLines, Queue<List<Integer>> commandOptions){
        if(commandLines.isEmpty()) return null;
        List<CommandUnit> result = new ArrayList<>();

        while(!commandLines.isEmpty()){
            List<String> commandLine = commandLines.poll();
            List<Integer> commandOption = commandOptions.poll();
            String option = commandLine.get(0);
            if(option.equals(BLOCK)){
                result.addAll(preprocessing(commandLines, commandOptions));
            }else if(option.equals(ROLLBACK)){
                return new ArrayList<>();
            }else if(option.equals(COMMIT)){
                break;
            }else{
                result.add(new CommandUnit(commandLine, commandOption));
            }

        }
        return result;
    }
    private static String[] formalInput(String s){
        String[] output = s.split("\\pP");
        for(int i = 0; i < output.length; i++){
            output[i] = output[i].trim();
        }
        if(output.length > 0) output[0] = output[0].toUpperCase();
        //System.out.println(Arrays.toString(output));
        return output;
    }
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int inputSize= Integer.parseInt(bufferedReader.readLine().trim());

        Queue<List<String>> commandLines = new LinkedList<>();
        Queue<List<Integer>> commandOptions = new LinkedList<>();

        for (int readCount = 0; readCount < inputSize; readCount++) {

            String s = bufferedReader.readLine();
            String[] commandInputs = formalInput(s);
            List<String> commandLine = new ArrayList<>();
            boolean skipSecIndex = (commandInputs[0].equals("GET")
                                || commandInputs[0].equals("SET")
                                || commandInputs[0].equals("UNSET"));

            commandLine.add(commandInputs[0]);
            if(skipSecIndex){
                commandLine.add(commandInputs[1]);
            }
            commandLines.add(commandLine);

            List<Integer> commandOption = new ArrayList<>();
            for(int j = skipSecIndex ? 2 : 1; j < commandInputs.length; j++){
                commandOption.add(Integer.valueOf(commandInputs[j]));
            }
            commandOptions.add(commandOption);
        }

        int commandPtr = 0, value, version;
        String key;
        DataBase db = new DataBase();
        List<CommandUnit> commandUnits = preprocessing(commandLines, commandOptions);
        for(CommandUnit cmdUnit: commandUnits){
            System.out.println(cmdUnit.commandLine);
            System.out.println(cmdUnit.commandOption);
        }
        while(commandPtr < commandUnits.size()){
            CommandUnit curUnit = commandUnits.get(commandPtr);
            List<String> processCommand = curUnit.commandLine;
            List<Integer> processCommandOption = curUnit.commandOption;
            switch(processCommand.get(0)){
                case "GET":
                    key = processCommand.get(1);
                    version = processCommandOption.get(0);
                    db.get(key, version);
                    break;
                case "SET":
                    key = processCommand.get(1);
                    value = processCommandOption.get(0);
                    version = processCommandOption.get(1);
                    db.set(key, value, version);
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
            commandPtr++;

        }
        bufferedReader.close();
      //  bufferedWriter.close();
    }
}

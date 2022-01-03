package com.company;
import  java.util.*;
public class CommandUnit {
    List<String> commandLine;
    List<Integer> commandOption;
    public CommandUnit(List<String> commandLine, List<Integer> commandOption){
        this.commandLine = commandLine;
        this.commandOption = commandOption;
    }
}

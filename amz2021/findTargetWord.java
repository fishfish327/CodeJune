package com.company;
import java.util.*;
public class Main {
    class Tupe {

        char firstLetter;
        char secondLetter;
        public Tupe(char firstLetter, char secondLetter){
            this.firstLetter = firstLetter;
            this.secondLetter = secondLetter;
        }
        public int hashCode(){
            return (this.firstLetter +" "+ this.secondLetter).hashCode();
        }
        public boolean equals(Object t1){
            if(t1 instanceof  Tupe){
                return ((Tupe) t1).firstLetter == this.firstLetter && ((Tupe) t1).secondLetter == this.secondLetter;
            }
            return false;
        }
    }
    static boolean result;
    static Map<Character, List<Tupe>> mapForTupes = new HashMap<>();
    static Set<Tupe> visited = new HashSet<>();
    public static boolean findTargetWord(String s, List<Tupe> tupes){

        for(Tupe t : tupes){
            mapForTupes.putIfAbsent(t.firstLetter, new ArrayList<>());
            mapForTupes.putIfAbsent(t.secondLetter, new ArrayList<>());
            mapForTupes.get(t.firstLetter).add(t);
            mapForTupes.get(t.secondLetter).add(t);
        }
        result = false;
        dfs(s, 0);
        return result;
    }
    public static void dfs( String s, int index){
        if(result) return;
        if(s.length() == index){
            result = true;
            return;
        }
        char ch = s.charAt(index);
        if(!mapForTupes.containsKey(ch) || visited.containsAll(mapForTupes.get(ch)) return;
        for(Tupe t : mapForTupes.get(ch)){
            if(!visited.contains(t)){
                visited.add(t);
                dfs(s, index + 1);
                visited.remove(t);
            }
        }
    }
    public static void main(String[] args) {
	// write your code here
    }
}

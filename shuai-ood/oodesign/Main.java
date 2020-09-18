package oodesign;
import java.util.*;
public class Main {
    public Party waitingList(Party[] partys, Table table){
        Arrays.sort(partys, (a, b) -> b.waitingTime - a.waitingTime);
        Queue<Party> queue = new LinkedList<>(Arrays.asList(partys));
        Queue<Party> remain = new LinkedList<>();
        while(!queue.isEmpty()){
            Party p = queue.poll();
            if(p.canFit(table)){
                return p;
            } else if(p.canRemain(table)){
                remain.add(p);
            }
        }
        if(remain.size() > 0){
            remain.poll();
        } else {
            return null;
        }
       
    }
    public static void main(String[] args){
        
        AbstractParty party = new Party(4, "party", 100);
        AbstractParty table = new Table(6, 1);
        System.out.println(party.canFit(table));
        Party p = (Party) party;

        System.out.println(party.size);
        System.out.println(p.name);
    }
    
}
package oodesign;

public class Party extends AbstractParty{
    public String name;
    public int waitingTime;

    public Party(int size, String name,int waitingTime){
           super(size);
           this.name = name;
           this.waitingTime = waitingTime;
    }
    
}
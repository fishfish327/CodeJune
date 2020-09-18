package oodesign;

public abstract class AbstractParty  {
    int size;
    public AbstractParty(int size){
        this.size = size;
    }

    public boolean canFit(AbstractParty table){
        return table.size >= this.size && table.size <= 2 * this.size;
    }

    public boolean canRemain(AbstractParty table){
        return table.size >= this.size;
    }

}
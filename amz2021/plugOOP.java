package com.company;

interface Plug{
    enum ChargeType{
        BATTERY,
        WALLPLUG,
        CHARGABLEBATTERY
    }
}
//can we put plug like this?
public interface Device {
    enum State{
    CHARGED,
    UNCARGED
    };
    State state;
    public boolean chargeable(Plug plug){};
    public void charge(Plug plug){}; 
    public void unCharge(){};
    public void chargeAlarm(){};
}
public class Phone implements Device{
    State state;
    int batteryVolume;
    int remainingBattery;
    public Phone(int remainingBattery, int batteryVolume){
        this.remainingBattery = remainingBattery;
        this.batteryVolume = batteryVolume;
        state = State.UNCARGED;
    }
    @Override
    public boolean chargeable(Plug plug) {

        switch(plug.ChargeType){
            case BATTERY:
            case WALLPLUG:
                return true;
        }
        return false;
    }

    @Override
    public void charge(Plug plug) {
        if(chargeable(plug)){
            state = State.CHARGED;
        }
        while(state == State.CHARGED && remainingBattery < batteryVolume){
            remainingBattery++;
        }
    }
    public void unCharge(){
        if(batteryVolume >= 5 * remainingBattery){
            chargeAlarm();
        }
        state = State.UNCARGED;
    }
    @Override
    public void chargeAlarm() {
        System.out.println("need to charge!!!");
    }
}

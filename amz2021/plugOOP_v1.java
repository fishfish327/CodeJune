package com.company;

/*觉得抽象类更适合这个
然后除了chargeable这个部分一直都不会（不知道interface、抽象类里面能不能带enum啊，这俩里面能不能带初始值啊，对应值
*/
interface Plug{
    enum ChargeType{
        BATTERY(PHONE),
        WALLPLUG(PAD),
        CHARGABLEBATTERY(LAPTOP)//如果这样的话我不知道怎么初始这些东西 我得去看看
    }
}
//can we put plug like this?
abstract class Device {
    enum State{
    CHARGED,
    UNCARGED
    };
    State state;;
    int batteryVolume;
    int remainingBattery;
    public Device(int remainingBattery, int batteryVolume){
        this.remainingBattery = remainingBattery;
        this.batteryVolume = batteryVolume;
        state = State.UNCARGED;
    }
    public abstract boolean chargeable(Plug plug);
    public void charge(Plug plug){
        if(chargeable(plug)){
            state = State.CHARGED;
        }
        while(state == State.CHARGED && remainingBattery < batteryVolume){
            remainingBattery++;
        }
    };
    public void unCharge(){};
    public void chargeAlarm(){};//为什么我会加一个unCharge呢 是因为我不知道除了这个办法我什么时候需要检测电量 和激活chargeAlarm
}
public class Phone extends Device{
    State state;
    int batteryVolume;
    int remainingBattery;
    public Phone(int remainingBattery, int batteryVolume){
        super(remainingBattery, batteryVolume);
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

  //  @Override
    public void charge(Plug plug) {
        super(plug);
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

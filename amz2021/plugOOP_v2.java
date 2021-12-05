package com.company;
enum ChargeType{
    BATTERY,
    WALLPLUG,
    CHARGABLEBATTERY
}
interface Plug{
   ChargeType chargeType = null;//不知道这个情况下怎么处理， 初始化能做null吗
    //怎么做声明呢 这么声明可以吗？
    public Plug(ChargeType type){
        this.chargeType = type;
    };
}
enum State{
    CHARGED,
    UNCARGED
};
//can we put plug like this?
abstract class Device {

    State state;;
    private int batteryVolume;
    private int remainingBattery;
    BatteryStatus batteryStatus;
    public Device(int remainingBattery, int batteryVolume){
        this.remainingBattery = remainingBattery;
        this.batteryVolume = batteryVolume;
        state = State.UNCARGED;
        checkBatteryStatus();
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
    public void chargeAlarm() {
        if(batteryStatus == BatteryStatus.LOW){
            System.out.println("need to charge!!!");
        }
    };
    public void checkBatteryStatus(){
        if(batteryVolume >= remainingBattery * 5){
            batteryStatus = BatteryStatus.LOW;
        }else if(batteryVolume <= remainingBattery + 20){
            batteryStatus = BatteryStatus.HIGH;
        }else{
            batteryStatus = BatteryStatus.NORMAL;
        }
    }
}
enum BatteryStatus{
    LOW,
    NORMAL,
    HIGH,
}
public class Phone extends Device{
    State state;
    int batteryVolume;
    int remainingBattery;
    BatteryStatus batteryStatus;
    public Phone(int remainingBattery, int batteryVolume){
        super(remainingBattery, batteryVolume);
    }
    @Override
    public boolean chargeable(Plug plug) {
        switch(plug.chargeType){
            case ChargeType.BATTERY:
            case ChargeType.WALLPLUG://我在这里想表达的是手机能用这两种方法充电 为啥会被报错？
                return true;
        }
        return false;
    }
    public void checkBatteryStatus(){
        super.checkBatteryStatus();
    }
  //  @Override
    public void charge(Plug plug) {
        super.charge(plug);
    }

    @Override
    public void chargeAlarm(){
        super.chargeAlarm();
    }
}
public class Kindle extends Device{
    State state;
    int batteryVolume;
    int remainingBattery;
    BatteryStatus batteryStatus;
    public Kindle(int remainingBattery, int batteryVolume){
        super(remainingBattery, batteryVolume);
    }
    @Override
    public boolean chargeable(Plug plug) {
        switch(plug.chargeType){
            case ChargeType.WALLPLUG:
                return true;
        }
        return false;
    }
    public void checkBatteryStatus(){
        if(remainingBattery <= 15){
            batteryStatus = BatteryStatus.LOW;
        }else if(remainingBattery >= 20){
            batteryStatus = BatteryStatus.HIGH;
        }else{
            batteryStatus = BatteryStatus.NORMAL;
        }
    }
    //  @Override
    public void charge(Plug plug) {
        super.charge(plug);
    }

    @Override
    public void chargeAlarm(){
        super.chargeAlarm();
    }
}

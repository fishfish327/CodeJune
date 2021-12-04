package com.company;
/*
https://leetcode.com/discuss/interview-question/1073375/amazon-seattle-virtual-onsite-sre2-all-4-rounds
Create a doggie daycare app:

to enter dogs need to be vaccinated
dogs less than 4 months and more than 10 yo go to a kessel, capacity 20
small dogs enter into small gods playgrounds, capacity 30
big dogs enter into small gods playgrounds, capacity 20
dogs come and go all the time
'''
class DoggieDayCare():
self.kessel = 0
self.small = 0
self.big = 0
def welcome_doggie(dogId):
pass
def bye_bye_doggie(dogId):
pass
'''
 */
enum Size{
    LARGE, SMALL, KESSEL
};
public class Dog{
    boolean vaccined;
    double age;
    Size size;
    public Dog(double age, boolean vaccined, Size size){
        this.size = size;
        this.vaccined = vaccined;
        this.age = age;
    }
}
public class DogGarden {
    int capacity;
    int largeCapacity;
    int smallCapacity;
    int kesselCapacity;
    int largeCount;
    int smallCount;
    int kesselCount;
    public DogGarden(){
        this.largeCapacity = 20;
        this.kesselCapacity = 20;
        this.smallCapacity = 20;
        largeCount = 0;
        smallCount = 0;
        kesselCount = 0;
        
    }
  //  HashMap<String, Integer> capacity? = ... if we need to know which puppy can be pick up -> Key: name,value: ID
    //or Key == Dog
    public boolean welcomeDog(Dog doggie){
        if(doggie.age >=10d || doggie.age <= 0.4d){
            doggie.size = Size.KESSEL;// not sure would be good to set like this or move this part to DogSetting
        }
        if(!doggie.vaccined){
            System.out.println("sorry we not welcome unvaccined doggie");
            return false;
        }
        switch(doggie.size){
            case LARGE:
                if(largeCount == largeCapacity){
                    System.out.println("capacity is full");
                    return false;
                }
                largeCount++;
                break;
                //same as small and kessel
        }
        
    }
    public boolean byeDog(Dog dog){
       if(map.containsKey(dog)) map.remove(dog)
    }

}

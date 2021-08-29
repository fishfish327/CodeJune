// "static void main" must be defined in a public class.

//这个思路把不一样的size的party 分成Map<Size, List<Party>> 存了起来，然后每次有桌子空出来就把所有小于等于饭桌size的party遍历一次 找到最小的party
//这里不能用pq的原因是每次要求不一样 上一个可能是一个size = 9 的table空出来，下一个是size = 2 的table  这样pq里很大一部分的都不能用
//之所以设计成这种，是因为这可能可以解决饭馆里table for4 只能坐4个人的话 这样比较好O(1) 查找



//而如果这么设计的话，当桌子size > party 那可以无限劈桌拼桌 但问题可能需要解决一下到底是给这个桌子塞满还是先到先得


//而当我们有比如说四个桌子，每个桌子size = 2 解决8个人的party 可能就是拿出来一个8 put(6,new Party(8 - 2, sameName, sameTimeStamp)) 这样其实能保证如果这个party是最老的就一直call 到这个party 没人

public class WaitList {
    class Party{

        int size;
        String name;
        Timestamp ts;
        public Party(int size, String name, Timestamp ts){
            
            this.size = size;
            this.name = name;
            this.ts = ts;
        }
    }
   

//follow up 是 解决饭馆只想4人桌坐4个人，队里即使只有2人的party也不给安排。 暂时也想不出更多更好的解决办法了
    //table 类 如果size 比xx大或者啥
   Map<Integer, ArrayDeque<Party>> partyMap;
    public WaitList(){
       partyMap = new HashMap<>();
  
    }
    
    public static void addToWL(Party p){
        int size = p.size;
        partyMap.putIfAbsent(p.size, new ArrayDeque<>());
        partyMap.get(p.size).addLast(p);
        
    }
    public static boolean setToTargetTable(Table t){
        int lowestTimestamp = Integer.MAX_VALUE;
        Party res = null;
        for(int i = 1; i <= t.size(); i++){
           if( partyMap.containsKey(i)){
               if(partyMap.get(i).peekFirst().timestamp < lowestTimestamp){
                   res = partyMap.get(i).peekFirst();
                   lowestTimestamp = party;Map.get(i).peekFirst().timestamp
               }
            
           }
        }
        partyMap.get(res.size).pollFirst();
        t.set(res);
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

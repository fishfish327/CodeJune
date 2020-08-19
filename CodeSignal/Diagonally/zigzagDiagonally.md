```java
// "static void main" must be defined in a public class.
public class Main {
    public static int[][] sortMatrix(int[][] input){
        int[][] output = new int[input.length][input[0].length];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < input.length; i++){
            for(int j = 0; j < input[0].length; j++){
              map.put(input[i][j] ,map.getOrDefault(input[i][j], 0) + 1);
              System.out.println(map);
            }
            
        }
        
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                 (a,b) -> a.getValue() == b.getValue() ? a.getKey() - b.getKey() : a.getValue()-b.getValue()
        );
        // <int[]> 
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            pq.offer(entry);
        }
        int i = input.length - 1, j = input[0].length - 1;
        int curkey = pq.poll().getKey();
        int cur = map.get(curkey);

        for(int y = j; y >= 0; y--){
            int num = y;
            int x = i;
            
            while(num <= j){
                output[x][num] = curkey;
                cur--;
                if(cur == 0){
                    curkey = pq.poll().getKey();
                    cur = map.get(curkey);
                }
                x--;
                num++;
               
            }
        }
        for(int x = i - 1; x >= 0; x--){
            int num = x;
            int y = 0;
            System.out.println(num);
            while(num >= 0){
                output[num][y] = curkey;
                cur--;
                if(cur == 0){
                    if(pq.size()!= 0)curkey = pq.poll().getKey();
                    
                   //System.out.println(map.containsKey(curkey));
                    cur = map.get(curkey);
                }
                num--;
                y++;
                
            }
        }
        
        
        return output;
    }
    public static void main(String[] args) {
       int[][] input = new int[][] {{2,2,3},{1,1,1},{2,2,4}};
        int[][] output = sortMatrix(input);
        for(int i = 0; i < output.length; i++){
            for(int j = 0; j < output[1].length; j++){
               System.out.print(output[i][j] + "   "); 
                
            }
            System.out.println();
        }
        
   
    }
}
```

一个可能的答案：
```java
// "static void main" must be defined in a public class.
public class Main {
    private static List<Integer> testFunc(int[][] test, int par){
        PriorityQueue<int[]> pq = new PriorityQueue<>(test.length, new Comparator<int[]>(){
            public int compare(int[] list1, int[] list2){
                if(list1[2] != list2[2]){
                    return list2[2] - list1[2];
                }
                return list1[3] - list2[3];//earlier time step
            }
        });
        for(int i = 0; i < test.length; i++){
            pq.offer(test[i]);
        }
        while(par > 0){
            int[] cur = pq.poll();
            par -= cur[1];
        }
        List<Integer> res = new ArrayList<>();
        while(pq.size() != 0){
            res.add(pq.poll()[0]);
        }
         Collections.sort(res);
        return res;
        
    }
    
    public static void main(String[] args) {
       int[][] test = new int[][]{{1,5,5,0},{2,7,8,1},{3,7,5,1},{4,10,3,3}};
        List<Integer> res = testFunc(test,18);
        System.out.println(res);
    }
}
```

// 通过轮询的方式分配相同价格的股票
```java
while(par > 0 && !pq.isEmpty()){
       int[] curr = pq.poll();
       Queue<int[]> queue = new LinkedList<>();
       while(!pq.isEmpty() && pq.peek()[1] == curr[1]){
           curr = pq.poll();
           queue.add(curr);
       }
       totalShare = ipo(queue, par);
       if(totalShare == 0){
           for(int[] bid: queue){
               pq.add(bid);
           }
       }
        
    }
    public int ipo(Queue<int[]> queue, int totalShare){
        if(queue.size() == 1){
            totalShare -= queue.poll()[1];
        } else {
            Queue<int[]> copy = new LinkedList<>();
            while(totalShare > 0 && queue.size() > 0){
                int[] curr = queue.poll();
                totalShare --;
                curr[1] --;
                if(curr[1] != 0){
                    copy.add(curr);
                }
            }
            while(totalShare > 0 && copy.size() > 0){
                int[] curr = copy.poll();
                totalShare --;
                curr[1] --;
                if(curr[1] != 0){
                    copy.add(curr);
                }
            }
        }
        return totalShare;
    }
```

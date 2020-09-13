原题是这道
https://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=519180&ctid=229043
之前花了很大劲理解题目的意思，后来问了朋友 发现漏看了一句： 
* Any value is more than the allowed increase above the window average in ALL windows in which it appears.
他的意思是，存不存在一个数，使得这个数大于它所在的滑窗的平均值*（1+t），这个大于要对它所在的所有滑窗成立
然后代码就是魔改maximum sliding windows  方便捋清楚逻辑
代码如下
把alert分开算了

```java
public class Main {
    public static boolean alert(int[] arr, int k, float time){
        return alert1(arr, k, time) || alert2(arr, k, time);
    }
    public static boolean alert1(int[] arr, int k, float time){
        for(int i = 0; i < arr.length -1; i++){
            if(time * arr[i] < (float)arr[i + 1]){ 
               // System.out.println(time * arr[i]);
                return true;
            }
        }
        return false;
    }
    public static boolean alert2(int[] arr, int k, float time){
            List<Integer> max = maxSlidingWindow(arr,k);
            //System.out.println(max.size());
            List<Integer> sum = sumSlidingWindow(arr,k);
           // System.out.println(sum.size());
            for(int i = 0; i < max.size(); i++){
                if((float) k * max.get(i) > time * sum.get(i)){
                    return true;
                }
            }
        return false;
    }
    public static List<Integer> sumSlidingWindow(int[] nums, int k){
        int sum = 0;
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < k - 1; i++){
            sum += nums[i];
        }
        int left = 0;
        for(int i = k - 1; i < nums.length; i++){
            
            sum += nums[i];
            res.add(sum);
            sum -= nums[left];
            
            left++;
        }
        
        return res;
    }
    static int[] a;
    
    static void inQueue(Deque<Integer> deque, int pos) {
        while (!deque.isEmpty() && a[deque.peekLast()] <= a[pos]) {
            deque.pollLast();
        }
        deque.offer(pos);
    }
    
    static void outQueue(Deque<Integer> deque, int pos) {
        if (deque.peekFirst() == pos) {
            deque.pollFirst();
        }
    }
    
    public static ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        a = nums;
        
        ArrayList<Integer> ans = new ArrayList<Integer>();
        Deque<Integer> deque = new ArrayDeque<Integer>();
        if (nums.length == 0) {
            return ans;
        }
        for (int i = 0; i < k - 1; i++){
            inQueue(deque, i);
        }
        
        for(int i = k - 1; i < nums.length; i++) {
            inQueue(deque, i);
            ans.add(a[deque.peekFirst()]);
            outQueue(deque, i - k + 1);
        }
        return ans;

    }
    public static void main(String[] args) {
       System.out.println(alert(new int[]{1, 2, 100, 2, 2}, 3, 1.5f));
        System.out.println(alert(new int[]{1, 2, 4, 2, 2}, 3, 2.0f));
        System.out.println(alert(new int[]{1, 2, 100, 2, 2}, 2, 2.5f));
       // System.out.println( sumSlidingWindow(new int[]{1, 2, 100, 2, 2},2));
    }
}
```

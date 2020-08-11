来源
https://leetcode.com/discuss/interview-question/358601/
```java
// "static void main" must be defined in a public class.
public class Main {
    
    private static int result(int[] income){
        int n = 0;
        for(int i = 0; i < income.length; i++){
            n = Math.max(income[i], n);
        }
        int[] bucket = new int[n + 1];
        for(int i = 0; i < income.length; i++){
            bucket[income[i]]++;
        }
       /* for(int i = 0 ; i < n; i++){
            System.out.print(bucket[i]);
        }
        System.out.println();*/
        int drop = 0;
        int left10 = 0; 
        int left60 = 0;
        int cum10 = 0;
        int cum60 = 0;
        for(int i = 0; i < n; i++){
            if(left10 > 0){
                cum10 -= bucket[left10];
            }
            if(left60 > 0){
                cum60 -= bucket[left60];
            }
            if(bucket[i] > 3){
                drop+= bucket[i] - 3;
            }
            cum10 += bucket[i];
            cum60 += bucket[i];
            if(cum10 > 20){
                drop += cum10 - 20;
            }
            if(cum60 > 60){
                drop += cum60 - 60;
            }
            left10 = Math.max(0, i - 9);
            left60 = Math.max(0, i - 59);
        }
        return drop;
    }
    public static void main(String[] args) {
       int[] test = new int[]{1,1,1,1,2,2,2,3,3,3,4,4,4,11,11,11,6,6,6,5,5,5};
           //new int[]{1,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,11,11,11,6,6,6,5,5,5};
        
        int res = result(test);
        System.out.println(res);
    }
}
```

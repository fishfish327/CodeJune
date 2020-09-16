import java.util.*;
public class Solution {
    public static int solution(int[] a, int[] b){
        Set<Integer> diffSet = new HashSet<>();
        if(a.length >= 2){
            diffSet.add(a[1] - a[0]);
        }
        if(a.length >= 1){
            int start = a[0];
            for(int i : b){
                diffSet.add(i - start);
                diffSet.add(start - i);
            }
        }
        int maxLen = -1;
        Set<Integer> set = new HashSet<>();
        for(int i : b){
            set.add(i);
        }
        for(int diff : diffSet){
           if(a.length > 0){
               
               int len = 0;
               int next = a[0] - diff;
               // 向前　expand;
               while(set.contains(next)){
                   len ++;
                   next -= diff;

               }
               int idx = 0;
               next = a[0];
               while(idx < a.length){
                   
                   if(a[idx] == next){
                       idx ++;
                       len ++;
                       next += diff;
                   } else if(a[idx] > next && set.contains(next)){
                        while(set.contains(next)){
                            len ++;
                            next += diff;
                        }
                   } else {
                       break;
                   }
               }
               
               if(idx == a.length){
                    // 继续 expand数组
                    while(set.contains(next)){
                        len ++;
                        next += diff;
                    }
                    maxLen = Math.max(len, maxLen);
               }
           }
        }
        
        return maxLen;
        
    }
   
    public static void main(String[] args){
           int[] a = {0,4,8,20};
           int[] b = {5,7,12,16,22};
           int[] c = {-2, 2,6,10,12,14,16,18,22};
           // expect 6
           System.out.println(solution(a, b));
           // expect 13
           System.out.println(solution(a, c));
    }
}
    

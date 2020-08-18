import java.util.*;
public class Solution {
    public static int solution(int[] a){
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        for(int num: a){
            String str = digitString(num);
            int cnt = map.getOrDefault(str, 0);
            res += cnt;
            map.put(str, cnt + 1);
            
        }
        
        return res;
    }

    public static String digitString(int a){
           StringBuilder sb = new StringBuilder();
           List<Character> list = new ArrayList<>();
           if(a == 0){
               list.add('0');
           }
           while(a > 0){
               int d = a %10;
               a /= 10;
               char curr = (char)('0' + d);
               list.add(curr);
           }
           Collections.sort(list);
           for(char c : list){
               sb.append(c);
           }

           return sb.toString();
        
    }
    public static void main(String[] args){
        System.out.println(solution(new int[]{25,35,872,228,53,278,872}));
    }

    
}
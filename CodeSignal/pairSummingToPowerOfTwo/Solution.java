import java.util.*;
public class Solution {
    public static int solution(int[] arr){
        
        int max = Integer.MIN_VALUE;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : arr){
            int freq = map.getOrDefault(i, 0);
            map.put(i, freq + 1);
            max = Math.max(i, max);
        }
        Set<Integer> set = new HashSet<>();
        for(int num : map.keySet()){
            int pow = 1;
            while(pow < 2 * max){
                int find = pow - num;
                if(map.containsKey(find) && !set.contains(find)){
                    res += (map.get(find) * map.get(num));
                    System.out.println(num + " " + find);
                }
                pow *= 2;
            }
            set.add(num);
        }

        return res;
    }

    public static void main(String[] args){
        System.out.println(solution(new int[]{1, -1, 2, 3}));
    }
}
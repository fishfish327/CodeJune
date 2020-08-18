import java.util.*;
public class Solution {
    public static int solution(int[] a){
        int res = 0;
        for(int i = 0; i < a.length - 1; i++){
            for(int j = i + 1; j < a.length; j++){
                if(digitAnagrams(a[i], a[j])){
                    res ++;
                }
            }
        }
        return res;
    }

    public static boolean digitAnagrams(int a, int b){

        if(a == b){
            return true;
        }
        // 找出　a 和 b中较小的一个
        int min = Math.min(a, b);
        if(a != min){
            b = a;
            a = min;
        }

        // 先算出最小数的digit, 若 digit 的数量不同直接返回false
        Set<Integer> sA = new HashSet<>();
        Set<Integer> sB = new HashSet<>();
        if(a == 0){
            sA.add(a);
        }
        while(a > 0){
            int d = a % 10;
            sA.add(d);
            a /= 10;
        }

        if(b == 0){
            sB.add(b);
        }
        while(b > 0){
            int d = b % 10;
            sB.add(d);
            if(sB.size() > sA.size()){
                return false;
            }
            b /= 10;
        }
        if(sA.size() != sB.size()){
            return false;
        }
        for(int d : sA){
            if(!sB.contains(d)){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args){
        System.out.println(solution(new int[]{25,35,872,228,53,278,872}));
    }

    
}
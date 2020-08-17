import java.util.*;
public class Solution {
    public static int solution(int n){
        int res = 0;
        for(int i = 0; i <= n; i++){
            res += countDigit(i, 0);
            res += countDigit(i, 2);
            res += countDigit(i, 4);
        }
        return res;
    }
    private static int countDigit(int num, int digit){
        int res = 0;
        if(num == 0 && num == digit){
            res ++;
        }
        while(num > 0){
            int d = num % 10;
            if(d == digit){
                res ++;
            }
            num /= 10;
        }
        return res;
    }

    public static void main(String[] args){
           System.out.println(solution(10));
           System.out.println(solution(22));
    }
}
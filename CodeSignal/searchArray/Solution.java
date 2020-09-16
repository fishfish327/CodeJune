import java.util.*;
public class Solution {
    public static int searchArray(int[] a, int[] b, int lower, int upper){
        for(int i = 0; i < a.length; i++){
            a[i] *= a[i];
        }
        Arrays.sort(a);
        for(int i = 0; i < b.length; i++){
            b[i] *= b[i];
        }
        Arrays.sort(b);
        
        int res = 0;
        for(int i = 0; i < a.length; i++){
            int idxL = Arrays.binarySearch(b, lower - a[i]);
            if(idxL < 0){
                idxL = -idxL - 1;
            }
            int idxU = Arrays.binarySearch(b, upper - a[i]);
            if(idxU < 0){
                idxU = -idxU - 2;
                // 
            }
            if(idxU < 0 || idxL < 0){
                continue;
            }
            System.out.println(idxL + " " + idxU);
            if(idxU < idxL){
                continue;
            }
            res += (idxU - idxL + 1);
        }
        return res;
    }
    public static void main(String[] args){
        int[] a = {1,2,3,-1,-2,-3};
        int[] b = {10};
        int lower = 200;
        int upper = 3e01;
        System.out.println(solution(a, b, lower, upper));
    }
}

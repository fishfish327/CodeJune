import java.util.*;
public class Solution {
    public static boolean solution(int[] a, int[] b, int[] c){
        Set<Integer> set = new HashSet<>();
        List<int[]> subArrList = new ArrayList<>();
        for(int i : c){
            set.add(i);
        }
        int start = 0, end = 0;
        int maxLen = 0;
        subArrList.add(new int[]{0,0});
        while(end < a.length){
            // 如果　a[end] 不在 arr c 里面, 移动指针start, 更新subArrList
            if(!set.contains(a[end])){
                
                int[] subArr = subArrList.get(0);
                
                if(end - start > subArr[1] - subArr[0]){
                    subArrList.clear();
                }
                if(end - start >= subArr[1] - subArr[0]){
                    subArrList.add(new int[]{start, end});
                } 
                start = end + 1;
                end ++;
            } else {
                end ++;
            }
            // 每次更新end　都更新maxLen
            maxLen = Math.max(end - start, maxLen);
            if(maxLen > b.length){
                break;
            }
        }
        if(end == a.length && set.contains(a[end-1])){
            if(end - start > maxLen){
                subArrList.clear();
                maxLen = end - start;
            }
            if(end - start == maxLen){
               subArrList.add(new int[]{start, end});
            }
        }
        if(maxLen != b.length){
            return false;
        }
        for(int[] subArr : subArrList){
            int lo = subArr[0];
            int i = 0;
            while(i < b.length){
                if(a[lo + i] == b[i]){
                    i++;
                } else {
                    break;
                }
            }
            if(i == b.length){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args){
        int[] a = {1,1,5,1,2,1};
        int[] b = {1,2};
        int[] c = {2,1};
        System.out.println(solution(a, b, c));
    }
}

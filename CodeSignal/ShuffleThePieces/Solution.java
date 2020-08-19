import java.util.*;
public class Solution {
    // 因为arr 里面的元素均为唯一的元素, 所以不用担心有多种解的可能
    public static boolean shuffleThePieces(int[] arr, int[][] pieces){
        // 检查 sum 是否相同

        int sum = 0;
        // 存储初始元素 -> pieces 的map
        Map<Integer, int[]> map = new HashMap<>();
        for(int[] p : pieces){
            sum += p.length;
            if(p.length == 0){
                continue;
            } else {
                map.put(p[0], p);
            }
        }
        if(sum != arr.length){
            return false;
        }

        // 遍历数组 时间复杂度o(n)
        int idx = 0;
        while(idx < arr.length){
            if(map.containsKey(arr[idx])){
                int[] p = map.get(arr[idx]);
                // 比较arr的片段和p
                int next = compareArr(arr, p, idx);
                if(next == -1){
                    break;
                } else {
                    idx = next;
                }
                
            } else {
                break;
            }
        }
        
        return idx == arr.length;

    }
    private static int compareArr(int[] arr, int[] p, int idx){
        int i;
        for(i = 0; i < p.length; i++){
            if(arr[idx + i] != p[i]){
               break;
            }
        }
        if(i == p.length){
            return idx + p.length;
        } else {
            return -1;
        }
    }

    public static void main(String[] args){
        System.out.println(shuffleThePieces(new int[]{1,2,5,3}, new int[][]{{5}, {1,2},{3}}));
        System.out.println(shuffleThePieces(new int[]{1,2,5,3,6}, new int[][]{{5}, {1,2},{6,3}}));
        System.out.println(shuffleThePieces(new int[]{1,5,4,3,2,8}, new int[][]{{1,5}, {4,3},{2}}));
    }
}
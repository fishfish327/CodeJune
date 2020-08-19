import java.util.*;
public class Solution {
    public static int[][] sortByBeauty(int[][] numbers, int size){
           
           int len = numbers.length;
           int numOfBlock = len / size;
           List<int[][]> list = new ArrayList<>((numOfBlock) * (numOfBlock));
           for(int i = 0; i < numOfBlock; i++){
               for(int j = 0; j < numOfBlock; j++){
                   int[][] subArr = new int[size][size];
                   copyArr(numbers, subArr, size, i, j);
               }
           }
           Comparator<int[][]> cmp = new Comparator<int[][]>(){
               @Override
               public int compare(int[][] a, int[][] b){
                   return getBe(a) - getBe(b);
               }
           };
           
    }
    private static void copyArr(int[][] numbers, int[][] subArr, int size, int row, int col){
        row *= size;
        col *= size;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                subArr[i][j] = numbers[row + i][col + i];
            }
        }
    }
    
    private static int getBe(int[][] num){
        int max = 0;
        Set<Integer> set = new HashSet<>();
        for(int[] arr: num){
            for(int i : arr){
                set.add(i);
                max = Math.max(i, max);
            }
        }
        
        
        for(int i = 1; i <= max; i++){
            if(!set.contains(i)){
                return i;
            }
        }
        return -1;
    }
}
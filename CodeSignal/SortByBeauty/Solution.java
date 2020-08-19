import java.util.*;
public class Solution {
    public static int[][] sortByBeauty(int[][] numbers, int size){
           
           int len = numbers.length;
           int numOfBlock = len / size;
           List<int[][]> list = new ArrayList<>((numOfBlock) * (numOfBlock));
           for(int i = 0; i < numOfBlock; i++){
               for(int j = 0; j < numOfBlock; j++){
                   int[][] subArr = new int[size][size];
                   copyArr(numbers, subArr, i, j);
               }
           }
           Comparator<int[][]> cmp = new Comparator<int[][]>(){
               @Override
               public int compare(int[][] a, int[][] b){
                   return getBe(a) - getBe(b);
               }
           };
           list.sort(cmp);
           for(int i = 0; i < list.size(); i++){
               int[][] subArr = list.get(i);
               copyBack(numbers, subArr, i, numOfBlock);
           }
           return numbers;
    }
    private static void copyArr(int[][] numbers, int[][] subArr, int row, int col){
        int size = subArr.length;
        row *= size;
        col *= size;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                subArr[i][j] = numbers[row + i][col + i];
            }
        }
    }

    private static void copyBack(int[][] numbers, int[][] subArr, int idx, int numOfBlock){
            int size = subArr.length;
            int row = idx / numOfBlock;
            int col = idx % numOfBlock;

            row *= size;
            col *= size;
            for(int i = 0; i < size; i++){
                for(int j = 0; j < size; j++){
                    numbers[row + i][col + i] = subArr[i][j];
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
    private static void printTest(int[][] numbers){
            for(int[] num : numbers){
                for(int i : num){
                    System.out.p
                }
                System.out.println();
            }
    }
    public static void main(String[] args){
        int[][] numbers = new int[][] {{1,2,2,3},{3,4,10,4},{2,10,1,2},{5,4,4,5}};
    }
}
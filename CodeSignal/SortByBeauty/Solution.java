import java.util.*;
/*
这个版本的解法取消了copyArr, 和copyBack两个方法
需要注意的问题: OA中不适合封装太多方法，注意代码的简洁度
*/
public class Solution {
    public static int[][] sortByBeauty(int[][] numbers, int size){
           
           int len = numbers.length;
           // 分割出的block数量
           int numOfBlock = (len / size) * (len / size);
           //           
           List<int[][]> list = new ArrayList<>(numOfBlock);
           for(int i = 0; i < numOfBlock; i++){
               list.add(new int[size][size]);
           }
           for(int i = 0; i < len; i++){
               for(int j = 0; j < len; j++){
                   // curr block idx : i / size * (len / size) + j / size
                   int idx = i / size * (len / size) + j / size;
                   int[][] subArr = list.get(idx);
                   // block 中对应元素的位置为　[i % size][j % size]
                   subArr[i % size][ j % size] = numbers[i][j];
               }
           }
           Comparator<int[][]> cmp = new Comparator<int[][]>(){
               @Override
               public int compare(int[][] a, int[][] b){
                   return getBe(a) - getBe(b);
               }
           };
           list.sort(cmp);
           for(int i = 0; i < len; i++){
            for(int j = 0; j < len; j++){
                // curr block idx : i / size * (len / size) + j / size
                int idx = i / size * (len / size) + j / size;
                int[][] subArr = list.get(idx);
                // block 中对应元素的位置为　[i % size][j % size]
                numbers[i][j] = subArr[i % size][ j % size];
            }
        }
           return numbers;
    }
    
    private static int getBe(int[][] num){
        int max = 0;
        int res = -1;
        Set<Integer> set = new HashSet<>();
        for(int[] arr: num){
            for(int i : arr){
                set.add(i);
                max = Math.max(i, max);
            }
        }
        res = max + 1;
        
        for(int i = 1; i <= max; i++){
            if(!set.contains(i)){
                res = i;
                break;
            }
        }
        System.out.println(res);
        return res;
    }
    private static void printTest(int[][] numbers){
            for(int[] num : numbers){
                for(int i : num){
                    System.out.print(i + " ");
                }
                System.out.println();
            }
    }
    public static void main(String[] args){
        /*
        1,2,2,3
        3,4,10,4
        2,10,1,2
        5,4,4,5
        */
        int[][] numbers = new int[][] {{1,2,2,3},{3,4,10,4},{2,10,1,2},{5,4,4,5}};
        numbers = sortByBeauty(numbers, 2);
        /* 期望的答案
        2 3 2 10 
        10 4 5 4 
        1 2 1 2 
        4 5 3 4 

        */
        printTest(numbers);
    }
}
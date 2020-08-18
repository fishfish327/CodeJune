import java.util.*;
public class Solution {
    
    public static int solution(int[] a, int[] b){
        int[] arr = new int[a.length + b.length];
        // 数组a 在新数组的index
        LinkedList<Integer> list = new LinkedList<>();
        int max = Integer.MIN_VALUE;

        // merge two sorted array
        int l1 = 0, l2 = 0;
        int idx = 0;
        while(l1 < a.length || l2 < b.length){
            int num1 = l1 < a.length ? a[l1] : Integer.MAX_VALUE;
            int num2 = l2 < b.length ? b[l2] : Integer.MAX_VALUE;
            if(num1 < num2){
                arr[idx] = a[l1 ++];
                list.add(idx); 
            } else {
                arr[idx] = b[l2 ++];
            }
            idx ++;
        }
        // key: a中元素的index, value : [公差, 当前位置等差数列的长度] 的list
        Map<Integer, List<int[]>> diffMap = new HashMap<>();
        if(a.length > 0){
            // 从idx = 1开始构建等差数列, 数据可插入 [0,1]区间内

            // [0,4] first diff -> 4, [0, 2,4] diff -> 2
            for(int i = 1; i < list.size(); i++){
                int element = list.get(i);
                int prev = list.get(i-1);
                List<int[]> tmpList = new ArrayList<>();
                //[0,1,2,3,4]
                if(!diffMap.containsKey(prev)){
                    int currDiff = arr[element] - arr[prev];
                    while(currDiff >= 0){
                        updateList(prev, element, currDiff--, 1, arr,tmpList);
                    }
                } else {
                    for(int[] pair : diffMap.get(prev)){
                        updateList(prev, element, pair[0], pair[1], arr,tmpList); 
                    }
                }
                

                if(tmpList.size() == 0){
                    return -1;
                }
                diffMap.put(element, tmpList);
                
            }
            // 向后延展等差数列
            int lastElement = list.getLast();
            //System.out.println(lastElement);
            List<int[]> allDiff = new ArrayList<>();
            for(int[] pair : diffMap.get(lastElement)){
                int diff = pair[0];
                int curr = arr[lastElement];
                for(int i = lastElement + 1; i < arr.length; i++){
                   if(arr[i] < curr + diff){
                       continue;
                   } else if(arr[i] == curr + diff){
                       curr = arr[i];
                       pair[1] ++;
                   } else {
                       break;
                   }
                }
                allDiff.add(pair);
            }
            // 向前延展等差数列
            int firstElement = list.getFirst();
            for(int[] pair : allDiff){
                int diff = pair[0];
                int curr = arr[firstElement];
                for(int i = firstElement - 1; i >= 0; i--){
                   if(arr[i] > curr - diff){
                       continue;
                   } else if(arr[i] == curr + diff){
                       curr = arr[i];
                       pair[1] ++;
                   } else {
                       break;
                   }
                }
            }

            // 记录max
            for(int[] pair: allDiff){
                max = Math.max(pair[1], max);
            }
        }
        if(max == Integer.MIN_VALUE){
            return -1;
        } else {
            return max;
        }
        
    }
    private static void updateList(int prev, int element, int currDiff, int count, int[] arr, List<int[]> list){
        int res;
        
        res = searchArr(arr, prev, element, currDiff);
        //System.out.println(currDiff + " " + res);
        if(res != -1){
            list.add(new int[]{currDiff, count + res});
        }
    }
    public static int searchArr(int[] arr, int lo, int hi, int diff){
        int curr = arr[hi];
        int res = 1;
        for(int i = hi -1; i > lo; i--){
            if(arr[i] < curr - diff){
                continue;
            } else if(arr[i] == curr - diff){
                curr = arr[i];
                res ++;
            } else {
                break;
            }
        }
        if(curr == arr[lo] + diff){
            return res;
        }
        return -1;
        
    }
   
    
    

    public static void main(String[] args){
           int[] a = {0,4,8,20};
           int[] b = {5,7,12,16,22};
           int[] c = {-2, 2,6,10,12,14,16,18,22};
           System.out.println(solution(a, b));
           System.out.println(solution(a, c));
    }
}
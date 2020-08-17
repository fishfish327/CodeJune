import java.util.*;
public class Solution {
    public static Set<Integer> filterSet = new HashSet<>();
    public static int solution(int[] a, int[] b){
        int[] arr = new int[a.length + b.length];
        LinkedList<Integer> list = new LinkedList<>();
        int max = Integer.MIN_VALUE;
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
        Map<Integer, List<int[]>> diffMap = new HashMap<>();
        if(a.length > 0){
            int start = list.get(0);
            List<int[]> pairList = new ArrayList<>();
            pairList.add(new int[]{0, 1});
            diffMap.put(start, pairList);
            for(int i = start - 1; i >= 0; i--){
                int diff = arr[start] - arr[i];
                int[] pair = {diff, 2};
                int curr = arr[i];
                for(int j = i - 1; i >=0; j--){
                    if(arr[j] < curr - diff){
                        continue;
                    } else if(arr[j] == curr - diff ){
                        curr = arr[j];
                        pair[1] ++;
                    } else {
                        break;
                    }
                }
                diffMap.get(start).add(pair);
            }
            for(int i = 1; i < list.size(); i++){
                int element = list.get(i);
                int prev = list.get(i-1);
        
                for(int[] pair : diffMap.get(prev)){
                    
                    List<int[]> tmpList = diffMap.getOrDefault(element, new ArrayList<>());
                    
                    if(pair[0] == 0 && pair[1] == 1){
                        int currDiff = arr[element] - arr[prev];
                        while(currDiff >= 0){
                            updateList(prev, element, currDiff--, pair[1], arr,tmpList);
                        }
                        
                    } else {
                        updateList(prev, element, pair[0], pair[1], arr,tmpList);
                    }
                    diffMap.put(element, tmpList);
                }
                if(diffMap.get(element).size() == 0){
                    return -1;
                }
                
            }
            // get Max
            int lastElement = list.getLast();
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
                max = Math.max(max, pair[1]);
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
        System.out.println(currDiff + " " + res);
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
           int[] c = {2,6,10,12,14,16,18};
          // System.out.println(solution(a, b));
           System.out.println(solution(a, c));
    }
}
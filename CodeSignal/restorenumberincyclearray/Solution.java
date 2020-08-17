import java.util.*;
public class Solution {
    public static int[] restoreNumber(int[][] pairs){
        if(pairs == null || pairs.length == 0){
            return null;
        }
        LinkedList<Integer> list = new LinkedList<>();
        Map<Integer,List<Integer>> map = new HashMap<>();
        for(int[] pair : pairs){
            List<Integer> l0 = map.getOrDefault(pair[0], new ArrayList<>());
            l0.add(pair[1]);
            map.put(pair[0], l0);
            List<Integer> l1 = map.getOrDefault(pair[1], new ArrayList<>());
            l1.add(pair[0]);
            map.put(pair[1], l1);
        }
        int start = pairs[0][0];
        list.add(start);
        while(list.size() < pairs.length){
            int curr = list.getFirst();
            List<Integer> l = map.get(curr);
            if(l.size() == 2){
                list.addFirst(l.get(0));
                list.addLast(l.get(1));
            } else {
                list.addFirst(l.get(0));
            }
            for(int num : l){
                List<Integer> neibor = map.get(num);
                int idx = neibor.indexOf(curr);
                neibor.remove(idx);
            }
        }
        int[] res = new int[pairs.length];
        for(int i = 0; i < res.length; i++){
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args){
        int[][] input = {{3,5},{1,4},{2,4},{1,5},{2,3}};
        int[] res = restoreNumber(input);
        for(int i : res){
            System.out.println(i);
        }
    }
}
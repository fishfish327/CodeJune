import java.util.Comparator;

public class Test {
    List<int[]> list = new ArrayList<>();
    for(int k: map.keySet()){
        list.add(new int[]{k, map.get(k)});
    }
    Comparator<int[]> cmp = new Comparator<int[]>(){
        @Override
        public int compare(int[] a, int[] b){
            if(a[1] != b[1]){
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        }
    };
    list.sort(cmp);
    
}
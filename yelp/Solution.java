import java.util.*;
import java.util.stream.*;
class Business {
   int id;
   float rating;
   boolean vegan_friendly;
   int price;
   float distance;
}

class Filter {
   boolean only_vegan_friendly;
   int max_price = -1;
   float max_distance = -1f;

}
public class Solution {
    public List<Integer> solution(List<Business> input, Filter filter) {
           
           input = input.stream().filter(b -> check(b, filter)).collect(Collectors.toList());

           input.sort((a, b) -> Float.compare(b.rating, a.rating));
           List<Integer> res = new ArrayList<>();
           input.forEach(b -> res.add(b.id));
           return res;
    }
    private boolean check(Business b, Filter filter){
               if(filter.only_vegan_friendly == true && b.vegan_friendly == false){
                       return false;
               }
               if(filter.max_price != -1 && b.price > filter.max_price){
                       return false;
               }
               if(filter.max_distance != -1f && b.price > filter.max_distance){
                       return false;
               }
               return true;

    }
}

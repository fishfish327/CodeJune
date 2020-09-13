用了一个stream filter, 这个部分不是很熟悉其实

```java
// "static void main" must be defined in a public class.

class Business {
   int id;
   float rating;
   boolean vegan_friendly;
   int price;
   float distance;
    public Business(int id, float rating, boolean vegan_friendly, int price, float distance){
        this.id = id;
        this.rating = rating;
        this.vegan_friendly = vegan_friendly;
        this.price = price;
        this.distance = distance;
    }
}

class Filter {
       boolean only_vegan_friendly;
       int max_price = -1;
       float max_distance = -1f;
    public Filter(boolean only_vegan_friendly,int max_price ,float max_distance){
       only_vegan_friendly = this.only_vegan_friendly;
       max_price = this.max_price;
       max_distance = this.max_distance;
    }
}
public class Solution {
    public static List<Integer> solution(List<Business> input, Filter filter) {
           List<Integer> res = new ArrayList<>();
           input = input.stream().filter(b -> check(b, filter)).collect(Collectors.toList());
            input.sort((a, b) -> Float.compare(b.rating, a.rating));
          // input.sort((a, b) -> (b.rating - a.rating));
           //List<Integer> res = new ArrayList<>();
           input.forEach(b -> res.add(b.id));
           return res;
    }
    private static boolean check(Business b, Filter filter){
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


    public static void main(String[] args) {
        
        List<Business> par = new ArrayList<>();
        par.add(new Business( 1,  4.0f,  true,  4, 10.0f));
        par.add(new Business( 2, 3.5f, false, 2, 5.0f));
        par.add(new Business( 3, 4.5f, false, 1,1.0f));
        par.add(new Business( 4, 3.0f, true, 2,  3.4f));
        par.add(new Business(  5, 4.5f, true, 1, 6.3f));
        par.add(new Business(  6, 3.5f, true,  2,  1.2f));

        Filter filter = new Filter( true, 2, 7.0f);   
        List<Integer> res = solution(par, filter);
        System.out.println(res);
    }
}
```

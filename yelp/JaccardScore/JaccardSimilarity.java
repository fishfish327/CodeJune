import java.util.*;

class Restaurant{
    String name;
    int rate;

    public Restaurant(String name, int rate){
        this.name = name;
        this.rate = rate;
    }
}
public class JaccardSimilarity {
    public static float calculate(String origin, String input){
        Set<Character> originSet = new HashSet<>();
        Set<Character> inputSet = new HashSet<>();
        for(char c : origin.toCharArray()){
            originSet.add(c);
        }
        for(char c : input.toCharArray()){
            inputSet.add(c);
        }
        int len = originSet.size();
        originSet.retainAll(inputSet);
        float res = (float)originSet.size() / (float)len;
        return res;
    }

    public static Restaurant getRes(List<Restaurant> list, Restaurant origin){
        if(list.size() == 0){
            return null;
        }
        Comparator<Restaurant> cmp = new Comparator<>(){
            @Override
            public int compare(Restaurant a, Restaurant b){
                float scoreA = calculate(origin.name, a.name);
                float scoreB = calculate(origin.name, b.name);
                return Float.compare(scoreB, scoreA);
            }
        };
        list.sort(cmp);
        return list.get(0);

    }
    public static void main(String[] args){
        Restaurant[] inputArr = {
             new Restaurant("aab", 100),
             new Restaurant("acn", 90),
             new Restaurant("kki", 200)
        };
        List<Restaurant> list = new ArrayList<>(Arrays.asList(inputArr));
        Restaurant origin = new Restaurant("aaf", 100);
        System.out.println(getRes(list, origin).name);

        
    }
}
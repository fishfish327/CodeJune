记录的问题主要是hashmap不需要重写两个arraylist是否相等，就跟string 一样
https://leetcode.com/discuss/interview-question/354051/yelp-oa-2019-unique-meals-distance-to-other-businesses





map1 是hashset, map 是list 两个的结果都是3， 说明不用重写equals和hashcode
```java
// "static void main" must be defined in a public class.
class Pair{
        String name;
        List<String> food;
        public Pair(String name, String[] foods){
            this.name = name;
            this.food = Arrays.asList(foods);
            
        }

    }
public class Main {
    
    public static int func(List<Pair> menu){
        Map<List<String>, List<String>> map = new HashMap<>();
        Map<Set<String>, List<String>> map1 = new HashMap<>();
        for(Pair t : menu){
            List<String> part = t.food;
            Set<String> set = new HashSet<>(t.food);
            Collections.sort(part);
            if(map.containsKey(part)){
                map.get(part).add(t.name);
                map1.get(set).add(t.name);
            }else{
                map.put(part, new ArrayList<>());
                map1.put(set, new ArrayList<>());
            }
        }
        
        System.out.println(map1.size());
        return map.size() ;
    }
    public static void main(String[] args) {
        List<Pair> test = new ArrayList<>();
       
        test.add(new Pair("Meal 1", new String[]{"A", "B", "C"}));
        test.add(new Pair("Meal 2", new String[]{"C", "D","A","B"}));
        test.add(new Pair("Meal 3", new String[]{"C", "A", "B"}));
        test.add(new Pair("Meal 4", new String[]{"A", "D", "B"}));

        
        System.out.println(func(test));
    }
}
```



目前遇到的问题：
1.list.add("sad Burger's"); 不知道怎么用trie保存标点符号 / trie 改成character map?
2.因为每个单词都可能会因为包含在很多个string里，所以trie我想成用list<String> 来保存数据 但如果这样不是一个一个加的话不太清楚怎么用
3.

```java
// "static void main" must be defined in a public class.
public class Main {
    class Trie{
        Trie[] next;
        List<String> KeyWrd;
        public Trie(){
            next = new Trie[26];
            List<String> keyWrd = new ArrayList<>();
            
        }
    }
    public static List<String> prefixMat(List<String> input, String s){
        Trie root = new Trie();
        for(String in : input){
            
            String[] sept = in.split(" ");
            for(String cur : sept){
                Trie r = root;
                for(int i = 0; i < cur.length(); i++){
                    int now = cur.charAt(i) - 'a';
                    if(i == cur.length() - 1){
                            r.keyWrd.add(in);
                        }
                    if(r.next[now] == null){
                        r.next[now] = new Trie();
                        r = r.next[now];
                        
                    }
                    
                }
            }
        }
        Set<String> res = new HashSet<>();
        Trie r = root;
        for(int i = 0; i < s.length(); i++){
            int now = s.charAt(i) - 'a';
            if(r.next[now] == null){
                
            }
            
        }
        
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Burger King");
        list.add("kdk dnsd Burgers");
        list.add("sad Burger's");
        list.add("asdd das a");
        list.add("Walburgers");
        
    }
}
```

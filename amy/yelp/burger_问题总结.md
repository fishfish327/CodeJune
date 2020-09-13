目前遇到的问题：
1.list.add("sad Burger's"); 不知道怎么用trie保存标点符号 / trie 改成character map?



2.因为每个单词都可能会因为包含在很多个string里，所以trie我想成用list<String> 来保存数据 但如果这样不是一个一个加的话不太清楚怎么用
  
  
3.不确定r = r.next[now];和
                        if(i == cur.length() - 1){
                            r.keyWrd.add(in);
                        }
    是a在b前面还是b在a前面
    

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
我的for循环逻辑很薄弱， 经常在里面判断ifelse 可能是把递归和这个东西搞混了，喜欢在for循环里做if(i==s.length - 1）这种奇奇怪怪的操作 但显得很多余 一般来说在for循环结束的时候不用check i== s.length - 1 直接做会更好更简洁安全方便
还有就是checknull 做的不好， 一般来说checknull 之后只是用来新建，上面这版代码写完就出现了只有一个burger king 的部分
因为后面的因为不是null 就被忽略了(详情参见41行)















更新后的最终版本
```java
// "static void main" must be defined in a public class.
public class Main {
   static class Trie{
        Trie[] next;
        List<String> keyWrd;
        public Trie(){
            next = new Trie[26];
            keyWrd = new ArrayList<>();
            
        }
    }
    public static List<String> prefixMat(List<String> input, String s){
        Trie root = new Trie();
        for(String in : input){
            String inRep = in.toLowerCase().replaceAll("\\pP","");
            String[] sept = inRep.split(" ");
            for(String cur : sept){
                Trie r = root;
                for(char ch : cur.toCharArray()){ 
                    int now = ch - 'a';
                    if(r.next[now] == null){
                        r.next[now] = new Trie(); 
                    }
                    r = r.next[now];    
                }
                r.keyWrd.add(in);
            }
        }
        
        Set<String> set = new HashSet<>();
        Trie r = root;
        for(int i = 0; i < s.length(); i++){
            int now = s.charAt(i) - 'a';
            if(r.next[now] == null){
                break;
            }
            r = r.next[now];   
        }
        
        Queue<Trie> q = new LinkedList<>();
        q.offer(r);
        while(!q.isEmpty()){
            Trie cur = q.poll();
            set.addAll(cur.keyWrd);
            for(Trie tr : cur.next){
                if(tr != null){
                    q.offer(tr);
                }
            }
        }
        
        List<String> result = new ArrayList<>(set);
        System.out.print(result);
        return result;
        
    }
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("Burger King");
        list.add("kdk dnsd Burger");
        list.add("sad Burger's");
        list.add("asdd das a");
        list.add("Walburgers");
        prefixMat(list, "bur");
    }
}
```


我一开始的顾虑还有并没有准备初始化 keyWrd（尽管自己的代码草稿里有初始化） 因为很怕后续bfs的时候加很多空的arraylist 但事实上无需担忧 因为如果这个arraylist 是空的那加多加少无所谓的嘛
总之代码习惯里喜欢加没有意义的if else 需要尽量避免


时空复杂度O(List<String>.size() * avg(string.size())) <- 建树
找string O(str.length() + avg(String.size())) ?
                                                    
follow up 除了kmp和string 有没有别的办法？

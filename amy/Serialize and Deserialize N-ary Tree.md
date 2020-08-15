记录一下自己之前的小错误
```java
class Codec {
    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        dfs(root,sb);
        System.out.println(sb.toString());
        return sb.toString();
        
        
    }
    private StringBuilder dfs(Node root, StringBuilder sb){
        //if(root == null) sb.append("null");//
        //不知道为什么 如果一开始进来的root 是null 直接return如下
        //if(root == null) return sb;就不会报错 而不return的就会报错
        if(root == null) return sb;
        
        else {
            sb.append(root.val);
            sb.append(",");
            for(Node child : root.children){
                dfs(child, sb);  
                sb.append("next").append(",");
            }  
        }
        
        return sb;
        
        
    }
	
    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if(data.length() == 0){
            return null;
        }
        /*跟上面的部分相对应*/
        String[] col = data.split(",");
       /* if(col.length==0 || col[0] == "null"){
            return null;
        }*/ 
        ```
       如果一开始return null 就相对来说不容易报错
        ```java
        Node root = new Node(Integer.parseInt(col[0]), new ArrayList<>());
        sdf(col,root, 1);
            
        return root;
    }
    private int sdf(String[] col,Node root, int loc){
      /*  if( col[loc].equals("null")||loc == col.length - 1 || col[loc].equals("next") ){
            return loc+ 1;
        }
        if(root.children == null){
            root.children = new ArrayList<>();   
        }*/这部分会导致一个root node 的出现空指针异常，类似https://leetcode.com/submissions/detail/381176668/
        
        //loc++;
        while(loc < col.length && !(col[loc].equals("next")|| col[loc].equals("null"))){
            root.children.add(new Node(Integer.parseInt(col[loc]), new ArrayList<>()));
            loc = sdf(col, root.children.get(root.children.size() - 1), loc+ 1);
            System.out.println(loc);
        }
        
        return loc + 1;
        
    }
}
```

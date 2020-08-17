import java.util.*;
class Node {
    int val;
    Node prev;
    Node next;
    public Node(int val){
        this.val = val;
    }
}
class MyList {
    Node head;
    Node tail;
    Map<Integer, Node> map;

    public MyList(){
        head = new Node(0);
        map = new HashMap<>();
        map.put(0, head);
        
    }
    public void append(int val){
        Node node = new Node(val);
        if(tail != null){
            node.prev = tail;
            tail.next = node;
             
        } else {
            head.next = node;
            node.prev = head;
        }
        tail = node;
        map.put(val, node);
    }
    public int peek(){
        Node first = head.next;
        if(first == null){
            return 0;
        } else {
            return first.val;
        }
    }

    public void delete(int val){
        Node curr = map.get(val);
        Node prevNode = curr.prev;
        Node nextNode = curr.next;
        if(prevNode != null){
            prevNode.next = nextNode;
        }
        if(nextNode != null){
            nextNode.prev = prevNode;
        }
        curr.prev = null;
        curr.next = null;
    }
}
public class Solution {
    static List<Integer>  solution(int n, int m, int[][] query){
        List<Integer> res = new ArrayList<>();
        MyList rows = new MyList();
        MyList cols = new MyList();
        for(int i = 1; i < n + 1; i++){
            rows.append(i);
        }
        for(int j = 1; j < m + 1; j++){
            cols.append(j);
        }
        for(int[] list : query){
            int type = list[0];
            if(type == 0){
               res.add(rows.peek() * cols.peek());
            } else if(type == 1){
               rows.delete(list[1]);
            } else {
               cols.delete(list[1]);
            }
        }
        return res;
    }
    
    public static void main(String[] args){
           int[][] query = new int[][] {{0}, {1, 2}, {0}, {2,1},{0},{1,1},{0}};
           List<Integer> res = solution(3, 4, query);
           for(int i : res){
               System.out.print(i + " ");
           }
    }
}
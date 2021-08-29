// "static void main" must be defined in a public class.
public class WaitList {
    class Party{

        int size;
        String name;
        Timestamp ts;
        public Party(int size, String name, Timestamp ts){
            
            this.size = size;
            this.name = name;
            this.ts = ts;
        }
    }
   /* class Node{
        Node prev;
        Node next;
        int size;
        public Node(int size){
            this.prev = null;
            this.next = null;
            this.size = size;
        }
    } */
   // Node head, tail

//follow up 是 解决饭馆只想4人桌坐4个人，队里即使只有2人的party也不给安排。 暂时也想不出更多更好的解决办法了
    //table 类 如果size 比xx大或者啥
    public WaitList(){
        
        /*
        head = new Node(-1);
        tail = new Node(-1);
        tail.prev = head;
        head.next = tail;
        */
        
    }
    
    public static addToWL(Party p){
        Node preSave = tail.prev;
        Node newNode = new Node(p.size);
        preSave.next = newNode;
        newNode.prev = preSave;
        newNode.next = tail;
        tail.prev = newNode;
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

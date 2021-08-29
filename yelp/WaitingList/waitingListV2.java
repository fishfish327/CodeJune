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
   


    ArrayDeque<Party> waitlist;
    Stack<Party> stackParty;
    public WaitList(){
        waitlist = new Arraydeque<>();
        stackParty = new Stack<>();
  
    }
    
    public static addToWL(Party p){
        waitlist.addLast(p);
        
        
    }
    public static setToTargetTable(Table t){
        while(waitlist.size() != 0){
            if(t.size >= waitlist.peekFirst().size()){
                t.set(waitlist.pollFirst());
                break;
            }
            stackParty.push(waitlist.pollFirst());
        }
        while(!stack.isEmpty()){
            waitlist.add(stack.pop());
        }//o N 时间复杂度，但确保每个桌是按顺序的
    }
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}

import java.util.*;

class People {
        String name;
        String team;
        public People(String name, String team){
            this.name = name;
            this.team = team;
        }
}
public class ShuffleUserSolution{
        public static List<String[]> shuffleUser(List<People> peoples, List<String[]> prev){
	       Map<String, Set<String>> historyMap;
	       List<String[]> res = new ArrayList<>();
	       peoples.forEach(p -> historyMap.put(p.name, new HashSet<>()));

	       for(String[] pair : prev){

	           if(historyMap.containsKey(pair[0])){
		      historyMap.get(pair[0]).add(pair[1]);
		   }

		   if(historyMap.containsKey(pair[1])){
                      historyMap.get(pair[1]).add(pair[0]);
                   }

	       }
	       Queue<People> queue = new LinkedList<>();
	       peoples.forEach(p -> queue.add(p));

	       String first = queue.poll();
	       while(){
		   String
	           if(history.get(first).contains(queue.peek()))
	       }
	
	}

}

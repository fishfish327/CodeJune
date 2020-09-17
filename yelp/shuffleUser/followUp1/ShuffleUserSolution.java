import java.util.*;

class People {
	String name;
	String team;

	public People(String name, String team) {
		this.name = name;
		this.team = team;
	}
}

public class ShuffleUserSolution {
	public static List<String[]> shuffleUser(List<People> peoples, List<String[]> prev) {
		if (peoples.size() == 0) {
			return new ArrayList<>();
		}
		Collections.shuffle(peoples);
		Map<String, Set<String>> historyMap = new HashMap<>();
		List<String[]> res = new ArrayList<>();
		peoples.forEach(p -> historyMap.put(p.name, new HashSet<>()));

		for (String[] pair : prev) {

			if (historyMap.containsKey(pair[0])) {
				historyMap.get(pair[0]).add(pair[1]);
			}

			if (historyMap.containsKey(pair[1])) {
				historyMap.get(pair[1]).add(pair[0]);
			}

		}
		Queue<String> queue = new LinkedList<>();
		peoples.forEach(p -> queue.add(p.name));

		String first = queue.poll();
		while (!queue.isEmpty()) {
			Set<String> firstHistory = historyMap.get(first);
			// check if we can match first

			if(firstHistory.containsAll(queue)){
				res.add(new String[]{first, ""});
				first = queue.poll();
				continue;
			}
			String second = queue.peek();
			if (!firstHistory.contains(second)) {
				res.add(new String[] { first, second });
				//remove second string
				queue.poll();
			} else{
				queue.add(first);
			}

			if(queue.size() == 1){
				res.add(new String[]{queue.poll(), ""});
			} else if(queue.size() > 1) {
                first = queue.poll();
			}
            
		

		}

		return res;

	}

}

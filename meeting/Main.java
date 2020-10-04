// "static void main" must be defined in a public class.
class Meeting {
      public int day;
      public int hour;
      public int minute;

      public int hourDuration;
      public int minuteDuration;
      

      public Meeting(int day, int hour, int minute, int hourDuration, int minuteDuration){
             this.day = day;
	     this.hour = hour;
	     this.minute = minute;

	     this.hourDuration = hourDuration;
	     this.minuteDuration = minuteDuration;
      }
}
public class Main {

    public static Map<String,Integer> weekDay = new HashMap<>();
    
    
	public static int findLongestBreak(List<Meeting> meetings){
	if(meetings.size() == 0){
	   return 0;
	}
	Comparator<Meeting> cmp = new Comparator<>(){
		@Override	
		public int compare(Meeting a, Meeting b){
			if(a.day == b.day) {
			   if(a.hour == b.hour) {
			      return a.minute - b.minute;
			   } else {
			      return a.hour - b.hour;
			   }
			} else {
			   return a.day - b.day;
			}
		}	
		};

    meetings.sort(cmp);

	Meeting prev = meetings.get(0);

	int max = 0;
	for(int i = 1; i < meetings.size(); i++){
	        Meeting curr = meetings.get(i);
		if(prev.day == curr.day){
		       int diff = 0;
		       diff += curr.minute - (prev.minute + prev.minuteDuration);
		       diff += (curr.hour - prev.hour - prev.hourDuration) * 60;
               diff += (curr.day -prev.day) * 24;
		       max = Math.max(diff, max);
		}
		prev = curr;
		
	}

	return max;

	}

    public static List<Meeting> solution(String s){
         String[] weekday = new String[]{"monday","tuesday", "wednesday", "thursday", "friday", "saturday", "sunday"};
         List<Meeting> list = new ArrayList<>();
         for(int j = 0; j < weekday.length; j++){
            weekDay.put(weekday[j], j + 1);
         }
    
        String[] sarray = s.split(" ");
        for(int i = 0; i < sarray.length/2; i++){
            String t = sarray[2 * i].toLowerCase();
            String[] par = sarray[2 *i + 1].split(":");
            Meeting m = new Meeting(weekDay.get(t),Integer.parseInt(par[0]),Integer.parseInt(par[1]), Integer.parseInt(par[0]) + 1, Integer.parseInt(par[1]) + 1);
            
            
            list.add(m);
             
        }
        return list;
    }
    public static void main(String[] args) {
        System.out.println(solution("monday 12:00 tuesday 13:00 Thursday 12:00"));
    }
}

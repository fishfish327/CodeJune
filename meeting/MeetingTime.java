import java.util.*;
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
public class MeetingTime {
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
		       max = Math.max(diff, max);
		}
		prev = curr;
		
	}

	return max;

	}


}

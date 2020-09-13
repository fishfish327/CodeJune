// "static void main" must be defined in a public class.
import java.util.*;
public class Main {
    public static int func(int year){
        return getSecondSundayOfMonth(year, 10);
    }
    public static int getSecondSundayOfMonth(int year, int month)
	{
		Calendar cal = Calendar.getInstance();
     //   System.out.println("first time" + cal);
		cal.set(Calendar.YEAR, year);
       // System.out.println("second Time" + cal);
		cal.set(Calendar.MONTH, month - 1);
       // System.out.println("Third time" + cal);
		cal.set(Calendar.DATE, 1);
       // System.out.println("fourth time " + cal);// 设为第一天
 
		while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
		{
			cal.add(Calendar.DATE, 1);
		}
        Date date = cal.getTime();
        System.out.println(date);
        // output : "Sun Oct 04 02:19:17 UTC 2020"
        int x = cal.getTime().toString().indexOf("Oct");
        
        int now = Integer.valueOf(cal.getTime().toString().substring(x + 4,x + 6)) + 2;
        // Output :"4" from "04"
		return x;
	}
    public static void main(String[] args) {
        
        System.out.println(func(2020));
    }
}

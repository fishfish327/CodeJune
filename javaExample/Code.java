import java.util.*;
interface People {
    public List<String> getUrl(String input);
}
interface Book {
  public List<Integer> getNumber(int input);
}
class PeopleImpl implements People, Book{
    @Override
    public List<String> getUrl(String input){
    	  List<String> res = new ArrayList<>();
        for(int i = 0; i < 3; i ++){
          res.add(input);
        }
      return res;
  }
  @Override
  public List<Integer> getNumber(int input){
    List<Integer> list = new ArrayList<>();
    list.add(input);
    list.add(input);
    return list;
  }
  
}
public class Code {
  
  public static void main(String[] args) {
    PeopleImpl p = new PeopleImpl();
    List<String> list = p.getUrl("hello from java");
    for(String str: list){
      System.out.println(str);
    }
    List<Integer> nums = p.getNumber(1);
    for(Integer n: nums){
      System.out.println(n);
    }
    
  }
}

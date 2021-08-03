package code.august;
import java.util.*;
public class Licenseplate {
    enum State {
        CA,
        NY,
        NJ,
        WA,
        PA
    }
    String name;
    int number;
    HashSet<String> stateName ;
    public Licenseplate(String input) {
        stateName= new HashSet<>();
        for(State s : State.values()){
            stateName.add(s.toString());
        }
        if(valid(input)){
            this.name = input;
        }
    }
    public boolean valid(String input){
        if(input.length() <= 0 || input.length() > 8) return false;
        if(stateName.contains(input.substring(0,2))){
            for(int i = 2; i < input.length(); i++){
                if(!Character.isDigit(input.charAt(i))) return false;
            }
            return true;
        } else {
            return false;
        }
    }

    static class LicenseplateIterator implements Iterator<Licenseplate> {
        List<Licenseplate> list = new ArrayList<>();
        Iterator<Licenseplate> it;


        public LicenseplateIterator(Iterator<String> iterator) {
            while(iterator.hasNext()){
                Licenseplate l = new Licenseplate(iterator.next());
                if(l.name != null) {
                    list.add(l);
                }
            }
            it = list.iterator();
        }


        public boolean hasNext() {
            return it.hasNext();
        }

        public Licenseplate next() {
            return it.next();
        }

    }

    public static void main(String[] args){
        List<String> strs = Arrays.asList(new String[]{"CA0001", "NY0009", "PP0009"});
        Iterator<String> stringIterator = strs.iterator();
        LicenseplateIterator licenseplateIterator = new LicenseplateIterator(stringIterator);
        while(licenseplateIterator.hasNext()){
            Licenseplate l = licenseplateIterator.next();
            System.out.println(l.name);
        }
    }
}

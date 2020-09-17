import java.util.Arrays;

public class KMPSolution {
    public static int[] getAux(String s) {
        int[] aux = new int[s.length()];
        int i = 1;
        int m = 0;
        while (i < aux.length) {
            if (s.charAt(i) == s.charAt(m)) {
                m++;
            } else if (s.charAt(i) != s.charAt(m) && m != 0) {
                m = aux[m - 1];
            } else if (m == 0) {
                aux[i] = 0;
                i++;
            }
        }

        return aux;
    }

    public static String mergeString(String a, String b) {
        int[] aux = getAux(b);
        int len = b.length();
        // the idx a should start to match b
        // "aabbc" "bbc", we should start at idx : 2
        int i = Math.max(a.length() - len, 0);
        int j = 0;
        while (i < a.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = aux[j - 1];
            }
        }
        String res = a + b.substring(j);
        return res;

    }

    public static void main(String[] args) {
        System.out.println(mergeString("abc", "bcde"));//abc + de
        System.out.println(mergeString("abc", "cde"));// abc + de
        System.out.println(mergeString("abc", "bc"));// abc
        
        
    }
}
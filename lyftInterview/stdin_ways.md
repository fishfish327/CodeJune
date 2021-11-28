general source:
https://rosettacode.org/wiki/Input_loop#Java

https://rosettacode.org/wiki/Hello_world/Text#Java

hackerrank上有相当一部分resource ： https://www.hackerrank.com/challenges/find-the-nearest-clone/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
如果是数字参杂着string，比如lru那种题目

可以
int capacity = Integer.valueOf(in.next().trim());
int inputSize = Integer.valueOf(in.next().trim());
String[]操作输入
int[][]操作格式

int i = 0; i < inputsize; i++
  操作输入[i] = in.nextLine();
  int[] next = null;
  if(操作输入[i] == "get") next = new int[1] (key值）
  else if（操作输入[i] == "set") next = new int[2] （key value 对）
  else if( 操作输入[i] == “rollback") next = new int[0]
  int j = 0;
  while(j < next.length) 一顿操作
  最后操作格式[i] = next 
  


##a copy for rosettacode

```{java}

import java.io.InputStream;
import java.util.Scanner;
 
public class InputLoop {
    public static void main(String args[]) {
        // To read from stdin:
        InputStream source = System.in;
 
        /*
        Or, to read from a file:
        InputStream source = new FileInputStream(filename);
 
        Or, to read from a network stream:
        InputStream source = socket.getInputStream();
        */
 
        Scanner in = new Scanner(source);
        while(in.hasNext()){
            String input = in.next(); // Use in.nextLine() for line-by-line reading
 
            // Process the input here. For example, you could print it out:
            System.out.println(input);
        }
    }
}
##########


public class InputLoop {
    public static void main(String args[]) {
        // To read from stdin:
        Reader reader = new InputStreamReader(System.in);
 
        /*
        Or, to read from a file:
        Reader reader = new FileReader(filename);
 
        Or, to read from a network stream:
        Reader reader = new InputStreamReader(socket.getInputStream());
        */
 
        try {
            BufferedReader inp = new BufferedReader(reader);
            while(inp.ready()) {
                int input = inp.read(); // Use in.readLine() for line-by-line
 
                // Process the input here. For example, you can print it out.
                System.out.println(input);
            }
        }  catch (IOException e) {
            // There was an input error.
        }
    }
}


```
##few code 

针对function（String input)

```Java

 public static void main(String[] args) throws IOException {// may useful through IOException
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = Integer.parseInt(bufferedReader.readLine().trim());
        #这个格外有用，因为如果bufferreader.read的是一个integer 那接下来对标string的readline 很容易出错 
        for (int qItr = 0; qItr < q; qItr++) {
            String s = bufferedReader.readLine();
            
            int result = Result.alternatingCharacters(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
```
bufferWriter 可能后续有用

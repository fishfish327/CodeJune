general source:
https://rosettacode.org/wiki/Input_loop#Java

https://rosettacode.org/wiki/Hello_world/Text#Java

hackerrank上有相当一部分resource ： https://www.hackerrank.com/challenges/find-the-nearest-clone/problem?isFullScreen=true&h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=graphs
如果是数字参杂着string，比如lru那种题目

可以
```java
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
  
```

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
!bufferWriter
https://codeleading.com/article/22714058991/

```java
/*  
字符流的缓冲区：缓冲区是为了提高读或写的效率
                只具备提高效率的功能，不具备读或者写的功能
                所以使用缓冲流必须结合着读流或写流

BufferdReader：只具备提高效率的功能，不具备读的功能
BufferdWriter：只具备提高效率的功能，不具备写的功能----newLine()跨平台写入换行
*/
import java.io.*;
class test{
    public static void main(String[] args)throws IOException{
        //使用缓冲写入流实现文件的写入

        //1、因为BufferdWriter不具备写入的功能，所以需要先创建字符写入流对象和文件相关联
        FileWriter fw = new FileWriter("tt.txt");

        //2、为了提高写入的效率，使用缓冲写入流
        //把需要被提高效率的字符输出流对象作为参数传递给BufferWriter的构造方法
        BufferedWriter bw = new BufferedWriter(fw);

        //3、使用BufferWriter对象实现写入数据
        for(int i=1;i<=3;i++){
            bw.write("abc"+i);
            bw.newLine();//写入换行---跨平台的
            bw.flush();//或者可以在最后关闭流
        }

        //关闭流
        bw.close();//关闭缓冲流，其实就是关闭字符输出流
    }
}

//BufferedReader:只具备提高效率的功能，不具备读的功能
//提供了一个一次读一行的功能readLine()
import java.io.*;
class test{
    public static void main(String[] args)throws IOException{
        //1、创建文件读取流
        FileReader fr = new FileReader("tt.txt");

        //2、为了提高读取的效率，使用BufferedReader
        //把需要被提高效率的座位参数传递给BufferedReader的构造方法
        BufferedReader br = new BufferedReader(fr);

        //BufferedReader有一个一次读一行的功能
        String line = null;

        /*line = br.readLine();//返回的一行不包含最后的换行符
        System.out.println(line);

        line = br.readLine();
        System.out.println(line);

        line = br.readLine();
        System.out.println(line);*/

        //改为循环
        while((line = br.readLine())!=null){
            System.out.println(line);
        }

        br.close();//实际上关闭的是fr
    }
}

```

结果很方便就能在project 列表里找到，一般在跟src一层，左上角能看到

- follow-up 1
 /* 接下來follow-up是， 給定一定間隔，產生的 pair 不能在之前出現的paris中出現過

- follow-up 2

现在每个人都有一个组，相同的组不能互相配对。
例如：[{id: 'Alan', team: 'a'}, {id: 'Da', team: 'a'}, {id: 'Jen', team: 'c'}, {id: 'Kevin', team: 'd'}, {id: 'Neha', team: 'm'}, {id: 'Rachel', team: 'q'}, {id: 'Tom', team: 'a'}]
那么 {id: 'Alan', team: 'a'} 不能和 {id: 'Da', team: 'a'} 配对，因为同一组。
输入： [{id: 'Alan', team: 'a'}, {id: 'Da', team: 'a'}]
输出：  [{id: 'Alan', team: 'a'}, None] 和 [{id: 'Da', team: 'a'}, None]. 因为输入2个是同一组，所以都不能找到配对。
##### 可能的问题
- 不能配对该如何表示？
可以的表示方法: [name, ""] 

##### Solution of Amy

不需要用static class
Class People 可以放在 public class 外面

了解概念:
Nested Class && Inner Class

ref : https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html


Java also can define multiple top level classes in a single file

see discussion here : https://stackoverflow.com/questions/2336692/java-multiple-class-declarations-in-one-file


如果 input 没有shuffle , 可以自己手动shuffle


连着　poll()两次有bug






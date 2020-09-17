coding题目是拼接两个字符串，合并中间的公共部分
例如： （输入）abc + bcd = （输出）abcd

#### 1 brute force  
input String A, B  
A : length m  
B : length n

from the begin of A, try to match A.substring(i, m) B.subString(0, m - i)  
match two substring char by char 
if mismatch, i++

time complexity: O(m * n) 

#### 2 brute force with hash

subStrA = A.subString(i, m)  
subStrB = B.subString(0, m - i)

if(subStrA.hashCode == subStrB.hashCode) 
then  compare two substring char by char

else  
i++

time complexity: 
worst: O(m * n)  
best: O(m + n) // only need to compare char by char once, or hashcode never get equal




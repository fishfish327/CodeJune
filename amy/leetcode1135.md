最低价钱联通所有城市

一个关于union find 和kruskal的小总结

难点主要在于find function 和unionfunc
通过的答案

```java
class Solution {
    int[] par;
    public int minimumCost(int N, int[][] connections) {
        int cost = 0;

        Arrays.sort(connections, (a,b) -> a[2] - b[2]);
         par = new int[N + 1];
        for(int i = 1; i < N + 1; i++){
            par[i] = i;
        }
        for(int[] cur : connections){
            if(union(cur[0], cur[1])){
                cost+= cur[2];
            }
        }
        int p = -1;
        for(int i = 1; i < N + 1; i++){
            int num = find(i);
            if(p == -1){
                p = num;
            }else if (p != num){//p要是不等于num
            /*通过并查集合并后，每个连通分量节点都会有相同的root，因此检查所有节点的root：

如果检查到只有一个root，说明这个图只有一个连通分量，是连通图，返回cost。
如果检查到超过一个root，说明这个图有多个连通分量，不是一个连通图，返回-1

作者：jzj1993
链接：https://leetcode-cn.com/problems/connecting-cities-with-minimum-cost/solution/java-kruskal-suan-fa-27ms-by-jzj1993/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
            */
                return -1;
            }
        }
        return cost;
    }
    public boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b) return false;
        par[a] = b;
        return true;
    }
    public int find(int x){
        while(x != par[x]){
            par[x] = par[par[x]];
            x = par[x];
        }
        return x;
    }
/*
    public int find(int x) {
        while (x != par[x]) {
            par[x] = par[par[x]];其实这部分也是硬背 可能看一下循环状态
            x = par[x];
        }
        return x;
    }
我写的部分主要是 a 和b的定义有问题
    public boolean union(int a, int b){
        int para = find(a);
        int parb = find(b);//这个部分有问题
        if(a == b) return false;
        para = parb;
        return true;
    }
*/
```

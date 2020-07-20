```java
class Solution {
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        // 记录edges 组成的无向图
        Map<Integer, List<Integer>> graph = getGraph(edges, n);
        
        // mark 数组,　记录访问过的节点
        boolean[] mark = new boolean[n];
        // 自顶向下遍历树
        int[] res = new int[n];
        dfs(graph, 0, mark, labels, res);
        return res;
    }
    // 类似于后序遍历
    private int[] dfs(Map<Integer, List<Integer>> graph, int curr, boolean[] mark, String labels, int[] res){
        mark[curr] = true;
        int[] currCnt = new int[26];
        currCnt[labels.charAt(curr) - 'a'] ++;
        List<Integer> child = graph.get(curr);
        for(int node : child){
            // 如果该节点被标记过，说明该节点记录的是当前节点的parent, 不予访问
            /*
            eg: map : map : {0: [1,2], 1:[0,4,5], 2:[0,3,6], 3:[2], 4:[1], 5:[1], 6:[1]}
            curr = 0, node = 1, 2    -> mark[0]
            curr = 1, node = 0,4,5   ->  (0 被跳过) -> mark[1]
            curr = 2, node = 0,3,6   ->  (0 被跳过) -> mark[2]
            curr = 3, node = 2 -> 2 被跳过 -> mark[3]
            curr = 4, node = 1 -> 1 被跳过 -> mark[4]
            curr = 5, node = 1 -> 1 被跳过 -> mark[5]
            curr = 6, node = 2 -> 2 被跳过 -> mark[6]
            */
            if(mark[node] == true){
                continue;
            }
            // 对子节点　dfs
            int[] childCnt = dfs(graph, node, mark, labels, res);
            
            for(int i = 0; i < 26; i++){
                  currCnt[i] += childCnt[i];
            }
        }
        res[curr] = currCnt[labels.charAt(curr) - 'a'];
        return currCnt;
        
        
    }
    // 构建无向图
    /*
    eg:  n = 7, edges = [[0,1],[0,2],[1,4],[1,5],[2,3],[2,6]]
    得到map : {0: [1,2], 1:[0,4,5], 2:[0,3,6], 3:[2], 4:[1], 5:[1], 6:[1]}
    */
    public Map<Integer, List<Integer>> getGraph(int[][] edges, int n){
        Map<Integer, List<Integer>> res = new HashMap<>();
        for(int i = 0; i < n; i++){
            res.put(i, new ArrayList<>());
        }
        for(int[] e : edges){
            res.get(e[0]).add(e[1]);
            res.get(e[1]).add(e[0]);
        }
        return res;
        
    }
}
```

//given a list of paths and starting point, find the destination 
  //无环，每个点至多只有一个出度

 public static String solution1(List<List<String>> paths, String start){
        Map<String, String> graph = new HashMap<>();
        for(List<String> path : paths){
            graph.put(path.get(0),path.get(1));
        }
        while(graph.containsKey(start)){
            start = graph.get(start);
        }
        return start;
    }
   }
    //have cycle + each node only have at most one outdegree 有环 至多一个出度
    public static String solution2(List<List<String>> paths, String start){
        Set<String> visited = new HashSet<>();
        Map<String, String> graph = new HashMap<>();
        boolean isCycle = false;
        for(List<String> path : paths){
            graph.put(path.get(0),path.get(1));
        }
        while(graph.containsKey(start)){
            visited.add(start);
            start = graph.get(start);
            if(visited.contains(start)){
                isCycle = true;
                break;
            }
        }
        return !isCycle ? start : "has cycle";
    }

//有环 多出度
    static List<List<String>> res = new ArrayList<>();
    static boolean isCycle = false;
    public static List<List<String>> solution3(List<List<String>> paths, String start){
        
        Map<String, List<String>> graph = new HashMap<>();
         for(List<String> path : paths){
            graph.putIfAbsent(path.get(0), new LinkedList<>());
             graph.get(path.get(0)).add(path.get(1));
        }
        System.out.println(graph);
        HashSet<String> visited = new HashSet<String>();
        visited.add(start);
        List<String> part = new ArrayList<>();
        part.add(start);
        helper(graph, start,part,visited);
        
        System.out.println(isCycle);
        return  isCycle ? new ArrayList<>() :res;
        
    }
    private static void helper(Map<String, List<String>> graph, String start, List<String> list, HashSet<String> visited){
        if(isCycle) return;
        if(!graph.containsKey(start)){
           // list.add(start);
            res.add(new ArrayList<>(list));
            return;
        }
        List<String> nextNodes = graph.get(start);
        
        
        for(int i = 0; i < nextNodes.size(); i++){
            String nextNode = nextNodes.get(i);
           // System.out.println(nextNodes);
            if(visited.contains(nextNode)) isCycle = true;
            list.add(nextNode);
            //graph.get(start).remove(nextNode);
           // if(graph.get(start).size() == 0) graph.remove(start);
            visited.add(nextNode);
            
            helper(graph, nextNode, list, visited);
            visited.remove(nextNode);
            list.remove(list.size() - 1);
            //graph.putIfAbsent(start, new ArrayList<>());
            //graph.get(start).add(i, nextNode);
            
        }
        
    }
    
    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList(new String[]{"A","B"}));
        paths.add(Arrays.asList(new String[]{"A", "C"}));
        paths.add(Arrays.asList(new String[]{"B","C"}));
        paths.add(Arrays.asList(new String[]{"B","D"}));
       //  paths.add(Arrays.asList(new String[]{"D","B"}));
        
        System.out.println(solution3(paths, "A"));
    }
}

//revision 版本有环多出度

static List<List<String>> res = new ArrayList<>();
    static boolean isCycle = false;
    public static List<List<String>> solution3(List<List<String>> paths, String start){
        
        Map<String, List<String>> graph = new HashMap<>();
         for(List<String> path : paths){
            graph.putIfAbsent(path.get(0), new LinkedList<>());
             graph.get(path.get(0)).add(path.get(1));
        }
        System.out.println(graph);
        Set<String> visited = new LinkedHashSet<String>();
        visited.add(start);
       
        helper(graph, start,visited);
        
        System.out.println(isCycle);
        return  isCycle ? new ArrayList<>() :res;
        
    }
    private static void helper(Map<String, List<String>> graph, String start, Set<String> visited){
        
        if(isCycle) return;
        if(visited.contains(start)) isCycle = true;
        if(!graph.containsKey(start)){
            res.add(new ArrayList<>(visited));
            return;
        }
        
        List<String> nextNodes = graph.get(start);
        for(int i = 0; i < nextNodes.size(); i++){
            String nextNode = nextNodes.get(i);
         
            visited.add(nextNode);
            helper(graph, nextNode,  visited);
            visited.remove(nextNode);
        }
    }
    
    public static void main(String[] args) {
        List<List<String>> paths = new ArrayList<>();
        paths.add(Arrays.asList(new String[]{"A","B"}));
        paths.add(Arrays.asList(new String[]{"A", "C"}));
        paths.add(Arrays.asList(new String[]{"B","C"}));
        paths.add(Arrays.asList(new String[]{"B","D"}));
       //  paths.add(Arrays.asList(new String[]{"D","B"}));
        
        System.out.println(solution3(paths, "A"));
    }
}

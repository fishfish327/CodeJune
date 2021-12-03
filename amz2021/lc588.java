class FileSystem {
    class FileNode{
        String name;
        Map<String, FileNode> children;
        boolean isFile;
        String content;
        public FileNode(String name){
            this.isFile = false;
            this.name = name;
            this.children = new HashMap<>();
            this.content = "";
        }
    }
    FileNode root = null;
    public FileSystem() {
        root = new FileNode("root");
    }
    
    public List<String> ls(String path) {
        String[] paths = path.split("/");
        FileNode node = root;
        List<String> list = new ArrayList<>();
        for(int i = 1; i < paths.length; i++){
            node = node.children.get(paths[i]);
        }
        if(node.isFile) list.add(paths[paths.length - 1]);
        else{
            for(String i : node.children.keySet()){
                list.add(i);
            }
        }
        Collections.sort(list);
        return list;
        
    }
    
    public void mkdir(String path) {
        String[] paths = path.split("/");
        FileNode node = root;
        for(int i = 1; i < paths.length; i++){
            if(!node.children.containsKey(paths[i])) node.children.put(paths[i], new FileNode(paths[i]));
            node = node.children.get(paths[i]);
        }
       // node.isFile = true;
    }
    
    public void addContentToFile(String filePath, String content) {
        String[] paths = filePath.split("/");
        FileNode node = root;
        for(int i = 1; i < paths.length; i++){
            if(!node.children.containsKey(paths[i])) node.children.put(paths[i], new FileNode(paths[i]));
            node = node.children.get(paths[i]);
        }
        node.isFile = true;
        node.content += content;
    }
    
    public String readContentFromFile(String filePath) {
        String[] paths = filePath.split("/");
        FileNode node = root;
        for(int i = 1; i < paths.length; i++){
            if(!node.children.containsKey(paths[i])) node.children.put(paths[i], new FileNode(paths[i]));
            node = node.children.get(paths[i]);
        }
        if(!node.isFile) return "";
        return node.content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

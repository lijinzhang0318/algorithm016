class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        if(root != null) {
            result.add(root.val);
            for(Node node : root.children) {
                result.addAll(preorder(node));
            }
        }
        return result;
    }
}
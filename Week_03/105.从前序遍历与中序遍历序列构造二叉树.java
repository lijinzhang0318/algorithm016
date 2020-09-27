public TreeNode buildTree(int[] preorder, int[] inorder) {
    return buildTreeHelper(preorder,  inorder, (long)Integer.MAX_VALUE + 1);
}
int pre = 0;
int in = 0;
private TreeNode buildTreeHelper(int[] preorder, int[] inorder, long stop) {
    if(pre == preorder.length){
        return null;
    }
    if (inorder[in] == stop) {
        in++;
        return null;
    }
    int root_val = preorder[pre++];
    TreeNode root = new TreeNode(root_val);   
    root.left = buildTreeHelper(preorder,  inorder, root_val);
    root.right = buildTreeHelper(preorder, inorder, stop);
    return root;
}

#### DFS-深度优先搜索

- 代码模板

```
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if(root == null){
            return allResults;
        }
        travel(root, 0 ,allResults);
        return allResults;
    }
    
    private void travel(TreeNode root,int level,List<List<Integer>> results){
        if(results.size() == level){
            results.add(new ArrayList<>());
        }
        results.get(level).add(root.val);
        if(root.left != null){
            travel(root.left, level+1, results);
        }
        if(root.right != null){
            travel(root.right, level+1, results);
        }
    }
```


#### BFS-广度优先搜索

- 代码模板

```
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> allResults = new ArrayList<>();
        if (root == null) {
            return allResults;
        }
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            List<Integer> results = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.poll();
                results.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            allResults.add(results);
        }
        return allResults;
    }
```

#### 贪心算法

- 概念

> 贪心算法是一种在每一步选择中都采取在当前状态下最好或最优（即最有利）的选择，从而希望导致结果是全局最好或最优的算法。

- 贪心算法与动态规则的区别

> 贪心算法与动态规划的不同在于它对每个子问题的解决方案都做出选择，不能回退。动态规划会保存以前的运算结果，并根据以前的结果对当前进行选择，有回退功能。

- 适用场景

> 问题能够分解成子问题来解决，子问题的最优解能递推到最终问题的最优解。这种子问题最优解称为最优子结构。

#### 二分查找

- 代码模板

```
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return -1;
}
```

- 二分查找的前提

&emsp;1. 目标函数单调性（单调递增或递减）

&emsp;2. 存在上下界

&emsp;3. 能够通过索引访问

class Solution {
    // 定义两个全局变量
    ArrayDeque<Integer> deq = new ArrayDeque<>();
    int[] nums;

    public void cleanDeque(int i, int k) {
        // 随着滑动窗口的移动，移除首部的一个元素
        if(!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }

        // 清除掉所有比当前值小的队列元素
        while(!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // 检查参数合法性
        int n = nums.length;
        if(n * k == 0) return new int[0];
        if(k == 1) return nums;

        this.nums = nums;
        // 最开始的初始窗口中最大值的索引
        int maxIndex = 0;
        
        // 进行队列初始化
        for(int i=0; i<k; i++) {
            cleanDeque(i, k);
            deq.addLast(i);
            // 不断更新最大值索引
            if(nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int[] result = new int[n - k + 1];
        result[0] = nums[maxIndex];

        for(int i=k; i<n; i++) {
            cleanDeque(i, k);
            deq.addLast(i);
            // 队首元素就是最大值
            result[i - k + 1] = nums[deq.getFirst()];
        }

        return result; 
    }
}

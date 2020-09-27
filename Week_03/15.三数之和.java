class Solution {
    // 排序 + 双指针法
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(nums == null || n < 3) return result;
        // 排序
        Arrays.sort(nums);
        // 遍历数组，先固定第一个数，剩下两个指针分别指向剩余数组的头尾
        for(int i=0; i<n; i++) {
            // 如果第一个数就已经大于0，则结束循环
            if(nums[i] > 0) break;
            // 第一个数的去重
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i + 1;
            int R = n - 1;
            while(L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // 头指针去重
                    while(L < R && nums[L] == nums[L + 1]) L++;
                    // 尾指针去重
                    while(L < R && nums[R] == nums[R - 1]) R--;
                    L++;
                    R--;
                }
                // 头尾指针动态移动
                else if(sum < 0) L++;
                else if(sum > 0) R--;
            }
        }
        return result;
    }
}
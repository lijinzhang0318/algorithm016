class Solution {
    public void moveZeroes(int[] nums) {
        // 指针j主要用以指向0元素，但指向的元素也可能非0
        int j = 0;
        for (int i=0; i<nums.length; i++) {
            // i指针将指向的非0元素赋值到j指针位置
            if(nums[i] != 0) {
                boolean isZero = nums[j] == 0;
                nums[j] = nums[i];
                // 原j指向0元素时
                if(isZero) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
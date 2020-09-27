class Solution {
    public int getKthMagicNumber(int k) {
        // 设置三个指针，p3指针指向的数永远乘以3，以此类推
        int p3 = 0, p5 = 0, p7 = 0;
        int[] nums = new int[k];
        // 第一个丑数为1
        nums[0] = 1;
        for(int i=1; i<k; i++) {
            // 每次三个数中取最小
            nums[i] = Math.min(nums[p3]*3, Math.min(nums[p5]*5, nums[p7]*7));

            // 指向最小数的指针往前走一步
            if(nums[i] == nums[p3]*3) p3++;
            if(nums[i] == nums[p5]*5) p5++;
            if(nums[i] == nums[p7]*7) p7++;
        }
        return nums[k - 1];
    }
}
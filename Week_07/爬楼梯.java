class Solution {
    // 滚动数组求解
    public int climbStairs(int n) {
        // 数组元素的初始化
        int num1 = 0, num2 = 0, num3 = 1;
        for(int i=1; i<=n; i++) {
            // 向左滚动，依次赋值
            num1 = num2;
            num2 = num3;
            num3 = num1 + num2;
        }
        return num3;
    }
}
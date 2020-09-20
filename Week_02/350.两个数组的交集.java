class Solution {
    // 双指针法
    public static int[] intersect(int[] nums1, int[] nums2) {
        // 先做排序操作
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // 初始为最短的数组长度
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int index1 = 0, index2 = 0, index = 0;
        while(index1 < nums1.length && index2 < nums2.length) {
            // 让指向小数的指针向右移
            if(nums1[index1] > nums2[index2]) {
                index2++;
            } else if(nums1[index1] < nums2[index2]) {
                index1++;
            } else {
                // 如果相等则保存交集值，并同时向右移动两个指针
                result[index++] = nums1[index1];
                index1++;
                index2++;
            }
        }
        // 只取交集长度的结果值
        return Arrays.copyOf(result, index);
    }
}
class Solution {
    public void moveZeroes(int[] nums) {
        // ָ��j��Ҫ����ָ��0Ԫ�أ���ָ���Ԫ��Ҳ���ܷ�0
        int j = 0;
        for (int i=0; i<nums.length; i++) {
            // iָ�뽫ָ��ķ�0Ԫ�ظ�ֵ��jָ��λ��
            if(nums[i] != 0) {
                boolean isZero = nums[j] == 0;
                nums[j] = nums[i];
                // ԭjָ��0Ԫ��ʱ
                if(isZero) {
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}
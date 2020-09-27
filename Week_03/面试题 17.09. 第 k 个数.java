class Solution {
    public int getKthMagicNumber(int k) {
        // ��������ָ�룬p3ָ��ָ�������Զ����3���Դ�����
        int p3 = 0, p5 = 0, p7 = 0;
        int[] nums = new int[k];
        // ��һ������Ϊ1
        nums[0] = 1;
        for(int i=1; i<k; i++) {
            // ÿ����������ȡ��С
            nums[i] = Math.min(nums[p3]*3, Math.min(nums[p5]*5, nums[p7]*7));

            // ָ����С����ָ����ǰ��һ��
            if(nums[i] == nums[p3]*3) p3++;
            if(nums[i] == nums[p5]*5) p5++;
            if(nums[i] == nums[p7]*7) p7++;
        }
        return nums[k - 1];
    }
}
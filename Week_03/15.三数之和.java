class Solution {
    // ���� + ˫ָ�뷨
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if(nums == null || n < 3) return result;
        // ����
        Arrays.sort(nums);
        // �������飬�ȹ̶���һ������ʣ������ָ��ֱ�ָ��ʣ�������ͷβ
        for(int i=0; i<n; i++) {
            // �����һ�������Ѿ�����0�������ѭ��
            if(nums[i] > 0) break;
            // ��һ������ȥ��
            if(i > 0 && nums[i] == nums[i-1]) continue;
            int L = i + 1;
            int R = n - 1;
            while(L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if(sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    // ͷָ��ȥ��
                    while(L < R && nums[L] == nums[L + 1]) L++;
                    // βָ��ȥ��
                    while(L < R && nums[R] == nums[R - 1]) R--;
                    L++;
                    R--;
                }
                // ͷβָ�붯̬�ƶ�
                else if(sum < 0) L++;
                else if(sum > 0) R--;
            }
        }
        return result;
    }
}
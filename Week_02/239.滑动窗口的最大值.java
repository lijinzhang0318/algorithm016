class Solution {
    // ��������ȫ�ֱ���
    ArrayDeque<Integer> deq = new ArrayDeque<>();
    int[] nums;

    public void cleanDeque(int i, int k) {
        // ���Ż������ڵ��ƶ����Ƴ��ײ���һ��Ԫ��
        if(!deq.isEmpty() && deq.getFirst() == i - k) {
            deq.removeFirst();
        }

        // ��������бȵ�ǰֵС�Ķ���Ԫ��
        while(!deq.isEmpty() && nums[i] > nums[deq.getLast()]) {
            deq.removeLast();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        // �������Ϸ���
        int n = nums.length;
        if(n * k == 0) return new int[0];
        if(k == 1) return nums;

        this.nums = nums;
        // �ʼ�ĳ�ʼ���������ֵ������
        int maxIndex = 0;
        
        // ���ж��г�ʼ��
        for(int i=0; i<k; i++) {
            cleanDeque(i, k);
            deq.addLast(i);
            // ���ϸ������ֵ����
            if(nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        int[] result = new int[n - k + 1];
        result[0] = nums[maxIndex];

        for(int i=k; i<n; i++) {
            cleanDeque(i, k);
            deq.addLast(i);
            // ����Ԫ�ؾ������ֵ
            result[i - k + 1] = nums[deq.getFirst()];
        }

        return result; 
    }
}

class Solution {
    // ˫ָ�뷨
    public static int[] intersect(int[] nums1, int[] nums2) {
        // �����������
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        // ��ʼΪ��̵����鳤��
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int index1 = 0, index2 = 0, index = 0;
        while(index1 < nums1.length && index2 < nums2.length) {
            // ��ָ��С����ָ��������
            if(nums1[index1] > nums2[index2]) {
                index2++;
            } else if(nums1[index1] < nums2[index2]) {
                index1++;
            } else {
                // �������򱣴潻��ֵ����ͬʱ�����ƶ�����ָ��
                result[index++] = nums1[index1];
                index1++;
                index2++;
            }
        }
        // ֻȡ�������ȵĽ��ֵ
        return Arrays.copyOf(result, index);
    }
}
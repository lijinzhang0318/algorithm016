    public int firstUniqChar(String s) {
        // �ó���26��������26����ĸ�ظ�����
        int[] nums = new int[26];
        char[] chars = s.toCharArray();
        for(char ch: chars) {
            nums[ch - 'a']++;
        }
        for(int i=0; i<chars.length; i++) {
            if(nums[chars[i] - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
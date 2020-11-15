    public int firstUniqChar(String s) {
        // 用长度26的数组存放26个字母重复次数
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
package array;

import java.util.HashMap;

/*
 * 文件名：Sum0.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Sum0.java
 * 修改人：yunhai
 * 修改时间：2015年9月29日
 * 修改内容：新增
 */

/**
 * 找出数组中和为0的最长子数组
 * 
 * currSum+=a[i],并将currSum存在map里，如果map中已存在大小为currSum，则说明两次currSum之间的序列和为0
 * 
 * @author yunhai
 */
public class Sum0 {
    public static void main(String[] args) {
        int[] seed = new int[]{1, 2, 3, 4, -9, 6, 7, -8, 1, 9, -13};
        int currSum = 0;
        int n = seed.length;
        HashMap map = new HashMap();
        int left = 0, right = 0, maxlen = 0;
        for (int i = 0; i < n; i++) {
            currSum += seed[i];
            if (map.containsKey(currSum)) {
                int d = (int) map.get(currSum) + 1;// 临时存放左边界
                System.out.println("所有和为0的序列：" + d + "-->" + i + "-->" + yanzheng(seed, d, i));
                if (i - d > maxlen) {
                    left = d;
                    right = i;
                    maxlen = right - left + 1;
                }
            } else {
                map.put(currSum, i);
            }
        }
        System.out.println("max:" + left + "-->" + right + "  【未考虑最长序列有多组的情况】");
    }

    /**
     * 验证序列和是否为0
     */
    private static boolean yanzheng(int[] seed, int left, int right) {
        int s = 0;
        for (int i = left; i <= right; i++) {
            s += seed[i];
        }
        return s == 0 ? true : false;
    }
}

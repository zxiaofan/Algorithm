/*
 * 文件名：TakeStairs.java
 * 版权：Copyright 2007-2017 zxiaofan.com. Co. Ltd. All Rights Reserved. 
 * 描述： TakeStairs.java
 * 修改人：zxiaofan
 * 修改时间：2017年6月30日
 * 修改内容：新增
 */
package dp;

import java.util.Map;

/**
 * 走楼梯.
 * 
 * http://mp.weixin.qq.com/s/vEUJ7pX3yrFrl69O0QOCwQ
 * 
 * 有一座高度是10级台阶的楼梯，从下往上走，每跨一步只能向上1级或者2级台阶。要求用程序来求出一共有多少种走法。
 * 
 * 思路1：假设只差最后一步走到第10级，此时只有2种情况（第9级走到10级，第8级走到10级）；
 * 
 * 忽略0到8级的过程，忽略0到9级的过程，想走到最后第10级，最后一步必然从8级或9级开始；
 * 
 * 引申新问题：如果0-9级走法有X种，0-8级走法有Y种，那么0-10级走法有X+Y种，即有F(10)=F(9)+F(8)；
 * 
 * F(1)=1;F(2)=2;F(n)=F(n-1)+F(n-2)(n>=3);
 * 
 * 方案1：递归求解getStairsWay(int n)
 * 
 * 优化：相同参数被重复计算，越往下，重复越多，可建立hash表存入计算结果，计算时先从缓存中匹配==》备忘录算法
 * 
 * 方案2：备忘录算法getStairsWay(int n, Map<Integer, Integer> map)
 * 
 * 方案3：动态规划（思路逆转）getStairsWayDP(int n)
 * 
 * @author zxiaofan
 */
public class TakeStairs {

    /**
     * 方案1：递归求解.
     * 
     * 时间复杂度：计算F(n)，需要先计算F(n-1)和F(n-2)，以此类推，构成一颗二叉树，树的节点个数就是递归方程所需就算的次数。
     * 
     * 树高n-1，节点个数接近2的n-1次方，时间复杂度近似看做O(2^n)。
     * 
     * @param n
     *            n
     * @return num
     */
    int getStairsWay(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        return getStairsWay(n - 1) + getStairsWay(n - 2);
    }

    /**
     * 方案2：备忘录算法.
     * 
     * F(1)到F(n)共有n个不同的输入，hash表存了n-2个结果，故时间复杂度、空间复杂度都是O(N)。
     * 
     * @param n
     *            n
     * @param map
     *            备忘录（此处未考虑并发、map应全局）
     * @return num
     */
    int getStairsWay(int n, Map<Integer, Integer> map) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else if (map.containsKey(n)) {
            return map.get(n);
        }
        int value = getStairsWay(n - 1, map) + getStairsWay(n - 2, map);
        map.put(n, value);
        return value;
    }

    /**
     * 动态规划.
     * 
     * 思路逆转，由自顶向下转为自底向上。
     * 
     * F(3)=F(2)+F(1);F(4)=F(3)+F(2)...
     * 
     * 每次迭代过程中，只需保留之前的2个状态，而不需要像备忘录算法保留所有的子状态（如果需要多次计算，备忘录算法或许更优）。
     * 
     * 时间复杂度O(N)；之引入了2或3个变量，故空间复杂度O(1)。
     * 
     * @param n
     *            n
     * @return num
     */
    int getStairsWayDP(int n) {
        if (n < 1) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int a = 1;
        int b = 2;
        int temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = a + b;
            a = b;
            b = temp;
        }
        return temp;
    }

}

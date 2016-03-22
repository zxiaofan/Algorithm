package array;

/**
 * 子数组最大和
 * 
 * 问题描述： 给定由n个整数组成的序列(a1,a2, …,an)，求该序列形如（ai，·····，aj）
 * 
 * 的子段和的最大值，当所有整数均为负整数时，其最大子段和为0。
 * 
 * @author yunhai
 */
public class MaxSubarray {
    public static void main(String[] args) {
        int a[] = {1, 2, -4, 6};
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("暴力计算最优值：" + maxSum(a, a.length));
        System.out.println("分治实现最优值：" + maxSum(a, 0, a.length - 1));
        maxSumDp(a);
    }

    public static int maxSum(int a[], int n) { // 暴力计算
        int left = 0, right = 0;
        int maxSum = 0;
        for (int i = 0; i < n; i++) // 从第一个数开始算起
        {
            int sum = 0;
            for (int j = i; j < n; j++)//
            {
                sum += a[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    left = i;
                    right = j;
                }
            }
        }
        System.out.println("暴力计算最优解:" + left + "-->" + right);
        return maxSum;
    }

    /*
     * 假定我们要寻找子数组A[low..high]的最大子数组，使用分治法意味着我们要将子数组划分为两个规模尽可能相等的子数组,
     * 
     * 也就是说，找到子数组的中央位置，比如mid，然后求解两个子数组A[low..mid]和A[mid + 1..high].所以，A[low..high]的任何连续子数组A[i..j]所处的位置必然是三种情况之一：
     * 
     * 1.完全位于子数组A[low..mid]中， 因此low<=i<=j<=mid; 2.完全位于子数组A[mid + 1..high]中，因此mid<=i<=j<=high; 3.跨越了中点，因此low<=i<=mid.
     * 
     * 实际上，A[low..high]的一个最大子数组必然是完全位于A[low..mid]中、完全位于A[mid + 1..high]中或者跨越中点的所有子数组中和最大者。
     */
    public static int maxSum(int a[], int left, int right) { // 分治实现
        int sum = 0;
        if (left == right) // 如果序列长度为1，直接求解
        {
            sum = a[left] > 0 ? a[left] : 0;
        } else {
            int center = (left + right) / 2; // 1分为2
            int leftsum = maxSum(a, left, center); // 对应情况1，递归求解
            int rightsum = maxSum(a, center + 1, right);// 对应情况2， 递归求解
            int cross_sum = FindMaxCrossSubarray(a, left, center, right);// 跨越center前后

            if (leftsum >= rightsum && leftsum >= cross_sum) // 最大子数组在左边
                sum = leftsum;
            else if (rightsum >= leftsum && rightsum >= cross_sum) // 右边
                sum = rightsum;
            else
                sum = cross_sum;// 跨越
        }
        return sum;
    }

    static int FindMaxCrossSubarray(int A[], int low, int mid, int high) // 跨越
    {
        int max_left = 0;// 左半部分取最大值时对应的下标
        int max_right = 0;
        int left_maxsum = 0;// 左半部分的最大和
        int sum = 0;// 临时存放左半部分的sum
        for (int i = mid; i >= low; i--) // 左半部的最大子数组
        {
            sum += A[i];
            if (sum > left_maxsum) {
                left_maxsum = sum;
                max_left = i;
            }
        }
        int right_maxsum = 0;
        sum = 0;
        for (int i = mid + 1; i <= high; i++) // 右半部的最大子数组
        {
            sum += A[i];
            if (sum > right_maxsum) {
                right_maxsum = sum;
                max_right = i;
            }
        }
        // System.out.println("分治实现最优解:" + max_left + "-->" + max_right); // 暂无法获得
        return left_maxsum + right_maxsum;
    }

    /**
     * 动态规划.
     * 
     * 时间复杂度和空间复杂度均为O(n)
     */
    public static void maxSumDp(int[] arr) {
        int summax = 0, sumTemp = 0, n = arr.length;
        int bestx = 0, besty = 0; // 最优解对应的下标
        for (int i = 0; i < n; i++) {
            if (sumTemp > 0) {// sumtemp>0则继续往下加，否则会让原本为正的子段和变小（加了负的sumtemp）
                sumTemp += arr[i];
            } else {// sumtemp<0，表明前部分为负，直接舍弃先前的计算结果，并重新计算
                sumTemp = arr[i];
                if (sumTemp > summax)
                    bestx = i;// 当sumtemp<=0且sumtemp>summax时，才更新左下标
            }
            if (sumTemp > summax) {// 当新的sumtemp更大时，才更新summax
                summax = sumTemp;
                besty = i;
            }
        }
        System.out.println("动态规划算法最优值：" + summax);
        System.out.println("动态规划最优解：" + bestx + "-->" + besty);
    }
}

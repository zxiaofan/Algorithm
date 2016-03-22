package array;

/*
 * 文件名：MaxSubmatrix.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： MaxSubmatrix.java
 * 修改人：yunhai
 * 修改时间：2015年9月28日
 * 修改内容：新增
 */

/**
 * 最大子矩阵
 * 
 * 问题描述： 给定一个m*n(0<n<=100)的矩阵，请找到此矩阵的一个子矩阵，并且此子矩阵的各个元素的和最大，输出这个最大的值。
 * 
 * Example：
 * 
 * 0 -2 -7 0
 * 
 * 9 2 -6 2
 * 
 * -4 1 -4 1
 * 
 * -1 8 0 -2
 * 
 * 其中左上角的子矩阵：
 * 
 * 9 2
 * 
 * -4 1
 * 
 * -1 8
 * 
 * 此子矩阵的值为9+2+(-4)+1+(-1)+8=15。
 * 
 * @author yunhai
 */
public class MaxSubmatrix {
    public static void main(String[] args) {
        // int a[][] = {{0, -2, -7}, {9, 2, -6}, {-4, 1, -4}, {-1, 8, 0}};
        int a[][] = {{0, -1, 0, -2, -7}, {-1, -2, 9, 2, -6}, {-1, -1, -4, 1, -4}, {-1, 0, -1, 8, 0}};
        for (int[] is : a) {
            for (int i : is) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        System.out.println("最大子矩阵和：" + subMaxMatrix(a));
    }

    /**
     * 假设这个最大子矩阵的维数是一维，要找出最大子矩阵, 原理与求“最大子段和问题” 是一样的；
     *
     * 假设这个矩阵只有两行，要找出最大子矩阵，如果我们把这两行上下相加，情况就和求“最大子段和问题” 又是一样的；
     * 
     * 矩阵有3行、4行...同上，但不管子矩阵有多少行，都有多种情况；
     * 
     * 0行到m行、1行到m行...m行到m行，不断找最大的子矩阵和；
     *
     * 用到了一个辅助矩阵从原始矩阵得到从 i 行到 j 行 的上下值之和。假设原始矩阵是matrix, 它每一层上下相加后得到的矩阵是total。
     */
    public static int subMaxMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;// 行列数
        int[][] total = matrix;// total[i][j]=matrix[0][j]+matrix[1][j]+...+matrix[i][j],即0行到i行所有第j列元素之和
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                total[i][j] += total[i - 1][j];
            }
        }
        int maxsum = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                // result[k] 保存的是 i 行 到 j 行 所对应的矩阵k列之和，可由total[j][k] - total[i-1][k]计算
                int[] result = new int[n];
                for (int k = 0; k < n; k++) {
                    if (i == 0) {// 0行到j行不用减
                        result[k] = total[j][k];
                    } else {
                        result[k] = total[j][k] - total[i - 1][k];
                    }
                } // 至此，一维数组result存了i到j行的列之和
                  // 计算i行到j行的最大子矩阵和==>求result的最大子数组和
                  // (0行到0行最大子数组和-->0行到1行...0行到m行-->1行到1行-->1行到2行...-1行到m行...m行到m行)
                int maxt = maxSumDp(result);
                if (maxt > maxsum) {
                    maxsum = maxt;
                }
            }
        }
        return maxsum;
    }

    /**
     * 动态规划求最大子数组.
     * 
     * 时间复杂度和空间复杂度均为O(n)
     */
    public static int maxSumDp(int[] arr) {
        // summax应为Integer.MIN_VALUE
        int summax = Integer.MIN_VALUE, sumTemp = 0, n = arr.length;
        int bestx = 0, besty = 0; // 最优解对应的下标
        for (int i = 0; i < n; i++) {
            if (sumTemp > 0) {// sumtemp>0则继续往下加，否则会让原本为正的子段和变小（加了负的sumtemp）
                sumTemp += arr[i];
            } else {// sumtemp<0，表明前部分为负，直接舍弃先前的计算结果，并重新计算
                sumTemp = arr[i];
                bestx = i;// 当sumtemp<0时，才更新左下标
            }
            if (sumTemp > summax) {// 当新的sumtemp更大时，才更新summax
                summax = sumTemp;
                besty = i;
            }
        }
        return summax;
        // System.out.println("动态规划算法最优值：" + summax);
        // System.out.println("动态规划最优解：" + bestx + "-->" + besty);//最优解很难算
    }
}

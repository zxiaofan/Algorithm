/*
 * 文件名：KingAndGoldMine.java
 * 版权：Copyright 2007-2017 zxiaofan.com. Co. Ltd. All Rights Reserved. 
 * 描述： KingAndGoldMine.java
 * 修改人：zxiaofan
 * 修改时间：2017年6月30日
 * 修改内容：新增
 */
package dp;

/**
 * 国王和金矿.
 * 
 * https://mp.weixin.qq.com/s/vEUJ7pX3yrFrl69O0QOCwQ【Note：原文代码有误，此处已更正】
 * 
 * 有一个国家发现了5座金矿，每座金矿的黄金储量不同，需要参与挖掘的工人数也不同。
 * 
 * 参与挖矿工人的总数是10人。每座金矿要么全挖，要么不挖，不能派出一半人挖取一半金矿。
 * 
 * 要求用程序求解出，要想得到尽可能多的黄金，应该选择挖取哪几座金矿？
 * 
 * 400金/5人；500金/5人；200金/3人；300金/4人；350金/3人。
 * 
 * ==方法1：排列组合暴力求解==
 * 
 * 每一座金矿都有挖与不挖两种选择，如果有N座金矿，排列组合起来就有2^N种选择。
 * 
 * 对所有可能性做遍历，排除那些使用工人数超过10的方案，在剩下的方案里找出获得金币数最多的方案。
 * 
 * 时间复杂度O(2^N)。
 * 
 * 
 * ==思路优化：寻找最优子结构（最后一个金矿有挖与不挖2种选择）==
 * 
 * 10人-5金矿最优子结构有2个：10人-4金矿；（10-第5金矿所需人数）人-4金矿。
 * 
 * 5金矿最优选择：1.前4金矿10人挖金数量；2.前4金矿(10-3)人挖金数量+第5金矿数量 的最大值
 * 
 * 模型建立：
 * 
 * 金矿数量N，工人数量W，金矿黄金量数组G[]，金矿用工量数组P[]。
 * 
 * F(5,10)=MAX( F(4,10), ( F(4,10-P[4])+ G[4]) )
 * 
 * 》》边界：
 * 
 * 当N=1，W>=P[0]时：F(N,W) = G[0];
 * 
 * 当N=1，W<P[0]时：F(N,W) = 0;
 * 
 * 》》 状态转移方程式：
 * 
 * F(N,W) = 0 (n<=1,w<P[0]);
 * 
 * F(N,W) = G[0] (n==1,w>=p[0]);
 * 
 * F(N,W) = F(N-1,W) (n>1,w<p[n-1]); # 补充方程
 * 
 * F(N,W) = max(F(N-1,W), F(N-1,W-P[N-1])+G[N-1]) (n>1,w>=p[n-1]);
 * 
 * 方法2：递归求解：递归的执行流程类似于一颗高度为N的二叉树，时间复杂度是O(2^N)。
 * 
 * 方法3：备忘录算法：HashMap的Key是一个包含金矿数N和工人数W的对象，Value是最优选择获得的黄金数。时间复杂度、空间复杂度，都等于备忘录中不同Key的数量。
 * 
 * 方法4：动态规划：
 * 
 * 表格分析（横坐标工人数量W，纵坐标N金矿编号，交叉项表示有N个金矿、W个人时的最优解），分析结果详见../KingAndGoldMine.png
 * 
 * 观察推导：3金矿8工人《==2金矿5工人、2金矿8工人（Max(500 ,500+200)）；5金矿10工人《==4金矿7工人、4金矿10工人（Max(900 ,500+350)）
 * 
 * （金矿数量N，工人数量W）。
 * 
 * @author zxiaofan
 */
public class KingAndGoldMine {
    public static void main(String[] args) {
        // 400金/5人；500金/5人；200金/3人；300金/4人；350金/3人。
        int goldMineNum = 5; // 金矿数量
        int personNum = 10; // 工人数量
        int[] golds = {400, 500, 200, 300, 350};
        int[] personNeeded = {5, 5, 3, 4, 3};
        int maxGold = getGoldDP(goldMineNum, personNum, golds, personNeeded);
        System.out.println(goldMineNum + "金矿-" + personNum + "人，最大金矿收益：" + maxGold);
        // 不同人数和不同金矿数量时的最大收益
        for (int i = 1; i <= goldMineNum; i++) {
            for (int j = 1; j <= personNum; j++) {
                System.out.print(getGoldDP(i, j, golds, personNeeded) + " | ");
            }
            System.out.println();
        }
    }

    /**
     * 动态规划求解金矿问题.
     * 
     * 时间复杂度是 O(goldMineNum * personNum)，空间复杂度是(personNum)，金矿数量越多，动态规划优势越明显【时空复杂度均与人数成正比】。
     * 
     * 当工人数增加（如1000），每个金矿用工数增加，需要计算500次，开辟1000单位的空间。
     * 
     * 而用简单递归，需计算2^5=32次，开辟5（递归深度）单位的空间，此时简单递归更有优势。
     * 
     * @param goldMineNum
     *            金矿数量
     * @param personNum
     *            工人数量
     * @param golds
     *            每座金矿对应的黄金数量
     * @param personNeeded
     *            每座金矿所需的工人数量
     * @return 最优解
     */
    static int getGoldDP(int goldMineNum, int personNum, int[] golds, int[] personNeeded) {
        // 以人为基准
        int[] preResults = new int[personNum]; // 上一行的最优结果数组 preResults
        int[] results = new int[personNum]; // 当前行的最优结果数组results
        // 只有一个金矿，遍历人数
        for (int i = 1; i <= personNum; i++) {
            if (personNeeded[0] > i) { // 第1个金矿所需人数>当前人数
                preResults[i - 1] = 0;
            } else {
                preResults[i - 1] = golds[0];
            }
        }
        // 第2座金矿开始计算，外层循环金矿数量，内存循环工人数量
        for (int i = 1; i < goldMineNum; i++) {
            for (int j = 1; j <= personNum; j++) {
                // 当前金矿所需人数>现有人数，取上一金矿的值
                if (personNeeded[i] > j) {
                    results[j - 1] = preResults[j - 1];
                } else if (personNeeded[i] == j) {
                    // 当前金矿所需人数=现有人数，取上一金矿和当前金矿的最大值
                    results[j - 1] = Math.max(preResults[j - 1], golds[i]);
                } else { // 当前金矿所需人数<现有人数
                    results[j - 1] = Math.max(preResults[j - 1], preResults[j - 1 - personNeeded[i]] + golds[i]);
                }
            }
            preResults = results;
            results = new int[personNum];
        }
        return preResults[personNum - 1];
    }
}

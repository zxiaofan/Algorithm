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
 * https://mp.weixin.qq.com/s/vEUJ7pX3yrFrl69O0QOCwQ
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

}

/*
 * 文件名：HanoiStack.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： HanoiStack.java
 * 修改人：xiaofan
 * 修改时间：2016年1月3日
 * 修改内容：新增
 */
package stackAndQueue;

import java.util.Stack;

/**
 * 用栈来求解汉诺塔问题：HanoiStack【3】
 * 
 * 【问题描述】：将汉诺塔游戏（小压大）规则修改，不能从左（右）侧的塔直接移到右（左）侧，而是必须经过中间塔。
 * 
 * 求当塔有N层时，打印最优移动过程和最优移动步数。如N=2，记上层塔为1，下层为2.则打印：1：left->mid;1
 * 
 * 由于必须经过中间，实际动作只有4个：左L->中M，中->左，中->右R，右->中。
 * 
 * 原则：①小压大；②相邻不可逆（上一步是L->M,下一步绝不能是M->L）
 * 
 * 非递归方法核心结论：1.第一步一定是L-M；2.为了走出最少步数，四个动作只可能有一个不违反上述两项原则。
 * 
 * 核心结论2证明：假设前一步是L->M（其他3种情况略）
 * 
 * a.根据原则①，L->M不可能发生；b.根据原则②，M->L不可能；c.根据原则①，M->R或R->M仅一个达标。
 * 
 * So，每走一步只需考察四个步骤中哪个步骤达标，依次执行即可。
 * 
 * @author xiaofan
 */
public class HanoiStack {
    private enum Action {
        None, LToM, MToL, MToR, RToM
    };

    static Action preAct = Action.None; // 上一步操作，最初什么移动操作都没有

    final static int num = 4; // 汉诺塔层数

    public static void main(String[] args) {
        int steps = transfer(num);
        System.out.println("It will move " + steps + " steps.");
    }

    private static int transfer(int n) {
        Stack<Integer> lS = new Stack<>(); // java7菱形用法，允许构造器后面省略范型。
        Stack<Integer> mS = new Stack<>();
        Stack<Integer> rS = new Stack<>();
        lS.push(Integer.MAX_VALUE);// 栈底有个最大值，方便后续可以直接peek比较
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = n; i > 0; i--) {
            lS.push(i);// 初始化待移动栈
        }
        int step = 0;

        while (rS.size() < n + 1) {// n+1,因为rS.push(Integer.MAX_VALUE);等于n+1说明全部移动完成
            step += move(Action.MToL, Action.LToM, lS, mS);// 第一步一定是LToM
            step += move(Action.LToM, Action.MToL, mS, lS);// 只可能有这4种操作
            step += move(Action.MToR, Action.RToM, rS, mS);
            step += move(Action.RToM, Action.MToR, mS, rS);
        }
        return step;
    }

    /**
     * 实施移动操作.
     * 
     * @param cantAct
     *            不能这样移动
     * @param nowAct
     *            即将执行的操作
     * @param fromStack
     *            起始栈
     * @param toStack
     *            目标栈
     * @return step(成功与否)
     */
    private static int move(Action cantAct, Action nowAct, Stack<Integer> fromStack, Stack<Integer> toStack) {
        if (preAct != cantAct && toStack.peek() > fromStack.peek()) {
            toStack.push(fromStack.pop()); // 执行移动操作
            System.out.println(toStack.peek() + ":" + nowAct);
            preAct = nowAct; // 更新“上一步动作”
            return 1;
        }
        return 0;
    }
}

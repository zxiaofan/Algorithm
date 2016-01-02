/*
 * 文件名：TwoStackQueue.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： TwoStackQueue.java
 * 修改人：xiaofan
 * 修改时间：2016年1月2日
 * 修改内容：新增
 */
package stackAndQueue;

import java.util.Stack;

import org.junit.Test;

/**
 * 由两个栈组成的队列：TwoStackQueue【2】
 * 
 * 【编写一个类，用两个栈实现队列，支持队列的基本操作(add、poll、peek)】
 * 
 * 设计思路：栈-先进后出，队列-先进先出。用两个栈把顺序反过来。
 * 
 * stackPush只管进栈，stackPop只管出栈且【仅当】其为空时，将stackPush的元素【全部】转移到stackPop。
 * 
 * @author xiaofan
 */
public class TwoStackQueue {
    private Stack<Integer> stackPush;

    private Stack<Integer> stackPop;

    public TwoStackQueue() {
        this.stackPush = new Stack<Integer>();
        this.stackPop = new Stack<Integer>();
    }

    public void add(int e) {
        this.stackPush.push(e);
    }

    public int poll() {
        tranfer();
        return this.stackPop.pop();
    }

    public int peek() {
        tranfer();
        return this.stackPop.peek();
    }

    private void tranfer() {
        if (this.stackPop.empty()) {
            if (this.stackPush.isEmpty()) { // isEmpty是Stack继承的Vector的方法
                throw new RuntimeException("Your queue is empty.");
            }
            while (!this.stackPush.empty()) { // empty是Stack自带的方法
                this.stackPop.push(this.stackPush.pop());
            }
        }
    }

    /////// 测试方法//////
    @Test
    public void test() {
        TwoStackQueue queue = new TwoStackQueue();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(3);
        queue.add(4);
        System.out.println("peek:" + queue.peek());
        while (true) { // 未重写empty方法，只能这样遍历
            try {
                System.out.println(queue.poll());
            } catch (Exception e) {
                break;
            }
        }
        TwoStackQueue queue1 = new TwoStackQueue();
        queue1.peek(); // java.lang.RuntimeException: Your queue is empty.
    }
}

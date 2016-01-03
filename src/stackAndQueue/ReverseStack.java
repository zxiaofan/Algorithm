/*
 * 文件名：ReverseStack.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ReverseStack.java
 * 修改人：xiaofan
 * 修改时间：2016年1月3日
 * 修改内容：新增
 */
package stackAndQueue;

import java.util.Stack;

import org.junit.Test;

/**
 * 仅用递归函数和栈逆序一个栈：ReverseStack【2】
 * 
 * 【一个栈依次压入1、2、3，将栈转置，使栈顶到栈底依次是1、2、3，只能用递归函数，不能借用额外的数据结构包括栈】
 * 
 * 算法思想：两个递归函数（getAndRemoveBottom、reverse）
 * 
 * @author xiaofan
 */
public class ReverseStack {
    /**
     * 返回并移除当前栈底元素（栈内元素<栈底>1、2、3<栈顶>变为2、3<栈顶>）.
     */
    private int getAndRemoveBottom(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.empty()) {
            return result;
        } else {
            int bottom = getAndRemoveBottom(stack);
            stack.push(result);
            return bottom; // 第一轮时，返回栈底元素1
        }
    }

    /**
     * 每层递归取出栈底的元素并缓存到变量中，直到栈空；
     * 
     * 然后逆向将每层变量压入栈，最后实现原栈数据的逆序。
     * 
     * @param stack
     */
    public void reverse(Stack<Integer> stack) {
        if (stack.empty()) {
            return;
        }
        int i = getAndRemoveBottom(stack); // 依次返回1、2、3
        reverse(stack);
        stack.push(i); // 依次压入3、2、1
    }

    ///////// 测试方法////////
    @Test
    public void test() {
        Stack stack = new Stack(); // Stack继承Vector，默认容量是10
        stack.push(1);
        stack.push(2);
        stack.push(3);
        ReverseStack rStack = new ReverseStack();
        rStack.reverse(stack);
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}

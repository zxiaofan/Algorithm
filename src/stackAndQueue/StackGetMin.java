package stackAndQueue;

import java.util.Stack;

import org.junit.Test;

/**
 * 
 * 设计一个有getMin功能的栈：StackGetMin【1】.
 * 
 * 【实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返回栈中最小元素的操作】
 * 
 * 要求：1、pop、push、getMin操作的时间复杂度都是O(1);2、设计的栈类型可以使用现成的栈结构。
 * 
 * 设计思路：两个栈-普通栈+getMin栈
 * 
 * @author xiaofan.
 */
public class StackGetMin {
    private Stack<Integer> stackData;

    private Stack<Integer> stackGetMin;

    /**
     * 构造函数.
     */
    public StackGetMin() {
        this.stackData = new Stack<Integer>();
        this.stackGetMin = new Stack<Integer>();
    }

    public void push(int item) { // 压入稍省空间
        this.stackData.push(item);
        if (this.stackGetMin.isEmpty()) {
            this.stackGetMin.push(item);
        } else if (item <= this.getMin()) { // 小于等于:避免插入多个最小值再弹出后，栈中无最小值
            this.stackGetMin.push(item);
        }
    }

    public int pop() { // 弹出稍费时间
        if (this.stackData.isEmpty()) { // 判空
            throw new RuntimeException("Your Stack is empty.");
        }
        int value = this.stackData.pop();
        if (value == this.getMin()) { // 两个栈同步弹出
            this.stackGetMin.pop();
        }
        return value;
    }

    public int getMin() {
        if (this.stackGetMin.isEmpty()) {
            throw new RuntimeException("Your Stack is empty.");
        }
        return this.stackGetMin.peek();
    }

    //////////// 测试类/////////////////
    @Test
    public void test() {
        StackGetMin myStack = new StackGetMin();
        myStack.push(2);
        myStack.push(5);
        myStack.push(4);
        myStack.push(1);
        myStack.push(1);
        System.out.println(myStack.pop());
        System.out.println(myStack.getMin());
        StackGetMin myStack1 = new StackGetMin();
        myStack1.pop(); // RuntimeException("Your Stack is empty.")
    }
    ///////// 方案2//////
    // 如果StackGetMin栈顶元素比比newNum小，则将栈顶元素重复压入
    // 压入稍费空间，弹出稍省时间
}

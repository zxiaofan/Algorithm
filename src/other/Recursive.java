/*
 * 文件名：Recursive.java
 * 版权：Copyright 2007-2016 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Recursive.java
 * 修改人：xiaofan
 * 修改时间：2016年1月3日
 * 修改内容：新增
 */
package other;

/**
 * 再来理解递归函数recursive
 * 
 * http://note.youdao.com/share/?id=2b45160bbca2e9cf946ded3ffab08e32&type=note
 * 
 * @author xiaofan
 */
public class Recursive {

    public static void main(String[] args) {
        binary(11);
    }

    private static void binary(int i) {
        if (i / 2 != 0) {
            binary(i / 2);
        } // 只是if，不是if()else{}
        System.out.print(i % 2);
    }
}
// output：1011
// 我们来看一下其字节码：
// public class other.Recursive {
// public other.Recursive();
// Code:
// 0: aload_0
// 1: invokespecial #8 // Method java/lang/Object."<init>":()V
// 4: return
//
// public static void main(java.lang.String[]);
// Code:
// 0: bipush 11
// 2: invokestatic #16 // Method binary:(I)V
// 5: return
// }
// 我们来解释一下上面的字节码含义：
// bipush：将一个byte型常量值推送至栈顶；
// invokestatic ：调用静态方法；
// aload_0：该指令的行为类似于aload指令index为0的情况；
// aload index： 当前frame的局部变量数组中下标为index的引用型局部变量进栈；
// return：当前方法返回void。
//
// 执行过程：
// if (i / 2 != 0)为真，则继续递归binary，执行完if之后，将输出语句 System.out.print(i % 2) 入栈；
// 递归一直到if()为假时，递归结束。此时开始执行出栈，栈是先进后出，所以先输出最后执行的i=2时的System.out.print(i % 2)。
//
// Note：
// 递归函数每次调用时，都会创建一批新的变量（将旧的变量隐藏），也会执行一次入栈。
// 一旦你理解了递归，阅读递归函数最容易的方法不是纠缠于它的执行过程，而是相信递归函数会顺利完成它的任务。如果你的每个步骤正确无误，你的限制条件设置正确，并且每次调用之后更接近限制条件，递归函数总是能正确的完成任务。
// 但是，为了理解递归的工作原理，你需要追踪递归调用的执行过程，所以让我们来进行这项工作。追踪一个递归函数的执行过程的关键是理解函数中所声 明的变量是如何存储的。当函数被调用时，它的变量的空间是创建于运行时堆栈上的。以前调用的函数的变量扔保留在堆栈上，但他们被新函数的变量所掩盖，因此 是不能被访问的。
// 当递归函数调用自身时，情况于是如此。每进行一次新的调用，都将创建一批变量，他们将掩盖递归函数前一次调用所创建的变量。当我追踪一个递归函数的执行过程时，必须把分数不同次调用的变量区分开来，以避免混淆。
//
// 参考资料：《Thinking In Algorithm》09.彻底理解递归

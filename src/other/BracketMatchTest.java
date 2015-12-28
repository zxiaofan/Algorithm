package other;

import java.util.Stack;

/*
 * 文件名：BracketMatch.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： BracketMatch.java
 * 修改人：yunhai
 * 修改时间：2015年9月22日
 * 修改内容：新增
 */

/**
 * 括号匹配算法-栈。
 * 
 * 判断3种括号是否匹配.
 * 
 * @author yunhai
 */
public class BracketMatchTest {
    /**
     * @param args
     *            参数
     */
    public static void main(String[] args) {
        String str = "([[]()])";
        BracketMatchTest bMatch = new BracketMatchTest();
        System.out.println(bMatch.bracketMatch(str));
        System.out.println(bMatch.bracketMatch("({)}"));
        System.out.println(bMatch.bracketMatch("(3{12})"));
    }

    /**
     * 匹配算法.
     * 
     * @param str
     *            参数
     * @return bool
     */
    public boolean bracketMatch(String str) {
        Stack<Character> st = new Stack<Character>();
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            switch (ch) { // 左括号全部入栈
                case '(':
                case '[':
                case '{':
                    st.push(ch);
                    break;
                case ')': // 右括号且匹配栈顶左括号，则出栈
                    if (!st.isEmpty() && st.pop() == '(') {
                        break;
                    } else {
                        return false;
                    }
                case ']':
                    if (!st.isEmpty() && st.pop() == '[') {
                        break;
                    } else {
                        return false;
                    }
                case '}':
                    if (!st.isEmpty() && st.pop() == '{') {
                        break;
                    } else {
                        return false;
                    }
                default:
                    break;
            }
        }
        return st.isEmpty();// 栈为空，则为true，括号完全匹配
    }
}
// 输出结果;true false true
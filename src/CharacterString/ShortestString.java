package CharacterString;

import java.util.HashMap;

/*
 * 文件名：ShortestString.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： ShortestString.java
 * 修改人：yunhai
 * 修改时间：2015年11月11日
 * 修改内容：新增
 */

/**
 * 最短子串
 * 
 * 在一个长字符串str1中找出其中一段str2，此段包含某个字符集合如{a,b,c}(集合通常包括str中的所有字符)的所有字符，并且这段字符串要最短。
 * 
 * 长字符串分是否首位相连。
 * 
 * 引申：一串首尾相连的珠子(m个)，有N种颜色(N<=10)，设计一个算法，取出其中一段，要求包含所有N中颜色，并使长度最短。
 * 
 * @author yunhai
 */
/**
 * 算法思想：可以定义一个head和一个tail标识子字符串的头和尾。
 * 
 * 【根据小字符串的词频建立hash表，表示大字符串欠小字符串各个字符的数量】定义一个数组bitMap[]，记录集合中各字符出现的次数， 之所以数组大小是256，是要用ASCII码(共256个)来标识字符。
 * 
 * 遍历大字符串，刚开始head=tail=0，head、tail构成窗口。
 * 
 * 定义变量t=str2.length，表示str1需要有效“还”数据的个数，map从2变1或1变0即为有效。
 * 
 * 当t=0时，收缩左边界直到map由0变1（t也0变1），记录此时边界。
 * 
 * head++，tail++，再次找使t=0的区间，记录其边界。从中取最优。
 * 
 * 首尾不相连：当tail==str2.length-1时算法结束.
 * 
 * 首尾相连：当tail>=m时，tail=tail%m，当head为m时算法结束.
 * 
 * @param str1
 *            大字符串.
 * @param str2
 *            小串.
 */
public class ShortestString {
    static String str1 = "iwlloliiiweewlooo";

    static String str2 = "liwo"; // 字符集合(可能重复)

    static int bestH = 0;

    static int bestT = str1.length();

    public static void main(String[] args) {
        int n = getNumOfDifference(str1);// 不同字符个数【即还款个数】，通常题目会告知，eloiw
        findShortest(str1, str2);
        System.out.println("bestHead=" + bestH + ",bestTail=" + bestT);
    }

    private static void findShortest(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int head = 0, tail = 0;
        int len = str1.length(); // 最优窗口下标
        int t = str2.length();// 需要"有效"还的数据个数。
        int[] bitMap = new int[256];
        for (int i = 0; i < char2.length; i++) {
            bitMap[char2[i]]++; // char变为int，即ASCII码,作为数组下标
        }
        // while (head < len) { // 首尾相连,则len取余
        // bitMap[char1[tail % len]]--;
        // if (bitMap[char1[tail % len]] >= 0) {
        while (tail < len) {
            bitMap[char1[tail]]--;// 不断归还，bitMap可能为负(多还)
            if (bitMap[char1[tail]] >= 0) { // 是否为有效归还
                t--;
            }
            if (t == 0) { // 都还完了
                while (bitMap[char1[head]] < 0) { // 若多还了
                    bitMap[char1[head++]]++;
                    // if (head == len) {//首尾相连时,head++后可能越界。
                    // return; //break只会跳出while循环,下方char1[head++]处将报错
                    // }
                }
                if (tail - head < bestT - bestH) {
                    // if ((tail + len - head) % len < bestT - bestH) {
                    bestH = head;
                    bestT = tail;
                }
                bitMap[char1[head++]]++; // 继续找新的区间，欠当前head处一个数据
                t++;
            }
            tail++; // tail %= len; // 首尾相连
        }
    }

    private static int getNumOfDifference(String str) {
        char[] ch = str.toCharArray();// 亦可用charAt
        int num = 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : ch) {
            if (!map.containsKey(c)) {
                map.put(c, 1);
                num++;
            }
        }
        return num;
    }
}

// http://www.nowcoder.com/discuss/2968?pos=156&type=0&order=0
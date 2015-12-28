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
 * 最短字符串。
 * 
 * 在一个长字符串str1中找出其中一段str2，此段包含某个字符集合如{a,b,c}(集合通常包括str中的所有字符)的所有字符，并且这段字符串要最短。
 * 
 * 长字符串分是否首位相连。
 * 
 * 引申：一串首尾相连的珠子(m个)，有N种颜色(N<=10)，设计一个算法，取出其中一段，要求包含所有N中颜色，并使长度最短。
 * 
 * @author yunhai
 */
public class ShortestString {

    public static void main(String[] args) {
        String str1 = "elloliiwilleeweoio";
        String str2 = "liwo"; // 字符集合
        int n = getNumOfDifference(str1);// 不同字符个数，通常题目会告知，eloiw
        findShortest(str1, str2);
    }

    /**
     * 算法思想：可以定义一个head和一个tail标识子字符串的头和尾。
     * 
     * 定义一个数组map[]，记录集合中的字符出现的次数，之所以数组大小是256，是要用ASCII码(共256个)来标识字符。
     * 
     * 刚开始head=tail=0，tail++直到字符集合中所有的字符数都不为0为止，然后head++直到字符集合中某个字符的数变为0，这是便得到一个字符段。
     * 
     * 首尾不相连：当tail==str2.length-1时算法结束.
     * 
     * 首尾相连：当tail>=m时，tail=tail %m，当head为m时算法结束.
     * 
     * @param str
     *            原字符串.
     * @param n
     *            不同字符个数.
     */
    private static void findShortest(String str1, String str2) {
        char[] char1 = str1.toCharArray();
        char[] char2 = str2.toCharArray();
        int head = 0, tail = 0;
        int[] map = new int[256];
        for (int i = 0; i < char2.length; i++) {
            map[char2[i]]++; // char变为int，即ASCII码,作为数组下标
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
// http://blog.csdn.net/godcupid/article/details/6557544
// http://blog.csdn.net/hai8902882/article/details/8299995
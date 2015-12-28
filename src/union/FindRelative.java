package union;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * 文件名：Union_Find.java
 * 版权：Copyright 2007-2015 517na Tech. Co. Ltd. All Rights Reserved. 
 * 描述： Union_Find.java
 * 修改人：yunhai
 * 修改时间：2015年9月30日
 * 修改内容：新增
 */

/**
 * 找亲戚（并查集显然很方便，但此文暂未用到并查集，后续更新）
 * 
 * n个人，m组亲戚关系，查找person1有多少个亲戚
 * 
 * 规定：x和y是亲戚，y和z是亲戚，那么x和z也是亲戚。如果x,y是亲戚，那么x的亲戚都是y的亲戚，y的亲戚也都是x的亲戚。
 * 
 * Input
 * 
 * 1、第一行：2个整数n,m（n< =5000,m< =5000,p< =5000），分别表示询问n的亲戚个数，共有m组亲戚关系。 以下m行：每行两个数Mi，Mj，1< =Mi，Mj< =N，表示Mi和Mj具有亲戚关系。
 * 
 * 2、接下来格式如1，直至输入n、m均为0时输入结束。
 * 
 * Output
 * 
 * person n的亲戚个数
 * 
 * 例如：
 * 
 * Input：
 * 
 * 1 3
 * 
 * 1 2
 * 
 * 3 4
 * 
 * 5 4
 * 
 * 3 2
 * 
 * 1 2
 * 
 * 1 3
 * 
 * 0 0
 * 
 * Output:
 * 
 * 1
 * 
 * 2
 * 
 * * @author yunhai
 */
public class FindRelative {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();// 查询n的亲戚个数
        int m = sc.nextInt();// m组亲戚关系
        while (n != 0 && m != 0) {
            ArrayList<ArrayList> listAll = new ArrayList<ArrayList>();
            for (int i = 0; i < m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                Union(listAll, x, y);
            }
            for (ArrayList listTemp : listAll) {
                if (listTemp.contains(n)) {
                    System.out.println("person " + n + " 有" + (listTemp.size() - 1) + "个亲戚");
                }
            }
            n = sc.nextInt();// 查询n的亲戚个数
            m = sc.nextInt();// m组亲戚关系
        }
        System.exit(0);// 结束程序
    }

    private static int find(ArrayList<ArrayList> listAll, int x) {// 查询listAll中是否存在x，存在则返回x所在list的下标
        int r = -1;
        for (ArrayList listTemp : listAll) {
            if (listTemp.contains(x)) {
                r = listAll.indexOf(listTemp);
            }
        }
        return r;
    }

    private static void Union(ArrayList<ArrayList> listAll, int x, int y) {// 将x、y放入list
        int tx = find(listAll, x);
        int ty = find(listAll, y);
        if (tx == -1 && ty == -1) {// x、y都不存在，新建一个list存放并加入到listAll中
            ArrayList list = new ArrayList();
            list.add(x);
            list.add(y);
            listAll.add(list);
        } else if (tx != ty && tx != -1 && ty != -1) {// x、y都存在，且x、y不在同一个list
            int ttx = listAll.get(tx).size();
            int tty = listAll.get(ty).size();
            if (tty > ttx) {// 将元素少的剪切到元素多的
                for (int i = 0; i < ttx; i++) {
                    listAll.get(ty).add(listAll.get(tx).get(i));
                }
                listAll.remove(tx);
            } else {
                for (int i = 0; i < tty; i++) {
                    listAll.get(tx).add(listAll.get(ty).get(i));
                }
                listAll.remove(ty);
            }
        } else if (tx != -1) {// x存在于list，y不存在于list，将y加入x对应的list
            listAll.get(tx).add(y);
        } else {// else ty != -1
            listAll.get(ty).add(x);
        }
    }
}

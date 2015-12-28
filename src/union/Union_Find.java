package union;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * 找亲戚（并查集）
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
public class Union_Find {
    static int MAXN = 12; // 结点数目上限,常取大值

    static int pa[] = new int[MAXN]; // pa[x]表示x的父节点

    static int rank[] = new int[MAXN]; // rank[x]是x的高度

    static int[][] a = {{1, 2}, {4, 5}, {3, 8}, {2, 5}, {6, 7}, {6, 8}, {9, 10}};

    static int n = 10;// 总人数

    static int m = a.length;;// 关系数目

    public static void main(String[] args) {

        for (int i = 0; i < n; i++) {
            make_set(i);
        }
        for (int i = 0; i < m; i++) {
            int x = a[i][0];
            int y = a[i][1];
            union_set(x, y);
        }
        int xx = 1, yy = 7;
        System.out.println(xx + "和" + yy + "是否在同一个集合:" + find_set(xx) + " " + find_set(yy) + (find_set(xx) == find_set(yy) ? true : false));
        print();
    }

    // 创建一个单元集
    private static void make_set(int i) {
        pa[i] = i;
        rank[i] = 0;
    }

    // 带路径压缩的查找父节点
    private static int find_set(int x) {
        if (x != pa[x])// x的父节点不是x本身
            pa[x] = find_set(pa[x]);
        return pa[x];
    }

    // 按秩合并x，y所在的集合
    private static void union_set(int x, int y) {
        x = find_set(x);
        y = find_set(y);
        if (rank[x] > rank[y]) {// 让rank较高的作为父结点
            pa[y] = x;
        } else
            pa[x] = y;
        if (rank[x] == rank[y]) {
            rank[y]++;
        }
    }

    private static void print() {
        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int i = 0; i < m; i++) {
            int x = a[i][0];
            int y = a[i][1];
            if (!map.containsKey(x)) {
                map.put(x, find_set(x));
            }
            if (!map.containsKey(y)) {
                map.put(y, find_set(y));
            }
        }
        // 按value排序
        // 这里将map.entrySet()转换成list
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(map.entrySet());
        // 然后通过比较器来实现排序
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            // 升序排序
            public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        // for (Entry<Integer, Integer> mapping : list) {// 输出排序后结果
        // System.out.println(mapping.getKey() + ":" + mapping.getValue());
        // }

        System.out.print(list.get(0).getKey() + " ");
        int value2 = list.get(0).getValue();
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i).getKey();
            int value = list.get(i).getValue();
            if (value2 != value) {
                System.out.println();
                System.out.print(key + " ");
            } else {
                System.out.print(key + " ");
            }
            value2 = value;
        }
    }
}
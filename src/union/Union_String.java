package union;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * 编写一个Java应用程序，对于给定的一个字符串的集合，格式如：
 {aaa bbb ccc}， {bbb ddd}，{eee fff}，{ggg}，{ddd hhh}
 要求将其中交集不为空的集合合并，要求合并完成后的集合之间无交集，例如上例应输出：
 {aaa bbb ccc ddd hhh}，{eee fff}， {ggg}
 */
public class Union_String {
    private static int[] elements;

    private static Map<String, Integer> map = new HashMap<String, Integer>();

    public static void main(String[] args) {
        String[][] a = {{"aaa", "bbb", "ccc"}, {"bbb", "ddd"}, {"eee", "fff"}, {"ggg"}, {"ddd", "hhh"}};
        make_set(a);
        union(a);
        print();
    }

    // 创建一个单元集
    static void make_set(String[][] a) {
        int count = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (map.get(a[i][j]) == null) {
                    map.put(a[i][j], count++);
                }
            }
        }
        elements = new int[count];
        for (int i = 0; i < count; i++) {
            elements[i] = i;
        }
    }

    // 将相邻元素所在的集合合并
    static void union(String[][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length - 1; j++) {
                int n1 = elements[map.get(a[i][j])];
                int n2 = elements[map.get(a[i][j + 1])];
                for (int k = 0; k < elements.length; k++) {
                    if (elements[k] == n2) {
                        elements[k] = n1;
                    }
                }
            }
        }
    }

    // 根据elements中的结果将元素合并并打印
    static void print() {
        HashMap<Integer, List<String>> results = new HashMap<Integer, List<String>>();
        // 迭代map找到字符串和对应的下标
        for (Iterator<Entry<String, Integer>> it = map.entrySet().iterator(); it.hasNext();) {
            Entry<String, Integer> entry = it.next();
            String s = entry.getKey();
            int collectionNumber = elements[entry.getValue()]; // 所属的集合
            if (results.get(collectionNumber) == null) {
                results.put(collectionNumber, new ArrayList<String>());
            }
            List<String> list = results.get(collectionNumber);
            list.add(s);
        }
        for (Iterator<List<String>> it = results.values().iterator(); it.hasNext();) {
            List<String> list = it.next();
            for (String s : list) {
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
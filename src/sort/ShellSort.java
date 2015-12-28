package sort;

/**
 * 希尔排序ShellSoet
 * 
 * 先取一个小于n的整数d1作为第一个增量，把文件的全部记录分成d1个组。
 * 
 * 所有距离为dl的倍数的记录放在同一个组中。先在各组内进行直接插人排序；
 * 
 * 然后，取第二个增量d21重复上述的分组和排序，直至所取的增量d t=1(d tt-l<… 21)，即所有记录放在同一组中进行直接插入排序为止。
 * 
 * 该方法实质上是一种分组插入方法。
 * 
 * @author yunhai
 */
public class ShellSort {
    public static void main(String[] args) {
        int source[] = new int[]{49, 38, 65, 97, 76, 13, 27, 49, 55, 4, 6};
        System.out.print("排序前：\t");
        printArray(source);
        shellSort(source);
        System.out.print("排序后：\t");
        printArray(source);
    }

    private static void shellSort(int[] source) {
        int j;
        for (int gap = source.length / 2; gap > 0; gap /= 2) { // gap 为增长序列，递减至1【亦可自定义增长序列】
            for (int i = gap; i < source.length; i++) { // 【直接插入排序】，从第一个gap处向后移,直至移到最后一个数
                int temp = source[i];// 当前gap处的值
                for (j = i; j >= gap && temp < source[j - gap];) {// 最后一个数如果是第一个gap的倍数，按理应第一次循环，但却最后循环
                    source[j] = source[j - gap]; // 较大数往后移
                    j -= gap; // 亦可放在循环体内
                }
                source[j] = temp; // 跳出循环意味着temp的位置已确定
            }
            System.out.print("增长序列" + gap + ": ");
            printArray(source);
        }
    }

    private static void printArray(int[] source) {
        for (int i = 0; i < source.length; i++) {
            System.out.print(source[i] + ",");
        }
        System.out.println();
    }
}

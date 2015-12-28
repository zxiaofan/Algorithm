package sort;

/**
 * 冒泡排序
 * 
 * @author yunhai
 *
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] source = {100, 24, 19, 26, 39, 36, 7, 31, 29, 38};
        System.out.println("排序前：");
        printArray(source);
        System.out.println("");
        bubbleSort3(source);
        System.out.println("排序后：");
        printArray(source);
    }

    public static void bubbleSort(int[] source) {
        int length = source.length;
        for (int i = 0; i < length - 1; i++) { // N个数需N-1趟,每趟完成之后,较大元素将冒到数组最末端
            for (int j = 0; j < length - 1 - i; j++) { // 每趟需要比较N-i次比较
                if (source[j] > source[j + 1]) {
                    swap(source, j, j + 1);
                }
                // System.out.print("\n外循环第" + (i + 1) + "次,内循环第" + (j + 1) + "次,排序结果：");
                // printArray(source);
            }
            System.out.println("");
        }
    }

    /**
     * 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。
     * 
     * 由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可。
     */
    public static void bubbleSort2(int[] source) {
        int high = source.length - 1;
        while (high > 0) { // high=0时证明最后一次进行交换的位置为0
            int pos = 0;// 每趟开始，无记录交换
            for (int i = 0; i < high; i++) {
                if (source[i] > source[i + 1]) {
                    swap(source, i, i + 1);
                    pos = i; // 有交换则令pos=i
                }
            }
            high = pos; // 每趟for循环结束，pos、length变更一次
            // System.out.println(high);
        }
    }

    /**
     * 每趟排序中进行正向和反向两遍冒泡的方法一次可以得到两个最终值(最大值和最小值),
     * 
     * 从而使排序趟数几乎减少了一半。
     */
    public static void bubbleSort3(int[] source) {
        int low = 0;
        int high = source.length - 1;
        while (low < high) {
            for (int i = low; i < high; i++) { // 正向找最大
                if (source[i] > source[i + 1]) {
                    swap(source, i, i + 1);
                }
            }
            high--; // 一次for循环结束，最大数冒出
            for (int i = high; i > low; i--) { // 逆向找最小
                if (source[i] < source[i - 1]) {
                    swap(source, i, i - 1);
                }
            }
            low++;
        }
    }

    private static void swap(int[] source, int x, int y) {
        int temp = source[x];
        source[x] = source[y];
        source[y] = temp;
    }

    private static void printArray(int[] source) {
        for (int i = 0; i < source.length; i++) {
            System.out.print("\t" + source[i]);
        }
    }
}
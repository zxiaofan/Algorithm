package sort;

public class StraightInsertSort {

    public static void main(String[] args) {
        int[] source = {99, 53, 27, 36, 15, 69, 42, 66};
        printArr(source);
        StraightInsertSort(source);
        printArr(source);
    }

    /**
     * 将一个记录插入到已排序好的有序表中，从而得到一个新的记录数增1的有序表。 即：先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。 要点：设立哨兵，作为临时存储和判断数组边界之用。
     */
    private static void StraightInsertSort(int[] source) {
        if (source.length <= 1 || source == null) {
            return;
        }
        int j;
        for (int i = 1; i < source.length; i++) { // 从第二个数开始，依次直接插入
            j = i;// 亦可只用一个变量i，但会增加比较次数
            while (j > 0 && source[j] < source[j - 1]) {// 位置不合适则交换
                source[j] = source[j - 1] + (source[j - 1] = source[j]) * 0;
                j--;
            }
            printArr(source);
        }
    }

    private static void printArr(int[] source) {
        for (int i : source) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

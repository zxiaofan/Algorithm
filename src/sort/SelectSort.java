package sort;

public class SelectSort {

    public static void main(String[] args) {
        int[] source = {99, 53, 27, 36, 15, 69, 42, 66};
        printArr(source);
        TwoSelectSort(source);
        printArr(source);
    }

    /**
     * 在要排序的一组数中，选出最小（或者最大）的一个数与第 i（i=0）个位置的数交换； 然后在剩下的数当中再找最小（或者最大）的与第i+1个位置的数交换， 依次类推，直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止。
     */
    private static void SimpleSelectSort(int[] source) {
        if (source.length <= 1 || source == null) {// 习惯，参数判断
            return;
        }
        for (int i = 0; i < source.length - 1; i++) { // i < source.length尚可
            int j = i + 1;
            int min = source[i]; // 最小值
            int minIndex = i; // 最小值下标
            while (j < source.length) {
                if (source[j] < min) {
                    min = source[j];
                    minIndex = j;
                }
                j++;
            }
            if (minIndex != i) { // 3次赋值
                source[i] = source[minIndex] + (source[minIndex] = source[i]) * 0;
            }
            printArr(source);
        }
    }

    /**
     * 简单选择排序，每趟循环只能确定一个元素排序后的定位。 我们可以考虑改进为每趟循环确定两个元素（当前趟最大和最小记录）的位置。 从而减少排序所需的循环次数。改进后对n个数据进行排序，最多只需进行[n/2]趟循环即可。
     */
    private static void TwoSelectSort(int[] source) {
        int n = source.length;
        if (n <= 1 || source == null) {// 习惯，参数判断
            return;
        }
        int minIndex, maxIndex, tempI;
        for (int i = 0; i < n / 2; i++) {
            minIndex = maxIndex = i;
            for (int j = i + 1; j < n - i; j++) {
                if (source[j] < source[minIndex]) {
                    minIndex = j;
                    continue;
                }
                if (source[j] > source[maxIndex]) {
                    maxIndex = j;
                }
            }
            if (minIndex != i) { // 3次赋值
                source[i] = source[minIndex] + (source[minIndex] = source[i]) * 0;
            }
            if (maxIndex == i) { // 此时最大值已被替换到minIndex处
                maxIndex = minIndex;
            }
            if (maxIndex != n - i - 1) {
                source[n - i - 1] = source[maxIndex] + (source[maxIndex] = source[n - i - 1]) * 0;
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

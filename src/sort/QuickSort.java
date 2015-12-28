package sort;

public class QuickSort {
    public static void main(String[] args) {
        int a[] = {3, 1, 5, 7, 2, 4, 9, 6, 10, 8};
        // int a[] = { 1, 2, 3, 4 };
        for (int i : a) {
            System.out.print(i + " ");
        }
        if (a.length > 0) { // 查看数组是否为空
            quickSort(a, 0, a.length - 1);
        }
        System.out.println();
        for (int i : a) {
            System.out.print(i + " ");
        }
    }

    /**
     * 一趟快速排序的算法是： 1）、设置两个变量I、J，排序开始的时候I：=1，J：=N；
     * 
     * 2）以第一个数组元素作为关键数据，赋值给X，即X：=A[1]；
     * 
     * 3）、从J开始向前搜索，即由后开始向前搜索（J：=J-1），找到第一个小于X的值，两者交换；
     * 
     * 4）、从I开始向后搜索，即由前开始向后搜索（I：=I+1），找到第一个大于X的值，两者交换；
     * 
     * 5）、重复第3、4步，直到I=J；
     */
    private static void quickSort(int[] list, int low, int high) {
        if (low < high) {// 不是while，否则死循环
            int middle = partition(list, low, high); // 将list数组进行一分为二
            quickSort(list, low, middle - 1); // 对低字表进行递归排序
            quickSort(list, middle + 1, high); // 对高字表进行递归排序
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int tmp = arr[low]; // 数组的第一个元素作为基准,用temp临时存储
        while (low < high) {// 从区间两端交替向中间扫描，直至low=high为止
            while (low < high && arr[high] >= tmp) {// high向前搜索,直到找到第一个比temp小的元素
                high--;
            }
            arr[low] = arr[high]; // 比temp小的(high)移到低端
            while (low < high && arr[low] <= tmp) {// low下标增至第一个比temp大的元素
                low++;
            }// while终止说明arr[low]>temp
            arr[high] = arr[low]; // 比temp大的(low)移到高端
        }
        // 当low == high，完成一趟快速排序，此时low位相当于空，等待基准temp补上
        arr[low] = tmp; // 基准记录已被最后定位
        return low; // 返回基准的位置【下标】<基准最终位置>
    }
}

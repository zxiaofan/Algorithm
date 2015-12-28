package sort;

public class MergingSort {
    public static void main(String[] args) {
        int[] a = {3, 2, 5, 4, 1};
        sort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public static void sort(int[] data, int left, int right) {
        if (left < right) {
            // 首先找出中间的索引
            int center = (left + right) / 2;
            // 对左边的数组进行递归,使左边有序
            sort(data, left, center);
            // 对中间索引右边的数组进行递归，使右边有序
            sort(data, center + 1, right);
            // 再将二个有序数列合并
            merge(data, left, center, right);
        }
    }

    public static void merge(int[] data, int left, int center, int right) {
        int[] tmpArr = new int[data.length];
        int mid = center + 1;
        // third记录中间数组的索引
        int third = left;
        int tmp = left;
        while (left <= center && mid <= right) {
            // 将两个数组中取出最小的数放入中间数组
            if (data[left] <= data[mid]) {
                tmpArr[third++] = data[left++];
            } else {
                tmpArr[third++] = data[mid++];
            }
        }
        // 剩余部分依次放入中间数组
        while (mid <= right) {
            tmpArr[third++] = data[mid++];
        }
        while (left <= center) {
            tmpArr[third++] = data[left++];
        }
        while (tmp <= right) {
            data[tmp] = tmpArr[tmp++];
        }
    }
}
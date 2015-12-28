package sort;

public class HeapSort {
	public static void main(String[] args) {
		int[] src = { 66, 89, 8, 123, 9, 44, 55, 37, 200, 127, 98 };
		System.out.println("初始值：");
		print(src);
		HeapSort(src, src.length);
		System.out.println("堆排后：");
		print(src);
	}

	/**
	 * 大顶堆排序算法
	 */
	private static void HeapSort(int src[], int length) {// 大顶堆排序算法
		// 初始化堆,从最后一个节点 i= (length-1) / 2
		for (int i = (length - 1) >> 1; i >= 0; --i)
			HeapAdjust(src, i, length);
		for (int i = length - 1; i > 0; --i) {// 从堆尾往前调整
			src[i] = src[0] + (src[0] = src[i]) * 0;// 弹出堆顶最大值，堆尾值填补
			HeapAdjust(src, 0, i);// 重新调整堆
		}
	}

	/**
	 * src[i+1,…. ,j]已经成堆，调整 i 的子树为堆.
	 * 
	 * @param src是待调整的堆数组
	 * @param i是待调整的数组元素的位置
	 * @param j是待调整数组的长度
	 */
	private static void HeapAdjust(int src[], int i, int j) {// 对下标为i的节点，调整其子树为堆
		int temp = src[i];
		int child = 2 * i + 1; // 左孩子的位置。(child+1 为当前调整结点的右孩子的位置)
		while (child < j) {// 防止第一次length为偶数12时，i=5与child=11非父子关系
			if (child + 1 < j && src[child] < src[child + 1]) { // 定位较大的的孩子结点
				++child;
			}
			if (src[i] < src[child]) { // 如果较大的子结点大于父结点
				src[i] = src[child]; // 那么把较大的子结点往上移动，替换它的父结点
				i = child; // 更新i，以便使新子树为堆（子树结构可能改变）
				src[i] = temp; // 父结点放到比它大的孩子结点上（temp值未变）
				child = 2 * i + 1;// 若child<length，则继续循环建堆
			} else { // 如果当前待调整结点大于它的左右孩子，则不需要调整，直接退出
				break;// 防止死循环
			}
		}
		print(src);
	}

	private static void print(int a[]) {
		for (int i : a) {
			System.out.print(i + " ");
		}
		System.out.println();
	}
}

package javase.basic.dataStructure.base;

import java.util.Random;

import org.junit.Test;


/**
 * 注意越界检查
 *
 * @author ppf
 * @see <a href="https://www.cnblogs.com/onepixel/p/7674659.html">算法blog</a>
 * @since 2017年3月31日
 */
public class SortTest {

    private static int[] arr/* = { 2, 1, 3 } */;
    private static final int SIZE = 30;// 栈上分配

    @FunctionalInterface
    interface Sort {
        void sort();// invokedynamic 指令
    }


    public static int[] generateArray(int size) {
        int[] arr = new int[size];
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = r.nextInt(100);
        }
        return arr;
    }


    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    public static void runTest(Sort s) {
        arr = generateArray(SIZE);
        printArray(arr);
        // --execute--
        s.sort();
        printArray(arr);
    }


    // ===========================sorting_algorithm=========================================

    /**
     * 冒泡排序。
     *
     * @throws Exception
     */
    @Test
    public void bubble() {
        Sort bubble = () -> {
            int max;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < arr.length - i; j++) {
                    max = arr[j];
                    if (j + 1 < arr.length - i && arr[j + 1] < max) {
                        arr[j] = arr[j + 1];
                        arr[j + 1] = max;
                    }
                }
            }
        };
        runTest(bubble);
    }


    @Test
    public void select() {
        Sort select = () -> {
            // 遍历，找最大最小
            selectSort(0, SIZE - 1);
        };
        runTest(select);
    }

    int[] tmp = new int[SIZE];
    int limit, index2, index1, k = 0, gap, num;


    @Test
    public void merge() {
        Sort merge = () -> {
            // 分组 按数组长度分组，先2 2^2 2^4 直到 >length/2,依次合并数组
            for (gap = 1; arr.length / gap > 0; gap = gap << 1) {
                mergeSort();
            }
        };
        runTest(merge);
    }


    private void mergeSort() {
        // ==============每次划分多少个数组==============
        num = arr.length / gap;
        if (arr.length % gap != 0) {
            num = num + 1;
        }
        // ==============正常合并==============
        for (int i = 0; i < num; i += 2) {
            index1 = gap * i;
            index2 = gap * i + gap;
            limit = index2;
            copyValues(limit, Math.min(SIZE, limit + gap));
        }
        // ==============最后一个数组归并=================
        if (1 == num) {
            index2 += gap;
            limit = index2;
            copyValues(limit, arr.length);
        }
        // ==============交换数组=================
        int[] swap = arr;
        arr = tmp;
        tmp = swap;
        k = 0;// 还原临时数组
    }


    private void copyValues(int begin, int end) {
        while (index1 < begin && index2 < end) {
            if (arr[index1] > arr[index2]) {
                tmp[k++] = arr[index2++];
            } else if (arr[index1] < arr[index2]) {
                tmp[k++] = arr[index1++];
            } else {// 相等全部拷贝
                tmp[k++] = arr[index1++];
                tmp[k++] = arr[index2++];
            }
        }
        // 剩余元素
        if (index1 < begin) {
            System.arraycopy(arr, index1, tmp, k, limit - index1);
            k += limit - index1;
        } else if (index2 < end) {// 右边数组有剩余
            System.arraycopy(arr, index2, tmp, k, limit - index2 + gap);
            k += limit - index2 + gap;
        }
    }


    private void selectSort(int begin, int end) {
        int maxIndex = end, littleIndex = begin;
        for (int i = begin; i < end; i++) {
            int tmp = arr[i];
            if (tmp > arr[maxIndex]) {
                maxIndex = i;// 标注最大
            } else if (tmp < arr[littleIndex]) {
                littleIndex = i;// 标注最小
            }
        }
        // 交换最大最小
        swap(arr, maxIndex, end);
        swap(arr, littleIndex, begin);
        if (end - begin > 2)
            selectSort(begin + 1, end - 1);
    }


    @Test
    public void insert() {
        Sort insert = () -> {
            // 遍历元素并插入
            for (int i = 1; i < arr.length; i++) {
                int tmp = arr[i];
                // 查找插入位置
                int index = Search.binarySearch(arr, tmp, 0, i);
                // 后移,从最后开始
                for (int j = i; j > index - 1 && j > 0; j--) {
                    arr[j] = arr[j - 1];
                }
                // 插入合适位置
                arr[index] = tmp;
            }
        };
        runTest(insert);
    }


    @Test
    public void quick() {
        Sort quick = () -> {
            quickSort(0, SIZE - 1);
        };
        runTest(quick);
    }


    // =======================================static_tools==============================================

    /**
     * @param base  基数，最理想比较基准
     * @param range 误差范围，提高基数选择效率
     * @return 基数
     */
    public static int selectBase(int[] arr, int begin, int end, int base, int range) {
        int gap/* 差距求最小 */ = base, index/* 要求的基数 */ = 0;
        for (int j = begin; j < end; j++) {
            int k = arr[j];
            int newGap = Math.abs(base - k);
            if (newGap < range) {
                return j;
            }
            if (gap > newGap) {// 需要交换
                index = j;
                gap = newGap;
            }
        }
        return index;
    }


    private static void quickSort(int begin, int end) {
        int start = begin;
        int stop = end;
        int key = arr[begin];

        while (stop > start) {
            // 从后往前比较
            while (stop > start && arr[stop] >= key)
                stop--;
            if (arr[stop] <= key) {
                swap(arr, start, stop);
            }
            // 从前往后比较
            while (stop > start && arr[start] <= key)
                start++;
            if (arr[start] >= key) {
                swap(arr, start, stop);
            }
        }
        if (start > begin)
            quickSort(begin, start - 1);
        if (stop < end)
            quickSort(stop + 1, end);
    }


    public static void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }


    @Test
    public void heapify() {
        Sort heapify = () -> {
            //1.构建大顶堆
            for (int i = arr.length / 2 - 1; i >= 0; i--) {
                //从第一个非叶子结点从下至上，从右至左调整结构
                adjustHeap(arr, i, arr.length);
            }
            //2.调整堆结构+交换堆顶元素与末尾元素
            for (int j = arr.length - 1; j > 0; j--) {
                //将堆顶元素与末尾元素进行交换
                swap(arr, 0, j);
                //重新对堆进行调整
                adjustHeap(arr, 0, j);
            }
        };
        runTest(heapify);
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素i
        int temp = arr[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        arr[i] = temp;
    }


}

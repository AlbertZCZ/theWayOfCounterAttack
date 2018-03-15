package counter.attack.sort;

/**
 * Create by zhang on 2018/3/12
 * 堆排序：不适宜于记录数较少的文件
 */
public class HeapMaxSort {
    public void heapSort(int[] array) {
        if (array == null || array.length <= 0)
            return;
        //1.建最大堆
        buildMaxHeap(array);
        //2.调整堆：堆顶与堆的最后一个元素交换位置
        for (int i = array.length - 1;i > 1; i --) {
            //交换堆顶与最后一个元素
            swap(array,0,i);
            maxHeapAdjust(array,i,0);
        }
    }

    //根据输入数组构建一个最大堆
    private void buildMaxHeap(int[] array) {
        int half = array.length / 2;
        for (int i = half;i > 0;i --)
            //调整堆：堆顶与堆的最后一个元素交换位置
            maxHeapAdjust(array,array.length,i);
    }

    //调整堆：堆顶与堆的最后一个元素交换位置
    private void maxHeapAdjust(int[] array, int length, int i) {
        //左子节点索引
        int left = 2 * i + 1;
        //右子节点索引
        int right = 2 * i + 2;
        //最大节点索引
        int largest = i;
        //如果左子节点大于父节点，就将左子节点作为最大节点
        if (left < length && array[left] > array[largest])
            largest = left;
        //如果右子节点大于父节点，就将右节点作为最大节点
        if (right < length && array[right] > array[largest])
            largest = right;

        //最后，如果父节点和最大节点不一致，就交换他们的值
        if (largest != i) {
            swap(array,largest,i);
        }
        //交换了父节点和子节点的值，对换了值的子节点检查是否符合最大堆的特性
        maxHeapAdjust(array,length,largest);
    }

    private void swap(int[] array,int i,int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

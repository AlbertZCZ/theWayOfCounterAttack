package counter.attack.sort;

import java.util.Arrays;

/**快排思想
 * 对于一个数组，我们选定一个基准数据（例如：数组中的最后一个或者第一个元素），剩下的数据组成一个新的数组，然后遍历这个新数组中的每一个元素，
 * 分别与基准元素进行对比，分别将小于基准元素和不小于基准元素的数据区分开来，这个时候基准元素在总的数组中的位置就确定了。然后，
 * 在分别对这个两个数组进行相同的操作，直到每一个元素的位置都唯一确定下来。
 */
public class QuickSort {

    /**
     * 拆分数组
     *
     * @param arr   要拆分的数组
     * @param start 数组拆分的起始索引 （从0开始）
     * @param end   数组拆分的结束索引
     */
    public static int partition(int[] arr, int start, int end) {
        //选取基准元素，这里我们以最后一个位置，作为基准

        int base = arr[end];
        //记录，比基准元素小的变量，这里我们假设要比较的元素都不小于基准元素，这样通过比较
        //就把小于基准元素的数据全部找到，n=start表示的就是默认没有小于基准元素。
        int n = start;
        //基准元素不参与遍历比较

        for (int i = start; i < end; i++) {
            if (arr[i] < base) {
                //将小于基准元素的放到基准的左边
                if (i != n)//i与n相等就没必要交换位置
                    exchangeE(arr, i, n);
                n++;
            }
        }
        //遍历完成之后，将基准元素放到应该的位置上
        exchangeE(arr, n, end);
        return n;
    }

    /**
     * 交换数组中指定位置的两个元素
     *
     * @param arr 数组
     * @param n 位置1
     * @param end 位置2
     */
    private static void exchangeE(int[] arr, int n, int end) {
        int temp = arr[n];
        arr[n] = arr[end];
        arr[end] = temp;
    }

    /**
     * 根据拆分结果进行再次拆分
     * 由于原理一样，因此我们使用递归思想
     * @param arr
     * @param start
     * @param end
     */
    public static void recurPartiton(int[] arr,int start,int end){

        //递归调用的结束条件,开始要拆分的数组就剩下一个元素的时候
        if(end-start<1)
            return;
        int part = partition(arr, start, end);
        //三种情况下的继续拆分
        if(part==start)
            recurPartiton(arr,part+1,end);
        else if(part ==end)
            recurPartiton(arr,start,end-1);
        else {
            recurPartiton(arr,start,part-1);
            recurPartiton(arr,part+1,end);
        }
    }

    /***********封装测试*************/
    public static void main(String[] args) {
        int[] arr = {8, 2, 1, 4,6,7, 3, 5, 9, 6,11,19,13,55,67,32,22};
        quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr){

        recurPartiton(arr,0,arr.length-1);

    }

}

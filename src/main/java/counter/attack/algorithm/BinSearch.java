package counter.attack.algorithm;

/**
 * 二分查找
 * 需要有序数组！！！
 *
 */
public class BinSearch {
    /**
     * 二分查找普通实现。(非递归)
     * @param srcArray 有序数组(从小到大)
     * @param key 查找元素
     * @return  不存在返回-1
     */
    private static int binSearchun(int srcArray[], int low, int high, int key) {
        while(low <= high) {
            int mid = (low + high)/2;
            if (srcArray[mid] > key)
                high = mid - 1;
            else if (srcArray[mid] < key)
                low = mid + 1;
            else
                return mid;
        }
        return -1;
    }


    /**
     * 二分查找递归实现。
     * @param srcArray  有序数组
     * @param start 数组低地址下标
     * @param end   数组高地址下标
     * @param key  查找元素
     * @return 查找元素不存在返回-1
     */
    private static int binSearch(int srcArray[], int start, int end, int key) {
        int mid = (end + start) / 2;
        if (srcArray[mid] == key) {
            return mid;
        }
        if (start >= end) {
            return -1;
        }else if (key < srcArray[mid]) {
            return binSearch(srcArray,start,mid - 1,key);
        }else if (key > srcArray[mid]) {
            return  binSearch(srcArray,mid + 1,end,key);
        }
        return -1;
    }

    public static void main(String[] args) {
        int srcArray[] = {3,5,11,17,21,23,28,30,32,50,64,78,81,95,101};
        System.out.println(binSearch(srcArray, 0, srcArray.length - 1, 222));
        System.out.println(binSearchun(srcArray,0,srcArray.length - 1, 81));
    }
}

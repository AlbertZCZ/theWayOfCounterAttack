package counter.attack.algorithm;

/**
 * Create by zhang on 2017/12/22
 * 求一无序数组中第k大元素
 * 经典的Google搜索排名算法，可用的解决方法有：
 1）使用常规排序方法后找到数组中对应下标的值；时间复杂度为O(n*logn + k)。
 2）将数组内容存入一升序优先队列中，进行k-1次pop操作，那么队尾的元素就是第k大的数字；
 3）使用数组内容构建一个最大堆/最小堆，通过每次pop出堆顶后继续维护堆的结构，直到满足一定的次数（最大堆k-1次，最小堆size-k次），
 堆顶的元素就是第k大的数字，实现的效果与优先队列相同；
 4）利用快排的partition函数思想，选定一个数组内的值作为pivot，将小于pivot的数字放到pivot右边，大于等于pivot的数字放到pivot左边。
 接着判断两边数字的数量，如果左边的数量小于k个，说明第k大的数字存在于pivot及pivot右边的区域之内，对右半区执行partition函数；如果右边的数量小于k个，
 说明第k大的数字在pivot和pivot左边的区域之内，对左半区执行partition函数。直到左半区刚好有k-1个数，那么第k大的数就已经找到了。时间复杂度近似为O(n)
 */
public class KthLargest {
    public int kthLargestElement(int k, int[] nums) {
        return quickSelect(nums, 0, nums.length - 1, k);
    }

    /**
     * 利用快排思想
     * @param nums 数组
     * @param start 起始位置
     * @param end 结束位置
     * @param k 位置k
     * @return
     */
    private int quickSelect(int[] nums, int start, int end, int k) {
        int left = start, right = end;
        //选定一个数组内的值作为pivot
        int pivot = nums[(start + end) / 2];
        //将小于pivot的数字放到pivot右边，大于等于pivot的数字放到pivot左边
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;

                left++;
                right--;
            }
        }
        // 判断两边数字的数量，如果左边的数量小于k个，说明第k大的数字存在于pivot及pivot右边的区域之内，对右半区执行partition函数；
        // 如果右边的数量小于k个，说明第k大的数字在pivot和pivot左边的区域之内，对左半区执行partition函数。
        // 直到左半区刚好有k-1个数，那么第k大的数就已经找到了。
        if (start + k - 1 <= right) {
            return quickSelect(nums, start, right, k);
        }
        if (start + k - 1 >= left) {
            return quickSelect(nums, left, end, k - (left - start));
        }
        return nums[right + 1];
    }
}

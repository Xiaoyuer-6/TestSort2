import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: GaoBo
 * Date: 2021-01-31
 * Time: 12:17
 */
public class TestSort {

    /**
     * 时间复杂度：
     * 最坏情况下：当数据是无序的情况下：O(n^2)
     * 最好情况下：当数据有序的时候，可以达到O(n)
     *     所以：结论：越有序越快。
     *     题目：当前有一组待排序序列，但是这组待排序序列大部分是有序的。
     *         请问，下面那个排序更适合    直接插入排序。
     *     另外：直接插入排序一般也会用在一些排序的优化上。
     *          快速排序。
     * 空间复杂度：
     * 稳定性：稳定的排序
     * @param array
     */
    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int j = i-1;
            for (; j >= 0 ; j--) {
                //如果这里是一个大于等于号 此时这个排序就不稳定了
                if(array[j] > tmp) {
                    array[j+1] = array[j];
                }else {
                    //array[j+1] = tmp;
                    break;
                }
            }
            array[j+1] = tmp;
        }
    }


    public static void shell(int[] array ,int gap) {
        for (int i = gap; i < array.length; i++) {
            int tmp = array[i];
            int j = i-gap;
            for (; j >= 0 ; j = j-gap) {
                //如果这里是一个大于等于号 此时这个排序就不稳定了
                if(array[j] > tmp) {
                    array[j+gap] = array[j];
                }else {
                    //array[j+1] = tmp;
                    break;
                }
            }
            array[j+gap] = tmp;
        }
    }

    /**
     * 了解：
     * 时间复杂度：O(1.5)  O(n^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * @param array
     */
    public static void shellSort(int[] array) {
        int[] drr = {5,3,1};//增量数组-->   16   5     3     1
        for (int i = 0; i < drr.length; i++) {
            shell(array,drr[i]);
        }
    }


    /**
     * 选择排序：
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     * @param array
     */
    public static void selectSort(int[] array) {
        for (int i = 0; i < array.length-1; i++) {
            for (int j = i+1; j < array.length; j++) {
                if(array[j] < array[i]) {
                    int tmp = array[j];
                    array[j] = array[i];
                    array[i] = tmp;
                }
            }
        }
    }

    /**
     * 堆排序：
     * 时间复杂度：O(nlogn)
     * 空间复杂度：O(1)
     * 稳定性：不稳定
     */

    /**
     * 冒泡排序：
     * 时间复杂度：O(N^2)
     * 空间复杂度：O(1)
     * 稳定性：稳定
     */


    public static int pivot(int[] array,int start,int end) {
        int tmp = array[start];
        while (start < end) {
            while (start < end && array[end] >= tmp) {
                end--;
            }
            array[start] = array[end];
            //把数据赋值给start
            while (start < end && array[start] <= tmp) {
                start++;
            }
            //把start下标的值给end
            array[end] = array[start];
        }
        array[start] = tmp;
        return start;
    }




    public static void swap(int[] array,int k,int i) {
        int tmp = array[k];
        array[k] = array[i];
        array[i] = tmp;
    }
    public static void medianOfThree(int[] array,int low,int high) {
        int mid = (low+high)/2;
        //array[mid] <= array[low] <= array[end]
        if(array[low] < array[mid]) {
            swap(array,low,mid);
        }//array[mid] <= array[low]
        if(array[low] > array[high]) {
            swap(array,low,high);
        }//array[low] <= array[high]
        if(array[mid] > array[high]) {
            swap(array,mid,high);
        }//array[mid] <= array[high]
    }


    public static void insertSortBount(int[] array,int low,int high) {
        for (int i = low+1; i <= high; i++) {
            int tmp = array[i];
            int j = i-1;
            for (; j >= low ; j--) {
                if(array[j] > tmp) {
                    array[j+1] = array[j];
                }else {
                    break;
                }
            }
            array[j+1] = tmp;
        }
    }

    public static void quick(int[] array,int low,int high) {
        if(low >= high) return;

        if(high-low + 1 <= 50) {
            //使用插入排序
            insertSortBount(array,low,high);
            return;//记着这里一定要return  这里说明 这个区别范围有序了 直接结束
        }
        //if(low < high) {
            //这个之前进行优化
            medianOfThree(array,low,high);
            int piv = pivot(array,low,high);
            quick(array,low,piv-1);
            quick(array,piv+1,high);
        //}
    }

    /**
     * 时间复杂度： 最好情况：O(nlogn)      最坏情况有序的情况：O(N^2)
     * 空间复杂度：O(logn)                    O(N)
     * 稳定性：不稳定的排序
     *
     * 分治思想：什么时候效率最高      每次把待排序序列     均匀的划分
     *
     * 调 JVM 的栈参数的
     *
     * 快速排序的递归 及递归 的优化方式
     * @param array
     */
    public static void quickSort1(int[] array) {
        quick(array,0,array.length-1);
    }

    /**
     * 非递归：和递归是一样的
     *     nlogn    空间复杂度：log2n     O(n)
     *     15分钟  后开始上课
     * @param array
     */
    public static void quickSort(int[] array) {
        Stack<Integer> stack = new Stack<>();
        int low = 0;
        int high = array.length-1;
        int piv = pivot(array,low,high);//
        if(piv > low + 1) {
            stack.push(low);
            stack.push(piv-1);
        }
        if(piv < high-1) {
            stack.push(piv+1);
            stack.push(high);
        }
        while (!stack.empty()) {
            high = stack.pop();
            low = stack.pop();
            piv = pivot(array,low,high);//
            if(piv > low + 1) {
                stack.push(low);
                stack.push(piv-1);
            }
            if(piv < high-1) {
                stack.push(piv+1);
                stack.push(high);
            }
        }
    }

    public static void merge(int[] array,int start,int mid,int end) {
        int s1 = start;
        int s2 = mid+1;

        int[] tmp = new int[end-start+1];
        int k = 0;//tmp数组的下标

        while (s1 <= mid && s2 <= end) {
            if(array[s1] <= array[s2]) {
                tmp[k++] = array[s1++];
            }else{
                tmp[k++] = array[s2++];
            }
        }
        //有可能第一个段还有数据 有可能第2个段也有数据
        while (s1 <= mid) {
            tmp[k++] = array[s1++];
        }
        while (s2 <= end){
            tmp[k++] = array[s2++];
        }

        for (int i = 0; i < tmp.length; i++) {
            array[i+start] = tmp[i];
        }
    }

    public static void mergeSortInternal(int[] array,int low,int high) {
        if(low >= high) return;
        int mid = (low+high)/2;
        mergeSortInternal(array,low,mid);
        mergeSortInternal(array,mid+1,high);
        //合并的操作
        merge(array,low,mid,high);
    }

    /**
     * 时间复杂度：O(nlogn)  不管好坏   都是：O(nlogn)
     * 空间复杂度：O(n)
     * 稳定性：稳定的排序
     *
     * 堆排   归并    不管好坏   都是：O(nlogn)
     * 快排   最坏O(n^2)
     * k*n*logn
     *
     * @param array
     */
    public static void mergeSort2(int[] array) {
        mergeSortInternal(array,0,array.length-1);
    }


    public static void merge(int[] array,int gap) {

        int s1 = 0;
        int e1 = s1+gap-1;
        int s2 = e1+1;
        int e2 = s2+gap-1 < array.length ? s2+gap-1 : array.length-1;

        int[] tmp = new int[array.length];
        int k = 0;//下标

        //当有两个归并段的时候
        while (s2 < array.length) {
            //当当有两个归并段 且 这两个段内都要数据
            while (s1 <= e1 && s2<= e2) {
                if(array[s1] <= array[s2]) {
                    tmp[k++] = array[s1++];
                }else{
                    tmp[k++] = array[s2++];
                }
            }
            while (s1 <= e1) {
                tmp[k++] = array[s1++];
            }
            while (s2 <= e2){
                tmp[k++] = array[s2++];
            }
            //从这里开始的时候，每个下标都可能越界
            s1 = e2+1;//
            e1 = s1+gap-1;
            s2 = e1+1;
            e2 = s2+gap-1 < array.length ? s2+gap-1 : array.length-1;
        }

        //退出上面循环后，
        // 那么把s1段内的数据给拷贝下来,因为 有可能e1已经越界了
        while (s1 < array.length) {
            tmp[k++] = array[s1++];
        }

        for (int i = 0; i < tmp.length; i++) {
            array[i] = tmp[i];
        }

    }
    /**
     * 非递归实现归并排序：
     *
     * @param array
     *
     */
    public static void mergeSort(int[] array) {
        for (int i = 1; i < array.length; i*=2) {
            merge(array,i);
        }
    }


    public static void main2(String[] args) {
        int[] array = new int[10_0000];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            //array[i] = i;
            array[i] = random.nextInt(1_0000);
        }
        long startTime = System.currentTimeMillis();
        mergeSort(array);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime-startTime);
        System.out.println(Arrays.toString(array));
    }

    public static void main(String[] args) {
        int[] array = {10,3,2,7,19,78,65,127};
        System.out.println(Arrays.toString(array));
        mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

}

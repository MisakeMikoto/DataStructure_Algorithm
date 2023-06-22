import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author MisakiMikoto
 * @Date 2023/5/26
 */
public class One_2023_5_26 {
    public static void main(String[] args) {
/*        int a = 1, b = 2;
        swap(a, b);*/
        int[] a = generateRandomArray(100, 100);
//        mergeSort(a);
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i]+" ");
//        }
        a = new int[]{1, 3, 4, 2, 5};
        System.out.println(smallSum(a));
    }

    /**
     * 交换 a b 的值
     * @param a
     * @param b
     */
    public static void swap(int a, int b){
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = "+ a+ " b = " + b);
    }

    public static void swap(int[] arr, int L, int R){
        if(L != R){
            arr[L] = arr[L] ^ arr[R];
            arr[R] = arr[L] ^ arr[R];
            arr[L] = arr[L] ^ arr[R];
        }
    }

    public static int[] generateRandomArray(int maxValue, int maxSize){
        int[] a = new int[(int) ((maxSize + 1) * Math.random()) ];
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return a;
    }

    public static int getMax(int[] arr, int L, int R){
        if(L == R){
           return arr[L];
        }
        int mid = L + ((R - L) >> 1);
        int leftMax = getMax(arr, L, mid);
        int rightMax = getMax(arr, mid + 1, R);
        return Math.max(leftMax, rightMax);
    }

    public static void mergeSort(int[] arr){
        if(arr.length <= 1 || arr == null){
            return;
        }
        mergeSortProcess(arr, 0, arr.length - 1);
    }

    public static void mergeSortProcess(int[] arr, int L, int R){
        if(L == R){
            return;
        }
        int M = L + ((R - L) >> 1);
        mergeSortProcess(arr, L, M);
        mergeSortProcess(arr,M + 1, R);
        merge(arr, L, M, R);
    }

    public static void merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L, p2 = M + 1;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

    public static int smallSum(int[] arr){
        if(arr.length <= 1 || arr == null){
            return 0;
        }
        return smallSumProcess(arr, 0, arr.length - 1);
    }

    public static int smallSumProcess(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return smallSumProcess(arr, L, M) +
               smallSumProcess(arr, M + 1, R) +
               smallMerge(arr, L, M, R);
    }

    public static int smallMerge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L, p2 = M + 1;
        int smallSum = 0;
        while (p1 <= M && p2 <= R){
            smallSum += arr[p1] < arr[p2] ? arr[p1] * (R - p2 + 1) : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[L + i] = help[i];
        }
        return smallSum;
    }

    public static int reverseOrder(int[] arr){
        if(arr == null || arr.length <= 1){
            return 0;
        }

        return reverseOrderProcess(arr, 0, arr.length-1);
    }
    public static int reverseOrderProcess(int[] arr, int L, int R){
        if(L == R){
            return 0;
        }
        int M = L + ((R - L) >> 1);
        return reverseOrderProcess(arr, L, M) +
               reverseOrderProcess(arr, M + 1, R) +
               reverseOrderMerge(arr, L, M, R);

    }

    private static int reverseOrderMerge(int[] arr, int L, int M, int R) {
        int p1 = L, p2 = M + 1;
        int reversePair = 0;
        int i = 0;
        int[] help = new int[(R - L + 1)];
        while (p1 <= M && p2 <= R){
            reversePair += arr[p1] > arr[p2] ? (M + 1 - p1) : 0;
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
        }
        while (p1 <= M){
            help[i++] = arr[p1++];
        }
        while (p2 <= R){
            help[i++] = arr[p2++];
        }

        for (i = 0; i < help.length; i++){
            arr[L + i] = help[i];
        }
        return reversePair;
    }

    @Test
    public void testReverseOrder(){
        int[] arr = generateRandomArray(100, 100);
        arr = new int[]{1,3,2,3,1};
        int sum = reverseOrder(arr);
        System.out.println(sum);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");

        }
    }

    public static void quickSort(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }
    public static void quickSort(int[] arr, int L, int  R){
        if(L < R){
            swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
            int[] p = partition(arr, L, R);
            quickSort(arr, L, p[0] - 1);
            quickSort(arr,p[1] + 1, R);
        }
    }

    /**
     *
     * @param arr
     * @param L
     * @param R
     * @return 返回的是数组中间相等位置的下标
     */
    public static int[] partition(int[] arr, int L, int R){
        // less 是小于num的左边界, more 是大于区域的右边界 less [  arr   ] more
        int less = L - 1;
        int more = R;
        // L 是遍历的下标，当待遍历的数与大于区域的右边界重合的时候，分区结束
        // R 是用来分区的数的下标，也就是数组的最右边的数，称为标识数
        while (L < more){
            if(arr[L] < arr[R]){  // 如果这个数小于标识数，小于边界++，把这个数放到小于区域里面去，遍历下标++
                swap(arr, ++less, L++);
            }else if(arr[L] > arr[R]){ // 如果这个数大于标识数，大于边界--，把这个数放到大于区域中去，被交换过来的数不知道是否大于还是小于，下标不变
                swap(arr, --more, L);
            }else { // 相等，不需要交换，遍历下标++
                L++;
            }
        }
        swap(arr, more, R); // 交换 标识数与大于边界的数，完成 [小于区域][等于区域][大于区域]
        return new int[]{less + 1, more}; //等于区域的边界是 小于区域右边界+1, 大于区域边界因为最后的swap，more++,等于区域边界等于大于区域边界-- 最后就等于more,
    }

    @Test
    public void testQuickSort() {
        int[] arr = generateRandomArray(100, 100);
        quickSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");

        }
    }

    @Test
    public void test() {
        System.out.println((-1 / 2));
    }

    // 某个数现在在index位置，往上移动
    public static void heapInsert(int[] arr, int index){
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1)/2;
        }
    }

    // 使 以index为头节点的子树为大根堆
    public static void heapify(int[] arr, int index, int heapSize){
        int left = 2 * index + 1;
        while (left < heapSize){
            int largest =  left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;

            largest = arr[largest] > arr[index] ? largest : index;

            if(largest == index){
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index  * 2 + 1;
        }
    }

    public static void heapSort(int[] arr){
        if(arr == null && arr.length < 2){
            return;
        }
//        for (int i = 0; i < arr.length; i++) { //O(N)
//            heapInsert(arr, i); // O(logN)
//        }
        //也可以这样，速度会快一些
        for (int i = arr.length - 1; i >= 0; i--){
            heapify(arr, i, arr.length);
        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while (heapSize > 0){  //O(N)
            heapify(arr, 0 , heapSize); // O(logN)
            swap(arr, 0, --heapSize); // O(1)
        }
    }

    @Test
    public void testHeapSort() {
        int[] arr = generateRandomArray(100, 100);
        heapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }


    public static void sortedArrDistanceLessK(int[] arr, int k){
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int index = 0;
        int i = 0;
        for (;index <= Math.min(arr.length, k); index++){
            heap.add(arr[index]);
        }
        for (; index < arr.length; i++, index++){
            heap.add(arr[index]);
            arr[i] = heap.poll();
        }
        while (!heap.isEmpty()){
            arr[i++]  =  heap.poll();
        }
    }

    @Test
    public void testPriorityQueue() {
        PriorityQueue<Integer> bigQueue = new PriorityQueue<>(new DescComparator());

        bigQueue.add(3);
        bigQueue.add(8);
        bigQueue.add(9);
        bigQueue.add(2);
        bigQueue.add(6);
        bigQueue.add(3);
        bigQueue.add(1);
        bigQueue.add(7);
        bigQueue.add(4);

        while (!bigQueue.isEmpty()){
            System.out.print(bigQueue.poll()+" ");
        }
        System.out.println();

        PriorityQueue<Integer> smallQueue = new PriorityQueue<>();

        smallQueue.add(3);
        smallQueue.add(8);
        smallQueue.add(9);
        smallQueue.add(2);
        smallQueue.add(6);
        smallQueue.add(3);
        smallQueue.add(1);
        smallQueue.add(7);
        smallQueue.add(4);

        while (!smallQueue.isEmpty()){
            System.out.print(smallQueue.poll()+" ");
        }


    }

    /**
     * 基数排序
     * @param arr 排序的数组
     * @param L 排序的左闭区间
     * @param R 排序的右闭区间
     * @param digit 一批数字中最大值有几个十进制位
     */
    public static void radixSort(int[] arr, int L, int R, int digit){
        final int radix = 10;
        int i = 0, j = 0;
        // 有多少个数准备多少个辅助空间
        int[] bucket = new int[R - L + 1];
        for (int d = 1; d <= digit; d++){ // 有多少位就进出几次
            // 10个空间
            // count[0] 当前位(d位)是0的数字有多少个
            // count[i] 当前位(d位)是i的数字有多少个
            int[] count = new int[radix];
            for (i = L; i <= R; i++){
                j = getDigit(arr[i], d);
                count[j]++;
            }
            for (i = 1; i < radix; i++){
                count[i] = count[i] + count[i - 1]; // 得到前缀和 count[i]表示d位比i小的有多少个
            }
            for (i = R; i >= L; i--){ // 利用前缀和来实现数组分片，也就是入桶
                j = getDigit(arr[i], d); // eg: count[i] = 3的时候，意味着有3个数字的d位是小于等于i的,那么这个数（倒序遍历）最后的位置就该在 3 - 1 也就是bucket[2]
                bucket[count[j] - 1] = arr[i];
                count[j]--;
            }
            for (i = L, j = 0; i <= R; i++, j++){ // 从桶中取出
                arr[i] = bucket[j];
            }

        }
    }

    private static int getDigit(int num, int radix) {
        if(num < 0){
            num = -num;
        }
        int tmp = (int) Math.pow(10, radix - 1);
        if(num >= tmp){
            num /= tmp;
            return num % 10;
        }else {
            return 0;
        }
    }

    public static int maxBits(int[] arr){
        int max = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            max = Math.max(arr[i], max);
        }
        int res = 0;
        while (max != 0){
            max /= 10;
            res++;
        }
        return res;
    }

    @Test
    public void testGetDigit() {
        System.out.println(getDigit(-1327, 3));
    }

}
class DescComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer t2, Integer t1) {
        return t1 - t2;
    }
}

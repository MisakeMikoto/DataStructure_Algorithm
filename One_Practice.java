import org.junit.Test;

import java.util.Queue;

/**
 * @Author MisakiMikoto
 * @Date 2023/6/1
 */

public class One_Practice {
    public static void main(String[] args) {

    }

    public static void MergeSort(int[] arr, int L, int R){
        if (arr == null || L == R){
            return;
        }
        int M = L + ((R - L) >> 1);
        MergeSort(arr, L, M);
        MergeSort(arr, M + 1, R);
        Merge(arr, L, M, R);
    }

    public static void Merge(int[] arr, int L, int M, int R){
        int[] help = new int[R - L + 1];
        int p1 = L, p2 = M + 1, i = 0;
        while (p1 <= M && p2 <= R){
            help[i++] = arr[p1] > arr[p2] ? arr[p2++] : arr[p1++];
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

    public static int[] generateRandomArr(int maxSize, int maxValue){
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1)) - (int)(Math.random() * (maxValue + 1));
        }
        return arr;
    }

    @Test
    public void testMergeSort(){
        int[] arr = generateRandomArr(100, 1000);
        MergeSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void swap(int[] arr, int L, int R){
        if(L == R){
            return;
        }
        arr[L] = arr[L] ^ arr[R];
        arr[R] = arr[L] ^ arr[R];
        arr[L] = arr[L] ^ arr[R];
    }
    public static int[] partition(int[] arr, int L, int R){
        int left = L - 1;
        int right = R;
        int temp = (int) ((R - L + 1) * Math.random() + L);
        swap(arr, temp, R);
        while (L < right){
            if (arr[L] < arr[R]){
                swap(arr, L++, ++left);
            }
            else if (arr[L] > arr[R]){
                swap(arr, L, --right);
            }
            else{
                L++;
            }
        }
        swap(arr, right, R);
        return new int[]{left + 1, right};
    }

    public static void QuickSort(int[] arr, int L, int R){
        if(arr == null || arr.length < 2){
            return;
        }
        if(L < R){
            int[] partition = partition(arr, L, R);
            QuickSort(arr, L, partition[0] - 1);
            QuickSort(arr, partition[1] + 1, R);
        }
    }

    @Test
    public void testQuickSort(){
        int[] arr = generateRandomArr(100, 1000);
        QuickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void heapify(int[] arr, int index, int heapSize){
        int left = index * 2 + 1;
        while (left < heapSize){
            int largest = arr[left] < arr[left + 1] && left + 1 < heapSize ? left + 1 : left;

            largest = arr[index] > arr[largest] ? index : largest;

            if(index == largest){
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index * 2 + 1;
        }
    }

    public static void heapInsert(int[] arr, int index){
        while (arr[index] > arr[(index - 1) / 2]){
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    public static void HeapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for(int i = 0; i < arr.length; i++){
            heapInsert(arr, i);
        }

        int heapSize = arr.length;

        swap(arr, 0, --heapSize);
        while (heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }
    }
    @Test
    public void testHeapSort(){
        int[] arr = generateRandomArr(1000, 10000);
        HeapSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    @Test
    public void testFindTwoSingleNum(){
        int[] arr = new int[]{1,1,29,3,3,7888};
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^=arr[i];
        }
//        eor = 0b1010111100;
//        System.out.println("eor=   "+Integer.toBinaryString(eor));
//        System.out.println("~eor=  "+Integer.toBinaryString(~eor));
//        System.out.println("~eor+1="+Integer.toBinaryString(~eor + 1));
        int rightOne = eor & (~eor + 1);
//        System.out.println(Integer.toBinaryString(rightOne));
//        System.out.println((rightOne & 0b1011) == 0);

        int onlyOne = 0;
        for(int cur : arr){
            if((cur & rightOne) == rightOne){
                onlyOne ^= cur;
            }
        }
        int a = eor ^ onlyOne;
        int b = eor ^ a;
        System.out.println("a="+a);
        System.out.println("b="+b);
    }
}

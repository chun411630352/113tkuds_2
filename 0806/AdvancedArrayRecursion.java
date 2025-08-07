import java.util.Arrays;

public class AdvancedArrayRecursion {
    public static void main(String[] args) {
        int[] arr = {9, 3, 7, 1, 5, 2, 8};
        int[] sorted = Arrays.copyOf(arr, arr.length);
        quickSort(sorted, 0, sorted.length - 1);
        System.out.println("快速排序結果: " + Arrays.toString(sorted));

        int[] a = {1, 3, 5};
        int[] b = {2, 4, 6, 7};
        int[] merged = mergeRecursive(a, 0, b, 0);
        System.out.println("合併後結果: " + Arrays.toString(merged));

        int k = 4;
        int kth = kthSmallest(Arrays.copyOf(arr, arr.length), 0, arr.length - 1, k - 1);
        System.out.println("第 " + k + " 小元素: " + kth);

        int[] subsetArr = {2, 4, 8};
        int target = 6;
        System.out.println("是否有子序列總和為 " + target + ": " + hasSubsetSum(subsetArr, 0, target));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p + 1, high);
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    public static int[] mergeRecursive(int[] a, int i, int[] b, int j) {
        if (i == a.length) return Arrays.copyOfRange(b, j, b.length);
        if (j == b.length) return Arrays.copyOfRange(a, i, a.length);
        if (a[i] <= b[j]) {
            int[] rest = mergeRecursive(a, i + 1, b, j);
            int[] result = new int[rest.length + 1];
            result[0] = a[i];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        } else {
            int[] rest = mergeRecursive(a, i, b, j + 1);
            int[] result = new int[rest.length + 1];
            result[0] = b[j];
            System.arraycopy(rest, 0, result, 1, rest.length);
            return result;
        }
    }

    public static int kthSmallest(int[] arr, int left, int right, int k) {
        if (left <= right) {
            int p = partition(arr, left, right);
            if (p == k) return arr[p];
            if (k < p) return kthSmallest(arr, left, p - 1, k);
            else return kthSmallest(arr, p + 1, right, k);
        }
        return -1;
    }

    public static boolean hasSubsetSum(int[] arr, int index, int target) {
        if (target == 0) return true;
        if (index == arr.length || target < 0) return false;
        return hasSubsetSum(arr, index + 1, target - arr[index]) || hasSubsetSum(arr, index + 1, target);
    }
}

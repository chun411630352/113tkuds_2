import java.util.Arrays;

public class SelectionSortImplementation {
    public static void main(String[] args) {
        int[] data = {64, 25, 12, 22, 11};
        int[] dataForBubble = Arrays.copyOf(data, data.length);

        System.out.println("原始陣列: " + Arrays.toString(data));
        System.out.println("\n=== 選擇排序 ===");
        SortStats selectionStats = selectionSort(data);
        System.out.println("排序結果: " + Arrays.toString(data));
        System.out.println("比較次數: " + selectionStats.comparisons);
        System.out.println("交換次數: " + selectionStats.swaps);

        System.out.println("\n=== 氣泡排序 ===");
        SortStats bubbleStats = bubbleSort(dataForBubble);
        System.out.println("排序結果: " + Arrays.toString(dataForBubble));
        System.out.println("比較次數: " + bubbleStats.comparisons);
        System.out.println("交換次數: " + bubbleStats.swaps);
    }

    public static SortStats selectionSort(int[] array) {
        int n = array.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                comparisons++;
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                swaps++;
            }

            System.out.println("第 " + (i + 1) + " 輪排序結果: " + Arrays.toString(array));
        }

        return new SortStats(comparisons, swaps);
    }

    public static SortStats bubbleSort(int[] array) {
        int n = array.length;
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - 1 - i; j++) {
                comparisons++;
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            System.out.println("第 " + (i + 1) + " 輪排序結果: " + Arrays.toString(array));

            if (!swapped) break; // 提早停止
        }

        return new SortStats(comparisons, swaps);
    }

    static class SortStats {
        int comparisons;
        int swaps;

        SortStats(int comparisons, int swaps) {
            this.comparisons = comparisons;
            this.swaps = swaps;
        }
    }
}

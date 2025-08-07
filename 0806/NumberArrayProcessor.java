import java.util.*;

public class NumberArrayProcessor {
    public static void main(String[] args) {
        int[] array1 = {4, 2, 7, 2, 3, 7, 4, 5};
        int[] array2 = {1, 3, 5, 7};
        int[] array3 = {2, 4, 6, 8};

        int[] deduplicated = removeDuplicates(array1);
        int[] merged = mergeSortedArrays(array2, array3);
        int mostFrequent = findMostFrequentElement(array1);
        int[][] split = splitArrayEvenly(array1);

        System.out.println("原始陣列： " + Arrays.toString(array1));
        System.out.println("移除重複後： " + Arrays.toString(deduplicated));
        System.out.println("合併後陣列： " + Arrays.toString(merged));
        System.out.println("出現最多次的元素： " + mostFrequent);
        System.out.println("分割後子陣列：");
        System.out.println("子陣列 1：" + Arrays.toString(split[0]));
        System.out.println("子陣列 2：" + Arrays.toString(split[1]));
    }

    public static int[] removeDuplicates(int[] array) {
        Set<Integer> set = new LinkedHashSet<>();
        for (int num : array) {
            set.add(num);
        }
        int[] result = new int[set.size()];
        int i = 0;
        for (int num : set) {
            result[i++] = num;
        }
        return result;
    }

    public static int[] mergeSortedArrays(int[] a, int[] b) {
        int[] result = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }
        while (i < a.length) {
            result[k++] = a[i++];
        }
        while (j < b.length) {
            result[k++] = b[j++];
        }
        return result;
    }

    public static int findMostFrequentElement(int[] array) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : array) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int maxCount = 0;
        int mostFrequent = array[0];
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount || (entry.getValue() == maxCount && entry.getKey() < mostFrequent)) {
                maxCount = entry.getValue();
                mostFrequent = entry.getKey();
            }
        }
        return mostFrequent;
    }

    public static int[][] splitArrayEvenly(int[] array) {
        Arrays.sort(array);
        List<Integer> part1 = new ArrayList<>();
        List<Integer> part2 = new ArrayList<>();
        int sum1 = 0, sum2 = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            if (sum1 <= sum2) {
                part1.add(array[i]);
                sum1 += array[i];
            } else {
                part2.add(array[i]);
                sum2 += array[i];
            }
        }
        int[][] result = new int[2][];
        result[0] = part1.stream().mapToInt(i -> i).toArray();
        result[1] = part2.stream().mapToInt(i -> i).toArray();
        return result;
    }
}

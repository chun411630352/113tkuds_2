import java.util.*;

public class M11_HeapSortWithTie {
    static class Student implements Comparable<Student> {
        int score;
        int index;
        Student(int score, int index) {
            this.score = score;
            this.index = index;
        }

        // Max-Heap 比較，分數大優先，分數相同時索引小優先
        @Override
        public int compareTo(Student o) {
            if (this.score != o.score)
                return this.score - o.score;
            else
                return o.index - this.index;  //準備用Min-Heap取反，所以反轉索引比較（倒著比較）
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Student[] arr = new Student[n];
        for (int i = 0; i < n; i++) {
            int score = sc.nextInt();
            arr[i] = new Student(score, i);
        }

        heapSort(arr);

        // 印出遞增分數序
        for (int i = 0; i < n; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(arr[i].score);
        }
    }

    static void heapSort(Student[] arr) {
        int n = arr.length;

        // 建Max-Heap (用比較為 score 小優先 => 反轉後陣列為遞減，再反轉才能升冪)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // 逐步將最大元素放末端並縮小堆積範圍
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }

        // 目前 arr 是降冪（最大在前）
        // 反轉 arr 為升冪
        reverse(arr);
    }

    static void heapify(Student[] arr, int heapSize, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < heapSize && compare(arr[left], arr[largest]) > 0) {
            largest = left;
        }
        if (right < heapSize && compare(arr[right], arr[largest]) > 0) {
            largest = right;
        }

        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, heapSize, largest);
        }
    }

    // 比較：分數較大優先；分數相同時索引較小者優先
    static int compare(Student a, Student b) {
        if (a.score != b.score)
            return a.score - b.score;
        else
            return b.index - a.index; // index 較小優先 所以逆向比較
    }

    static void swap(Student[] arr, int i, int j) {
        Student tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    static void reverse(Student[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
}

/*
 * 時間複雜度：O(n log n)
 * 說明：
 * 1. 建立 Max-Heap 時間複雜度為 O(n)。
 * 2. 每次抽出最大元素需調整堆積並執行 heapify，總共進行 n-1 次，每次 O(log n)。
 * 3. 反轉陣列為 O(n)。
 * 4. 整體耗時以堆排序內部 heapify 主導，總時間複雜度為 O(n log n)。
 */

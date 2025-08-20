
import java.util.Scanner;

public class M01_BuildHeap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String type = sc.next();
        int n = sc.nextInt();
        int[] heap = new int[n];
        for (int i = 0; i < n; ++i) heap[i] = sc.nextInt();

        if ("max".equals(type)) {
         
            for (int i = n / 2 - 1; i >= 0; --i)
                heapifyDown(heap, n, i, true);
        } else {
            
            for (int i = n / 2 - 1; i >= 0; --i)
                heapifyDown(heap, n, i, false);
        }

        
        for (int i = 0; i < n; ++i) {
            if (i > 0) System.out.print(" ");
            System.out.print(heap[i]);
        }
    }

    
    static void heapifyDown(int[] heap, int n, int i, boolean isMax) {
        int child;
        while ((child = 2 * i + 1) < n) {
            int right = child + 1;
            
            if (right < n &&
                 ((isMax && heap[right] > heap[child]) ||
                  (!isMax && heap[right] < heap[child])))
                child = right;
            if ((isMax && heap[i] >= heap[child]) ||
                (!isMax && heap[i] <= heap[child]))
                break;
            
            int tmp = heap[i];
            heap[i] = heap[child];
            heap[child] = tmp;
            i = child;
        }
    }
}

/*
 * Time Complexity: O(n)
 * 說明：
 * 1. Build-Heap採用自底向上，每次heapifyDown對於每個節點的調整次數，與其離葉節點的距離成正比。
 * 2. 葉節點不用調整，每層向上，節點數雖減少，但heapifyDown次數增加，整體運算次數近似一個等比級數和。
 * 3. 經推導（見課本或CLRS 6.3節），總時間複雜度為 O(n)。
 */

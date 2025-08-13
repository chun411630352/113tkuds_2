import java.util.ArrayList;

public class BasicMinHeapPractice {
    static class MinHeap {
        private final ArrayList<Integer> heap = new ArrayList<>();

        public void insert(int val) {
            heap.add(val);
            heapifyUp(heap.size() - 1);
        }

        public int extractMin() {
            if (heap.isEmpty()) throw new IllegalStateException("heap is empty");
            int min = heap.get(0);
            int last = heap.remove(heap.size() - 1);
            if (!heap.isEmpty()) {
                heap.set(0, last);
                heapifyDown(0);
            }
            return min;
        }

        public int getMin() {
            if (heap.isEmpty()) throw new IllegalStateException("heap is empty");
            return heap.get(0);
        }

        public int size() {
            return heap.size();
        }

        public boolean isEmpty() {
            return heap.isEmpty();
        }

        private void heapifyUp(int i) {
            while (i > 0) {
                int p = (i - 1) / 2;
                if (heap.get(i) >= heap.get(p)) break;
                swap(i, p);
                i = p;
            }
        }

        private void heapifyDown(int i) {
            int n = heap.size();
            while (true) {
                int l = 2 * i + 1, r = 2 * i + 2, s = i;
                if (l < n && heap.get(l) < heap.get(s)) s = l;
                if (r < n && heap.get(r) < heap.get(s)) s = r;
                if (s == i) break;
                swap(i, s);
                i = s;
            }
        }

        private void swap(int i, int j) {
            int t = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, t);
        }
    }

    public static void main(String[] args) {
        MinHeap h = new MinHeap();
        int[] a = {15, 10, 20, 8, 25, 5};
        for (int v : a) h.insert(v);
        StringBuilder sb = new StringBuilder();
        while (!h.isEmpty()) {
            sb.append(h.extractMin());
            if (!h.isEmpty()) sb.append(", ");
        }
        System.out.println(sb.toString());
    }
}
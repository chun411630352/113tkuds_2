import java.util.*;

public class M12_MergeKTimeTables {
    static class TimeEntry implements Comparable<TimeEntry> {
        int time;
        int listIndex;
        int elementIndex;

        TimeEntry(int time, int listIndex, int elementIndex) {
            this.time = time;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }

        @Override
        public int compareTo(TimeEntry o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt();
        int[][] lists = new int[K][];

        for (int i = 0; i < K; i++) {
            int len = sc.nextInt();
            lists[i] = new int[len];
            for (int j = 0; j < len; j++) {
                lists[i][j] = sc.nextInt();
            }
        }

        PriorityQueue<TimeEntry> minHeap = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            if (lists[i].length > 0) {
                minHeap.offer(new TimeEntry(lists[i][0], i, 0));
            }
        }

        List<Integer> merged = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            TimeEntry cur = minHeap.poll();
            merged.add(cur.time);

            int nextIndex = cur.elementIndex + 1;
            if (nextIndex < lists[cur.listIndex].length) {
                minHeap.offer(new TimeEntry(lists[cur.listIndex][nextIndex], cur.listIndex, nextIndex));
            }
        }

        for (int i = 0; i < merged.size(); i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(merged.get(i));
        }
    }
}


import java.util.*;

public class MeetingRoomScheduler {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0])); // 依起始時間排序

        PriorityQueue<Integer> heap = new PriorityQueue<>(); // 儲存會議結束時間

        for (int[] meeting : intervals) {
            if (!heap.isEmpty() && meeting[0] >= heap.peek()) {
                heap.poll(); // 重用舊的會議室
            }
            heap.offer(meeting[1]); // 加入新的會議室或更新時間
        }

        return heap.size();
    }

    public static void main(String[] args) {
        MeetingRoomScheduler mrs = new MeetingRoomScheduler();

        int[][] case1 = {{0,30}, {5,10}, {15,20}};
        System.out.println("答案：" + mrs.minMeetingRooms(case1)); // 2

        int[][] case2 = {{9,10}, {4,9}, {4,17}};
        System.out.println("答案：" + mrs.minMeetingRooms(case2)); // 2

        int[][] case3 = {{1,5}, {8,9}, {8,9}};
        System.out.println("答案：" + mrs.minMeetingRooms(case3)); // 2
    }
}
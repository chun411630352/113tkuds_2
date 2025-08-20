import java.util.*;

public class M02_YouBikeNextArrival {
   
    static int toMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String[] times = new String[n];
        int[] mins = new int[n];
        for (int i = 0; i < n; ++i) {
            times[i] = sc.nextLine();
            mins[i] = toMinutes(times[i]);
        }
        String query = sc.nextLine();
        int queryMin = toMinutes(query);

   
        int left = 0, right = n - 1, ans = -1;
        while (left <= right) {
            int mid = (left + right) /2;

            if (mins[mid] > queryMin) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (ans == -1) {
            System.out.println("No bike");
        } else {
            System.out.println(times[ans]);
        }
    }
}

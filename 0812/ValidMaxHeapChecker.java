
public class ValidMaxHeapChecker {
    public static int firstViolation(int[] a) {
        int n = a.length;
        for (int i = 0; i <= n / 2 - 1; i++) {
            int l = 2 * i + 1, r = 2 * i + 2;
            if (l < n && a[i] < a[l]) return l;
            if (r < n && a[i] < a[r]) return r;
        }
        return -1;
    }

    public static boolean isValidMaxHeap(int[] a) {
        return firstViolation(a) == -1;
    }

    private static String s(int[] a) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < a.length; i++) {
            if (i > 0) sb.append(", ");
            sb.append(a[i]);
        }
        return sb.append("]").toString();
    }

    public static void main(String[] args) {
        int[][] tests = new int[][]{
            new int[]{100, 90, 80, 70, 60, 75, 65},
            new int[]{100, 90, 80, 95, 60, 75, 65},
            new int[]{50},
            new int[]{} 
        };
        for (int[] t : tests) {
            int v = firstViolation(t);
            if (v == -1) System.out.println(s(t) + " -> true");
            else System.out.println(s(t) + " -> false, index=" + v);
        }
    }
}
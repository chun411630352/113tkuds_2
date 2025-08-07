public class RecursiveMathCalculator {
    public static void main(String[] args) {
        System.out.println("C(5, 2) = " + combination(5, 2));
        System.out.println("Catalan(5) = " + catalan(5));
        System.out.println("Hanoi(4) = " + hanoiMoves(4));
        System.out.println("Is 12321 a palindrome? " + isPalindrome(12321));
        System.out.println("Is 12345 a palindrome? " + isPalindrome(12345));
    }

    public static int combination(int n, int k) {
        if (k == 0 || k == n) return 1;
        return combination(n - 1, k - 1) + combination(n - 1, k);
    }

    public static int catalan(int n) {
        if (n == 0) return 1;
        int result = 0;
        for (int i = 0; i < n; i++) {
            result += catalan(i) * catalan(n - 1 - i);
        }
        return result;
    }

    public static int hanoiMoves(int n) {
        if (n == 1) return 1;
        return 2 * hanoiMoves(n - 1) + 1;
    }

    public static boolean isPalindrome(int number) {
        return number == reverse(number);
    }

    public static int reverse(int number) {
        return reverseHelper(number, 0);
    }

    public static int reverseHelper(int number, int result) {
        if (number == 0) return result;
        return reverseHelper(number / 10, result * 10 + number % 10);
    }
}

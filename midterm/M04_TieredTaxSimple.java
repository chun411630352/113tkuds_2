import java.util.*;

public class M04_TieredTaxSimple {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] incomes = new int[n];
        for (int i = 0; i < n; i++) {
            incomes[i] = sc.nextInt();
        }

        long totalTax = 0;
        for (int income : incomes) {
            int tax = calculateTax(income);
            System.out.println("Tax: " + tax);
            totalTax += tax;
        }

        System.out.println("Average: " + (int)(totalTax / n));
    }

    static int calculateTax(int income) {
        int tax = 0;
        if (income > 1000000) {
            tax += (income - 1000000) * 30 / 100;
            income = 1000000;
        }
        if (income > 500000) {
            tax += (income - 500000) * 20 / 100;
            income = 500000;
        }
        if (income > 120000) {
            tax += (income - 120000) * 12 / 100;
            income = 120000;
        }
        tax += income * 5 / 100;
        return tax;
    }
}

/*
 * 時間複雜度：O(n)
 * 說明：
 * 1. 對 n 筆收入資料逐一計算稅額，每筆計算固定步驟，為常數時間 O(1)。
 * 2. 輸入與輸出皆為 O(n)。
 * 因此整體時間複雜度為 O(n)。
 */

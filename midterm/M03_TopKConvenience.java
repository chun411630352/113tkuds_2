import java.util.*;

public class M03_TopKConvenience {
    static class Item {
        String name;
        int qty;
        int order;
        Item(String name, int qty, int order) {
            this.name = name;
            this.qty = qty;
            this.order = order;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), K = sc.nextInt();
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int qty = sc.nextInt();
            items.add(new Item(name, qty, i));
        }

        items.sort((a, b) -> {
            if (b.qty != a.qty)
                return b.qty - a.qty;
            else
                return a.order - b.order;
        });

        int m = Math.min(K, n);
        for (int i = 0; i < m; i++)
            System.out.println(items.get(i).name + " " + items.get(i).qty);
    }
}

/*
 * 時間複雜度：O(n log n)
 * 說明：
 * 1. 輸入資料：O(n)
 * 2. 依銷量由大到小排序並保持輸入順序穩定：O(n log n)
 * 3. 輸出前 K 名：O(K)
 * 總體時間複雜度以排序為主，為 O(n log n)
 */

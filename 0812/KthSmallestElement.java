// KthSmallestElement.java
import java.util.*;

public class KthSmallestElement {
    public static int kthSmallest(int[] a, int k){
        PriorityQueue<Integer> maxh = new PriorityQueue<>(Comparator.reverseOrder());
        for(int v: a){
            if (maxh.size()<k) maxh.add(v);
            else if (v<maxh.peek()){ maxh.poll(); maxh.add(v); }
        }
        return maxh.peek();
    }

    private static String arr(int[] a){
        StringBuilder sb=new StringBuilder("[");
        for(int i=0;i<a.length;i++){ if(i>0) sb.append(", "); sb.append(a[i]); }
        return sb.append("]").toString();
    }

    public static void main(String[] args){
        int[] a1={7,10,4,3,20,15}; int k1=3;
        System.out.println("陣列："+arr(a1)+", K="+k1);
        System.out.println("答案："+kthSmallest(a1,k1));
        int[] a2={1}; int k2=1;
        System.out.println("\n陣列："+arr(a2)+", K="+k2);
        System.out.println("答案："+kthSmallest(a2,k2));
        int[] a3={3,1,4,1,5,9,2,6}; int k3=4;
        System.out.println("\n陣列："+arr(a3)+", K="+k3);
        System.out.println("答案："+kthSmallest(a3,k3));
    }
}
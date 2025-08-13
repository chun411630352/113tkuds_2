// MergeKSortedArrays.java
import java.util.*;

public class MergeKSortedArrays {
    static class Node {
        final int val, ai, idx;
        Node(int v,int a,int i){val=v;ai=a;idx=i;}
    }

    public static int[] merge(int[][] arrays){
        int n=0;
        for (int[] t: arrays) n+=t.length;
        int[] out=new int[n];
        PriorityQueue<Node> minpq = new PriorityQueue<>(Comparator.comparingInt(o->o.val));
        for(int i=0;i<arrays.length;i++){
            if(arrays[i].length>0) minpq.add(new Node(arrays[i][0], i, 0));
        }
        int p=0;
        while(!minpq.isEmpty()){
            Node cur=minpq.poll();
            out[p++]=cur.val;
            int ni=cur.idx+1;
            if(ni<arrays[cur.ai].length){
                minpq.add(new Node(arrays[cur.ai][ni], cur.ai, ni));
            }
        }
        return out;
    }

    private static String arr(int[] a){
        StringBuilder sb=new StringBuilder("[");
        for(int i=0;i<a.length;i++){ if(i>0) sb.append(", "); sb.append(a[i]); }
        return sb.append("]").toString();
    }

    public static void main(String[] args){
        int[][] a1={{1,4,5},{1,3,4},{2,6}};
        System.out.println(arr(merge(a1)));
        int[][] a2={{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(arr(merge(a2)));
        int[][] a3={{1},{0}};
        System.out.println(arr(merge(a3)));
    }
}
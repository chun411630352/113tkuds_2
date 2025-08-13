// SlidingWindowMedian.java
import java.util.*;

public class SlidingWindowMedian {
    static class DualHeap {
        PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> large = new PriorityQueue<>();
        Map<Integer,Integer> delayed = new HashMap<>();
        int smallSize=0, largeSize=0;
        int k;
        DualHeap(int k){this.k=k;}
        void prune(PriorityQueue<Integer> pq){
            while(!pq.isEmpty()){
                int x=pq.peek();
                int c=delayed.getOrDefault(x,0);
                if(c==0) break;
                if(c==1) delayed.remove(x); else delayed.put(x,c-1);
                pq.poll();
            }
        }
        void makeBalance(){
            if(smallSize>largeSize+1){
                large.add(small.poll()); smallSize--; largeSize++; prune(small);
            }else if(smallSize<largeSize){
                small.add(large.poll()); smallSize++; largeSize--; prune(large);
            }
        }
        void insert(int x){
            if(small.isEmpty()||x<=small.peek()){ small.add(x); smallSize++; }
            else { large.add(x); largeSize++; }
            makeBalance();
        }
        void erase(int x){
            delayed.put(x,delayed.getOrDefault(x,0)+1);
            if(!small.isEmpty() && x<=small.peek()){ smallSize--; if(x==small.peek()) prune(small); }
            else { largeSize--; if(!large.isEmpty() && x==large.peek()) prune(large); }
            makeBalance();
        }
        double median(){
            if((k&1)==1) return small.peek();
            return ((double)small.peek()+large.peek())/2.0;
        }
    }
    public static double[] medianSlidingWindow(int[] nums,int k){
        int n=nums.length;
        if(n==0||k==0) return new double[0];
        DualHeap dh=new DualHeap(k);
        for(int i=0;i<k;i++) dh.insert(nums[i]);
        double[] ans=new double[n-k+1];
        ans[0]=dh.median();
        for(int i=k;i<n;i++){
            dh.insert(nums[i]);
            dh.erase(nums[i-k]);
            ans[i-k+1]=dh.median();
        }
        return ans;
    }
    private static String arr(double[] a){
        StringBuilder sb=new StringBuilder("[");
        for(int i=0;i<a.length;i++){
            if(i>0) sb.append(", ");
            double v=a[i];
            if(Math.abs(v-Math.round(v))<1e-9) sb.append((long)Math.round(v));
            else sb.append(v);
        }
        return sb.append("]").toString();
    }
    public static void main(String[] args){
        int[] a1={1,3,-1,-3,5,3,6,7}; int k1=3;
        System.out.println(arr(medianSlidingWindow(a1,k1)));
        int[] a2={1,2,3,4}; int k2=2;
        System.out.println(arr(medianSlidingWindow(a2,k2)));
    }
}
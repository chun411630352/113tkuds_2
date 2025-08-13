// StockMaximizer.java
import java.util.*;

public class StockMaximizer {
    public static int maxProfitK(int[] p,int K){
        int n=p.length; if(n==0||K==0) return 0;
        if(K>=n/2){
            int s=0; for(int i=1;i<n;i++) if(p[i]>p[i-1]) s+=p[i]-p[i-1]; return s;
        }
        int[] buy=new int[K+1], sell=new int[K+1];
        Arrays.fill(buy, Integer.MIN_VALUE/4);
        Arrays.fill(sell, 0);
        for(int price: p){
            for(int k=1;k<=K;k++){
                buy[k]=Math.max(buy[k], sell[k-1]-price);
                sell[k]=Math.max(sell[k], buy[k]+price);
            }
        }
        return sell[K];
    }
    public static void main(String[] args){
        System.out.println(maxProfitK(new int[]{2,4,1},2));
        System.out.println(maxProfitK(new int[]{3,2,6,5,0,3},2));
        System.out.println(maxProfitK(new int[]{1,2,3,4,5},2));
    }
}
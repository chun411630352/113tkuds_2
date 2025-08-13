// MovingAverageStream.java
import java.util.*;

public class MovingAverageStream {
    static class MovingAverage {
        final int size;
        Deque<Integer> q=new ArrayDeque<>();
        long sum=0;
        Deque<Integer> minQ=new ArrayDeque<>();
        Deque<Integer> maxQ=new ArrayDeque<>();
        PriorityQueue<Integer> small=new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> large=new PriorityQueue<>();
        Map<Integer,Integer> delayed=new HashMap<>();
        int smallSize=0, largeSize=0;

        MovingAverage(int size){this.size=size;}

        private void pushMedian(int x){
            if(small.isEmpty()||x<=small.peek()){ small.add(x); smallSize++; }
            else { large.add(x); largeSize++; }
            rebalance();
        }
        private void eraseMedian(int x){
            delayed.put(x,delayed.getOrDefault(x,0)+1);
            if(!small.isEmpty() && x<=small.peek()){ smallSize--; if(x==small.peek()) prune(small); }
            else { largeSize--; if(!large.isEmpty() && x==large.peek()) prune(large); }
            rebalance();
        }
        private void prune(PriorityQueue<Integer> pq){
            while(!pq.isEmpty()){
                int x=pq.peek();
                int c=delayed.getOrDefault(x,0);
                if(c==0) break;
                if(c==1) delayed.remove(x); else delayed.put(x,c-1);
                pq.poll();
            }
        }
        private void rebalance(){
            if(smallSize>largeSize+1){ large.add(small.poll()); smallSize--; largeSize++; prune(small); }
            else if(smallSize<largeSize){ small.add(large.poll()); smallSize++; largeSize--; prune(large); }
        }

        public double next(int val){
            q.addLast(val);
            sum+=val;
            while(!minQ.isEmpty() && minQ.peekLast()>val) minQ.pollLast();
            minQ.addLast(val);
            while(!maxQ.isEmpty() && maxQ.peekLast()<val) maxQ.pollLast();
            maxQ.addLast(val);
            pushMedian(val);
            if(q.size()>size){
                int out=q.pollFirst();
                sum-=out;
                if(minQ.peekFirst()==out) minQ.pollFirst();
                if(maxQ.peekFirst()==out) maxQ.pollFirst();
                eraseMedian(out);
            }
            return sum*1.0/Math.min(q.size(), size);
        }
        public double getMedian(){
            if(q.isEmpty()) return 0;
            if((q.size()&1)==1) return small.peek();
            return ((double)small.peek()+large.peek())/2.0;
        }
        public int getMin(){ return minQ.peekFirst(); }
        public int getMax(){ return maxQ.peekFirst(); }
    }
    public static void main(String[] args){
        MovingAverage ma=new MovingAverage(3);
        System.out.println(ma.next(1));
        System.out.println(ma.next(10));
        System.out.println(ma.next(3));
        System.out.println(ma.next(5));
        System.out.println(ma.getMedian());
        System.out.println(ma.getMin());
        System.out.println(ma.getMax());
    }
}
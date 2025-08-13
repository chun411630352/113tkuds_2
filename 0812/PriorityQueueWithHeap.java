// PriorityQueueWithHeap.java
import java.util.*;

public class PriorityQueueWithHeap {
    static class Task {
        final String name;
        final int priority;
        final long seq;
        final int version;
        Task(String n, int p, long s, int v){name=n;priority=p;seq=s;version=v;}
    }

    private final PriorityQueue<Task> pq =
            new PriorityQueue<>((a,b)-> a.priority==b.priority ? Long.compare(a.seq,b.seq) : Integer.compare(b.priority,a.priority));
    private final Map<String,Integer> version = new HashMap<>();
    private long clock = 0;

    public void addTask(String name, int priority){
        int ver = version.getOrDefault(name,0)+1;
        version.put(name, ver);
        pq.add(new Task(name, priority, clock++, ver));
    }

    public String peek(){
        clean();
        return pq.isEmpty()? null : pq.peek().name;
    }

    public String executeNext(){
        clean();
        if (pq.isEmpty()) return null;
        return pq.poll().name;
    }

    public void changePriority(String name, int newPriority){
        if (!version.containsKey(name)) return;
        int ver = version.get(name)+1;
        version.put(name, ver);
        pq.add(new Task(name, newPriority, clock++, ver));
    }

    private void clean(){
        while(!pq.isEmpty()){
            Task t = pq.peek();
            if (version.getOrDefault(t.name,0) != t.version) pq.poll();
            else break;
        }
    }

    private static void print(String s){ System.out.println(s); }

    public static void main(String[] args){
        PriorityQueueWithHeap q = new PriorityQueueWithHeap();
        q.addTask("備份",1);
        q.addTask("緊急修復",5);
        q.addTask("更新",3);
        print(q.executeNext());
        print(q.executeNext());
        print(q.executeNext());
    }
}
// MultiLevelCacheSystem.java
import java.util.*;

public class MultiLevelCacheSystem {
    static class Entry {
        int key; String value;
        int freq; long ts; int ver; int level;
        Entry(int k,String v,int f,long t,int lv,int ver){key=k;value=v;freq=f;ts=t;level=lv;this.ver=ver;}
    }
    static class Level {
        final int cap;
        PriorityQueue<Entry> pq=new PriorityQueue<>((a,b)->{
            if(a.freq!=b.freq) return Integer.compare(a.freq,b.freq);
            return Long.compare(a.ts,b.ts);
        });
        Level(int c){cap=c;}
    }

    Level L1=new Level(2), L2=new Level(5), L3=new Level(10);
    Map<Integer,Entry> map=new HashMap<>();
    long clock=0;

    private void touch(Entry e){
        e.freq++; e.ts=clock++; e.ver++;
        promote(e);
    }
    private void promote(Entry e){
        if(e.level==3){ move(e, L3, L2, 2); }
        else if(e.level==2){ move(e, L2, L1, 1); }
        enforceCap(L1,1); enforceCap(L2,2); enforceCap(L3,3);
    }
    private void move(Entry e, Level from, Level to, int toLv){
        from.pq.add(new Entry(-1,"",Integer.MAX_VALUE,Long.MAX_VALUE,0,0));
        from.pq.removeIf(x->x.key==e.key);
        e.level=toLv;
        to.pq.add(e);
    }
    private void demote(Level from, Level to, int toLv){
        while(from.pq.size()>from.cap){
            Entry evict=from.pq.poll();
            if(evict==null) break;
            evict.level=toLv;
            to.pq.add(evict);
        }
    }
    private void enforceCap(Level l,int id){
        if(id==1) demote(L1,L2,2);
        else if(id==2) demote(L2,L3,3);
    }

    public void put(int key,String value){
        Entry e=map.get(key);
        if(e==null){
            e=new Entry(key,value,1,clock++,1,0);
            map.put(key,e);
            L1.pq.add(e);
            enforceCap(L1,1); enforceCap(L2,2);
        }else{
            e.value=value; touch(e);
        }
    }
    public String get(int key){
        Entry e=map.get(key);
        if(e==null) return null;
        touch(e);
        return e.value;
    }

    private static String dump(Level L){
        List<Integer> keys=new ArrayList<>();
        for(Entry e: L.pq) keys.add(e.key);
        Collections.sort(keys);
        return keys.toString();
    }

    public static void main(String[] args){
        MultiLevelCacheSystem c=new MultiLevelCacheSystem();
        c.put(1,"A"); c.put(2,"B"); c.put(3,"C");
        System.out.println("L1:"+dump(c.L1)+", L2:"+dump(c.L2)+", L3:"+dump(c.L3));
        c.get(1); c.get(1); c.get(2);
        System.out.println("L1:"+dump(c.L1)+", L2:"+dump(c.L2)+", L3:"+dump(c.L3));
        c.put(4,"D"); c.put(5,"E"); c.put(6,"F");
        System.out.println("L1:"+dump(c.L1)+", L2:"+dump(c.L2)+", L3:"+dump(c.L3));
    }
}
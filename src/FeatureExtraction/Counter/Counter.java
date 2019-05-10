package FeatureExtraction.Counter;

import java.util.*;
import java.util.Map.Entry;

public class Counter<K> implements Iterable<Entry<K, Integer>> {

    public static final int DEFAULT_CAPACITY = 16;
    private HashMap<K, Integer> counter;

    public Counter() {
        this(DEFAULT_CAPACITY);
    }

    public Counter(int initialCapacity) {
        counter = new HashMap<>(initialCapacity);
    }

    public Counter(Collection<K> c) {
        this();
        update(c);
    }

    public Counter(K[] arr) {
        this();
        update(arr);
    }

    public Counter(Iterable<K[]> c) {
        this();
        updateAll(c);
    }

    public void add(K element) {
        if (counter.containsKey(element))
            counter.put(element, counter.get(element) + 1);
        else
            counter.put(element, 1);
    }

    public void update(Collection<K> c) {
        for (K elem : c) {
            add(elem);
        }
    }

    public void update(K[] arr) {
        for (K elem : arr)
            add(elem);
    }

    public void updateAll(Iterable<K[]> c) {
        for (K[] arr : c) {
            update(arr);
        }
    }

    public int get(K key) {
        if (counter.containsKey(key))
            return counter.get(key);
        return 0;
    }

    @Override
    public Iterator<Entry<K, Integer>> iterator() {
        return counter.entrySet().iterator();
    }

    public Set<K> vocab() {
        return counter.keySet();
    }

    public int size() {
        return counter.size();
    }

}

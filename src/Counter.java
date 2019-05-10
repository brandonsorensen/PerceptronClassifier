import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
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

    public void add(K element) {
        if (counter.containsKey(element))
            counter.put(element, counter.get(element) + 1);
    }

    public void update(Iterable<K> c) {
        for (K elem : c)
            add(elem);
    }

    public void update(K[] arr) {
        for (K elem : arr)
            add(elem);
    }

    @Override
    public Iterator<Entry<K, Integer>> iterator() {
        return counter.entrySet().iterator();
    }

    public int size() {
        return counter.size();
    }
}

package FeatureExtraction.Counter;

import java.util.*;

/**
 * Counts the document frequency
 * @param <K> most likely a string, but works with any type
 */
public class DocumentCounter<K> extends Counter<K> {

    public DocumentCounter() {
        super();
    }

    public DocumentCounter(int initialCapacity) {
        super(initialCapacity);
    }

    public DocumentCounter(Collection<K> c) {
        super(c);
    }

    public DocumentCounter(K[] arr) {
        super(arr);
    }

    public DocumentCounter(Iterable<K[]> c) {
        super(c);
    }

    @Override
    public void update(Collection<K> c) {
        Set<K> docSet = new HashSet<>(c);
        for (K elem : c)
            add(elem);
    }

    @Override
    public void update(K[] arr) {
        Set<K> docSet = new HashSet<>();
        for (K elem : arr)
            docSet.add(elem);

        for (K elem : docSet)
            add(elem);
    }
}


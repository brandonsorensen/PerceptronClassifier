package Vectors;
import java.util.*;

/**
 * A compressed implementation of the <code>FeatureVector</code> interface.
 * Only stores non-zero values. Maintains a <code>Map</code> of non-zero indices
 * to their corresponding values. This represents a significant savings in memory
 * for vectors that are at all sparse. It also significantly speeds up vector multiplication
 * on sparse vectors.
 */
public class CompressedFeatureVector implements FeatureVector {

    private Map<Integer, Double> indexMap;
    private int length;

    CompressedFeatureVector(int length) {
        this(length, new HashMap<>());
    }

    CompressedFeatureVector(int length, Map<Integer, Double> indexMap) {
        this.length = length;
        this.indexMap = indexMap;
    }

    CompressedFeatureVector(double[] vector) {
        HashMap<Integer, Double> index = new HashMap<>();
        this.length = vector.length;

        for (int i = 0; i < length; i++) {
            double value = vector[i];
            if (value != 0)
                index.put(i, vector[i]);
        }
        this.indexMap = index;
    }

    CompressedFeatureVector(List<Double> vector) {
        HashMap<Integer, Double> index = new HashMap<>();
        this.length = vector.size();

        for (int i = 0; i < length; i++) {
            double value = vector.get(i);
            if (value != 0)
                index.put(i, vector.get(i));
        }
        this.indexMap = index;
    }

    @Override
    public double dot(FeatureVector other) {
        double dotProduct = 0.0;

        for (int index : indexMap.keySet()) {
            dotProduct += get(index) * other.get(index);
        }

        return dotProduct;
    }

    @Override
    public CompressedFeatureVector addition(FeatureVector other) {
        HashMap<Integer, Double> retMap = new HashMap<>();
        for (int i = 0; i < this.size(); i++)
            retMap.put(i, this.get(i) + other.get(i));
        return new CompressedFeatureVector(length, retMap);
    }

    @Override
    public FeatureVector addition(double scalar) {
        return null;
    }

    @Override
    public FeatureVector subtract(FeatureVector other) {
        return null;
    }

    @Override
    public CompressedFeatureVector multiply(FeatureVector other) {
        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair : indexMap.entrySet()) {
            int index = pair.getKey();
            double val = pair.getValue();
            updatedIndexMap.put(index, val * other.get(index));
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
    }

    @Override
    public CompressedFeatureVector multiply(double scalar) {
        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair: indexMap.entrySet()) {
            updatedIndexMap.put(pair.getKey(), pair.getValue() * scalar);
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
    }

    @Override
    public FeatureVector divide(FeatureVector other) {
        return null;
    }

    @Override
    public CompressedFeatureVector divide(double scalar) {
        if (scalar == 0)
            throw new IllegalArgumentException("Argument 'scalar' is 0");

        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair : indexMap.entrySet()) {
            updatedIndexMap.put(pair.getKey(), pair.getValue() / scalar);
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
    }

    @Override
    public FeatureVector pow(FeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector pow(double scalar) {
        return null;
    }

    @Override
    public double sum() {
        double sum = 0.0;
        for (double val : indexMap.values()) {
            sum += val;
        }
        return sum;
    }

    @Override
    public double product() {
        double prod = 0.0;
        for (double val : indexMap.values()) {
            prod *= val;
        }
        return prod;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public double get(int index) {
        if (indexMap.containsKey(index)) {
            return indexMap.get(index);
        }
        return 0.0;
    }

    @Override
    public void set(int index, double val) {
        if (indexMap.containsKey(index))
            indexMap.put(index, val);
        else
            indexMap.put(index, indexMap.get(index) + val);
    }

    @Override
    public SparseFeatureVector changeState() {
        return sparsify();
    }

    public SparseFeatureVector sparsify() {
        return new SparseFeatureVector(getVector());
    }

    @Override
    public boolean isCompressed() { return true; }

    public void update(CompressedFeatureVector other) {
        indexMap = other.indexMap;
        length = other.size();
    }

    @Override
    public Iterator<Double> iterator() {
        return new CompressedIterator(this);
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(Double aDouble) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public double[] getVector() {
        double[] vector = new double[length];
        Iterator<Double> iter = iterator();
        for (int i=0; i < length; i++) {
            vector[i] = iter.next();
        }
        return vector;
    }

    public Set<Integer> nonZeros() {
        return indexMap.keySet();
    }

    public int[] nonZeroIndices() {
        int[] retArray = new int[indexMap.size()];
        int currentIndex = 0;
        for (int index : indexMap.keySet()) {
            retArray[currentIndex] = index;
            currentIndex++;
        }
        return retArray;
    }

    public void zero() {
        indexMap = new HashMap<>();
    }

    @Override
    public boolean contains(Object o) { return indexMap.keySet().contains(o); }

    @Override
    public boolean containsAll(Collection<?> c) { return indexMap.keySet().containsAll(c); }

    @Override
    public boolean addAll(Collection<? extends Double> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    private class CompressedIterator implements Iterator<Double> {

        private CompressedFeatureVector vector;
        private Set<Integer> nonZeroIndices;
        private int currentIndex;

        CompressedIterator(CompressedFeatureVector vector) {
            this.vector = vector;
            nonZeroIndices = vector.nonZeros();
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() { return currentIndex < vector.size(); }

        @Override
        public Double next() {
            currentIndex++;
            if (nonZeroIndices.contains(currentIndex - 1))
                return vector.get(currentIndex - 1);
            else
                return 0.0;
        }
    }
}

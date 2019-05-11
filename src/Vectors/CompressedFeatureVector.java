package Vectors;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A compressed implementation of the <code>FeatureVector</code> interface.
 * Only stores non-zero values. Maintains a <code>Map</code> of non-zero indices
 * to their corresponding values. This represents a significant savings in memory
 * for vectors that are at all sparse. It also significantly speeds up vector multiplication
 * on sparse vectors.
 */
public class CompressedFeatureVector extends FeatureVector {

    private Map<Integer, Double> indexMap;
    private int length;
    private final boolean isCompressed = true;

    public CompressedFeatureVector(int length) {
        this(length, new HashMap<>());
    }

    public CompressedFeatureVector(int length, Map<Integer, Double> indexMap) {
        this.length = length;
        this.indexMap = indexMap;
    }

    public CompressedFeatureVector(double[] vector) {
        HashMap<Integer, Double> index = new HashMap<>();
        this.length = vector.length;

        for (int i = 0; i < length; i++) {
            double value = vector[i];
            if (value != 0)
                index.put(i, vector[i]);
        }
        this.indexMap = index;
    }

    public CompressedFeatureVector(List<Double> vector) {
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
        checkVectorSize(other);
        double dotProduct = 0.0;

        for (int index : indexMap.keySet()) {
            dotProduct += get(index) * other.get(index);
        }

        return dotProduct;
    }

    @Override
    public CompressedFeatureVector addition(FeatureVector other) {
        checkVectorSize(other);
        HashMap<Integer, Double> retMap = new HashMap<>();
        for (int i = 0; i < this.size(); i++)
            retMap.put(i, this.get(i) + other.get(i));
        return new CompressedFeatureVector(length, retMap);
    }

    // TODO
    @Override
    public FeatureVector addition(double scalar) {
        return null;
    }

    // TODO
    @Override
    public FeatureVector subtract(FeatureVector other) {
        return null;
    }

    @Override
    public CompressedFeatureVector multiply(FeatureVector other) {
        checkVectorSize(other);
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
    public void multiplyInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (Map.Entry<Integer, Double> pair: indexMap.entrySet()) {
            int index = pair.getKey();
            double value = pair.getValue();
            set(index, value * other.get(index));
        }
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
    public boolean contains(Object o) {
        return indexMap.values().contains(o);
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
        if (val == 0)
            return;

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
    public void update(FeatureVector other) {
        for (int i = 0; i < size(); i++) {
            double val = other.get(i);
            if (val != 0)
                set(i, val);
        }
    }

    public void update(CompressedFeatureVector other) {
        indexMap = other.indexMap;
        length = other.size();
    }

    @Override
    public Iterator<Double> iterator() {
        return new CompressedIterator(this);
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

    public List<Double> nonZeros() {
        ArrayList<Double> retArray = new ArrayList<>(indexMap.size());
        retArray.addAll(indexMap.values());
        return retArray;
    }

    public List<Integer> nonZeroIndices() {
        ArrayList<Integer> retArray = new ArrayList<>(indexMap.size());
        retArray.addAll(indexMap.keySet());
        Collections.sort(retArray);
        return retArray;
    }

    @Override
    public void zero() {
        indexMap = new HashMap<>();
    }

    public static CompressedFeatureVector randomInitialize(int length, double sparsity) {
        if (sparsity < 0 || sparsity > 1)
            throw new IllegalArgumentException("sparsity must be between 0 and 1");

        int numNonZero = (int) ((1 - sparsity) * length);
        HashMap<Integer, Double> randIndices = new HashMap<>();
        for (int i = 0; i < numNonZero; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, length);
            double randValue = Math.random();
            while (randIndices.containsKey(randomIndex))
                randomIndex = ThreadLocalRandom.current().nextInt(0, length);
            randIndices.put(randomIndex, randValue);
        }
        return new CompressedFeatureVector(length, randIndices);
    }

    private class CompressedIterator implements Iterator<Double> {

        private CompressedFeatureVector vector;
        private List<Integer> nonZeroIndices;
        private int currentIndex;

        CompressedIterator(CompressedFeatureVector vector) {
            this.vector = vector;
            nonZeroIndices = vector.nonZeroIndices();
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

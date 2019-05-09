package Vectors;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.lang.Math;
import java.util.Set;

public class CompressedFeatureVector implements FeatureVector {

    private Map<Integer, Double> indexMap;
    private int length;

    CompressedFeatureVector(int length) {
        this(length, new HashMap<Integer, Double>());
    }

    CompressedFeatureVector(int length, Map<Integer, Double> indexMap) {
        this.length = length;
        this.indexMap = indexMap;
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
    public FeatureVector add(CompressedFeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector add(SparseFeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector add(int scalar) {
        return null;
    }

    @Override
    public FeatureVector add(double scalar) {
        return null;
    }

    @Override
    public FeatureVector subtract(CompressedFeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector subtract(SparseFeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector subtract(int scalar) {
        return null;
    }

    @Override
    public FeatureVector subtract(double scalar) {
        return null;
    }

    public FeatureVector multiply(FeatureVector other) {
        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair : indexMap.entrySet()) {
            int index = pair.getKey();
            double val = pair.getValue();
            updatedIndexMap.put(index, val * other.get(index));
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
    }

    @Override
    public FeatureVector multiply(CompressedFeatureVector other) {
        return multiply((FeatureVector) other);
    }

    @Override
    public FeatureVector multiply(SparseFeatureVector other) {
        return multiply((FeatureVector) other);
    }

    @Override
    public FeatureVector multiply(int scalar) {
        return multiply((double) scalar);
    }

    @Override
    public FeatureVector multiply(double scalar) {
        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair: indexMap.entrySet()) {
            updatedIndexMap.put(pair.getKey(), pair.getValue() * scalar);
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
    }

    @Override
    public FeatureVector divide(CompressedFeatureVector other) {
        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair : indexMap.entrySet()) {
            int index = pair.getKey();
            double val = pair.getValue();
            updatedIndexMap.put(index, val / other.get(index));
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
    }

    @Override
    public FeatureVector divide(SparseFeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector divide(int scalar) {
        return divide((double) scalar);
    }

    @Override
    public FeatureVector divide(double scalar) {
        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair : indexMap.entrySet()) {
            updatedIndexMap.put(pair.getKey(), pair.getValue() / scalar);
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
    }

    @Override
    public FeatureVector pow(int scalar) {
        return pow((float) scalar);
    }

    @Override
    public FeatureVector pow(double scalar) {
        HashMap<Integer, Double> updatedIndexMap = new HashMap<>();
        for (Map.Entry<Integer, Double> pair : indexMap.entrySet()) {
            updatedIndexMap.put(pair.getKey(), Math.pow(pair.getValue(), scalar));
        }
        return new CompressedFeatureVector(length, updatedIndexMap);
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
    public int getLength() {
        return length;
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
    public FeatureVector changeState() {
        return sparsify();
    }

    public SparseFeatureVector sparsify() {
        return null;
    }

    @Override
    public boolean isCompressed() { return true; }

    @Override
    public void update(FeatureVector other) {

    }

    public Set<Integer> nonZeros() {
        return indexMap.keySet();
    }

    @Override
    public Iterator<Double> iterator() {
        return null;
    }
}

class CompressedIterator implements Iterator<Double> {

    private CompressedFeatureVector vector;
    private Set<Integer> nonZeroIndices;
    private int currentIndex;

    CompressedIterator(CompressedFeatureVector vector) {
        this.vector = vector;
        nonZeroIndices = vector.nonZeros();
        currentIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < vector.getLength();
    }

    @Override
    public Double next() {
        if (nonZeroIndices.contains(currentIndex))
            return vector.get(currentIndex);
        else
            return 0.0;
    }
}

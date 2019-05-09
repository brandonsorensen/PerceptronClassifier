package Vectors;
import java.util.*;
import java.lang.Math;

public class CompressedFeatureVector extends FeatureVector {

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
    public int size() {
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

    public FeatureVector sparsify() {
        return new FeatureVector(getVector());
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

    @Override
    public boolean contains(Object o) { return indexMap.keySet().contains(o); }

    @Override
    public boolean containsAll(Collection<?> c) { return indexMap.keySet().containsAll(c); }

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
    public boolean hasNext() {
        return currentIndex < vector.size();
    }

    @Override
    public Double next() {
        if (nonZeroIndices.contains(currentIndex))
            return vector.get(currentIndex);
        else
            return 0.0;
    }

}

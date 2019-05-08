package Vectors;
import java.util.HashMap;
import java.util.Map;

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
    public double cosineSimilarity(FeatureVector other) {
        return 0;
    }

    @Override
    public FeatureVector add(FeatureVector other) {
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
    public FeatureVector subtract(FeatureVector other) {
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

    @Override
    public FeatureVector multiply(FeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector multiply(int scalar) {
        return null;
    }

    @Override
    public FeatureVector multiply(double scalar) {
        return null;
    }

    @Override
    public FeatureVector divide(FeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector divide(int scalar) {
        return null;
    }

    @Override
    public FeatureVector divide(double scalar) {
        return null;
    }

    @Override
    public FeatureVector pow(int scalar) {
        return null;
    }

    @Override
    public FeatureVector pow(double scalar) {
        return null;
    }

    @Override
    public double get(int index) {
        return 0;
    }

    @Override
    public void set() {

    }

    @Override
    public FeatureVector changeState() {
        return null;
    }

    @Override
    public boolean isCompressed() { return true; }

    @Override
    public void update(FeatureVector other) {

    }
}

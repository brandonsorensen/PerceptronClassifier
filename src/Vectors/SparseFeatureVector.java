package Vectors;

public class SparseFeatureVector implements FeatureVector {
    @Override
    public double dot(FeatureVector other) {
        return 0;
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
    public double get() {
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
    public boolean isCompressed() {
        return false;
    }
}

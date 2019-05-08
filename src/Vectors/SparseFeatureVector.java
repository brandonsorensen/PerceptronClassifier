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

    @Override
    public FeatureVector multiply(CompressedFeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector multiply(SparseFeatureVector other) {
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
    public FeatureVector divide(CompressedFeatureVector other) {
        return null;
    }

    @Override
    public FeatureVector divide(SparseFeatureVector other) {
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
    public double sum() {
        return 0;
    }

    @Override
    public double product() {
        return 0;
    }

    @Override
    public int getLength() {
        return 0;
    }

    @Override
    public double get(int index) {
        return 0;
    }

    @Override
    public void set(int index, double val) {

    }

    @Override
    public FeatureVector changeState() {
        return null;
    }

    @Override
    public boolean isCompressed() {
        return false;
    }

    @Override
    public void update(FeatureVector other) {

    }
}

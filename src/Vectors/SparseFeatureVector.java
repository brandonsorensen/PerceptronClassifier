package Vectors;
import java.lang.reflect.Array;
import java.util.*;

public class SparseFeatureVector implements FeatureVector {

    private double[] vector;

    /**
     * Initializes an empty vector of a given length.
     * @param length the length of the vector
     */
    SparseFeatureVector(int length) {
        vector = new double[length];
    }

    SparseFeatureVector(List<Double> vector) {
        this.vector = new double[vector.size()];
        for (int i = 0; i < vector.size(); i++)
            this.vector[i] = vector.get(i);
    }

    SparseFeatureVector(double[] vector) {
        this.vector = vector;
    }

    @Override
    public FeatureVector add(CompressedFeatureVector other) {
        return other.add(this);
    }

    @Override
    public FeatureVector add(SparseFeatureVector other) {
        double[] retVector = new double[getLength()];
        for (int i = 0; i < getLength(); i++)
            retVector[i] = this.get(i) +  other.get(i);
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector add(int scalar) {
        return add((double) scalar);
    }

    @Override
    public FeatureVector add(double scalar) {
        double[] retVector = new double[getLength()];
        for (int i = 0; i < getLength(); i++)
            retVector[i] = this.get(i) + scalar;
        return new SparseFeatureVector(retVector);
    }

    public FeatureVector subtract(FeatureVector other) {
        double[] retVector = new double[getLength()];
        for (int i = 0; i < getLength(); i++)
            retVector[i] = this.get(i) - other.get(i);
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector subtract(CompressedFeatureVector other) {
        return subtract((FeatureVector) other);
    }

    @Override
    public FeatureVector subtract(SparseFeatureVector other) {
        return subtract((FeatureVector) other);
    }

    @Override
    public FeatureVector subtract(int scalar) {
        return add(-scalar);
    }

    @Override
    public FeatureVector subtract(double scalar) {
        return add(-scalar);
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
        return vector.length;
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

    public CompressedFeatureVector compressed() {
        Map<Integer, Double> nonZeroIndices = new HashMap<>();
        for (int i = 0; i < getLength(); i++) {
            double value = get(i);
            if (value != 0)
                nonZeroIndices.put(i, value);
        }
        return new CompressedFeatureVector(getLength(), nonZeroIndices);
    }

    @Override
    public boolean isCompressed() {
        return false;
    }

    @Override
    public void update(FeatureVector other) {
        vector = other.getVector();
    }

    public double[] getVector() {
        return vector;
    }

    public Iterator<Double> iterator() {
        List array = Arrays.asList(vector);
        return (Iterator<Double>) array.iterator();
    }
}

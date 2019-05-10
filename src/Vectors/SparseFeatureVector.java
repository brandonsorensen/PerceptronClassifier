package Vectors;
import java.util.*;

public class SparseFeatureVector extends FeatureVector {

    private double[] vector;
    private final boolean isCompressed = false;

    /**
     * Initializes a vector containing all zeros of a given length.
     * @param length the length of the vector
     */
    public SparseFeatureVector(int length) {
        vector = new double[length];
    }

    public SparseFeatureVector(List<Double> vector) {
        this.vector = new double[vector.size()];
        for (int i = 0; i < vector.size(); i++)
            this.vector[i] = vector.get(i);
    }

    public SparseFeatureVector(double[] vector) {
        this.vector = vector;
    }

    public double dot(CompressedFeatureVector other) {
        return other.dot(this);
    }

    @Override
    public FeatureVector addition(FeatureVector other) {
        checkVectorSize(other);
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) +  other.get(i);
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector addition(double scalar) {
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) + scalar;
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector subtract(FeatureVector other) {
        checkVectorSize(other);
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) - other.get(i);
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector multiply(FeatureVector other) {
        checkVectorSize(other);
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) * other.get(i);
        return new SparseFeatureVector(retVector);
    }

    public FeatureVector multiply(CompressedFeatureVector other) {
        return other.multiply(this);
    }

    @Override
    public FeatureVector multiply(double scalar) {
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) * scalar;
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector divide(FeatureVector other) {
        checkVectorSize(other);
        double[] retVector = new double[size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) / other.get(i);
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector divide(double scalar) {
        double[] retVector = new double[size()];
        for (int i = 0; i < size(); i++)
            retVector[i] = get(i) / scalar;
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector pow(FeatureVector other) {
        checkVectorSize(other);
        double[] retVector = new double[size()];
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), other.get(i)));
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector pow(double scalar) {
        double[] retVector = new double[size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = Math.pow(this.get(i), scalar);
        return new SparseFeatureVector(retVector);
    }

    public void powInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), other.get(i)));
    }

    @Override
    public int size() {
        return vector.length;
    }

    public double get(int index) {
        return vector[index];
    }

    public void set(int index, double val) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        vector[index] = val;
    }

    public CompressedFeatureVector changeState() {
        return compress();
    }

    public CompressedFeatureVector compress() {
        Map<Integer, Double> nonZeroIndices = new HashMap<>();
        for (int i = 0; i < this.size(); i++) {
            double value = get(i);
            if (value != 0)
                nonZeroIndices.put(i, value);
        }
        return new CompressedFeatureVector(this.size(), nonZeroIndices);
    }

    public void update(FeatureVector other) {
        vector = other.getVector();
    }

    public double[] getVector() {
        return vector;
    }

    public void zero() {
        for (int i = 0; i < size(); i++) {
            set(i, 0);
        }
    }

    @Override
    public boolean isEmpty() { return size() == 0; }

    @Override
    public boolean contains(Object o) {
        for (Double val : this) {
            if (val.equals(o))
                return true;
        }
        return false;
    }

    public Iterator<Double> iterator() {
        List array = Arrays.asList(vector);
        return (Iterator<Double>) array.iterator();
    }

    public static SparseFeatureVector randomInitialize(int length) {
        double[] randArray = new double[length];
        for (int i = 0; i < length; i++)
            randArray[i] = Math.random();
        return new SparseFeatureVector(randArray);
    }
}

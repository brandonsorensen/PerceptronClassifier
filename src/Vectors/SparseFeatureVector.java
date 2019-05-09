package Vectors;
import java.util.*;

// TODO: Does it make sense to inherit from Collection?
public class SparseFeatureVector implements FeatureVector {

    private double[] vector;

    /**
     * Initializes an empty vector.
     */
    SparseFeatureVector() {
        vector = new double[0];
    }

    /**
     * Initializes a vector containing all zeros of a given length.
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

    public double dot(CompressedFeatureVector other) {
        return other.dot(this);
    }

    @Override
    public FeatureVector addition(FeatureVector other) {
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
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) - other.get(i);
        return new SparseFeatureVector(retVector);
    }

    @Override
    public FeatureVector multiply(FeatureVector other) {
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

    public void powInPlace(SparseFeatureVector other) {
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

    public boolean isCompressed() {
        return false;
    }

    /**
     * Updates the values of this vector to match those of another.
     * @param other another <code>SparseFeatureVector</code>
     */
    public void update(SparseFeatureVector other) {
        vector = other.getVector();
    }

    public double[] getVector() {
        return vector;
    }

    /**
     * Sets all elements to zero.
     */
    public void zero() {
        for (int i = 0; i < size(); i++) {
            vector[i] = 0.0;
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

    @Override
    public Object[] toArray() {
        Object[] asObject = new Object[size()];
        for (int i = 0; i < size(); i++)
            asObject[i] = get(i);
        return asObject;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        T[] asGeneric = new T[size()];
        return (T[]) vector;
    }

    @Override
    public boolean add(Double aDouble) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem : c) {
            if (!contains(elem))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Double> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    /**
     * Resets all elements in the vector to zero.
     */
    @Override
    public void clear() {
        zero();
    }

    public static SparseFeatureVector randomInitialize(int length) {
        double[] randArray = new double[length];
        for (int i = 0; i < length; i++)
            randArray[i] = Math.random();
        return new SparseFeatureVector(randArray);
    }
}

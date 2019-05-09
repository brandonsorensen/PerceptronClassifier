package Vectors;
import java.util.*;

public class FeatureVector implements Collection<Double>, Iterable<Double> {

    private double[] vector;

    /**
     * Initializes an empty vector of a given length.
     * @param length the length of the vector
     */
    FeatureVector(int length) {
        vector = new double[length];
    }

    FeatureVector(List<Double> vector) {
        this.vector = new double[vector.size()];
        for (int i = 0; i < vector.size(); i++)
            this.vector[i] = vector.get(i);
    }

    FeatureVector(double[] vector) {
        this.vector = vector;
    }
    
    /**
     * Calculates the cosine similarity of two vectors.
     * @param other another vector
     * @return the cosine similarity of this and another vector
     */
    double cosineSimilarity(FeatureVector other) {
        double dotProduct = dot(other);
        double euclideanLengthA = pow(2).sum();
        double euclideanLengthB = other.pow(2).sum();

        return dotProduct / (Math.sqrt(euclideanLengthA) * Math.sqrt(euclideanLengthB));
    }

    public double dot(FeatureVector other) {
        double dotProduct = 0.0;
        for (int i = 0; i < this.size(); i++)
            dotProduct += get(i) * other.get(i);
        return dotProduct;
    }

    public double dot(CompressedFeatureVector other) {
        return other.dot(this);
    }

    public FeatureVector addition(FeatureVector other) {
        return null;
    }

    public void additionInPlace(FeatureVector other) {

    }

    public void additionInPlace(double scalar) {

    }

    public FeatureVector addition(CompressedFeatureVector other) {
        return other.add(this);
    }

    public FeatureVector addition(FeatureVector other) {
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) +  other.get(i);
        return new FeatureVector(retVector);
    }
    FeatureVector addition(int scalar) { return addition((double) scalar); }

    public FeatureVector addition(double scalar) {
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) + scalar;
        return new FeatureVector(retVector);
    }

    public FeatureVector subtract(FeatureVector other) {
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) - other.get(i);
        return new FeatureVector(retVector);
    }

    public FeatureVector subtract(CompressedFeatureVector other) {
        return subtract((FeatureVector) other);
    }

    public FeatureVector subtract(FeatureVector other) {
        return subtract((FeatureVector) other);
    }

    public FeatureVector subtract(int scalar) {
        return addition(-scalar);
    }

    public FeatureVector subtract(double scalar) {
        return addition(-scalar);
    }

    public void subtractInPlace(FeatureVector other) {

    }

    public void subtractInPlace(double scalar) {

    }

    public FeatureVector multiply(FeatureVector other) {
        return null;
    }

    public FeatureVector multiply(CompressedFeatureVector other) {
        return null;
    }

    public FeatureVector multiply(FeatureVector other) {
        return null;
    }

    public FeatureVector multiply(int scalar) {
        return null;
    }

    public FeatureVector multiply(double scalar) {
        return null;
    }

    public void multiplyInPlace(FeatureVector other) {

    }

    public void multiplyInPlace(double scalar) {

    }

    public FeatureVector divide(FeatureVector other) {
        return null;
    }

    public FeatureVector divide(CompressedFeatureVector other) {
        return null;
    }

    public FeatureVector divide(FeatureVector other) {
        return null;
    }

    public FeatureVector divide(int scalar) {
        return null;
    }

    public FeatureVector divide(double scalar) {
        return null;
    }

    public void divideInPlace(FeatureVector other) {

    }

    public void divideInPlace(double scalar) {

    }

    public FeatureVector pow(FeatureVector other) {
        return null;
    }

    public FeatureVector pow(int scalar) {
        return null;
    }

    public FeatureVector pow(double scalar) {
        return null;
    }

    public void powInPlace(FeatureVector other) {

    }

    public void powInPlace(double scalar) {

    }

    public double sum() {
        int sum = 0;
        for (double val : this)
            sum += val;
        return sum;
    }

    @Override
    public double product() {
        double product = 0.0;
        for (double val : this)
            product *= val;
        return product;
    }

    @Override
    public int size() {
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

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
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
        return false;
    }

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
}

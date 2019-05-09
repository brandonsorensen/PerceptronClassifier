package Vectors;
import java.util.*;

// TODO: Does it make sense to inherit from Collection?
public class FeatureVector implements Collection<Double>, Iterable<Double> {

    private double[] vector;

    /**
     * Initializes an empty vector.
     */
    FeatureVector() {
        vector = new double[0];
    }

    /**
     * Initializes a vector containing all zeros of a given length.
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
        double dotProduct = 0.0, euclideanLengthA = 0.0, euclideanLengthB = 0.0;
        for (int i = 0; i < size(); i++) {
            double thisValue = get(i);
            double otherValue = other.get(i);

            dotProduct += thisValue * otherValue;
            euclideanLengthA += Math.pow(thisValue, 2);
            euclideanLengthB += Math.pow(otherValue, 2);
        }

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

    public void additionInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) + other.get(i));
    }

    public void additionInPlace(int scalar) { additionInPlace((double) scalar); }

    public void additionInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) + scalar);
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

    public FeatureVector subtract(int scalar) {
        return addition(-scalar);
    }

    public FeatureVector subtract(double scalar) {
        return addition(-scalar);
    }

    public void subtractInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) - other.get(i));
    }

    public void subtractInPlace(int scalar) {
        subtract((double) scalar);
    }

    public void subtractInPlace(double scalar) {
        additionInPlace(-scalar);
    }

    public FeatureVector multiply(FeatureVector other) {
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) * other.get(i);
        return new FeatureVector(retVector);
    }

    public FeatureVector multiply(CompressedFeatureVector other) {
        return other.multiply(this);
    }

    public FeatureVector multiply(int scalar) {
        return multiply((double) scalar);
    }

    public FeatureVector multiply(double scalar) {
        double[] retVector = new double[this.size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) * scalar;
        return new FeatureVector(retVector);
    }

    public void multiplyInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) * other.get(i));
    }

    public void multiplyInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) * scalar);
    }

    public FeatureVector divide(FeatureVector other) {
        double[] retVector = new double[size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = this.get(i) / other.get(i);
        return new FeatureVector(retVector);
    }

    public FeatureVector divide(int scalar) { return divide((double) scalar); }

    public FeatureVector divide(double scalar) {
        double[] retVector = new double[size()];
        for (int i = 0; i < size(); i++)
            retVector[i] = get(i) / scalar;
        return new FeatureVector(retVector);
    }

    public void divideInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) / other.get(i));
    }

    public void divideInPlace(int scalar) { divideInPlace((double) scalar); }

    public void divideInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) / scalar);
    }

    public FeatureVector pow(FeatureVector other) {
        double[] retVector = new double[size()];
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), other.get(i)));
        return new FeatureVector(retVector);
    }

    public FeatureVector pow(int scalar) { return pow((double) scalar); }

    public FeatureVector pow(double scalar) {
        double[] retVector = new double[size()];
        for (int i = 0; i < this.size(); i++)
            retVector[i] = Math.pow(this.get(i), other.get(i));
        return new FeatureVector(retVector);

    }

    public void powInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), other.get(i)));
    }

    public void powInPlace(int scalar) {powInPlace((double) scalar);}

    public void powInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), scalar));
    }

    public double sum() {
        int sum = 0;
        for (double val : this)
            sum += val;
        return sum;
    }

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

    public double get(int index) {
        return vector[index];
    }

    public void set(int index, double val) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException();
        vector[index] = val;
    }

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

    public boolean isCompressed() {
        return false;
    }

    public void update(FeatureVector other) {
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

    public static FeatureVector randomInitialize(int length) {
        double[] randArray = new double[length];
        for (int i = 0; i < length; i++)
            randArray[i] = Math.random();
        return new FeatureVector(randArray);
    }
}

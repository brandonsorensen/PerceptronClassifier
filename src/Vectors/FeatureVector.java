package Vectors;

import java.util.*;

abstract class FeatureVector extends AbstractCollection<Double> implements Iterable<Double> {
    private boolean isCompressed;
    /**
     * Calculates the cosine similarity of two vectors.
     *
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

    /**
     * Calculates the dot product of two vectors.
     * @param other another vector
     * @return the dot product of the two vectors
     */
    double dot(FeatureVector other) {
        checkVectorSize(other);
        double dotProduct = 0.0;
        for (int i = 0; i < this.size(); i++)
            dotProduct += get(i) * other.get(i);
        return dotProduct;
    }

    void checkVectorSize(FeatureVector other) {
        if (other.size() != size()) {
            throw new IllegalArgumentException("Vectors are not of equal lengths");
        }
    }

    void additionInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) + other.get(i));
    }

    void additionInPlace(int scalar) {
        additionInPlace((double) scalar);
    }

    void additionInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) + scalar);
    }

    abstract FeatureVector addition(FeatureVector other);

    FeatureVector addition(int scalar) {
        return addition((double) scalar);
    }

    abstract FeatureVector addition(double scalar);

    abstract FeatureVector subtract(FeatureVector other);

    FeatureVector subtract(int scalar) {
        return subtract((double) scalar);
    }

    FeatureVector subtract(double scalar) {
        return addition(-scalar);
    }

    void subtractInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) - other.get(i));
    }

    void subtractInPlace(int scalar) {
        subtract((double) scalar);
    }

    void subtractInPlace(double scalar) {
        additionInPlace(-scalar);
    }

    abstract FeatureVector multiply(FeatureVector other);

    FeatureVector multiply(int scalar) {
        return multiply((double) scalar);
    }

    abstract FeatureVector multiply(double scalar);

    void multiplyInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) * other.get(i));
    }

    void multiplyInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) * scalar);
    }

    abstract FeatureVector divide(FeatureVector other);

    FeatureVector divide(int scalar) {
        return divide((double) scalar);
    }

    abstract FeatureVector divide(double scalar);

    void divideInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) / other.get(i));
    }

    void divideInPlace(int scalar) {
        divideInPlace((double) scalar);
    }

    void divideInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) / scalar);
    }

    abstract FeatureVector pow(FeatureVector other);

    FeatureVector pow(int scalar) {
        return pow((double) scalar);
    }

    abstract FeatureVector pow(double scalar);

    void powInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), other.get(i)));
    }

    /**
     * Takes every dimension of the vector to the power of a given value
     * @param scalar a given scalar
     */
    void powInPlace(int scalar) {
        powInPlace((double) scalar);
    }

    void powInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), scalar));
    }

    /**
     * Gets the sum of all the values in the vector
     * @return the sum of all the values in the vector.
     */
    double sum() {
        int sum = 0;
        for (double val : this)
            sum += val;
        return sum;
    }

    /**
     * Gets the inner product of the vector
     * @return the inner product of the vector
     */
    double product() {
        double product = 0.0;
        for (double val : this)
            product *= val;
        return product;
    }

    /**
     * Sets all elements to zero.
     */
    void zero() {
        for (int i = 0; i < size(); i++)
            set(i, 0);
    }

    List<Double> nonZeroValues() {
        LinkedList<Double> retVal = new LinkedList<>();
        for (double val : this)
            if (val != 0.0)
                retVal.add(val);
        return retVal;
    }

    /**
     * Returns all indices at which the value is not zero
     * @return all indices at which the value is not zero
     */
    List<Integer> nonZeroIndices() {
        LinkedList<Integer> retVal = new LinkedList<>();
        for (int i = 0; i < size(); i++) {
            if (get(i) != 0)
                retVal.add(i);
        }
        return retVal;
    }

    /**
     * Get the value at a given dimension.
     * @param index the given index
     * @return the value at a given index
     */
    abstract double get(int index);

    /**
     * Sets the value at a given index.
     * @param index the index to change
     * @param val the value to update index
     */
    abstract void set(int index, double val);

    /**
     * Returns a <code>CompressedFeatureVector</code> if the current feature of
     * type <code>SparseFeatureVector</code> and vice versa.
     * @return
     */
    abstract FeatureVector changeState();

    /**
     * Updates the values of this vector to match those of another.
     * @param other another <code>SparseFeatureVector</code>
     */
    abstract void update(FeatureVector other);

    /**
     * Whether the vector is compressed or sparse.
     * @return whether the vector is compressed or sparse.
     */
    boolean isCompressed() {
        return isCompressed;
    }

    /**
     * Gets the internal array of the vector.
     * @return the internal double array of the vector.
     */
    abstract double[] getVector();

    @Override
    abstract public Iterator<Double> iterator();

    @Override
    public void clear() {
        zero();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("FeatureVectors cannot change length.");
    }

    public boolean addAll(Collection c) {
        throw new UnsupportedOperationException("FeatureVectors cannot change length.");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("FeatureVectors cannot change length.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("FeatureVectors cannot change length.");
    }
}

package Vectors;

import java.util.*;

/**
 * An unmodifiable, iterable collection that represents a vector of features for use
 * in a machine learning context. Being unmodifiable, it offers no way to add or remove
 * elements from a collection. The <code>clear</code> method, contrary to the typical
 * behavior of an unmodifiable collection, sets all values in the vector to zero and
 * is as such indistinguishable from the <code>zero</code> method.
 */
abstract public class FeatureVector extends AbstractCollection<Double> {
    private boolean isCompressed;

    /**
     * Calculates the cosine similarity of two vectors.
     * @param other another vector
     * @return the cosine similarity of this and another vector
     */
    public double cosineSimilarity(FeatureVector other) {
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
    public double dot(FeatureVector other) {
        checkVectorSize(other);
        double dotProduct = 0.0;
        for (int i = 0; i < this.size(); i++)
            dotProduct += get(i) * other.get(i);
        return dotProduct;
    }

    public void checkVectorSize(FeatureVector other) {
        if (other.size() != size()) {
            throw new IllegalArgumentException("Vectors are not of equal lengths");
        }
    }

    public void additionInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) + other.get(i));
    }

    public void additionInPlace(int scalar) {
        additionInPlace((double) scalar);
    }

    public void additionInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) + scalar);
    }

    public abstract FeatureVector addition(FeatureVector other);

    public FeatureVector addition(int scalar) {
        return addition((double) scalar);
    }

    public abstract FeatureVector addition(double scalar);

    public abstract FeatureVector subtract(FeatureVector other);

    public FeatureVector subtract(int scalar) {
        return subtract((double) scalar);
    }

    public FeatureVector subtract(double scalar) {
        return addition(-scalar);
    }

    public void subtractInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) - other.get(i));
    }

    public void subtractInPlace(int scalar) {
        subtract((double) scalar);
    }

    public void subtractInPlace(double scalar) {
        additionInPlace(-scalar);
    }

    public abstract FeatureVector multiply(FeatureVector other);

    public FeatureVector multiply(int scalar) {
        return multiply((double) scalar);
    }

    public abstract FeatureVector multiply(double scalar);

    public void multiplyInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) * other.get(i));
    }

    public void multiplyInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) * scalar);
    }

    public abstract FeatureVector divide(FeatureVector other);

    public FeatureVector divide(int scalar) {
        return divide((double) scalar);
    }

    public abstract FeatureVector divide(double scalar);

    public void divideInPlace(FeatureVector other) {
        checkVectorSize(other);
        for (int i = 0; i < size(); i++)
            set(i, get(i) / other.get(i));
    }

    public void divideInPlace(int scalar) {
        divideInPlace((double) scalar);
    }

    public void divideInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) / scalar);
    }

    public abstract FeatureVector pow(FeatureVector other);

    public FeatureVector pow(int scalar) {
        return pow((double) scalar);
    }

    public abstract FeatureVector pow(double scalar);

    public void powInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), other.get(i)));
    }

    /**
     * Takes every dimension of the vector to the power of a given value
     * @param scalar a given scalar
     */
    public void powInPlace(int scalar) {
        powInPlace((double) scalar);
    }

    public void powInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), scalar));
    }

    /**
     * Gets the sum of all the values in the vector
     * @return the sum of all the values in the vector.
     */
    public double sum() {
        int sum = 0;
        for (double val : this)
            sum += val;
        return sum;
    }

    /**
     * Gets the inner product of the vector
     * @return the inner product of the vector
     */
    public double product() {
        double product = 0.0;
        for (double val : this)
            product *= val;
        return product;
    }

    /**
     * Sets all elements to zero.
     */
    public void zero() {
        for (int i = 0; i < size(); i++)
            set(i, 0);
    }

    public List<Double> nonZeroValues() {
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
    public List<Integer> nonZeroIndices() {
        LinkedList<Integer> retVal = new LinkedList<>();
        for (int i = 0; i < size(); i++) {
            if (get(i) != 0)
                retVal.add(i);
        }
        Collections.sort(retVal);
        return retVal;
    }

    /**
     * Get the value at a given dimension.
     * @param index the given index
     * @return the value at a given index
     */
    public abstract double get(int index);

    /**
     * Sets the value at a given index.
     * @param index the index to change
     * @param val the value to update index
     */
    public abstract void set(int index, double val);

    /**
     * Returns a <code>CompressedFeatureVector</code> if the current feature of
     * type <code>SparseFeatureVector</code> and vice versa.
     * @return
     */
    public abstract FeatureVector changeState();

    /**
     * Updates the values of this vector to match those of another.
     * @param other another <code>SparseFeatureVector</code>
     */
    public abstract void update(FeatureVector other);

    /**
     * Whether the vector is compressed or sparse.
     * @return whether the vector is compressed or sparse.
     */
    public boolean isCompressed() {
        return isCompressed;
    }

    /**
     * Gets the internal array of the vector.
     * @return the internal double array of the vector.
     */
    public abstract double[] getVector();

    @Override
    public abstract Iterator<Double> iterator();

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

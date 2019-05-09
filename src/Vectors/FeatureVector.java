package Vectors;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

interface FeatureVector extends Collection<Double>, Iterable<Double> {
    /**
     * Calculates the cosine similarity of two vectors.
     *
     * @param other another vector
     * @return the cosine similarity of this and another vector
     */
    default double cosineSimilarity(FeatureVector other) {
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
    default double dot(FeatureVector other) {
        checkVectorSize(other);
        double dotProduct = 0.0;
        for (int i = 0; i < this.size(); i++)
            dotProduct += get(i) * other.get(i);
        return dotProduct;
    }

    default void checkVectorSize(FeatureVector other) {
        if (other.size() != size()) {
            throw new IllegalArgumentException("Vectors are not of equal lengths");
        }
    }

    default void additionInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) + other.get(i));
    }

    default void additionInPlace(int scalar) {
        additionInPlace((double) scalar);
    }

    default void additionInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) + scalar);
    }

    FeatureVector addition(FeatureVector other);

    default FeatureVector addition(int scalar) {
        return addition((double) scalar);
    }

    FeatureVector addition(double scalar);

    FeatureVector subtract(FeatureVector other);

    default FeatureVector subtract(int scalar) {
        return subtract((double) scalar);
    }

    default FeatureVector subtract(double scalar) {
        return addition(-scalar);
    }

    default void subtractInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) - other.get(i));
    }

    default void subtractInPlace(int scalar) {
        subtract((double) scalar);
    }

    default void subtractInPlace(double scalar) {
        additionInPlace(-scalar);
    }

    FeatureVector multiply(FeatureVector other);

    default FeatureVector multiply(int scalar) {
        return multiply((double) scalar);
    }

    FeatureVector multiply(double scalar);

    default void multiplyInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) * other.get(i));
    }

    default void multiplyInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) * scalar);
    }

    FeatureVector divide(FeatureVector other);

    default FeatureVector divide(int scalar) {
        return divide((double) scalar);
    }

    FeatureVector divide(double scalar);

    default void divideInPlace(SparseFeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) / other.get(i));
    }

    default void divideInPlace(int scalar) {
        divideInPlace((double) scalar);
    }

    default void divideInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, get(i) / scalar);
    }

    FeatureVector pow(FeatureVector other);

    default FeatureVector pow(int scalar) {
        return pow((double) scalar);
    }

    FeatureVector pow(double scalar);

    default void powInPlace(FeatureVector other) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), other.get(i)));
    }

    /**
     * Takes every dimension of the vector to the power of a given value
     * @param scalar a given scalar
     */
    default void powInPlace(int scalar) {
        powInPlace((double) scalar);
    }

    default void powInPlace(double scalar) {
        for (int i = 0; i < size(); i++)
            set(i, Math.pow(get(i), scalar));
    }

    /**
     * Gets the sum of all the values in the vector
     * @return the sum of all the values in the vector.
     */
    default double sum() {
        int sum = 0;
        for (double val : this)
            sum += val;
        return sum;
    }

    /**
     * Gets the inner product of the vector
     * @return the inner product of the vector
     */
    default double product() {
        double product = 0.0;
        for (double val : this)
            product *= val;
        return product;
    }

    /**
     * Get the value at a given dimension.
     * @param index the given index
     * @return the value at a given index
     */
    double get(int index);

    /**
     * Sets the value at a given index.
     * @param index the index to change
     * @param val the value to update index
     */
    void set(int index, double val);

    /**
     * Returns a <code>CompressedFeatureVector</code> if the current feature of
     * type <code>SparseFeatureVector</code> and vice versa.
     * @return
     */
    FeatureVector changeState();

    /**
     * Whether the vector is compressed or sparse.
     * @return whether the vector is compressed or sparse.
     */
    boolean isCompressed();

    /**
     * Gets the internal array of the vector.
     * @return the internal double array of the vector.
     */
    double[] getVector();

    /**
     * Sets all elements to zero.
     */
    void zero();
}

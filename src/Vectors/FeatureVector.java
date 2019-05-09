package Vectors;
import java.lang.Iterable;
import java.util.Collection;
import java.util.Iterator;

interface FeatureVector extends Collection<Double>, Iterable<Double> {

    /**
     * Calculates the inner dot product of two <code>FeatureVector</code> objects.
     * @param other another vector
     * @return the dot product of two vectors
     */
    double dot(FeatureVector other);

    /**
     * Calculates the cosine similarity of two vectors.
     * @param other another vector
     * @return the cosine similarity of this and another vector
     */
    default double cosineSimilarity(FeatureVector other) {
        double dotProduct = dot(other);
        double euclideanLengthA = pow(2).sum();
        double euclideanLengthB = other.pow(2).sum();

        return dotProduct / (Math.sqrt(euclideanLengthA) * Math.sqrt(euclideanLengthB));
    }

    FeatureVector addition(FeatureVector other);

    default FeatureVector addition(int scalar) {
        return addition((double) scalar);
    }

    FeatureVector addition(double scalar);

    void additionInPlace(FeatureVector other);

    default void additionInPlace(int scalar) {
        addition((double) scalar);
    }

    void additionInPlace (double scalar);

    FeatureVector subtract(FeatureVector other);

    default FeatureVector subtract(int scalar) {
        return subtract((double) scalar);
    }

    FeatureVector subtract(double scalar);

    void subtractInPlace(FeatureVector other);

    default void subtractInPlace(int scalar) {
        subtractInPlace((double) scalar);
    }

    void subtractInPlace(double scalar);

    FeatureVector multiply(FeatureVector other);

    default FeatureVector multiply(int scalar) {
        return multiply((double) scalar);
    }

    FeatureVector multiply(double scalar);

    void multiplyInPlace(FeatureVector other);

    default void multiplyInPlace(int scalar) {
        multiplyInPlace((double) scalar);
    }

    void multiplyInPlace(double scalar);

    FeatureVector divide(FeatureVector other);

    default FeatureVector divide(int scalar) {
        return divide((double) scalar);
    }

    FeatureVector divide(double scalar);

    void divideInPlace(FeatureVector other);

    default void divideInPlace(int scalar) {
        divideInPlace((double) scalar);
    }

    void divideInPlace(double scalar);

    FeatureVector pow(FeatureVector other);

    default FeatureVector pow(int scalar) {
        return pow((double) scalar);
    }

    FeatureVector pow(double scalar);

    void powInPlace(FeatureVector other);

    default void powInPlace(int scalar) {
        powInPlace((double) scalar);
    }

    void powInPlace(double scalar);

    double sum();

    double product();

    int getLength();

    double get(int index);

    void set(int index, double val);

    FeatureVector changeState();

    boolean isCompressed();

    void update(FeatureVector other);

    Iterator<Double> iterator();

    public double[] getVector();

}

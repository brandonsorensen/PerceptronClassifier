package Vectors;

interface FeatureVector {

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

    FeatureVector add(CompressedFeatureVector other);

    FeatureVector add(SparseFeatureVector other);

    FeatureVector add(int scalar);

    FeatureVector add(double scalar);

    FeatureVector subtract(CompressedFeatureVector other);

    FeatureVector subtract(SparseFeatureVector other);

    FeatureVector subtract(int scalar);

    FeatureVector subtract(double scalar);

    FeatureVector multiply(CompressedFeatureVector other);

    FeatureVector multiply(SparseFeatureVector other);

    FeatureVector multiply(int scalar);

    FeatureVector multiply(double scalar);

    FeatureVector divide(CompressedFeatureVector other);

    FeatureVector divide(SparseFeatureVector other);

    FeatureVector divide(int scalar);

    FeatureVector divide(double scalar);

    FeatureVector pow(int scalar);

    FeatureVector pow(double scalar);

    double sum();

    double product();

    int getLength();

    double get(int index);

    void set();

    FeatureVector changeState();

    boolean isCompressed();

    void update(FeatureVector other);

}

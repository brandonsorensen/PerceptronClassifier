package Vectors;

public interface FeatureVector {

    double dot(FeatureVector other);

    double cosineSimilarity(FeatureVector other);

    FeatureVector add(FeatureVector other);

    FeatureVector add(int scalar);

    FeatureVector add(double scalar);

    FeatureVector subtract(FeatureVector other);

    FeatureVector subtract(int scalar);

    FeatureVector subtract(double scalar);

    FeatureVector multiply(FeatureVector other);

    FeatureVector multiply(int scalar);

    FeatureVector multiply(double scalar);

    FeatureVector divide(FeatureVector other);

    FeatureVector divide(int scalar);

    FeatureVector divide(double scalar);

    FeatureVector pow(int scalar);

    FeatureVector pow(double scalar);

    double get(int index);

    void set();

    FeatureVector changeState();

    boolean isCompressed();

    void update(FeatureVector other);

}

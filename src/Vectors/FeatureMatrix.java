package Vectors;

import java.util.*;

public class FeatureMatrix extends AbstractCollection<FeatureVector> {
    private FeatureVector[] matrix;
    private final int[] shape;

    public FeatureMatrix(Collection<FeatureVector> vectors) throws VectorLengthException {
        matrix = new FeatureVector[vectors.size()];
        vectors.toArray(matrix);
        ensureLength(matrix);
        shape = new int[] {matrix[0].size(), matrix.length};
    }

    public FeatureMatrix(FeatureVector[] vectors) throws VectorLengthException {
        ensureLength(vectors);
        matrix = vectors;
        shape = new int[] {matrix[0].size(), matrix.length};
    }

    // TODO: Add multiple axes
    public void addition(double scalar) {
        for (FeatureVector vector : matrix) {
            vector.additionInPlace(scalar);
        }
    }

    public void addition(FeatureVector other) {
        for (FeatureVector vector : matrix) {
            vector.additionInPlace(other);
        }
    }

    // TODO: Add multiple axes
    public void subtract(double scalar) {
        for (FeatureVector vector : matrix) {
            vector.subtractInPlace(scalar);
        }
    }

    public void subtract(FeatureVector other) {
        for (FeatureVector vector : matrix) {
            vector.subtractInPlace(other);
        }
    }

    // TODO: Add multiple axes
    public void multiply(double scalar) {
        for (FeatureVector vector : matrix) {
            vector.multiplyInPlace(scalar);
        }
    }

    public void multiply(FeatureVector other) {
        for (FeatureVector vector : matrix) {
            vector.multiplyInPlace(other);
        }
    }

    // TODO: Add multiple axes
    public void divide(double scalar) {
        for (FeatureVector vector : matrix) {
            vector.divideInPlace(scalar);
        }
    }

    public void divide(FeatureVector other) {
        for (FeatureVector vector : matrix) {
            vector.divideInPlace(other);
        }
    }

    // TODO: Add multiple axes
    public void pow(double scalar) {
        for (FeatureVector vector : matrix) {
            vector.powInPlace(scalar);
        }
    }

    public void pow(FeatureVector other) {
        for (FeatureVector vector : matrix) {
            vector.powInPlace(other);
        }
    }

    private void ensureLength(FeatureVector[] matrix) throws VectorLengthException {
        int firstLength = matrix[0].size();
        for (FeatureVector vector : matrix) {
            if (vector.size() != firstLength)
                throw new VectorLengthException("Vector lengths are not uniform.");
        }
    }

    // TODO
    @Override
    public Iterator<FeatureVector> iterator() {
        List<FeatureVector> array = Arrays.asList(matrix);
        return array.iterator();
    }

    @Override
    public int size() {
        return shape[1];
    }

    public int[] getShape() {
        return shape;
    }
}

class VectorLengthException extends Exception {
    VectorLengthException(String message) {
        super(message);
    }
}

package Vectors;

import java.util.*;

// TODO: Extend serializable?
public class FeatureMatrix extends AbstractCollection<FeatureVector> {
    private FeatureVector[] matrix;
    private final int[] shape;

    public FeatureMatrix(Collection<FeatureVector> vectors) {
        matrix = new FeatureVector[vectors.size()];
        vectors.toArray(matrix);
        ensureLength(matrix);
        shape = new int[] {matrix[0].size(), matrix.length};
    }

    public FeatureMatrix(FeatureVector[] vectors) {
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

    private void ensureLength(FeatureVector[] matrix) {
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

    public FeatureVector get(int i) {
        if (i < 0 || i > size())
            throw new IndexOutOfBoundsException();
        return matrix[i];
    }

    public int[] getShape() {
        return shape;
    }

    @Override
    public String toString() {
        return Arrays.toString(matrix);
    }

    /**
     * Initializes a <code>FeatureMatrix</code> object with random values of the shape
     * {nFeatures, length}. Uses the <code>SparseFeatureVector</code> implementation.
     * @param length the number of vectors in the matrix
     * @param nFeatures the length of each vector
     * @return a matrix of shape {nFeatures, length}
     */
    public static FeatureMatrix randomInitialize(int nFeatures, int length) {
        if (length < 0)
            throw new IllegalArgumentException("Length must be a positive integer.");

        SparseFeatureVector[] matrix = new SparseFeatureVector[length];
        for (int i = 0; i < length; i++) {
            matrix[i] = SparseFeatureVector.randomInitialize(nFeatures);
        }
        return new FeatureMatrix(matrix);
    }

    /**
     * Initializes a <code>FeatureMatrix</code> object with random values of the shape
     * {nFeatures, length}. Uses the <code>CompressedFeatureVector</code> implementation.
     * @param length the number of vectors in the matrix
     * @param nFeatures the length of each vector
     * @param sparsity the level of sparsity of the array: higher values mean fewer non-
     *                 zero elements in the array. Must be between 0 and 1.
     * @return a matrix of shape {nFeatures, length}
     */
    public static FeatureMatrix randomInitialize(int nFeatures, int length, double sparsity) {
        if (length < 0)
            throw new IllegalArgumentException("Length must be a positive integer.");

        CompressedFeatureVector[] matrix = new CompressedFeatureVector[length];
        for (int i = 0; i < length; i++) {
            matrix[i] = CompressedFeatureVector.randomInitialize(nFeatures, sparsity);
        }
        return new FeatureMatrix(matrix);
    }
}

class VectorLengthException extends RuntimeException {
    VectorLengthException(String message) {
        super(message);
    }
}

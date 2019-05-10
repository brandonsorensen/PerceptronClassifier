package Vectors;

import java.util.List;

public class WeightVector extends SparseFeatureVector {

    private double bias;

    WeightVector(int length) {
        this(length, 0.0);
    }

    WeightVector(int length, double bias) {
        super(length);
        this.bias = bias;
    }

    WeightVector(double[] vector, double bias) {
        super(vector);
        this.bias = bias;
    }

    WeightVector(double[] vector) {
        this(vector, 0.0);
    }

    WeightVector(List<Double> vector) {
        this(vector, 0.0);
    }

    WeightVector(List<Double> vector, double bias) {
        super(vector);
        this.bias = bias;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public static WeightVector randomInitialize(int length, double bias) {
        double[] randArray = new double[length];
        for (int i = 0; i < length; i++)
            randArray[i] = Math.random();
        return new WeightVector(randArray, bias);
    }
}

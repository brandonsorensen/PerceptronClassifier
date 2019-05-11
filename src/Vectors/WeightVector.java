package Vectors;

import java.util.List;

public class WeightVector extends SparseFeatureVector {

    private double bias;

    public WeightVector(int length) {
        this(length, 0.0);
    }

    public WeightVector(int length, double bias) {
        super(length);
        this.bias = bias;
    }

    public WeightVector(double[] vector, double bias) {
        super(vector);
        this.bias = bias;
    }

    public WeightVector(double[] vector) {
        this(vector, 0.0);
    }

    public WeightVector(List<Double> vector) {
        this(vector, 0.0);
    }

    public WeightVector(List<Double> vector, double bias) {
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

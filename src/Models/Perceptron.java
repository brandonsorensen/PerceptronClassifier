package Models;

import Evaluation.Scores;
import Vectors.FeatureMatrix;
import Vectors.FeatureVector;
import Vectors.WeightVector;

import java.util.ArrayList;
import java.util.List;

public class Perceptron implements Model<Byte> {

    private double lr, theta;
    private int nFeatures;
    private WeightVector weights;
    private int nIter;

    private final int DEFAULT_NUM_ITER = 5;

    public Perceptron(double lr, double theta, WeightVector weights) {
        this(lr, theta, weights.size());
        this.weights = weights;
    }

    public Perceptron(double lr, double theta, int nFeatures) {
        this.lr = lr;
        this.theta = theta;
        this.nFeatures = nFeatures;
        this.nIter = DEFAULT_NUM_ITER;
    }

    @Override
    public void fit(FeatureMatrix inputs, List<Byte> targets) {
        if (weights == null) {
            int[] shape = inputs.getShape();
            weights = WeightVector.randomInitialize(nFeatures, theta);
        }

        double update;
        byte target, prediction;
        FeatureVector currentInput;

        for (int i = 0; i < nIter; i++) {
            for (int j = 0; j < inputs.size(); j++) {
                target = targets.get(i);
                currentInput = inputs.get(i);
                prediction = predictSingleInput(currentInput);

                update = lr * (target - prediction);
                currentInput.multiplyInPlace(update);
                weights.additionInPlace(currentInput);
                weights.setBias(weights.getBias() + update);
            }
        }
    }

    public byte predictSingleInput(FeatureVector vector) {
        if (vector.dot(weights) + weights.getBias() >= 0) return 1;
        else return -1;
    }

    @Override
    public List<Byte> predict(FeatureMatrix inputs) {
        List<Byte> predictions = new ArrayList<>(inputs.size());
        for (int i = 0; i < inputs.size(); i++) {
            FeatureVector input = inputs.get(i);
            predictions.add(predictSingleInput(input));
        }
        return predictions;
    }

    // TODO
    @Override
    public Scores validate(List targets) {
        return null;
    }

    public double getLr() {
        return lr;
    }

    public void setLr(double lr) {
        this.lr = lr;
    }

    public double getTheta() {
        return theta;
    }

    public void setTheta(double theta) {
        this.theta = theta;
    }

    public int getnFeatures() {
        return nFeatures;
    }

    public WeightVector getWeights() {
        return weights;
    }

    public void setWeights(WeightVector weights) {
        this.weights = weights;
    }

    public int getnIter() {
        return nIter;
    }

    public void setnIter(int nIter) {
        this.nIter = nIter;
    }
}

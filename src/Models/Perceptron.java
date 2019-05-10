package Models;

import Evaluation.Scores;
import Vectors.FeatureMatrix;

import java.util.List;

public class Perceptron implements Model {

    private double lr, theta;
    private int nFeatures;
    private FeatureMatrix weights;

    public Perceptron(double lr, double theta, int nFeatures, FeatureMatrix weights) {
        this.lr = lr;
        this.theta = theta;
        this.nFeatures = nFeatures;
        this.weights = weights;
    }

    public Perceptron(double lr, double theta, int nFeatures) {
        this(lr, theta, nFeatures, null);
    }

    @Override
    public void fit(FeatureMatrix inputs, List targets) {

    }

    @Override
    public double[] predict(FeatureMatrix inputs) {
        return new double[0];
    }

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

    public void setnFeatures(int nFeatures) {
        this.nFeatures = nFeatures;
    }

    public FeatureMatrix getWeights() {
        return weights;
    }

    public void setWeights(FeatureMatrix weights) {
        this.weights = weights;
    }
}

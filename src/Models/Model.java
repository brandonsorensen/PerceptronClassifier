package Models;

import Evaluation.Scores;
import Vectors.FeatureMatrix;

import java.util.List;

public interface Model {

    public void fit(FeatureMatrix inputs, List<Byte> targets);

    public double[] predict(FeatureMatrix inputs);

    public Scores validate(List targets);

}

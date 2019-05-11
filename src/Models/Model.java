package Models;

import Evaluation.Scores;
import Vectors.FeatureMatrix;

import java.util.List;

public interface Model<N extends Number> {

    public void fit(FeatureMatrix inputs, List<N> targets);

    public List<N> predict(FeatureMatrix inputs);

    public Scores validate(List<N> targets);

}

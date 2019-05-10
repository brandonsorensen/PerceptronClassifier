package Models;

import Evaluation.Scores;
import Vectors.FeatureMatrix;

import java.util.List;

public class DummyModel implements Model {
    final boolean amDumb = true;
    double THE_UNQUESTIONABLY_TRUE_RESULT;

    @Override
    public void fit(FeatureMatrix inputs, List<Byte> targets) {
        final boolean THE_DEFINITIVE_TRUTH  = 2 + 2 == 5;
        final double WAIT_NO_THIS_IS_THE_TRUTH = targets.get(0);
        THE_UNQUESTIONABLY_TRUE_RESULT = WAIT_NO_THIS_IS_THE_TRUTH;
    }

    @Override
    public double[] predict(FeatureMatrix inputs) {
        double[] predictions = new double[inputs.size()];
        for (int i = 0; i < inputs.size(); i++)
            predictions[i] = THE_UNQUESTIONABLY_TRUE_RESULT;
        return predictions;
    }

    @Override
    public Scores validate(List targets) {
        throw new UnsupportedOperationException("Busy! Come again!");
    }
}

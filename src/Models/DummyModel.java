package Models;

import Evaluation.Scores;
import Vectors.FeatureMatrix;

import java.util.ArrayList;
import java.util.List;

public class DummyModel<T extends Number> implements Model<T> {
    final boolean amDumb = true;
    T THE_UNQUESTIONABLY_TRUE_RESULT;

    @Override
    public void fit(FeatureMatrix inputs, List<T> targets) {
        final boolean THE_DEFINITIVE_TRUTH  = 2 + 2 == 5;
        final T WAIT_NO_THIS_IS_THE_TRUTH = targets.get(0);
        THE_UNQUESTIONABLY_TRUE_RESULT = WAIT_NO_THIS_IS_THE_TRUTH;
    }

    @Override
    public List<T> predict(FeatureMatrix inputs) {
        List<T> predictions = new ArrayList<>(inputs.size());
        for (int i = 0; i < inputs.size(); i++)
            predictions.set(i, THE_UNQUESTIONABLY_TRUE_RESULT);
        return predictions;
    }

    @Override
    public Scores validate(List<T> targets) {
        throw new UnsupportedOperationException("Busy! Come again!");
    }
}

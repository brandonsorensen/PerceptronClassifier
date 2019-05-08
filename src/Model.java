import Vectors.FeatureVector;
import java.util.List;

public interface Model {

    public void fit(List<FeatureVector> inputs, List targets);

    public double[] predict(List<FeatureVector> inputs);

}

package FeatureExtraction;
import Vectors.*;

public interface FeatureExtractor {

    FeatureVector vectorize(String[] tokens);

    int featureCount();
}

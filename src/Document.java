import FeatureExtraction.FeatureExtractor;
import Vectors.FeatureVector;

import java.util.Arrays;

public class Document {
    private FeatureExtractor extractor;
    private String[] text;
    private FeatureVector vector;

    public Document(String[] text, FeatureExtractor extractor) {
        this.extractor = extractor;
        this.text = text;
        this.vector = extractor.vectorize(text);
    }

    public FeatureExtractor getExtractor() {
        return extractor;
    }

    public String[] getText() {
        return text;
    }

    public FeatureVector getVector() {
        return vector;
    }

    @Override
    public String toString() {
        return "Document(" + Arrays.toString(text) + ")";
    }
}

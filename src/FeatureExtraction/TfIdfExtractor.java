package FeatureExtraction;

import FeatureExtraction.Counter.Counter;
import FeatureExtraction.Counter.DocumentCounter;
import Vectors.CompressedFeatureVector;
import Vectors.FeatureVector;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * A TF-IDF feature extractor.
 */
public class TfIdfExtractor implements FeatureExtractor {

    private DocumentCounter<String> docFreqs;
    private HashMap<String, Integer> word2Idx;
    private int nDocuments;

    public TfIdfExtractor(Collection<String[]> dataPoints) {
        docFreqs = new DocumentCounter<String>(dataPoints);
        word2Idx = BOWExtractor.initWord2Idx(docFreqs.vocab());
        nDocuments = dataPoints.size();
    }

    @Override
    public FeatureVector vectorize(String[] tokens) {
        Map<Integer, Double> nonZerosIndices = new HashMap<>(tokens.length);
        int[] indices = mapToIndices(tokens);
        double[] tfIdfValues = calculateTfIdfValues(tokens);

        for (int i = 0; i < tokens.length; i++) {
            int index = indices[i];
            double value = tfIdfValues[i];
            nonZerosIndices.put(index, value);
        }

        return new CompressedFeatureVector(featureCount(), nonZerosIndices);
    }

    @Override
    public int featureCount() {
        return word2Idx.size();
    }

    private int[] mapToIndices(String[] tokens) {
        int[] retArray = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            retArray[i] = word2Idx.get(tokens[i]);
        }
        return retArray;
    }

    private double[] calculateTfIdfValues(String[] tokens) {
        double[] values = new double[tokens.length];
        Counter<String> docCount = new Counter<>(tokens);

        double tf;
        double idf;
        String token;

        for (int i = 0; i < tokens.length; i++) {
            token = tokens[i];
            tf = termFrequency(token, docCount, tokens.length);
            idf = inverseDocFreq(token);
            values[i] = tf * idf;
        }
        return values;
    }

    private static double termFrequency(String token, Counter<String> context, int docLength) {
        return Math.log(1 + (double) context.get(token) / docLength);
    }

    private double inverseDocFreq(String token) {
        int docFreq = docFreqs.get(token);
        if (docFreq == 0)
            return 0.0;
        return Math.log((double) nDocuments / docFreqs.get(token));
    }
}

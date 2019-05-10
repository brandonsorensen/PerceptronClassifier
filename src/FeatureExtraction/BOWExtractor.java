package FeatureExtraction;

import Vectors.CompressedFeatureVector;
import Vectors.FeatureVector;

import java.util.*;

/**
 * A bag-of-words feature extractor.
 */
public class BOWExtractor implements FeatureExtractor {

    private HashMap<String, Integer> word2Idx;

    BOWExtractor(Iterable<String[]> dataPoints) {
        Set<String> vocab = getAllWords(dataPoints);
        word2Idx = initWord2Idx(vocab);
    }

    static HashMap<String, Integer> initWord2Idx(Set<String> vocab) {
        HashMap<String, Integer> word2Idx = new HashMap<>(vocab.size());
        int index = 0;
        for (String word : vocab) {
            word2Idx.put(word, index);
            index++;
        }
        return word2Idx;
    }

    static Set<String> getAllWords(Iterable<String[]> collection) {
        HashSet<String> vocab = new HashSet<>();
        for (String[] tokens : collection) {
            for (String token : tokens) {
                vocab.add(token);
            }
        }
        return vocab;
    }

    @Override
    public FeatureVector vectorize(String[] tokens) {
        HashMap<Integer, Double> indexMap = new HashMap<>();
        for (String token : tokens) {
            if (word2Idx.keySet().contains(token))
                indexMap.put(word2Idx.get(token), 1.0);
        }
        return new CompressedFeatureVector(featureCount(), indexMap);
    }

    @Override
    public int featureCount() {
        return word2Idx.size();
    }
}

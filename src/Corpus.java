import FeatureExtraction.FeatureExtractor;
import Vectors.FeatureMatrix;
import Vectors.FeatureVector;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.LinkedList;

public class Corpus extends AbstractCollection<FeatureVector> {

    private FeatureMatrix featureMatrix;
    private LinkedList<Document> documents;
    private FeatureExtractor extractor;

    public Corpus(Iterable<String[]> collection, FeatureExtractor extractor) {
        LinkedList<FeatureVector> matrix = new LinkedList<>();
        this.extractor = extractor;
        documents = new LinkedList<>();
        Document currentDocument;

        for (String[] tokens : collection) {
            currentDocument = new Document(tokens, extractor);
            matrix.add(currentDocument.getVector());
            documents.add(currentDocument);
        }

        featureMatrix = new FeatureMatrix(matrix);
    }

    public FeatureMatrix getFeatureMatrix() {
        return featureMatrix;
    }

    public LinkedList<Document> getDocuments() {
        return documents;
    }

    public FeatureExtractor getExtractor() {
        return extractor;
    }

    @Override
    public Iterator<FeatureVector> iterator() {
        return featureMatrix.iterator();
    }

    public Document get(int i) {
        if (i < 0 || i > documents.size())
            throw new IndexOutOfBoundsException();
        return documents.get(i);
    }

    @Override
    public int size() {
        return documents.size();
    }
}

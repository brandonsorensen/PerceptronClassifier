package Evaluation;

import java.util.*;

/**
 * A class for evaluating a model's performance based on a gold standard
 * and the predictions of the model. Allows for narrowing the scope of
 * the evaluation to specific labels.
 * @param <T> any prediction type, likely strings or numerics
 */
public class Evaluator<T> {

    private List<T> predictions, gold;
    private HashMap<T, ConfusionMatrix> classMatrices;

    public Evaluator(List<T> predictions, List<T> gold, Set<T> labels) {
        this.predictions = predictions;
        this.gold = gold;
        classMatrices = new HashMap<>();
        initClassMatrices(labels);
    }

    /**
     * Initializes the evaluator with no specified labels, As such, assumes
     * all labels that appear in the union of <code>predictions</code> and
     * <code>gold</code> to be relevant labels.
     * @param predictions a model's predictions
     * @param gold a list of targets
     */
    public Evaluator(List<T> predictions, List<T> gold) {
        this.predictions = predictions;
        this.gold = gold;
        classMatrices = new HashMap<>();

        HashSet<T> labelUnion = new HashSet<>();
        labelUnion.addAll(predictions);
        labelUnion.addAll(gold);
        initClassMatrices(labelUnion);
    }

    private void initClassMatrices(Set<T> labels) {
        ConfusionMatrix classMatrix;
        for (T label : labels) {
            classMatrix = ConfusionMatrix.initFromPredictions(predictions, gold, label);
            classMatrices.put(label, classMatrix);
        }
    }

    public List<T> getPredictions() {
        return predictions;
    }

    public List<T> getGold() {
        return gold;
    }

    public Set<T> getLabels() {
        return classMatrices.keySet();
    }

    public HashMap<T, ConfusionMatrix> getClassMatrices() {
        return classMatrices;
    }

    public double getAccuracy() {
        int correctPredictions = 0;
        T prediction;
        T target;
        for (int i = 0; i < predictions.size(); i++) {
            prediction = predictions.get(i);
            target = gold.get(i);
            if (prediction.equals(target))
                correctPredictions++;
        }
        return (double) correctPredictions / predictions.size();
    }
    public Scores getMacroAverages() {
        return calculateMacroAverages(classMatrices.values());
    }

    public Scores getMicroAverages() {
        return calculateMicroAverages(classMatrices.values());
    }

    public static Scores calculateMacroAverages(Iterable<ConfusionMatrix> matrices) {
        double p = 0, r = 0, f = 0;
        int nMatrices = 0;

        for (ConfusionMatrix matrix : matrices) {
            p += matrix.precision();
            r += matrix.recall();
            f += matrix.fscore();
            nMatrices++;
        }

        return new Scores(
                p / nMatrices,
                r / nMatrices,
                f / nMatrices
        );
    }

    public static Scores calculateMicroAverages(Iterable<ConfusionMatrix> matrices) {
        ConfusionMatrix totalMatrix = new ConfusionMatrix();

        for (ConfusionMatrix matrix : matrices) {
            totalMatrix.add(matrix);
        }

        return new Scores(
                totalMatrix.precision(),
                totalMatrix.recall(),
                totalMatrix.fscore()
        );
    }
}

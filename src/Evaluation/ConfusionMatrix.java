package Evaluation;

import java.util.List;

/**
 * Represents a confusion matrix for the analysis of a binary classifier.
 * Provided methods for calculating the precision, recall, and f1-score
 * of the model.
 */
public class ConfusionMatrix {
    private int tp;
    private int fp;
    private int tn;
    private int fn;

    /**
     * Initializes an empty matrix;
     */
    ConfusionMatrix() {
        tp = 0;
        fp = 0;
        tn = 0;
        fn = 0;
    }

    /**
     * Initializes a confusion matrix with the given values
     * @param tp true positive
     * @param fp false positive
     * @param tn true negative
     * @param fn false negative
     */
    ConfusionMatrix(int tp, int fp, int tn, int fn) {
        this.tp = tp;
        this.fp = fp;
        this.tn = tn;
        this.fn = fn;
    }

    /**
     * Calculates the precision of a model. Essentially, what percentage
     * of the times that the model predicted the target class was it
     * correct in its prediction.
     * @return the model's precision
     */
    public double precision() {
        if (tp == 0 || fp == 0)
            return 0.0;
        return (double) tp / (tp + fp);
    }

    /**
     * Calculates the recall of a model. Essentially, how many of the actual
     * appearances of the target class in the validation set did the model
     * predict.
     * @return the model's recall
     */
    public double recall() {
        if (tp == 0 || fn == 0)
            return 0.0;
       return (double) tp / (tp + fn);
    }

    /**
     * Calculates the f1-score of a model, the harmonic mean betwen
     * precision and recall.
     * @return the f1-score
     */
    public double fscore() {
        double p = precision();
        double r = recall();
        if (p == 0 && r == 0)
            return 0.0;

        return 2 * ((p * r) / (p + r));
    }

    /**
     * @return true positives
     */
    public int getTp() {
        return tp;
    }

    /**
     * @param tp true positives
     */
    public void setTp(int tp) {
        this.tp = tp;
    }

    /**
     * Increments true positives by 1.
     */
    public void incrementTp() {
        this.tp++;
    }

    /**
     * @return false positives
     */
    public int getFp() {
        return fp;
    }

    /**
     * @param fp false positive
     */
    public void setFp(int fp) {
        this.fp = fp;
    }

    /**
     * Increments false positive by 1.
     */
    public void incrementFp() {
        this.fp++;
    }

    /**
     * @return true negative
     */
    public int getTn() {
        return tn;
    }

    /**
     * @param tn true negative
     */
    public void setTn(int tn) {
        this.tn = tn;
    }

    /**
     * Increments true negative by 1.
     */
    public void incrementTn() {
        this.tn++;
    }

    /**
     * @return false negative
     */
    public int getFn() {
        return fn;
    }

    /**
     * @param fn false negative
     */
    public void setFn(int fn) {
        this.fn = fn;
    }

    /**
     * Increments false negative by 1.
     */
    public void incrementFn() {
        this.fn++;
    }

    /**
     * Initializes a <code>ConfusionMatrix</code> object from two lists
     * of parallel data: one containing a model's predictions, the other
     * the gold standard for the same inputs. Calculates all relevant
     * statistics based on the provided target class.
     *
     * @param predictions a model's predictions
     * @param gold the "correct"/target values of the same samples
     * @param targetClass the class on which the model is being evaluated
     * @param <T> any prediction type, likely <code>String</code> or numeric
     *           types
     * @return a ConfusionMatrix evaluating the predictions on <code>targetClass</code>
     */
    public static <T> ConfusionMatrix initFromPredictions(List<T> predictions, List<T> gold, T targetClass) {
        if (predictions.size() != gold.size())
            throw new IllegalArgumentException("Containers are not of equal size.");
        int tp = 0, fp = 0, tn = 0, fn = 0;
        for (int i = 0; i < predictions.size(); i++) {
            T prediction = predictions.get(i);
            T target = gold.get(i);
            // the "positive" scenarios
            if (prediction.equals(targetClass)) {
                if (target.equals(targetClass)) tp++;
                else fp++;
            } else { // the "negative" scenarios
                if (target.equals(targetClass)) fn++;
                else tn++;
            }
        }
        return new ConfusionMatrix(tp, fp, tn, fn);
    }
}

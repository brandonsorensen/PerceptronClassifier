package Evaluation;

public class Scores {
    private double precision, recall, fscore;

    public Scores(double precision, double recall, double fscore) {
        this.precision = precision;
        this.recall = recall;
        this.fscore = fscore;
    }

    public Scores() {
        this(0.0, 0.0, 0.0);
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getRecall() {
        return recall;
    }

    public void setRecall(double recall) {
        this.recall = recall;
    }

    public double getFscore() {
        return fscore;
    }

    public void setFscore(double fscore) {
        this.fscore = fscore;
    }

    @Override
    public String toString() {
        return String.format("Scores(precision: %.3f, recall: %.3f, f1-score: %.3f)",
                precision, recall, fscore);
    }
}

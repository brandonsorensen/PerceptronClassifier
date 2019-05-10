package DataLoaders;

import java.util.Arrays;

public class DataPoint {

    private String target;
    private String[] tokens;

    DataPoint(String target, String[] tokens) {
        this.target = target;
        this.tokens = tokens;
    }

    public String getTarget() {
        return target;
    }

    public String[] getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        String output = "(" + target + ", ";
        output += Arrays.toString(tokens);
        output += ")";

        return output;
    }
}

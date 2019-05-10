package DataLoaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class IsearDataLoader implements Iterator<DataPoint> {

    private Iterator<DataPoint> it;
    private HashSet<String> validLabels;
    public static final String[] EMOTIONS = {
            "joy", "fear", "shame", "disgust",
            "guilt", "anger", "sadness"
    };

    public IsearDataLoader(String path) {
        validLabels = new HashSet<>();
        validLabels.addAll(Arrays.asList(EMOTIONS));
        List<DataPoint> data = readCSV(path);
        this.it = data.iterator();
    }

    private List<DataPoint> readCSV(String path) {
        LinkedList<DataPoint> allPoints = new LinkedList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            String[] sample;
            String label;
            String[] text;
            while ((line = br.readLine()) != null) {

                // use comma as separator
                sample = line.split(",");
                label = sample[0];
                if (!validLabels.contains(label))
                    continue;
                sample = sample[1].split(" ");
                DataPoint point = new DataPoint(label, sample);
                allPoints.add(point);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return allPoints;
    }

    private void eliminateQuotes(String[] sample) {
        // starts at index 1 to avoid the label
        for (int i = 1; i < sample.length; i++) {
            String token = sample[i];
            if (token.charAt(0) == '"')
                sample[i] = token.substring(1, token.length() - 1);
        }
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public DataPoint next() {
        return it.next();
    }
}

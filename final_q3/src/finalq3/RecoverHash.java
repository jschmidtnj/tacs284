package finalq3;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecoverHash {
    private final List<String> words;
    private final int numRepeat;
    private final int numHashKeys;
    private final char firstHash;
    private HashMap<Character, Integer> hashResult;

    /**
     * constructor for recovering hashes
     *
     * @param words     given words
     * @param minHash   minimum hash value
     * @param maxHash   maximum hash value
     * @param numRepeat number of times hash value can be repeated (1 = all unique, 2 = 2 elements can have same)
     */
    public RecoverHash(List<String> words, int minHash, int maxHash, int numRepeat) {
        this.words = words;
        this.numRepeat = numRepeat;
        this.hashResult = null;
        this.firstHash = 'a';
        this.numHashKeys = numRepeat * ((maxHash - minHash) + 1);
        if (this.numHashKeys <= 0) {
            throw new IllegalArgumentException("invalid min / max hash provided");
        }
        computeHash();
    }

    public static void main(String[] args) {
        if (args.length < 1 || args[0].length() == 0) {
            throw new IllegalArgumentException("no file argument provided");
        }
        FileReader fr;
        try {
            fr = new FileReader(args[0]);
        } catch (FileNotFoundException notFound) {
            throw new IllegalArgumentException("file \"" + args[0] + "\" not found");
        }
        int current_char_int;
        char current_char;
        StringBuilder sb = new StringBuilder();
        List<String> words = new ArrayList<>();
        try {
            while ((current_char_int = fr.read()) != -1) {
                current_char = (char) current_char_int;
                if (current_char == '\n') {
                    words.add(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(current_char);
                }
            }
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
        if (sb.length() > 0) {
            words.add(sb.toString());
        }
        RecoverHash recover = new RecoverHash(words, 0, 12, 2);
        System.out.println(recover.toString());
    }

    private double getAverage(List<Integer> data) {
        Integer sum = 0;
        if (!data.isEmpty()) {
            for (Integer elem : data) {
                sum += elem;
            }
            return sum.doubleValue() / data.size();
        }
        return sum;
    }

    private void computeHash() {
        if (this.words.size() == 0) {
            throw new IllegalArgumentException("no words provided");
        }
        HashMap<Character, ArrayList<Integer>> indexes = new HashMap<>(numHashKeys);
        for (int i = 0; i < numHashKeys; i++) {
            indexes.put((char) (firstHash + i), new ArrayList<>(this.words.size()));
        }
        for (String currentWord : this.words) {
            for (int j = 0; j < currentWord.length(); j++) {
                char currentChar = currentWord.charAt(j);
                indexes.get(currentChar).add(j);
            }
        }
        HashMap<Character, Double> countLessThan = new HashMap<>(numHashKeys);
        for (int i = 0; i < numHashKeys; i++) {
            char currentChar = (char) (firstHash + i);
            countLessThan.put(currentChar, getAverage(indexes.get(currentChar)));
        }
        List<Map.Entry<Character, Double>> countSorted = new ArrayList<>(countLessThan.entrySet());
        countSorted.sort(Map.Entry.comparingByValue());
        System.out.println(countSorted);
        hashResult = new HashMap<>(numHashKeys);
        for (int i = 0; i < countSorted.size(); i++) {
            hashResult.put(countSorted.get(i).getKey(), i / numRepeat);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numHashKeys; i++) {
            char currentChar = (char) (firstHash + i);
            sb.append(currentChar);
            sb.append('\t');
            sb.append(hashResult.get(currentChar));
            if (i != numHashKeys - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}

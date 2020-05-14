import finalq3.RecoverHash;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class RecoverHash2 {
    private final List<String> words;
    private final int minHash;
    private final int numRepeat;
    private final int numHashKeys;
    private ArrayList<Node<Character>> hashList;
    public final char startChar;

    /**
     * constructor for recovering hashes
     *
     * @param words     given words
     * @param minHash   minimum hash value
     * @param maxHash   maximum hash value
     * @param numRepeat number of times hash value can be repeated (1 = all unique, 2 = 2 elements can have same)
     */
    public RecoverHash2(List<String> words, int minHash, int maxHash, int numRepeat) {
        this.words = words;
        this.minHash = minHash;
        this.numRepeat = numRepeat;
        this.numHashKeys = numRepeat * ((maxHash - minHash) + 1);
        if (this.numHashKeys <= 0) {
            throw new IllegalArgumentException("invalid min / max hash provided");
        }
        this.hashList = new ArrayList<>(this.numHashKeys);
        this.startChar = (char)('a' + minHash);
        for (int i = 0; i < this.numHashKeys; i++) {
            this.hashList.add(new Node<>((char)(i + startChar)));
        }
        computeHash();
    }
    private class Node<E> {
        public Node previous, next;
        public E data;
        public Node(E data) {
            previous = null;
            next = null;
            this.data = data;
        }
        public Node(E data, Node previous, Node next) {
            this.previous = previous;
            this.next = next;
            this.data = data;
        }
        public String toString() {
            return data.toString();
        }
    }

    private boolean findInRemaining(Node startNode, Node targetNode) {
        Node currentNode = startNode;
        while (currentNode != null) {
            if (currentNode == targetNode) {
                return true;
            }
            currentNode = currentNode.next;
        }
        return false;
    }

    private void computeHash() {
        if (this.words.size() == 0) {
            throw new IllegalArgumentException("no words provided");
        }
        HashSet<Character> usedCharacters = new HashSet<>(numHashKeys);
        for (int i = 0; i < this.words.size(); i++) {
            String currentWord = this.words.get(i);
            int lastIndex = 0, currentIndex;
            char currentChar;
            Node<Character> currentNode = null;
            for (int j = 0; j < currentWord.length(); j++) {
                currentChar = currentWord.charAt(j);
                currentIndex = currentChar - this.startChar;
                currentNode = this.hashList.get(currentIndex);
                int lowerBound = -1;
                if (j != 0) {
                    Node<Character> lastNode = this.hashList.get(lastIndex);
                    if (lastNode.next == null) {
                        lastNode.next = currentNode;
                    } else {
                        if (currentNode.previous == null) {
                            currentNode.previous = lastNode.next;
                            lastNode.next = currentNode;
                        } else {
                            if (!findInRemaining(lastNode, currentNode)) {

                            } else {
                                // node already in correct spot
                            }
                        }
                    }
                }
                int upperBound = hashList.size() - 1;
                if (j != currentWord.length() - 1) {
                    char nextChar = currentWord.charAt(j + 1);
                    if (usedCharacters.contains(nextChar)) {
                        upperBound = hashList.indexOf(nextChar);
                    }
                }
                if (!usedCharacters.contains(currentChar)) {
                    currentIndex = lowerBound + 1;
                    hashList.get(currentIndex).addNext(currentChar);
                    hashList.size++;
                    usedCharacters.add(currentChar);
                } else {
                    currentIndex = hashList.indexOf(currentChar);
                    if (currentIndex < lowerBound) {
                        // this case never occurs
                        hashList.swap(lowerBound, currentIndex);
                        currentIndex = lowerBound;
                    } else if (currentIndex > upperBound) {
                        hashList.swap(currentIndex, upperBound);
                        currentIndex = upperBound;
                    }
                }
                lastIndex = currentIndex;
                if (hashList.size > numHashKeys) {
                    break;
                }
            }
            if (hashList.size > numHashKeys) {
                break;
            }
            System.out.println(hashList.toString());
        }
        if (hashList.size > numHashKeys) {
            throw new IllegalArgumentException("invalid hashed words provided");
        } else if (hashList.size < numHashKeys) {
            // throw new IllegalArgumentException("not enough characters provided");
        }
        hashListData = hashList;
    }

    public String toString() {
        int currentHashVal = minHash;
        HashMap<Character, Integer> sortedHashVals = new HashMap<>(numHashKeys);
        for (int i = 0; i < numHashKeys; i++) {
            sortedHashVals.put(hashListData.get(i).data, currentHashVal);
            if ((i + 1) % numRepeat == 0) {
                currentHashVal++;
            }
        }
        List<Character> keys = new ArrayList<>(sortedHashVals.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numHashKeys; i++) {
            char currentChar = keys.get(i);
            sb.append(currentChar);
            sb.append('\t');
            sb.append(sortedHashVals.get(currentChar));
            if (i != numHashKeys - 1) {
                sb.append('\n');
            }
        }
        return sb.toString();
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
}

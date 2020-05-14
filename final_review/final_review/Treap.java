package final_review;

import java.util.Random;

/**
 * Treap Class
 *
 * @author Joshua Schmidt I pledge my honor that I have abided by the Stevens
 *         Honor System
 * @param <E> datatype for Treap
 */

public class Treap<E extends Comparable<E>> {

    /**
     * Treap Node class
     *
     * @param <E>
     */
    private static class Node<E extends Comparable<E>> {

        // Data fields

        public Node<E> right, left;
        public int priority;
        public E data;

        /**
         * constructor for Treap Node
         *
         * @param data is data in Node
         * @param      priority, priority of the node
         */
        public Node(E data, int priority) {
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }

        /**
         * overrides toString for Node, printing the data and priority
         */
        @Override
        public String toString() {
            return "(key=" + this.data.toString() + ", priority=" + Integer.toString(this.priority) + ')';
        }

        /**
         * rotates node to right
         *
         * @return the new root for rotated node
         */
        private Node<E> rotateRight() {
            Node<E> leftnode = this.left;
            // flip nodes
            this.left = leftnode.right;
            leftnode.right = this;
            // return left Node
            return leftnode;
        }

        /**
         * rotates node to left
         *
         * @return the new root for rotated node
         */
        private Node<E> rotateLeft() {
            Node<E> rightnode = this.right;
            this.right = rightnode.left;
            rightnode.left = this;
            return rightnode;
        }
    }

    // Data fields

    private Random priorityGenerator;
    private Node<E> root;

    /**
     * constructor for Treap (no seed)
     */
    public Treap() {
        this.root = null;
        this.priorityGenerator = new Random();
    }

    /**
     * constructor for Treap (with seed seed)
     *
     * @param seed (long)
     */
    public Treap(long seed) {
        root = null;
        priorityGenerator = new Random(seed);
    }

    /**
     * add a new Node with random priority
     *
     * @param key - data in Node
     * @return true if added else false
     */
    public boolean add(E key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        int numInts = 100;
        boolean result = !(find(key));
        if (result)
            root = add(key, priorityGenerator.nextInt(numInts), root);
        return result;
    }

    /**
     * add new Node with given priority
     *
     * @param key, the data in the node
     * @param priority, the priority of the node
     * @return true if it can be added and false otherwise
     */
    public boolean add(E key, int priority) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        boolean result = !(find(key));
        if (result)
            root = add(key, priority, root);
        return result;
    }

    /**
     * add new Node with given priority (helper function)
     *
     * @param key, the data in the node
     * @param priority, the priority of the node
     * @return Node<E> of new root
     */
    private Node<E> add(E data, int priority, Node<E> current) {
        if (current == null) {
            Node<E> newNode = new Node<E>(data, priority);
            return newNode;
        }
        // adding to bst using comp
        int comp = data.compareTo(current.data);
        if (comp < 0) {
            current.left = add(data, priority, current.left);
            // check if priority is less and if it is rotate
            if (current.priority < current.left.priority) {
                return current.rotateRight();
            }
        } else if (comp > 0) {
            current.right = add(data, priority, current.right);
            // rotate left if priority is less
            if (current.priority < current.right.priority) {
                return current.rotateLeft();
            }
        }
        return current;
    }

    /**
     * finds Node with given key
     *
     * @param key - data of Node that needs to be found
     * @return true if found, else false
     */
    public boolean find(E key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        return find(root, key);
    }

    /**
     * finds if Node is in a tree
     *
     * @param root - current Node root of Treap
     * @param key     - key for Node to be found
     * @return true if found, else false
     */
    private boolean find(Node<E> root, E key) {
        // implementation is obvious
        while (root != null) {
            int comp = key.compareTo(root.data);
            if (comp > 0) {
                root = root.right;
            } else if (comp < 0) {
                root = root.left;
            } else {
                // the key is found
                return true;
            }
        }
        return false;
    }

    /**
     * helper function for delete that locates the soon to be deleted node
     *
     * @param key, the data of the node to be deleted
     * @param current, root of the current Treap
     * @return the new root after deletion of the node
     */
    private Node<E> delete(E key, Node<E> current) {
        if (current != null) {
            int comp = key.compareTo(current.data);
            // bst so first search for key
            if (comp > 0) {
                current.right = delete(key, current.right);
            } else if (comp < 0) {
                current.left = delete(key, current.left);
            } else {
                // when you get the key, flip left if left is null
                if (current.left == null) {
                    return current.right;
                } else if (current.right == null) {
                    // else flip right if right is null
                    return current.left;
                } else {
                    // else both are not null
                    // get leftmost node of right subtree
                    Node<E> leftmostNode = current.right;
                    while (leftmostNode.left != null) {
                        leftmostNode = leftmostNode.left;
                    }
                    // then recurse down the right-hand side, saving to right
                    current.data = leftmostNode.data;
                    current.right = delete(current.data, current.right);
                }
            }
        }
        return current;
    }

    /**
     * deletes a node from the Treap
     *
     * @param key, data of the node to be deleted
     * @return true if it can be deleted and false if not
     */
    public boolean delete(E key) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
        boolean result = find(key);
        if (result)
            root = delete(key, root);
        return result;
    }

    /**
     * creates a String for the Treap
     *
     * @param current - the current root Node
     * @param level   - current level of the Treap
     * @return String Treap
     */
    private String toString(Node<E> current, int level) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < level; i++) {
            result.append("  ");
        }
        if (current == null) {
            result.append("null");
        } else {
            result.append(current.toString());
            result.append("\n");
            result.append(toString(current.left, level + 1));
            result.append("\n");
            result.append(toString(current.right, level + 1));
        }
        return result.toString();

    }

    /**
     * creates a Treap String
     *
     * @return String-ed Treap
     */
    @Override
    public String toString() {
        return toString(root, 0) + "\n";
    }

    public static void main(String[] args) {

    }
}

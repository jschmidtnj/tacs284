package final_review;

import java.util.ArrayList;
import java.lang.Math;

public class BinaryTree<E> {

    public static class Node<F> {
        protected F data;
        protected Node<F> left;
        protected Node<F> right;

        public Node(F data) {
            this.data = data;
            left = null;
            right = null;
        }

        public Node(F data, Node<F> left, Node<F> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public F getData() {
            return data;
        }

        public void setData(F data) {
            this.data = data;
        }

        public Node<F> getLeft() {
            return left;
        }

        public void setLeft(Node<F> left) {
            this.left = left;
        }

        public Node<F> getRight() {
            return right;
        }

        public void setRight(Node<F> right) {
            this.right = right;
        }

        public Boolean isLeaf() {
            return this.left == null && this.right == null;
        }

    }

    protected Node<E> root;
    protected int size;

    public BinaryTree() {
        root = null;
        size = 0;
    }

    public BinaryTree(E data) {
        size = 1;
        root = new Node<E>(data);
    }

    public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
        size = leftTree.size + rightTree.size + 1;
        root = new Node<E>(data, leftTree.root, rightTree.root);
    }

    public Node<E> getRoot() {
        return root;
    }

    public void setRoot(Node<E> root) {
        this.root = root;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isLeaf() {
        return root != null && root.isLeaf();
    }

    public int no_of_leaves() {
        return no_of_leaves(root);
    }

    public int no_of_leaves(Node<E> current) {
        if (current == null) {
            return 0;
        }
        if (current.isLeaf()) {
            return 1;
        }
        return no_of_leaves(current.left) + no_of_leaves(current.right);
    }

    private ArrayList<E> preorder(Node<E> current) {
        ArrayList<E> result = new ArrayList<E>();
        if (current == null) {
            return new ArrayList<E>();
        }
        result.add(current.data);
        result.addAll(preorder(current.left));
        result.addAll(preorder(current.right));
        return result;
    }

    public ArrayList<E> preorder() {
        return preorder(root);
    }

    private void mirror_image(Node<E> current) {
        if (current == null) {
            return;
        }
        Node<E> temp = current.left;
        current.left = current.right;
        current.right = temp;
        mirror_image(current.left);
        mirror_image(current.right);
    }

    public void mirror_image() {
        mirror_image(root);
    }

    private int size(Node<E> current) {
        if (current == null) {
            return 0;
        }
        return 1 + size(current.left) + size(current.right);
    }


    public int size() {
        return size(root);
    }

    public boolean isFull(Node<E> current) {
        if (current == null) {
            return true;
        }
        if (current.left == null && current.right == null) {
            return true;
        }
        if (current.left != null && current.right == null) {
            return false;
        }
        if (current.right != null && current.left == null) {
            return false;
        }
        return isFull(current.left) && isFull(current.right);
    }

    public boolean isFull() {
        return isFull(root);
    }

    private int height(Node<E> current) {
        if (current == null) {
            return 0; //this can be -1 (depends on definition)
        }
        return 1 + Math.max(height(current.left), height(current.right));
    }


    public int height() {
        return height(root);
    }

    private boolean isComplete(Node<E> current, int index, int size) {
        if (index >= size) {
            return false;
        }
        if (current == null) {
            return true;
        }
        return isComplete(current.left, index * 2 + 1, size) && isComplete(current.right, index * 2 + 2, size);
    }


    public boolean isComplete() {
        return isComplete(root, 0, this.size());
    }

    public String toString(Node<E> current, int i) {
        // print out tree in preorder
        StringBuilder s = new StringBuilder();
        for (int j = 0; j < i; j++) {
            s.append("---");
        }
        if (current == null) {
            s.append("null");
            return s.toString();
        }
        s.append(current.data.toString());
        s.append("\n");
        s.append(toString(current.left, i + 1));
        s.append("\n");
        s.append(toString(current.right, i + 1));
        s.append("\n");
        return s.toString();
    }

    public String toString() {
        return toString(root, 0);
    }

    public static void main(String[] args) {
        BinaryTree<Integer> leaf1 = new BinaryTree<Integer>(5);
        BinaryTree<Integer> leaf2 = new BinaryTree<Integer>(10);
        BinaryTree<Integer> bt = new BinaryTree<Integer>(12, leaf1, leaf2);

        System.out.println(bt);

        System.out.println(bt.isLeaf());
        System.out.println(leaf1.isLeaf());
        System.out.println(bt.preorder());
        System.out.println(bt.no_of_leaves());
        bt.mirror_image();
        System.out.println(bt.preorder());
        System.out.println(bt.isFull());
        System.out.println(bt.height());
        System.out.println(bt.isComplete());

        // preorder vs. post order: https://images.app.goo.gl/TYwaaJznPxtkG3vJ7
    }
}

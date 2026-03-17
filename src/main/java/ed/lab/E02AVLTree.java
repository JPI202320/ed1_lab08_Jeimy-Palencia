package ed.lab;

import java.util.Comparator;

public class E02AVLTree<T> {

    private class Node {
        T value;
        Node left, right;
        int height;

        Node(T val) {
            value = val;
            height = 1;
        }
    }

    private Node root;
    private final Comparator<T> comparator;
    private int size;


    public E02AVLTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    private int getHeight(Node n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(Node n) {
        n.height = 1 + Math.max(getHeight(n.left), getHeight(n.right));
    }

    private int getBalance(Node n) {
        return n == null ? 0 : getHeight(n.left) - getHeight(n.right);
    }

    private Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        // Izquierda - Izquierda
        if (balance > 1 && getBalance(node.left) >= 0)
            return rotateRight(node);

        // Izquierda - Derecha
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // Derecha - Derecha
        if (balance < -1 && getBalance(node.right) <= 0)
            return rotateLeft(node);

        // Derecha - Izquierda
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    public void insert(T value) {
        root = insert(root, value);
    }

    private Node insert(Node node, T value) {
        if (node == null) {
            size++;
            return new Node(value);
        }

        if (comparator.compare(value, node.value) < 0)
            node.left = insert(node.left, value);
        else if (comparator.compare(value, node.value) > 0)
            node.right = insert(node.right, value);
        else
            return node; // no duplicados

        updateHeight(node);
        return balance(node);
    }

    public void delete(T value) {
        root = delete(root, value);
    }

    private Node delete(Node node, T value) {
        if (node == null) return null;

        if (comparator.compare(value, node.value) < 0)
            node.left = delete(node.left, value);
        else if (comparator.compare(value, node.value) > 0)
            node.right = delete(node.right, value);
        else {
            if (node.left == null || node.right == null) {
                size--;
                node = (node.left != null) ? node.left : node.right;
            } else {
                Node min = getMin(node.right);
                node.value = min.value;
                node.right = delete(node.right, min.value);
            }
        }

        if (node == null) return null;

        updateHeight(node);
        return balance(node);
    }

    private Node getMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    public T search(T value) {
        Node node = search(root, value);
        return node == null ? null : node.value;
    }

    private Node search(Node node, T value) {
        if (node == null) return null;

        if (comparator.compare(value, node.value) == 0)
            return node;
        else if (comparator.compare(value, node.value) < 0)
            return search(node.left, value);
        else
            return search(node.right, value);
    }

    public int height() {return getHeight(root);
    }

    public int size() {
        return size;
    }
}

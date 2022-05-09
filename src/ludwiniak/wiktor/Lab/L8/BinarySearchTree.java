package ludwiniak.wiktor.Lab.L8;

import java.util.ArrayList;

import static java.util.Objects.isNull;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;

    public void add(T value) throws DuplicateElementException {
        if (isNull(root)) {
            root = new TreeNode<>(value);
        } else {
            add(root, value);
        }
    }

    private void add(TreeNode<T> node, T value) throws DuplicateElementException {
        if (value.compareTo(node.value) == 0) {
            throw new DuplicateElementException();
        }

        if (value.compareTo(node.value) < 0) {
            if (isNull(node.left)) {
                node.left = new TreeNode<>(value);
                return;
            }
            add(node.left, value);
            return;
        }

        if (isNull(node.right)) {
            node.right = new TreeNode<>(value);
            return;
        }
        add(node.right, value);
    }

    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, TreeNode<T> node) {
        if (isNull(node)) {
            return false;
        }

        int result = value.compareTo(node.value);

        if (result > 0) {
            return contains(value, node.right);
        }

        if (result < 0) {
            return contains(value, node.left);
        }

        return true;
    }

    public void delete(T value) {
        delete(value, root);
    }

    private TreeNode<T> delete(T value, TreeNode<T> node) {
        if (isNull(node)) {
            return null;
        }

        int result = value.compareTo(node.value);

        if (result < 0) {
            node.left = delete(value, node.left);
        } else if (result > 0) {
            node.right = delete(value, node.right);
        } else {
            if (isNull(node.left)) {
                return node.right;
            }

            if (isNull(node.right)) {
                return node.left;
            }

            node.value = min(node.right);
            node.right = delete(node.value, node.right);
        }

        return node;
    }

    private T min(TreeNode<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.value;
    }

    public String toStringPreOrder() {
        ArrayList<String> output = new ArrayList<>();
        preOrder(root, output);
        return String.join(", ", output);
    }

    private void preOrder(TreeNode<T> node, ArrayList<String> output) {
        if (isNull(node)) {
            return;
        }

        output.add(node.value.toString());
        preOrder(node.left, output);
        preOrder(node.right, output);

    }

    public String toStringInOrder() {
        ArrayList<String> output = new ArrayList<>();
        toStringInOrder(root, output);
        return String.join(", ", output);
    }

    public ArrayList<T> inOrder() {
        ArrayList<T> output = new ArrayList<>();
        inOrder(root, output);
        return output;
    }

    private void inOrder(TreeNode<T> node, ArrayList<T> output) {
        if (isNull(node)) {
            return;
        }

        inOrder(node.left, output);
        output.add(node.value);
        inOrder(node.right, output);

    }

    private void toStringInOrder(TreeNode<T> node, ArrayList<String> output) {
        if (isNull(node)) {
            return;
        }

        toStringInOrder(node.left, output);
        output.add(node.value.toString());
        toStringInOrder(node.right, output);

    }

    public String toStringPostOrder() {
        ArrayList<String> output = new ArrayList<>();
        postOrder(root, output);
        return String.join(", ", output);
    }

    private void postOrder(TreeNode<T> node, ArrayList<String> output) {
        if (isNull(node)) {
            return;
        }

        postOrder(node.left, output);
        postOrder(node.right, output);
        output.add(node.value.toString());

    }

    private static class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T value) {
            this.value = value;
        }
    }
}

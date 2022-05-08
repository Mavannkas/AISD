package ludwiniak.wiktor.Lab.L8;

import java.util.ArrayList;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;

    public void add(T value) throws DuplicateElementException {
        if (Objects.isNull(root)) {
            root = new TreeNode<>(value);
        } else {
            add(root, value);
        }
    }

    private void add(TreeNode<T> node, T value) throws DuplicateElementException {
        if (value.equals(node.value)) {
            throw new DuplicateElementException();
        }

        if (value.compareTo(node.value) < 0) {
            if (Objects.isNull(node.left)) {
                node.left = new TreeNode<>(value);
                return;
            }
            add(node.left, value);
            return;
        }

        if (Objects.isNull(node.right)) {
            node.right = new TreeNode<>(value);
            return;
        }
        add(node.right, value);
    }

    public boolean contains(T value) {
        return contains(value, root);
    }

    private boolean contains(T value, TreeNode<T> node) {
        if (Objects.isNull(node)) {
            return false;
        }

        int result = value.compareTo(node.value);

        if (result < 0) {
            return contains(value, node.left);
        }

        if (result > 0) {
            return contains(value, node.right);
        }

        return true;
    }

    public void delete(T value) {
        delete(value, root);
    }

    private TreeNode<T> delete(T value, TreeNode<T> node) {
        if (Objects.isNull(node)) {
            return null;
        }

        int result = value.compareTo(node.value);

        if (result < 0) {
            node.left = delete(value, node.left);
        } else if (result > 0) {
            node.right = delete(value, node.right);
        } else {
            if (Objects.isNull(node.right)) {
                return node.left;
            }

            if (Objects.isNull(node.left)) {
                return node.right;
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
        if (Objects.isNull(node)) {
            return;
        }

        output.add(node.value.toString());
        preOrder(node.left, output);
        preOrder(node.right, output);

    }

    public String toStringInOrder() {
        ArrayList<String> output = new ArrayList<>();
        inOrder(root, output);
        return String.join(", ", output);
    }

    private void inOrder(TreeNode<T> node, ArrayList<String> output) {
        if (Objects.isNull(node)) {
            return;
        }

        inOrder(node.left, output);
        output.add(node.value.toString());
        inOrder(node.right, output);

    }

    public String toStringPostOrder() {
        ArrayList<String> output = new ArrayList<>();
        postOrder(root, output);
        return String.join(", ", output);
    }

    private void postOrder(TreeNode<T> node, ArrayList<String> output) {
        if (Objects.isNull(node)) {
            return;
        }

        postOrder(node.left, output);
        postOrder(node.right, output);
        output.add(node.value.toString());

    }

    private class TreeNode<T> {
        T value;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T value) {
            this.value = value;
        }
    }
}

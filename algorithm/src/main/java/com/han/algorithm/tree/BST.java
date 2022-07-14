package com.han.algorithm.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 二分搜索树
 *  若它的左子树不为空，左子树上所有节点的值都小于它的根节点
 *  若它的右子树不为空，右子树上所有的节点的值都大于它的根节点
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;
    private int count;

    private class Node {
        private Key key;
        private Value value;
        private Node left;
        private Node right;

        public Node(Node node) {
            this.key = node.key;
            this.value = node.value;
            left = right = null;
        }

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }

    public BST() {
        root = null;
        count = 0;
    }

    public void insert(Key key, Value value) {
        root = insert(root, key, value);
    }

    // 在以node为根节点的树中插入
    private Node insert(Node node, Key key, Value value) {
        if (root == null) {
            count ++;
            return new Node(key, value);
        } else if (node.key.compareTo(key) == 0){
            // key 相同替换
            node.value = value;
            return node;
        } else if (node.key.compareTo(key) < 0) {
            // key > 当前node.key 插入到右子树
            node.right = insert(node.right, key, value);
        } else  {
            // // key < 当前node.key 插入到左子树
            node.left = insert(node.left, key, value);
        }
        return node;
    }

    public Node search(Key key) {
        return search(root, key);
    }

    //在以node为根节点的树中搜索
    private Node search(Node node, Key key) {
        if (node == null) {
            return null;
        }
        if (node.key.compareTo(key) == 0) {
            return node;
        } else if (node.key.compareTo(key) < 0) {
            return search(node.right, key);
        } else {
            return search(node.left, key);
        }
    }
    public List<Node> preOrder() {
        List<Node> list = new ArrayList<>();
        preOrder(list, root);
        return list;
    }

    private void preOrder(List<Node> list, Node node) {
        if (node != null) {
            list.add(new Node(node));
            preOrder(list, node.left);
            preOrder(list, node.right);
        }
    }

    public List<Node> inOrder() {
        List<Node> list = new ArrayList<>();
        preOrder(list, root);
        return list;
    }

    private void inOrder(List<Node> list, Node node) {
        if (node != null) {
            inOrder(list, node.left);
            list.add(new Node(node));
            inOrder(list, node.right);
        }
    }

    public List<Node> postOrder() {
        List<Node> list = new ArrayList<>();
        preOrder(list, root);
        return list;
    }

    private void postOrder(List<Node> list, Node node) {
        if (node != null) {
            postOrder(list, node.left);
            postOrder(list, node.right);
            list.add(new Node(node));
        }
    }

    public List<Node> levelOrder() {
        if (count == 0) {
            return null;
        }
        List<Node> list = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            list.add(new Node(node));
        }
        return list;
    }

    public Node min() {
        if (count == 0) {
            return root;
        }
        return min(root);
    }

    private Node min(Node node) {
        if (node.left != null) {
            return min(node.left);
        } else {
            return node;
        }
    }

    public Node max() {
        if (count == 0) {
            return root;
        }
        return max(root);
    }

    private Node max(Node node) {
        if (node.right != null) {
            return min(node.right);
        } else {
            return node;
        }
    }

    public void removeMin() {
        if (count != 0) {
            root = removeMin(root);
        }
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            count--;
            Node right = node.right;
            node.right = null;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public void removeMax() {
        if (count != 0) {
            root = removeMax(root);
        }
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            count--;
            Node left = node.left;
            node.left = null;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public Node remove(Key key) {
        if (count == 0) {
            return root;
        }
        return remove(root, key);
    }

    private Node remove(Node node, Key key) {
        // TODO
        return node;
    }
}

package com.algorithms.tree;

import java.util.HashMap;
import java.util.TreeMap;

/**
 * Created by linxiao on 2016/3/9.
 * 二叉搜索树
 */
public class BinarySearchTree {
    //根节点
    private Node root;
    //默认构造函数
    public BinarySearchTree() { this.root = null;}

    /**
     * 插入一个值
     * @param data
     */
    public void insert(int data) {
        Node node = new Node();
        node.setData(data);
        //root为空，表示当前是一颗空树，没有任何节点。
        // node设置为根节点
        if(this.root == null) {
            this.root = node;
            return;
        }

        HashMap m;
        TreeMap o;
        Node current = root;
        Node parent;
        while (true) {
            parent = current;
            //比当前节点小，往左子节点寻找
            if (data < current.getData()) {
                current = current.getLeftChild();
                if(current == null) {
                    parent.leftChild = node;
                    return;
                }
            }
            //大于或等于当前节点，往右子节点寻找
            else {
                current = current.getRightChild();
                if(current == null) {
                    parent.rightChild = node;
                    return;
                }
            }
        }
    }

    /**
     * 查询一个值
     * @param data
     * @return
     */
    public Node find(int data) {
        Node current = root;
        while (current.data != data) {
            if(data < current.data) {
                current = current.getLeftChild();
            }else {
                current = current.getRightChild();
            }
            //没有找到，返回空
            if(current == null)
                return null;
        }
        return current;
    }

    /**
     * 先序遍历
     * @param node
     */
    public void preOrder(Node node) {
        if(node == null) return;

        System.out.print(node.getData()+" ");
        preOrder(node.leftChild);
        preOrder(node.rightChild);
    }

    public void inOrder(Node node) {
        if(node == null) return;

        inOrder(node.leftChild);
        System.out.print(node.getData()+" ");
        inOrder(node.rightChild);
    }

    public void postOrder(Node node) {
        if(node == null) return;
        postOrder(node.leftChild);
        postOrder(node.rightChild);
        System.out.print(node.getData()+" ");
    }

    static final class Node {
        int data;
        Node leftChild;
        Node rightChild;

        public final int getData() {
            return data;
        }

        public final void setData(int data) {
            this.data = data;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }
    }
}

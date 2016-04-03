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
     * 删除一个值，有三种情况：
     * 1：没有子节点，把当前节点的父节点引用改为null
     * 2：有一个子节点，把当前节点的子节点链接到他的父节点上
     * 3：有两个子节点，最复杂。把当前节点的右子节点当做一颗树，找这棵树的最小值，用最小值替换当前值。
     * @param data
     * @return
     */
    public void delete(int data) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current.data != data) {
            parent = current;
            if(data < current.data) {
                isLeftChild = true;
                current = current.getLeftChild();
            }else {
                isLeftChild = false;
                current = current.getRightChild();
            }
            //没有找到，返回空
            if(current == null)
                return ;
        }

        //没有子节点
        if(current.leftChild == null && current.rightChild == null) {
            if(current == root)
                root = null;
            else if (isLeftChild)
                parent.leftChild = null;
            else
                parent.rightChild = null;
        }
        //只有一个子节点，无右子节点
        else if (current.rightChild == null) {
            if(current == root)
                root = current.leftChild;
            else if (isLeftChild)
                parent.leftChild = current.leftChild;
            else
                parent.rightChild = current.leftChild;
        }
        //只有一个子节点，无左子节点
        else if (current.leftChild == null) {
            if (current == root)
                root = current.rightChild;
            else if (isLeftChild)
                parent.leftChild = current.rightChild;
            else
                parent.rightChild = current.rightChild;
        }
        //有两个节点
        else {

        }



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

    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(Node node) {
        if(node == null) return;

        inOrder(node.leftChild);
        System.out.print(node.getData()+" ");
        inOrder(node.rightChild);
    }

    /**
     * 后序遍历
     * @param node
     */
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

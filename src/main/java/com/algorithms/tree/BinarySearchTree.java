package com.algorithms.tree;

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



    }
}

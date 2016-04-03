package com.algorithms.graph;

/**
 * Created by linxiao on 2016/4/3.
 * 有向图的拓扑排序
 */
public class TopologicalSort {
    //默认大小
    private final static int DEFAULT_SIZE = 20;
    //顶点
    private Vertex vertexList[];
    //邻接矩阵
    private int adjMat[][];
    //当前顶点数量
    private int vertexNum;
    //存放顶点顺序
    private char sortedArray[];

    public TopologicalSort() {this(DEFAULT_SIZE);}

    public TopologicalSort(int size) {
        vertexList = new Vertex[size];
        adjMat = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                adjMat[i][j] = 0;

        vertexNum = 0;
        sortedArray = new char[size];
    }
    /**
     * 增加顶点
     * @param lab
     */
    public void addVertex(char lab) {
        vertexList[vertexNum++] = new Vertex(lab);
    }

    /**
     * 增加边，有向图是有方向的，因此只有一条边
     * @param start
     * @param end
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    /**
     * 显示顶点
     * @param i
     */
    public void displayVertex(int i) {
        System.out.print(vertexList[i].getLabel());
    }

    public void topo() {
        int orig_ = vertexNum;

        while (vertexNum > 0) {
            int currentVertex = noSuccessors();
            if( currentVertex == -1) {
                System.out.println("ERROR:Graph has cycles");
                return;
            }
            //找到了一个没有后继顶点的顶点，然后数据中
            sortedArray[vertexNum-1] = vertexList[currentVertex].getLabel();
            //删除当前顶点
            deleteVertex(currentVertex);
        }
        //按顺序输出顶点
        System.out.print("Topologically sorted order: ");
        System.out.print(sortedArray[0]);
        for (int i = 1; i < orig_; i++) {
            System.out.print("->");
            System.out.print(sortedArray[i]);
        }
        System.out.println();
    }
    /**
     * 使用邻接矩阵查找没有后继的顶点。只有整个行都没有1存在，才说明有一个顶点没有后继；这时，就返回他的行号。
     * 如果没有这样的顶点，就返回-1
     * @return
     */
    public int noSuccessors() {
        boolean isEdge;
        for (int row = 0; row < vertexNum; row ++ ) {
            isEdge = false;
            for (int col = 0; col < vertexNum; col ++) {
                //如果有，终止内循环，从下个顶点开始继续找
                if(adjMat[row][col] == 1) {
                    isEdge = true;
                    break;
                }
            } //end inner for
            //没有后继顶点，返回当前顶点
            if (!isEdge)
                return row;
        }//end outer for
        return -1;
    }

    /**
     * 删除顶点
     * @param delVert
     */
    public void deleteVertex(int delVert) {
        //如果不是最后一个顶点
        if(delVert != vertexNum-1) {
            //删除顶点
            for (int j = delVert; j < vertexNum -1; j++)
                vertexList[j] = vertexList[j+1];
            //删除row
            for (int row = delVert; row < vertexNum; row++)
                moveRowUp(row, vertexNum);
            //删除列
            for (int col = delVert; col < vertexNum; col++)
                moveColLeft(col, vertexNum-1);
        }
        vertexNum --;
    }

    /**
     * 移动列
     * @param col
     * @param length
     */
    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++)
            adjMat[row][col] = adjMat[row][col+1];
    }
    /**
     * 移动行
     * @param row
     * @param length
     */
    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++)
            adjMat[row][col] = adjMat[row+1][col];
    }
    public static void topoApp() {
        TopologicalSort topo = new TopologicalSort();

        topo.addVertex('A');
        topo.addVertex('B');
        topo.addVertex('C');
        topo.addVertex('D');
        topo.addVertex('E');
        topo.addVertex('F');
        topo.addVertex('G');
        topo.addVertex('H');

        topo.addEdge(0,3);  //AD
        topo.addEdge(0,4);  //AE
        topo.addEdge(1,4);  //BE
        topo.addEdge(2,5);  //CF
        topo.addEdge(3,6);  //DG
        topo.addEdge(4,6);  //EG
        topo.addEdge(5,7);  //FH
        topo.addEdge(6,7);  //GH

        topo.topo();

    }
    public static void main(String args[]) {
        topoApp();
    }

}

package com.algorithms.graph;

/**
 * Created by linxiao on 2016/3/17.
 * 深度优先搜索
 */
public class GraphDFS {
    //默认大小
    private final static int DEFAULT_SIZE = 20;
    //顶点
    private Vertex vertexList[];
    //邻接矩阵
    private int adjMat[][];
    //当前顶点数量
    private int vertexNum;
    //用栈来保存访问过的顶点
    private StackG stackG;

    public GraphDFS() {
        this(DEFAULT_SIZE);
    }
    public GraphDFS(int size) {
        vertexList = new Vertex[size];
        adjMat = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                adjMat[i][j] = 0;

        vertexNum = 0;
        stackG = new StackG(size);
    }

    /**
     * 增加顶点
     * @param lab
     */
    public void addVertex(char lab) {
        vertexList[vertexNum++] = new Vertex(lab);
    }

    /**
     * 增加边
     * @param start
     * @param end
     */
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    /**
     * 显示顶点
     * @param i
     */
    public void displayVertex(int i) {
        System.out.print(vertexList[i].getLabel());
    }

    /**
     * 深度优先遍历
     */
    public void dfs() {
        //从0个顶点开始
        vertexList[0].setWasVisited(true);
        displayVertex(0);
        //放入栈中
        stackG.push(0);

        while ( !stackG.isEmpty() ) {
            //
            int v = getAdjUnvisitedVertex(stackG.peek());
            if (v == -1)
                stackG.pop();
            else  {
                vertexList[v].setWasVisited(true);
                System.out.print("->");
                displayVertex(v);
                stackG.push(v);
            }
        }//end while
        for (int i = 0; i < vertexNum; i++) {
            vertexList[i].setWasVisited(false);
        }

    }

    /**
     * 最小生成树
     */
    public void mst() {
        //从0个顶点开始
        vertexList[0].setWasVisited(true);
        //放入栈中
        stackG.push(0);

        while ( !stackG.isEmpty() ) {
            int currentV = stackG.peek();
            //
            int v = getAdjUnvisitedVertex(currentV);
            if (v == -1)
                stackG.pop();
            else  {
                vertexList[v].setWasVisited(true);
                displayVertex(currentV);
                displayVertex(v);
                System.out.print(" ");
                stackG.push(v);
            }
        }//end while
        for (int i = 0; i < vertexNum; i++) {
            vertexList[i].setWasVisited(false);
        }
    }

    /**
     * 查找给定顶点的邻接点，并且是未访问的。
     * @param v
     * @return
     */
    public int getAdjUnvisitedVertex(int v) {
        for (int i = 0; i < vertexNum; i++) {
            if (adjMat[v][i] == 1 && vertexList[i].isWasVisited() == false)
                return i;
        }
        return -1;
    }
    class StackG {
        private int size;
        private int node[];
        private int top;

        public StackG() {
            this(DEFAULT_SIZE);
        }
        public StackG(int size) {
            this.size = size;
            node = new int[this.size];
            top = -1;
        }
        public void push(int j) {
            node[++top] = j;
        }
        public int pop() {
            return node[top--];
        }
        public int peek() {
            return node[top];
        }
        public boolean isEmpty() {
            return top == -1;
        }
    }

    /**
     * 深度优先搜索
     */
    public static void dfsApp(){
        GraphDFS graphDFS = new GraphDFS();

        graphDFS.addVertex('A');
        graphDFS.addVertex('B');
        graphDFS.addVertex('C');
        graphDFS.addVertex('D');
        graphDFS.addVertex('E');

        graphDFS.addEdge(0,1);  //AB
        graphDFS.addEdge(1,2);  //BC
        graphDFS.addEdge(0,3);  //AD
        graphDFS.addEdge(3,4);  //DE

        System.out.print("Visits: ");
        graphDFS.dfs();
        System.out.println();
    }

    /**
     * 最小生成树
     */
    public static void mstApp() {
        GraphDFS graphDFS = new GraphDFS();

        graphDFS.addVertex('A');
        graphDFS.addVertex('B');
        graphDFS.addVertex('C');
        graphDFS.addVertex('D');
        graphDFS.addVertex('E');

        graphDFS.addEdge(0,1);  //AB
        graphDFS.addEdge(0,2);  //AC
        graphDFS.addEdge(0,3);  //AD
        graphDFS.addEdge(0,4);  //AE

        graphDFS.addEdge(1,2);  //BC
        graphDFS.addEdge(1,3);  //BD
        graphDFS.addEdge(1,4);  //BE

        graphDFS.addEdge(2,3);  //CD
        graphDFS.addEdge(2,4);  //CE

        graphDFS.addEdge(3,4);  //DE

        System.out.print("Minimum spanning tree: ");
        graphDFS.mst();
        System.out.println();

    }
    public static void main(String args[]) {
        dfsApp();
        mstApp();
    }
}

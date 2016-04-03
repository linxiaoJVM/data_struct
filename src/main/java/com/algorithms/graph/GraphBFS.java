package com.algorithms.graph;

/**
 * Created by linxiao on 2016/3/22.
 * 广度优先搜索
 */
public class GraphBFS {
    //默认大小
    private final static int DEFAULT_SIZE = 20;
    //顶点
    private Vertex vertexList[];
    //邻接矩阵
    private int adjMat[][];
    //当前顶点数量
    private int vertexNum;
    //用队列来保存访问过的顶点
    private Queue queue;

    public GraphBFS() {this(DEFAULT_SIZE);}
    public GraphBFS(int size) {
        vertexList = new Vertex[size];
        adjMat = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                adjMat[i][j] = 0;

        vertexNum = 0;
        queue = new Queue(size);
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
     * 广度优先遍历
     */
    public void bfs() {
        //从0个顶点开始
        vertexList[0].setWasVisited(true);
        displayVertex(0);
        //放入队列中
        queue.insert(0);
        int v1, v2;

        while ( !queue.isEmpty() ) {
            v1 = queue.remove();
            /**
             * 查找v1顶点的 所有邻接顶点。打印出来，并放入队列头中
             */
            while ( (v2=getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].setWasVisited(true);
                System.out.print("->");
                displayVertex(v2);
                //放入队列中
                queue.insert(v2);
            }//end while
        } //end while

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


    class Queue {
        private  int maxSize;
        private int [] queArray;
        private int front;
        private int rear;
        private int nItems;

        public Queue(int size) {
            maxSize = size;
            queArray = new int[maxSize];
            front = 0;
            rear = -1;
            nItems = 0;
        }
        public Queue() {
            this(10);
        }

        public void insert(int i) {
            if(rear == (maxSize -1))
                rear = -1;
            queArray[++rear] = i;
            nItems ++;
        }
        public int remove() {
            int temp = queArray[front++];
            if(front == maxSize)
                front = 0;
            nItems --;
            return temp;
        }
        public int peekFront() {
            return queArray[front];
        }
        public boolean isEmpty() {
            return (nItems==0);
        }
        public boolean isFull() {
            return (nItems==maxSize);
        }
        public int size() {
            return nItems;
        }
    }

    public static void main(String args[]) {
        GraphBFS graphBFS = new GraphBFS();

        graphBFS.addVertex('A');
        graphBFS.addVertex('B');
        graphBFS.addVertex('C');
        graphBFS.addVertex('D');
        graphBFS.addVertex('E');

        graphBFS.addEdge(0,1);  //AB
        graphBFS.addEdge(1,2);  //BC
        graphBFS.addEdge(0,3);  //AD
        graphBFS.addEdge(3,4);  //DE

        System.out.print("Visits: ");
        graphBFS.bfs();
        System.out.println();
    }
}

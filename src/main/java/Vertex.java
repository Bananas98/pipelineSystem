import java.util.ArrayList;

class Vertex{
    int vertexNum;                  //int vertexNum
    ArrayList<Integer> adjList;     //list of adjacent vertices.
    ArrayList<Integer> costList;    //list of cost or distance of adjacent vertices.

    int queuePos;                   //pos of vertex in the priority queue.
    long dist;                      //distance from start vertex.
    boolean processed;              //is processed while traversing the graph.

    public Vertex(){
    }


    //Vertex Constructor.
    public Vertex(int vertexNum){
        this.vertexNum=vertexNum;
        this.adjList = new ArrayList<>();
        this.costList = new ArrayList<>();
    }


    //function to create the graph and reverse graph. forwPriorityQ for graph and revPriorityQ for reverse graph.
    public void createGraph(Vertex[] graph, Vertex[] reverseGraph, int [] forwPriorityQ, int [] revPriorityQ){
        for(int i=0;i<graph.length;i++){
            graph[i].queuePos = i;
            graph[i].processed = false;
            graph[i].dist = Integer.MAX_VALUE;

            reverseGraph[i].queuePos = i;
            reverseGraph[i].processed = false;
            reverseGraph[i].dist = Integer.MAX_VALUE;

            forwPriorityQ[i]=i;
            revPriorityQ[i]=i;
        }
    }
}

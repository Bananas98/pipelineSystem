import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestApp {

    //main function to run the program.
    public static void main(String[] args) throws Exception {
        String allData = ParseCSVLineByLine.getContent("MyCSV.csv");
        String twoFindVertexPath = ParseCSVLineByLine.getContent("MyCSVR.csv");
        int n = ParseCSVLineByLine.countVertex(allData);   //number of vertices.
        int m = ParseCSVLineByLine.countEdge(allData);   //number of edges.
        //create two graphs forw graph and reverse graph.
        Vertex[] graph = new Vertex[n];
        Vertex[] reverseGraph = new Vertex[n];

        //initialize the vertex.
        for (int i = 0; i < n; i++) {
            graph[i] = new Vertex(i);
            reverseGraph[i] = new Vertex(i);
        }

        //get the edges.
        for (int i = 0; i < m; i++) {
            int u, v;
            int w;
            u = ParseCSVLineByLine.startVertexEdge(allData,i); //start vertex edge
            v = ParseCSVLineByLine.endVertexEdge(allData,i);   //end vertex edge
            w = ParseCSVLineByLine.weightOfEdge(allData,i);   //weight of edge

            graph[u - 1].adjList.add(v - 1);
            graph[u - 1].costList.add(w);

            reverseGraph[v - 1].adjList.add(u - 1);
            reverseGraph[v - 1].costList.add(w);
        }


        int q = ParseCSVLineByLine.numberOfQueries(twoFindVertexPath); //number of queries.

        List <String>list = new LinkedList<>();
        //get the queries
        for (int i = 0; i < q; i++) {
            int s, t;
            s = ParseCSVLineByLine.sourceVertex(twoFindVertexPath,i) - 1;   //source vertex
            t = ParseCSVLineByLine.targetVertex(twoFindVertexPath,i) - 1;   //target vertex

            list.add(Dijkstra.computeDistance(graph, reverseGraph, s, t, i));
        }

        WriterInCSV.writer(list,";","result.csv" );
    }
}

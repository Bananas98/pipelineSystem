import java.util.ArrayList;

public class Dijkstra {
    //function to relax edges i.e traverse only the adjacent vertices of the given vertex.
    private static void relaxEdges(Vertex[] graph, int vertex, int[] priorityQ, PriorityQueue queue, int queryId) {
        ArrayList<Integer> vertexList = graph[vertex].adjList;   //get the adjacent vertices list.
        ArrayList<Integer> costList = graph[vertex].costList;    //get the cost list of adjacent vertices.
        graph[vertex].processed = true;             //mark processed true.

        for (int i = 0; i < vertexList.size(); i++) {
            int temp = vertexList.get(i);
            int cost = costList.get(i);

            if (graph[temp].dist > graph[vertex].dist + cost) {
                graph[temp].dist = graph[vertex].dist + cost;
                queue.changePriority(graph, priorityQ, graph[temp].queuePos);
            }
        }
    }


    //function to compute distance between start vertex s and target vertex t.
    public static String computeDistance(Vertex[] graph, Vertex[] reverseGraph, int s, int t, int queryId) {


        StringBuilder result  = new StringBuilder();

        //create two PriorityQueues forwQ for forward graph and revQ for reverse graph.
        PriorityQueue queue = new PriorityQueue();
        int[] forwPriorityQ = new int[graph.length];  //for forward propagation.
        int[] revPriorityQ = new int[graph.length];   //for reverse propagation.

        //create graph.
        Vertex vertex = new Vertex();
        vertex.createGraph(graph, reverseGraph, forwPriorityQ, revPriorityQ);

        //dist of s from s is 0.
        //in rev graph dist of t from t is 0.
        graph[s].dist = 0;
        reverseGraph[t].dist = 0;
        queue.makeQueue(graph, forwPriorityQ, s, t);
        queue.makeQueue(reverseGraph, revPriorityQ, t, s);

        //store the processed vertices while traversing.
        ArrayList<Integer> forgraphprocessedVertices = new ArrayList<>();  //for forward propagation.
        ArrayList<Integer> revgraphprocessedVertices = new ArrayList<>();  //for reverse propagation.


        for (int i = 0; i < graph.length; i++) {

            //extract the vertex with min dist from forwQ.
            int vertex1 = queue.extractMin(graph, forwPriorityQ, i);
            if (graph[vertex1].dist == Integer.MAX_VALUE) {
                continue;
            }

            //relax the edges of the extracted vertex.
            relaxEdges(graph, vertex1, forwPriorityQ, queue, queryId);

            //store into the processed vertices list.
            forgraphprocessedVertices.add(vertex1);

            //check if extracted vertex also processed in the reverse graph. If yes find the shortest distance.
            if (reverseGraph[vertex1].processed) {
                result.append("true;").append(shortestPath(graph, reverseGraph, forgraphprocessedVertices, revgraphprocessedVertices, queryId)+"\n");
                break;
            }


            //extract the vertex with min dist from revQ.
            int revVertex = queue.extractMin(reverseGraph, revPriorityQ, i);
            if (reverseGraph[revVertex].dist == Integer.MAX_VALUE) {
                continue;
            }

            //relax the edges of the extracted vertex.
            relaxEdges(reverseGraph, revVertex, revPriorityQ, queue, queryId);

            //store in the processed vertices list of reverse graph.
            revgraphprocessedVertices.add(revVertex);

            //check if extracted vertex is also processed in the forward graph. If yes find the shortest distance.
            if (graph[revVertex].processed) {
                result.append("true;").append(shortestPath(graph, reverseGraph, forgraphprocessedVertices, revgraphprocessedVertices, queryId)+"\n");
                break;
            }


        }

        return result.toString();
    }


    //function to find the shortest distance from the stored processed vertices of both forward and reverse graph.
    private static long shortestPath(Vertex[] graph, Vertex[] reverseGraph, ArrayList<Integer> forgraphprocessedVertices, ArrayList<Integer> revgraphprocessedVertices, int queryId) {
        long distance = Integer.MAX_VALUE;

        //process the forward list.
        for (int i = 0; i < forgraphprocessedVertices.size(); i++) {
            int vertex = forgraphprocessedVertices.get(i);
            if (reverseGraph[vertex].dist + graph[vertex].dist >= Integer.MAX_VALUE) {
                continue;
            }
            long tempdist = graph[vertex].dist + reverseGraph[vertex].dist;
            if (distance > tempdist) {
                distance = tempdist;
            }
        }

        //process the reverse list.
        for (int i = 0; i < revgraphprocessedVertices.size(); i++) {
            int vertex = revgraphprocessedVertices.get(i);
            if (reverseGraph[vertex].dist + graph[vertex].dist >= Integer.MAX_VALUE) {
                continue;
            }
            long tempdist = reverseGraph[vertex].dist + graph[vertex].dist;
            if (distance > tempdist) {
                distance = tempdist;
            }

        }
        return distance;
    }

}

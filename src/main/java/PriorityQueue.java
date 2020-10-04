public class PriorityQueue{

    //function to swap elements in the PriorityQueue
    public void swap(Vertex[] graph, int [] priorityQ, int index1, int index2){
        int temp = priorityQ[index1];

        priorityQ[index1]=priorityQ[index2];
        graph[priorityQ[index2]].queuePos=index1;

        priorityQ[index2]=temp;
        graph[temp].queuePos=index2;
    }

    //function to swap start vertex and first vertex in the priorityQ.
    public void makeQueue( Vertex[] graph, int [] forwpriorityQ, int source, int target){
        swap(graph, forwpriorityQ,0,source);
    }


    //function to extract the min value from the PriorityQueue
    public int extractMin( Vertex[] graph, int [] priorityQ, int extractNum){
        int vertex = priorityQ[0];
        int size = priorityQ.length-1-extractNum;
        swap(graph,priorityQ,0,size);
        siftDown(0,graph,priorityQ,size);
        return vertex;
    }

    //function to sift down the element at the given index in the PriorityQueue.
    public void siftDown(int index,  Vertex[] graph, int [] priorityQ, int size){
        int min = index;
        if(2*index+1<size && graph[priorityQ[index]].dist > graph[priorityQ[2*index+1]].dist){
            min = 2*index+1;
        }
        if(2*index+2<size && graph[priorityQ[min]].dist > graph[priorityQ[2*index+2]].dist){
            min = 2*index+2;
        }
        if(min!=index){
            swap(graph,priorityQ,min,index);
            siftDown(min,graph,priorityQ,size);
        }
    }

    //function to change priority of an element.(priority can only be decreased.)
    public void changePriority(Vertex[] graph, int [] priorityQ, int index){
        if((index-1)/2 > -1 && graph[priorityQ[index]].dist < graph[priorityQ[(index-1)/2]].dist){
            swap(graph,priorityQ,index,(index-1)/2);
            changePriority(graph,priorityQ,(index-1)/2);
        }
    }
}

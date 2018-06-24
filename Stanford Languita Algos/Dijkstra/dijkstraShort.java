package dijkstra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import dijkstra.graph.Edge;

public class dijkstraShort{
    public PriorityQueue<Integer> minHeap;
    
    public Integer[] distKey;
    private ArrayList<Integer> dynamicWeights;
    private ArrayList<Integer> vertexs; // List of unvisited Vertex
    ArrayList<graph.Edge> [] adjList;
    class vertex{
        int vertexID;
        int key;
        vertex(int id, int keyVal){
            vertexID = id;
            key = keyVal;
        }
    }
    class keyComparator implements Comparator<vertex>{
        public int compare(vertex v1, vertex v2){
            if(v1.key > v2.key){
                return 1;
            }
            else if(v1.key < v2.key){
                return -1;
            }
            return 0;
        }
    }

    dijkstraShort(graph grphWtGraph){
        int vetrices = grphWtGraph.vertices;
        adjList = grphWtGraph.adjList;
        distKey = new Integer[vetrices];
        vertexs = new ArrayList<>();
        minHeap = new PriorityQueue<>(vetrices);
        dynamicWeights = new ArrayList<>();
        for(int i =0; i< vetrices; i++){
            vertexs.add(i, i);
            distKey[i] = 1000000;
            dynamicWeights.add(i, distKey[i]);
        }
    
    }

   public void intilaizeRunDijks(int sourceKey){
    distKey[sourceKey] = 0;
    dynamicWeights.set(sourceKey, distKey[sourceKey]);
    minHeap.addAll(dynamicWeights);

    while(vertexs.size() != 0)
    {
        Integer temp_wts = minHeap.poll();
        int temp_ID = dynamicWeights.indexOf(temp_wts);
        int current = vertexs.get(temp_ID);
        // Remove the Current element
        dynamicWeights.remove(temp_ID);
        vertexs.remove(temp_ID);
        ArrayList<Edge> edgesFrmCurrent = adjList[current];
        // Relaxation
        for(int i =0; i <adjList[current].size(); i++){
            Integer distance = distKey[current] + edgesFrmCurrent.get(i).getWeight();
            int v_ID = edgesFrmCurrent.get(i).getNodeID()-1;
           if(distKey[v_ID] > distance) {
                // Delete old Key Val
                minHeap.remove(distKey[v_ID]);
                distKey[v_ID] = distance;
                // Insert New Key Val
                minHeap.add(distKey[v_ID]);
                // UpDate Dynamic Weights
                int dynUpID = vertexs.indexOf(v_ID);
                dynamicWeights.set(dynUpID, distance);
           }
        }
    }
    // Print REquired  Shortest distnce from Source 
    int [] req_V = new int[] {7,37,59,82,99,115,133,165,188,197};
    for(int i = 0; i < req_V.length; i++){
        System.out.println("Shortest Distance Between "+(sourceKey+1) + " and " + req_V[i] + " is " + distKey[req_V[i]-1]);
    }
    }    
}
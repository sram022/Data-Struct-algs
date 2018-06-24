package dijkstra;
import java.io.*;
import java.util.ArrayList;

public class graph {
    public ArrayList <graph.Edge>[] adjList;
    int vertices;

    class Edge{
        private int nodeID,weight;

        Edge(int id, int wt){
            nodeID = id;
            weight = wt;
        }
        public int getNodeID(){
            return nodeID;
        }
        public int getWeight(){
            return weight;
        }

    }
    public void weightedGraph(int num, String path) throws FileNotFoundException,IOException{
        File file =  new File(path);
        vertices = num;
        BufferedReader bf  = new BufferedReader(new FileReader(file));
        String line;
        adjList = new ArrayList[vertices];
        for (int i = 0; i < vertices; i++){
            adjList[i] = new ArrayList<graph.Edge>();
        }
        int vertex_Cnt = 0;
        while ((line = bf.readLine()) != null ){
                String[] str = line.split("\t");
                ArrayList<graph.Edge> temArrayList = new ArrayList<graph.Edge>();
            for(int i = 1; i < str.length; i++){
                String subStr  =  str[i];
                String[] subStrArr = subStr.split(",");
                graph gp = new graph();
                Edge edg = gp.new Edge(Integer.parseInt(subStrArr[0]) , Integer.parseInt(subStrArr[1]));
                temArrayList.add(i-1, edg);
            }
            adjList[vertex_Cnt] = temArrayList;
            vertex_Cnt++;

        }
        bf.close();

    }
}

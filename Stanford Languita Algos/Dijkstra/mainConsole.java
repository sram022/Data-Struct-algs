package dijkstra;

import java.io.FileNotFoundException;
import java.io.IOException;

public class mainConsole{ 

public static void main(String[] args) throws FileNotFoundException, IOException {
    graph wtGraph = new graph();
    wtGraph.weightedGraph(200, "/home/sram022/Data Science/Java/dijkstra/src/dijkstra/dijkstraData.txt");
    dijkstraShort shortPath = new dijkstraShort(wtGraph);
    shortPath.intilaizeRunDijks(0);
    System.out.println("");
    
}
}
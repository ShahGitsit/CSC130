import java.io.*;
import java.util.*;
 
public class DijkstraTest {
   private static final Graph.Edge[] GRAPH = {
      new Graph.Edge("0", "1", 10),
      new Graph.Edge("0", "4", 5),
      new Graph.Edge("1", "2", 1),
      new Graph.Edge("1", "4", 2),
      new Graph.Edge("2", "3", 4),
      new Graph.Edge("3", "0", 7),
      new Graph.Edge("3", "2", 6),
      new Graph.Edge("4", "1", 3),
      new Graph.Edge("4", "2", 9),
      new Graph.Edge("4", "3", 2),
   };

 
   public static void main(String[] args) {
      Graph g = new Graph(GRAPH);
      g.dijkstra("0");
      g.printAllPaths();
   }
}
 

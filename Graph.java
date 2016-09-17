import java.io.*;
import java.util.*;

public class Graph {
   //mapping the name of vertex to objects
   private final Map<String, Vertex> graph; 
 
   public static class Edge {
      public final String v1, v2;
      public final int dist;
      public Edge(String v1, String v2, int dist) {
         this.v1 = v1;
         this.v2 = v2;
         this.dist = dist;
      }
   }
 
   public static class Vertex implements Comparable<Vertex> {
      public final String name;
      //setting max value to infinity
      public int dist = Integer.MAX_VALUE;
      public Vertex previous = null;
      public final Map<Vertex, Integer> neighbours = new HashMap<>();
   
      public Vertex(String name) {
         this.name = name;
      }
      //PrintPath
      private void printPath() {      
         System.out.printf(" -> %s(%d)", this.name, this.dist); 
      }
   
      public int compareTo(Vertex other) {
         return Integer.compare(dist, other.dist);
      }
   }
   //Building a graph from the set of edges provided
   public Graph(Edge[] edges) {
      graph = new HashMap<>(edges.length);
      //go through to find all vertices
      for (Edge e : edges) {
         if (!graph.containsKey(e.v1)) graph.put(e.v1, new Vertex(e.v1));
         if (!graph.containsKey(e.v2)) graph.put(e.v2, new Vertex(e.v2));
      }
      //go through to set the neighbouring vertices
      for (Edge e : edges) {
         graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
      }
   }
   //Running dijkstra using the source 
   public void dijkstra(String startName) {
      //special condition 
      if (!graph.containsKey(startName)) {
         System.err.printf("Graph does not contain a starting condition \"%s\"\n", startName);
         return;
      }
      final Vertex source = graph.get(startName);
      NavigableSet<Vertex> q = new TreeSet<>();
   
      // set-up vertices
      for (Vertex v : graph.values()) {
         v.previous = v == source ? source : null;
         v.dist = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }
   
      dijkstra(q);
   }
   //Java Implementation of the dijkastra's algorithm from study guide
   private void dijkstra(final NavigableSet<Vertex> q) {      
      Vertex u, v;
      //while queue is not empty
      while (!q.isEmpty()) {
         //instead of going alphabetical order, it picks the closest neighbor
         //the very first time this will pick the source
         u = q.pollFirst(); 
         //ignoring all other vertices since they are not reachable
         if (u.dist == Integer.MAX_VALUE) 
            break;
      
         //look at distances of each neighbour
         for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
            v = a.getKey();
         
            final int alternateDist = u.dist + a.getValue();
            //update if new distance is shorter
            if (alternateDist < v.dist) {
               q.remove(v);
               v.dist = alternateDist;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
    /** Prints the path from the source to every vertex (output order is not guaranteed) */
   public void printAllPaths() {
      for (Vertex v : graph.values()) {
         v.printPath();
         System.out.println();
      }
   }
}
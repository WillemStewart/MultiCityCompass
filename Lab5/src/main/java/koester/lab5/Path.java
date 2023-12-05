//Path.java
package koester.lab5;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Path {
    private double pDiddy;
    class PathVertexInfo implements Comparable<PathVertexInfo> {
        public Vertex vertex;
        public double distance;
        public Vertex predecessor;

        public PathVertexInfo(Vertex vertex) {
           this.vertex = vertex;
           distance = Double.POSITIVE_INFINITY;
           predecessor = null;
        }

        public int compareTo(PathVertexInfo other) {
            if (distance > other.distance) {
                return 1;
            }
            else if (distance < other.distance) {
                return -1;
            }
            return 0;
        }
    }
    HashMap<Vertex, PathVertexInfo> dijkstraShortestPath(Graph graph, Vertex startVertex) {
        // Create the HashMap for vertex information
        HashMap<Vertex, PathVertexInfo> info;
        info = new HashMap<Vertex, PathVertexInfo>();

        // Put all graph vertices in both the info HashMap and the PriorityQueue 
        // of unvisited vertices
        PriorityQueue<PathVertexInfo> unvisited;
        unvisited = new PriorityQueue<PathVertexInfo>();
        for (Vertex vertex : graph.getVertices()) 
        {
            PathVertexInfo vertexInfo = new PathVertexInfo(vertex);
            unvisited.add(vertexInfo);
            info.put(vertex, vertexInfo);
        }

        // startVertex has a distance of 0 from itself
        info.get(startVertex).distance = 0.0;

        // Iterate through all vertices in the priority queue
        while (unvisited.size() > 0) 
        {
            // Get info about the vertex with the shortest distance from startVertex
            PathVertexInfo currentInfo = unvisited.peek();
            unvisited.remove();

            // Check potential path lengths from the current vertex to all neighbors
            for (Edge edge : graph.getEdgesFrom(currentInfo.vertex)) 
            {
                Vertex adjacentVertex = edge.toVertex;
                double alternativePathDistance = currentInfo.distance + edge.weight;

                // If a shorter path from startVertex to adjacentVertex is found,
                // update adjacentVertex's distance and predecessor
                PathVertexInfo adjacentInfo = info.get(adjacentVertex);
                if (alternativePathDistance < adjacentInfo.distance) 
                {
                    unvisited.remove(adjacentInfo);
                    adjacentInfo.distance = alternativePathDistance;
                    adjacentInfo.predecessor = currentInfo.vertex;
                    unvisited.add(adjacentInfo);
                }
            }
        }
        return info;
    }
    String getShortestPath(Vertex startVertex, Vertex endVertex, HashMap<Vertex, PathVertexInfo> infoMap) {
        // Start from endVertex and build the path in reverse.
        String path = "";
        Vertex currentVertex = endVertex;
        pDiddy = infoMap.get(currentVertex).distance;
        while (currentVertex != startVertex) {
           path = " -> " + currentVertex.label + path;
           currentVertex = infoMap.get(currentVertex).predecessor;
        }
        path = startVertex.label + path;
        return path;
    }
    
    public double getpDiddy()
    {
        return pDiddy;
    }
}

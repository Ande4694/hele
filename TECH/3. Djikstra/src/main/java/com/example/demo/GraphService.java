package com.example.demo;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class GraphService {

    List<Vertex> vertices;
    List<Edge> edges;
    List<Edge> shortestPath;

    public GraphService(){
        initGraph();
    }

    private void initGraph(){
        List<Vertex> vertices = new ArrayList<>();
        System.out.println("iniGraph Called");

        vertices.add(new Vertex(306, 328, "Rødovre")); // 0
        vertices.add(new Vertex(200, 340, "Glostrup"));// 1
        vertices.add(new Vertex(320, 275, "Vanløse"));// 2
        vertices.add(new Vertex(240, 170, "Herlev"));// 3
        vertices.add(new Vertex(136, 183, "Ballerup"));// 4
        vertices.add(new Vertex(62, 330, "Herstedvester"));// 5
        vertices.add(new Vertex(200, 340, "Glostrup"));// 6
        vertices.add(new Vertex(190, 210, "Skovlunde"));// 7

        this.vertices = vertices;

        // Create edges:
        this.edges = new ArrayList<>();
        edges.add(new Edge(vertices.get(0), vertices.get(1), 12));
        edges.add(new Edge(vertices.get(0), vertices.get(2), 8));
        edges.add(new Edge(vertices.get(2), vertices.get(7), 11));
        edges.add(new Edge(vertices.get(2), vertices.get(3), 10));
        edges.add(new Edge(vertices.get(3), vertices.get(4), 6));
        edges.add(new Edge(vertices.get(3), vertices.get(7), 10));
        edges.add(new Edge(vertices.get(4), vertices.get(7), 6));
        edges.add(new Edge(vertices.get(4), vertices.get(5), 13));
        edges.add(new Edge(vertices.get(1), vertices.get(5), 8));

        // Create Graph
        Graph<Vertex> graph = new Graph<>();
        for (Edge<Vertex> edge :edges) { // add all edges, and hence create the connections
            graph.addEdge(edge);
        }
        Dijkstra dijkstra = new Dijkstra();
        shortestPath = dijkstra.runDijkstra(vertices.get(5), vertices.get(2), graph);  // find vej fra Herstedvester til Vanløse

    }
}
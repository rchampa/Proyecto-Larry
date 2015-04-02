package es.ric.core.algoritmos.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import es.ric.core.Arista;
import es.ric.core.Grafo;
import es.ric.core.Vertice;
import es.ric.core.algoritmos.Dijkstra;

public class Test1 {

	private List<Vertice> vertices;
	private List<Arista> aristas;

	@Test
	public void testExcute() {
		vertices = new ArrayList<Vertice>();
		aristas = new ArrayList<Arista>();
		for (int i = 0; i < 11; i++) {
			Vertice location = new Vertice("Node_" + i, "Node_" + i);
			vertices.add(location);
		}

		addLane("Arista_0", 0, 1, 85);
		addLane("Arista_1", 0, 2, 217);
		addLane("Arista_2", 0, 4, 173);
		addLane("Arista_3", 2, 6, 186);
		addLane("Arista_4", 2, 7, 103);
		addLane("Arista_5", 3, 7, 183);
		addLane("Arista_6", 5, 8, 250);
		addLane("Arista_7", 8, 9, 84);
		addLane("Arista_8", 7, 9, 167);
		addLane("Arista_9", 4, 9, 502);
		addLane("Arista_10", 9, 10, 40);
		addLane("Arista_11", 1, 10, 600);

		// Lets check from location Loc_1 to Loc_10
		Grafo graph = new Grafo(vertices, aristas);
		Dijkstra dijkstra = new Dijkstra(graph);
		dijkstra.execute(vertices.get(0));
		LinkedList<Vertice> path = dijkstra.getPath(vertices.get(10));

		assertNotNull(path);
		assertTrue(path.size() > 0);

		for (Vertice Vertice : path) {
			System.out.println(Vertice);
		}

	}

	private void addLane(String laneId, int sourceLocNo, int destLocNo,	int duration) {
		Arista lane = new Arista(laneId, vertices.get(sourceLocNo),	vertices.get(destLocNo), duration);
		aristas.add(lane);
	}

}

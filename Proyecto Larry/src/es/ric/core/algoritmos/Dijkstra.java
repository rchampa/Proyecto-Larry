package es.ric.core.algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.ric.core.Arista;
import es.ric.core.Grafo;
import es.ric.core.Vertice;

public class Dijkstra {

	private final List<Vertice> vertices;
	private final List<Arista> aristas;
	private Set<Vertice> settledNodes;
	private Set<Vertice> unSettledNodes;
	private Map<Vertice, Vertice> predecessors;
	private Map<Vertice, Integer> distance;

	public Dijkstra(Grafo grafo) {
		// create a copy of the array so that we can operate on this array
		this.vertices = new ArrayList<Vertice>(grafo.getVertices());
		this.aristas = new ArrayList<Arista>(grafo.getAristas());
	}

	public void execute(Vertice source) {
		settledNodes = new HashSet<Vertice>();
		unSettledNodes = new HashSet<Vertice>();
		distance = new HashMap<Vertice, Integer>();
		predecessors = new HashMap<Vertice, Vertice>();
		distance.put(source, 0);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Vertice node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}

	private void findMinimalDistances(Vertice node) {
		List<Vertice> adjacentNodes = getNeighbors(node);
		for (Vertice target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node)
					+ getDistance(node, target)) {
				distance.put(target,
						getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	private int getDistance(Vertice node, Vertice target) {
		for (Arista Arista : aristas) {
			if (Arista.getSource().equals(node)
					&& Arista.getDestination().equals(target)) {
				return Arista.getWeight();
			}
		}
		throw new RuntimeException("Should not happen");
	}

	private List<Vertice> getNeighbors(Vertice node) {
		List<Vertice> neighbors = new ArrayList<Vertice>();
		for (Arista Arista : aristas) {
			if (Arista.getSource().equals(node)
					&& !isSettled(Arista.getDestination())) {
				neighbors.add(Arista.getDestination());
			}
		}
		return neighbors;
	}

	private Vertice getMinimum(Set<Vertice> Verticees) {
		Vertice minimum = null;
		for (Vertice Vertice : Verticees) {
			if (minimum == null) {
				minimum = Vertice;
			} else {
				if (getShortestDistance(Vertice) < getShortestDistance(minimum)) {
					minimum = Vertice;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Vertice Vertice) {
		return settledNodes.contains(Vertice);
	}

	private int getShortestDistance(Vertice destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	/*
	 * This method returns the path from the source to the selected target and
	 * NULL if no path exists
	 */
	public LinkedList<Vertice> getPath(Vertice target) {
		LinkedList<Vertice> path = new LinkedList<Vertice>();
		Vertice step = target;
		// check if a path exists
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		// Put it into the correct order
		Collections.reverse(path);
		return path;
	}

}

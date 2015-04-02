package es.ric.core;

public class Arista {

	private String id;
	private Vertice origen;
	private Vertice destino;
	private int peso;

	public Arista(String id, Vertice origen, Vertice destino, int peso) {
		this.id = id;
		this.origen = origen;
		this.destino = destino;
		this.peso = peso;
	}

	public String getId() {
		return id;
	}

	public Vertice getDestination() {
		return destino;
	}

	public Vertice getSource() {
		return origen;
	}

	public int getWeight() {
		return peso;
	}

	@Override
	public String toString() {
		return origen + " " + destino;
	}

}

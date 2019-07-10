package it.polito.tdp.crimes.model;

public class ArcoPeso implements Comparable<ArcoPeso>{
	
	private String id1;
	private String id2;
	private Double peso;
	public ArcoPeso(String id1, String id2, Double peso) {
		super();
		this.id1 = id1;
		this.id2 = id2;
		this.peso = peso;
	}
	public String getId1() {
		return id1;
	}
	public String getId2() {
		return id2;
	}
	public Double getPeso() {
		return peso;
	}
	@Override
	public int compareTo(ArcoPeso o) {
		return - peso.compareTo(o.peso);
	}
	@Override
	public String toString() {
		return String.format("Id1=%s, Id2=%s, peso=%s", id1, id2, peso);
	}
	

}

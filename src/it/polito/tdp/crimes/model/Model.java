package it.polito.tdp.crimes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private Graph<String, DefaultWeightedEdge> grafo;
	private List<ArcoPeso> arcopeso;
	
	public Model() {
		this.dao = new EventsDao();
		this.arcopeso = new ArrayList<>();
	}
	
	public List<String> getCategoriaReato(){
		return this.dao.getCategoriaReato();
	}
	
	public List<Integer> getAnno(){
		return this.dao.getAnno();
	}
	
	public void creaGrafo(String categoria, Integer anno) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.grafo, this.dao.getOffenseId(categoria, anno));
		for(String id1: this.grafo.vertexSet()) {
			for(String id2: this.grafo.vertexSet()) {
				if(!id1.equals(id2) && this.grafo.getEdge(id1, id2)==null) {
					Double peso = this.dao.getConnessioni(categoria, anno, id1, id2);
					Graphs.addEdge(this.grafo, id1, id2, peso);
					this.arcopeso.add(new ArcoPeso(id1, id2, peso));
					System.out.println("Arco aggiunto: "+ id1 + " -> " + id2+ " con peso= "+ peso);
				}
			}
		}
	}
	
	public int getVertici() {
		return this.grafo.vertexSet().size();
	}
	public int getArchi() {
		return this.grafo.edgeSet().size();
	}
	public List<ArcoPeso> getPesoMax(){
		List<ArcoPeso> list = new ArrayList<>();
		Collections.sort(this.arcopeso);
		Double pesofisso = this.arcopeso.get(0).getPeso();
		for(ArcoPeso pesomobile: this.arcopeso) {
			if(pesomobile.getPeso().equals(pesofisso)) {
				list.add(pesomobile);
			}
			else
				break;
			
		}
		return list;
	}
}

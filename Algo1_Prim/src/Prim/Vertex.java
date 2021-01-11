package Prim;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Vertex {
	String vName;
	Integer vColor;
	Double key;
	Vertex pie;
	Map<Vertex,Double> adj;
	
	public Vertex(String s) {
		this.vName = s;
		adj = new LinkedHashMap<Vertex,Double>();
	}
	
	@Override
	public String toString() {
		return vName;
	}
	
	@Override
	public boolean equals(Object v) {
		return this.vName.equals(v.toString());
	}
	
	@Override
	public int hashCode() {
		return vName.hashCode(); //String implementation
	}
	
	
}

class VertexComparator implements Comparator<Vertex>{

	@Override
	public int compare(Vertex v1, Vertex v2) {
		return (int) (v1.key - v2.key);
	}
	
	
}

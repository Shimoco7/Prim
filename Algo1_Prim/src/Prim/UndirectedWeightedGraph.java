package Prim;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Vector;


public class UndirectedWeightedGraph {
	
	Vector<Vertex> vertices;
	private Map<String,Vertex> m1;
	
	public UndirectedWeightedGraph() {
		vertices = new Vector<Vertex>();
		m1 = new LinkedHashMap<String,Vertex>();
		
	}
	
	public boolean addVertex(String n) {
		if(n==null)
			return false;
		Vertex v = new Vertex(n);
		m1.put(n, v);
		vertices.add(v);
		return true;
	}
	
	public boolean removeVertex(String n) {
		if(n==null)
			return false;
		if(!m1.containsKey(n)) 
			return false;
		m1.remove(n);
		vertices.removeIf(v->v.vName.equals(n));
		return true;
	}
	
	public boolean addEdge(String src,String dst,Double weight) {
		if(src==null||dst==null)
			return false;
		if(m1.get(src).adj.containsKey(m1.get(dst))||m1.get(dst).adj.containsKey(m1.get(src)))
			return false;
		m1.get(src).adj.put(m1.get(dst), weight);
		m1.get(dst).adj.put(m1.get(src), weight);
		return true;
	}
	
	public boolean removeEdge(String src, String dst) {
		if(src==null||dst==null)
			return false;
		if(!m1.get(src).adj.containsKey(m1.get(dst))||!m1.get(dst).adj.containsKey(m1.get(src)))
			return false;
		m1.get(src).adj.remove(m1.get(dst));
		m1.get(dst).adj.remove(m1.get(src));
		return true;
	}
	
public UndirectedWeightedGraph getMST() {
		
		if(this.vertices.isEmpty())
			return null;
		UndirectedWeightedGraph gMST = new UndirectedWeightedGraph();
		for(Vertex v : this.vertices) {
			v.key = Double.MAX_VALUE;
		}
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(new VertexComparator());
		for(Vertex v : this.vertices) {
			pq.add(v);
		}
		pq.peek().key = 0.0;
		pq.peek().pie = null;
		while(!pq.isEmpty()){
			Vertex ver = pq.poll();
			for (Map.Entry<Vertex, Double> set : ver.adj.entrySet()) {
			    if(pq.contains(set.getKey()) && set.getValue()<set.getKey().key) {
			    	set.getKey().pie = ver;
			    	set.getKey().key = set.getValue();
			    	pq.remove(set.getKey());
			    	pq.add(set.getKey());
			    }
			}
			gMST.addVertex(ver.vName);
		}
		for(Vertex v : this.vertices) {
			if(v.pie==null)
				continue; 
			gMST.addEdge(v.pie.vName,v.vName,v.key);
			
		}
		
		return gMST;
		}

	public UndirectedWeightedGraph AddEdgeToMSTandGetUpdatedMST(UndirectedWeightedGraph mst,String src,String dst,Double w) {
		
		if(!mst.addEdge(src, dst, w))		//Added edge was failed because SRC or DST doesn't exist in MST
			return null;
		
		UndirectedWeightedGraph detectedCycle = mst.DFScycle();
		Double maximumWeightInCycle = Double.MIN_VALUE;
		for(Vertex v : detectedCycle.vertices) {
			for (Map.Entry<Vertex, Double> set : v.adj.entrySet()) {
				if(maximumWeightInCycle<set.getValue())
					maximumWeightInCycle = set.getValue();
			}	
		}
		
		//MST WASN'T CHANGED
		if(maximumWeightInCycle==w) {
			System.out.println("Added edge didn't effect MST");
			mst.removeEdge(src, dst);
			return mst;
		}
		
		//MST CHANGED
		for(Vertex v : detectedCycle.vertices) {
			for (Map.Entry<Vertex, Double> set : v.adj.entrySet()) {
				if(maximumWeightInCycle==set.getValue()) {
					System.out.println("Added edge CHANGED previous MST");
					mst.removeEdge(v.vName, set.getKey().vName);
				return mst;
					
				}
			}	
		}
		return null;
		
	}

	public UndirectedWeightedGraph DFScycle() {
		UndirectedWeightedGraph Cycle = new UndirectedWeightedGraph();
		boolean Cycleflag = false;
		for(Vertex v : this.vertices) {
			v.vColor = 0; //White
			v.pie = null;
		}
		
		DFSUtil(this.vertices.get(0),Cycle,Cycleflag); //We assume the graph is connected, thus DFS from any vertex will reach all of the vertices
		return Cycle;
	}
	
	
	private void DFSUtil(Vertex v,UndirectedWeightedGraph g,boolean flag) {
		if (flag)
			return;
		v.vColor =1; // visited(Gray)
		for (Map.Entry<Vertex, Double> set : v.adj.entrySet()) {
			if(set.getKey().vColor ==0 && !flag) { //not visited
				set.getKey().pie = v;
				DFSUtil(set.getKey(),g,flag);
			}
			else if(set.getKey().vColor ==2 && !flag) // completely visited Vertex
				return;
			else if(set.getKey().vColor ==1 && v.pie!=set.getKey() &&!flag){	//Back-Edge in the Graph - Cycle has been detected
				//Back-tracking to return the cycle
				
				g.addVertex(set.getKey().vName);
				g.addVertex(v.vName);
				Double w = set.getValue(); 
				g.addEdge(set.getKey().vName, v.vName, w);
				
				Vertex temp = v;
				while(temp!=set.getKey()) {
					if(!g.m1.containsKey(temp.pie.vName))
						g.addVertex(temp.pie.vName);
					w= temp.pie.adj.get(temp);
					g.addEdge(temp.pie.vName,temp.vName,w);
					
					temp = temp.pie;
				}
				flag = true;
			}
		}
		v.vColor =2; //completely visited
		
	}
	
	public void printGraph() {
		for(Vertex v : vertices) {
			System.out.print(v+": ");
			System.out.println(v.adj);
			System.out.println();
		}
	}
	
	
	
}

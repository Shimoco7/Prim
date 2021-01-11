package Prim;

public class Main {

	public static void main(String[] args) {
		UndirectedWeightedGraph g = new UndirectedWeightedGraph();
		Character c = 'A';
		//Graph-Vertices run from A...V
		for(int i = 0;i<22;i++) {
			g.addVertex(c.toString());
			c++;
		}
		
		//Adding 50 Undirected-Weighted Edges
		g.addEdge("A", "D", 5.0); //A<->D
		g.addEdge("A", "C", 23.0); //A<->C
		g.addEdge("A", "B", 12.0); //A<->B
		g.addEdge("C", "D", 18.0); //C<->D
		g.addEdge("B", "F", 7.0); //B<->F
		g.addEdge("D", "G", 9.0); //D<->G
		g.addEdge("D", "F", 10.0); //D<->F
		g.addEdge("C", "E", 17.0); //C<->E
		g.addEdge("F", "L", 20.0); //F<->L
		g.addEdge("G", "H", 4.0); //G<->H
		g.addEdge("G", "J", 3.0); //G<->J
		g.addEdge("J", "E", 14.0); //J<->E
		g.addEdge("H", "L", 8.0); //H<->L
		g.addEdge("L", "K", 12.0); //L<->K
		g.addEdge("E", "I", 16.0); //E<->I
		g.addEdge("I", "K", 7.0); //I<->K
		g.addEdge("C", "M", 32.0); //C<->M
		g.addEdge("E", "M", 21.0); //E<->M
		g.addEdge("E", "N", 11.0); //E<->N
		g.addEdge("I", "O", 2.0); //I<->O
		g.addEdge("O", "K", 13.0); //O<->K
		g.addEdge("P", "K", 6.0); //P<->K
		g.addEdge("L", "P", 22.0); //L<->P
		g.addEdge("B", "P", 25.0); //B<->P
		g.addEdge("I", "Q", 19.0); //I<->Q
		g.addEdge("Q", "K", 15.0); //Q<->K
		g.addEdge("A", "R", 40.0); //A<->R
		g.addEdge("R", "C", 11.0); //R<->C
		g.addEdge("R", "M", 18.0); //R<->M
		g.addEdge("M", "N", 21.0); //M<->N
		g.addEdge("N", "O", 1.0); //N<->O
		g.addEdge("O", "S", 43.0); //O<->S
		g.addEdge("I", "S", 26.0); //I<->S
		g.addEdge("K", "S", 22.0); //K<->S
		g.addEdge("K", "T", 5.0); //K<->T
		g.addEdge("L", "T", 37.0); //L<->T
		g.addEdge("H", "P", 28.0); //H<->P
		g.addEdge("J", "L", 9.0); //J<->L
		g.addEdge("J", "Q", 20.0); //J<->Q
		g.addEdge("E", "G", 31.0); //E<->G
		g.addEdge("M", "V", 35.0); //M<->V
		g.addEdge("V", "U", 38.0); //V<->U
		g.addEdge("U", "N", 41.0); //U<->N
		g.addEdge("V", "O", 47.0); //V<->O
		g.addEdge("U", "O", 4.0); //U<->O
		g.addEdge("P", "T", 50.0); //P<->T
		g.addEdge("S", "T", 30.0); //S<->T
		g.addEdge("U", "S", 29.0); //U<->S
		g.addEdge("R", "V", 32.0); //R<->V
		g.addEdge("F", "H", 19.0); //F<->H
		
		
		System.out.println("The Original Graph:");
	
		g.printGraph();
		System.out.println();
		System.out.println();
		
		UndirectedWeightedGraph g2 = g.getMST();
		System.out.println("MST:");
		g2.printGraph();
		System.out.println();
		System.out.println();
		
		//Adding an edge that doesn't change MST
		System.out.println("Now we are adding the edge between the vertices B & L with the weight of 12.5: ");
		System.out.println("B: {L=12.5} && L: {B=12.5}");
		System.out.println("After Addition of B<->L: ");
		System.out.println();
		g2 = g2.AddEdgeToMSTandGetUpdatedMST(g2, "B", "L", 12.5);
		g2.printGraph();
		
		//Adding an edge that will CHANGE MST
		System.out.println("Now we are adding the edge between the vertices V & N with the weight of 30: ");
		System.out.println("V: {N=30.0} && N: {V=30.0}");
		System.out.println("After Addition of V<->N: ");
		System.out.println();
		g2 = g2.AddEdgeToMSTandGetUpdatedMST(g2, "V", "N", 30.0);
		g2.printGraph();
		

	}

}


public class Tools4A {

	public static void printArray(int[] tab) { 
	    for(int i=0;i<tab.length;i++) {
	    	System.out.print(" "+tab[i]);
	    }
	    System.out.println();
}
	// Méthode pour imprimer une matrice 
	public static void printMatrix(GraphM4A  matrix) {
		for (int i = 0; i < matrix.getN(); i++) {
			for (int j = 0; j < matrix.getN(); j++) {
				System.out.print(matrix.getAdjmat()[i][j] + " ");
			}
			System.out.println();
		}
}
	public static void printAdjList(GraphL4A graph) {
		for (int i = 0; i < graph.getN(); i++) {
			System.out.print("Sommet " + (i + 1) + ": ");
			
			// Handle unweighted graphs
			if (graph.getWeighted() == 0) {
				Node4A current = graph.getAdjlist()[i];
				while (current != null) {
					System.out.print((current.getVal() + 1) + " ");
					current = current.getNext();
				}
			} 
			
			// Handle weighted graphs
			else if (graph.getWeighted() == 1) {
				WeightedNode4A currentW = graph.getAdjlistW()[i];
				while (currentW != null) {
					System.out.print((currentW.getVal() + 1) + " (poids: " + currentW.getWeight() + ") ");
					currentW = currentW.getNext();
				}
			}
			
			System.out.println();
		}
	}



}

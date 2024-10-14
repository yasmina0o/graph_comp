import java.io.File;
import java.util.Scanner;


public class Main4A {

  public static void main(String args[]){

    try{
      File file= new File(args[0]);
      
    //If we choose the representation by adjacency matrix
      Scanner sc = new Scanner(file);      
      GraphM4A graphM = new GraphM4A(sc); 
      GraphM4A graphT = new GraphM4A(graphM.getN(), graphM.getType(), graphM.getWeighted());
      GraphL4A graphLT = new GraphL4A(graphM.getN(), graphM.getType(), graphM.getWeighted());
      // TP2 Exercice 1: DFS Traversal and Arc Types
      graphM.Tp2Exercice1(graphM);
        
      // TP2 Exercice 2: Detect Cycle
      graphM.Tp2Exercice2();
      
      // TP2 Exercice 3: Output Cycle Vertices
      graphM.Tp2Exercice3();



      

      if (graphM.getType()==0) { //undirected
    	  int[] degree = graphM.degree();
      System.out.println("(Matrix) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");  
      Tools4A.printArray(degree);
      }
      else { //directed
    	  TwoArrays4A pair=graphM.degrees();
    	  int[] indegree =pair.in(); //the result of graphM.degrees() is a pair of arrays, indegree and outdegree
          int[] outdegree =pair.out();
          System.out.println("(Matrix)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(indegree);
          System.out.println("(Matrix)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(outdegree);

        // TP1 exercice 1 

        // Conversion de la matrice en matrice d'adjacence
         System.out.println("TP1 exercice 1 pour la matrice d'adjacence:");
         graphM.Exercice1(graphM, graphT);  // Conversion
        // Affichage de la matrice d'adjacence après conversion
          System.out.println("la matrice d'adjacence transposé après conversion depuis la matrice :");
          Tools4A.printMatrix(graphT); 

        // Conversion de la matrice en List d'adjacence
          
          graphM.Exercice_1(graphM, graphLT);  // Conversion
        // Affichage de la matrice d'adjacence après conversion
          System.out.println("Liste d'adjacence transposé après conversion depuis la matrice :");
          Tools4A.printAdjList(graphLT); 
    	  }	  
        System.out.println("TP1 exercice 2:");
        int[] path1 = {1,3,1};
        System.out.println("Test de path1 (Matrice): " + graphM.Exercice2(path1));
        
      


   // If we choose the representation by adjacency lists
      sc = new Scanner(file);
      GraphL4A graphL = new GraphL4A(sc); 
      graphL.Tp2Exercice1(graphL);

      System.out.println("Test de path1 (Liste): " + graphL.Exercice2(path1));
      if (graphL.getType()==0&&graphL.getWeighted()==0) { //undirected and unweighted
    	  int[] degree = graphL.degree();
    	  System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");  
          Tools4A.printArray(degree);
      }
      if (graphL.getType()==0&&graphL.getWeighted()==1) { //undirected and weighted
    	  int[] degree = graphL.degreeW();
    	  System.out.println("(List) Degrees for vertices from 1 to " + degree.length + " for the given undirected graph");  
          Tools4A.printArray(degree);
      }
      if (graphL.getType()==1&&graphL.getWeighted()==0){ //directed and unweighted
    	  TwoArrays4A pair=graphL.degrees();
    	  int[] indegree = pair.in(); 
          int[] outdegree = pair.out();
          System.out.println("(List) Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(indegree);
          System.out.println("(List) Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(outdegree);
        // TP1 exercice 1 

          // Conversion de la liste en liste d'adjacence
          System.out.println("TP1 exercice 1 :");
          graphL.Exercice1(graphL, graphLT);  // Conversion
        // Affichage de la liste d'adjacence après conversion
          System.out.println("Liste d'adjacence après conversion depuis la Liste :");
          Tools4A.printAdjList(graphLT); 
          

        // Conversion de la liste en matrice d'adjacence
          
          graphL.Exercice_1(graphL, graphT);  // Conversion
        // Affichage de la matrice d'adjacence après conversion
          System.out.println("Matrice d'adjacence après conversion depuis la liste :");
          Tools4A.printMatrix(graphT);
    	  }	
    	   
      if (graphL.getType()==1&&graphL.getWeighted()==1){ //directed and unweighted
    	  TwoArrays4A pair=graphL.degreesW();
    	  int[] indegree = pair.in(); 
          int[] outdegree = pair.out();
          System.out.println("(List)Indegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(indegree);
          System.out.println("(List)Outdegrees for vertices from 1 to " + indegree.length + " for the given digraph");  
          Tools4A.printArray(outdegree);


          
          // TP1 exercice 1 

          // Conversion de la liste en liste d'adjacence
          graphL.Exercice1(graphL, graphLT);  // Conversion
        // Affichage de la liste d'adjacence après conversion
          System.out.println("Liste d'adjacence après conversion depuis la Liste :");
          Tools4A.printAdjList(graphLT); 

        // Conversion de la liste en matrice d'adjacence
          
          graphL.Exercice_1(graphL, graphT);  // Conversion
        // Affichage de la matrice d'adjacence après conversion
          System.out.println("Matrice d'adjacence après conversion depuis la liste :");
          Tools4A.printMatrix(graphT);

    	  }	
        
    	    
      sc.close();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}

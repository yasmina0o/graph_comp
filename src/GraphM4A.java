import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GraphM4A {

    private int n;
    private int type; // 0 if undirected, 1 if directed
    private int weighted; // 0 if unweighted, 1 otherwise
    private float[][] adjmat;
    private int[] d; // discovery times
    private int[] f; // finishing times
    private int nb = 0; // time counter
    private boolean cycleExist; 
    private Stack<Integer> path; 
    private List<Integer> cycle; 


    public GraphM4A( int n ,int type, int weighted ){
        this.n = n; 
        this.type = type;
        this.weighted = weighted;
        this.adjmat =  new float[n][n];
        this.cycleExist = false; 
        this.path = new Stack<>(); 
        this.cycle = new ArrayList<>(); 
    }


    public GraphM4A(Scanner sc) {
        String[] firstline = sc.nextLine().split(" ");
        this.n = Integer.parseInt(firstline[0]);
        this.cycleExist = false; 
        this.path = new Stack<>(); 
        this.cycle = new ArrayList<>(); 
        System.out.println("Number of vertices " + this.n);
        
        if (firstline[1].equals("undirected"))
            this.type = 0;
        else
            this.type = 1;
            
        System.out.println("Type= " + this.type);
        if (firstline[2].equals("unweighted"))
            this.weighted = 0;
        else
            this.weighted = 1;
        System.out.println("Weighted= " + this.weighted);

        this.adjmat = new float[this.n][this.n];
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                adjmat[i][j] = 0; // Initialize adjacency matrix

        for (int k = 0; k < this.n; k++) {
            String[] line = sc.nextLine().split(" : ");
            int i = Integer.parseInt(line[0]); // the vertex "source"
            if (weighted == 0) {
                if ((line.length > 1) && (line[1].charAt(0) != ' ')) {
                    String[] successors = line[1].split(", ");
                    System.out.println(i + " " + successors.length);
                    for (int h = 0; h < successors.length; h++) {
                        System.out.println(Integer.parseInt(successors[h]));
                        this.adjmat[i - 1][Integer.parseInt(successors[h]) - 1] = 1;
                    }
                }
            } else {
                line = line[1].split(" // ");
                if ((line.length == 2) && (line[1].charAt(0) != ' ')) {
                    String[] successors = line[0].split(", ");
                    String[] theirweights = line[1].split(", ");
                    for (int h = 0; h < successors.length; h++)
                        this.adjmat[i - 1][Integer.parseInt(successors[h]) - 1] = Float.parseFloat(theirweights[h]);
                }
            }
        }
    }

    // Method to be applied only when type=0
    public int[] degree() {
        int[] tmp = new int[this.n];
        for (int i = 0; i < this.n; i++)
            tmp[i] = 0;
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.adjmat[i][j] != 0)
                    tmp[i] = tmp[i] + 1;
        return tmp;
    }

    // Method to be applied only when type=1
    public TwoArrays4A degrees() {
        int[] tmp1 = new int[this.n]; // indegrees
        int[] tmp2 = new int[this.n]; // outdegrees
        for (int i = 0; i < this.n; i++) {
            tmp1[i] = 0;
            tmp2[i] = 0;
        }
        for (int i = 0; i < this.n; i++)
            for (int j = 0; j < this.n; j++)
                if (this.adjmat[i][j] != 0) {
                    tmp2[i] = tmp2[i] + 1; // outdegree
                    tmp1[j] = tmp1[j] + 1; // indegree
                }
        return (new TwoArrays4A(tmp1, tmp2));
    }
    public int getType() {
        return this.type;
    }
  public int getWeighted() {
        return this.weighted;
    }
    public int getN() {
        return this.n;
    }

   
    public float[][] getAdjmat() {
        return this.adjmat;
    }
    // TP1

    
   // Exercice 1
   // 1ere method adj matrix -> transposed adj matrix
public  GraphM4A Exercice1(GraphM4A graph , GraphM4A result) {
    for (int i = 0; i < graph.n; i++) {
        for (int j = 0; j < graph.n; j++) {
            result.adjmat[j][i] = graph.adjmat[i][j];
        }
    }  
    return result; 
}
// 2eme method adj matrix -> transposed graph represented by adjlist


public GraphL4A Exercice_1(GraphM4A graph, GraphL4A result) {
        int n = graph.n; 
        for (int i = 0; i < n; i++) {
            if (graph.weighted == 0) { 
                result.getAdjlist()[i] = null; 
            } else { 
                result.getAdjlistW()[i] = null; 
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph.adjmat[i][j] != 0) { 
                    if (graph.weighted == 1) { 
                        
                        WeightedNode4A weightedNode = new WeightedNode4A(j, result.getAdjlistW()[i], graph.adjmat[i][j]);
                        result.getAdjlistW()[i] = weightedNode; 
                    } else {
                        Node4A node = new Node4A(j, result.getAdjlist()[i]);
                        result.getAdjlist()[i] = node; 
                    }
                }
            }
        }
    
        return result; 
    }

    // Exercice 2

    public boolean Exercice2(int[] path) {
        for (int i = 0; i < path.length - 1; i++) {
            int v1 = path[i] - 1;    
            int v2 = path[i + 1] - 1; 

            
            if (adjmat[v1][v2] == 0) {
                System.out.println("Pas de chemin entre " + (v1 + 1) + " et " + (v2 + 1));
                return false; 
            }
        }
        return true; 
    }
    
    






    // TP2






    

       
    public void Tp2Exercice1(GraphM4A graph) {
        System.out.println("\n--- TP2 Exercice 1: DFS Traversal and Arc Types ---");
        
        int n = this.n;
        d = new int[n];
        f = new int[n];
        cycleExist = false;
        nb = 0;
        
        for (int i = 0; i < n; i++) {
            d[i] = 0;
            f[i] = 0;
        }
        for (int s = 0; s < n; s++) {
            dfsVisit(s, nb);
        }

        System.out.println("--- End of TP2 Exercice 1 ---\n");
    }

    private int dfsVisit(int s, int nb) {
        d[s] = ++nb;
        System.out.println("Visiting vertex " + (s + 1) + ": d[" + (s + 1) + "] = " + d[s]);

        for (int v = 0; v < this.n; v++) {
            if (this.adjmat[s][v] != 0) {
                String arcType = TypeArcs(s, v);
                System.out.println("Arc (" + (s + 1) + ", " + (v + 1) + "): " + arcType);

                if (arcType.equals("Back Arc") || arcType.equals("Back Edge")) {
                    cycleExist = true;
                }
                
                if (d[v] == 0) {
                    path.push(v);
                    System.out.println("Recursively visiting " + (v + 1) + " from " + (s + 1));
                    nb = dfsVisit(v, nb);
                } else if (path.contains(v)) {
                    cycleExist = true;
                    buildCycle(v);
                }
            }
        }

        f[s] = ++nb;
        System.out.println("Returning from " + (s + 1) + ": f[" + (s + 1) + "] = " + f[s]);

        if (!path.isEmpty()) {
            path.pop();
        }

        return nb;
    }

    private void buildCycle(int v) {
        cycle.add(v);
        while (!path.isEmpty()) {
            int node = path.pop();
            cycle.add(node);
            if (node == v) break;
        }
        Collections.reverse(cycle);
        printCycle();
    }

    private void printCycle() {
        System.out.print("Cycle detected: ");
        for (int vertex : cycle) {
            System.out.print((vertex + 1) + " ");
        }
        System.out.println();
    }

    public boolean hasCycle() {
        return cycleExist;
    }

    public void Tp2Exercice2() {
        System.out.println("\n--- TP2 Exercice 2: Cycle Detection ---");
        if (hasCycle()) {
            System.out.println("The graph contains a cycle.");
        } else {
            System.out.println("The graph does not contain any cycles.");
        }
        System.out.println("--- End of TP2 Exercice 2 ---\n");
    }

    public void Tp2Exercice3() {
        System.out.println("\n--- TP2 Exercice 3: Outputting Cycle Vertices ---");
        if (hasCycle()) {
            System.out.print("Cycle vertices: ");
            for (int vertex : cycle) {
                System.out.print((vertex + 1) + " ");
            }
            System.out.println();
        } else {
            System.out.println("No cycle detected in the graph.");
        }
        System.out.println("--- End of TP2 Exercice 3 ---\n");
    }

    public String TypeArcs(int s, int v) {
        if (this.type == 1) {
            if (d[s] < d[v] && d[v] < f[v] && f[v] < f[s]) {
                return "Tree or Forward Arc";
            } else if (d[v] < d[s] && d[s] < f[s] && f[s] < f[v]) {
                return "Back Arc";
            } else if (f[v] < d[s]) {
                return "Cross Arc";
            }
        } else if (this.type == 0) {
            if (d[v] == 0) {
                return "Tree Edge";
            } else if (d[v] < d[s]) {
                return "Back Edge";
            } else {
                return "Cross Edge";
            }
        }
        return "Unknown Arc Type";
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class GraphL4A {

	private int n;
	private int type; //0 if undirected, 1 if directed
	private int weighted; // 0 if unweighted, 1 otherwise
	private WeightedNode4A[] adjlistW; //one of adjlistW and adjlist is null, depending on the type of the graph
	private Node4A[] adjlist;
	private int[] d; // discovery times
    private int[] f; // finishing times
	private int nb = 0; // time counter
	private boolean cycleExist;
    private Stack<Integer> path;
    private List<Integer> cycle;


	public GraphL4A( int n ,int type, int weighted ){
        this.n = n; 
        this.type = type;
        this.weighted = weighted;
		if (this.weighted==0) {
	    	this.adjlist=new Node4A[this.n];
	    	for (int i=0;i<this.n;i++)
	    		adjlist[i]=null;
	        adjlistW=null;
	    }
	    else {
	    	this.adjlistW=new WeightedNode4A[this.n];
	    	for (int i=0;i<this.n;i++)
	    		adjlistW[i]=null;
	        adjlist=null;
	    }
		this.cycleExist = false; 
        this.path = new Stack<>(); 
        this.cycle = new ArrayList<>(); 
		
    }

	public GraphL4A(Scanner sc){
	    String[] firstline = sc.nextLine().split(" ");
	    this.n = Integer.parseInt(firstline[0]);
	    System.out.println(this.n);
		this.cycleExist = false; 
        this.path = new Stack<>(); 
        this.cycle = new ArrayList<>(); 
	    if (firstline[1].equals("undirected"))
	    	this.type = 0;
	    else
	    	this.type=1;
	    System.out.println("Type= "+this.type);
	    if (firstline[2].equals("unweighted"))
	    	this.weighted = 0;
	    else
	    	this.weighted=1;
	    System.out.println("Weighted= "+this.weighted);
	    if (this.weighted==0) {
	    	this.adjlist=new Node4A[this.n];
	    	for (int i=0;i<this.n;i++)
	    		adjlist[i]=null;
	        adjlistW=null;
	    }
	    else {
	    	this.adjlistW=new WeightedNode4A[this.n];
	    	for (int i=0;i<this.n;i++)
	    		adjlistW[i]=null;
	        adjlist=null;
	    }
	    	
	    
	  	for(int k=0;k<this.n;k++){
	  		String[] line = sc.nextLine().split(" : ");
	  		int i = Integer.parseInt(line[0]); //the vertex "source"
	  		if (weighted==0) {
	  			if ((line.length>1) && (line[1].charAt(0)!=' ')) {
	  				String[] successors= line[1].split(", "); 
	  				System.out.println(i+ " "+ successors.length);
	  				for (int h=0;h<successors.length;h++) {
	  					Node4A node=new Node4A(Integer.parseInt(successors[h])-1,null);
	  					node.setNext(adjlist[i-1]);
	  					adjlist[i-1]=node;
	  				}
	  			}
	  		}
	  		else {
	  			line = line[1].split(" // "); 
	  			if ((line.length==2)&&(line[1].charAt(0)!=' ')){// if there really are somme successors, then we must have something different from " " after "// "
	  				  	String[] successors= line[0].split(", ");
	  			  	    String[] theirweights= line[1].split(", ");
	  				  	for (int h=0;h<successors.length;h++) {
	  				  	  	WeightedNode4A nodeW = new WeightedNode4A(Integer.parseInt(successors[h])-1,null,Float.parseFloat(theirweights[h]));
	  				  	  	nodeW.setNext(adjlistW[i-1]);
	  				  	  	adjlistW[i-1]=nodeW;
	  				  	}
	  			
	  			}
	  		}
	  	}
	  	
	  }
	    
	   	  	
	
	//method to be applied only when type=0 and weighted=0
	public int[] degree(){
		int[] tmp=new int[this.n];
	    for(int i=0;i<this.n;i++) 
	    	tmp[i]=0;
	    	for(int i=0;i<this.n;i++) {
	    		Node4A p=adjlist[i];
	    		while (p!=null) {
	    			tmp[i]=tmp[i]+1;
	    			p=p.getNext();
	    		}
	    	}
	    	return(tmp);
	  }
	
	//method to be applied only when type=0 and weighted=1
		public int[] degreeW(){
			int[] tmp=new int[this.n];
		    for(int i=0;i<this.n;i++) 
		    	tmp[i]=0;
		    	for(int i=0;i<this.n;i++) {
		    		WeightedNode4A p=adjlistW[i];
		    		while (p!=null) {
		    			tmp[i]=tmp[i]+1;
		    			p=p.getNext();
		    		}
		    	}
		    	return(tmp);
		  }
	

	//method to be applied only when type=1 and weighted=0
	  public TwoArrays4A degrees(){
		  int[] tmp1=new int[this.n]; //indegrees
		  int[] tmp2=new int[this.n]; //outdegrees
		  for(int i=0;i<this.n;i++) { 
		    tmp1[i]=0;
		    tmp2[i]=0;
		  }
    	for(int i=0;i<this.n;i++) {
    		Node4A p=adjlist[i];
    		while (p!=null) {
    			tmp2[i]=tmp2[i]+1;
    			tmp1[p.getVal()]=tmp1[p.getVal()]+1;
    			p=p.getNext();
    		}
    	}	
	    return(new TwoArrays4A(tmp1,tmp2));        
	  }
		
	//method to be applied only when type=1 and weighted=1
	  public TwoArrays4A degreesW(){
		  int[] tmp1=new int[this.n]; //indegrees
		  int[] tmp2=new int[this.n]; //outdegrees
		  for(int i=0;i<this.n;i++) { 
		    tmp1[i]=0;
		    tmp2[i]=0;
		  }
    	for(int i=0;i<this.n;i++) {
    		WeightedNode4A p=adjlistW[i];
    		while (p!=null) {
    			tmp2[i]=tmp2[i]+1;
    			tmp1[p.getVal()]=tmp1[p.getVal()]+1;
    			p=p.getNext();
    		}
    	}	
	    return(new TwoArrays4A(tmp1,tmp2));        
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
	  public WeightedNode4A[] getAdjlistW() {
			return this.adjlistW;
		}
	  public Node4A[] getAdjlist() {
			return this.adjlist;
		}

	  public void setadjlist(Node4A[] adjlist) {
			this.adjlist = adjlist;
		}

	  public void setadjlistW(WeightedNode4A[] adjlistW) {
			this.adjlistW = adjlistW;
		}
		
		


// Tp1 Exercice 1
	 
	  public GraphL4A Exercice1(GraphL4A graph, GraphL4A result) {
			int n = graph.n;
		
			if (graph.weighted == 0) {
				Node4A[] adjList = graph.getAdjlist();
				Node4A[] transposedAdjList = result.getAdjlist(); 
		
				for (int i = 0; i < n; i++) {
					Node4A current = adjList[i];
					while (current != null) {
						Node4A node = new Node4A(i, transposedAdjList[current.getVal()]);
						transposedAdjList[current.getVal()] = node;
						current = current.getNext();
					}
				}
		
				result.setadjlist(transposedAdjList);  
		
			} else {
				WeightedNode4A[] adjListW = graph.getAdjlistW();
				WeightedNode4A[] transposedAdjListW = result.getAdjlistW();  
		
				for (int i = 0; i < n; i++) {
					WeightedNode4A current = adjListW[i];
					while (current != null) {
						
						WeightedNode4A node = new WeightedNode4A(i, transposedAdjListW[current.getVal()], current.getWeight());
						transposedAdjListW[current.getVal()] = node;
						current = current.getNext();
					}
				}
		
				result.setadjlistW(transposedAdjListW);  
			}
		
			return result;
		}
		
		
	  
	  public GraphM4A Exercice_1(GraphL4A graph , GraphM4A reslut) {
			int n = graph.n;
			
		
			if (graph.weighted == 0) {
				Node4A[] adjList = graph.adjlist; 
				for (int i = 0; i < n; i++) {
					Node4A current = adjList[i];
					while (current != null) {
						reslut.getAdjmat()[current.getVal()][i] = 1.0f; 
						current = current.getNext();
					}
				}
			} else {
				WeightedNode4A[] adjListW = graph.adjlistW; 
				for (int i = 0; i < n; i++) {
					WeightedNode4A current = adjListW[i];
					while (current != null) {
						reslut.getAdjmat()[current.getVal()][i] = current.getWeight(); 
						current = current.getNext();
					}
				}
			}
		
			return reslut; 
		}

	// TP1 exerice 2 
	public boolean Exercice2(int[] path) {
        if (this.weighted == 0) {
            return checkPathUnweighted(path);
        } else {
            return checkPathWeighted(path);
        }
    }
	 private boolean checkPathUnweighted(int[] path) {
        Node4A[] adjList = this.getAdjlist(); 

        for (int i = 0; i < path.length - 1; i++) {
            int v1 = path[i] - 1; 
            int v2 = path[i + 1] - 1;

            Node4A current = adjList[v1];
            boolean found = false;

            while (current != null) {
                if (current.getVal() == v2) {
                    found = true;
                    break;
                }
                current = current.getNext();
            }

            if (!found) {
                return false; 
            }
        }

        return true; 
    }

    private boolean checkPathWeighted(int[] path) {
        WeightedNode4A[] adjListW = this.getAdjlistW(); 

        for (int i = 0; i < path.length - 1; i++) {
            int v1 = path[i] - 1; 
            int v2 = path[i + 1] - 1;

            WeightedNode4A current = adjListW[v1];
            boolean found = false;

            
            while (current != null) {
                if (current.getVal() == v2) {
                    found = true;
                    break;
                }
                current = current.getNext();
            }

            if (!found) {
                return false; 
            }
        }

        return true;
    }

// Tp2
public void Tp2Exercice1(GraphL4A graph) {
        d = new int[graph.n];
        f = new int[graph.n];
        path = new Stack<>();
        cycle = new ArrayList<>();
        cycleExist = false;
        nb = 0;

        for (int i = 0; i < n; i++) {
            d[i] = 0;
            f[i] = 0;
        }

        for (int s = 0; s < n; s++) {
            if (d[s] == 0) {
                path.push(s);
                nb = dfsVisit(graph, s, nb);
            }
        }
    }

    private int dfsVisit(GraphL4A graph, int s, int nb) {
        d[s] = ++nb;
        System.out.println("Visite " + (s + 1) + ": d[" + (s + 1) + "] = " + d[s]);

        Node4A currentNode = graph.weighted == 0 ? adjlist[s] : null;
        WeightedNode4A currentWeightedNode = graph.weighted == 1 ? adjlistW[s] : null;

        while (currentNode != null || currentWeightedNode != null) {
            int v = graph.weighted == 0 ? currentNode.getVal() : currentWeightedNode.getVal();

            String arcType = TypeArcs(s, v);
            System.out.println("Arc (" + (s + 1) + ", " + (v + 1) + ") : " + arcType);

            if (arcType.equals("Back Arc") || arcType.equals("Back Edge")) {
                cycleExist = true;
            }

            if (d[v] == 0) {
                path.push(v);
                System.out.println("Visite " + (v + 1) + " depuis " + (s + 1));
                nb = dfsVisit(graph, v, nb);
            } else if (path.contains(v)) {
                cycleExist = true;
                cycle.clear();
                cycle.add(v);
                while (!path.isEmpty()) {
                    int node = path.pop();
                    cycle.add(node);
                    if (node == v) break;
                }
                Collections.reverse(cycle);
                printCycle();
            }

            if (graph.weighted == 0) {
                currentNode = currentNode.getNext();
            } else {
                currentWeightedNode = currentWeightedNode.getNext();
            }
        }

        f[s] = ++nb;
        System.out.println("Retour à " + (s + 1) + ": f[" + (s + 1) + "] = " + f[s]);

        if (!path.isEmpty()) {
            path.pop();
        }

        return nb;
    }

    private String TypeArcs(int s, int v) {
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

    private void printCycle() {
        System.out.print("Cycle détecté: ");
        for (int vertex : cycle) {
            System.out.print((vertex + 1) + " ");
        }
        System.out.println();
    }

    public boolean hasCycle() {
        return cycleExist;
    }
}
	




		
	

	 
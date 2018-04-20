package code;

import edu.princeton.cs.algs4.*;

/**
 * A06_WordNet
 * CS 2420
 * @author David Jones and Mason Parry
 */
public class SAP 
{
   private Digraph graph;
	
   // constructor takes a digraph (not necessarily a DAG)
   public SAP(Digraph G)
   {
	   if(G == null) throw new NullPointerException("Passed in null Diagraph");
	   
	   graph = new Digraph(G);
   }

   // is the digraph a directed acyclic graph?
   public boolean isDAG()
   {
	   DepthFirstOrder dfo = new DepthFirstOrder(graph.reverse());
	   int[] id = new int[graph.V()];
	   boolean[] marked = new boolean[graph.V()];
	   int count = 0;
	   
	   id = new int[graph.V()];
	   marked = new boolean[graph.V()];
	   
	   for(int v : dfo.reversePost())
	   {
		   if(!marked[v])
		   {
			   if(reachedCycle(v, id, marked, count))
			   {
				   return false;
			   }
			   count++;
		   }
	   }
	   
	   return true;
   }
   
   //recursive function that checks to see if we have reached a cycle in the digraph
   private boolean reachedCycle(int v, int[] id, boolean[] marked, int count)
   {
	   marked[v] = true;
	   id[v] = count;
	   for(int w: graph.adj(v))
	   {
		   if(!marked[w])
		   {
			   reachedCycle(w, id, marked, count);
		   }
		   
		   if(marked[w] && id[w] == count)
		   {
			   return true;
		   }
	   }
	   
	   return false;
   }

   // is the digraph a rooted DAG?
   public boolean isRootedDAG()
   {
	   if(!isDAG())
		   return false;
	   
	   DepthFirstOrder dfo = new DepthFirstOrder(graph);
	   int potentialRoot = dfo.post(0);
	   
	   //if the there is a root then there should be no outdegree
	   if(graph.outdegree(potentialRoot) == 0)
	   {
		   return true;
	   }
	   
	   return false;
   }

   // length of shortest ancestral path between v and w; -1 if no such path
   public int length(int v, int w)
   {
	   if(v < 0 || v > graph.V() - 1) throw new IndexOutOfBoundsException("'v' needs to be a value between 0 and " + (graph.V() - 1));
	   if(w < 0 || w > graph.V() - 1) throw new IndexOutOfBoundsException("'w' needs to be a value between 0 and " + (graph.V() - 1));
	   
	   /*BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(graph, v);
	   BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(graph, w);
	   int minDistance = Integer.MAX_VALUE;
	   
	   for(int i = 0; i < graph.V(); i++)
	   {
		   int dist = -1;
		   if(wPaths.hasPathTo(i) && vPaths.hasPathTo(i))
		   {
			   dist = wPaths.distTo(i) + vPaths.distTo(i);
		   }
		   
		   if(minDistance > dist && dist != -1)
		   {
			   minDistance = dist;
		   }
	   }
	   
	   return minDistance == Integer.MAX_VALUE ? -1 : minDistance;*/
	   
	   Queue<Integer> q = new Queue<Integer>();
       boolean[] vMarked = new boolean[graph.V()];
       boolean[] wMarked = new boolean[graph.V()];
       int[] vDist = new int[graph.V()];
       int[] wDist = new int[graph.V()];
       
       
       //go through the path for v
       q.enqueue(v);
       vMarked[v] = true;
       vDist[v] = 0;
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {
               if (!vMarked[i]) 
               {
            	   vMarked[i] = true;
            	   vDist[i] = vDist[value] + 1;
                   q.enqueue(i);
               }
           }
       }     
       
       //go through the path w
       q.enqueue(w);
       wMarked[w] = true;
       wDist[w] = 0;
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {     	   
               if (!wMarked[i]) 
               {
            	   wMarked[i] = true;
            	   wDist[i] = wDist[value] + 1;
                   q.enqueue(i);
               }
               
        	   //if we bump into a vertex that was marked 
        	   // by v then we found an intersection   
        	   if(vMarked[i])
        	   {
        		   //return the addition of both distances
        		   return vDist[i] + wDist[i];
        	   }
           }
       }
       
       return -1;
   }

   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
   public int ancestor(int v, int w)
   {
	   if(v < 0 || v > graph.V() - 1) throw new IndexOutOfBoundsException("'v' needs to be a value between 0 and " + (graph.V() - 1));
	   if(w < 0 || w > graph.V() - 1) throw new IndexOutOfBoundsException("'w' needs to be a value between 0 and " + (graph.V() - 1));
	   
	   /*BreadthFirstDirectedPaths vPaths = new BreadthFirstDirectedPaths(graph, v);
	   BreadthFirstDirectedPaths wPaths = new BreadthFirstDirectedPaths(graph, w);
	   int minDistance = Integer.MAX_VALUE;
	   int ancestor = -1;
	   
	   for(int i = 0; i < graph.V(); i++)
	   {
		   int dist = -1;
		   if(wPaths.hasPathTo(i) && vPaths.hasPathTo(i))
		   {
			   dist = wPaths.distTo(i) + vPaths.distTo(i);
		   }
		   
		   if(minDistance > dist && dist != -1)
		   {
			   minDistance = dist;
			   ancestor = i;
		   }
	   }
	   
	   return ancestor;*/
	   
       Queue<Integer> q = new Queue<Integer>();
       boolean[] vMarked = new boolean[graph.V()];
       boolean[] wMarked = new boolean[graph.V()];
       
       //go through the path for v
       q.enqueue(v);
       vMarked[v] = true;      
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {
               if (!vMarked[i]) 
               {
            	   vMarked[i] = true;
                   q.enqueue(i);
               }
           }
       }     
       
       //go through the path w
       q.enqueue(w);
       wMarked[w] = true;
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {
        	   //if we bump into a vertex that was marked 
        	   // by v then we found an intersection
        	   if(vMarked[i])
        	   {
        		   return i;
        	   }
        	   
               if (!wMarked[i]) 
               {
            	   wMarked[i] = true;
                   q.enqueue(i);
               }
           }
       }
       
       return -1;
   }

   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
   public int length(Iterable<Integer> v, Iterable<Integer> w)
   {
	   if(v == null || w == null) throw new NullPointerException("Values being passed in cannot be null");
       
       Queue<Integer> q = new Queue<Integer>();
       boolean[] vMarked = new boolean[graph.V()];
       boolean[] wMarked = new boolean[graph.V()];
       int[] vDist = new int[graph.V()];
       int[] wDist = new int[graph.V()];
       int distance = Integer.MAX_VALUE;
       
       //go through the path of all the v vertices
       for(int i : v)
       {
    	   q.enqueue(i);
    	   vMarked[i] = true;
    	   vDist[i] = 0;
       }     
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {
               if (!vMarked[i]) 
               {
            	   vMarked[i] = true;
            	   vDist[i] = vDist[value] + 1;
                   q.enqueue(i);
               }
           }
       }     
       
       //go through the path of all the w vertices
       for(int i : w)
       {
    	   q.enqueue(i);
    	   wMarked[i] = true;
    	   wDist[i] = 0;
       }  
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {
        	   //if we bump into a vertex that was marked 
        	   // by v then we found an intersection
        	   
               if (!wMarked[i]) 
               {
            	   wMarked[i] = true;
            	   wDist[i] = wDist[value] + 1;
                   q.enqueue(i);
               }
               
        	   if(vMarked[i])
        	   {		   
        		   if(vDist[i] + wDist[i] < distance)
        		   {
        			   distance = vDist[i] + wDist[i];
        		   }
        	   }
           }
       }
       
       if(distance == Integer.MAX_VALUE)
    	   distance = -1;
       
       return distance;
   }

   // a common ancestor that participates in shortest ancestral path; -1 if no such path
   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
   {
	   if(v == null || w == null) throw new NullPointerException("Values being passed in cannot be null");
       Queue<Integer> q = new Queue<Integer>();
       boolean[] vMarked = new boolean[graph.V()];
       boolean[] wMarked = new boolean[graph.V()];
       int[] vDist = new int[graph.V()];
       int[] wDist = new int[graph.V()];
       int closestAncestor = -1;
       int distance = Integer.MAX_VALUE;
       
       //go through the path of all the v vertices
       for(int i : v)
       {
    	   q.enqueue(i);
    	   vMarked[i] = true;
    	   vDist[i] = 0;
       }     
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {
               if (!vMarked[i]) 
               {
            	   vMarked[i] = true;
            	   vDist[i] = vDist[value] + 1;
                   q.enqueue(i);
               }
           }
       }     
       
       //go through the path of all the w vertices
       for(int i : w)
       {
    	   q.enqueue(i);
    	   wMarked[i] = true;
    	   wDist[i] = 0;
       }  
       while (!q.isEmpty()) 
       {
           int value = q.dequeue();
           for (int i : graph.adj(value)) 
           {
        	   //if we bump into a vertex that was marked 
        	   // by v then we found an intersection
        	   
               if (!wMarked[i]) 
               {
            	   wMarked[i] = true;
            	   wDist[i] = wDist[value] + 1;
                   q.enqueue(i);
               }
               
        	   if(vMarked[i])
        	   {		   
        		   if(vDist[i] + wDist[i] < distance)
        		   {
        			   distance = vDist[i] + wDist[i];
        			   closestAncestor = i;
        		   }
        	   }
           }
       }
       
       return closestAncestor;
   }

   // do unit testing of this class
   public static void main(String[] args)
   {
	    In in = new In("src/resources/digraph1.txt");
	    Digraph G = new Digraph(in);
	    SAP sap = new SAP(G);
	    
	    StdOut.println("is dag = " + sap.isDAG());
        StdOut.println("is rooted dag = " + sap.isRootedDAG());
        
        Stack<Integer> vs = new Stack<Integer>();
        vs.push(2);
        vs.push(10);
        
        Stack<Integer> ws = new Stack<Integer>();
        ws.push(4);
        ws.push(8);
        
        System.out.println("ancestor: " + sap.ancestor(vs, ws));
        System.out.println("ancestor length: " + sap.length(vs, ws));
        
	    while (!StdIn.isEmpty()) 
	    {
	        int v = StdIn.readInt();
	        int w = StdIn.readInt();
	        int length   = sap.length(v, w);
	        int ancestor = sap.ancestor(v, w);
	        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
	    }
   }
}

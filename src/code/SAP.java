package code;

import edu.princeton.cs.algs4.Digraph;

/**
 * A06_WordNet
 * CS 2420
 * @author David Jones and Mason Parry
 */
public class SAP 
{
	   // constructor takes a digraph (not necessarily a DAG)
	   public SAP(Digraph G)
	   {
		   
	   }

	   // is the digraph a directed acyclic graph?
	   public boolean isDAG()
	   {
		   return false;
	   }

	   // is the digraph a rooted DAG?
	   public boolean isRootedDAG()
	   {
		   return false;
	   }

	   // length of shortest ancestral path between v and w; -1 if no such path
	   public int length(int v, int w)
	   {
		   return 0;
	   }

	   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	   public int ancestor(int v, int w)
	   {
		   return 0;
	   }

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	   public int length(Iterable<Integer> v, Iterable<Integer> w)
	   {
		   return 0;
	   }

	   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	   {
		   return 0;   
	   }

	   // do unit testing of this class
	   public static void main(String[] args)
	   {
		   
	   }
}

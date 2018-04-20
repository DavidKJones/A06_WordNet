package code;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 * A06_WordNet
 * CS 2420
 * @author David Jones and Mason Parry
 */
public class WordNet 
{
	//fields
	private int size;
	private SAP sap;
	private Digraph graph;
	private ST<String, Integer> nounST;
	private ST<Integer, String> synsetsST;
	
	
	//constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms)
	{
		//assign varaibles
		if(synsets == null || hypernyms == null) throw new NullPointerException();
		nounST = new ST<String, Integer>();
		synsetsST = new ST<Integer, String>();
		size = 0;
		
		//Build symbol tables from sysnets
		In in = new In(synsets);
		while (in.hasNextLine()) {
            String[] a = in.readLine().split(",");
            String[] b = a[1].split(" ");
            for (String n : b) {
            	nounST.put(n, Integer.parseInt(a[0]));
            }
            synsetsST.put(Integer.parseInt(a[0]), a[1]);
			size++;
		}
		
		//build graph from hypernyms
		graph = new Digraph(size);
        in = new In(hypernyms);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(",");
            for (int i = 1; i < a.length; i++) {
                graph.addEdge(Integer.parseInt(a[0]), Integer.parseInt(a[i]));
            }
        }
		sap = new SAP(graph);
		
        //extra challenge
        if (!sap.isDAG()) {
            throw new IllegalArgumentException();
        }
        
	}
	
	//returns all the WordNet nouns
	public Iterable<String> nouns()
	{
		return nounST;
	}
	
	//is the word a WordNet noun?
	public boolean isNoun(String word)
	{
		if(word == null) throw new NullPointerException();
		return (nounST.contains(word));
	}
	
	//distance between nounA and nounB(defined below)
	public int distance(String nounA, String nounB)
	{
		if(nounA == null || nounB == null ) throw new NullPointerException();
		if(!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
		return sap.length(nounST.get(nounA), nounST.get(nounB));
	}
	
	//a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	//in a shortest ancestral path(defined below)
	public String sap(String nounA, String nounB)
	{
		if(nounA == null || nounB == null ) throw new NullPointerException();
		if(!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
		return synsetsST.get(sap.ancestor(nounST.get(nounA), nounST.get(nounB)));
	}
	
	//unit testing
	public static void main(String[] args)
	{
		WordNet wn = new WordNet("","");
		//WordNet wn = new WordNet(args[0], args[1]);
		//StdOut.println("Distnace between "+args[2]+" and "+args[3]+" is "+wn.distance(args[2], args[3]));
		//StdOut.println("The sysnset between "+args[2]+" and "+args[3]+" is: "+wn.sap(args[2], args[3]));
	}
}

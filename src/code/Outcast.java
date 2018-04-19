package code;

import edu.princeton.cs.algs4.*;

/**
 * A06_WordNet
 * CS 2420
 * @author David Jones and Mason Parry
 */
public class Outcast 
{
	private WordNet wordnet;
	
	// constructor takes a WordNet object
	public Outcast(WordNet wordnet)
	{
		this.wordnet = wordnet;
	}
	
	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns)
	{
		int largestDistance = 0;
		String outcast = null;
		
		for(String n : nouns)
		{
			for(String m : nouns)
			{
				int distance = wordnet.distance(n, m);
				if(largestDistance < distance)
				{
					largestDistance = distance;
					outcast = n;
				}
			}
		}
		
		return outcast;
	}
	
	// see test client below
	public static void main(String[] args)
	{
	    WordNet wordnet = new WordNet(args[0], args[1]);
	    Outcast outcast = new Outcast(wordnet);
	    for (int t = 2; t < args.length; t++) {
	        In in = new In(args[t]);
	        String[] nouns = in.readAllStrings();
	        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
	    }
	}
}

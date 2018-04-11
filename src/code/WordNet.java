package code;

/**
 * A06_WordNet
 * CS 2420
 * @author David Jones and Mason Parry
 */
public class WordNet 
{
	//constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms)
	{
		if(synsets == null || hypernyms == null) throw new NullPointerException();
	}
	
	//returns all the WordNet nouns
	public Iterable<String> nouns()
	{
		return null;
	}
	
	//is the word a WordNet noun?
	public boolean isNoun(String word)
	{
		if(word == null) throw new NullPointerException();
		return false;
	}
	
	//distance between nounA and nounB(defined below)
	public int distance(String nounA, String nounB)
	{
		if(nounA == null || nounB == null ) throw new NullPointerException();
		if(!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
		return 0;	
	}
	
	//a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	//in a shortest ancestral path(defined below)
	public String sap(String nounA, String nounB)
	{
		if(nounA == null || nounB == null ) throw new NullPointerException();
		if(!isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
		return "";
	}
	
	//unit testing
	public static void main(String[] args)
	{
		
	}
}

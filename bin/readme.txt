/******************************************************************************
 *  Name: David Jones & Mason Parry     
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name: Mason Parry & David Jones   
 *  Partner NetID:    
 *  Partner Precept:  
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 6: WordNet


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 *****************************************************************************/



/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 *****************************************************************************/



/******************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithms as a function of the number of vertices V and the
 *  number of edges E in the digraph?
 *****************************************************************************/

Description: It uses DepthFirstOrder class to sort the graph in post order. It then
grabs the first item on the newly sorted array and checks to see if that the selected vertex
has anything pointing to it. If it doesn't then we are at the root of the digraph, if it has
outdegrees then we determine that the graph isn't rooted.



Order of growth of running time: O(E+V)


/******************************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. For each method, what is the order of
 *  growth of the worst-case running time as a function of the number of
 *  vertices V and the number of edges E in the digraph? For each method,
 *  what is the order of growth of the best-case running time?
 *
 *  If you use hashing, you should assume the uniform hashing assumption
 *  so that put() and get() take constant time.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't
 *  forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 *****************************************************************************/

Description: In the ancestor method it keeps track of two boolean arrays and keep track
	of what vertices have been visited by what vertex we started with. First it loops through
	the adjacent vertices of vertex v and then marks areas much like DFS. Then it goes through
	the adjacent vertices of vertex w and then checks to see if the vertices have already been marked
	for the v vertex, if so then we found the shortest common ancestor.

                                              running time
method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)				   O(1) 		      O(E + V * 2)

ancestor(int v, int w)                 O(1)               O(E + V * 2)

length(Iterable<Integer> v,          O(E + V)            O((E + V) * 2N)
       Iterable<Integer> w)

ancestor(Iterable<Integer> v,        O(E + V)            O((E + V) * 2N)
         Iterable<Integer> w)




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/


/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/


/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/
	Mason implemented Wordnet and David implemented SAP. We both worked together on
	Outcast and helped each other whenever needed.



/**********************************************************************
 *  Have you completed the mid-semester survey? If you haven't yet,
 *  please complete the brief mid-course survey at https://goo.gl/gB3Gzw
 * 
 ***********************************************************************/


/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/

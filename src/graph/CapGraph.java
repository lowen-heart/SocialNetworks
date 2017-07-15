/**
 * 
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * @author Your name here.
 * 
 * For the warm up assignment, you must implement your Graph in a class
 * named CapGraph.  Here is the stub file.
 *
 */
public class CapGraph implements Graph {

	private int numVertices;
	private int numEdges;
	private Map<Integer,ArrayList<Integer>> adjListMap;
	private List<Graph> sccs;
	
	
	public CapGraph() {
		super();
		adjListMap = new HashMap<Integer,ArrayList<Integer>>();
		sccs =  new LinkedList<Graph>();
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(int num) {
		if(num < 0){
			throw new IllegalArgumentException("A number passed must be grater than 0");
		}
		
		ArrayList<Integer> neighbors = new ArrayList<Integer>();
		adjListMap.put(num, neighbors);
		
		numVertices++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(int from, int to) {
		if(from < 0 || to < 0){
			throw new IllegalArgumentException("A number passed must be greater than 0");
		}
		
		if(!adjListMap.containsKey(from)){
			throw new NullPointerException("Vertex must be created first");
		}
		
		ArrayList<Integer> neighbors = getNeighbors(from);
		neighbors.add(to);
		adjListMap.replace(from, neighbors);
		
		numEdges++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		Graph egonet = new CapGraph();
		ArrayList<Integer> neighbors = getNeighbors(center);
		egonet.addVertex(center);
		for(Integer i : neighbors){
			egonet.addEdge(center, i);
			ArrayList<Integer> vneighbors = getNeighbors(i);
			egonet.addVertex(i);
			for(Integer n : vneighbors){
				if(neighbors.contains(n)){
					egonet.addEdge(i, n);
				}
			}
		}
		return egonet;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		Stack<Integer> vertices = new Stack<Integer>();
		for(Integer item : getVertices()){
			vertices.push(item);
		}
		Stack<Integer> finishedDfs = Dfs(this,vertices);
		Graph transpose = buildSubGraph(getVertices(),true);
		finishedDfs = Dfs(transpose, finishedDfs);
		return sccs;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Integer, HashSet<Integer>> exportGraph() {
		HashMap<Integer,HashSet<Integer>> export = new HashMap<Integer,HashSet<Integer>>();
		Set<Integer> vertices = adjListMap.keySet();
		for(Integer i : vertices){
			export.put(i, new HashSet<Integer>(getNeighbors(i)));
		}
		return export;
	}
	
	/**
	 * Helps to print the informations about the graph
	 */
	public void printGraph(){
		
		System.out.println("Number of vertices: " + numVertices +
							" Number of edges: " + numEdges);
		
		Set<Integer> vertices = getVertices();
		
		for(Integer i : vertices){
			System.out.println("Vertex: " + i + " Edges: " + getNeighbors(i));
		}
	}

	/**
	 * 
	 * @param vertex
	 * @return return true if the vertex passed is a vertex of this graph. For tesing purpose.
	 */
	public boolean isVertex(int vertex){
		return adjListMap.containsKey(vertex);
	}
	
	/**
	 * 
	 * @param vertex
	 * 		vertex from which search for neighbors
	 * @return returns the list of neighbors of a vertex
	 */
	private ArrayList<Integer> getNeighbors(int vertex){
		ArrayList<Integer> neighbors;
		neighbors = new ArrayList<Integer>(adjListMap.get(vertex));
		return neighbors;
	}
	
	/**
	 * 
	 * @return
	 */
	public Stack<Integer> Dfs(){
		Stack<Integer> vertices = new Stack<Integer>();
		for(Integer item : getVertices()){
			vertices.push(item);
		}
		return Dfs(this, vertices);	
	}
	
	/**
	 * 
	 * @param graph
	 * @param vertices
	 * @return
	 */
	private Stack<Integer> Dfs(Graph graph, Stack<Integer> vertices){
		Set<Integer> visited = new HashSet<Integer>();
		Set<Integer> sccVertices = new HashSet<Integer>();
		Stack<Integer> finished = new Stack<Integer>();
		sccs.clear();
		Integer v;
		while(!vertices.isEmpty()){
			v = vertices.pop();
			if(!visited.contains(v)){
				sccVertices.clear();
				DfsVisit(graph,v,visited,finished,sccVertices);
				sccs.add(buildSubGraph(sccVertices, false));
			}
		}
		return finished;
	}
	
	/**
	 * 
	 * @param graph
	 * @param v
	 * @param visited
	 * @param finished
	 * @param sccVertices
	 */
	private void DfsVisit(Graph graph, Integer v, Set<Integer> visited, Stack<Integer> finished,Set<Integer> sccVertices) {
		visited.add(v);
		sccVertices.add(v);
		CapGraph g = (CapGraph) graph;
		for(Integer n : g.getNeighbors(v)){
			if(!visited.contains(n)){
				DfsVisit(g,n,visited,finished,sccVertices);
			}
		}
		finished.push(v);
	}
	
	/**
	 * Method that builds a subgraph starting from given vertices using the current graph. It can build a transpose graph
	 * setting the parameter transpose to true
	 * @param vertices
	 * @param transpose
	 * 			parameter to set to true that tells that if you want build a transpose graph
	 */
	private Graph buildSubGraph(Set<Integer> vertices, boolean transpose) {
		CapGraph g = new CapGraph();
		for(Integer from : vertices){
			if(!g.isVertex(from)){
				g.addVertex(from);
			}
			for(Integer to: getNeighbors(from)){
				if(vertices.contains(to)){
					if(!g.isVertex(to)){
						g.addVertex(to);
					}
					if(!transpose){
						g.addEdge(from, to);
					}else{
						g.addEdge(to, from);
					}
				}
			}
		}
		return g;
	}
	
	/**
	 * Report size of vertex set
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices() {
		return numVertices;
	}
	
	
	/**
	 * Report size of edge set
	 * @return The number of edges in the graph.
	 */	
	public int getNumEdges() {
		return numEdges;
	}
	
	/**
	 * Method to have the vertices of a graph
	 * @return return the set of vertices
	 */
	public Set<Integer> getVertices(){
		return adjListMap.keySet();
	}
}

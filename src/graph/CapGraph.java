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
	private Map<Vertex<Integer>,ArrayList<Vertex<Integer>>> adjListMap;
	private List<Graph> sccs;
	
	
	public CapGraph() {
		super();
		adjListMap = new HashMap<Vertex<Integer>,ArrayList<Vertex<Integer>>>();
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
		
		Vertex<Integer> vertex = new Vertex<Integer>(num);
		ArrayList<Vertex<Integer>> neighbors = new ArrayList<Vertex<Integer>>();
		adjListMap.put(vertex, neighbors);
		
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
		
		Vertex<Integer> vertexFrom = new Vertex<Integer>(from);
		Vertex<Integer> vertexTo = new Vertex<Integer>(to);
		
		if(!adjListMap.containsKey(vertexFrom)){
			throw new NullPointerException("Vertex must be created first");
		}
		
		ArrayList<Vertex<Integer>> neighbors = getNeighbors(from);
		neighbors.add(vertexTo);
		adjListMap.replace(vertexFrom, neighbors);
		
		numEdges++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		if(!this.isVertex(center)){
			throw new IllegalArgumentException("This is not a vertex of the graph");
		}
		Graph egonet = new CapGraph();
		ArrayList<Vertex<Integer>> neighbors = getNeighbors(center);
		egonet.addVertex(center);
		for(Vertex<Integer> i : neighbors){
			egonet.addEdge(center, i.getValue());
			ArrayList<Vertex<Integer>> vneighbors = getNeighbors(i.getValue());
			egonet.addVertex(i.getValue());
			for(Vertex<Integer> n : vneighbors){
				if(neighbors.contains(n)){
					egonet.addEdge(i.getValue(), n.getValue());
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
		Stack<Vertex<Integer>> vertices = new Stack<Vertex<Integer>>();
		vertices.addAll(getVertices());
		/*for(Vertex<Integer> item : getVertices()){
			vertices.push(item);
		}*/
		Stack<Vertex<Integer>> finishedDfs = Dfs(this,vertices);
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
		Set<Vertex<Integer>> vertices = adjListMap.keySet();
		for(Vertex<Integer> i : vertices){
			ArrayList<Vertex<Integer>> neighbors = getNeighbors(i.getValue());
			HashSet<Integer> n = new HashSet<Integer>();
			for(Vertex<Integer> v : neighbors){
				n.add(v.getValue());
			}
			export.put(i.getValue(), n);
		}
		return export;
	}
	
	/**
	 * Helps to print the informations about the graph
	 */
	public void printGraph(){
		
		System.out.println("Number of vertices: " + numVertices +
							" Number of edges: " + numEdges);
		
		Set<Vertex<Integer>> vertices = getVertices();
		
		for(Vertex<Integer> i : vertices){
			System.out.println("Vertex: " + i + " Edges: " + getNeighbors(i.getValue()));
		}
	}

	/**
	 * 
	 * @param vertex
	 * @return return true if the vertex passed is a vertex of this graph. For tesing purpose.
	 */
	public boolean isVertex(int vertex){
		Vertex<Integer> v = new Vertex<Integer>(vertex);
		return adjListMap.containsKey(v);
	}
	
	/**
	 * 
	 * @param vertex
	 * 		vertex from which search for neighbors
	 * @return returns the list of neighbors of a vertex
	 */
	private ArrayList<Vertex<Integer>> getNeighbors(int vertex){
		ArrayList<Vertex<Integer>> neighbors;
		Vertex<Integer> v = new Vertex<Integer>(vertex);
		
		neighbors = new ArrayList<Vertex<Integer>>(adjListMap.get(v));
		return neighbors;
	}
	
	/**
	 * 
	 * @return
	 */
	public Stack<Vertex<Integer>> Dfs(){
		Stack<Vertex<Integer>> vertices = new Stack<Vertex<Integer>>();
		for(Vertex<Integer> item : getVertices()){
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
	private Stack<Vertex<Integer>> Dfs(Graph graph, Stack<Vertex<Integer>> vertices){
		Set<Vertex<Integer>> visited = new HashSet<Vertex<Integer>>();
		Set<Vertex<Integer>> sccVertices = new HashSet<Vertex<Integer>>();
		Stack<Vertex<Integer>> finished = new Stack<Vertex<Integer>>();
		sccs.clear();
		Vertex<Integer> v;
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
	private void DfsVisit(Graph graph, Vertex<Integer> v, Set<Vertex<Integer>> visited, Stack<Vertex<Integer>> finished,Set<Vertex<Integer>> sccVertices) {
		visited.add(v);
		sccVertices.add(v);
		CapGraph g = (CapGraph) graph;
		for(Vertex<Integer> n : g.getNeighbors(v.getValue())){
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
	private Graph buildSubGraph(Set<Vertex<Integer>> vertices, boolean transpose) {
		CapGraph g = new CapGraph();
		for(Vertex<Integer> from : vertices){
			if(!g.isVertex(from.getValue())){
				g.addVertex(from.getValue());
			}
			for(Vertex<Integer> to: getNeighbors(from.getValue())){
				if(vertices.contains(to)){
					if(!g.isVertex(to.getValue())){
						g.addVertex(to.getValue());
					}
					if(!transpose){
						g.addEdge(from.getValue(), to.getValue());
					}else{
						g.addEdge(to.getValue(), from.getValue());
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
	public Set<Vertex<Integer>> getVertices(){
		return adjListMap.keySet();
	}
}

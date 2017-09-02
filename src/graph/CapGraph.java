/**
 * 
 */
package graph;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

import graph.entity.ReviewAirline;
import graph.entity.Reviewer;

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
	private Map<Vertex<Reviewer>,ArrayList<Edge<Reviewer>>> adjListMap;
	private List<Graph> sccs;
	private Vertex<Reviewer> best;
	private Vertex<Reviewer> worst;

	public CapGraph() {
		super();
		adjListMap = new HashMap<Vertex<Reviewer>,ArrayList<Edge<Reviewer>>>();
		sccs =  new LinkedList<Graph>();
		best = new Vertex<Reviewer>(new Reviewer(Integer.MAX_VALUE,"Best","Best", "Unknown", new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.ECONOMY.toString(), 0.0f,"",0.0f,0.0f,0.0f,0.0f,0.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f,"",0.0f,0.0f,0.0f,0.0f,0.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.BUSINESS.toString(), 0.0f,"",0.0f,0.0f,0.0f,0.0f,0.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f,"",0.0f,0.0f,0.0f,0.0f,0.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.EMPTY.toString(), 0.0f,"",0.0f,0.0f,0.0f,0.0f,0.0f,false)));
		worst = new Vertex<Reviewer>(new Reviewer(Integer.MAX_VALUE-1,"Worst","Worst","Unknown",new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.ECONOMY.toString(), 10.0f,"",5.0f,5.0f,5.0f,5.0f,5.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.PREMIUMECONOMY.toString(), 10.0f,"",5.0f,5.0f,5.0f,5.0f,5.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.BUSINESS.toString(), 10.0f,"",5.0f,5.0f,5.0f,5.0f,5.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.FIRSTCLASS.toString(), 10.0f,"",5.0f,5.0f,5.0f,5.0f,5.0f,false), new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(),"",ReviewAirline.Classes.EMPTY.toString(), 10.0f,"",5.0f,5.0f,5.0f,5.0f,5.0f,false)));
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(Reviewer r) {
		if(r == null){
			throw new NullPointerException("A reviewer passed is null");
		}
		
		Vertex<Reviewer> vertex = new Vertex<Reviewer>(r);
		ArrayList<Edge<Reviewer>> neighbors = new ArrayList<Edge<Reviewer>>();
		adjListMap.put(vertex, neighbors);
		
		numVertices++;
	}
	
	public Reviewer getVertexValue(Reviewer value){
		
		Set<Vertex<Reviewer>> set = adjListMap.keySet();
		Vertex<Reviewer> v = new Vertex<Reviewer>(value);
		
		if(set.contains(v)){
			for(Vertex<Reviewer> vr : set){
				Reviewer r = vr.getValue();
				if(r.equals(v.getValue())){
					return r;
				}		
			}
		}
	
		return null;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(Reviewer from, Reviewer to) {
		if(from == null || to == null){
			throw new IllegalArgumentException("A reviewer passed is null");
		}
		
		Vertex<Reviewer> vertexFrom = new Vertex<Reviewer>(from);
		
		if(!adjListMap.containsKey(vertexFrom)){
			throw new NullPointerException("Vertex must be created first");
		}
		
		Edge<Reviewer> newEdge = new Edge<Reviewer>(from,to);
		ArrayList<Edge<Reviewer>> edges = getEdges(from);
		edges.add(newEdge);
		adjListMap.replace(vertexFrom, edges);
		
		numEdges++;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {
		
		/*if(!this.isVertex(center)){
			throw new IllegalArgumentException("This is not a vertex of the graph");
		}*/
		Graph egonet = new CapGraph();
		/*ArrayList<Vertex<Integer>> neighbors = getNeighbors(center);
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
		}*/
		return egonet;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		Stack<Vertex<Reviewer>> vertices = new Stack<Vertex<Reviewer>>();
		vertices.addAll(getVertices());
		/*for(Vertex<Integer> item : getVertices()){
			vertices.push(item);
		}*/
		Stack<Vertex<Reviewer>> finishedDfs = Dfs(this,vertices);
		Graph transpose = buildSubGraph(getVertices(),true);
		finishedDfs = Dfs(transpose, finishedDfs);
		return sccs;
	}

	/* (non-Javadoc)
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Reviewer, HashSet<Reviewer>> exportGraph() {
		HashMap<Reviewer,HashSet<Reviewer>> export = new HashMap<Reviewer,HashSet<Reviewer>>();
		Set<Vertex<Reviewer>> vertices = adjListMap.keySet();
		for(Vertex<Reviewer> i : vertices){
			ArrayList<Vertex<Reviewer>> neighbors = (ArrayList<Vertex<Reviewer>>) getNeighbors(i.getValue());
			HashSet<Reviewer> n = new HashSet<Reviewer>();
			for(Vertex<Reviewer> v : neighbors){
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
		
		Set<Vertex<Reviewer>> vertices = getVertices();
		
		for(Vertex<Reviewer> i : vertices){
			System.out.println("Vertex: " + i + " Edges: " + getEdges(i.getValue()));
		}
	}

	/**
	 * 
	 * @param vertex
	 * @return return true if the vertex passed is a vertex of this graph. For tesing purpose.
	 */
	public boolean isVertex(Reviewer vertex){
		Vertex<Reviewer> v = new Vertex<Reviewer>(vertex);
		return adjListMap.containsKey(v);
	}
	
	/**
	 * 
	 * @param reviewer
	 * 		vertex from which search for neighbors
	 * @return returns the list of neighbors of a vertex
	 */
	private List<Vertex<Reviewer>> getNeighbors(Reviewer reviewer){
		ArrayList<Vertex<Reviewer>> neighbors = new ArrayList<Vertex<Reviewer>>();
		Vertex<Reviewer> v = new Vertex<Reviewer>(reviewer);
		
		for(Edge<Reviewer> edge : adjListMap.get(v)){
			neighbors.add(edge.getTo());
		}
		
		return neighbors;
	}
	
	/**
	 * 
	 * @param from
	 * 		vertex from which search for edges
	 * @return returns the list of edges of a vertex
	 */
	private ArrayList<Edge<Reviewer>> getEdges(Reviewer from){
		ArrayList<Edge<Reviewer>> edges;
		Vertex<Reviewer> v = new Vertex<Reviewer>(from);
		
		edges = new ArrayList<Edge<Reviewer>>(adjListMap.get(v));
		return edges;
	}
	
	/**
	 * Method to prepare to make a DFS on this graph
	 * 
	 * @return returns the vertices of a DFS in a stack structure
	 */
	public Stack<Vertex<Reviewer>> Dfs(){
		Stack<Vertex<Reviewer>> vertices = new Stack<Vertex<Reviewer>>();
		for(Vertex<Reviewer> item : getVertices()){
			vertices.push(item);
		}
		return Dfs(this, vertices);	
	}
	
	/**
	 * Helper method used to actually make DFS on a given graph
	 * 
	 * @param graph
	 * 			the graph where you need to make a DFS search
	 * @param vertices
	 * 			the stack of vertices used for the DFS search
	 * @return
	 * 			returns a stack of visited vertices during DFS
	 */
	private Stack<Vertex<Reviewer>> Dfs(Graph graph, Stack<Vertex<Reviewer>> vertices){
		Set<Vertex<Reviewer>> visited = new HashSet<Vertex<Reviewer>>();
		Set<Vertex<Reviewer>> sccVertices = new HashSet<Vertex<Reviewer>>();
		Stack<Vertex<Reviewer>> finished = new Stack<Vertex<Reviewer>>();
		sccs.clear();
		Vertex<Reviewer> v;
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
	 * Helper method that does DFS visit on a given graph and a vertex.
	 * @param graph
	 * 		graph on where do DFS
	 * @param v
	 * 		vertex where start to make DFS visit
	 * @param visited
	 * 		Set of visited vertices
	 * @param finished
	 * 		Stack of already visited vertices
	 * @param sccVertices
	 * 		Set of vertices used for SCCS
	 */
	private void DfsVisit(Graph graph, Vertex<Reviewer> v, Set<Vertex<Reviewer>> visited, Stack<Vertex<Reviewer>> finished,Set<Vertex<Reviewer>> sccVertices) {
		visited.add(v);
		sccVertices.add(v);
		CapGraph g = (CapGraph) graph;
		for(Vertex<Reviewer> n : g.getNeighbors(v.getValue())){
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
	private Graph buildSubGraph(Set<Vertex<Reviewer>> vertices, boolean transpose) {
		CapGraph g = new CapGraph();
		for(Vertex<Reviewer> from : vertices){
			if(!g.isVertex(from.getValue())){
				g.addVertex(from.getValue());
			}
			for(Edge<Reviewer> edge: getEdges(from.getValue())){
				if(vertices.contains(edge.getTo())){
					if(!g.isVertex(edge.getTo().getValue())){
						g.addVertex(edge.getTo().getValue());
					}
					if(!transpose){
						g.addEdge(edge.getFrom().getValue(), edge.getTo().getValue());
					}else{
						g.addEdge(edge.getTo().getValue(), edge.getFrom().getValue());
					}
				}
			}
		}
		return g;
	}
	
	public List<Reviewer> degreesOfSeparation(Reviewer start, Reviewer goal){
		
		//List<Reviewer> path = new ArrayList<Reviewer>();
		
		return bfs(start,goal);
	}
	
	/**
	 * Find the path from start to goal using breadth first search
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *         path from start to goal (including both start and goal).
	 */
	public List<Reviewer> bfs(Reviewer start, Reviewer goal) {
		// Dummy variable for calling the search algorithms
		Consumer<Reviewer> temp = (x) -> {
		};
		return bfs(start, goal, temp);
	}

	/**
	 * Find the path from start to goal using breadth first search
	 * 
	 * @param start
	 *            The starting location
	 * @param goal
	 *            The goal location
	 * @param nodeSearched
	 *            A hook for visualization. See assignment instructions for how
	 *            to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *         path from start to goal (including both start and goal).
	 */
	public List<Reviewer> bfs(Reviewer start, Reviewer goal,
			Consumer<Reviewer> nodeSearched) {


		// if start or goal is null just return a null value
		if (start == null || goal == null) {
			System.out.println("Start or goal node is null!  No path exists.");
			return null;
		}

		// create parent hashmap
		HashMap<Reviewer, Reviewer> parent = new HashMap<Reviewer, Reviewer>();

		// call bfsSearch sub-routine to do bfs search on the graph
		boolean found = bfsSearch(start, goal, parent);

		// if the path does not exists, return null
		if (!found) {
			System.out.println("No path exists");
			return null;
		}

		// reconstruct the path
		return buildPath(start, goal, parent);
	}
	
	/**
	 * Find if exist a path between a two given geographic points
	 * 
	 * @param start
	 *            The starting geographic point where start
	 * @param end
	 *            The ending target geographic point where arrive
	 * @param parent
	 *            The parent HashMap
	 *
	 * @return True if there is a path, false if there is no path between the
	 *         two geographic points
	 */
	private boolean bfsSearch(Reviewer start, Reviewer end,
			HashMap<Reviewer, Reviewer> parent) {

		// create a queue where put geographic points to visit
		Queue<Reviewer> queue = new LinkedList<Reviewer>();
		// hashset of geographic points visited
		HashSet<Reviewer> visited = new HashSet<Reviewer>();

		queue.add(start);
		visited.add(start);
		Reviewer curr;

		// while there is no more geographic points to analyze
		while (!queue.isEmpty()) {

			curr = queue.poll();

			if (curr.equals(end)) {
				return true;
			}

			// get the neighbors of the current geographic point
			List<Vertex<Reviewer>> neighbors = getNeighbors(curr);
			ListIterator<Vertex<Reviewer>> neighIter = neighbors.listIterator(neighbors.size());

			while (neighIter.hasPrevious()) {
				Reviewer next = neighIter.previous().getValue();
				if (!visited.contains(next)) {
					visited.add(next);
					parent.put(curr, next);
					queue.add(next);
				}
			}
		}

		return false;
	}

	/**
	 * Reconstruct the path between two geographic points
	 * 
	 * @param start
	 *            The starting geographic point where start
	 * @param end
	 *            The ending target geographic point where arrive
	 * @param parent
	 *            The parent HashMap
	 *
	 * @return return a list of geographic points that represents the path
	 *         between two intersections
	 */
	private List<Reviewer> buildPath(Reviewer start, Reviewer end,
			HashMap<Reviewer, Reviewer> parent) {

		LinkedList<Reviewer> path = new LinkedList<Reviewer>();
		Reviewer curr = start;
		Reviewer prev = null;
		// while we have not reached the start add the current node visited as
		// first node inside the path
		// doing so we are creating the path from the start to the end
		while (curr != null) {
			//path.addFirst(curr);
			path.add(curr);
			//add parent list to visitedPaths inside MapNode.
			//adjListMap.get(new Vertex<Reviewer>(curr)).addVisitedPath(end, new HashMap<Reviewer, Reviewer>(parent));
			prev = curr;
			curr = parent.get(curr);
			parent.remove(prev);
		}
		// add start to the begin
		//path.addFirst(start);
		path.add(end);
		System.out.println(path);
		return path;
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
	public Set<Vertex<Reviewer>> getVertices(){
		return adjListMap.keySet();
	}
	
	public Vertex<Reviewer> getBest() {
		return best;
	}

	public void setBest(Vertex<Reviewer> best) {
		this.best = best;
	}

	public Vertex<Reviewer> getWorst() {
		return worst;
	}

	public void setWorst(Vertex<Reviewer> worst) {
		this.worst = worst;
	}
}

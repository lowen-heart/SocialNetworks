/**
 * 
 */
package graph;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import graph.entity.ReviewAirline;
import graph.entity.Reviewer;

/**
 * @author LP
 * 
 *        CapGraph for Capstone Project of UCSD Course
 *
 */
public class CapGraph implements Graph {

	private int numVertices;
	private int numEdges;
	private Map<Vertex<Reviewer>, ArrayList<Edge<Reviewer>>> adjListMap;
	private List<Graph> sccs; // variable for SCCs
	private Vertex<Reviewer> best; // reviewer with best review
	private Vertex<Reviewer> worst; // reviewer with worst review

	// TOMITA'S ALGORITHM VARIABLES
	
	long nodes; // number of decisions
	long timeLimit; // milliseconds
	long cpuTime; // milliseconds
	int maxSize; // size of max clique
	LinkedList<Reviewer> solution;
	ArrayList<Vertex<Reviewer>>[] colorClass;

	/**
	 * Constructor of graph CapGraph
	 * 
	 * @throws IllegalAccessException
	 */
	public CapGraph() throws IllegalAccessException {
		super();
		adjListMap = new HashMap<Vertex<Reviewer>, ArrayList<Edge<Reviewer>>>();
		sccs = new LinkedList<Graph>();
		
		//setup best reviewer to have all 0s as lower limit
		best = new Vertex<Reviewer>(
				new Reviewer(Integer.MAX_VALUE, "Best", "Best", "Unknown", new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
								0.0f, 0.0f, 0.0f, 0.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f,
								"", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "",
								0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "",
								0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f,
								0.0f, 0.0f, 0.0f, 0.0f, false)));
		
		//setup worst reviewer to have all 5s as higher limit
		worst = new Vertex<Reviewer>(
				new Reviewer(Integer.MAX_VALUE - 1, "Worst", "Worst", "Unknown", new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 10.0f, "",
								5.0f, 5.0f, 5.0f, 5.0f, 5.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 10.0f,
								"", 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 10.0f, "",
								5.0f, 5.0f, 5.0f, 5.0f, 5.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 10.0f, "",
								5.0f, 5.0f, 5.0f, 5.0f, 5.0f, false),
						new ArrayList<ReviewAirline>(),
						new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 10.0f, "", 5.0f,
								5.0f, 5.0f, 5.0f, 5.0f, false)));

		// TOMITA'S VARIABLES SETUP
		this.nodes = maxSize = 0;
		this.cpuTime = timeLimit = -1;
		this.solution = new LinkedList<Reviewer>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addVertex(int)
	 */
	@Override
	public void addVertex(Reviewer r) {
		if (r == null) {
			throw new NullPointerException("A reviewer passed is null");
		}

		Vertex<Reviewer> vertex = new Vertex<Reviewer>(r);
		ArrayList<Edge<Reviewer>> neighbors = new ArrayList<Edge<Reviewer>>();
		adjListMap.put(vertex, neighbors);

		numVertices++;
	}

	/**
	 * Method that helps to get a specific value from the graph
	 * 
	 * @param value 
	 * 				The reviewer to check if it is inside the graph
	 * @return The reviewer object contained in the vertex. If it does not exist return null
	 */
	public Reviewer getVertexValue(Reviewer value) {

		//get all vertices
		Set<Vertex<Reviewer>> set = adjListMap.keySet();
		Vertex<Reviewer> v = new Vertex<Reviewer>(value);

		if (set.contains(v)) {
			for (Vertex<Reviewer> vr : set) {
				Reviewer r = vr.getValue();
				if (r.equals(v.getValue())) {
					return r;
				}
			}
		}

		
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#addEdge(int, int)
	 */
	@Override
	public void addEdge(Reviewer from, Reviewer to) {
		
		if (from == null || to == null) {
			throw new NullPointerException("A reviewer passed is null");
		}

		//create vertices from and to using arguments passed
		Vertex<Reviewer> vertexFrom = new Vertex<Reviewer>(from);
		Vertex<Reviewer> vertexTo = new Vertex<Reviewer>(to);

		if (!adjListMap.containsKey(vertexFrom) || !adjListMap.containsKey(vertexTo)) {
			throw new NullPointerException("Vertex must be created first");
		}

		//create edges for of ways
		Edge<Reviewer> newEdgeFromTo = new Edge<Reviewer>(from, to);
		Edge<Reviewer> newEdgeToFrom = new Edge<Reviewer>(to, from);
		//get the current edges for vertices passed as arguments
		ArrayList<Edge<Reviewer>> edgesFrom = getEdges(from);
		ArrayList<Edge<Reviewer>> edgesTo = getEdges(to);

		if (!edgesFrom.contains(newEdgeFromTo) & !edgesTo.contains(newEdgeToFrom)) {
			edgesFrom.add(newEdgeFromTo);
			edgesTo.add(newEdgeToFrom);

			//take the real vertices memorized, not their copies
			for(Vertex<Reviewer> v : getVertices()){
				if(v.equals(vertexFrom)){
					vertexFrom = v;
				}
				if(v.equals(vertexTo)){
					vertexTo = v;
				}
			}
			
			//add 1 to the degree of the vertices
			vertexFrom.setDegree(vertexFrom.getDegree() + 1);
			vertexTo.setDegree(vertexTo.getDegree() + 1);
			
			//calculate sum of neighbor vertices degree
			vertexFrom.setNebDeg(vertexFrom.getNeighborDeg() + vertexTo.getDegree());
			vertexTo.setNebDeg(vertexTo.getNeighborDeg() + vertexFrom.getDegree());

			adjListMap.replace(vertexFrom, edgesFrom);
			adjListMap.replace(vertexTo, edgesTo);

			numEdges++;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * Not working in this version of CapGraph
	 * 
	 * @see graph.Graph#getEgonet(int)
	 */
	@Override
	public Graph getEgonet(int center) {

		/*
		 * if(!this.isVertex(center)){ throw new
		 * IllegalArgumentException("This is not a vertex of the graph"); }
		 */
		Graph egonet = null;
		try {
			egonet = new CapGraph();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		/*
		 * ArrayList<Vertex<Integer>> neighbors = getNeighbors(center);
		 * egonet.addVertex(center); for(Vertex<Integer> i : neighbors){
		 * egonet.addEdge(center, i.getValue()); ArrayList<Vertex<Integer>>
		 * vneighbors = getNeighbors(i.getValue());
		 * egonet.addVertex(i.getValue()); for(Vertex<Integer> n : vneighbors){
		 * if(neighbors.contains(n)){ egonet.addEdge(i.getValue(),
		 * n.getValue()); } } }
		 */
		return egonet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Not working in this version on CapGraph
	 * 
	 * @see graph.Graph#getSCCs()
	 */
	@Override
	public List<Graph> getSCCs() {
		Stack<Vertex<Reviewer>> vertices = new Stack<Vertex<Reviewer>>();
		vertices.addAll(getVertices());
		/*
		 * for(Vertex<Integer> item : getVertices()){ vertices.push(item); }
		 */
		Stack<Vertex<Reviewer>> finishedDfs = Dfs(this, vertices);
		Graph transpose = buildSubGraph(getVertices(), true);
		finishedDfs = Dfs(transpose, finishedDfs);
		return sccs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.Graph#exportGraph()
	 */
	@Override
	public HashMap<Reviewer, HashSet<Reviewer>> exportGraph() {
		HashMap<Reviewer, HashSet<Reviewer>> export = new HashMap<Reviewer, HashSet<Reviewer>>();
		Set<Vertex<Reviewer>> vertices = adjListMap.keySet();
		for (Vertex<Reviewer> i : vertices) {
			ArrayList<Vertex<Reviewer>> neighbors = (ArrayList<Vertex<Reviewer>>) getNeighbors(i.getValue());
			HashSet<Reviewer> n = new HashSet<Reviewer>();
			for (Vertex<Reviewer> v : neighbors) {
				n.add(v.getValue());
			}
			export.put(i.getValue(), n);
		}
		return export;
	}

	/**
	 * Helps to print informations about the graph
	 */
	public void printGraph() {

		System.out.println("Number of vertices: " + numVertices + " Number of edges: " + numEdges);

		Set<Vertex<Reviewer>> vertices = getVertices();

		for (Vertex<Reviewer> i : vertices) {
			System.out.println("Vertex: " + i + " Edges: " + getEdges(i.getValue()));
		}
	}

	/**
	 * Helper method to check if a vertex is part of the graph
	 * 
	 * @param vertex
	 * @return return true if the vertex passed is a vertex of this graph. Mostly for
	 *          tesing purpose.
	 */
	public boolean isVertex(Reviewer vertex) {
		Vertex<Reviewer> v = new Vertex<Reviewer>(vertex);
		return adjListMap.containsKey(v);
	}

	/**
	 * Helper method to get neighbors vertices of a given node.
	 * 
	 * @param reviewer 
	 * 					Vertex from which search for neighbors
	 * @return returns the list of neighbors of a vertex
	 */
	private List<Vertex<Reviewer>> getNeighbors(Reviewer reviewer) {
		
		if(reviewer == null){
			throw new NullPointerException("Reviewer is null");
		}
		
		if(!isVertex(reviewer)){
			throw new NullPointerException("Vertex not in graph");
		}
		
		ArrayList<Vertex<Reviewer>> neighbors = new ArrayList<Vertex<Reviewer>>();
		Vertex<Reviewer> v = new Vertex<Reviewer>(reviewer);

		//Get edges of the given vertex and build neighbors array list getting "to" vertex from them
		for (Edge<Reviewer> edge : adjListMap.get(v)) {
			// System.out.println("Neighbor " + v.getValue().getId() + " : " +
			// edge.getTo());
			neighbors.add(edge.getTo());
		}

		return neighbors;
	}

	/**
	 * Helper method to get edges from a given vertex.
	 * 
	 * @param from 
	 * 				Vertex from which search for edges
	 * @return Returns the list of edges of a vertex
	 */
	private ArrayList<Edge<Reviewer>> getEdges(Reviewer from) {
		ArrayList<Edge<Reviewer>> edges;
		Vertex<Reviewer> v = new Vertex<Reviewer>(from);

		edges = new ArrayList<Edge<Reviewer>>(adjListMap.get(v));
		return edges;
	}

	/**
	 * Method to prepare to make a DFS on this graph
	 * 
	 * @return Returns the vertices of a DFS in a stack structure
	 */
	public Stack<Vertex<Reviewer>> Dfs() {
		Stack<Vertex<Reviewer>> vertices = new Stack<Vertex<Reviewer>>();
		for (Vertex<Reviewer> item : getVertices()) {
			vertices.push(item);
		}
		return Dfs(this, vertices);
	}

	/**
	 * Helper method used to actually make DFS on a given graph
	 * 
	 * @param graph
	 *            The graph where you need to make a DFS search
	 * @param vertices
	 *            The stack of vertices used for the DFS search
	 * @return Returns a stack of visited vertices during DFS
	 */
	private Stack<Vertex<Reviewer>> Dfs(Graph graph, Stack<Vertex<Reviewer>> vertices) {
		Set<Vertex<Reviewer>> visited = new HashSet<Vertex<Reviewer>>();
		Set<Vertex<Reviewer>> sccVertices = new HashSet<Vertex<Reviewer>>();
		Stack<Vertex<Reviewer>> finished = new Stack<Vertex<Reviewer>>();
		sccs.clear();
		Vertex<Reviewer> v;
		while (!vertices.isEmpty()) {
			v = vertices.pop();
			if (!visited.contains(v)) {
				sccVertices.clear();
				DfsVisit(graph, v, visited, finished, sccVertices);
				sccs.add(buildSubGraph(sccVertices, false));
			}
		}
		return finished;
	}

	/**
	 * Helper method that does DFS visit on a given graph and a vertex.
	 * 
	 * @param graph
	 *            graph on where do DFS
	 * @param v
	 *            vertex where start to make DFS visit
	 * @param visited
	 *            Set of visited vertices
	 * @param finished
	 *            Stack of already visited vertices
	 * @param sccVertices
	 *            Set of vertices used for SCCS
	 */
	private void DfsVisit(Graph graph, Vertex<Reviewer> v, Set<Vertex<Reviewer>> visited,
			Stack<Vertex<Reviewer>> finished, Set<Vertex<Reviewer>> sccVertices) {
		visited.add(v);
		sccVertices.add(v);
		CapGraph g = (CapGraph) graph;
		for (Vertex<Reviewer> n : g.getNeighbors(v.getValue())) {
			if (!visited.contains(n)) {
				DfsVisit(g, n, visited, finished, sccVertices);
			}
		}
		finished.push(v);
	}

	/**
	 * Method that builds a subgraph starting from given vertices using the
	 * current graph. It can build a transpose graph setting the parameter
	 * transpose to true
	 * 
	 * @param vertices
	 * @param transpose
	 *            parameter to set to true that tells that if you want build a
	 *            transpose graph
	 */
	private Graph buildSubGraph(Set<Vertex<Reviewer>> vertices, boolean transpose) {
		CapGraph g = null;
		try {
			g = new CapGraph();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		for (Vertex<Reviewer> from : vertices) {
			if (!g.isVertex(from.getValue())) {
				g.addVertex(from.getValue());
			}
			for (Edge<Reviewer> edge : getEdges(from.getValue())) {
				if (vertices.contains(edge.getTo())) {
					if (!g.isVertex(edge.getTo().getValue())) {
						g.addVertex(edge.getTo().getValue());
					}
					if (!transpose) {
						g.addEdge(edge.getFrom().getValue(), edge.getTo().getValue());
					} else {
						g.addEdge(edge.getTo().getValue(), edge.getFrom().getValue());
					}
				}
			}
		}
		return g;
	}

	/**
	 * Entry point for question 1 about degrees of separation. This method checks arguments passed 
	 * and calls bidirectional BFS helper method.
	 * 
	 * @param start 
	 * 				Starting point
	 * @param end 
	 * 				Ending point
	 * @return Returns a List of Reviewers after running helper method biBfs(). Returns null if there is no path.
	 */
	public List<Reviewer> degreesOfSeparation(Reviewer start, Reviewer end) {

		if (start == null || end == null) {
			throw new NullPointerException("Start and/or end node is null");
		}

		return biBfs(start, end);
	}

	/**
	 * Helper method that prepares data structures to execute bidirectional BFS on graph. 
	 *
	 * @param start 
	 * 				Starting point
	 * @param end 
	 * 				Ending point
	 * @return Returns a List of Reviewers after running helper method biBfsSearchVisit(). Returns null if there is no path.
	 */
	private List<Reviewer> biBfs(Reviewer start, Reviewer end) {

		//Initialize starting point data structures
		Queue<Reviewer> queueStart = new LinkedList<Reviewer>();
		HashSet<Reviewer> visitedStart = new HashSet<Reviewer>();
		HashMap<Reviewer, Reviewer> parentStart = new HashMap<Reviewer, Reviewer>();
		List<Reviewer> frontierFromStart = new LinkedList<Reviewer>();
		//Add start to queue and visited list
		queueStart.add(start);
		visitedStart.add(start);

		//Initialize ending point data structures
		Queue<Reviewer> queueEnd = new LinkedList<Reviewer>();
		HashSet<Reviewer> visitedEnd = new HashSet<Reviewer>();
		HashMap<Reviewer, Reviewer> parentEnd = new HashMap<Reviewer, Reviewer>();
		List<Reviewer> frontierFromEnd = new LinkedList<Reviewer>();
		//Add end to queue and visited list
		queueEnd.add(end);
		visitedEnd.add(end);

		//call bidirectional BFS and if returns a Reviewer, that is a collision reviewer where the two BFS encountered
		Reviewer collisionReviewer = biBfsSearchVisit(queueStart, visitedStart, parentStart, queueEnd, visitedEnd,
				parentEnd, frontierFromStart, frontierFromEnd);
		
		//if exist a collision reviewer build the path between the starting and ending point, if not there is no path
		if (collisionReviewer != null) {
			return printPath(start, end, parentStart, parentEnd, collisionReviewer);
		} else {
			System.out.println("NO PATH FOUND");
			return null;
		}

	}

	/**
	 * Helper method that executes bidirectional BFS on the given graph.
	 * 
	 * @param queueStart 
	 * 						Queue from starting vertex
	 * @param visitedStart 
	 * 						List of visited vertices from start
	 * @param parentStart 
	 * 						Parent map from start where key is next vertex (child) and value is its current vertex (parent)
	 * @param queueEnd 
	 * 						Queue from ending vertex
	 * @param visitedEnd 
	 * 						List of visited vertices from end
	 * @param parentEnd 
	 * 						Parent map from end where key is next vertex (child) and value is its current vertex (parent)
	 * @param frontierFromStart 
	 * 						List of neighbors of current visited node from start used to trace the current BFS frontier
	 * @param frontierFromEnd 
	 * 						List of neighbors of current visited node from end used to trace the current BFS frontier
	 * @return Returns the collision reviewer, null if there is no collision between the two bfs searches.
	 */
	private Reviewer biBfsSearchVisit(Queue<Reviewer> queueStart, HashSet<Reviewer> visitedStart,
			HashMap<Reviewer, Reviewer> parentStart, Queue<Reviewer> queueEnd, HashSet<Reviewer> visitedEnd,
			HashMap<Reviewer, Reviewer> parentEnd, List<Reviewer> frontierFromStart, List<Reviewer> frontierFromEnd) {

		while (!queueStart.isEmpty() && !queueEnd.isEmpty()) {
			
			// take the current vertex from queues (start and end)
			Reviewer currFromStart = queueStart.remove();
			Reviewer currFromEnd = queueEnd.remove();

			// check for collisions
			for (Reviewer pfs : frontierFromStart) {
				for (Reviewer pfe : frontierFromEnd) {
					// System.out.println("From Start: " + pfs);
					// System.out.println("From End: " + pfe);
					if (pfs.equals(pfe)) {
						//System.out.println("Matching Reviewer:" + pfs);
						return pfs;
					}
				}
			}

			// clear current frontiers
			frontierFromStart.clear();
			frontierFromEnd.clear();

			// FROM START BFS
			// take the neighbors of current starting queue vertex
			List<Vertex<Reviewer>> neighborsStart = getNeighbors(currFromStart);
			ListIterator<Vertex<Reviewer>> neighStartIter = neighborsStart.listIterator(neighborsStart.size());

			while (neighStartIter.hasPrevious()) {
				Reviewer nextStart = neighStartIter.previous().getValue();
				// System.out.println("CurrFromStart: " + currFromStart);
				
				// adding neighbor to the frontier list
				frontierFromStart.add(nextStart);
				// System.out.println("Next Start:" + nextStart);
				
				// if there a neighbor was not already visited add it to visited, add it to parent map and to the bottom of the queue.
				if (!visitedStart.contains(nextStart)) {
					visitedStart.add(nextStart);
					parentStart.put(nextStart, currFromStart);
					//System.out.println("Next: " + nextStart.getId() + " Curr: " + currFromStart.getId());
					queueStart.add(nextStart);
				}
			}

			// FROM END BFS
			// take the neighbors of current ending queue vertex
			List<Vertex<Reviewer>> neighborsEnd = getNeighbors(currFromEnd);
			ListIterator<Vertex<Reviewer>> neighEndIter = neighborsEnd.listIterator(neighborsEnd.size());

			while (neighEndIter.hasPrevious()) {
				Reviewer nextEnd = neighEndIter.previous().getValue();
				// System.out.println("CurrFromStart: " + currFromStart);
				
				// adding neighbor to the frontier list
				frontierFromEnd.add(nextEnd);
				// System.out.println("Next End:" + nextEnd);
				
				// if there a neighbor was not already visited add it to visited, add it to parent map and to the bottom of the queue.
				if (!visitedEnd.contains(nextEnd)) {
					visitedEnd.add(nextEnd);
					parentEnd.put(nextEnd, currFromEnd);
					//System.out.println("Next End: " + nextEnd.getId() + " Curr: " + currFromEnd.getId());
					queueEnd.add(nextEnd);
				}
			}

		}

		return null;
	}
	
	
	/**
	 * Helper method that builds the path between the starting and ending point linking the two paths 
	 * from start to collision reviewer and from collision reviewer to end
	 * 
	 * @param start 
	 * 				Starting point
	 * @param end 
	 * 				Ending point
	 * @param parentStart 
	 * 				Parent map from start where key is next vertex (child) and value is its current vertex (parent)
	 * @param parentEnd 
	 * 				Parent map from end where key is next vertex (child) and value is its current vertex (parent)
	 * @param collisionReviewer 
	 * 				The collision reviewer
	 * @return List of reviewers in order from end to start
	 */
	private List<Reviewer> printPath(Reviewer start, Reviewer end, HashMap<Reviewer, Reviewer> parentStart,
			HashMap<Reviewer, Reviewer> parentEnd, Reviewer collisionReviewer) {
		
		LinkedList<Reviewer> path = new LinkedList<Reviewer>();

		// starting from the collision reviewer and going down to end
		Reviewer curr = collisionReviewer;
		// Reviewer prev = null;
		
		// while we have not reached the end add the current node visited as
		// first node inside the path
		while (!curr.equals(end)) {
			path.addFirst(curr);
			// prev = curr;
			curr = parentEnd.get(curr);
			// parentStart.remove(prev);
		}

		// add end as first vertex
		path.addFirst(end);

		// reset current node to collision reviewer and now going up to start
		curr = parentStart.get(collisionReviewer);

		// while we have not reached the start add the current node visited as
		// last node inside the path
		while (!curr.equals(start)) {
			path.addLast(curr);
			// prev = curr;
			curr = parentStart.get(curr);
			// parentEnd.remove(prev);
		}

		// add start as last vertex
		path.addLast(start);

		System.out.println("DONE");

		return path;
	}

	/**
	 * Report size of vertex set
	 * 
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices() {
		return numVertices;
	}

	/**
	 * Report size of edge set
	 * 
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges() {
		return numEdges;
	}

	/**
	 * Method to have the vertices of a graph
	 * 
	 * @return return the set of vertices
	 */
	public Set<Vertex<Reviewer>> getVertices() {
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

	//TOMITAS'S ALGORITHM MAX CLIQUE

	/**
	 * Tomita's algorithm to find cliques. Inside this algorithm it is also used a greedy sequential colouring algorithm (Welsh and Powell) 
	 * Time complexity is driven by expand method O(n^2) that is the upper bound for Tomita's algorithm.
	 * 
	 * To gain the value of time complexity: O(3^n/3) it is needed a mathematical demonstration that in this case is out of scope.
	 * 
	 * @return Returns a list of reviewers that forms the maximum clique
	 */
	public LinkedList<Reviewer> search() {

		// setting the system for not going over a set time limit 
		cpuTime = System.currentTimeMillis();
		timeLimit = 1000*(long) 100000;
		// set up
		nodes = 0;
		colorClass = new ArrayList[numVertices];

		//growing clique of vertices
		ArrayList<Vertex<Reviewer>> growingClique = new ArrayList<Vertex<Reviewer>>();
		//candidate set of vertices
		ArrayList<Vertex<Reviewer>> candidateSet = new ArrayList<Vertex<Reviewer>>();
		
		//initialize colorClass array to contain an array list of vertices. 
		//Each index represent a color so every vertex inside that array list is a vertex of that color
		//Time complexity: O(numVertices), number of vertices in the graph [O(n)]
		for (int i = 0; i < numVertices; i++) {
			colorClass[i] = new ArrayList<Vertex<Reviewer>>();
		}

		//order the candidate set
		//Time complexity: O(numVerticeslog(numVertices))) [O(nlogn)]
		orderVertices(candidateSet);
		
		//call helper method to find maximum clique
		//Time complexity: O(numVerticesInTheCandidateSet^2)
		expand(growingClique, candidateSet);
		
		return solution;
		
	}

	/**
	 * Helper method that sort in a non-increasing order the candidate set using Tomita's Comparator.
	 * Vertices are ordered by vertex degree number and neighbor vertex degree sum. 
	 * 
	 * Time complexity: O(numVertices(log(numVertices))) [O(nlogn)]
	 * 
	 * @param colorOrd 
	 * 					Candidate set to be ordered
	 */
	private void orderVertices(ArrayList<Vertex<Reviewer>> colorOrd) {

		ArrayList<Vertex<Reviewer>> v = new ArrayList<Vertex<Reviewer>>();

		// add all the vertices to v
		// Time complexity: O(numVertices) [O(n)]
		v.addAll(getVertices());

		//sort v using the comparator defined
		// Time complexity: O(numVertices(log(numVertices))) [O(n logn)]
		v.sort(new TomitaComparator<Vertex<Reviewer>>());
		
		// add ordered vertices to ColOrd that is candidateSet ordered by colors
		// Time complexity: O(numVertices) [O(n)]
		colorOrd.addAll(v);

	}

	/**
	 * Helper method used to find the maximum clique. It try to expand a clique that is already computed to add one vertex more.
	 * 
	 * Time complexity: O(numVerticesInCandidateSet^2) + O(numVerticesInCandidateSet)*(O(i)*O(numEdgesOfVertex)+O(numVerticesInGrowingClique))
	 * The second term is less than the numVerticesInCandidateSet^2 so asymptotically that means that the time complexity is: O(numVerticesInCandidateSet^2)
	 * In the first iteration this is O(numVertices^2)
	 * O(n^2) is the upper bound of Tomita's Algorithm Cliques as stated here: 
	 * http://ac.els-cdn.com/S0304397506003586/1-s2.0-S0304397506003586-main.pdf?_tid=15477c4c-9c50-11e7-8ad5-00000aab0f02&acdnat=1505725522_4f7eac9abb3e46a792c4055ee12bb612
	 * 
	 * To gain the value: O(3^n/3) it is needed a mathematical demonstration that in this case is out of scope.
	 * 
	 * @param growingClique 
	 * 						Growing clique of vertices
	 * @param candidateSet 
	 * 						Candidate set of vertices
	 */
	private void expand(ArrayList<Vertex<Reviewer>> growingClique, ArrayList<Vertex<Reviewer>> candidateSet) {
		
		//if the algorithm is taking too much time stop it
		if (timeLimit > 0 && (System.currentTimeMillis() - cpuTime) >= timeLimit) {
			return;
		}

		nodes++;

		// array of color. Maximum number of colors is the size of candidateSet, all the vertices fits one color.
		// holds the color of the ith vertex in candidateSet.
		int[] color = new int[candidateSet.size()];

		// helper method that orders the candidate set by color classes, candidateSet is sorted in non-decreasing color order
		// Time complexity: O(numVerticesInCandidateSet^2) [O(n^2)] --> worst case first iteration O(numVertices^2)
		colorSort(candidateSet, candidateSet, color);

		// Time complexity external for: O(numVerticesInCandidateSet) [O(n)] --> worst case O(numVertices)
		// Time complexity: O(numVerticesInCandidateSet)*(O(i)*O(numEdgesOfVertex)+O(numVerticesInGrowingClique))
		for (int i = candidateSet.size() - 1; i >= 0; i--) {
			
			//System.out.println("Expand: " + growingClique + " , " + candidateSet);
			//check if the size of the current max clique + the color of the vertex
			if (growingClique.size() + color[i] <= maxSize) {
				//System.out.println("FAIL: " + growingClique + " color " + color[i]);
				return;
			}
			
			Vertex<Reviewer> v = candidateSet.get(i);
			growingClique.add(v);
			
			//create a new candidate set newCandidateSet, set of vertices in candidateSet that are adjacent to v
			ArrayList<Vertex<Reviewer>> newCandidateSet = new ArrayList<Vertex<Reviewer>>(i);
			
			// Time complexity for: O(i), i decreasing from candidate set size to 0
			// Total time complexity: O(i)*O(numEdgesOfVertex) --> worst case O(i)*O(numVertices) --> O(numVertices^2) in the first run
			for (int j = 0; j <= i; j++) {
				Vertex<Reviewer> u = candidateSet.get(j);
				
				//Time complexity: O(numEdgesOfVertex) --> worst case connected with each vertex O(numVertices)
				if (adjListMap.get(u).contains(new Edge<Reviewer>(u.getValue(),v.getValue()))) {
					newCandidateSet.add(u);
				}
			}
			
			// Time complexity: O(numVerticesInGrowingClique) [O(n)]
			if (newCandidateSet.isEmpty() && growingClique.size() > maxSize){
				//System.out.println("Select: " + growingClique + " , " + candidateSet);
				saveSolution(growingClique);
				//System.out.println("SAVE :" + growingClique);
			}
			if (!newCandidateSet.isEmpty()){
				//System.out.println("Select: " + growingClique + " , " + newCandidateSet);
				// Time complexity less than the overall because the newCandidateSet has less vertices at each iteration
				expand(growingClique, newCandidateSet);
			}
			
			//System.out.println("Reject " + i);
			growingClique.remove(growingClique.size() - 1);
			candidateSet.remove(i);
		}
	}

	/**
	 * This helper method help to put vertices into color classes.
	 * The candidate set candidateSet is sorted in non-decreasing color order.
	 * Complexity is quadratic in the size of candidateSet.
	 * Time complexity: O(numVertices^2), in general O(numVerticesInCandidateSet) [O(n^2)]
	 * 
	 * @param colorOrd 
	 * 					Vertices to be colored 
	 * @param candidateSet 
	 * 					Colored vertices in non-decreasing color order
	 * @param color 
	 * 					Array of colors
	 */
	private void colorSort(ArrayList<Vertex<Reviewer>> colorOrd, ArrayList<Vertex<Reviewer>> candidateSet, int[] color) {
		
		//variable to record the number of colors used.
		int colors = 0;
		
		//reset the colorClass array of ArrayList that might be use in a previous step
		// Time complexity: O(numVerticesinCandidateSet) [O(n)] --> worst case all vertices --> O(numVertices) [O(n)]
		for (int i = 0; i < colorOrd.size(); i++){
			colorClass[i].clear();
		}
		
		// Time complexity for: O(numVerticesinCandidateSet) [O(n)] --> worst case all vertices --> O(numVertices) [O(n)]
		// Total time complexity: O(numVertices^2) [O(n^2)]
		for(Vertex<Reviewer> v : colorOrd){
			int k = 0;
			
			//place vertex v from ColOrd in the first class of colors where there are no conflicts.
			//A class in which the vertex is not adjacent to any other vertex in that class
			// Time complexity conflicts: O(numVerticesinColorClass) [O(n)] --> worst case 1 vertex if all vertices create a color --> O(1)
			// Time complexity while: O(numConflicts) --> worst case all vertices --> O(numVertices) [O(n)]
			// Total time complexity: O(numVertices) [O(n)]
			while (conflicts(v, colorClass[k])){
				k++;
			}
			
			//add vertex v to that color class.
			//System.out.println("Add " + v.getValue().getId() + " to colorClass " + k);
			colorClass[k].add(v);
			
			//take the max between colors and k+1
			colors = Math.max(colors, k + 1);
		}
		
		// clear candidateSet, the candidate set
		candidateSet.clear();
		int i = 0;
		//put vertices in candidateSet ordered by color class
		// Time complexity internal for: O(numVerticesInColorClass) [O(n)] --> worst case 1 vertex if all vertices create a color --> O(1)
		// Time complexity external for: O(numColors) [O(n)] --> worst case all vertices O(numVertices) [O(n)]
		// Total time complexity: O(numVertices) [O(n)]
		for (int k = 0; k < colors; k++) {
			for(Vertex<Reviewer> v :  colorClass[k]){
				//System.out.println("ColorClass: " + k + " Size: " + colorClass[k].size());
				candidateSet.add(v);
				color[i++] = k + 1;
			}
		}
	}

	/**
	 * Check if a given vertex is linked with a vertex of a given color class
	 * 
	 * Time complexity: O(numVerticesinColorClass) [O(n)]
	 * 
	 * @param v 
	 * 			Vertex to check against a color class
	 * @param colorClass 
	 * 			Array list of vertices of a given color class
	 * @return Returns true if there is a conflict
	 */
	private boolean conflicts(Vertex<Reviewer> v, ArrayList<Vertex<Reviewer>> colorClass) {
		
		for(Vertex<Reviewer> w : colorClass){		
			if(adjListMap.get(v).contains(new Edge<Reviewer>(v.getValue(),w.getValue()))){
				//System.out.println("Conflict: " + v.getValue().getId() + " - " + w.getValue().getId());
				return true;
			}
		}
		return false;
	}

	/**
	 * Helper method to save the current maximum clique found.
	 * 
	 * Time complexity: O(numVerticesInGrowingClique) [O(n)]
	 * 
	 * @param maxClique 
	 * 					Array list of vertices that compose the current maximum clique
	 */
	private void saveSolution(ArrayList<Vertex<Reviewer>> maxClique) {

		for(Vertex<Reviewer> v : maxClique){
			solution.add(v.getValue());
		}

		maxSize = maxClique.size();
		
	}

	/**
	 * Method that returns the max size of the clique found.
	 * 
	 * @return Max size of the clique found
	 */
	public int getMaxSize() {
		return maxSize;
	}

}

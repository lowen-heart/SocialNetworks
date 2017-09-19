package graph;

public class Edge <E extends Comparable<E>>{
	
	private Vertex<E> from;
	private Vertex<E> to;
	private double weight;
	
	
	/**
	 * Constructor of Edge object
	 * 
	 * @param from
	 * 				Vertex from
	 * @param to
	 * 				Vertex to
	 */
	public Edge(E from, E to) {
	
		this.from = new Vertex<E>(from);
		this.to = new Vertex<E>(to);
		weight = 0.0;
		
	}
	
	/**
	 * Constructor of Edge object
	 * 
	 * @param from
	 * 				Vertex from
	 * @param to
	 * 				Vertex to
	 * @param weight
	 * 				Weight of the edge
	 */
	public Edge(E from, E to, double weight){
		this(from,to);
		this.weight = weight;
	}

	/**
	 * Getter method of from instance variable
	 * 
	 * @return Vertex
	 */
	public Vertex<E> getFrom() {
		return from;
	}

	/**
	 * Setter method of from instance variable
	 * 
	 * @param from 
	 * 				New value
	 */
	public void setFrom(E from) {
		this.from.setValue(from);;
	}

	/**
	 * Getter method of to instance variable
	 * 
	 * @return Vertex
	 */
	public Vertex<E> getTo() {
		return to;
	}

	/**
	 * Setter method of from instance variable
	 * 
	 * @param to
	 * 			New value
	 */
	public void setTo(E to) {
		this.to.setValue(to);;
	}

	/**
	 * Getter method of weight instance variable
	 * 
	 * @return weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Setter method of weight instance variable
	 * 
	 * @param weight
	 * 				New value
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String edge = "[" + this.from + "," + this.to + "]";
		return edge;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null || !(obj instanceof Edge))
	        return false;
	    if (obj == this)
	        return true;

	    Edge<E> edge = (Edge<E>) obj;    
	    return (this.getFrom() != null && 
	    		this.getTo() != null && 
	    		this.getFrom().equals(edge.getFrom()) && 
	    		this.getTo().equals(edge.getTo()));
	}

}

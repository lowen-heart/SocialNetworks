package graph;

public class Edge <E extends Comparable<E>>{
	
	private Vertex<E> from;
	private Vertex<E> to;
	private double weight;
	
	
	public Edge(E from, E to) {
	
		this.from = new Vertex<E>(from);
		this.to = new Vertex<E>(to);
		weight = 0.0;
		
	}
	
	public Edge(E from, E to, double weight){
		this(from,to);
		this.weight = weight;
	}

	public Vertex<E> getFrom() {
		return from;
	}

	public void setFrom(E from) {
		this.from.setValue(from);;
	}

	public Vertex<E> getTo() {
		return to;
	}

	public void setTo(E to) {
		this.to.setValue(to);;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		String edge = "[" + this.from + "," + this.to + "]";
		return edge;
	}
	
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

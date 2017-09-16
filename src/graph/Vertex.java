package graph;

public class Vertex <E extends Comparable<E>> {

	private E value;
	
	int degree; //degree of the vertex
	int neighborDeg; //sum of degree of neighbors vertices

	public Vertex(E value) {
		this(value,0);
	}
	
	public Vertex (E value, int degree){
		super();
		if(degree < 0){
			throw new IllegalArgumentException("Argument degree must be greater than zero");
		}
		this.value = value;
		this.degree = degree;
		neighborDeg = 0;
	}


	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		if(degree < 0){
			throw new IllegalArgumentException("degree must be greater than 0");
		}
		this.degree = degree;
	}

	public int getNeighborDeg() {
		return neighborDeg;
	}

	public void setNebDeg(int nebDeg) {
		if(nebDeg < 0){
			throw new IllegalArgumentException("Neighbours degree must be greater than 0");
		}
		this.neighborDeg = nebDeg;
	}

	@Override
	public boolean equals(Object obj){
		if (obj == null || !(obj instanceof Vertex))
	        return false;
	    if (obj == this)
	        return true;

	    Vertex<?> vertex = (Vertex<?>) obj; 
	    return (this.value != null && this.value.equals(vertex.value));
	}
	
	// there is no equal to maintain a non-increasing order
	public int compareTo(Vertex<?> v){
		if(degree < v.degree || degree == v.degree) return 1;
		return -1;
	}
	
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((value == null) ? 0 : value.hashCode());
	    return result;
	}

	@Override
	public String toString() {
		return value.toString();
	}
	
}

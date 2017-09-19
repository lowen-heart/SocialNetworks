package graph;

public class Vertex <E extends Comparable<E>> {

	private E value; //value contained by vertex
	
	int degree; //degree of the vertex
	int neighborDeg; //sum of degree of neighbors vertices

	/**
	 * Constructor of vertex object with one parameter
	 * 
	 * @param value
	 */
	public Vertex(E value) {
		this(value,0);
	}
	
	/**
	 * Constructor of vertex object with two parameter. It will initialize neighbor degree to 0
	 * 
	 * @param value
	 * 				value to assign to the vertex
	 * @param degree
	 * 				degree of the vertex
	 */
	public Vertex (E value, int degree){
		super();
		if(degree < 0){
			throw new IllegalArgumentException("Argument degree must be greater than zero");
		}
		this.value = value;
		this.degree = degree;
		neighborDeg = 0;
	}


	/**
	 * Getter method of value instance variable
	 * 
	 * @return value contained inside the vertex
	 */
	public E getValue() {
		return value;
	}

	/**
	 * Setter method of value instance variable
	 * 
	 * @param value
	 * 				new value
	 */
	public void setValue(E value) {
		this.value = value;
	}

	/**
	 * Getter method of degree instance variable
	 * 
	 * @return degree of the vertex
	 */
	public int getDegree() {
		return degree;
	}

	/**
	 * Setter value of degree instance variable.
	 * Checks if less than 0
	 * 
	 * @param degree
	 * 				new value
	 */
	public void setDegree(int degree) {
		if(degree < 0){
			throw new IllegalArgumentException("degree must be greater than 0");
		}
		this.degree = degree;
	}

	/**
	 * Getter method of neighborDeg instance variable
	 * 
	 * @return neighbor degree of the vertex
	 */
	public int getNeighborDeg() {
		return neighborDeg;
	}

	/**
	 * Setter method of neighbor degree.
	 * Check if less than 0.
	 * 
	 * @param nebDeg
	 * 				new value
	 */
	public void setNebDeg(int nebDeg) {
		if(nebDeg < 0){
			throw new IllegalArgumentException("Neighbours degree must be greater than 0");
		}
		this.neighborDeg = nebDeg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null || !(obj instanceof Vertex))
	        return false;
	    if (obj == this)
	        return true;

	    Vertex<?> vertex = (Vertex<?>) obj; 
	    return (this.value != null && this.value.equals(vertex.value));
	}
	
	/**
	 * 
	 * There is no equal to maintain a non-increasing order
	 * 
	 * @param v
	 * @return
	 */
	public int compareTo(Vertex<?> v){
		if(degree < v.degree || degree == v.degree) return 1;
		return -1;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((value == null) ? 0 : value.hashCode());
	    return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return value.toString();
	}
	
}

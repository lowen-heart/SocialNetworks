package graph;

import java.util.HashMap;

public class Vertex <E extends Comparable<E>> {

	private E value;
	

	public Vertex(E value) {
		super();
		this.value = value;
	}

	public E getValue() {
		return value;
	}

	public void setValue(E value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Vertex))
	        return false;
	    if (obj == this)
	        return true;

	    Vertex<E> vertex = (Vertex<E>) obj; 
	    //System.out.println("Equals Vertex: " + this.value.toString() + "," + vertex.value.toString());
	    return (this.value != null && this.value.equals(vertex.value));
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

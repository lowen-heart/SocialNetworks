package graph;

public class Vertex <E> {

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
	public boolean equals(Object other){
	    if (other instanceof Vertex<?>){
	        if ( ((Vertex<?>)other).value.equals(value) ){
	            return true;
	        }
	    }
	    return false;
	}
	
	
}

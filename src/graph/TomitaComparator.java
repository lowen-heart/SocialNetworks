package graph;

import java.util.Comparator;

public class TomitaComparator<E> implements Comparator<E> {

	@Override
	public int compare(E o1, E o2) {
		Vertex<?> u = (Vertex<?>) o1;
		Vertex<?> v = (Vertex<?>) o2;
		if (u. degree < v. degree || u. degree == v. degree && u.neighborDeg < v.neighborDeg ||
				u.degree == v.degree && u.neighborDeg == v.neighborDeg) return 1; 
		return -1;
	}

}

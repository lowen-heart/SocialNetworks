package graph;

import java.util.Comparator;

/**
 * @author LP
 *
 * @param <E>
 */
public class TomitaComparator<E> implements Comparator<E> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(E o1, E o2) {
		Vertex<?> u = (Vertex<?>) o1;
		Vertex<?> v = (Vertex<?>) o2;
		if (u. degree < v. degree || u. degree == v. degree && u.neighborDeg < v.neighborDeg ||
				u.degree == v.degree && u.neighborDeg == v.neighborDeg) return 1; 
		return -1;
	}

}

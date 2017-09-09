package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class TomitaCliques {

	int[] degree; // degree of vertices
	int[][] A; // adjacency list
	int n; // n vertices
	long nodes; // number of decisions
	long timeLimit; // milliseconds
	long cpuTime; // milliseconds
	int maxSize; // size of max clique
	int style; // used to flavor algorithm
	int[] solution; // as it says
	ArrayList[] colourClass;

	TomitaCliques(int n, int[][] A, int[] degree, int style) {
		this.n = n;
		this.A = A;
		this.degree = degree;
		this.nodes = maxSize = 0;
		this.cpuTime = timeLimit = -1;
		this.style = style;
		this.solution = new int[n];
	}

	void search() {
		cpuTime = System.currentTimeMillis();
		nodes = 0;
		colourClass = new ArrayList[n];
		ArrayList<Integer> C = new ArrayList<Integer>(n);
		ArrayList<Integer> P = new ArrayList<Integer>(n);
		for (int i = 0; i < n; i++)
			colourClass[i] = new ArrayList<Integer>(n);
		orderVertices(P);
		expand(C, P);
	}

	private void orderVertices(ArrayList<Integer> ColOrd) {
		Vertex[] V = new Vertex[n];
		for (int i = 0; i < n; i++)
			V[i] = new Vertex(i, degree[i]);
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				if (A[i][j] == 1)
					V[i].nebDeg = V[i].nebDeg + degree[j];
		if (style == 1)
			Arrays.sort(V);
		if (style == 2)
			minWidthOrder(V);
		if (style == 3)
			Arrays.sort(V, new MCRComparator());
		for (Vertex v : V)
			ColOrd.add(v.index);

	}

	private void minWidthOrder(Vertex[] V) {
		ArrayList<Vertex> L = new ArrayList<Vertex>(n);
		Stack<Vertex> S = new Stack<Vertex>();
		for (Vertex v : V)
			L.add(v);
		while (!L.isEmpty()) {
			Vertex v = L.get(0);
			for (Vertex u : L)
				if (u.degree < v.degree)
					v = u;
			S.push(v);
			L.remove(v);
			for (Vertex u : L)
				if (A[u.index][v.index] == 1)
					u.degree--;
		}
		int k = 0;
		while (!S.isEmpty())
			V[k++] = S.pop();
	}

	void expand(ArrayList<Integer> C, ArrayList<Integer> P) {
		if (timeLimit > 0 && (System.currentTimeMillis() - cpuTime) >= timeLimit)
			return;
		nodes++;
		int[] colour = new int[P.size()];
		numberSort(C, P, P, colour);
		for (int i = P.size() - 1; i >= 0; i--) {
			if (C.size() + colour[i] <= maxSize)
				return;
			int v = P.get(i);
			C.add(v);
			ArrayList<Integer> newP = new ArrayList<Integer>(i);
			for (int j = 0; j <= i; j++) {
				int u = P.get(j);
				if (A[u][v] == 1)
					newP.add(u);
			}

			if (newP.isEmpty() && C.size() > maxSize)
				saveSolution(C);
			if (!newP.isEmpty())
				expand(C, newP);
			C.remove(C.size() - 1);
			P.remove(i);
		}
	}

	private void numberSort(ArrayList<Integer> C, ArrayList<Integer> ColOrd, ArrayList<Integer> P, int[] colour) {
		int colours = 0;
		int m = ColOrd.size();
		for (int i = 0; i < m; i++)
			colourClass[i].clear();
		for (int i = 0; i < m; i++) {
			int v = ColOrd.get(i);
			int k = 0;
			while (conflicts(v, colourClass[k]))
				k++;
			colourClass[k].add(v);
			colours = Math.max(colours, k + 1);
		}
		P.clear();
		int i = 0;
		for (int k = 0; k < colours; k++) {
			for (int j = 0; j < colourClass[k].size(); j++) {
				int v = (Integer) (colourClass[k].get(j));
				P.add(v);
				colour[i++] = k + 1;
			}
		}
	}

	private boolean conflicts(int v, ArrayList<Integer> colourClass) {
		for (int i = 0; i < colourClass.size(); i++) {
			int w = colourClass.get(i);
			if (A[v][w] == 1)
				return true;
		}
		return false;
	}

	void saveSolution(ArrayList<Integer> C) {
		Arrays.fill(solution, 0);
		for (int i : C)
			solution[i] = 1;
		maxSize = C.size();
	}
}

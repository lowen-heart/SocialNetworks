package application;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;

import graph.CapGraph;
import util.GraphLoader;

public class SkytraxApplication {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Graph guigraph = new MultiGraph("Skytrax Airline Reviews");
		graph.Graph graph = new CapGraph();
		
		GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, "data/skytrax_airline_review.csv");

		/*guigraph.addNode("A");
		guigraph.addNode("B");
		guigraph.addNode("C");
		guigraph.addEdge("AB", "A", "B");
		guigraph.addEdge("BC", "B", "C");
		*/
		
		//((CapGraph) graph).printGraph();

		guigraph.display();
	}
	
}


package util.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Before;
import org.junit.Test;

import graph.CapGraph;
import graph.Vertex;
import graph.entity.ReviewAirline;
import graph.entity.Reviewer;
import graph.entity.ReviewAirline.Classes;
import util.GraphLoader;

public class GraphLoaderTest {

	private static HashMap<Classes, String> cabinClass = new HashMap<Classes, String>() {
		{
			put(ReviewAirline.Classes.ECONOMY, "Economy");
			put(ReviewAirline.Classes.BUSINESS, "Business Class");
			put(ReviewAirline.Classes.PREMIUMECONOMY, "Premium Economy");
			put(ReviewAirline.Classes.FIRSTCLASS, "First Class");
			put(ReviewAirline.Classes.EMPTY, "");
		}
	};

	private static final int VERTICESLOADED = 15;
	private static final int EDGESLOADED = 31;
	private static final String CHOSENCABINCLASS = cabinClass.get(ReviewAirline.Classes.PREMIUMECONOMY);
	private static final String FILE = "data/skytrax_airline_review_test_150.csv";

	private Graph guigraph;
	private graph.Graph graph;
	private Reviewer best;
	private Reviewer worst;

	@Before
	public void setUp() throws Exception {

		try {
			graph = new CapGraph();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		guigraph = new MultiGraph("Skytrax Airline Reviews Test Graph Loader");

	}

	@Test
	public void testLoadAirportsReviewsFromCSV() {

		System.out.println("-------- START TEST LOAD AIRPORTS REVIEWS FROM CSV --------");

		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, CHOSENCABINCLASS, FILE);
		} catch (IOException | IllegalAccessException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Vertices loaded must be " + VERTICESLOADED + ",loaded: " + ((CapGraph) graph).getNumVertices());
		assertEquals(VERTICESLOADED, ((CapGraph) graph).getNumVertices());
		System.out.println("Edges loaded must be "+ EDGESLOADED  + ",loaded: " + ((CapGraph) graph).getNumEdges());
		assertEquals(31, ((CapGraph) graph).getNumEdges());
		System.out.println("Start vertices loaded");
		for(Vertex<Reviewer> r : ((CapGraph)graph).getVertices()){
			System.out.println(r);
		}
		System.out.println("End vertices loaded");

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, "LUXURY", FILE);
		} catch (IOException | IllegalAccessException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Vertices loaded must be 0 " + ",loaded: " + ((CapGraph) graph).getNumVertices());
		assertEquals(0, ((CapGraph) graph).getNumVertices());
		System.out.println("Edges loaded must be 0 " + ",loaded: " + ((CapGraph) graph).getNumEdges());
		assertEquals(0, ((CapGraph) graph).getNumEdges());

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, CHOSENCABINCLASS, null);
		} catch (IOException | IllegalAccessException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, null, FILE);
		} catch (IOException | IllegalAccessException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, null, CHOSENCABINCLASS, FILE);
		} catch (IOException | IllegalAccessException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			GraphLoader.loadAirportsReviewsFromCSV(null, guigraph, CHOSENCABINCLASS, FILE);
		} catch (IOException | IllegalAccessException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, CHOSENCABINCLASS, "testdata.xls");
		} catch (IOException | IllegalAccessException | NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("-------- START TEST LOAD AIRPORTS REVIEWS FROM CSV --------");
	}

}

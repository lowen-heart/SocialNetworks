package graph.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import graph.CapGraph;
import graph.TomitaComparator;
import graph.Vertex;
import graph.entity.ReviewAirline;
import graph.entity.Reviewer;
import graph.entity.ReviewAirline.Classes;
import util.GraphLoader;

public class CapGraphTestSkytrax {

	private static HashMap<Classes, String> cabinClass = new HashMap<Classes, String>() {
		{
			put(ReviewAirline.Classes.ECONOMY, "Economy");
			put(ReviewAirline.Classes.BUSINESS, "Business Class");
			put(ReviewAirline.Classes.PREMIUMECONOMY, "Premium Economy");
			put(ReviewAirline.Classes.FIRSTCLASS, "First Class");
			put(ReviewAirline.Classes.EMPTY, "");
		}
	};

	private static final String CHOSENCABINCLASS = cabinClass.get(ReviewAirline.Classes.PREMIUMECONOMY);
	private static final String FILE = "data/skytrax_airline_review_test_150.csv";
	private static final String FILE_50 = "data/skytrax_airline_review_test_50.csv";

	private Graph guigraph;
	private graph.Graph graph;
	private Graph guigraph50;
	private graph.Graph graph50;
	private Reviewer best;
	private Reviewer worst;
	private Reviewer from;
	private Reviewer to;
	private Reviewer best50;
	private Reviewer worst50;

	@Before
	public void setUp() throws Exception {

		loadData(CHOSENCABINCLASS);

		from = new Reviewer(3, "Test", "Person", "Unknown", new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 10.0f, "", 5.0f, 5.0f,
						5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 10.0f, "", 5.0f,
						5.0f, 5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 10.0f, "", 5.0f, 5.0f,
						5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 10.0f, "", 5.0f,
						5.0f, 5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(), new ReviewAirline(LocalDate.now(), "",
						ReviewAirline.Classes.EMPTY.toString(), 10.0f, "", 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, false));

		to = new Reviewer(3, "Test2", "Person2", "Unknown", new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 10.0f, "", 5.0f, 5.0f,
						5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 10.0f, "", 5.0f,
						5.0f, 5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 10.0f, "", 5.0f, 5.0f,
						5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 10.0f, "", 5.0f,
						5.0f, 5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(), new ReviewAirline(LocalDate.now(), "",
						ReviewAirline.Classes.EMPTY.toString(), 10.0f, "", 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, false));

	}

	private void loadData(String cabinClass) {

		try {
			graph = new CapGraph();
			graph50 = new CapGraph();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		guigraph = new MultiGraph("Skytrax Airline Reviews Test CapGraph");
		guigraph50 = new MultiGraph("Skytrax Airline Reviews Test CapGraph 50");
		
		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, cabinClass, FILE);
			GraphLoader.loadAirportsReviewsFromCSV(graph50, guigraph50, cabinClass, FILE_50);
		} catch (IOException | IllegalAccessException e) {
			e.printStackTrace();
		}

		best = ((CapGraph) graph).getBest().getValue();
		worst = ((CapGraph) graph).getWorst().getValue();
		best50 = ((CapGraph) graph50).getBest().getValue();
		worst50 = ((CapGraph) graph50).getWorst().getValue();

	}

	@Test
	public void testAddVertex() {
		System.out.println("-------- START TEST ADD VERTEX --------");

		int numVertices = ((CapGraph) graph).getNumVertices();
		System.out.println("Num vertices before: " + numVertices);

		graph.addVertex(from);
		System.out.println("Num vertices later: " + ((CapGraph) graph).getNumVertices());
		assertEquals(numVertices + 1, ((CapGraph) graph).getNumVertices());
		assertEquals(from, ((CapGraph) graph).getVertexValue(from));

		System.out.println("Adding null vertex");
		try {
			((CapGraph) graph).addVertex(null);
			fail("Added null vertex");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("-------- END TEST ADD VERTEX --------");
	}

	@Test
	public void testGetVertexValue() {
		System.out.println("-------- START TEST GET VERTEX VALUE --------");

		int numVertices = ((CapGraph) graph).getNumVertices();
		System.out.println("Num vertices before: " + numVertices);

		graph.addVertex(from);
		System.out.println("Num vertices later: " + ((CapGraph) graph).getNumVertices());
		assertEquals(numVertices + 1, ((CapGraph) graph).getNumVertices());
		assertEquals(from, ((CapGraph) graph).getVertexValue(from));

		System.out.println("Getting null vertex");
		assertEquals(null, ((CapGraph) graph).getVertexValue(null));

		System.out.println("-------- END TEST GET VERTEX VALUE--------");
	}

	@Test
	public void testAddEdge() {
		System.out.println("-------- START TEST ADD EDGE --------");

		System.out.println("Adding an edge without adding a vertex first (from + to)");

		try {
			graph.addEdge(from, to);
			fail("Added edge without adding both vertex first");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		// Adding vertex from
		graph.addVertex(from);

		System.out.println("Adding an edge without adding a vertex first");

		try {
			graph.addEdge(from, to);
			fail("Added edge without adding vertex first");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		// Adding vertex to
		graph.addVertex(to);

		int numEdges = ((CapGraph) graph).getNumEdges();
		System.out.println("Num edges before: " + numEdges);

		graph.addEdge(from, to);

		System.out.println("Num edges later: " + ((CapGraph) graph).getNumEdges());
		assertEquals(numEdges + 1, ((CapGraph) graph).getNumEdges());

		System.out.println("Add null edge from");
		try {
			((CapGraph) graph).addEdge(null, to);
			fail("Addded edge with null from vertex");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Add null edge to");
		try {
			((CapGraph) graph).addEdge(from, null);
			fail("Added edge with null to vertex");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("-------- END TEST ADD EDGE --------");

	}

	// This test the internal algorithm of Bidirectional BFS
	@Test
	public void testDegreesOfSeparation() {
		System.out.println("-------- START TEST DEGREE OF SEPARATION --------");

		List<Reviewer> dos = null;

		try {
			System.out.println("Degree of separation with start argument null");
			dos = ((CapGraph) graph).degreesOfSeparation(null, worst);
			fail("Check null pointer excetpion in method");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			System.out.println("Degree of separation with end argument null");
			dos = ((CapGraph) graph).degreesOfSeparation(best, null);
			fail("Check null pointer excetpion in method");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		dos = ((CapGraph) graph).degreesOfSeparation(best, worst);
		assertEquals("Stanley", dos.get(0).getSurname());
		assertEquals("Worthington", dos.get(1).getSurname());
		assertEquals("Barrance", dos.get(2).getSurname());
		assertEquals("Sunder", dos.get(3).getSurname());
		assertEquals("Gordon", dos.get(4).getSurname());
		assertEquals(5, dos.size());

		try {
			System.out.println("First attempt without adding vertex first");
			dos = ((CapGraph) graph).degreesOfSeparation(from, worst);
			fail("From is not a vertex of this graph");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Second attempt adding vertex");
		// Adding vertex to
		graph.addVertex(from);
		assertEquals(null, ((CapGraph) graph).degreesOfSeparation(from, worst));
		
		System.out.println("No path test");
		assertEquals(null, ((CapGraph) graph50).degreesOfSeparation(best50, worst50));

		System.out.println("-------- END TEST DEGREE OF SEPARATION --------");
	}

	@Test
	public void testTomitaAlgorithm() {

		System.out.println("-------- START TEST TOMITA ALGORITHM --------");

		List<Reviewer> tomita = ((CapGraph) graph).search();
		for (Reviewer r : tomita) {
			switch (r.getSurname()) {
			case "Rego":
				assertEquals("Rego", r.getSurname());
				break;
			case "Powell":
				assertEquals("Powell", r.getSurname());
				break;
			case "Baker":
				assertEquals("Baker", r.getSurname());
				break;
			case "Taylor":
				assertEquals("Taylor", r.getSurname());
				break;
			case "Gordon":
				assertEquals("Gordon", r.getSurname());
				break;
			default:
				fail("Clique not correct");
			}
			System.out.println(r);
		}
		
		System.out.println("Second test with 2 vertex clique");
		

		tomita = ((CapGraph) graph50).search();
		for (Reviewer r : tomita) {
			switch (r.getSurname()) {
			case "Powell":
				assertEquals("Powell", r.getSurname());
				break;
			case "Miller":
				assertEquals("Miller", r.getSurname());
				break;
			default:
				fail("Clique not correct");
			}
			System.out.println(r);
		}


		System.out.println("-------- END TEST TOMITA ALGORITHM  --------");
	}

	@Test
	public void testOrderTomita() {
		ArrayList<Vertex<Reviewer>> v = new ArrayList<Vertex<Reviewer>>();

		// add all the vertices to v
		v.addAll(((CapGraph) graph).getVertices());

		System.out.println("Not ordered");
		for (Vertex<Reviewer> r : v) {
			System.out.println(r.getValue() + " Degree: " + r.getDegree() + " Neighbour degree: " + r.getNeighborDeg());
		}

		// sort v using the comparator defined
		v.sort(new TomitaComparator<Vertex<Reviewer>>());

		System.out.println("Ordered");
		for (Vertex<Reviewer> r : v) {
			System.out.println(r.getValue() + " Degree: " + r.getDegree() + " Neighbour degree: " + r.getNeighborDeg());
		}
	}
}

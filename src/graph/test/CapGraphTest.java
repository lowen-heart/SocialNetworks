package graph.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import graph.CapGraph;
import graph.Vertex;
import util.GraphLoader;

public class CapGraphTest {
	
	CapGraph g = new CapGraph();

	@Before
	public void setUp() throws Exception {	
		GraphLoader.loadGraph(g, "data/small_test_graph.txt");
		//GraphLoader.loadGraph(g, "data/small_test_graph_no_bidirectional.txt");
		//GraphLoader.loadGraph(g, "data/facebook_1000.txt");
	}

	@Test
	public void printGraphTest() {
		
		g.printGraph();
		
	}

	@Test
	public void isVertexTest(){
		assertTrue(g.isVertex(2));
		assertFalse(g.isVertex(-10));
	}
	
	@Test
	public void addVertexTest(){
		try{
			g.addVertex(-10);
		}catch(IllegalArgumentException e){
			System.out.println("OK Exception taken");
		}
		
	}
	
	@Ignore @Test
	public void DfsTest(){
		Stack<Vertex<Integer>> dfs = g.Dfs();
		System.out.println(dfs);
	}
	
	@Test
	public void exportGraphTest(){
		HashMap<Integer,HashSet<Integer>> export;
		export = g.exportGraph();
		System.out.println(export);
	}
	
	@Test
	public void getEgonetTest(){
		try{
			CapGraph egonet = (CapGraph) g.getEgonet(44);
			egonet.printGraph();
		}catch(IllegalArgumentException e){
			System.out.println(e);
		}
	}
	
	@Test
	public void getSCCs(){
		System.out.println(g.getSCCs());
	}
}

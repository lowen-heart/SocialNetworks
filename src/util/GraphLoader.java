/**
 * @author UCSD MOOC development team
 * 
 * Utility class to add vertices and edges to a graph
 *
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.graphstream.graph.Graph;

import application.SkytraxApplication;
import graph.CapGraph;
import graph.entity.Person;
import graph.entity.ReviewAirline;
import graph.entity.Reviewer;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.DateTimeStringConverter;
import jdk.nashorn.internal.parser.DateParser;
import scala.util.Random;

public class GraphLoader {

	/**
	 * Loads graph with data from a file. The file should consist of lines with
	 * 2 integers each, corresponding to a "from" vertex and a "to" vertex.
	 */
	public static void loadGraph(graph.Graph g, String filename) {
		/*Set<Integer> seen = new HashSet<Integer>();
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		// Iterate over the lines in the file, adding new
		// vertices as they are found and connecting them with edges.
		while (sc.hasNextInt()) {
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			if (!seen.contains(v1)) {
				g.addVertex(v1);
				seen.add(v1);
			}
			if (!seen.contains(v2)) {
				g.addVertex(v2);
				seen.add(v2);
			}
			g.addEdge(v1, v2);
		}

		sc.close();*/
	}


	public static void loadAirportsReviewsFromCSV(graph.Graph g, Graph guigraph, String fileName) {
		boolean result;
		BufferedReader buffer = null;
		Set<Reviewer> seen = new HashSet<Reviewer>();

		try {
			String line;
			buffer = new BufferedReader(
					new FileReader(fileName));
			System.out.println("Header: " + buffer.readLine());
			int index = 0;
			// How to read file in java line by line?
			while ((line = buffer.readLine()) != null) {
				System.out.println("Raw CSV data: " + line);
				result = utilityCSVtoList(index,line,g,guigraph,seen);
				// System.out.println("Converted data: " + result + "\n");
				index++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	// Utility which converts CSV to ArrayList using Split Operation
	private static boolean utilityCSVtoList(int index, String line, graph.Graph g, Graph guigraph, Set<Reviewer> seen) {
		List<Person> result = new ArrayList<Person>();

		if (line != null) {
			String[] splitData = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

			String[] nameSurname = splitData[1].split(" ");
			
			Reviewer reviewer;
			
			if(nameSurname.length >= 2){
				reviewer = new Reviewer(index, nameSurname[0].trim(), nameSurname[1].trim(), splitData[2].trim());
			}
			else
			{
				reviewer = new Reviewer(index, "", nameSurname[0].trim(), splitData[2].trim());
			}
			 
			//System.out.println("Name Surname: " + reviewer.getName() + " " + reviewer.getSurname());
			
			//Reviewer reviewer = new Reviewer(index, nameSurname[0].trim(), nameSurname[1].trim(), splitData[2].trim());
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = null;
			try {
				date = df.parse(splitData[3].trim());
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
			ReviewAirline airlineReview = new ReviewAirline(date, splitData[4].trim(),
					splitData[5].trim(), Float.parseFloat(splitData[6].trim()), splitData[0].trim(),
					Float.parseFloat(splitData[7].trim()), Float.parseFloat(splitData[8].trim()),
					Float.parseFloat(splitData[9].trim()), Float.parseFloat(splitData[10].trim()),
					Float.parseFloat(splitData[11].trim()), Boolean.getBoolean(splitData[12].trim()));
			
			if(!seen.contains(reviewer)){
				
				reviewer.addReview(airlineReview);
				
				g.addVertex(reviewer);
				guigraph.addNode(String.valueOf(index));
				seen.add(reviewer);
				
			}
			else
			{
				System.out.println("Already Exists");
				reviewer = ((CapGraph) g).getVertexValue(reviewer);
				System.out.println(reviewer);
				reviewer.addReview(airlineReview);
			}
			
			System.out.println("Converted data: " + reviewer.toString() + "\n");
			return true;
		}

		return false;
	}

}

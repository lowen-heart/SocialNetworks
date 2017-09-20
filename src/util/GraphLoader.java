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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.graphstream.graph.Graph;

import graph.CapGraph;
import graph.Vertex;
import graph.entity.ReviewAirline;
import graph.entity.Reviewer;

public class GraphLoader {

	/**
	 * Loads graph with data from a file. The file should consist of lines with
	 * 2 integers each, corresponding to a "from" vertex and a "to" vertex.
	 */
	/*
	public static void loadGraph(graph.Graph g, String filename) {

		Set<Integer> seen = new HashSet<Integer>();
		Scanner sc;
		try {
			sc = new Scanner(new File(filename));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		// Iterate over the lines in the file,adding new vertices as they are
		// found and connecting them with edges.
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

		sc.close();

	}
	*/

	/**
	 * This function loads Airport Reviews from a CSV file in a defined format into a CapGraph object and a GUI Graph object
	 * to represent it later in a GUI
	 * 
	 * @param graph
	 * 			Cap Graph to populate
	 * @param guigraph
	 * 			Graphical GraphStream graph to populate
	 * @param cabinClass
	 * 			Cabin class to use
	 * @param fileName
	 * 			File name path
	 */
	public static void loadAirportsReviewsFromCSV(graph.Graph graph, Graph guigraph, String cabinClass, String fileName)
			throws IOException, IllegalAccessException {
		boolean result;
		BufferedReader buffer = null;
		Set<Reviewer> seen = new HashSet<Reviewer>();

		//Argument checks
		if (cabinClass == null) {
			throw new NullPointerException("Cabin class is null");
		}
		if (graph == null) {
			throw new NullPointerException("Graph is null");
		}
		if (guigraph == null) {
			throw new NullPointerException("GUI Graph is null");
		}
		if (fileName == null) {
			throw new NullPointerException("File name is null");
		}

		try {
			String line;
			buffer = new BufferedReader(new FileReader(fileName));
			// System.out.println("Header: " + buffer.readLine());
			System.out.println("LOADING CABIN CLASS: " + cabinClass);
			int index = 0;
			// Reading file line by line
			while ((line = buffer.readLine()) != null) {
				// System.out.println("Raw CSV data: " + line);
				//call helper class to load a single line from the CSV file
				result = utilityCSVtoList(index, line, graph, guigraph, cabinClass, seen);
				if (!result) {
					System.out.println("ERROR LOADING");
				}
				// System.out.println("Converted data: " + result + "\n");
				index++;
			}
			System.out.println("LOADED");

			//call helper class that calculates edges based on a defined convention
			calculateEdges(graph, guigraph, cabinClass);

		} catch (IOException | IllegalAccessException e) {
			System.out.println(e.getMessage());
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

	/**
	 * Helper class that calculate and add edges inside the two graph objects.
	 * 
	 * @param graph
	 * 			Cap Graph to populate
	 * @param guigraph
	 * 			Graphical GraphStream graph to populate
	 * @param cabinClass
	 * 			Cabin class to use
	 */
	private static void calculateEdges(graph.Graph graph, Graph guigraph, String cabinClass) {

		boolean closeness = false;

		Set<Vertex<Reviewer>> verteces = ((CapGraph) graph).getVertices();

		for (Vertex<Reviewer> from : verteces) {
			
			//calculate best and worst Reviewer for the selected class passed with cabinClass argument
			switch (cabinClass) {
			case "Economy":
			case "ECONOMY":
				if (from.getValue().getAvgEconomyReview()
						.compareTo(((CapGraph) graph).getBest().getValue().getAvgEconomyReview()) == 1) {
					((CapGraph) graph).setBest(from);
				}

				if (from.getValue().getAvgEconomyReview()
						.compareTo(((CapGraph) graph).getWorst().getValue().getAvgEconomyReview()) == -1) {
					((CapGraph) graph).setWorst(from);
				}
				break;
			case "Premium Economy":
			case "PREMIUMECONOMY":
				if (from.getValue().getAvgPremiumReview()
						.compareTo(((CapGraph) graph).getBest().getValue().getAvgPremiumReview()) == 1) {
					((CapGraph) graph).setBest(from);
				}

				if (from.getValue().getAvgPremiumReview()
						.compareTo(((CapGraph) graph).getWorst().getValue().getAvgPremiumReview()) == -1) {
					((CapGraph) graph).setWorst(from);
				}
				break;
			case "Business Class":
			case "BUSINESS":
				if (from.getValue().getAvgBusinessReview()
						.compareTo(((CapGraph) graph).getBest().getValue().getAvgBusinessReview()) == 1) {
					((CapGraph) graph).setBest(from);
				}

				if (from.getValue().getAvgBusinessReview()
						.compareTo(((CapGraph) graph).getWorst().getValue().getAvgBusinessReview()) == -1) {
					((CapGraph) graph).setWorst(from);
				}
				break;
			case "First Class":
			case "FIRSTCLASS":
				if (from.getValue().getAvgFirstReview()
						.compareTo(((CapGraph) graph).getBest().getValue().getAvgFirstReview()) == 1) {
					((CapGraph) graph).setBest(from);
				}

				if (from.getValue().getAvgFirstReview()
						.compareTo(((CapGraph) graph).getWorst().getValue().getAvgFirstReview()) == -1) {
					((CapGraph) graph).setWorst(from);
				}
				break;
			case "":
			case "EMPTY":
				if (from.getValue().getAvgEmptyReview()
						.compareTo(((CapGraph) graph).getBest().getValue().getAvgEmptyReview()) == 1) {
					((CapGraph) graph).setBest(from);
				}

				if (from.getValue().getAvgEmptyReview()
						.compareTo(((CapGraph) graph).getWorst().getValue().getAvgEmptyReview()) == -1) {
					((CapGraph) graph).setWorst(from);
				}
				break;
			}

			for (Vertex<Reviewer> to : verteces) {

				//if vertex "to" is not the same that is iterated with the external for "from"
				if (!to.equals(from)) {

					/*Get closeness parameter to check if the two vertices are connected based on a pre-defined convention.
					  Two vertices are linked if 4 of 5 of their detailed reviews have at least -/+ 1 as delta value.
					  Calculate it based on cabin class.*/
					switch (cabinClass) {
					case "Economy":
					case "ECONOMY":
						if (!from.getValue().getEconomyReviews().isEmpty()
								&& !to.getValue().getEconomyReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.ECONOMY.toString());

						}
						break;
					case "Premium Economy":
					case "PREMIUMECONOMY":
						if (!from.getValue().getPremiumReviews().isEmpty()
								&& !to.getValue().getPremiumReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.PREMIUMECONOMY.toString());

						}
						break;
					case "Business Class":
					case "BUSINESS":
						if (!from.getValue().getBusinessReviews().isEmpty()
								&& !to.getValue().getBusinessReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.BUSINESS.toString());

						}
						break;
					case "First Class":
					case "FIRSTCLASS":
						if (!from.getValue().getFirstReviews().isEmpty()
								&& !to.getValue().getFirstReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.FIRSTCLASS.toString());

						}
						break;
					case "":
					case "EMPTY":
						if (!from.getValue().getEmptyReviews().isEmpty()
								&& !to.getValue().getEmptyReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.EMPTY.toString());

						}
						break;

					}

					if (closeness) {
						/*
						 * System.out.print("From: " + from.getValue().getId());
						 * System.out.print(" - To: " + to.getValue().getId());
						 * System.out.println(" - Closensess: " + closeness);
						 * System.out.println("Edges: " +
						 * from.getValue().getId() + " - " +
						 * to.getValue().getId());
						 */
						graph.addEdge(from.getValue(), to.getValue());
						
						//if the edge does not exists inside the GUI Graph object add it.
						if (guigraph.getEdge(String.valueOf(from.getValue().getId()) + "-" + String.valueOf(to.getValue().getId())) == null
								&& guigraph.getEdge(String.valueOf(to.getValue().getId()) + "-" + String.valueOf(from.getValue().getId())) == null) {
							guigraph.addEdge(
									String.valueOf(from.getValue().getId()) + "-" + String.valueOf(to.getValue().getId()),
									String.valueOf(from.getValue().getId()), 
									String.valueOf(to.getValue().getId()));
							// System.out.println("Gui Edge: " +
							// (guigraph.getEdge(String.valueOf(from.getValue().getId())
							// + "-" +
							// String.valueOf(to.getValue().getId()))).toString());
						}
					}
					
					//reset closeness value
					closeness = false;

				}

			}

		}
		//info about the reviewers with the best and worst review
		System.out.println("Best: " + ((CapGraph) graph).getBest().getValue());
		System.out.println("Worst: " + ((CapGraph) graph).getWorst().getValue());
	}

	
	/**
	 * Utility which converts CSV to ArrayList using Split Operation
	 * 
	 * @param index
	 * @param line
	 * @param graph
	 * @param guigraph
	 * @param cabinClass
	 * @param seen
	 * @return
	 * @throws IllegalAccessException
	 */
	private static boolean utilityCSVtoList(int index, String line, graph.Graph graph, Graph guigraph, String cabinClass,
			Set<Reviewer> seen) throws IllegalAccessException {
		if (line != null) {
			
			// split CSV line
			String[] splitData = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

			// take just the lines that are equals to the defined cabin class
			if (splitData[5].trim().equals(cabinClass)) {

				// Split to take name and surname
				String[] nameSurname = splitData[1].split(" ");

				Reviewer reviewer;

				// if there are two values inside the array, there is a name and a surname, if not there is just a surname.
				if (nameSurname.length >= 2) {
					reviewer = new Reviewer(index, nameSurname[0].trim(), nameSurname[1].trim(), splitData[2].trim());
				} else {
					reviewer = new Reviewer(index, "", nameSurname[0].trim(), splitData[2].trim());
				}

				// System.out.println("Name Surname: " + reviewer.getName() + "
				// " + reviewer.getSurname());

				// Reviewer reviewer = new
				// Reviewer(index,nameSurname[0].trim(),nameSurname[1].trim(),
				// splitData[2].trim());

				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = null;
				date = LocalDate.parse(splitData[3].trim(), df);

				ReviewAirline airlineReview = new ReviewAirline(date, splitData[4].trim(), splitData[5].trim(),
						Float.parseFloat(splitData[6].trim()), splitData[0].trim(),
						Float.parseFloat(splitData[7].trim()), Float.parseFloat(splitData[8].trim()),
						Float.parseFloat(splitData[9].trim()), Float.parseFloat(splitData[10].trim()),
						Float.parseFloat(splitData[11].trim()), Boolean.getBoolean(splitData[12].trim()));

				// if the reviewer is new create the vertex inside the two graphs if not just add the review to the existent.
				if (!seen.contains(reviewer)) {

					reviewer.addReview(airlineReview);

					graph.addVertex(reviewer);
					guigraph.addNode(String.valueOf(index));
					
					//create label for GUI graph
					guigraph.getNode(String.valueOf(index)).addAttribute("ui.label",
							index + " - " + reviewer.getName().toString() + " " + reviewer.getSurname().toString()
									+ " - " + reviewer.getCountry() + " - " + airlineReview.getOverallRating());
					// System.out.println(index + " GUI GRAPH INDEX: " + guigraph.getNode(String.valueOf(index)));
					seen.add(reviewer);

				} else {
					// System.out.println("Already Exists");
					reviewer = ((CapGraph) graph).getVertexValue(reviewer);
					// System.out.println(reviewer);
					reviewer.addReview(airlineReview);
				}

				// System.out.println("Converted data: " + reviewer.toString() + "\n");
				return true;
			}

			return true;
		}

		return false;
	}

}

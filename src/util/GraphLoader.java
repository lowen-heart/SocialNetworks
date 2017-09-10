/**
 * @author UCSD MOOC development team
 * 
 * Utility class to add vertices and edges to a graph
 *
 */
package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
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
	public static void loadGraph(graph.Graph g, String filename) {
		/*
		 * Set<Integer> seen = new HashSet<Integer>(); Scanner sc; try { sc =
		 * new Scanner(new File(filename)); } catch (Exception e) {
		 * e.printStackTrace(); return; } // Iterate over the lines in the file,
		 * adding new // vertices as they are found and connecting them with
		 * edges. while (sc.hasNextInt()) { int v1 = sc.nextInt(); int v2 =
		 * sc.nextInt(); if (!seen.contains(v1)) { g.addVertex(v1);
		 * seen.add(v1); } if (!seen.contains(v2)) { g.addVertex(v2);
		 * seen.add(v2); } g.addEdge(v1, v2); }
		 * 
		 * sc.close();
		 */
	}

	/**
	 * @param g
	 * @param guigraph
	 * @param cabinClass
	 * @param fileName
	 */
	public static void loadAirportsReviewsFromCSV(graph.Graph g, Graph guigraph, String cabinClass, String fileName) {
		boolean result;
		BufferedReader buffer = null;
		Set<Reviewer> seen = new HashSet<Reviewer>();

		try {
			String line;
			buffer = new BufferedReader(new FileReader(fileName));
			//System.out.println("Header: " + buffer.readLine());
			System.out.println("LOADING CABIN CLASS: " + cabinClass);
			int index = 0;
			// How to read file in java line by line?
			while ((line = buffer.readLine()) != null) {
				// System.out.println("Raw CSV data: " + line);
				result = utilityCSVtoList(index, line, g, guigraph, cabinClass, seen);
				if(!result){
					System.out.println("ERROR LOADING");
				}
				// System.out.println("Converted data: " + result + "\n");
				index++;
			}
			System.out.println("LOADED");
		} catch (IOException | IllegalAccessException e) {
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
	 * @param g
	 * @param guigraph
	 * @param cabinClass
	 */
	public static void calculateEdges(graph.Graph g, Graph guigraph, String cabinClass) {

		boolean closeness = false;

		Set<Vertex<Reviewer>> verteces = ((CapGraph) g).getVertices();

		for (Vertex<Reviewer> from : verteces) {
			
			switch (cabinClass) {
			case "Economy": case "ECONOMY":
				if(from.getValue().getAvgEconomyReview().compareTo(((CapGraph) g).getBest().getValue().getAvgEconomyReview()) == 1){
					((CapGraph) g).setBest(from);
				}
				
				if(from.getValue().getAvgEconomyReview().compareTo(((CapGraph) g).getWorst().getValue().getAvgEconomyReview()) == -1){
					((CapGraph) g).setWorst(from);
				}
				break;
			case "Premium Economy": case "PREMIUMECONOMY":
				if(from.getValue().getAvgPremiumReview().compareTo(((CapGraph) g).getBest().getValue().getAvgPremiumReview()) == 1){
					((CapGraph) g).setBest(from);
				}
				
				if(from.getValue().getAvgPremiumReview().compareTo(((CapGraph) g).getWorst().getValue().getAvgPremiumReview()) == -1){
					((CapGraph) g).setWorst(from);
				}
				break;
			case "Business Class": case"BUSINESS":
				if(from.getValue().getAvgBusinessReview().compareTo(((CapGraph) g).getBest().getValue().getAvgBusinessReview()) == 1){
					((CapGraph) g).setBest(from);
				}
				
				if(from.getValue().getAvgBusinessReview().compareTo(((CapGraph) g).getWorst().getValue().getAvgBusinessReview()) == -1){
					((CapGraph) g).setWorst(from);
				}
				break;
			case "First Class": case "FIRSTCLASS":
				if(from.getValue().getAvgFirstReview().compareTo(((CapGraph) g).getBest().getValue().getAvgFirstReview()) == 1){
					((CapGraph) g).setBest(from);
				}
				
				if(from.getValue().getAvgFirstReview().compareTo(((CapGraph) g).getWorst().getValue().getAvgFirstReview()) == -1){
					((CapGraph) g).setWorst(from);
				}
				break;
			case "": case "EMPTY":
				if(from.getValue().getAvgEmptyReview().compareTo(((CapGraph) g).getBest().getValue().getAvgEmptyReview()) == 1){
					((CapGraph) g).setBest(from);
				}
				
				if(from.getValue().getAvgEmptyReview().compareTo(((CapGraph) g).getWorst().getValue().getAvgEmptyReview()) == -1){
					((CapGraph) g).setWorst(from);
				}
				break;

			}
			

			for (Vertex<Reviewer> to : verteces) {
				
				if (!to.equals(from)) {

					switch (cabinClass) {
					case "Economy": case "ECONOMY":
						if (!from.getValue().getEconomyReviews().isEmpty()
								&& !to.getValue().getEconomyReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.ECONOMY.toString());

						}
						break;
					case "Premium Economy": case "PREMIUMECONOMY":
						if (!from.getValue().getPremiumReviews().isEmpty()
								&& !to.getValue().getPremiumReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.PREMIUMECONOMY.toString());

						}
						break;
					case "Business Class": case"BUSINESS":
						if (!from.getValue().getBusinessReviews().isEmpty()
								&& !to.getValue().getBusinessReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.BUSINESS.toString());

						}
						break;
					case "First Class": case "FIRSTCLASS":
						if (!from.getValue().getFirstReviews().isEmpty()
								&& !to.getValue().getFirstReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.FIRSTCLASS.toString());

						}
						break;
					case "": case "EMPTY":
						if (!from.getValue().getEmptyReviews().isEmpty()
								&& !to.getValue().getEmptyReviews().isEmpty()) {

							closeness = from.getValue().reviewersCloseness(to.getValue(),
									ReviewAirline.Classes.EMPTY.toString());

						}
						break;

					}

					if (closeness) {
						/*System.out.print("From: " + from.getValue().getId());
						System.out.print(" - To: " + to.getValue().getId());
						System.out.println(" - Closensess: " + closeness);
						System.out.println("Edges: " + from.getValue().getId() + " - " + to.getValue().getId());*/
						g.addEdge(from.getValue(), to.getValue());
						if(guigraph.getEdge(String.valueOf(from.getValue().getId()) + "-" + String.valueOf(to.getValue().getId())) == null && guigraph.getEdge(String.valueOf(to.getValue().getId()) + "-" + String.valueOf(from.getValue().getId())) == null){
							guigraph.addEdge(String.valueOf(from.getValue().getId()) + "-" + String.valueOf(to.getValue().getId()),
									String.valueOf(from.getValue().getId()), String.valueOf(to.getValue().getId()));
							//System.out.println("Gui Edge: " + (guigraph.getEdge(String.valueOf(from.getValue().getId()) + "-" + String.valueOf(to.getValue().getId()))).toString());
						}
					}

					closeness = false;

				}
				
			}

		}
		System.out.println("Best " + ((CapGraph) g).getBest().getValue());
		System.out.println("Worst " + ((CapGraph) g).getWorst().getValue());
	}

	// Utility which converts CSV to ArrayList using Split Operation
	/**
	 * @param index
	 * @param line
	 * @param g
	 * @param guigraph
	 * @param cabinClass
	 * @param seen
	 * @return
	 * @throws IllegalAccessException
	 */
	private static boolean utilityCSVtoList(int index, String line, graph.Graph g, Graph guigraph, String cabinClass,
			Set<Reviewer> seen) throws IllegalAccessException {
		if (line != null) {
			String[] splitData = line.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

			if (splitData[5].trim().equals(cabinClass)) {

				String[] nameSurname = splitData[1].split(" ");

				Reviewer reviewer;

				if (nameSurname.length >= 2) {
					reviewer = new Reviewer(index, nameSurname[0].trim(), nameSurname[1].trim(), splitData[2].trim());
				} else {
					reviewer = new Reviewer(index, "", nameSurname[0].trim(), splitData[2].trim());
				}

				// System.out.println("Name Surname: " + reviewer.getName() + " " + reviewer.getSurname());

				// Reviewer reviewer = new Reviewer(index,nameSurname[0].trim(),nameSurname[1].trim(), splitData[2].trim());

				DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate date = null;
				date = LocalDate.parse(splitData[3].trim(), df);

				ReviewAirline airlineReview = new ReviewAirline(date, splitData[4].trim(), splitData[5].trim(),
						Float.parseFloat(splitData[6].trim()), splitData[0].trim(),
						Float.parseFloat(splitData[7].trim()), Float.parseFloat(splitData[8].trim()),
						Float.parseFloat(splitData[9].trim()), Float.parseFloat(splitData[10].trim()),
						Float.parseFloat(splitData[11].trim()), Boolean.getBoolean(splitData[12].trim()));

				if (!seen.contains(reviewer)) {

					reviewer.addReview(airlineReview);

					g.addVertex(reviewer);
					guigraph.addNode(String.valueOf(index));
					guigraph.getNode(String.valueOf(index)).addAttribute("ui.label", index + " - " + 
																		reviewer.getName().toString() + " " + 
																		reviewer.getSurname().toString() + " - " + 
																		reviewer.getCountry() + " - " + 
																		airlineReview.getOverallRating());
					//System.out.println(index + " GUI GRAPH INDEX: " + guigraph.getNode(String.valueOf(index)));
					seen.add(reviewer);

				} else {
					// System.out.println("Already Exists");
					reviewer = ((CapGraph) g).getVertexValue(reviewer);
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

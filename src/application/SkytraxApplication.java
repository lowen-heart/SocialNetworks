package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import graph.CapGraph;
import graph.entity.ReviewAirline;
import graph.entity.ReviewAirline.Classes;
import graph.entity.Reviewer;
import util.GraphLoader;

public class SkytraxApplication extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9080966197484218070L;

	HashMap<Classes, String> cabinClass = new HashMap<Classes, String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = -2156037618641583793L;

		{
			put(ReviewAirline.Classes.ECONOMY, "Economy");
			put(ReviewAirline.Classes.BUSINESS, "Business Class");
			put(ReviewAirline.Classes.PREMIUMECONOMY, "Premium Economy");
			put(ReviewAirline.Classes.FIRSTCLASS, "First Class");
			put(ReviewAirline.Classes.EMPTY, "");
		}
	};

	Graph guigraph;
	graph.Graph graph;
	Reviewer best;
	Reviewer worst;
	Viewer viewer;
	View graphPanel;
	JPanel userPanel;
	JPanel mainPanel;
	JLabel numVerticesLabel;
	JLabel numEdgesLabel;
	JLabel cabinClassesLabel;
	JLabel fileLabel;
	JLabel result;
	JComboBox<String> comboClasses;
	JComboBox<String> comboFiles;
	JButton easyQuestion;
	JButton easyQuestion2;
	JButton diffQuestion;

	public SkytraxApplication() {

		loadData(cabinClass.get(ReviewAirline.Classes.ECONOMY), "data/skytrax_airline_review_test_150.csv");
		initUI();

	}

	private void loadData(String cabinClass, String file) {

		try {
			graph = new CapGraph();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		guigraph = new MultiGraph("Skytrax Airline Reviews");

		GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, cabinClass, file);

		GraphLoader.calculateEdges(graph, guigraph, cabinClass);

		best = ((CapGraph) graph).getBest().getValue();
		worst = ((CapGraph) graph).getWorst().getValue();

	}

	private void initUI() {

		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		initGraphPanel();
		initUserPanel();

		mainPanel = new JPanel();
		mainPanel.add(userPanel);
		mainPanel.add((Component) graphPanel);

		add(mainPanel);
		setTitle("Skytrax Application");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void initGraphPanel() {

		guigraph.addAttribute("ui.stylesheet",
				"node{ shape: circle; size: 18px, 18px;fill-mode: plain; fill-color: red; stroke-mode: plain; stroke-color: blue; } node#\""
						+ String.valueOf(worst.getId()) + "\" {fill-color: yellow; } node#\""
						+ String.valueOf(best.getId())
						+ "\" {fill-color: green; }  edge{shape: line; fill-color: lightgray;} edge.path{fill-color: green; shape: blob;}");
		guigraph.addAttribute("ui.quality");
		guigraph.addAttribute("ui.antialias");

		viewer = new Viewer(guigraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();

		graphPanel = viewer.addDefaultView(false); // false indicates "no
													// JFrame".

		((Component) graphPanel).setPreferredSize(new Dimension(600, 600));
		((Component) graphPanel).setBackground(Color.GREEN);
	}

	private void initUserPanel() {

		userPanel = new JPanel();
		userPanel.setPreferredSize(new Dimension(200, 600));
		userPanel.setBackground(Color.LIGHT_GRAY);

		numVerticesLabel = new JLabel("#Vertices: " + String.valueOf(((CapGraph) graph).getNumVertices()));
		numEdgesLabel = new JLabel("#Edges: " + String.valueOf(((CapGraph) graph).getNumEdges()));
		cabinClassesLabel = new JLabel("Cabin Classes");
		fileLabel = new JLabel("File");
		fileLabel.setVisible(false);

		// create an empty combo box with items of type String
		comboClasses = new JComboBox<String>();
		comboClasses.setPreferredSize(new Dimension(150, 20));

		// add items to the combo box
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.ECONOMY));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.PREMIUMECONOMY));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.BUSINESS));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.FIRSTCLASS));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.EMPTY));

		ActionListener cbActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reloadData();
			}
		};

		comboClasses.addActionListener(cbActionListener);

		// create an empty combo box with items of type String
		comboFiles = new JComboBox<String>();
		comboFiles.setPreferredSize(new Dimension(150, 20));

		// add items to the combo box
		comboFiles.addItem("data/skytrax_airline_review_test_150.csv");
		comboFiles.addItem("data/skytrax_airline_review_test.csv");
		comboFiles.addItem("data/skytrax_airline_review.csv");
		comboFiles.addActionListener(cbActionListener);

		easyQuestion = new JButton();
		easyQuestion.setPreferredSize(new Dimension(150, 70));
		easyQuestion.setText("<html>Easy 1: Distance between <br />best and worst</html>");
		ActionListener eq1ActionListener = new ActionListener() {// add
			// actionlistner
			// to listen for
			// change
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Button Easy 1 Pressed");
				LinkedList<Reviewer> path = (LinkedList<Reviewer>) ((CapGraph) graph).degreesOfSeparation(worst, best);

				printPath(path);

				result.setText("Degree of separation: " + (path.size() - 1));

				((Component) graphPanel).repaint();
				((Component) graphPanel).revalidate();
			}

		};

		easyQuestion.addActionListener(eq1ActionListener);

		easyQuestion2 = new JButton();
		easyQuestion2.setPreferredSize(new Dimension(150, 70));
		easyQuestion2.setText("<html>Easy 2: Fundamental Reviewers</html>");
		ActionListener eq2ActionListener = new ActionListener() {// add
			// actionlistner
			// to listen for
			// change
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Pressed");
			}
		};

		easyQuestion2.addActionListener(eq2ActionListener);

		diffQuestion = new JButton();
		diffQuestion.setPreferredSize(new Dimension(150, 70));
		diffQuestion.setText("<html>Difficult : Tomita's Algorithm</html>");
		ActionListener dqActionListener = new ActionListener() {// add
			// actionlistner
			// to listen for
			// change
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Difficult Pressed");

				long cpuTime = System.currentTimeMillis();

				LinkedList<Reviewer> vertices = ((CapGraph) graph).search();

				cpuTime = System.currentTimeMillis() - cpuTime;

				System.out.println("CPU Time: " + cpuTime);

				printClique(vertices);

				if(cpuTime < 1000){
					result.setText("CPUTime: " + cpuTime + " ms; Max Clique: " + ((CapGraph) graph).getMaxSize());
				}else{
					result.setText("CPUTime: " + cpuTime/1000 + " s; Max Clique: " + ((CapGraph) graph).getMaxSize());
				}
				

				((Component) graphPanel).repaint();
				((Component) graphPanel).revalidate();
			}
		};

		diffQuestion.addActionListener(dqActionListener);

		result = new JLabel("");

		userPanel.add(numVerticesLabel);
		userPanel.add(numEdgesLabel);
		userPanel.add(cabinClassesLabel);
		userPanel.add(comboClasses);
		userPanel.add(fileLabel);
		userPanel.add(comboFiles);
		userPanel.add(easyQuestion);
		userPanel.add(easyQuestion2);
		userPanel.add(diffQuestion);
		userPanel.add(result);
	}

	private void reloadData() {

		loadData(comboClasses.getSelectedItem().toString(), comboFiles.getSelectedItem().toString());
		mainPanel.remove((Component) graphPanel);
		initGraphPanel();
		numVerticesLabel.setText("#Vertices: " + String.valueOf(((CapGraph) graph).getNumVertices()));
		numEdgesLabel.setText("#Edges: " + String.valueOf(((CapGraph) graph).getNumEdges()));
		mainPanel.add((Component) graphPanel, BorderLayout.LINE_START);
		result.setText("");
		((Component) graphPanel).repaint();
		((Component) graphPanel).revalidate();

	}

	private void printPath(List<Reviewer> path) {

		System.out.println(path);
		if (path != null) {
			Reviewer curr = null;
			Reviewer prev = null;
			for (Reviewer r : path) {
				curr = r;
				// System.out.println("Current " + curr.toString());
				if (prev != null) {
					System.out.println("Previous " + prev.toString());

					// System.out.println((guigraph.getEdge(String.valueOf(prev.getId())
					// + "-" + String.valueOf(curr.getId()))).toString());

					if (guigraph.getEdge(String.valueOf(curr.getId()) + "-" + String.valueOf(prev.getId())) == null) {
						guigraph.getEdge(String.valueOf(prev.getId()) + "-" + String.valueOf(curr.getId()))
								.addAttribute("ui.class", "path");
						// System.out.println("Entrato 1 " + curr.toString());
					} else {
						guigraph.getEdge(String.valueOf(curr.getId()) + "-" + String.valueOf(prev.getId()))
								.addAttribute("ui.class", "path");
						// System.out.println("Entrato 2 " + curr.toString());
					}
				}
				prev = curr;
			}

		}
	}

	private void printClique(List<Reviewer> vertices) {
		
		System.out.println(vertices);

		if (vertices != null) {
			for (Reviewer from : vertices) {
				for (Reviewer to : vertices) {
					if (!from.equals(to)) {		
						if (guigraph.getEdge(String.valueOf(from.getId()) + "-" + String.valueOf(to.getId())) == null) {
							guigraph.getEdge(String.valueOf(to.getId()) + "-" + String.valueOf(from.getId()))
									.addAttribute("ui.class", "path");
						} else {
							guigraph.getEdge(String.valueOf(from.getId()) + "-" + String.valueOf(to.getId()))
									.addAttribute("ui.class", "path");
						}	
					}	
				}
			}
		}
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			SkytraxApplication sa = new SkytraxApplication();
			sa.pack();
			sa.setVisible(true);
		});

	}

}

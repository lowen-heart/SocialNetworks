package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.geom.Point3;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import graph.CapGraph;
import graph.entity.ReviewAirline;
import graph.entity.ReviewAirline.Classes;
import graph.entity.Reviewer;
import util.GraphLoader;

/**
 * @author LP
 *
 * GUI main application of Skytrax CapStone Project
 *
 */
public class SkytraxApplication extends JFrame implements KeyListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9080966197484218070L;

	/**
	 * Hashmap of cabin classes and their string representation
	 */
	private static final HashMap<Classes, String> cabinClass = new HashMap<Classes, String>() {
		
		private static final long serialVersionUID = -2156037618641583793L;

		{
			put(ReviewAirline.Classes.ECONOMY, "Economy");
			put(ReviewAirline.Classes.BUSINESS, "Business Class");
			put(ReviewAirline.Classes.PREMIUMECONOMY, "Premium Economy");
			put(ReviewAirline.Classes.FIRSTCLASS, "First Class");
			put(ReviewAirline.Classes.EMPTY, "");
		}
	};
	
	//default selected cabin class
	private static final String SELECTED = cabinClass.get(ReviewAirline.Classes.PREMIUMECONOMY);
	
	private Graph guigraph;
	private graph.Graph graph;
	private Reviewer best;
	private Reviewer worst;
	private Viewer viewer;
	private View graphView;
	private JPanel userPanel;
	private JPanel mainPanel;
	private JLabel numVerticesLabel;
	private JLabel numEdgesLabel;
	private JLabel cabinClassesLabel;
	private JLabel result;
	private JComboBox<String> comboClasses;
	private JComboBox<String> comboFiles;
	private JButton easyQuestion;
	private JButton diffQuestion;
	private JLabel labelTextArea;
	private JTextArea textArea;

	private boolean dragged;
	private boolean keypressed;

	/**
	 * Constructor
	 * 
	 */
	public SkytraxApplication() {

		dragged = false;
		keypressed = false;

		loadData(cabinClass.get(ReviewAirline.Classes.PREMIUMECONOMY), "data/skytrax_airline_review_test_150.csv");
		initUI();

	}

	/**
	 * Method that loads all the data inside the graphs.
	 * 
	 * @param cabinClass
	 * 			Cabin class to use
	 * @param file
	 * 			Dataset file path
	 */
	private void loadData(String cabinClass, String file) {

		try {
			graph = new CapGraph();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		guigraph = new MultiGraph("Skytrax Airline Reviews");

		// load data
		try {
			GraphLoader.loadAirportsReviewsFromCSV(graph, guigraph, cabinClass, file);
		} catch (IOException | IllegalAccessException e) {
			e.printStackTrace();
		}

		//GraphLoader.calculateEdges(graph, guigraph, cabinClass);

		// set best and worst after loading
		best = ((CapGraph) graph).getBest().getValue();
		worst = ((CapGraph) graph).getWorst().getValue();

	}

	/**
	 * Method that initialized user interface
	 */
	private void initUI() {

		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		initGraphPanel();
		initUserPanel();

		mainPanel = new JPanel();
		mainPanel.add(userPanel);
		mainPanel.add((Component) graphView);

		add(mainPanel);
		setTitle("Skytrax Application");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/**
	 * Method that initialize the GraphStream panel inside the GUI
	 */
	private void initGraphPanel() {

		guigraph.addAttribute("ui.stylesheet",
				"node{ shape: circle; size: 18px, 18px;fill-mode: plain; fill-color: red; stroke-mode: plain; stroke-color: blue;} "
						+ "node#\"" + String.valueOf(worst.getId()) + "\" {fill-color: yellow; } "
						+ "node#\"" + String.valueOf(best.getId()) + "\" {fill-color: green;}" 
						+ "edge{shape: line; fill-color: lightgray;} "
						+ "node.clique{shape: cross; size: 20px, 20px; fill-color: orange; z-index: 5;} "
						+ "edge.clique{fill-color: orange; shape: angle; z-index: 4;} "
						+ "edge.path{fill-color: green; shape: blob;}");
		guigraph.addAttribute("ui.quality");
		guigraph.addAttribute("ui.antialias");

		viewer = new Viewer(guigraph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
		viewer.enableAutoLayout();

		graphView = viewer.addDefaultView(false); // false indicates "no
													// JFrame"

		((Component) graphView).setMaximumSize(new Dimension(800, 800));
		((Component) graphView).setPreferredSize(new Dimension(600, 600));
		((Component) graphView).setMinimumSize(new Dimension(350, 350));
		((Component) graphView).setBackground(Color.GREEN);

		// create adapter for mouse events
		MouseAdapter mouseAdapter = new MouseAdapter() {
			private Point mousePtStart;

			//implement zoom on wheel
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				System.out.println("Mouse wheel");

				viewer.disableAutoLayout();

				graphView.getCamera().setViewPercent(Math.max(graphView.getCamera().getViewPercent() + Math.signum(e.getUnitsToScroll()) * 0.05, 0.01));

			}

			//implement zoom on selected area
			@Override
			public void mouseDragged(MouseEvent e) {
				System.out.println("Mouse dragged (" + e.getX() + "," + e.getY() + ")");
				dragged = true;

			}

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println("Mouse pressed (" + e.getX() + "," + e.getY() + ")");
				mousePtStart = e.getPoint();
			}

			//implement and calculate zoom after drag on GUI
			@Override
			public void mouseReleased(MouseEvent e) {
				System.out.println("Mouse released (" + e.getX() + "," + e.getY() + ")");

				if (dragged && keypressed) {

					viewer.disableAutoLayout();

					int dx = e.getX() - mousePtStart.x;
					int dy = e.getY() - mousePtStart.y;

					if (dx > 0 && dy > 0) {

						//System.out.println("Center View (" + graphView.getCamera().getViewCenter().x + "," + graphView.getCamera().getViewCenter().y + ")");

						Point3 point = graphView.getCamera().transformPxToGu(mousePtStart.x + dx / 2, mousePtStart.y + dy / 2);

						graphView.getCamera().setViewCenter(point.x, point.y, 0);

						double ratio = (dx * dy) / (600.0 * 600.0);

						//System.out.println("Ratio: " + ratio + " Area delta: " + (dx * dy));

						graphView.getCamera().setViewPercent(ratio);

						//System.out.println("New Center View (" + graphView.getCamera().getViewCenter().x + "," + graphView.getCamera().getViewCenter().y + ")");
					} else {
						graphView.getCamera().setViewPercent(1);
					}

					dragged = false;
				}

			}
		};

		((Component) graphView).addMouseWheelListener(mouseAdapter);
		((Component) graphView).addMouseListener(mouseAdapter);
		((Component) graphView).addMouseMotionListener(mouseAdapter);
		graphView.addKeyListener(this);

	}

	/**
	 * Method that initialize user panel
	 */
	private void initUserPanel() {

		userPanel = new JPanel();
		userPanel.setMaximumSize(new Dimension(400, 800));
		userPanel.setPreferredSize(new Dimension(200, 600));
		userPanel.setMinimumSize(new Dimension(50, 350));
		userPanel.setBackground(Color.LIGHT_GRAY);

		numVerticesLabel = new JLabel("#Vertices: " + String.valueOf(((CapGraph) graph).getNumVertices()));
		numEdgesLabel = new JLabel("#Edges: " + String.valueOf(((CapGraph) graph).getNumEdges()));
		cabinClassesLabel = new JLabel("Cabin Classes");

		// create an empty combo box with items of type String
		comboClasses = new JComboBox<String>();
		comboClasses.setPreferredSize(new Dimension(150, 20));

		// add items to the combo box
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.ECONOMY));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.PREMIUMECONOMY));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.BUSINESS));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.FIRSTCLASS));
		comboClasses.addItem(cabinClass.get(ReviewAirline.Classes.EMPTY));
		comboClasses.setSelectedItem(SELECTED);

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
		comboFiles.addItem("data/skytrax_airline_review_test_100.csv");
		comboFiles.addItem("data/skytrax_airline_review_test_50.csv");
		comboFiles.addItem("data/skytrax_airline_review_test.csv");
		comboFiles.addItem("data/skytrax_airline_review.csv");
		comboFiles.addActionListener(cbActionListener);

		// create button for easy question (degree of separation)
		easyQuestion = new JButton();
		easyQuestion.setPreferredSize(new Dimension(150, 70));
		easyQuestion.setText("<html>Easy 1: Distance between <br />best and worst</html>");
		// add actionlistner to listen for changes
		ActionListener eq1ActionListener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				System.out.println("Button Easy Question Pressed");
				
				textArea.setText("");
				labelTextArea.setVisible(false);
				textArea.setVisible(false);
				
				LinkedList<Reviewer> path = (LinkedList<Reviewer>) ((CapGraph) graph).degreesOfSeparation(worst, best);
				
				if(path != null){
					printPath(path);
				
					result.setText("Degree of separation: " + (path.size() - 1));
				}else{
					result.setText("No path found");
				}

				((Component) graphView).repaint();
				((Component) graphView).revalidate();
			}

		};

		easyQuestion.addActionListener(eq1ActionListener);

		// create button for difficult question (Max clique)
		diffQuestion = new JButton();
		diffQuestion.setPreferredSize(new Dimension(150, 70));
		diffQuestion.setText("<html>Difficult : Tomita's Algorithm</html>");
		// add actionlistner to listen for changes
		ActionListener dqActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button Difficult Pressed");
				
				textArea.setText("");
				labelTextArea.setVisible(false);
				textArea.setVisible(false);

				// calculate time to perform Tomita's algorithm
				long cpuTime = System.currentTimeMillis();

				LinkedList<Reviewer> vertices = ((CapGraph) graph).search();

				cpuTime = System.currentTimeMillis() - cpuTime;

				System.out.println("CPU Time: " + cpuTime);

				printClique(vertices);

				if (cpuTime < 1000) {
					result.setText("CPUTime: " + cpuTime + " ms; Max Clique: " + ((CapGraph) graph).getMaxSize());
				} else {
					result.setText("CPUTime: " + cpuTime / 1000 + " s; Max Clique: " + ((CapGraph) graph).getMaxSize());
				}

				((Component) graphView).repaint();
				((Component) graphView).revalidate();
			}
		};

		diffQuestion.addActionListener(dqActionListener);

		result = new JLabel("");
		labelTextArea = new JLabel("");
		labelTextArea.setVisible(false);
		textArea = new JTextArea("");
		textArea.setVisible(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

		userPanel.add(numVerticesLabel);
		userPanel.add(numEdgesLabel);
		userPanel.add(cabinClassesLabel);
		userPanel.add(comboClasses);
		userPanel.add(comboFiles);
		userPanel.add(easyQuestion);
		userPanel.add(diffQuestion);
		userPanel.add(result);
		userPanel.add(labelTextArea);
		userPanel.add(textArea);
	}

	/**
	 * Method that reloads data after selected changes by user
	 */
	private void reloadData() {

		loadData(comboClasses.getSelectedItem().toString(), comboFiles.getSelectedItem().toString());
		mainPanel.remove((Component) graphView);
		initGraphPanel();
		numVerticesLabel.setText("#Vertices: " + String.valueOf(((CapGraph) graph).getNumVertices()));
		numEdgesLabel.setText("#Edges: " + String.valueOf(((CapGraph) graph).getNumEdges()));
		mainPanel.add((Component) graphView, BorderLayout.LINE_START);
		result.setText("");
		labelTextArea.setText("");
		labelTextArea.setVisible(false);
		textArea.setText("");
		textArea.setVisible(false);
		((Component) graphView).repaint();
		((Component) graphView).revalidate();

	}

	/**
	 * Helper method to print path on GraphStream
	 * 
	 * @param path
	 */
	private void printPath(List<Reviewer> path) {

		System.out.println(path);
		
		if (path != null) {
			labelTextArea.setText("Reviewers:");
				
			Reviewer curr = null;
			Reviewer prev = null;
			for (Reviewer r : path) {
				textArea.append(r.getId() + "-" + r.getSurname() + "\n");
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
			
			labelTextArea.setVisible(true);
			textArea.setVisible(true);
		}
	}

	/**
	 * Helper method to print clique
	 * 
	 * @param vertices
	 */
	private void printClique(List<Reviewer> vertices) {

		System.out.println(vertices);
		
		guigraph.addAttribute("ui.stylesheet", "node{ fill-color: lightgrey; size: 10px, 10px;}");

		if (vertices != null) {
			
			labelTextArea.setText("Reviewers:");
			
			
			for (Reviewer from : vertices) {
				guigraph.getNode(String.valueOf(from.getId())).setAttribute("ui.class", "clique");
				textArea.append(from.getId() + "-" + from.getSurname() + "\n");
				
				for (Reviewer to : vertices) {
					if (!from.equals(to)) {
						if (guigraph.getEdge(String.valueOf(from.getId()) + "-" + String.valueOf(to.getId())) == null) {
							guigraph.getEdge(String.valueOf(to.getId()) + "-" + String.valueOf(from.getId()))
									.addAttribute("ui.class", "clique");
						} else {
							guigraph.getEdge(String.valueOf(from.getId()) + "-" + String.valueOf(to.getId()))
									.addAttribute("ui.class", "clique");
						}
					}
				}
			}
			
			labelTextArea.setVisible(true);
			textArea.setVisible(true);
		}
	}

	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			SkytraxApplication sa = new SkytraxApplication();
			sa.pack();
			sa.setVisible(true);
		});

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key Typed: " + e.getKeyChar());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed: " + e.getKeyChar());
		if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
			System.out.println("Z pressed");
			keypressed = true;
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key Released: " + e.getKeyChar());
		if (e.getKeyChar() == 'z' || e.getKeyChar() == 'Z') {
			System.out.println("Z pressed");
			keypressed = false;
		}
	}

}

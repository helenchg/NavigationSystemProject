import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
public class GUI {

	private JFrame frame;
	private JFrame frameMap;
	private JFrame frameList;
	private JFrame frameNevada;
	private JFrame frameTesting;
	private JTextField txtInsertCity;
	private JTextField txtInsertState;
	private JTextField textFieldMinutes;
	private JTextField textFieldMiles;
	private JTextField txtInsertPlace;
	private JTextField txtMostInteresting;
	private JTextField txtInsertXCoord;
	private JTextField txtInsertYCoord;
	private static Graph map; 
	private String begin;
	private String end;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		map = new Graph();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Data from graph
		String[] statesArray = {"Nevada"};
		ArrayList<String> citiesArray = new ArrayList();
		for(String current : map.nodes.keySet()) citiesArray.add(current);
//		System.out.print(nodes.toString());

		// MAP FRAME
		frameMap = new JFrame("M.E.T. MAP OF THE UNITED STATES");
		frameMap.getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if((e.getX() >= 105 && e.getX() <= 267) && (e.getY() >= 350 && e.getY() <= 500)){
					System.out.println("X: " + e.getX() + "Y: " + e.getY());
					frameNevada = new JFrame("Nevada");
					frameNevada.setResizable(false);
					frameNevada.getContentPane().setForeground(Color.LIGHT_GRAY);
					frameNevada.getContentPane().setBackground(SystemColor.control);
					frameNevada.setBounds(100, 100, 500, 700);
					frameNevada.setVisible(true);
//					drawCities();
					ImageIcon imageNevada = new ImageIcon("src\\nevadau.png");
					JLabel nevadaLabel = new JLabel(imageNevada);
					nevadaLabel.setBounds(10, 10, 400, 400);
					nevadaLabel.setVisible(true);
					frameNevada.getContentPane().add(nevadaLabel);
				}
			}
		});
		frameMap.setResizable(false);
		frameMap.getContentPane().setForeground(Color.LIGHT_GRAY);
		frameMap.getContentPane().setBackground(SystemColor.control);
		frameMap.setBounds(100, 100, 1400, 880);
//		frameMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon image = new ImageIcon("src\\usmap.png");
		JLabel mapLabel = new JLabel(image);
		mapLabel.setBounds(10, 10, 400, 400);
		mapLabel.setVisible(true);
		frameMap.getContentPane().add(mapLabel);
		
		// NAVIGATION SYSTEM FRAME
		frame = new JFrame("M.E.T. Navigation System");
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 600, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// INTERESTING PLACES FRAME
		frameList = new JFrame("M.E.T. News Report: Most Interesting Places");
		frameList.setResizable(false);
		frameList.getContentPane().setForeground(Color.LIGHT_GRAY);
		frameList.getContentPane().setBackground(SystemColor.control);
		frameList.setBounds(100, 100, 500, 500);
//		frameList.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Title Panel
		JPanel panelTitle = new JPanel();
		JLabel lblTitle = new JLabel("M.E.T Navigation System");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		// Trip Planner Panel
		JPanel panelTripPlanner = new JPanel();
		panelTripPlanner.setBackground(Color.LIGHT_GRAY);		
		// Adding elements Panel. >>> NOT GOING TO BE IMPLEMENTED BECAUSE OF TIME CONSTRAINT
		JPanel panelAddition = new JPanel();
		panelAddition.setBackground(Color.LIGHT_GRAY);
		// Result Panel
		JPanel panelResultBox = new JPanel();
		panelResultBox.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelResultBox.setBackground(SystemColor.controlHighlight);
		// Alternative Route Panel
		JPanel panelAlternatives = new JPanel();
		panelAlternatives.setBackground(SystemColor.controlHighlight);
		panelAlternatives.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		/**
		 * This block below deals with the GUI part of selecting current location, destination and features.	
		 * This is basically the heart of the navigation system.	
		 */
		JLabel lblSelectCurrentCity = new JLabel("Select City:");
		
		JLabel lblCurrentLocation = new JLabel("Current Location:");
		lblCurrentLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JComboBox comboBoxCurrentCity = new JComboBox(citiesArray.toArray());

		JComboBox comboBoxCurrentStates = new JComboBox(statesArray);
		comboBoxCurrentStates.setEnabled(false);
		
		JLabel lblSelectCurrentState = new JLabel("Select State:");
		
		JLabel lblSelectDestinationState = new JLabel("Select State:");
		
		JLabel lblSelectCity = new JLabel("Select City");
		
		JComboBox comboBoxDestinationState = new JComboBox(statesArray);
		comboBoxDestinationState.setEnabled(false);
		JComboBox comboBoxDestinationCity = new JComboBox(citiesArray.toArray());
		JSeparator separator = new JSeparator();
		
		JLabel lblFeatures = new JLabel("Check the desired features");
		lblFeatures.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JCheckBox chckbxShortestRoute = new JCheckBox("Shortest route");
		chckbxShortestRoute.setSelected(true);
		
		JCheckBox chckbxFastestRoute = new JCheckBox("Fastest route");
		chckbxFastestRoute.setSelected(true);
		
		JCheckBox chckbxAlternativeRoutes = new JCheckBox("Alternative routes");
		JCheckBox chckbxInterestingPlaces = new JCheckBox("Interesting places");
		JCheckBox chckbxShowMap = new JCheckBox("Show Map");

		// DISPLAY INFORMATION AND CALCULATION ON JPANEL RESULT BOX
		// Search Button Action!
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					System.out.println("Button Pressed");
					// Refresh the panels whenever the 'SEARCH' button is pressed
					panelResultBox.removeAll();
					panelResultBox.updateUI();
					panelAlternatives.removeAll();
					panelAlternatives.updateUI();
					
					// Strings
					// TODO: Bold A and B
					begin = String.valueOf(comboBoxCurrentCity.getSelectedItem());
					end = comboBoxDestinationCity.getSelectedItem().toString();
					JTextPane txtpnShort = new JTextPane();
					JTextPane txtpnFast = new JTextPane();
					JTextPane txtpnAlter = new JTextPane();
					txtpnShort.setBackground(SystemColor.controlHighlight);
					txtpnShort.setText("Shortest route from " + begin + " to " + end + " takes: ");
					txtpnFast.setBackground(SystemColor.controlHighlight);
					txtpnFast.setText("Fastest route from " + begin + " to " + end + " takes: ");
					txtpnAlter.setBackground(SystemColor.controlHighlight);
					txtpnAlter.setText("Alternative routes to get from " + begin + " to " + end + " takes: ");
					
					// CONDITIONS FOR DISPLAYING THE DIFFERENT INFORMATION ASKED BY USER
					// Shortest Route selected ONLY
					if(chckbxShortestRoute.isSelected()){
						GroupLayout gl_panelResultBox = new GroupLayout(panelResultBox);
						gl_panelResultBox.setHorizontalGroup(
							gl_panelResultBox.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelResultBox.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnShort, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
									.addContainerGap())
						);
						gl_panelResultBox.setVerticalGroup(
							gl_panelResultBox.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelResultBox.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnShort, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(79, Short.MAX_VALUE))
						);
						panelResultBox.setLayout(gl_panelResultBox);
					}				
					// Fastest Route selected ONLY
					else if(chckbxFastestRoute.isSelected()){
						GroupLayout gl_panelResultBox = new GroupLayout(panelResultBox);
						gl_panelResultBox.setHorizontalGroup(
							gl_panelResultBox.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelResultBox.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnFast, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
									.addContainerGap())
						);
						gl_panelResultBox.setVerticalGroup(
							gl_panelResultBox.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelResultBox.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnFast, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(79, Short.MAX_VALUE))
						);
						panelResultBox.setLayout(gl_panelResultBox);
					}
					// BOTH FASTEST ROUTE AND SHORTEST ROUTE SELECTED
					if(chckbxFastestRoute.isSelected() && chckbxShortestRoute.isSelected()){
						GroupLayout gl_panelResultBox = new GroupLayout(panelResultBox);
						gl_panelResultBox.setHorizontalGroup(
							gl_panelResultBox.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelResultBox.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_panelResultBox.createParallelGroup(Alignment.LEADING)
										.addComponent(txtpnShort, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
										.addComponent(txtpnFast))
									.addContainerGap(306, Short.MAX_VALUE))
						);
						gl_panelResultBox.setVerticalGroup(
							gl_panelResultBox.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelResultBox.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnShort,GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtpnFast)
									.addContainerGap(79, Short.MAX_VALUE))
						);
						panelResultBox.setLayout(gl_panelResultBox);
					}
					// Show Map
					if(chckbxShowMap.isSelected()){
						frameMap.setVisible(true);
						
					}
					// Show Alternative Routes
					if(chckbxAlternativeRoutes.isSelected()){
						
						GroupLayout g_panelAlternatives = new GroupLayout(panelAlternatives);
						g_panelAlternatives.setHorizontalGroup(
								g_panelAlternatives.createParallelGroup(Alignment.LEADING)
								.addGroup(g_panelAlternatives.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnAlter, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
									.addContainerGap())
						);
						g_panelAlternatives.setVerticalGroup(
								g_panelAlternatives.createParallelGroup(Alignment.LEADING)
								.addGroup(g_panelAlternatives.createSequentialGroup()
									.addContainerGap()
									.addComponent(txtpnAlter, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
									.addContainerGap(79, Short.MAX_VALUE))
						);
						panelAlternatives.setLayout(g_panelAlternatives);
					}
					// Interesting Places
					if(chckbxInterestingPlaces.isSelected()){
						ArrayList<Graph.Node> nodes = map.listRatings();
						
						String[] columnNames = {"Places", "Ranking", "City or Place of Interest"};
						
						ArrayList<String> names = new ArrayList();
						ArrayList<Double> ranking = new ArrayList();
						ArrayList<String> type = new ArrayList();
						for(int i =0; i < nodes.size(); i++){
							names.add(nodes.get(i).getName());
							ranking.add(nodes.get(i).getRating());
							if(nodes.get(i).cost == 1){
								type.add("City");
							}
							else if(nodes.get(i).cost == 2){
								type.add("Landmark");
							}
						}
						Object[][] data = new Object[nodes.size()][columnNames.length]; // 3 columns of information
						
						for(int i =0; i < nodes.size(); i++){
							data[i][0] =names.get(i);
							data[i][1] = ranking.get(i);
							data[i][2] = type.get(i);
						}
						JTable table = new JTable(data, columnNames);
						JScrollPane scrollPane= new  JScrollPane(table);
						frameList.getContentPane().add(scrollPane);
						frameList.setVisible(true);
					}
			}
		});

		/**
		 * Trip Planner Panel: placing components on panel
		 */
		
		JLabel lblDevelopedByElena = new JLabel("Developed by: Elena Chong, Tim McDaniel, and Mitchell Murray");
		GroupLayout gl_panelTitle = new GroupLayout(panelTitle);
		gl_panelTitle.setHorizontalGroup(
			gl_panelTitle.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTitle.createSequentialGroup()
					.addGroup(gl_panelTitle.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTitle.createSequentialGroup()
							.addGap(96)
							.addComponent(lblDevelopedByElena))
						.addGroup(gl_panelTitle.createSequentialGroup()
							.addGap(198)
							.addComponent(lblTitle)))
					.addContainerGap(249, Short.MAX_VALUE))
		);
		gl_panelTitle.setVerticalGroup(
			gl_panelTitle.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTitle.createSequentialGroup()
					.addComponent(lblTitle)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblDevelopedByElena))
		);
		panelTitle.setLayout(gl_panelTitle);
				
		JLabel lblHowLongDo = new JLabel("How long do you want to spend on the road?");
		
		JLabel lblTripPlanner = new JLabel("Trip Planner");
		lblTripPlanner.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		textFieldMinutes = new JTextField();
		textFieldMinutes.setEnabled(false);
		textFieldMinutes.setText("in minutes");
		textFieldMinutes.setColumns(10);
		
		JLabel lblYouWantTo = new JLabel("You want to travel but do not know where to go? Let us help you decide!");
		
		JLabel lblMinutes = new JLabel("minutes");
		
		JLabel lblOr = new JLabel("or");
		
		textFieldMiles = new JTextField();
		textFieldMiles.setEnabled(false);
		textFieldMiles.setText("in miles");
		textFieldMiles.setColumns(10);
		
		JLabel lblMiles = new JLabel("miles");
		
		JButton btnExplore = new JButton("Explore");
		btnExplore.setEnabled(false);
		
		// Extra features for trip planner >>> NOT IMPLEMENTED
//		JLabel lblDoYouWant = new JLabel("Do you want to explore a city or an interesting place?");
		
//		JCheckBox chckbxCity = new JCheckBox("city");
//		chckbxCity.setEnabled(false);
//		chckbxCity.setBackground(Color.LIGHT_GRAY);
		
//		JCheckBox chckbxInterestingPlace = new JCheckBox("interesting place");
//		chckbxInterestingPlace.setEnabled(false);
//		chckbxInterestingPlace.setBackground(Color.LIGHT_GRAY);
		
		// If trip planner is selected, then enable all the options of this panel.
		JCheckBox chckbxEnableTripPlanner = new JCheckBox("Enable Trip Planner");
		chckbxEnableTripPlanner.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxEnableTripPlanner.isSelected()){
//					chckbxInterestingPlace.setEnabled(true);
//					chckbxCity.setEnabled(true);
					textFieldMiles.setEnabled(true);
					textFieldMinutes.setEnabled(true);
					btnExplore.setEnabled(true);

				}
				else{
//					chckbxInterestingPlace.setEnabled(false);
//					chckbxCity.setEnabled(false);
					textFieldMinutes.setEnabled(false);
					textFieldMiles.setEnabled(false);
					btnExplore.setEnabled(false);

				}
			}
		});
		
		chckbxEnableTripPlanner.setForeground(new Color(255, 255, 255));
		chckbxEnableTripPlanner.setBackground(Color.LIGHT_GRAY);

		GroupLayout gl_panelTripPlanner = new GroupLayout(panelTripPlanner);
		gl_panelTripPlanner.setHorizontalGroup(
			gl_panelTripPlanner.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelTripPlanner.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTripPlanner.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTripPlanner.createSequentialGroup()
							.addComponent(chckbxEnableTripPlanner)
							.addGap(95)
							.addComponent(lblTripPlanner))
						.addComponent(lblYouWantTo)
						.addGroup(gl_panelTripPlanner.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnExplore)
							.addGroup(gl_panelTripPlanner.createSequentialGroup()
								.addComponent(lblHowLongDo)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldMinutes, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblMinutes)
								.addGap(18)
								.addComponent(lblOr)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textFieldMiles, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblMiles))))
					.addContainerGap(82, Short.MAX_VALUE))
		);
		gl_panelTripPlanner.setVerticalGroup(
			gl_panelTripPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTripPlanner.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelTripPlanner.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxEnableTripPlanner)
						.addComponent(lblTripPlanner))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblYouWantTo)
					.addGap(18)
					.addGroup(gl_panelTripPlanner.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHowLongDo)
						.addComponent(textFieldMinutes, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMinutes)
						.addComponent(textFieldMiles, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMiles)
						.addComponent(lblOr))
					.addGap(18)
					.addComponent(btnExplore)
					.addGap(12))
		);
		panelTripPlanner.setLayout(gl_panelTripPlanner);
		
		/**
		 * Alternatives Route Panel
		 */
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(panelTitle, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addComponent(lblCurrentLocation))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(12)
							.addComponent(lblFeatures))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(27)
							.addComponent(chckbxShortestRoute)
							.addGap(18)
							.addComponent(chckbxFastestRoute)
							.addGap(18)
							.addComponent(chckbxAlternativeRoutes)
							.addGap(18)
							.addComponent(chckbxInterestingPlaces))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panelAlternatives, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(panelResultBox, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE))
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(chckbxShowMap)
								.addComponent(btnSearch)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSelectCurrentCity)
								.addComponent(comboBoxCurrentCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectCurrentState)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(comboBoxCurrentStates, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(238)
									.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblSelectDestinationState)
								.addComponent(comboBoxDestinationState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDestination)
								.addComponent(lblSelectCity)
								.addComponent(comboBoxDestinationCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(1)
							.addComponent(panelAddition, GroupLayout.PREFERRED_SIZE, 603, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelTripPlanner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(panelTripPlanner, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCurrentLocation)
						.addComponent(lblDestination))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectCurrentState)
						.addComponent(lblSelectDestinationState))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(comboBoxCurrentStates, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBoxDestinationState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(20)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectCurrentCity)
						.addComponent(lblSelectCity))
					.addGap(7)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxCurrentCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxDestinationCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblFeatures)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(chckbxShortestRoute)
						.addComponent(chckbxFastestRoute)
						.addComponent(chckbxAlternativeRoutes)
						.addComponent(chckbxInterestingPlaces))
					.addGap(13)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelResultBox, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(14)
							.addComponent(chckbxShowMap)))
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelAlternatives, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addGap(7))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSearch)
							.addGap(18)))
					.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
					.addComponent(panelAddition, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		frame.getContentPane().setLayout(groupLayout);
		
		/**
		 * Adding elements to our files. TODO: ADDING STUFF / NOT IMPLEMENTED
		 */
		JLabel lblsubtitle = new JLabel("Cannot find the location? Help us add it to the map! ");
		lblsubtitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblInsertCity = new JLabel("City:");
		
		txtInsertCity = new JTextField();
		txtInsertCity.setEnabled(false);
		txtInsertCity.setText("Insert City");
		txtInsertCity.setColumns(10);
		
		JLabel lblInsertState = new JLabel("State:");
		
		txtInsertState = new JTextField();
		txtInsertState.setEnabled(false);
		txtInsertState.setText("Insert State");
		txtInsertState.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setEnabled(false);
		
		JLabel lblInterestingPlace = new JLabel("Interesting place:");
		
		txtInsertPlace = new JTextField();
		txtInsertPlace.setEnabled(false);
		txtInsertPlace.setText("Insert place");
		txtInsertPlace.setColumns(10);
		
		JLabel lblRanking = new JLabel("Ranking:");
		
		txtMostInteresting = new JTextField();
		txtMostInteresting.setEnabled(false);
		txtMostInteresting.setText("5 most interesting - 0 less interesting");
		txtMostInteresting.setColumns(10);
		
		JLabel lblXCoordinate = new JLabel("X coordinate:");
		
		JLabel lblYCoordinate = new JLabel("Y coordinate:");
		
		txtInsertXCoord = new JTextField();
		txtInsertXCoord.setEnabled(false);
		txtInsertXCoord.setText("Insert X coord");
		txtInsertXCoord.setColumns(10);
		
		txtInsertYCoord = new JTextField();
		txtInsertYCoord.setEnabled(false);
		txtInsertYCoord.setText("Insert Y coord");
		txtInsertYCoord.setColumns(10);
		
		JLabel lblNotAvailableAt = new JLabel("Not available at the time");
		lblNotAvailableAt.setForeground(new Color(255, 0, 0));
		lblNotAvailableAt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		GroupLayout gl_panelAddition = new GroupLayout(panelAddition);
		gl_panelAddition.setHorizontalGroup(
			gl_panelAddition.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAddition.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAddition.createSequentialGroup()
							.addComponent(lblInterestingPlace)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtInsertPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblRanking)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtMostInteresting, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(55)
							.addComponent(btnAdd))
						.addGroup(gl_panelAddition.createSequentialGroup()
							.addComponent(lblsubtitle)
							.addGap(52)
							.addComponent(lblNotAvailableAt))
						.addGroup(gl_panelAddition.createSequentialGroup()
							.addGroup(gl_panelAddition.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAddition.createSequentialGroup()
									.addComponent(lblInsertState)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInsertState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelAddition.createSequentialGroup()
									.addComponent(lblXCoordinate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInsertXCoord, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)))
							.addGap(29)
							.addGroup(gl_panelAddition.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAddition.createSequentialGroup()
									.addComponent(lblInsertCity)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInsertCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelAddition.createSequentialGroup()
									.addComponent(lblYCoordinate)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInsertYCoord, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(59, Short.MAX_VALUE))
		);
		gl_panelAddition.setVerticalGroup(
			gl_panelAddition.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAddition.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblsubtitle)
						.addComponent(lblNotAvailableAt))
					.addGap(18)
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertState)
						.addComponent(txtInsertState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInsertCity)
						.addComponent(txtInsertCity, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblXCoordinate)
						.addComponent(txtInsertXCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblYCoordinate)
						.addComponent(txtInsertYCoord, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInterestingPlace)
						.addComponent(txtInsertPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblRanking)
						.addComponent(txtMostInteresting, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAdd))
					.addContainerGap(40, Short.MAX_VALUE))
		);
		panelAddition.setLayout(gl_panelAddition);
//		drawCities();

	}
//	
//	public class testing extends JFrame{
//		ArrayList<Graph.Node> nodesA = map.listRatings();
//		
//		public void paintComponent(Graphics g){
//			int width = 10;
//			int height = 10;
//			super.paintComponents(g);
////			Graphics g = frameTesting.getGraphics();
//			g.setColor(Color.black);
//			for(int i =0; i< nodesA.size(); i++){
//				g.fillOval(nodesA.get(i).x, nodesA.get(i).y, width, height);
//			}
//		}
//	}
//	public void drawCities(){
//		JFrame frameTesting = new JFrame("sd");
//		int width = 10;
//		int height = 10;
//		frameTesting.setResizable(false);
//		frameTesting.getContentPane().setForeground(Color.LIGHT_GRAY);
//		frameTesting.getContentPane().setBackground(SystemColor.control);
//		frameTesting.setBounds(100, 100, 600, 900);
//		frameTesting.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frameTesting.setVisible(true);
//		ArrayList<Graph.Node> nodesA = map.listRatings();
//		Graphics g = frameTesting.getGraphics();
//		g.setColor(Color.black);
////		g.fillOval(500, 500, width, height);
//
//		for(int i =0; i< nodesA.size(); i++){
//			g.fillOval(nodesA.get(i).x, nodesA.get(i).y, width, height);
//		}
////		frameTesting.revalidate();
////		frameTesting.repaint();
//	}
}

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;


public class GUI {

	private JFrame frame;
	private JTextField txtInsertCity;
	private JTextField txtInsertState;
	private JTextField textFieldMinutes;
	private JTextField textFieldMiles;
	private JTextField txtInsertPlace;
	private JTextField txtMostInteresting;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setForeground(Color.LIGHT_GRAY);
		frame.getContentPane().setBackground(SystemColor.control);
		frame.setBounds(100, 100, 600, 780);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		String[] statesArray = {"Nevada"};
		String[] citiesArray = {"Las Vegas", "Los Angeles", "Miami", "Orlando", "Disney World", "Yellowstone"};
		
		JPanel panelTitle = new JPanel();
		
		JPanel panelAddition = new JPanel();
		panelAddition.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblSelectCurrentCity = new JLabel("Select City:");
		
		JLabel lblCurrentLocation = new JLabel("Current Location:");
		lblCurrentLocation.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JComboBox comboBoxCurrentCity = new JComboBox(citiesArray);
		
		JComboBox comboBoxCurrentStates = new JComboBox(statesArray);
		
		JLabel lblSelectCurrentState = new JLabel("Select State:");
		
		JButton btnSearch = new JButton("Search");
		
		JLabel lblSelectDestinationState = new JLabel("Select State:");
		
		JLabel lblSelectCity = new JLabel("Select City");
		
		JComboBox comboBoxDestinationState = new JComboBox(statesArray);
		
		JComboBox comboBoxDestinationCity = new JComboBox(citiesArray);
		
		JSeparator separator = new JSeparator();
		
		JLabel lblFeatures = new JLabel("Check the desired features");
		lblFeatures.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JCheckBox chckbxShortestRoute = new JCheckBox("Shortest route");
		chckbxShortestRoute.setSelected(true);
		
		JCheckBox chckbxFastestRoute = new JCheckBox("Fastest route");
		chckbxFastestRoute.setSelected(true);
		
		JCheckBox chckbxAlternativeRoutes = new JCheckBox("Alternative routes");
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.controlHighlight);
		
		JCheckBox chckbxInterestingPlaces = new JCheckBox("Interesting places");
		
		JPanel panelTripPlanner = new JPanel();
		panelTripPlanner.setBackground(Color.LIGHT_GRAY);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblFeatures)
					.addContainerGap(406, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCurrentLocation)
						.addComponent(lblSelectCurrentState)
						.addComponent(lblSelectCurrentCity)
						.addComponent(comboBoxCurrentStates, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxCurrentCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(chckbxShortestRoute)
							.addGap(18)
							.addComponent(chckbxFastestRoute)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
							.addGap(41)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(comboBoxDestinationState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSelectCity)
								.addComponent(lblSelectDestinationState)
								.addComponent(lblDestination)
								.addComponent(comboBoxDestinationCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(171))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(chckbxAlternativeRoutes)
							.addGap(18)
							.addComponent(chckbxInterestingPlaces)
							.addGap(63))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
					.addComponent(btnSearch)
					.addGap(50))
				.addComponent(panelTitle, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelTripPlanner, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(panelAddition, 0, 0, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panelTitle, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCurrentLocation)
								.addComponent(lblDestination))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelectCurrentState)
								.addComponent(lblSelectDestinationState))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(comboBoxCurrentStates, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBoxDestinationState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelTripPlanner, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
							.addGap(76)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblSelectCurrentCity)
						.addComponent(lblSelectCity))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBoxCurrentCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBoxDestinationCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblFeatures)
					.addGap(9)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(chckbxShortestRoute)
						.addComponent(chckbxFastestRoute)
						.addComponent(chckbxAlternativeRoutes)
						.addComponent(chckbxInterestingPlaces))
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSearch)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
					.addGap(62)
					.addComponent(panelAddition, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
		);
		
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
		
		JLabel lblDoYouWant = new JLabel("Do you want to explore a city or an interesting place?");
		
		JCheckBox chckbxCity = new JCheckBox("city");
		chckbxCity.setEnabled(false);
		chckbxCity.setBackground(Color.LIGHT_GRAY);
		
		JCheckBox chckbxInterestingPlace = new JCheckBox("interesting place");
		chckbxInterestingPlace.setEnabled(false);
		chckbxInterestingPlace.setBackground(Color.LIGHT_GRAY);
		
		JCheckBox chckbxEnableTripPlanner = new JCheckBox("Enable Trip Planner");
		chckbxEnableTripPlanner.setForeground(new Color(255, 255, 255));
		chckbxEnableTripPlanner.setBackground(Color.LIGHT_GRAY);
		GroupLayout gl_panelTripPlanner = new GroupLayout(panelTripPlanner);
		gl_panelTripPlanner.setHorizontalGroup(
			gl_panelTripPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTripPlanner.createSequentialGroup()
					.addContainerGap()
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
					.addComponent(lblMiles)
					.addContainerGap(82, Short.MAX_VALUE))
				.addGroup(gl_panelTripPlanner.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDoYouWant)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxCity)
					.addGap(18)
					.addComponent(chckbxInterestingPlace)
					.addContainerGap(88, Short.MAX_VALUE))
				.addGroup(gl_panelTripPlanner.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelTripPlanner.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panelTripPlanner.createSequentialGroup()
							.addComponent(chckbxEnableTripPlanner)
							.addPreferredGap(ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
							.addComponent(lblTripPlanner)
							.addGap(262))
						.addGroup(gl_panelTripPlanner.createSequentialGroup()
							.addComponent(lblYouWantTo)
							.addContainerGap(174, Short.MAX_VALUE))))
		);
		gl_panelTripPlanner.setVerticalGroup(
			gl_panelTripPlanner.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTripPlanner.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_panelTripPlanner.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTripPlanner)
						.addComponent(chckbxEnableTripPlanner))
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelTripPlanner.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDoYouWant)
						.addComponent(chckbxCity)
						.addComponent(chckbxInterestingPlace))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panelTripPlanner.setLayout(gl_panelTripPlanner);
		
		JLabel lblsubtitle = new JLabel("Cannot find the location? Help us add it to the map! ");
		lblsubtitle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		
		JLabel lblInsertCity = new JLabel("City:");
		
		txtInsertCity = new JTextField();
		txtInsertCity.setText("Insert City");
		txtInsertCity.setColumns(10);
		
		JLabel lblInsertState = new JLabel("State:");
		
		txtInsertState = new JTextField();
		txtInsertState.setText("Insert State");
		txtInsertState.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		
		JLabel lblInterestingPlace = new JLabel("Interesting place:");
		
		txtInsertPlace = new JTextField();
		txtInsertPlace.setText("Insert place");
		txtInsertPlace.setColumns(10);
		
		JLabel lblRanking = new JLabel("Ranking:");
		
		txtMostInteresting = new JTextField();
		txtMostInteresting.setText("5 most interesting - 1 less interesting");
		txtMostInteresting.setColumns(10);
		GroupLayout gl_panelAddition = new GroupLayout(panelAddition);
		gl_panelAddition.setHorizontalGroup(
			gl_panelAddition.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelAddition.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAddition.createSequentialGroup()
							.addGroup(gl_panelAddition.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAddition.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(lblInterestingPlace)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInsertPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_panelAddition.createSequentialGroup()
									.addComponent(lblInsertState)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInsertState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(0)
							.addGroup(gl_panelAddition.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panelAddition.createSequentialGroup()
									.addComponent(lblInsertCity)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtInsertCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, gl_panelAddition.createSequentialGroup()
									.addGap(12)
									.addComponent(lblRanking)
									.addGap(6)
									.addComponent(txtMostInteresting, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
									.addComponent(btnAdd)
									.addGap(58))))
						.addGroup(gl_panelAddition.createSequentialGroup()
							.addComponent(lblsubtitle)
							.addContainerGap(237, Short.MAX_VALUE))))
		);
		gl_panelAddition.setVerticalGroup(
			gl_panelAddition.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelAddition.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblsubtitle)
					.addGap(18)
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblInsertState)
						.addComponent(txtInsertState, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInsertCity)
						.addComponent(txtInsertCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panelAddition.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelAddition.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelAddition.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(lblInterestingPlace)
								.addComponent(txtInsertPlace, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panelAddition.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panelAddition.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtMostInteresting, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblRanking))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelAddition.setLayout(gl_panelAddition);
		
		JLabel lblTitle = new JLabel("M.E.T Navigation System");
		panelTitle.add(lblTitle);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
		frame.getContentPane().setLayout(groupLayout);

	}
}

package gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kpsMail.Route;
/**
 * Class to represent a route specific statistics panel
 * @author treachmich
 *
 */
public class RouteSpecificStatisticsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel routeLabel;
	private JLabel totalVolumeLabel;
	private JLabel totalWeightLabel;
	private JLabel totalItemsLabel;
	private JLabel averageDeliverTimeLabel;
	private JComboBox routesComboBox;
	private JTextField totalVolumeTextField;
	private JTextField totalWeightTextField;
	private JTextField totalItemsTextField;
	private JTextField averageDeliverTimeTextField;
	
	/**
	 * constructs a route specific statistics panel
	 */
	public RouteSpecificStatisticsPanel(){
		setLayout(new GridLayout(0,1,0,5));
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Route Specific Statistics"));
		routeLabel = new JLabel("Route");
		totalVolumeLabel = new JLabel("Total Volume Of Mail");
		totalWeightLabel = new JLabel("Total Weight Of Mail");
		totalItemsLabel = new JLabel("Total Amount Of Mail Items");
		averageDeliverTimeLabel = new JLabel("Current Average Delivery Time");
		routesComboBox = new JComboBox();
		totalVolumeTextField = new JTextField("0.0");
		totalWeightTextField = new JTextField("0.0");
		totalItemsTextField = new JTextField("0");
		averageDeliverTimeTextField = new JTextField("0.0");
		disableTextBoxs();
		addComponents();
	}
	/**
	 * Disable the text boxes so they are not editable
	 */
	private void disableTextBoxs() {
		totalItemsTextField.setEditable(false);
		totalWeightTextField.setEditable(false);
		totalVolumeTextField.setEditable(false);
		averageDeliverTimeTextField.setEditable(false);
	}
	/**
	 * Add all the components to the panel
	 */
	public void addComponents(){
		add(routeLabel);
		add(routesComboBox);
		add(totalVolumeLabel);
		add(totalVolumeTextField);
		add(totalWeightLabel);
		add(totalWeightTextField);
		add(totalItemsLabel);
		add(totalItemsTextField);
		add(averageDeliverTimeLabel);
		add(averageDeliverTimeTextField);
	}
	/**
	 * Populate the combo boxes with the available routes
	 * @param routes
	 */
	public void populateComboBox(List<Route> routes){
		routesComboBox.removeAllItems();
		for(Route r : routes){
			routesComboBox.addItem(r);
		}
		routesComboBox.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				DecimalFormat dFormat = new DecimalFormat("0.00");
				Route r = (Route)routesComboBox.getSelectedItem();
				if(r != null){
					double totalWeight = ((Route)routesComboBox.getSelectedItem()).getTotalWeight();
					double totalVolume = ((Route)routesComboBox.getSelectedItem()).getTotalVolume();
					int amountOfMail = ((Route)routesComboBox.getSelectedItem()).getAmountOfMail();
					double aveDeliverytime = ((Route)routesComboBox.getSelectedItem()).getAverageDeliveryTime();
				
					if(routesComboBox.getSelectedItem() != null){
						setValues(dFormat.format(totalWeight)+ "",dFormat.format(totalVolume)+ "", amountOfMail+ "", dFormat.format(aveDeliverytime) + "");
					}
				}
			}
		});
		routesComboBox.setSelectedIndex(0);
	}
	/**
	 * Sets the values in the panel
	 * @param totalWeightOfMail
	 * @param totalVolumeOfMail
	 * @param totalAmountOfMailItems
	 * @param currentAveDeliveryTime
	 */
	public void setValues(String totalWeightOfMail,String totalVolumeOfMail,String totalAmountOfMailItems,String currentAveDeliveryTime){
		totalWeightTextField.setText((totalWeightOfMail));
		totalVolumeTextField.setText((totalVolumeOfMail));
		totalItemsTextField.setText((totalAmountOfMailItems));
		averageDeliverTimeTextField.setText((currentAveDeliveryTime));
	}
	
}

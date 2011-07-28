package gui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kpsMail.CustomerPriceUpdate;
import kpsMail.KPS;
import kpsMail.NewZealand;
import kpsMail.Route;
import kpsMail.Type;


/**
 * Class to represent a Customer price update dialog
 * @author treachmich
 *
 */
public class CustomerPriceUpdateDialog extends JDialog {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel routeLabel;
	private JLabel weightLabel;
	private JLabel volumeLabel;
	private JLabel destinationLabel;
	private JComboBox originComboBox;
	private JComboBox destinationComboBox;
	private JComboBox typeComboBox;
	private JTextField weightField;
	private JTextField volumeField; 
	private JButton updateButton;
	private JButton cancelButton;
	private KPS kps;
	private KPSMainFrame mainframe;
	private final int WIDTH = 315;
	private final int HEIGHT = 295;
	private Component typeLabel;
	
	/**
	 * Constructs a customer price update dialog
	 * @param kps
	 * @param mainframe
	 */
	public CustomerPriceUpdateDialog(KPS kps, KPSMainFrame mainframe){
		this.setTitle("Customer Price Update");
		setLayout(null);
		this.kps = kps;
		this.mainframe = mainframe;
		weightField = new JTextField();
		volumeField = new JTextField();
		setupComboBoxes();
		setupLabelsAndTextBoxes();
		setupButtons();
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	/**
	 * Gets the origin list
	 * @return origin list
	 */
	private List<String> getOriginList(){
		boolean specialCaseNewZealandFlag = false;
		List<Route> routes = kps.getRouteManager().getRoutes();
		List<String> originStrings = new ArrayList<String>();
		for(Route r :routes){
			if(NewZealand.contains(r.origin) && !specialCaseNewZealandFlag){
				originStrings.add("New Zealand");
				specialCaseNewZealandFlag = true;
			}
			else if(NewZealand.contains(r.origin)){
				// do nothing we have taken care of this case
			}
			else if(!originStrings.contains(r.origin)) {
				originStrings.add(r.origin);
			}
		}
		return originStrings;
	}
	
	/**
	 * Gets the destionation list from the origin
	 * @param origin
	 * @return destination list
	 */
	private List<String> getDestinationList(String origin){
		List<Route> routes = kps.getRouteManager().getRoutes();
		List<String> destinationStrings = new ArrayList<String>();
		for(Route r : routes){
			if(origin.equalsIgnoreCase("New Zealand")){
				if(NewZealand.contains(r.origin) && NewZealand.contains(r.destination) && !destinationStrings.contains("New Zealand")){
					destinationStrings.add("New Zealand");
				}
				else if(NewZealand.contains(r.origin) && !NewZealand.contains(r.destination) && !destinationStrings.contains(r.destination)) {
					destinationStrings.add(r.destination);
				}
			}
			else {
				if(r.origin.equalsIgnoreCase(origin) && NewZealand.contains(r.destination) && !destinationStrings.contains("New Zealand")){
					destinationStrings.add("New Zealand");
				}
				else if(r.origin.equalsIgnoreCase(origin) && !NewZealand.contains(r.destination) && !destinationStrings.contains(r.destination)) {
					destinationStrings.add(r.destination);
				}
			}
		}
		return destinationStrings;
	}
	/**
	 * Sets up the buttons
	 */
	private void setupButtons() {
		updateButton = new JButton("Update");
		updateButton.setBounds(WIDTH/11,(volumeField.getY() + volumeField.getHeight()) + KPSButtonBox.GAP,KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		
		updateButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				String origin = (String)originComboBox.getSelectedItem();
				String destination = (String)destinationComboBox.getSelectedItem();
				String typeString = (String) typeComboBox.getSelectedItem();
				Type type = null;
				double weight = 0;
				double volume = 0;
				if(typeString.equalsIgnoreCase("Air")){
					type = Type.Air;
				}
				else {
					type = Type.Land;
				}
				try{
					weight = Double.valueOf(weightField.getText());
					volume = Double.valueOf(volumeField.getText());
					if(weight >= 0 && volume >= 0){
						if(origin == null || destination == null){
							throw new BadInputException("Can not use empty origin or destination");
						}
						CustomerPriceUpdate cpu = new CustomerPriceUpdate(destination,origin, kps.getRouteManager().translateTransportType(origin, destination,type ), weight, volume);
						kps.processEvent(cpu);
						mainframe.updatePanels();
						mainframe.updateStatsBox();
						JOptionPane.showMessageDialog(null, "Customer price has been updated!");
						resetFields();
					}
					else{
						throw new BadInputException("Can not use negative numbers");
					}
				}
				catch(BadInputException ex){
					JOptionPane.showMessageDialog(null, "Can not use negative numbers for weight or height.", "KPS Mail - Input Error!", JOptionPane.ERROR_MESSAGE);
					weightField.setText("");
					volumeField.setText("");
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Can not have non-number values for weight or volume.", "KPS Mail - Input Error!", JOptionPane.ERROR_MESSAGE);
					weightField.setText("");
					volumeField.setText("");
				}
				
			}

			private void resetFields() {
				originComboBox.setSelectedIndex(0);
				destinationComboBox.setSelectedIndex(0);
				typeComboBox.setSelectedIndex(0);
				weightField.setText("");
				volumeField.setText("");
			}
			
		});
		
		add(updateButton);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerPriceUpdateDialog.this.dispose();
			}
			
		});
		
		cancelButton.setBounds((updateButton.getX()+KPSButtonBox.BUTTON_WIDTH) + (KPSButtonBox.GAP * 3),updateButton.getY(),KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		add(cancelButton);
		
	}
	/**
	 * Sets up the labels and text boxes on the dialog
	 */
	private void setupLabelsAndTextBoxes() {
		routeLabel = new JLabel("Origin:");
		routeLabel.setBounds(KPSButtonBox.GAP,0,KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP*4);
		add(routeLabel);
		
		destinationLabel = new JLabel("Destination:");
		destinationLabel.setBounds(KPSButtonBox.GAP,originComboBox.getY() + originComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(destinationLabel);
		
		typeLabel = new JLabel("Type:");
		typeLabel.setBounds(KPSButtonBox.GAP,destinationComboBox.getY() + destinationComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(typeLabel);
		
		weightLabel = new JLabel("Weight:");
		weightLabel.setBounds(KPSButtonBox.GAP, typeComboBox.getY() + typeComboBox.getHeight(), KPSButtonBox.LABEL_WIDTH, KPSButtonBox.GAP * 4);
		add(weightLabel);
		
		weightField.setBounds(KPSButtonBox.GAP, (weightLabel.getY() + weightLabel.getHeight()),KPSButtonBox.COMBO_BOX_WIDTH , KPSButtonBox.COMBO_BOX_HEIGHT);
		add(weightField);
		
		volumeLabel = new JLabel("Volume:");
		volumeLabel.setBounds(KPSButtonBox.GAP, weightField.getY() + weightField.getHeight(), KPSButtonBox.LABEL_WIDTH, KPSButtonBox.GAP * 4);
		add(volumeLabel);
		
		volumeField.setBounds(KPSButtonBox.GAP, (volumeLabel.getY() + volumeLabel.getHeight()),KPSButtonBox.COMBO_BOX_WIDTH , KPSButtonBox.COMBO_BOX_HEIGHT);
		add(volumeField);
	
	}
	/**
	 * Sets up all the combo boxes
	 */
	private void setupComboBoxes() {
		originComboBox = new JComboBox(getOriginList().toArray());
		destinationComboBox = new JComboBox();
		originComboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				destinationComboBox.removeAllItems();
				for(String dest : getDestinationList((String)originComboBox.getSelectedItem())){
						destinationComboBox.addItem(dest);
				}
			}
			
		});

		originComboBox.setBounds(KPSButtonBox.GAP,KPSButtonBox.GAP * 4,KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		// initialise the combo box
		if(originComboBox.getSelectedItem() != null){
			originComboBox.setSelectedIndex(0);
		}
		destinationComboBox.setBounds(KPSButtonBox.GAP,(originComboBox.getY() + originComboBox.getHeight()) + (KPSButtonBox.GAP * 4),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		
		typeComboBox = new JComboBox(new String[]{"Air","Standard"});
		typeComboBox.setBounds(KPSButtonBox.GAP,(destinationComboBox.getY() + destinationComboBox.getHeight()) + (KPSButtonBox.GAP * 4),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(typeComboBox);
		
		add(originComboBox);
		add(destinationComboBox);
	}
		
}

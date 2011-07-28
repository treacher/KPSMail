package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kpsMail.Day;
import kpsMail.KPS;
import kpsMail.Type;
import kpsMail.TransportCostUpdate;

/**
 * Class to represent an Add Route Dialog
 * @author treachmich
 *
 */
public class AddRouteDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel companyLabel;
	private JLabel destinationLabel;
	private JLabel originLabel;
	private JLabel typeLabel;
	private JLabel weightCostLabel;
	private JLabel maxWeightLabel;
	private JLabel volumeCostLabel;
	private JLabel maxVolumeLabel;
	private JLabel durationLabel;
	private JLabel frequencyLabel;
	private JLabel dayLabel;
	private JTextField companyTextField;
	private JTextField destinationTextField;
	private JTextField originTextField;
	private JTextField weightCostTextField;
	private JTextField maxWeightTextField;
	private JTextField volumeCostTextField;
	private JTextField maxVolumeTextField;
	private JTextField durationTextField;
	private JTextField frequencyTextField;
	private JComboBox typeComboBox;
	private JComboBox dayComboBox;
	private KPS kps;
	private KPSMainFrame mainframe;
	private final int WIDTH = 315;
	private final int HEIGHT = 565;
	private JButton updateButton;
	private JButton cancelButton;
	
	
	/**
	 * Constructs an Add Route Dialog
	 * @param kps
	 * @param mainframe
	 */
	public AddRouteDialog(KPS kps,KPSMainFrame mainframe){
		this.setTitle("Add Route");
		this.kps = kps;
		this.mainframe = mainframe;
		setLayout(null);
		this.setSize(WIDTH,HEIGHT);
		setupComponents();
		setupButtons();
		setupListeners();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	/**
	 * Sets up the components to go on the dialog
	 */
	public void setupComponents(){
		companyLabel = new JLabel("Transport Firm:");
		companyLabel.setBounds(KPSButtonBox.GAP,0,KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(companyLabel);
		
		companyTextField = new JTextField();
		companyTextField.setBounds(KPSButtonBox.GAP,companyLabel.getY() + companyLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(companyTextField);
		
		originLabel = new JLabel("Origin:");
		originLabel.setBounds(KPSButtonBox.GAP,companyTextField.getY() + companyTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(originLabel);
		
		originTextField = new JTextField();
		originTextField.setBounds(KPSButtonBox.GAP,originLabel.getY() + originLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(originTextField);
		
		destinationLabel = new JLabel("Destination:");
		destinationLabel.setBounds(KPSButtonBox.GAP,originTextField.getY() + originTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(destinationLabel);
		
		destinationTextField = new JTextField();
		destinationTextField.setBounds(KPSButtonBox.GAP,destinationLabel.getY() + destinationLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(destinationTextField);
		
		typeLabel = new JLabel("Type:");
		typeLabel.setBounds(KPSButtonBox.GAP,destinationTextField.getY() + destinationTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(typeLabel);
		
		typeComboBox = new JComboBox(Type.values());
		typeComboBox.setBounds(KPSButtonBox.GAP,typeLabel.getY() + typeLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(typeComboBox);
		
		weightCostLabel = new JLabel("Weight Cost:");
		weightCostLabel.setBounds(KPSButtonBox.GAP,typeComboBox.getY() + typeComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(weightCostLabel);
		
		weightCostTextField = new JTextField();
		weightCostTextField.setBounds(KPSButtonBox.GAP,weightCostLabel.getY() + weightCostLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(weightCostTextField);
		
		maxWeightLabel = new JLabel("Max weight:");
		maxWeightLabel.setBounds(KPSButtonBox.GAP,weightCostTextField.getY() + weightCostTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(maxWeightLabel);
		
		maxWeightTextField = new JTextField();
		maxWeightTextField.setBounds(KPSButtonBox.GAP,maxWeightLabel.getY() + maxWeightLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(maxWeightTextField);
		
		volumeCostLabel = new JLabel("Volume Cost:");
		volumeCostLabel.setBounds(KPSButtonBox.GAP,maxWeightTextField.getY() + maxWeightTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(volumeCostLabel);
		
		volumeCostTextField = new JTextField();
		volumeCostTextField.setBounds(KPSButtonBox.GAP,volumeCostLabel.getY() + volumeCostLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(volumeCostTextField);
		
		maxVolumeLabel = new JLabel("Max Volume:");
		maxVolumeLabel.setBounds(KPSButtonBox.GAP,volumeCostTextField.getY() + volumeCostTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(maxVolumeLabel);
		
		maxVolumeTextField = new JTextField();
		maxVolumeTextField.setBounds(KPSButtonBox.GAP,maxVolumeLabel.getY() + maxVolumeLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(maxVolumeTextField);
		
		durationLabel = new JLabel("Duration:");
		durationLabel.setBounds(KPSButtonBox.GAP,maxVolumeTextField.getY() + maxVolumeTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(durationLabel);
		
		durationTextField = new JTextField();
		durationTextField.setBounds(KPSButtonBox.GAP,durationLabel.getY() + durationLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(durationTextField);
		
		frequencyLabel = new JLabel("Frequency:");
		frequencyLabel.setBounds(KPSButtonBox.GAP,durationTextField.getY() + durationTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(frequencyLabel);
		
		frequencyTextField = new JTextField();
		frequencyTextField.setBounds(KPSButtonBox.GAP,frequencyLabel.getY() + frequencyLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(frequencyTextField);
		
		dayLabel = new JLabel("Day:");
		dayLabel.setBounds(KPSButtonBox.GAP,frequencyTextField.getY() + frequencyTextField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(dayLabel);
		
		dayComboBox = new JComboBox(Day.values());
		dayComboBox.setBounds(KPSButtonBox.GAP,dayLabel.getY() + dayLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(dayComboBox);
	
	}
	/**
	 * Sets up the buttons on the dialog
	 */
	public void setupButtons(){
			
			updateButton = new JButton("Add Route");
			updateButton.setBounds(WIDTH/11,(dayComboBox.getY() + dayComboBox.getHeight()) + KPSButtonBox.GAP,KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
			add(updateButton);
			
			cancelButton = new JButton("Cancel");
			cancelButton.setBounds((updateButton.getX()+KPSButtonBox.BUTTON_WIDTH) + (KPSButtonBox.GAP * 3),updateButton.getY(),KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
			add(cancelButton);
			
	}
	/**
	 * Clears all the number fields
	 */
	public void clearNumberFields(){
		weightCostTextField.setText("");
		maxWeightTextField.setText("");
		volumeCostTextField.setText("");
		maxVolumeTextField.setText("");
		durationTextField.setText("");
		frequencyTextField.setText("");
	}
	/**
	 * Sets up the listeners
	 */
	public void setupListeners(){
		updateButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				double weightCost = 0, maxWeight = 0,volumeCost = 0,maxVolume = 0;
				int duration = 0,frequency = 0;
				try{
					// get  values from text fields
					String company = companyTextField.getText();
					String origin = originTextField.getText();
					String destination = destinationTextField.getText();
					Type type = (Type)typeComboBox.getSelectedItem();
					weightCost = Double.valueOf(weightCostTextField.getText());
					maxWeight = Double.valueOf(maxWeightTextField.getText());
					volumeCost = Double.valueOf(volumeCostTextField.getText());
					maxVolume = Double.valueOf(maxVolumeTextField.getText());
					duration = Integer.valueOf(durationTextField.getText());
					frequency = Integer.valueOf(frequencyTextField.getText());
					Day day = (Day)dayComboBox.getSelectedItem();
					
					// check they are all positive values
					if(weightCost >= 0 && maxWeight >0 && volumeCost >= 0 && maxVolume > 0 && duration > 0 && frequency > 0){
						TransportCostUpdate tcu = new TransportCostUpdate(company, destination, origin, type, weightCost, maxWeight, volumeCost, maxVolume, duration, frequency, day);
						kps.processEvent(tcu);
						mainframe.updatePanels();
						mainframe.updateStatsBox();
						kps.saveEvents();
						JOptionPane.showMessageDialog(null, "Route has been added to the KPS mail system.");
						resetFields();
					}
					else {
						throw new BadInputException("Can not use negative numbers in fields related to volume, weight,duration and frequency.");
					}
				}
				catch(BadInputException ex){
					JOptionPane.showMessageDialog(null,ex.toString(), "KPS Mail - Input Error!", JOptionPane.ERROR_MESSAGE);
					clearNumberFields();
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Can not have non-number values in fields related to volume, weight,duration and frequency.", "KPS Mail - Input Error!", JOptionPane.ERROR_MESSAGE);
					clearNumberFields();
				}
				
				
			}
			/**
			 * Resets all the fields
			 */
			private void resetFields() {
				companyTextField.setText("");
				destinationTextField.setText("");
				originTextField.setText("");
				weightCostTextField.setText("");
				maxWeightTextField.setText("");
				volumeCostTextField.setText("");
				maxVolumeTextField.setText("");
				durationTextField.setText("");
				frequencyTextField.setText("");
				typeComboBox.setSelectedIndex(0);
				dayComboBox.setSelectedIndex(0);
			}
			
		});
		
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AddRouteDialog.this.dispose();
			}
			
		});
	}

	

}

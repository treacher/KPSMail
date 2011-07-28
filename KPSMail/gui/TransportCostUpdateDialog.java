package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kpsMail.KPS;
import kpsMail.Route;
import kpsMail.TransportCostUpdate;
import kpsMail.TransportFirm;
/**
 * Class to represent the transport cost update dialog
 * @author treachmich
 *
 */
public class TransportCostUpdateDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel routeLabel;
	private JLabel transportFirmLabel;
	private JLabel weightLabel;
	private JLabel volumeLabel;
	private JComboBox routeComboBox;
	private JComboBox transportFirmComboBox;
	private JTextField weightField;
	private JTextField volumeField;
	private JButton updateButton;
	private JButton cancelButton;
	private final int WIDTH = 315;
	private final int HEIGHT = 250;
	private KPS kps;
	private KPSMainFrame mainframe;
	/**
	 * Constructs a transport cost update dialog
	 * @param kps
	 * @param mainframe
	 */
	public TransportCostUpdateDialog(KPS kps, KPSMainFrame mainframe){
		this.setTitle("Transport Firm Cost Update");
		this.kps = kps;
		this.mainframe = mainframe;
		setLayout(null);
		this.setSize(WIDTH,HEIGHT);
		setupComboBoxes();
		setupTextFields();
		setupButtons();
		setupListeners();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	/**
	 * Sets up the buttons
	 */
	private void setupButtons(){
		
		updateButton = new JButton("Update");
		updateButton.setBounds(WIDTH/11,(volumeField.getY() + volumeField.getHeight()) + KPSButtonBox.GAP,KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		add(updateButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds((updateButton.getX()+KPSButtonBox.BUTTON_WIDTH) + (KPSButtonBox.GAP * 3),updateButton.getY(),KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		add(cancelButton);
		
	}
	/**
	 * Sets up the text fields
	 */
	private void setupTextFields(){
		
		weightLabel = new JLabel("Weight:");
		weightLabel.setBounds(KPSButtonBox.GAP,transportFirmComboBox.getY() + transportFirmComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP*4);
		add(weightLabel);
		
		weightField = new JTextField();
		weightField.setBounds(KPSButtonBox.GAP,weightLabel.getY() + weightLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(weightField);
		
		volumeLabel = new JLabel("Volume:");
		volumeLabel.setBounds(KPSButtonBox.GAP,weightField.getY() + weightField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(volumeLabel);
		
		volumeField = new JTextField();
		volumeField.setBounds(KPSButtonBox.GAP,volumeLabel.getY() + volumeLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(volumeField);
		
	}
	/**
	 * Sets up the combo boxes
	 */
	private void setupComboBoxes(){
		
		routeLabel = new JLabel("Route:");
		routeLabel.setBounds(KPSButtonBox.GAP,0,KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(routeLabel);
		
		routeComboBox = new JComboBox(kps.getRouteManager().getRoutes().toArray());
		routeComboBox.setBounds(KPSButtonBox.GAP,routeLabel.getY() + routeLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(routeComboBox);
		
		transportFirmLabel = new JLabel("Transport Firm:");
		transportFirmLabel.setBounds(KPSButtonBox.GAP,routeComboBox.getY() + routeComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(transportFirmLabel);
		
		transportFirmComboBox = new JComboBox();
		transportFirmComboBox.setBounds(KPSButtonBox.GAP,transportFirmLabel.getY() + transportFirmLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(transportFirmComboBox);
		
	}
	/**
	 * Sets up the listeners
	 */
	private void setupListeners(){
		
		routeComboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				transportFirmComboBox.removeAllItems();
				for(TransportFirm o : ((Route)routeComboBox.getSelectedItem()).getTransportFirms()){
					transportFirmComboBox.addItem(o);
				}	
			}
			
		});
		
		transportFirmComboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(((TransportFirm)transportFirmComboBox.getSelectedItem()) != null){
					DecimalFormat dFormat = new DecimalFormat("0.00");
					double weight = ((TransportFirm)transportFirmComboBox.getSelectedItem()).getWeightCost();
					double volume = ((TransportFirm)transportFirmComboBox.getSelectedItem()).getVolumeCost();
					weightField.setText(dFormat.format(weight));
					volumeField.setText(dFormat.format(volume));
				}
				else{
					weightField.setText("");
					volumeField.setText("");
				}
			}
			
			
		});
		// initialise the selected value.
		if(routeComboBox.getItemAt(0)!= null){
			routeComboBox.setSelectedIndex(0); // triggers event
		}
		if(transportFirmComboBox.getItemAt(0)!= null){
			transportFirmComboBox.setSelectedIndex(0); // trigger event
		}
		
		updateButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				double weight;
				double volume;
			
				try{
					weight = Double.parseDouble(weightField.getText());
					volume = Double.parseDouble(volumeField.getText());
					if(weight >= 0 && volume >= 0){
						if(routeComboBox.getSelectedItem()==null || transportFirmComboBox.getSelectedItem() == null){
							throw new BadInputException("Can not use non existant route or transport firm");
						}
						TransportCostUpdate tcu = new TransportCostUpdate((Route)routeComboBox.getSelectedItem(), (TransportFirm)transportFirmComboBox.getSelectedItem(), weight, volume);
						kps.processEvent(tcu);
						mainframe.updatePanels();
						mainframe.updateStatsBox();
						kps.saveEvents();
						JOptionPane.showMessageDialog(null, "Transport Cost has been updated");
						resetFields();
					}
					else{
						throw new BadInputException("Can not use negative numbers");
					}
				}
				catch(BadInputException ex){
					JOptionPane.showMessageDialog(null, ex.toString(), "KPS Mail - Input Error!", JOptionPane.ERROR_MESSAGE);
					weightField.setText("");
					volumeField.setText("");
				}
				catch(NumberFormatException ex){
					JOptionPane.showMessageDialog(null, "Can not have non-number values for weight or volume.", "KPS Mail - Input Error!", JOptionPane.ERROR_MESSAGE);
					weightField.setText("");
					volumeField.setText("");
				}
				
			}
			/**
			 * Resets the fields
			 */
			private void resetFields() {
				if(routeComboBox.getItemAt(0)!= null)
					routeComboBox.setSelectedIndex(0);
				if(transportFirmComboBox.getItemAt(0) != null)
					transportFirmComboBox.setSelectedIndex(0);
				weightField.setText("");
				volumeField.setText("");
			}
			
		});
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				TransportCostUpdateDialog.this.dispose();
			}
			
		});
		
	}

}

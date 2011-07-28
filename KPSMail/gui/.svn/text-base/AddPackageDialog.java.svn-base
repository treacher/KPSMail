package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import kpsMail.KPS;
import kpsMail.MailDelivery;
import kpsMail.DayNames;
/**
 * Class to represent an add package dialog
 * @author treachmich
 *
 */
public class AddPackageDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KPS kps;
	private KPSMainFrame mainframe;
	private JLabel originLabel;
	private JLabel destinationLabel;
	private JLabel typeLabel;
	private JLabel weightLabel;
	private JLabel volumeLabel;
	private JLabel dayLabel;
	private JComboBox originComboBox;
	private JComboBox destinationComboBox;
	private JComboBox priorityComboBox;
	private JComboBox dayComboBox;
	private JTextField weightField;
	private JTextField volumeField;
	private JButton updateButton;
	private JButton cancelButton;
	private final int WIDTH = 315;
	private final int HEIGHT = 340;
	/**
	 * Creates an Add Package Dialog
	 * @param kps
	 * @param mainframe
	 */
	public AddPackageDialog(KPS kps,KPSMainFrame mainframe){
		this.setTitle("Add Package");
		this.kps = kps;
		this.mainframe = mainframe;
		setLayout(null);
		this.setSize(WIDTH,HEIGHT);
		setupComboBoxSetOne();
		setupTextFields();
		setupComboBoxSetTwo();
		setupButtons();
		setupListeners();
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	/**
	 * Sets up the button and combo box listeners
	 */
	private void setupListeners() {
		updateButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				double weight = 0;
				double volume = 0;
				String destination = (String)destinationComboBox.getSelectedItem();
				String origin = (String)originComboBox.getSelectedItem();
				String type = (String)priorityComboBox.getSelectedItem();
				DayNames day = (DayNames) dayComboBox.getSelectedItem();
				try{
					weight = Double.parseDouble(weightField.getText());
					volume = Double.parseDouble(volumeField.getText());
					if(volume >= 0 && weight >= 0){
						if(destination == null || origin == null){
							throw new BadInputException("Can not deliver to a destination or origin that does not exist.");
						}
						MailDelivery md = new MailDelivery(destination,origin,type, weight, volume,day.toString());
						if(!kps.processEvent(md)){
							throw new BadInputException("KPS can not do this delivery. We are sorry for the inconvenience.");
						}
						mainframe.updatePanels();
						mainframe.updateStatsBox();
						kps.saveEvents();
						JOptionPane.showMessageDialog(null, "You have successfully added the package to the KPS mail system");
						resetFields();
					}
					else{
						throw new BadInputException("Can not use negative numbers in the weight or volume fields");
					}
				}
				catch(BadInputException ex){
					JOptionPane.showMessageDialog(null,ex.toString(), "KPS Mail - Input Error!", JOptionPane.ERROR_MESSAGE);
					// quick hack
					if(!ex.toString().substring(0, 3).equals("KPS")){
						weightField.setText("");
						volumeField.setText("");
					}
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
				if(originComboBox.getItemAt(0) != null)
					originComboBox.setSelectedIndex(0);
				if(destinationComboBox.getItemAt(0) != null)
					destinationComboBox.setSelectedIndex(0);
				if(priorityComboBox.getItemAt(0) != null)
					priorityComboBox.setSelectedIndex(0);
				
				dayComboBox.setSelectedIndex(0);
				weightField.setText("");
				volumeField.setText("");
			}

			
			
		});
		
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				AddPackageDialog.this.dispose();
			}
			
		});
	}


	/**
	 * Sets up the buttons
	 */
	private void setupButtons(){
		
		updateButton = new JButton("Add Package");
		updateButton.setBounds(WIDTH/11,(dayComboBox.getY() + dayComboBox.getHeight()) + KPSButtonBox.GAP,KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		add(updateButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setBounds((updateButton.getX()+KPSButtonBox.BUTTON_WIDTH) + (KPSButtonBox.GAP * 3),updateButton.getY(),KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		add(cancelButton);
		
	}


	/**
	 * Sets up the second lot of combo boxes
	 */
	private void setupComboBoxSetTwo() {
		
		dayLabel = new JLabel("Day:");
		dayLabel.setBounds(KPSButtonBox.GAP,volumeField.getY() + volumeField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(dayLabel);
		
		dayComboBox = new JComboBox(DayNames.values());
		dayComboBox.setBounds(KPSButtonBox.GAP,dayLabel.getY() + dayLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(dayComboBox);
	}


	/**
	 * Sets up all the text fields
	 */
	private void setupTextFields() {
		
		weightLabel = new JLabel("Weight:");
		weightLabel.setBounds(KPSButtonBox.GAP,priorityComboBox.getY() + priorityComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(weightLabel);
		
		weightField = new JTextField();
		weightField.setBounds(KPSButtonBox.GAP,weightLabel.getY()+weightLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(weightField);
		
		volumeLabel = new JLabel("Volume:");
		volumeLabel.setBounds(KPSButtonBox.GAP,weightField.getY() + weightField.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(volumeLabel);
		
		volumeField = new JTextField();
		volumeField.setBounds(KPSButtonBox.GAP,volumeLabel.getY()+volumeLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(volumeField);
		
	}


	/**
	 * Sets up the first set of combo boxes
	 */
	private void setupComboBoxSetOne() {
		
		originLabel = new JLabel("Origin:");
		originLabel.setBounds(KPSButtonBox.GAP,0,KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(originLabel);
		
		originComboBox = new JComboBox(kps.getRouteManager().getCityNames().toArray());
		originComboBox.setBounds(KPSButtonBox.GAP,originLabel.getY()+originLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(originComboBox);
		
		destinationLabel = new JLabel("Destination:");
		destinationLabel.setBounds(KPSButtonBox.GAP,originComboBox.getY() + originComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(destinationLabel);
		
		destinationComboBox = new JComboBox(kps.getRouteManager().getCityNames().toArray());
		destinationComboBox.setBounds(KPSButtonBox.GAP,destinationLabel.getY() + destinationLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(destinationComboBox);
		
		typeLabel = new JLabel("Type:");
		typeLabel.setBounds(KPSButtonBox.GAP,destinationComboBox.getY() + destinationComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(typeLabel);
		
		priorityComboBox = new JComboBox(new String[]{"Air","Standard"});
		priorityComboBox.setBounds(KPSButtonBox.GAP,typeLabel.getY() + typeLabel.getHeight(),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(priorityComboBox);
		
	}
	
}

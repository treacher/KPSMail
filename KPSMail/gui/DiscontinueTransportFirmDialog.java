package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import kpsMail.KPS;
import kpsMail.Route;
import kpsMail.TransportDiscontinued;
import kpsMail.TransportFirm;
/**
 * Class to represent a discontinue transport firm dialog
 * @author treachmich
 *
 */
public class DiscontinueTransportFirmDialog extends JDialog{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox routeComboBox;
	private JComboBox transportFirmComboBox;
	private KPS kps;
	private JButton discontinueButton;
	private JButton cancelButton;
	private KPSMainFrame mainframe;
	private JLabel routeLabel;
	private JLabel transportFirmLabel;

	private final int WIDTH = 315;
	private final int HEIGHT = 160;

	/**
	 * Constructs a discontinue transport firm dialog
	 * @param kps
	 * @param mainframe
	 */
	public DiscontinueTransportFirmDialog(KPS kps ,KPSMainFrame mainframe){
		this.mainframe = mainframe;
		this.setTitle("Discontinue Transport Firm");
		this.setLayout(null);
		this.kps = kps;
		setupComboBoxes();
		setupButtons();
		setupLabels();
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setModal(true);
		this.setVisible(true);
	}
	/**
	 * Sets up the labels
	 */
	private void setupLabels(){
		routeLabel = new JLabel("Route:");
		routeLabel.setBounds(KPSButtonBox.GAP,0,KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(routeLabel);
		transportFirmLabel = new JLabel("Transport Firm:");
		transportFirmLabel.setBounds(KPSButtonBox.GAP,routeComboBox.getY() + routeComboBox.getHeight(),KPSButtonBox.LABEL_WIDTH,KPSButtonBox.GAP * 4);
		add(transportFirmLabel);
	}
	/**
	 * Sets up the combo boxes
	 */
	private void setupComboBoxes() {
		routeComboBox = new JComboBox(kps.getRouteManager().getRoutes().toArray());
		transportFirmComboBox = new JComboBox();
		routeComboBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				transportFirmComboBox.removeAllItems();
				if(!routeComboBox.getSelectedItem().equals("")){
					for(TransportFirm o : ((Route)routeComboBox.getSelectedItem()).getTransportFirms()){
						transportFirmComboBox.addItem(o);
					}
				}	
				
			}
		});
		//initialise selection
		if(routeComboBox.getItemAt(0) != null){
			routeComboBox.setSelectedIndex(0);
		}
		routeComboBox.setBounds(KPSButtonBox.GAP, KPSButtonBox.GAP * 4, KPSButtonBox.COMBO_BOX_WIDTH, KPSButtonBox.COMBO_BOX_HEIGHT);
		transportFirmComboBox.setBounds(KPSButtonBox.GAP,(routeComboBox.getY() + routeComboBox.getHeight()) + (KPSButtonBox.GAP * 4),KPSButtonBox.COMBO_BOX_WIDTH,KPSButtonBox.COMBO_BOX_HEIGHT);
		add(routeComboBox);
		add(transportFirmComboBox);
	}
	/**
	 * set up the buttons
	 */
	private void setupButtons(){
		discontinueButton = new JButton("Discontinue");
		discontinueButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(routeComboBox.getSelectedItem() == null || transportFirmComboBox.getSelectedItem() == null){
					JOptionPane.showMessageDialog(null, "Can not discontinue a non existant transport firm");
					return;
				}
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to discontinue " + ((TransportFirm)transportFirmComboBox.getSelectedItem()).getCompany(),"Confirm discontinue",JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION){
					
					TransportDiscontinued td = new TransportDiscontinued((Route)routeComboBox.getSelectedItem(),(TransportFirm)transportFirmComboBox.getSelectedItem());
					kps.processEvent(td);
					mainframe.updatePanels();
					mainframe.updateStatsBox();
					kps.saveEvents();
					JOptionPane.showMessageDialog(null, "Transport firm has been discontinued.");
					if(routeComboBox.getItemAt(0) != null)
						routeComboBox.setSelectedIndex(0); // reset combo boxes
				}
			}
		});
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				DiscontinueTransportFirmDialog.this.dispose();				
			}
			
		});
		discontinueButton.setBounds(WIDTH/11,(transportFirmComboBox.getY() + transportFirmComboBox.getHeight()) + KPSButtonBox.GAP,KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		cancelButton.setBounds((discontinueButton.getX()+KPSButtonBox.BUTTON_WIDTH) + (KPSButtonBox.GAP * 3),discontinueButton.getY(),KPSButtonBox.BUTTON_WIDTH,KPSButtonBox.BUTTON_HEIGHT);
		add(discontinueButton);
		add(cancelButton);
	}
}

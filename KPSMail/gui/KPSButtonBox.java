package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * class to represent the button box on the KPS frame
 * @author treachmich
 *
 */
public class KPSButtonBox extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton addRouteButton;
	private JButton editRoutePriceButton;
	private JButton editTransportCostButton;
	private JButton addPackageButton;
	private JButton viewStatsButton;
	private JButton discontinueTransportButton;
	private NavigationPanel parent;
	public static final int COMBO_BOX_HEIGHT = 25;
	public static final int COMBO_BOX_WIDTH = 300;
	public static final int BUTTON_WIDTH = 120;
	public static final int BUTTON_HEIGHT = 30;
	public static final int GAP = 5;
	public static final int LABEL_WIDTH = 100;

	/**
	 * Constructs a button box
	 * @param parent
	 */
	public KPSButtonBox(NavigationPanel parent){
		this.parent = parent;
		setLayout(new GridLayout(0,1));
		setupButtons();
	}

	/**
	 * Sets up the buttons the the button box
	 */
	public void setupButtons(){
		 //				//
		 //Add Route Button//
		//				  //
		addRouteButton = new JButton("Add Route");
		
		addRouteButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addRouteButton.doClick();
			}
		});
		
		addRouteButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddRouteDialog(parent.getParent().getKPS(),parent.getParent());
			}
		});
		
		//					  			//
		//Transport Discontinue Button //
		//							  //
		discontinueTransportButton = new JButton("Discontinue Transport");
		
		discontinueTransportButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				discontinueTransportButton.doClick();
			}
		});
		
		discontinueTransportButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				new DiscontinueTransportFirmDialog(parent.getParent().getKPS(),parent.getParent());
			}
		});
		
		  //				     //
		 //Customer Price Update//
		//				       //
		editRoutePriceButton = new JButton("Customer Price Update");
		// quick hack had issues with layout managers
		editRoutePriceButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				editRoutePriceButton.doClick();
			}
		});
		editRoutePriceButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new CustomerPriceUpdateDialog(parent.getParent().getKPS(), parent.getParent());
			}
		
		});
		
		  //				   //
		 //Edit Transport Cost//
		//				     //
		editTransportCostButton = new JButton("Edit Transport Cost");
		editTransportCostButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				editTransportCostButton.doClick();
			}
		});
		
		editTransportCostButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new TransportCostUpdateDialog(parent.getParent().getKPS(), parent.getParent());
			}
		});
			
		  //				 //
		 //Add PackageButton//
		//				   //
		addPackageButton = new JButton("Add Package");
		
		addPackageButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				addPackageButton.doClick();
			}
		});
		
		addPackageButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddPackageDialog(parent.getParent().getKPS(),parent.getParent());	
			}
			
		});
		
		  //					  //
		 //View Statistics Button//
		//				  		//
		
		viewStatsButton = new JButton("View Statistics");
		
		viewStatsButton.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				viewStatsButton.doClick();
			}
		});
		
		viewStatsButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(EventPanel.getSelectedEventPanel() != null){
					new StatisticsDialog(parent.getParent().getKPS(),parent.getParent().isManager(),EventPanel.getSelectedEventPanel().getIndex());
				}
				else {
					new StatisticsDialog(parent.getParent().getKPS(),parent.getParent().isManager(),EventPanel.getCount());
				}
				
			}
			
		});
		
		add(addPackageButton);
		add(addRouteButton);
		add(editRoutePriceButton);
		add(discontinueTransportButton);
		add(editTransportCostButton);
		add(viewStatsButton);
	}

}


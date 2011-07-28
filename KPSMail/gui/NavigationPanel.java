package gui;


import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
/**
 * Class to represent the navigation panel
 * @author treachmich
 *
 */
public class NavigationPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static int STATISTICS_BOX_HEIGHT = 100;
	public final static int NAVIGATION_PANEL_WIDTH = 200;
	public final static int BUTTON_BOX_WIDTH = 170;
	public final static int BUTTON_BOX_HEIGHT = 50;
	public final static int BUTTON_BOX_GAP = 20;
	private KPSButtonBox buttonBox;
	private KPSMainFrame parent;
	private StatisticsBox statsBox;
	/**
	 * Constructs a navigation pane this contains all the buttons to navigate through the application
	 */
	public NavigationPanel(KPSMainFrame parent){
		this.setLayout(null);
		this.parent = parent;
		this.setBounds(KPSMainFrame.WIDTH - NAVIGATION_PANEL_WIDTH ,0,NAVIGATION_PANEL_WIDTH,600-50);
		setupStatisticsBox();
		setupButtons();
	}
	/**
	 * Gets the parent frame
	 */
	public KPSMainFrame getParent(){
		return parent;
	}
	/**
	 * Sets up the statistic box which will show the current statistics
	 */
	public void setupStatisticsBox(){
		statsBox = new StatisticsBox(0,0,0);
		statsBox.setBounds(0,0,NavigationPanel.NAVIGATION_PANEL_WIDTH,NavigationPanel.STATISTICS_BOX_HEIGHT);
		add(statsBox);
		statsBox.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
	}
	
	public StatisticsBox getStatisticsBox(){
		return statsBox;
	}

	/**
	 * Sets up the GUI buttons
	 */
	public void setupButtons(){
		buttonBox = new KPSButtonBox(this);
		buttonBox.setBounds(0,STATISTICS_BOX_HEIGHT, getWidth(), getHeight() - STATISTICS_BOX_HEIGHT);
		add(buttonBox);
	}
}

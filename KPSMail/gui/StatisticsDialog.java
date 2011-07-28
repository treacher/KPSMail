package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import kpsMail.KPS;

/**
 * Class to represent the statistics frame
 * @author treachmich
 *
 */
public class StatisticsDialog extends JDialog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GeneralStatisticsPanel generalStatsPanel;
	private RouteSpecificStatisticsPanel routeSpecificStatisticsPanel;
	private JScrollPane criticalRouteScroller;
	private CriticalRoutePanel criticalRoutePanel;
	private KPS kps;
	private TraceButtonPanel traceButtonPanel;
	private JLabel positionLabel;
	private boolean isManager;
	public static final int PANEL_WIDTH = 350;
	public static final int GENERAL_STATS_PANEL_HEIGHT = 120;
	public static final int HEIGHT = 765;
	public static final int ROUTE_SPECIFIC_STATS_PANEL_HEIGHT = 300;
	public static final int TRACE_BUTTON_HEIGHT = 100;
	public static final int SEPERATOR_VALUE = 10;
	public static final String TITLE = "Statistics:";
	private int indexEndRange;
	
	/**
	 * Constructs a statistics frame with a given statistics object
	 * @param stats object
	 */
	public StatisticsDialog(KPS kps, boolean isManager,int indexEndRange){
		setTitle("Statistics");
		this.indexEndRange = indexEndRange;
		this.isManager = isManager;
		setLayout(null);
		this.kps = kps;
		setupPositionLabel();
		setupGeneralStatisticsPanel();
		setupRouteSpecificPanel();
		setupCriticalRoutePanel();
		setupButtons();
		setupPositionLabel(indexEndRange);
		
		//Don't setup if user is not a manager
		if(isManager){
			kps.recalculateStats(indexEndRange);
			updateStatistics();
			setupTraceButtonListeners();
		}
		this.setModal(true);
		this.setSize(PANEL_WIDTH + 10,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		routeSpecificStatisticsPanel.populateComboBox(kps.getStats().getRouteData());
		this.setVisible(true);
	}
	/**
	 * Update the current statistics
	 */
	public void updateStatistics(){
		generalStatsPanel.setTextFields( kps.getStats().totalRevenue() +"", kps.getStats().totalNumberOfEvents()+"",kps.getStats().totalExpenditure()+"");
		routeSpecificStatisticsPanel.populateComboBox(kps.getStats().getRouteData());
		criticalRoutePanel.generateCriticalRoutes(kps.getStats().calculateCriticalRoutes());
	}
	/**
	 * Sets up the label for the current position in the events list
	 * @param indexEndRange
	 */
	public void setupPositionLabel(int indexEndRange){
		if(indexEndRange == kps.events.size()){
			positionLabel.setText(TITLE + " Viewing all events");
		}
		else if(indexEndRange == 1){
			positionLabel.setText(TITLE + " Viewing the first event");
		}
		else {
			positionLabel.setText(TITLE + " Viewing events from 1 - " + indexEndRange );
		}
	}
	/**
	 * sets up the general statistics panel
	 */
	public void setupGeneralStatisticsPanel(){
		generalStatsPanel = new GeneralStatisticsPanel();
		generalStatsPanel.setTextFields( kps.getStats().totalRevenue() +"", kps.getStats().totalNumberOfEvents()+"",kps.getStats().totalExpenditure()+"");
		generalStatsPanel.setBounds(0, positionLabel.getY() + positionLabel.getHeight() , PANEL_WIDTH, GENERAL_STATS_PANEL_HEIGHT);
		add(generalStatsPanel);
	}
	
	public void setupPositionLabel(){
		positionLabel = new JLabel();
		positionLabel.setBounds(PANEL_WIDTH/4,0,PANEL_WIDTH,20);
		add(positionLabel);
	}
	/**
	 * sets up the route specific statistics panel
	 */
	public void setupRouteSpecificPanel(){
		routeSpecificStatisticsPanel = new RouteSpecificStatisticsPanel();
		routeSpecificStatisticsPanel.setBounds(0, (generalStatsPanel.getY() + GENERAL_STATS_PANEL_HEIGHT) + SEPERATOR_VALUE, PANEL_WIDTH, ROUTE_SPECIFIC_STATS_PANEL_HEIGHT);
		add(routeSpecificStatisticsPanel);
	}
	/**
	 * sets up the critical route statistics panel
	 */
	public void setupCriticalRoutePanel(){
		criticalRoutePanel = new CriticalRoutePanel(kps.getStats().calculateCriticalRoutes());
		criticalRouteScroller = new JScrollPane(criticalRoutePanel);
		criticalRoutePanel.setBounds(0,0,getWidth(),getHeight());
		criticalRouteScroller.setBounds(0, routeSpecificStatisticsPanel.getHeight()+routeSpecificStatisticsPanel.getY() + SEPERATOR_VALUE, PANEL_WIDTH, 200);
		criticalRouteScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		criticalRouteScroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		criticalRouteScroller.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"Critical Routes"));
		add(criticalRouteScroller);
	}
	/**
	 * Sets up the buttons
	 */
	public void setupButtons(){
		traceButtonPanel = new TraceButtonPanel(0,(criticalRouteScroller.getY() + criticalRouteScroller.getHeight() - (SEPERATOR_VALUE * 2)),PANEL_WIDTH,TRACE_BUTTON_HEIGHT,this,isManager);
		add(traceButtonPanel);
		traceButtonPanel.getExitButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				kps.recalculateStats(kps.events.size());
				StatisticsDialog.this.dispose();
			}
		});
	}
	/**
	 * Sets up the tracer button listeners
	 */
	public void setupTraceButtonListeners(){

		traceButtonPanel.getForwardButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// bounds check

				if(indexEndRange < kps.events.size()){
					indexEndRange++;
					//update title
					kps.recalculateStats(indexEndRange);
					updateStatistics();
					setupPositionLabel(indexEndRange);
				}
			}
			
		});
		
		traceButtonPanel.getBackButton().addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// bounds check
				if(indexEndRange > 1){
					indexEndRange--;
					kps.recalculateStats(indexEndRange);
					updateStatistics();
					setupPositionLabel(indexEndRange);
				}
			}
			
		});
		
	}
	
}

package gui;

import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to represent the statistics box which will contain relevant statistical imformation
 * @author treachmich
 *
 */
public class StatisticsBox extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel totalRevenueTitleLabel;
	private JLabel totalExpenTitleLabel;
	private JLabel totalEventsTitleLabel;
	private JLabel totalRevenueLabel;
	private JLabel totalExpenLabel;
	private JLabel totalEventsLabel;
	public static final int TITLE_WIDTH = 120;
	public static final int TITLE_HEIGHT = 15;
	public static final int GAP = 10;
	
	/**
	 * Constructs a statistic box
	 * @param revenue
	 * @param expen
	 * @param events
	 */
	public StatisticsBox(double revenue, double expen,int events){
		this.setLayout(null);
		setupLabels();
		setupValues(revenue,expen,events);
	}
	/**
	 * Sets up the title labels for the statistics box
	 */
	public void setupLabels(){
		totalRevenueTitleLabel = new JLabel("Total Revenue:");
		totalRevenueTitleLabel.setBounds(GAP,GAP , TITLE_WIDTH, TITLE_HEIGHT);
		add(totalRevenueTitleLabel);
		
		totalExpenTitleLabel = new JLabel("Total Expenditure:");
		totalExpenTitleLabel.setBounds(GAP,((NavigationPanel.STATISTICS_BOX_HEIGHT/3)+GAP) , TITLE_WIDTH, TITLE_HEIGHT);
		add(totalExpenTitleLabel);
		
		totalEventsTitleLabel = new JLabel("Total Events:");
		totalEventsTitleLabel.setBounds(GAP,(((NavigationPanel.STATISTICS_BOX_HEIGHT/3)*2)+GAP) , TITLE_WIDTH, TITLE_HEIGHT);
		add(totalEventsTitleLabel);
		
		totalRevenueLabel = new JLabel("0.0");
		totalRevenueLabel.setBounds((GAP) + TITLE_WIDTH,GAP , TITLE_WIDTH, TITLE_HEIGHT);
		add(totalRevenueLabel);
		
		totalExpenLabel = new JLabel("0.0");
		totalExpenLabel.setBounds((GAP) + TITLE_WIDTH,((NavigationPanel.STATISTICS_BOX_HEIGHT/3)+GAP) , TITLE_WIDTH, TITLE_HEIGHT);
		add(totalExpenLabel);
		
		totalEventsLabel = new JLabel("0");
		totalEventsLabel.setBounds((GAP) + TITLE_WIDTH,(((NavigationPanel.STATISTICS_BOX_HEIGHT/3)*2)+GAP) , TITLE_WIDTH, TITLE_HEIGHT);
		add(totalEventsLabel);
		
	}
	/**
	 * Sets the current statistics values
	 * @param revenue 
	 * @param expen
	 * @param events
	 */
	public void setupValues(double revenue, double expen,int events){
		// fix to two dp later
		DecimalFormat dFormat = new DecimalFormat("0.00");
		totalRevenueLabel.setText(dFormat.format(revenue));
		totalExpenLabel.setText(dFormat.format(expen));
		totalEventsLabel.setText(events + "");
	}
	
	

}

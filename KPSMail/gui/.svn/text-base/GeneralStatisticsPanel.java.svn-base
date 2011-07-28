package gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
/**
 * Class to represent the general statistics panel
 * @author treachmich
 *
 */
public class GeneralStatisticsPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel totalRevenueLabel;
	private JLabel totalExpenditureLabel;
	private JLabel totalNumberOfEventsLabel;
	private JTextField totalRevenueTextField;
	private JTextField totalExpenditureTextField;
	private JTextField totalNumberOfEventsTextField;
	/**
	 * Constructs a general staistics panel
	 */
	public GeneralStatisticsPanel(){
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK),"General Statistics"));
		setLayout(new GridLayout(0,2));
		totalRevenueLabel = new JLabel("Total Revnue: ");
		totalExpenditureLabel = new JLabel("Total Expenditure");
		totalNumberOfEventsLabel = new JLabel("Total Number Of Events: ");
		totalExpenditureTextField = new JTextField();
		totalRevenueTextField = new JTextField();
		totalNumberOfEventsTextField = new JTextField();
		disableTextFields();
		addComponents();
	}
	/**
	 * Adds all the components to the panel
	 */
	public void addComponents(){
		add(totalRevenueLabel);
		add(totalRevenueTextField);
		add(totalExpenditureLabel);
		add(totalExpenditureTextField);
		add(totalNumberOfEventsLabel);
		add(totalNumberOfEventsTextField);
	}
	/**
	 * Disables the text fields so they can not be edited
	 */
	public void disableTextFields(){
		totalExpenditureTextField.setEditable(false);
		totalNumberOfEventsTextField.setEditable(false);
		totalRevenueTextField.setEditable(false);
	}
	/**
	 * Sets the string values in each text field
	 * @param totalRevenue 
	 * @param totalNumberOfEvents
	 * @param totalExpenditure
	 */
	public void setTextFields(String totalRevenue,String totalNumberOfEvents,String totalExpenditure){
		totalExpenditureTextField.setText(totalExpenditure);
		totalNumberOfEventsTextField.setText(totalNumberOfEvents);
		totalRevenueTextField.setText(totalRevenue);
	}
}

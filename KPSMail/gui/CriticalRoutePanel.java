package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import kpsMail.Route;
/**
 * Class to represent the critical route panel.
 * @author treachmich
 *
 */
public class CriticalRoutePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel routeLabel;
	private JLabel valueLabel;
	/**
	 * Constructs a critical route panel
	 */
	public CriticalRoutePanel(List<Route> criticalRoutes){
		setLayout(new GridLayout(0,2));
		routeLabel = new JLabel(" Route");
		routeLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		routeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(routeLabel);
		valueLabel = new JLabel(" Value");
		valueLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 16));
		valueLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		add(valueLabel);
		generateCriticalRoutes(criticalRoutes);
		
	}
	/**
	 * Generates the critical routes and populates the panel
	 */
	public void generateCriticalRoutes(List<Route> criticalRoutes){
		DecimalFormat dFormat = new DecimalFormat("0.00");		
		removeAll();
		for(Route r : criticalRoutes){
			JLabel route = new JLabel(" "+r.toString()+" ");
			JLabel value = new JLabel(" "+dFormat.format(r.calculateCriticalValue())+" ");
			route.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			value.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			add(route);
			add(value);
		}
	}

}

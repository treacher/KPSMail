package kpsMail;

import java.util.*;

/**
 * 
 * @author Calvin Kaye
 *
 */
public class Statistics {
	
	private double totalRevenue = 0;
	private double totalExpenditure = 0;
	private int totalNumberOfEvents = 0;
	private KPS kps;
	
	public Statistics(KPS kps){
		this.kps = kps;
	}
	
	
	/**
	 * Getter method, returns the total revenue
	 * @return double
	 */
	public double totalRevenue(){
		return totalRevenue;
	}
	
	/**
	 * Getter method, returns the total expenditure
	 * @return double
	 */
	public double totalExpenditure(){
		return totalExpenditure;
	}
	
	/**
	 * GetterMethod, returns the total number of events
	 * @return int
	 */
	public int totalNumberOfEvents(){
		return totalNumberOfEvents;
	}
	
	/**
	 * Gets the route data from KPS, the list of routes
	 * @return List of routes
	 */
	public List<Route> getRouteData(){
		return kps.getStatisticsRouteManagaer().getRoutes();
	}
	
	/**
	 * Updates the total earnings made from a delivered package
	 * @param dp , DeliveredPackage
	 */
	public void updateEarnings(DeliveredPackage dp){
		totalRevenue += dp.getKpsPrice();
		totalExpenditure += dp.getKpsCost();
	}
	
	/**
	 * Increase the total number of events by one
	 */
	public void incrementNumberOfEvents(){
		totalNumberOfEvents++;
	}
	
	/**
	 * Calculates a list of critical routes. A route is critical if it's expenditure is greater than the revenue
	 * it is generating.
	 * @return List of critical routes.
	 */
	public List<Route> calculateCriticalRoutes(){
		
		List<Route> routes = getRouteData();
		List<Route> criticalRoutes = new ArrayList<Route>();
		
		for(Route r : routes){
			if(r.isCritical()){
				criticalRoutes.add(r);
			}
		}
		
		return criticalRoutes;
		
	}
	
}

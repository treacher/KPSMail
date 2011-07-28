package kpsMail;

import java.util.*;


/**
 * Class for representing a route between two cities
 * @author DamianKaye
 */
public class Route {

	public final String origin;
	public final String destination;
	public final Priority priority;

	private double kpsWeightCost;
	private double kpsVolumeCost;

	private int amountOfMail = 0;
	private double totalWeight = 0;
	private double totalVolume = 0;
	private int totalDeliveryTime = 0;
	private double totalExpenditure = 0;
	private double totalRevenue = 0;


	private ArrayList<TransportFirm> firms = new ArrayList<TransportFirm>();

	/**
	 * create a new route object
	 * @param orig
	 * @param dest
	 * @param p
	 * @param wCost
	 * @param vCost
	 */
	public Route(String orig, String dest, Priority p, double wCost, double vCost){
		origin = orig;
		destination = dest;
		priority = p;
		kpsWeightCost = wCost;
		kpsVolumeCost = vCost;		
	}

	/**
	 * Checks if there are any tranports firms for this route
	 * @return has at least one transport firm
	 */
	public boolean hasTransportFirms(){
		return !firms.isEmpty();
	}
	
	/**
	 * gets a transport firm with the given company name
	 * 
	 * @param companyName
	 * @return
	 */
	public TransportFirm getTransportFirm(String companyName){
		
		for(TransportFirm t : firms){
			if(t.getCompany().equals(companyName)){
				return t;
			}
		}
		
		// Could not find a transport firm with the given name.
		return null;
		
	}

	/**
	 * adds all relevant statistics from values given
	 * 
	 * @param weight
	 * @param volume
	 * @param delTime
	 * @param expendi
	 * @param revenue
	 */
	public void processPackage(double weight , double volume , int delTime , double expendi , double revenue){
		amountOfMail++;
		totalWeight += weight;
		totalVolume += volume;
		totalDeliveryTime += delTime;
		totalExpenditure += expendi;
		totalRevenue += revenue;
	}

	/**
	 * gets a valid transport firm that supports a package
	 * 
	 * @precondition requires firms to be sorted by cost
	 * @param md
	 * @return
	 */
	public TransportFirm getTransportFirm(MailDelivery md){

		Priority p = md.priority;
		double weight = md.weight;
		double volume = md.volume;

		for (TransportFirm t : firms){

			if(weight <= t.getMaxWeight() && volume <= t.getMaxVolume() && 
					!(p.isAir() ==
						!t.getType().isAir()))
				return t;
		}

		return null;


	}
	
	/**
	 * Adds new weight and volume costs to a route
	 * 
	 * @requires requires weight and volume > 0
	 * @param weight
	 * @param volume
	 */
	public boolean setNewCosts(double weight, double volume){
		if(weight < 0 || volume < 0)
			return false;
	
		this.kpsWeightCost = weight;
		this.kpsVolumeCost = volume;
		return true;
	}

	
	public boolean addTransportFirm(TransportFirm t){
		boolean output = firms.add(t);
		Collections.sort(firms);
		return output;
	}

	public boolean removeTransportFirm(TransportFirm t){
		return firms.remove(t);
	}

	public double calculatePrice(double weight, double vol){
		return (kpsWeightCost * weight) + (kpsVolumeCost * vol);
	}

	public double getKpsWeightCost(){return kpsWeightCost;}

	public double getKpsVolumeCost() {return kpsVolumeCost;}

	public int getAmountOfMail() {return amountOfMail;}

	public double getTotalWeight() { return totalWeight; }

	public double getTotalVolume() {return totalVolume;	}

	public double getTotalExpenditure() {return totalExpenditure;}

	public double getTotalRevenue() {return totalRevenue;}	

	public int getTotalDeliveryTime() {return totalDeliveryTime;}
	
	public List<TransportFirm> getTransportFirms(){
		return firms;
	}
	
	public double getAverageDeliveryTime(){
		if(amountOfMail == 0 ) return 0;
		return totalDeliveryTime / amountOfMail;
	}

	public boolean equals(Object o){
		if (o instanceof Route){
			Route r = (Route) o;

			return r.origin == origin && r.destination == destination
			&& r.priority == priority;
		}
		return false;
	}

	public boolean equals(String orig, String dest, Priority p){		
		return orig == origin && dest == destination
		&& p == priority;
	}

	public String toString(){
		return origin + " To " + destination + " via " + priority;
	}
	
	/**
	 * Tells the user if average delivery cost is less than the average customer is paying
	 * @return isCritical
	 */
	public boolean isCritical(){
		if(amountOfMail == 0){
			return false;
		}
		return (totalExpenditure / amountOfMail) > (totalRevenue / amountOfMail);
	}
	
	/**
	 * Calculates the average difference between revenue and expenditure.
	 * @return
	 */
	public double calculateCriticalValue(){
		return (totalExpenditure / amountOfMail) - (totalRevenue / amountOfMail);
	}

}

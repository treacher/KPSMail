package kpsMail;

import java.util.ArrayList;

/**
 * Class for representing a package that has been processed and delivered
 * @author SeanAnderson
 */
public class DeliveredPackage {

	private MailDelivery mailEvent;
	
	private double kpsPrice;
	private double kpsCost;
	private double deliveryTime;
	
	private ArrayList<TransportFirm> connectedRoutes = new ArrayList<TransportFirm>();
	
	/**
	 * creates a delivered package event
	 * 
	 * @param m
	 * @param price
	 * @param cost
	 * @param delTime
	 */
	public DeliveredPackage(MailDelivery m, double price, double cost, double delTime){
		mailEvent = m;
		kpsPrice = price;
		kpsCost = cost;
		deliveryTime = delTime;
	}
	
	/**
	 * adds a transport firm to the route
	 * @param transport firm
	 * @return whether the route was added or not
	 */
	public boolean addTransportFirm(TransportFirm t){
		return connectedRoutes.add(t);
	}
	
	/**
	 * get volume of the package
	 * @return volume
	 */
	public double getVolume() {
		return mailEvent.volume;
	}

	/**
	 * get weight of the package
	 * @return weight
	 */
	public double getWeight() {
		return mailEvent.weight;
	}

	/**
	 * get price of package
	 * @return price
	 */
	public double getKpsPrice() {
		return kpsPrice;
	}

	/**
	 * get cost of package
	 * @return cost
	 */
	public double getKpsCost() {
		return kpsCost;
	}

	/**
	 * get delivery time of package
	 * @return time in transit
	 */
	public double getDeliveryTime() {
		return deliveryTime;
	}
	
	/**
	 * get package priority
	 * @return priority
	 */
	public Priority getPriority(){
		return mailEvent.priority;
	}
}

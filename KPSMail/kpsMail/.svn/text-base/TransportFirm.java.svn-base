package kpsMail;

/**
 * 
 * @author kayedami
 *
 */

public class TransportFirm implements Comparable<TransportFirm>{

	private String company;
	private double weightCost;
	private double volCost;
	private double maxWeight;
	private double maxVolume;
	private int duration;
	private int frequency;
	private Day day;
	private Route route;
	private Type type;
	
	
	public TransportFirm(String name, double weightC, double volC, double maxW, double maxV, int dura, int freq, Day d, Route r, Type t){
		
		this.company = name;
		this.weightCost = weightC;
		this.volCost = volC;
		this.maxWeight = maxW;
		this.maxVolume = maxV;
		this.duration = dura;
		this.frequency = freq;
		this.day = d;
		this.route = r;
		this.type = t;
					
	}
	
	/**
	 * Calculates the cost of the transport firm
	 * 
	 * @param weight
	 * @param volume 
	 * @return double , the total cost based on the weight and volume
	 */
	public double calculateCost(double weight, double volume){
		return (weightCost * weight) + (volCost * volume);
	}
	
	/**
	 * Getter method, returns the company name
	 * 
	 * @return , String representing the companies name
	 */
	
	public String getCompany(){
		return company;
	}
	
	/**
	 * Getter method, returns the volume cost 
	 * @return double , returns a double which is the volume cost.
	 */
	public double getVolumeCost(){
		return volCost;
	}
	
	/**
	 * Getter method, returns the transport firms type
	 * @return type, the type of the transport firm
	 */
	public Type getType(){
		return type;
	}
	
	/**
	 * Getter method, returns the weight cost of the transport firm
	 * @return double, weight cost of the transport firm
	 */
	public double getWeightCost(){
		
		return this.weightCost;
	}
	
	/**
	 * Getter method, returns the max weight this transport firm can handle
	 * @return double, the max weight this transport firm can handle.
	 */
	public double getMaxWeight(){
		
		return this.maxWeight;
	}
	
	/**
	 * Getter method, returns the max volume this transport firm can handle
	 * @return double, the max volume this transport firm can handle.
	 */
	public double getMaxVolume(){
		return this.maxVolume;
	}
	
	/**
	 * Getter method, The duration this transport firm takes
	 * @return int
	 */
	public int getDuration(){
		return this.duration;
	
	}
	/**
	 * Getter method, how many times per day the transport firm operates
	 * @return int
	 */
	public int getFrequency(){
		return this.frequency;
	}
	
	/**
	 * Getter method, the day this transport firm operates on
	 * @return Day , the day this transport firm operates on
	 */
	public Day getDay(){
		return this.day;
	}
	
	/**
	 * Getter method, gets the route associated with this transport firm
	 * @return Route , the route the transport firm works on
	 */
	public Route getRoute(){
		return this.route;
	}
	
	/**
	 * Compares for equality between two transport firms.
	 * @return boolean , determining if they are equal
	 */
	public boolean equals(Object other){
		
		if(other instanceof TransportFirm){			
			TransportFirm tf = (TransportFirm) other;
			return tf.company == this.company && tf.weightCost == this.weightCost && tf.maxWeight == this.maxWeight && tf.maxVolume == this.maxVolume;
			
		}
		return false;
	}

	

	/**
	 * Compares two transport firms
	 * @return int
	 */
	public int compareTo(TransportFirm t) {
		
		return (int) ((this.volCost * this.weightCost) - (t.volCost * t.weightCost)); 	
		
	}
	
	/**
	 * Returns a string representation of a transport firm
	 * @return String 
	 */
	public String toString(){
		return company + " (" + type + ")"; 
		
	}

	/**
	 * sets the new transport costs from a transportCostUpdate
	 * @param tcu
	 */
	public void setNewCosts(TransportCostUpdate tcu) {
		
		weightCost = tcu.weightCost;
		volCost = tcu.volumeCost;
		
	}
	
	
	
	
	
	
}

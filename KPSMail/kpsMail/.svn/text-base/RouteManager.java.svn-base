package kpsMail;

import java.util.*;

/**
 * 
 * Damian , Calvin , Sean
 *
 */

public class RouteManager {

	private ArrayList<Route> routes = new ArrayList<Route>(); 
	private ArrayList<RouteNode> nodes = new ArrayList<RouteNode>();
	private int currentHour = 0;
	private Day currentDay;


	/**
	 *
	 *Works out the transport type based on the origin and destination
	 *
	 * @param origin
	 * @param desti
	 * @param type
	 * @return Priority
	 */
	public Priority translateTransportType(String origin , String desti , Type type){
		
		boolean domOrigin = false;
		boolean domDesti = false;
		
		// checks to see of both origin and dest are in NZ, therefore its domestic
		for( NewZealand cities : NewZealand.values()){
			if(cities.toString().equals(origin)){
				domOrigin = true;
			}
			
			if(cities.toString().equals(desti)){
				domDesti = true;
			}
		}
		//if both booleans are true then its domestic
		if(domOrigin && domDesti){
			if(type == Type.Air){
				return Priority.DomesticAir;
			}
			else{
				return Priority.DomesticStandard;
			}
		}
		//otherwise its international
		else{
			if(type == Type.Air){
				return Priority.InternationalAir;
			}
			else{
				return Priority.InternationalStandard;
			}
		}
	}
	
	/**
	 * Makes a path of transport firms to take from the origin to the destination
	 * after the use of djikstra to find the path
	 * @param s
	 * @return ArrayList
	 */
	private ArrayList<TransportFirm> makePath(SearchNode s){

		ArrayList<TransportFirm> path = new ArrayList<TransportFirm>();

		while(s != null){
			
			path.add(s.tFirm);
			s = s.parent;
		}

		Collections.reverse(path);
		
		// First tFirm is always null because it is never set.
		path.remove(0);
		
		return path;
	}

	/**
	 * Creates a package from the specified mail delivery event. 
	 * Returns null if no path is found
	 * @param md
	 * @return
	 */
	public DeliveredPackage createPackage(MailDelivery md){
		
		double customerPrice = 0;
		double kpsCost = 0;
		double delTime = 0;
		
		SearchNode endNode = dijkstra(md);

		if(endNode == null)
			return null;

		ArrayList<TransportFirm> path = makePath(endNode);
		
		if(path.size() == 0){
			return null;
		}
		
		delTime = calculateDeliveryTime(md , path);

		for(TransportFirm t : path){
			double expenditure = t.calculateCost(md.weight, md.volume);
			double revenue = t.getRoute().calculatePrice(md.weight, md.volume);
			kpsCost += expenditure;
			customerPrice += revenue;
			Route usedRoute = t.getRoute();
			usedRoute.processPackage(md.weight , md.volume , t.getDuration() , expenditure , revenue);
		}

		return new DeliveredPackage(md, customerPrice, kpsCost, delTime);

	}
	
	/**
	 * Adds time to the current day
	 * @param hour
	 */
	public void addTime( int hour ){

		int times = hour / 24;
		int remainder = 24 % hour;
		int time = currentHour;
		time = currentHour + hour;
	

		if(times > 0 ){
			while(times != 0){
				currentDay = currentDay.nextDay();
				times--;
			}

			if(remainder > 0){
				time = currentHour + remainder;
			}
		}

		if(time >= 24){
			currentHour = time - 24;
			currentDay = currentDay.nextDay();
		}
		else{
			currentHour = time;
		}
	}

	/**
	 * Works out the delivery time from the details of the mail delivery
	 * @param md
	 * @param path
	 * @return int , total time going to be taken to deliver
	 */
	public int calculateDeliveryTime(MailDelivery md, List<TransportFirm> path){

		currentDay = Day.fromString(md.day);
		int deliveryTime = 0;
		currentHour = 0;

		// Add to the deliveryTime the wait between package arrival and the first
		// transport firm dispatch time.
	
		deliveryTime += currentDay.difference(path.get(0).getDay());
		currentDay = path.get(0).getDay();

	
	

		//iterate through the path and update price / cost / delTime
		for (int count = 0 ; count < path.size()-1 ; count++){

			TransportFirm t = path.get(count);
			deliveryTime = transportFirmHandling(t , deliveryTime);

			// If the travel time overshoots the next transportFirms dispatch time
			// , wait a week and the difference between the 2 days.
			if(t.getDuration() + currentHour >= currentDay.difference(path.get(count+1).getDay().nextDay()) - currentHour){
			

				deliveryTime += (7*24 + currentDay.difference(path.get(count+1).getDay()) - currentHour);
			
				currentHour = 0;
				currentDay = path.get(count+1).getDay();
			}
			// Add the wait time to delivery time.
			else{

				addTime(t.getDuration());
				deliveryTime += t.getDuration();
			
				deliveryTime += currentDay.difference(path.get(count+1).getDay()) - currentHour;
				
				currentHour = 0;
				currentDay = path.get(count+1).getDay();
				


			}
		}

		// At the very end of the path add the duration of the travel time to
		// to get the package to it's final destination.
		deliveryTime = transportFirmHandling(path.get(path.size()-1), deliveryTime);
		deliveryTime += path.get(path.size()-1).getDuration();

		return deliveryTime;
	}
	
	/**
	 * 
	 * Works out the total time to be added onto the delivery time from
	 * the transport firm handling
	 * @param t
	 * @param deliveryTime
	 * @return int , time to be added on
	 */
	public int transportFirmHandling(TransportFirm t, int deliveryTime){

		int frequency = 0;
		if(24 % t.getFrequency() == 0){
			frequency = 24 /t.getFrequency();
		}
		else{
			frequency = 24 /t.getFrequency() + 1;
		}

		int num = 0;
		int waitTime = 0;
		while(num < 24){
			num += frequency;
			if(num > currentHour){
				waitTime = num - currentHour;
				break;
			}
		}

		currentHour = num;
	
		deliveryTime += waitTime;
	
		return deliveryTime;

	}

	// main for testing purposes
	public static void main(String[] args){

		TransportFirm t1 = new TransportFirm("" , 0.0 , 0.0 , 0.0 , 0.0 , 10  , 6 , Day.Thursday , null , Type.Air);
		TransportFirm t2 = new TransportFirm("" , 0.0 , 0.0 , 0.0 , 0.0 , 12 , 4 , Day.Friday , null , Type.Air);
		TransportFirm t3 = new TransportFirm("" , 0.0 , 0.0 , 0.0 , 0.0 , 6 , 3 , Day.Sunday , null , Type.Air);
		List<TransportFirm> ts = new ArrayList<TransportFirm>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		MailDelivery md = new MailDelivery("", "", Priority.DomesticAir, 0.0,
				0.0, "Tuesday");
		RouteManager rm = new RouteManager();
		rm.calculateDeliveryTime(md , ts);
		
	}

	/**
	 * adds a transportFirm object to a route
	 * 
	 * @requires route object must exist.
	 * @param tcu
	 * @return
	 */
	public void addTransportFirm(TransportCostUpdate tcu){

		for(Route r : routes){
			//find a route with match orig, dest and priority
			if (r.origin == tcu.orig && r.destination == tcu.dest){
				if( r.priority.isAir() == tcu.type.isAir()){
					r.addTransportFirm(new TransportFirm(tcu.company, tcu.weightCost, tcu.volumeCost, tcu.maxWeight, tcu.maxVolume, 
							tcu.duration, tcu.frequency,tcu.day,r, tcu.type));
				}
			}
		}
	}

	/**
	 * Adds a route to the route list
	 * @param route
	 */
	public void addRoute(Route route){
		routes.add(route);
		addNode(route);
	}

	/**
	 * Removes transport firm from the specific route
	 * @param td
	 * @return boolean , representing if the route was removed or ot
	 */
	public boolean removeTransportFirm(TransportDiscontinued td){
		for(Route r : routes){
			//find a route with match orig, dest and priority
			if (r.origin == td.orig && r.destination == td.dest && (r.priority.isAir() == td.type.isAir())){
				TransportFirm t = r.getTransportFirm(td.company);
					if(t != null){
						r.removeTransportFirm(t);
						return true;
					}
			}
		}
		
		return false;	
	}
	
	/**
	 * Modify the route costs based on the new costs
	 * @param cpu
	 * @return boolean, determining the success of the update
	 */
	public boolean modifyCustomerPrices(CustomerPriceUpdate cpu){
		
		if(cpu.orig.equalsIgnoreCase("New Zealand") && cpu.dest.equalsIgnoreCase("New Zealand")){
			for(Route route : routes){
				if(NewZealand.contains(route.origin) && NewZealand.contains(route.destination)){
					route.setNewCosts(cpu.weightCost , cpu.volumeCost);
					
				}
			}
			
		}
		else if(cpu.orig.equalsIgnoreCase("New Zealand")){
			for(Route route : routes){
				if(NewZealand.contains(route.origin)){
					route.setNewCosts(cpu.weightCost , cpu.volumeCost);
				}
			}
			
			
		}
		else{
			Route r = getRoute(cpu.orig, cpu.dest, cpu.priority);
			if(r != null){
				 r.setNewCosts(cpu.weightCost , cpu.volumeCost);
				
			}	
		}
		return true;
		
		
	}

	/** finds the cheapest path for a given route
	 * 
	 * @param r
	 * @param md
	 * @return searchNode, holds the path through a series of parent pointers
	 */
	private SearchNode dijkstra(MailDelivery md){
		
		PriorityQueue<SearchNode> fringe = new PriorityQueue<SearchNode>();

		RouteNode startNode = getNode(md.orig);
		RouteNode goalNode = getNode(md.dest);

		//check start and end nodes exist
		if(startNode == null || goalNode == null)
			return null;

		//  reset visited flag for all nodes
		for(RouteNode n : nodes){
			n.visited = false;
		}

		//add the first search node to the fringe
		SearchNode firstNode = new SearchNode(startNode, 0.0 , null);
		fringe.add(firstNode);

		while(!fringe.isEmpty()){

			SearchNode current = fringe.poll();

			if(current.node.equals(goalNode)){
				return current;
			}
			
			//want to explore nodes that have not been visited
			if(current.node.visited == false){
				current.node.visited = true;

				for(RouteManager.RouteNode.Link l : current.node.neighbours){
					if(l.destNode.visited == false){				

						RouteNode node = l.destNode;
						SearchNode parent = current;

						TransportFirm t = findTransportFirm(l.routes , md);
						
						//build the links between nodes
						if (t != null){
						
							double length = current.toHere + t.getWeightCost();
							SearchNode sn = new SearchNode(node , length , parent);
							sn.tFirm = t;
							fringe.add(sn);
						
						}

					}
				}

			}


		}

		//no path found!
		return null;
	}

	/**
	 * Finds and returns a transport firm that matches requirements.
	 * @param routes
	 * @return
	 */
	public TransportFirm findTransportFirm(List<Route> routes , MailDelivery md){

		TransportFirm t1 = null;
		TransportFirm t2 = null;

		for(Route r : routes){

			if (md.priority.isAir() && r.priority.isAir()){
				return r.getTransportFirm(md);
			}

			else if (md.priority.isAir() == r.priority.isAir())
				t1 = r.getTransportFirm(md);

			else 
				t2 = r.getTransportFirm(md);
		}

		if (t1 != null)
			return t1;
		else
			return t2;
	}



	/**
	 * Finds the route node in the route list
	 * @param city
	 * @return RouteNode found
	 */
	public RouteNode getNode(String city){
		for (RouteNode n : nodes){
			if (n.city.equals(city))
				return n;			
		}
		//node not found
		return null;
	}

	/**
	 * Tries to find the RouteNode from the city graph. If successful it returns the RouteNode otherwise
	 * it creates a new RouteNode and adds it to the city graph.
	 * @param name of city to search for.
	 * @return RouteNode
	 */
	public RouteNode generateNode(String city){
		
		// Search for the city.
		for (RouteNode n : nodes){
			if (n.city.equals(city))
				return n;			
		}
		
		// Add a new City to the graph.
		RouteNode n = new RouteNode(city);
		nodes.add(n);
		return n;
	}

	/**
	 * Creates links in the city graph.
	 * @param route
	 */
	public void addNode(Route r){
		//find origin and dest nodes
		RouteNode orig = generateNode(r.origin);
		RouteNode dest = generateNode(r.destination);

		//add dest to origin neighbours (creates a route between the two).
		orig.addNeighbour(dest, r);
	}

	/**
	 * 
	 * @author kayedami
	 *
	 */
	private class SearchNode implements Comparable<SearchNode>{
		
		public RouteNode node;
		public double toHere = 0;
		public SearchNode parent;
		public TransportFirm tFirm;
		
		public SearchNode(RouteNode r, double length , SearchNode parent){
			
			node = r;
			this.toHere = length;
			this.parent = parent;

		}
		
		/**
		 * compares two search nodes
		 * @return int
		 */
		public int compareTo(SearchNode sn) {
			return (int) (toHere - sn.toHere);
		}
	}
	/**
	 * 
	 * @author kayedami
	 *
	 */
	private class RouteNode{

		public String city;
		public ArrayList<Link> neighbours = new ArrayList<Link>();
		public boolean visited = false;

		public RouteNode(String cityName){
			city = cityName;
		}
		
		public String toString(){
			return city;
		}

		/**
		 * Checks equality beween two routeNodes
		 * @return boolean stating whether they are equal or not
		 */
		public boolean equals(Object other){
			
			if(other == null){
				return false;
			}
			
			if(other.getClass() != RouteNode.class){
				return false;
			}
			
			RouteNode rn = (RouteNode) other;
			
			return this.city.equalsIgnoreCase(rn.city);
			
		}

		/**
		 * Builds links between route nodes, used for djikstra
		 * @param n
		 * @param r
		 */
		public void addNeighbour(RouteNode n, Route r){
			
			// The link between this neighbour might already exist, so add the new route to the link.
			for(Link l : neighbours){
				if(l.destNode.city.equals(n.city)){
					l.routes.add(r);
					return;
				}				
			}			
			
			// A link is created between this RouteNode and RouteNode n.
			Link link = new Link();
			link.routes.add(r);
			link.destNode = n;
			this.neighbours.add(link);

		}
		/**
		 * 
		 * @author kayedami
		 *
		 */
		private class Link {
			public List<Route> routes = new ArrayList<Route>();
			public RouteNode destNode;
		}
	}
	
	/**
	 * returns a route matching the given parameters
	 * 
	 * @param orig
	 * @param dest
	 * @param priority
	 * @return
	 */
	public Route getRoute(String orig, String dest, Priority priority) {
		for(Route r : routes){
			if (r.origin.equals(orig) && r.destination.equals(dest) && priority == r.priority)
				return r;	
		}
		
		// Did not find route that matches parameters.
		return null;
	}
	
	/**
	 * Finds the transport firm with the given origin and destination out of the route list
	 * @param transportFirm
	 * @param orig
	 * @param dest
	 * @param type
	 * @return TransportFirm , the transport found 
	 */
	public TransportFirm getTransportFirm(String transportFirm, String orig, String dest, Type type) {
		
		for(Route r : routes){
			if (r.origin.equals(orig) && r.destination.equals(dest)){
				for(TransportFirm t : r.getTransportFirms()){
					if(t.getType() == type && t.getCompany().equals(transportFirm))
						return t;					
				}
			}
		}

		// Could not find the transportFirm
		return null;
	}
	/**
	 * Getter method,  gets the city names of all the routes
	 * @return
	 */
	public Set<String> getCityNames(){
		Set<String> cityNames = new HashSet<String>();
		for(RouteNode node : nodes){
			cityNames.add(node.city);
		}
		return cityNames;
	}
	
	/**
	 * Getter method, gets the route list 
	 * @return list of routes
	 */
	public ArrayList<Route> getRoutes(){
		return routes;
	}
	
	/**
	 * Getter method, gets the cities of all the routes
	 * @return list of cities
	 */
	public ArrayList<RouteNode> getCities(){
		return nodes;
	}
}

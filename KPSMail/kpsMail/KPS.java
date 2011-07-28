package kpsMail;



import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing major components and processing events
 * 
 * @author SeanAnderson & CalvinKaye & MichaelTreacher
 */
public class KPS {

	private LogManager logManager;
	public List<Event> events = new ArrayList<Event>();
	private RouteManager routeManager;
	private Statistics stats;

	private RouteManager clerkRouteManager;
	private Statistics clerkStats;
	private List<Event> badEvents;
	private String file = "kpslogfile.xml";
	private boolean addToList = true;
	private boolean isManager = false;

	/**
	 * create a KPS object
	 */
	public KPS(){
		logManager = new LogManager(file);
		routeManager = new RouteManager();
		stats = new Statistics(this);
		badEvents = processEvents(logManager.events);		
	}
	
	/**
	 * get list of invalid events not processed on startup
	 * @return list of invalid events
	 */
	public List<Event> getBadEvents(){
		return badEvents;
	}
	
	/**
	 * saves events to a log file
	 */
	public void saveEvents(){
		logManager.writeEventsToLog(events);
	}
	
	/**
	 * gets statistics object
	 * @return stats
	 */
	public Statistics getStats(){
		return stats;
	}
	
	/**
	 * set manager mode accessibility
	 * @param isManager
	 */
	public void switchMode(boolean isManager){
		this.isManager = isManager;
		if(isManager){
			//save previous objects to another variable
			clerkRouteManager = routeManager;
			clerkStats = stats;
		}
		else{
			//restore previous objects
			routeManager = clerkRouteManager;
			stats = clerkStats;
		}
	}

	/**
	 * calculates the given number of events from the first event
	 * @param numberOfEvents
	 */
	public void recalculateStats(int numberOfEvents){
		//reset routemanager and stats
		routeManager = new RouteManager();
		stats = new Statistics(this);
		
		List<Event> subEvents =  new ArrayList<Event>(events.subList(0, numberOfEvents));
		
		//make sure events are not added again when processed
		addToList = false;
		processEvents(subEvents);
		addToList = true;

	}

	/**
	 * processes a mail delivery object
	 * @param md
	 * @return package processed successfully
	 */
	public boolean processEvent(MailDelivery md){
		// if the type field has been set handle the event
		//by converting the type string into an actual type object
		if(md.type != null){
			Type t;
			if(md.type.equalsIgnoreCase("Air")){
				t = Type.Air;
			}
			else{
				t = Type.Land;
			}
			//convert type to priority
			md.priority = routeManager.translateTransportType(md.orig , md.dest , t);
		}
		
		DeliveredPackage dp = routeManager.createPackage(md);								

		//check package can be delivered
		if(dp != null){			
			stats.updateEarnings(dp);
			stats.incrementNumberOfEvents();
			
			// make sure not to add events to list when recalculating statistics
			if(addToList)
				events.add(md);
			return true;
		}
		return false;
	}

	/**
	 * processes a customer price update event
	 * @param customer price update
	 * @return event processed successfully
	 */
	public boolean processEvent(CustomerPriceUpdate cpu){
		//update customer price
		boolean output = routeManager.modifyCustomerPrices(cpu);
		stats.incrementNumberOfEvents();
		
		// make sure not to add events to list when recalculating statistics
		if(addToList)
			events.add(cpu);
		return output;
	}

	/**
	 * processes a transport cost update
	 * @param transport cost update
	 * @return event processed successfully
	 */
	public boolean processEvent(TransportCostUpdate tcu){
		//check if mail is within New Zealand
		Priority priority = routeManager.translateTransportType(tcu.orig, tcu.dest, tcu.type);
		Route r = routeManager.getRoute(tcu.orig, tcu.dest, priority);
		
		//make new route to support transport firm
		if( r == null){
			Route newRoute = new Route(tcu.orig, tcu.dest, priority, 0 , 0);
			newRoute.addTransportFirm(new TransportFirm(tcu.company , tcu.weightCost , tcu.volumeCost , tcu.maxWeight , tcu.maxVolume , tcu.duration, tcu.frequency , tcu.day , newRoute , tcu.type));
			routeManager.addRoute(newRoute);
		}
		//route exists
		else{
			TransportFirm t = r.getTransportFirm(tcu.company);
			//edit existing transport firm
			if(t != null){
				t.setNewCosts(tcu);
			}
			//create new transport firm
			else{
				r.addTransportFirm(new TransportFirm(tcu.company , tcu.weightCost , tcu.volumeCost , tcu.maxWeight , tcu.maxVolume , tcu.duration, tcu.frequency , tcu.day , r , tcu.type));
			}
		}
		//keep track of stats
		stats.incrementNumberOfEvents();
		
		// make sure not to add events to list when recalculating statistics
		if(addToList)
			events.add(tcu);
		return true;
	}

	/**
	 * process a transport discontinued event
	 * @param transport discontinued event
	 * @return  if successfully processed
	 */
	public boolean processEvent(TransportDiscontinued td){
		routeManager.removeTransportFirm(td);
		stats.incrementNumberOfEvents();
		
		// make sure not to add events to list when recalculating statistics
		if(addToList)
			 events.add(td);
		return true;
	}

	/**
	 * get route manager object
	 * @return route manager
	 */
	public RouteManager getRouteManager(){
		//check for manager mode
		if(isManager){
			return clerkRouteManager;
		}
		else {
			return routeManager;
		}
	}
	
	/**
	 * Sets the routemanager of this kps
	 * @param rm
	 */
	public void setRouteManager(RouteManager rm){
		this.routeManager = rm;
	}
	
	/**
	 * method for stats class to get route manager
	 * @return route manager
	 */
	public RouteManager getStatisticsRouteManagaer(){
		return routeManager;
	}

	/**
	 * processes a list of events
	 * @param events list
	 * @return unprocessed events
	 */
	public List<Event> processEvents(List<Event> events){
		//use visitor pattern to execute correct process method
		//list of bad events
		boolean success;
		List<Event> badEvents = new ArrayList<Event>();
		for (int i = 0;i < events.size();i++){
				success = events.get(i).processEvent(this);
				if(!success){
					badEvents.add(events.get(i)); //store invalid events
				}
		}
		return badEvents;
	}
}

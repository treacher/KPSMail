package kpsMail;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

/**
 * 
 * @author Sean Anderson
 *
 */
public class TransportCostUpdate extends Event {
	
	public final String company;	
	public final double weightCost;
	public final double maxWeight;
	public final Type type;
	
	public final double volumeCost;
	public final double maxVolume;
	
	public final Day day;
	public final int frequency;
	public final int duration;
	
	
	public TransportCostUpdate(String company, String dest, String orig, Type t, 
			double wCost, double maxW, double vCost, double maxV, int dur, int freq,
			Day d){
		
		this.company = company;
		this.dest = dest;
		this.orig = orig;
		this.type = t;
		
		weightCost = wCost;
		maxWeight = maxW;
		
		volumeCost = vCost;
		maxVolume = maxV;
		
		duration = dur;
		frequency = freq;
		day = d;			
	}
	
	public TransportCostUpdate(Route r, TransportFirm t, double weight, double volume){
		this.company = t.getCompany();
		this.dest = r.destination;
		this.orig = r.origin;
		this.type = t.getType();
		
		weightCost = weight;
		volumeCost = volume;
		
		maxWeight = t.getMaxWeight();
		maxVolume = t.getMaxVolume();
		
		duration = t.getDuration();
		frequency = t.getFrequency();
		day = t.getDay();
	}
	
	/**
	 * Returns if a transport firm will accept the package
	 * @param package
	 * @return
	 */
	public boolean acceptPackage(DeliveredPackage p){
		//match priority with a type
		return //p.getPriority() == priority &&
			 p.getWeight() <= maxWeight 
			&& p.getVolume() <= maxVolume;	
	}
	/**
	 * Returns the how much the transport firm will charge to
	 * deliver the package
	 * @param package
	 * @return
	 */
	public double packageCost(DeliveredPackage p){
		return (p.getWeight() * weightCost) + (p.getVolume() * volumeCost);				
	}

	
	/**
	 * Turns this event into XML
	 */
	public void toXML(XMLEventWriter eventWriter){
		
		try {
			
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			StartElement sElement = eventFactory.createStartElement("", "", "cost");
			eventWriter.add(eventFactory.createDTD("\t"));
			eventWriter.add(sElement);
			eventWriter.add(eventFactory.createDTD("\n"));
			writeXMLElement(eventWriter , "company" , company);
			writeXMLElement(eventWriter, "to" , dest);
			writeXMLElement(eventWriter, "from" , orig);
			writeXMLElement(eventWriter, "type" , type.getText());
			writeXMLElement(eventWriter, "weightcost" , Double.toString(weightCost));
			writeXMLElement(eventWriter, "volumecost" , Double.toString(volumeCost));
			writeXMLElement(eventWriter, "maxWeight" , Double.toString(maxWeight));
			writeXMLElement(eventWriter, "maxVolume" , Double.toString(maxVolume));
			writeXMLElement(eventWriter, "duration" , Integer.toString(duration));
			writeXMLElement(eventWriter, "frequency" , Integer.toString(frequency));
			writeXMLElement(eventWriter , "day" , day.getText());
			EndElement eElement = eventFactory.createEndElement("", "", "cost");
			eventWriter.add(eventFactory.createDTD("\t"));
			eventWriter.add(eElement);
			eventWriter.add(eventFactory.createDTD("\n"));
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	/**
	 * Processes this event
	 */
	public boolean processEvent(KPS k) {
		return k.processEvent(this);
	}
	

	/**
	 * Returns a string representation of this event
	 * @return String
	 */
	public String getEventName() {
		return "Transport Cost Update";
	}

	/**
	 * Returns the details of this event as a string
	 * @return String
	 */
	public String getContents() {
		return company + " travelling " + orig + "->" + dest;
	}
	
}

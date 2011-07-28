package kpsMail;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

/**
 * Class for representing customer price updates
 * @author anderssean2
 */
public class CustomerPriceUpdate extends Event {
	
	public final double weightCost;
	public final double volumeCost;
	public final Priority priority;
	
	/**
	 * creates a customer price update
	 * @param dest
	 * @param orig
	 * @param p
	 * @param weight
	 * @param volume
	 */
	public CustomerPriceUpdate(String dest, String orig, Priority p, double weight, double volume){
		this.dest = dest;
		this.orig = orig;
		this.priority = p;
		
		this.weightCost = weight;
		this.volumeCost = volume;	
	}
	/**
	 * creates a customer price update
	 * 
	 * @param r
	 * @param weightCost
	 * @param volumeCost
	 */
	public CustomerPriceUpdate(Route r, double weightCost, double volumeCost){
		
		this.weightCost = weightCost;
		this.volumeCost = volumeCost;
		
		//get information from route object
		this.orig = r.origin;
		this.dest = r.destination;
		this.priority = r.priority;
	}

	
	/**
	 * writes the object in xml to a XMLEventWriter
	 * @param XMLEventWriter
	 */
	public void toXML(XMLEventWriter eventWriter){
		
		try {
			
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			StartElement sElement = eventFactory.createStartElement("", "", "price");
			eventWriter.add(eventFactory.createDTD("\t"));
			eventWriter.add(sElement);
			eventWriter.add(eventFactory.createDTD("\n"));
			writeXMLElement(eventWriter, "to" , dest);
			writeXMLElement(eventWriter, "from" , orig);
			writeXMLElement(eventWriter, "priority" , priority.toString());
			writeXMLElement(eventWriter, "weightcost" , Double.toString(weightCost));
			writeXMLElement(eventWriter, "volumecost" , Double.toString(volumeCost));
			EndElement eElement = eventFactory.createEndElement("", "", "price");
			eventWriter.add(eventFactory.createDTD("\t"));
			eventWriter.add(eElement);
			eventWriter.add(eventFactory.createDTD("\n"));
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean processEvent(KPS k) {
		return k.processEvent(this);
	}
	
	/**
	 * returns a string representation of the transport cost update
	 * @return text
	 */
	public String toString(){
		return "Transport Cost Update: " + orig + " to " + dest + " via " + priority;
	}

	@Override
	public String getEventName() {
		return "Customer Price Update";
	}

	@Override
	public String getContents() {
		return orig + "->" + dest +" by " + priority.toString() ;
	}
}

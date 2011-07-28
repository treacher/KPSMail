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
public class TransportDiscontinued extends Event {
	
	public final String company;
	public final Type type;
	
	public TransportDiscontinued(String tf , Type t , String orig, String dest){
	
		company = tf;
		type = t;
		this.orig = orig;
		this.dest = dest;
		
	}
	
	public TransportDiscontinued(Route r, TransportFirm t){
		company = t.getCompany();
		type = t.getType();
		this.orig = r.origin;
		this.dest = r.destination;
	}
	
	/**
	 * Turns this event into XML
	 */
	public void toXML(XMLEventWriter eventWriter){
		
		try {
			
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			StartElement sElement = eventFactory.createStartElement("", "", "discontinue");
			eventWriter.add(eventFactory.createDTD("\t"));
			eventWriter.add(sElement);
			eventWriter.add(eventFactory.createDTD("\n"));
			writeXMLElement(eventWriter , "company" , company);
			writeXMLElement(eventWriter, "to" , dest);
			writeXMLElement(eventWriter, "from" , orig);
			writeXMLElement(eventWriter, "type" , type.getText());
			EndElement eElement = eventFactory.createEndElement("", "", "discontinue");
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
	public String getEventName(){
		return "Transport Firm Discontinued";
	}
	
	/**
	 * Returns the details of this discontinue event as a string
	 * @return String
	 */
	public String getContents(){
		return company +" discontinued from " + orig + "->" + dest ;
	}
	
	
}

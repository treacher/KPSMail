package kpsMail;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Abstract class for event class structure
 * @author SeanAnderson & CalvinKaye
 */
public abstract class Event {

	protected String orig;
	protected String dest;
	
	public String getOrigin(){
		return orig;
	}
	
	public String getDest(){
		return dest;
	}

	public abstract void toXML(XMLEventWriter eventWriter);
	
	public void writeXMLElement(XMLEventWriter eventWriter, String name , String value){
		
		try {
			
			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			XMLEvent end = eventFactory.createDTD("\n");
			XMLEvent tab = eventFactory.createDTD("\t");
			StartElement sElement = eventFactory.createStartElement("", "", name);
			eventWriter.add(tab);
			eventWriter.add(tab);
			eventWriter.add(sElement);
			Characters characters = eventFactory.createCharacters(value);
			eventWriter.add(characters);
			EndElement eElement = eventFactory.createEndElement("", "", name);
			eventWriter.add(eElement);
			eventWriter.add(end);
		
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}	
	/**
	 * string representation of event name
	 * @return string
	 */
	public abstract String getEventName();
	
	/**
	 * String representation of class information
	 * @return string
	 */
	public abstract String getContents();
	
	/**
	 * tells KPS how to process this event (visitor pattern)
	 * @param kps
	 * @return successfully processed
	 */
	public abstract boolean processEvent(KPS k);
	
}

package kpsMail;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;

/**
 * Class to represent a mail delivery event
 * @author Sean Anderson
 *
 */
public class MailDelivery extends Event {

	public final double weight;
	public final double volume;
	public final String day;
	public String type;
	public Priority priority;


	/**
	 * create a mail delivery event
	 * @param destination
	 * @param origin
	 * @param type
	 * @param weight
	 * @param volume
	 * @param day
	 */
	public MailDelivery(String destination, String origin, String type, double weight,
			double volume, String day){

		dest = destination;
		orig = origin;
		this.type = type;


		this.weight = weight;
		this.volume = volume;
		this.day = day;
	}
	/**
	 * create a mail delivery event
	 * @param destination
	 * @param origin
	 * @param p
	 * @param weight
	 * @param volume
	 * @param day
	 */
	public MailDelivery(String destination, String origin, Priority p, double weight,
			double volume, String day){

		dest = destination;
		orig = origin;
		priority = p;

		this.weight = weight;
		this.volume = volume;
		this.day = day;
	}

	/**
	 * writes mail delivery to an XMLEventWriter in xml format
	 */
	public void toXML(XMLEventWriter eventWriter){

		try {

			XMLEventFactory eventFactory = XMLEventFactory.newInstance();
			StartElement sElement = eventFactory.createStartElement("", "", "mail");
			eventWriter.add(
					eventFactory.
					createDTD("\t")
			);
			eventWriter.add(sElement);
			eventWriter.add(eventFactory.createDTD("\n"));
			writeXMLElement(eventWriter , "day" , day);
			writeXMLElement(eventWriter, "to" , dest);
			writeXMLElement(eventWriter, "from" , orig);
			writeXMLElement(eventWriter, "weight" , Double.toString(weight));
			writeXMLElement(eventWriter, "volume" , Double.toString(volume));
			writeXMLElement(eventWriter, "priority" , priority.toString());
			EndElement eElement = eventFactory.createEndElement("", "", "mail");
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

	@Override
	public String getEventName() {
		return "Package Delivery";
	}

	@Override
	public String getContents() {
		return orig + "->" + dest + " on " + day +" by " + priority;
	}
}

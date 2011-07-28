package kpsMail;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Class for saving and writing events in XML format to a file
 * @author CalvinKaye
 *
 */
public class LogManager {
	
	private XMLEventReader eventReader;
	List<Event> events = new ArrayList<Event>();
	Stack<String> xmlStack = new Stack<String>();
	private XMLEventWriter eventWriter;
	private XMLEvent prologue;
	private XMLEvent firstTag;
	private String logFile;
	
	/**
	 * creates a log manager from a filename
	 * @param logFile filename
	 */
	public LogManager(String logFile){
		this.logFile = logFile;
		File f = new File("kpslogfile.xml");
		if(!f.exists()){
			try {
				f.createNewFile();
				BufferedWriter bw = new BufferedWriter(new FileWriter(f));
				bw.write("<?xml version=\"1.0\"?>\n");
				bw.write("<simulation xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:noNamespaceSchemaLocation=\"postal.xsd\">\n");
				bw.write("</simulation>");
				bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		  }
		}
		try {
			//Setting up xml reading variables
			events = new ArrayList<Event>();
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();
			InputStream input = new FileInputStream(logFile);
			eventReader = inputFactory.createXMLEventReader(input);
			
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
			
			readLogFile();
			
		//writeEventsToLog(events);
	}
	
	/**
	 * reads an XML file and adds events to events list
	 */
	public void readLogFile(){
		
		try {
			prologue = eventReader.nextEvent();
		} catch (XMLStreamException e2) {
			e2.printStackTrace();
		}
		
		String fTag = "";
		//find all header tags
		try {
			firstTag = eventReader.nextEvent();
			fTag = firstTag.asStartElement().getName().getLocalPart();
			xmlStack.push(fTag);
		}catch (XMLStreamException e1){
			e1.printStackTrace();
		}
		
		while( eventReader.hasNext()){
			try {
				XMLEvent event = eventReader.nextEvent();
				if(event.isStartElement()){
					StartElement startE = event.asStartElement();
					
					//mail event
					if(startE.getName().getLocalPart().equals("mail")){
						xmlStack.push("mail");
						Event e = createMailDelivery();
						events.add(e);
					}
					//cost update event
					else if(startE.getName().getLocalPart().equals("cost")){
						xmlStack.push("cost");
						Event e = createTransportCostUpdate();
						events.add(e);					
					}
					//price update event
					else if(startE.getName().getLocalPart().equals("price")){
						xmlStack.push("price");
						Event e = createCustomerPriceUpdate();
						events.add(e);
					}
					//transport discontinued event
					else if(startE.getName().getLocalPart().equals("discontinue")){
						xmlStack.push("discontinue");
						Event e = createTransportDiscontinued();
						events.add(e);
					}
				}
				else if(event.isEndElement()){
					validateClosingTag(event);
				}
			} catch (XMLStreamException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * creates a mail delivery events from event reader
	 * @return mail delivery
	 */
	public MailDelivery createMailDelivery(){
		
		String day = "";
		String to = "";
		String from = "";
		double weight = 0.0;
		double volume = 0.0;
		String priority = "";
		
		while( eventReader.hasNext()){
			try {
				XMLEvent event = eventReader.nextEvent();
				
				if(event.isStartElement()){
					StartElement startE = event.asStartElement();
					
					if(startE.getName().getLocalPart().equals("day")){
						xmlStack.push("day");
						event = eventReader.nextEvent();
						day = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("to")){
						xmlStack.push("to");
						event = eventReader.nextEvent();
						to = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("from")){
						xmlStack.push("from");
						event = eventReader.nextEvent();
						from = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("weight")){
						xmlStack.push("weight");
						event = eventReader.nextEvent();
						weight = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("volume")){
						xmlStack.push("volume");
						event = eventReader.nextEvent();
						volume = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("priority")){
						xmlStack.push("priority");
						event = eventReader.nextEvent();
						priority = event.asCharacters().getData();
						continue;
					}
				}
				if(event.isEndElement()){
					validateClosingTag(event);
					
					if(event.asEndElement().getName().getLocalPart().equals("mail")){
						break;
					}
				}
			} catch (XMLStreamException e) {
					e.printStackTrace();
			}
		}
		

		return new MailDelivery(to, from, Priority.fromString(priority),  weight,
				volume, day);
	}
	/**
	 * checks for closing tags, correct nested structure
	 * @param event
	 */
	public void validateClosingTag(XMLEvent event){
		
		EndElement endElement = event.asEndElement();
		String closingTag = endElement.getName().getLocalPart();
		String peek = xmlStack.peek();
		
		if(!closingTag.equals(xmlStack.pop())){
			try {
				throw new Exception("Invalid XML : closing tag + " + closingTag + " does not match " + peek);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * creates a transport discontinued event from event reader
	 * @return transport discontinued event
	 */
	public TransportDiscontinued createTransportDiscontinued(){
		
		String company = "";
		String to = "";
		String from = "";
		String type = "";
		
		while( eventReader.hasNext()){
			try {
				XMLEvent event = eventReader.nextEvent();
				
				if(event.isStartElement()){
					StartElement startE = event.asStartElement();
					
					if(startE.getName().getLocalPart().equals("to")){
						xmlStack.push("to");
						event = eventReader.nextEvent();
						to = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("from")){
						xmlStack.push("from");
						event = eventReader.nextEvent();
						from = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("type")){
						xmlStack.push("type");
						event = eventReader.nextEvent();
						type = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("company")){
						xmlStack.push("company");
						event = eventReader.nextEvent();
						company = event.asCharacters().getData();
						continue;
					}
				}
				if(event.isEndElement()){
					validateClosingTag(event);
					
					if(event.asEndElement().getName().getLocalPart().equals("discontinue")){
						break;
					}
					
				}
			} catch (XMLStreamException e) {
					e.printStackTrace();
			}
		}
		

		return new TransportDiscontinued(company , Type.fromString(type) , from, to );
		
	}
	/**
	 * create customer price update event from event reader
	 * @return customer price update
	 */
	public CustomerPriceUpdate createCustomerPriceUpdate(){
		
		String to = "";
		String from = "";
		double weightCost = 0.0;
		double volumeCost = 0.0;
		String priority = "";
		
		while( eventReader.hasNext()){
			try {
				XMLEvent event = eventReader.nextEvent();
				
				if(event.isStartElement()){
					StartElement startE = event.asStartElement();
					
					if(startE.getName().getLocalPart().equals("to")){
						xmlStack.push("to");
						event = eventReader.nextEvent();
						to = event.asCharacters().getData();
						
						continue;
					}
					else if(startE.getName().getLocalPart().equals("from")){
						xmlStack.push("from");
						event = eventReader.nextEvent();
						from = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("weightcost")){
						xmlStack.push("weightcost");
						event = eventReader.nextEvent();
						weightCost = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("volumecost")){
						xmlStack.push("volumecost");
						event = eventReader.nextEvent();
						volumeCost = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("priority")){
						xmlStack.push("priority");
						event = eventReader.nextEvent();
						priority = event.asCharacters().getData();
						continue;
					}
				}
				if(event.isEndElement()){
					validateClosingTag(event);
					
					if(event.asEndElement().getName().getLocalPart().equals("price")){
						break;
					}
				}
			} catch (XMLStreamException e) {
					e.printStackTrace();
			}
		}

		return new CustomerPriceUpdate(to, from, Priority.fromString(priority), weightCost, volumeCost);
		
	}
	/**
	 * creates a transport cost update from event reader
	 * @return transport cost update
	 */
	public TransportCostUpdate createTransportCostUpdate(){
		
		String company = "";
		String day = "";
		String to = "";
		String from = "";
		double weightCost = 0.0;
		double volumeCost = 0.0;
		double maxWeight = 0.0;
		double maxVolume = 0.0;
		int duration = 0;
		int frequency = 0;
		String type = "";
		
		while( eventReader.hasNext()){
			try {
				XMLEvent event = eventReader.nextEvent();
				
				if(event.isStartElement()){
					StartElement startE = event.asStartElement();
					
					if(startE.getName().getLocalPart().equals("day")){
						xmlStack.push("day");
						event = eventReader.nextEvent();
						day = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("to")){
						xmlStack.push("to");
						event = eventReader.nextEvent();
						to = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("from")){
						xmlStack.push("from");
						event = eventReader.nextEvent();
						from = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("weightcost")){
						xmlStack.push("weightcost");
						event = eventReader.nextEvent();
						weightCost = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("volumecost")){
						xmlStack.push("volumecost");
						event = eventReader.nextEvent();
						volumeCost = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("type")){
						xmlStack.push("type");
						event = eventReader.nextEvent();
						type = event.asCharacters().getData();
						continue;
					}
					else if(startE.getName().getLocalPart().equals("maxWeight")){
						xmlStack.push("maxWeight");
						event = eventReader.nextEvent();
						maxWeight = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("maxVolume")){
						xmlStack.push("maxVolume");
						event = eventReader.nextEvent();
						maxVolume = Double.parseDouble(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("duration")){
						xmlStack.push("duration");
						event = eventReader.nextEvent();
						duration = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("frequency")){
						xmlStack.push("frequency");
						event = eventReader.nextEvent();
						frequency = Integer.parseInt(event.asCharacters().getData());
						continue;
					}
					else if(startE.getName().getLocalPart().equals("company")){
						xmlStack.push("company");
						event = eventReader.nextEvent();
						company = event.asCharacters().getData();
						continue;
					}
				}
				if(event.isEndElement()){
					validateClosingTag(event);
					
					if(event.asEndElement().getName().getLocalPart().equals("cost")){
						break;
					}
				}
			} catch (XMLStreamException e) {
					e.printStackTrace();
			}
		}

		return new TransportCostUpdate(company, to, from, Type.fromString(type) , 
				weightCost, maxWeight, volumeCost, maxVolume, duration, frequency,
				Day.fromString(day));
		
	}
	/**
	 * save a list of events to a log file
	 * @param es
	 */
	public void writeEventsToLog(List<Event> es){
		
		//Setting up xml writing variables

		try {
			XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
			eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(logFile));
		} catch (FileNotFoundException e2) {
			e2.printStackTrace();
		} catch (XMLStreamException e2) {
			e2.printStackTrace();
		}

		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		
		try {
			
			eventWriter.add(prologue);
			eventWriter.add(end);
			eventWriter.add(firstTag);
			eventWriter.add(end);
			
		for(Event e : es){
			writeEventToLog(e);
		}
		
			eventWriter.add(eventFactory.createEndElement("", "", firstTag.asStartElement().getName().getLocalPart()));
			eventWriter.add(eventFactory.createDTD("\n"));
			eventWriter.add(eventFactory.createEndDocument());
			eventWriter.close();

			
		} catch (XMLStreamException e1) {
			e1.printStackTrace();
		}
		
	}
	/**
	 * helper method to write events to log
	 * @param event
	 */
	private void writeEventToLog(Event e) {	
		e.toXML(eventWriter);
		
	}
}

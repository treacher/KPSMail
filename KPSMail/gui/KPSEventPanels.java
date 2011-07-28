package gui;

import java.awt.GridLayout;

import kpsMail.Event;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JPanel;

/**
 * Class to represent the KPS event panels
 * @author treachmich
 *
 */
public class KPSEventPanels extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private KPSMainFrame mainFrame;
	/**
	 * Constructs KPSEventPanels object
	 * @param mainFrame parent frame
	 */
	public KPSEventPanels(KPSMainFrame mainFrame){
		setLayout(new GridLayout(0,1));
		this.mainFrame = mainFrame;
		populateEvents(mainFrame.getKPS().events);
		
	}
	/**
	 * Populates each event panel with in the event panels panel
	 * @param events
	 */
	public void populateEvents(List<Event> events){
		EventPanel.resetCount();
		EventPanel e = null;
		List<Event> sortedEvents = new ArrayList<Event>(events);
		Collections.reverse(sortedEvents);
		removeAll();
		for(Event ev : sortedEvents){
			e = new EventPanel(ev.getEventName(),ev.getContents(),this);
			add(e);
		}
		this.scrollRectToVisible(getBounds());
	}
	/**
	 * Get the main frame
	 * @return
	 */
	public KPSMainFrame getMainFrame(){
		return mainFrame;
	}
	/**
	 * Reset the current selected event panel
	 */
	public void resetPanel(){
		EventPanel.resetCurrentEvent();
	}
}

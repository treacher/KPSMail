
package gui;

import java.awt.Color;
import java.awt.Dimension;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class to represent the event panels containing information relevant to each event
 * @author treachmich
 *
 */
public class EventPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private JLabel indexLabel;
	private JLabel eventLabel;
	private JLabel contentsLabel;
	public static int EVENT_PANEL_HEIGHT = 50;
	private static EventPanel selectedEventPanel = null;
	public static final int HEIGHT = 40;
	public static final int WIDTH = 700;
	public static final int TEXT_HEIGHT = 20;
	public static final int START_BOUNDS = 10;
	public static final int INDEX_WIDTH = 40;
	public static final int EVENT_WIDTH = 185;
	public static final int CONTENTS_WIDTH = 700;
	private static int count = 0;
	private int index = 0;
	/**
	 * Construct an Event Panel
	 * @param text the text which the label will display
	 */
	public EventPanel(String event,String contents,final KPSEventPanels parent){
		count++; // incriment static count so that there is a count for each type of event objects
		index = count; // set an index for each event object
		setLayout(null);
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		indexLabel = new JLabel(count + ".");
		indexLabel.setBounds(START_BOUNDS,HEIGHT / 4,INDEX_WIDTH,TEXT_HEIGHT);
		add(indexLabel);
		// make event label
		eventLabel = new JLabel(event);
		eventLabel.setBounds(indexLabel.getX() + indexLabel.getWidth(), HEIGHT / 4, EVENT_WIDTH, TEXT_HEIGHT);
		add(eventLabel);
		
		// make contents label
		contentsLabel = new JLabel(contents);
		contentsLabel.setBounds(eventLabel.getX() + eventLabel.getWidth(), HEIGHT / 4, CONTENTS_WIDTH, TEXT_HEIGHT);
		add(contentsLabel);
		
		// set up the event panel
		setBorder(BorderFactory.createLoweredBevelBorder());
		this.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(!parent.getMainFrame().isManager()){
					return;
				}
				// currently selected change back to un selected
					if(EventPanel.this.equals(selectedEventPanel)){
						selectedEventPanel.setBackground(null);
						selectedEventPanel = null;
						repaint();
					}
					//currently unselected change to selected
					else if( selectedEventPanel != null){
						selectedEventPanel.setBackground(null);
						selectedEventPanel = EventPanel.this;
						selectedEventPanel.setBackground(Color.gray);
						repaint();
					}
					else{
						selectedEventPanel = EventPanel.this;
						selectedEventPanel.setBackground(Color.gray);
						repaint();
					}
				}
			
		});
		
	}
	/**
	 * Gets the index value
	 * @return index value
	 */
	public int getIndex(){
		return index;
	}
	/**
	 * Method to get the currently selected event panel
	 * @return EventPanel
	 */
	public static EventPanel getSelectedEventPanel(){
		return selectedEventPanel;
	}
	/**
	 * Resets the current event so that it becomes un selected
	 */
	public static void resetCurrentEvent(){
		if(selectedEventPanel != null){
			selectedEventPanel.setBackground(null);
			selectedEventPanel = null;
		}
		else {
			selectedEventPanel = null;
		}
	}
	/**
	 * Reset the count of how many event panels are currently made
	 */
	public static void resetCount(){
		count = 0;
	}
	/**
	 * Gets the count of how many event panels are made
	 * @return
	 */
	public static int getCount(){
		return count;
	}
	
}


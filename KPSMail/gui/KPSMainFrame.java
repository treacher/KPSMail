package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

import kpsMail.Event;
import kpsMail.KPS;

/**
 * Class to represent the main GUI frame for KPS Mail system
 * @author treachmich
 *
 */
public class KPSMainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int HEIGHT = 598;
	public static final int WIDTH = 800;
	public static final int MENU_ERROR = 15;
	private JScrollPane scroller;
	private KPSEventPanels eventPanels;
	private boolean isManager = false;
	private NavigationPanel navPanel;
	private KPS kps;
	private JPasswordField passwordField;
	private final int passwordHash = 1253706213;
	
	/**
	 * Constructs the KPS Main Frame
	 */
	public KPSMainFrame(KPS kps){
		super("KPS Mail - Synergy Software Systems | Current Mode: Clerk");
		this.kps = kps;
		navPanel= new NavigationPanel(this);
		this.setLayout(null);
		setupMenu();
		this.setSize(WIDTH,HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(navPanel);
		add(new TitlePanel());
		setupScroller();
		updateStatsBox();
		this.setResizable(false);
		this.setVisible(true);
		reportBadEvents(kps.getBadEvents());
	}
	/**
	 * Reports the bad events that occured when the xml was processed
	 * @param badEvents
	 */
	private void reportBadEvents(List<Event> badEvents) {
		if(badEvents.isEmpty()){
			JOptionPane.showMessageDialog(null, "All events loaded successfully!");
		}
		else{
			String errorHeader = "The following events were not processed: \n\n\n";
			String error = "";
			for(Event e : badEvents){
				error+= e.getEventName() + '\t'+ e.getContents() + '\n';
			}
			JOptionPane.showMessageDialog(null, errorHeader + '\t' +  error);
		}
	}

	public void updatePanels(){
		eventPanels.populateEvents(kps.events);
	}
	/**
	 * Sets up the scroller
	 */
	public void setupScroller(){
		scroller = new JScrollPane();
		eventPanels = new KPSEventPanels(this);
		scroller.setBounds(0,TitlePanel.TITLE_HEIGHT, TitlePanel.TITLE_WIDTH + 1, KPSMainFrame.HEIGHT - TitlePanel.TITLE_HEIGHT - 47);	
		scroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scroller.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setViewportView(eventPanels);
		scroller.setAutoscrolls(true);
		add(scroller);
	}
	/**
	 * Get the KPS object
	 * @return 
	 */
	public KPS getKPS(){
		return kps;
	}
	/**
	 * Sets up the menu bar
	 */
	public void setupMenu(){
		JMenuBar menubar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		fileMenu.getPopupMenu().setLightWeightPopupEnabled(false);
		final JMenuItem modeChange = new JMenuItem("Manager Mode");
		passwordField = new JPasswordField("");
		modeChange.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordField.setText("");
				JComponent[] inputs = {
						new JLabel("Password:"),passwordField
				};
				isManager = !isManager;
				
				if(isManager){
					JOptionPane.showMessageDialog(KPSMainFrame.this, inputs,"Manager Password",JOptionPane.QUESTION_MESSAGE);
					isManager = hashPassword(passwordField.getPassword())==passwordHash;
					if(!isManager){
						showDialog("Incorrect Password");
						return;
					}
				}
				if(isManager){
					KPSMainFrame.this.setTitle("KPS Mail - Synergy Software Systems | Current Mode: Manager");
					kps.switchMode(isManager);
					modeChange.setText("Clerk Mode");
				}
				else {
					KPSMainFrame.this.setTitle("KPS Mail - Synergy Software Systems | Current Mode: Clerk");
					kps.switchMode(isManager);
					modeChange.setText("Manager Mode");
				}
				eventPanels.resetPanel();
			}
			
		});
		fileMenu.add(modeChange);
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				exit();
			}
			
		});
		fileMenu.add(exit);
		JMenu helpMenu = new JMenu("Help");
		JMenuItem about = new JMenuItem("About");
		helpMenu.getPopupMenu().setLightWeightPopupEnabled(false);
		about.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				KPSMainFrame.this.showDialog( 
						"KPS Mail System\n\n" +
						"Synergy Software Systems\n\n"+
						"Michael Treacher\n"+
						"Calvin Kaye\n"+
						"Sean Anderson\n"+
						"Damian Kaye\n\n"+
						"\u00a9 2011"
						);
				
			}
			
		});
		helpMenu.add(about);
		menubar.add(fileMenu);
		menubar.add(helpMenu);
		setJMenuBar(menubar);
	}
	/**
	 * Sets the manager boolean
	 * @return isManager
	 */
	public boolean isManager(){
		return isManager;
	}
	/**
	 * Shows a dialog with the string as the given message
	 * @param message
	 */
	public void showDialog(String message){
		JOptionPane.showMessageDialog(null, message,"KPS Mail System",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void updateStatsBox(){
		navPanel.getStatisticsBox().setupValues(kps.getStats().totalRevenue(), kps.getStats().totalExpenditure(), kps.getStats().totalNumberOfEvents());
	}
	
	public int hashPassword(char[] password){
			String passwordString = String.valueOf(password);
			return passwordString.hashCode() * 19;
	}
	/**
	 * Exits the client
	 */
	public void exit(){
		int option = JOptionPane.showConfirmDialog(KPSMainFrame.this,"Are you sure you want to exit?","Exit Game?",JOptionPane.YES_NO_OPTION); 
		// Possibly a save option here.
		if(option == JOptionPane.YES_OPTION)System.exit(0);
	}

}

package gui;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


/**
 * Class to represent the Trace button panel
 * @author treachmich
 *
 */
public class TraceButtonPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton forwardTrace;
	private JButton backwardTrace;
	private JButton exitButton;
	private Icon leftIcon;
	private Icon rightIcon;
	public static final int BUTTON_WIDTH = 60;
	public static final int BUTTON_HEIGHT = 65;
	/**
	 * Constructs a trace button panel
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param parent
	 * @param isManager
	 */
	public TraceButtonPanel(int x,int y,int width,int height,StatisticsDialog parent,boolean isManager){
		
		setLayout(null);
		setBounds(x,y,width,height);
		// setup the trace buttons if user is a manager
		
		if(isManager){
			leftIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("leftArrow.png")).getScaledInstance(BUTTON_WIDTH - 5, BUTTON_HEIGHT - 5,Image.SCALE_SMOOTH));
			rightIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("rightArrow.png")).getScaledInstance(BUTTON_WIDTH - 5, BUTTON_HEIGHT - 5,Image.SCALE_SMOOTH));
			setupTraceBackwardButton();
			setupTraceForwardButton();
		}
		setupExitButton();
	}
	/**
	 * Sets up the trace forward button
	 */
	private void setupTraceForwardButton(){
		forwardTrace = new JButton();
		forwardTrace.setBounds((getWidth() - BUTTON_WIDTH) - 5,getHeight()/4,BUTTON_WIDTH,BUTTON_HEIGHT);
		forwardTrace.setIcon(rightIcon);
		add(forwardTrace);
	}
	/**
	 * Sets up the trace backwards button
	 */
	private void setupTraceBackwardButton(){
		backwardTrace = new JButton();
		backwardTrace.setBounds(5,(getHeight()/4),BUTTON_WIDTH,BUTTON_HEIGHT);
		backwardTrace.setIcon(leftIcon);
		add(backwardTrace);
	}
	/**
	 * Sets up the exit button
	 */
	public void setupExitButton(){
		exitButton = new JButton("Exit");
		exitButton.setBounds(BUTTON_WIDTH + 15,getHeight()/4 , 200, BUTTON_HEIGHT);
		add(exitButton);
	}
	/**
	 * Get the exit button
	 * @return
	 */
	public JButton getExitButton(){
		return exitButton;
	}
	/**
	 * Get the backward button
	 * @return backward button
	 */
	public JButton getBackButton(){
		return backwardTrace;
	}
	/**
	 * Get the forward button
	 * @return forward button
	 */
	public JButton getForwardButton(){
		return forwardTrace;
	}
	
}

package gui;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
/**
 * Class to represent the title panel
 * @author treachmich
 *
 */
public class TitlePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int TITLE_HEIGHT = 60;
	public static int TITLE_WIDTH = KPSMainFrame.WIDTH - NavigationPanel.NAVIGATION_PANEL_WIDTH;
	private JLabel title;
	/**
	 * Constructs the titl panel;
	 */
	public TitlePanel(){
		setLayout(null);
		setBounds(0,0,TITLE_WIDTH,TITLE_HEIGHT);
		this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		title = new JLabel("KPS MAIL");
		title.setFont(new Font(Font.SANS_SERIF,Font.PLAIN,40));
		title.setBounds(TITLE_WIDTH/3,0,(TITLE_WIDTH/3)*3,(TITLE_HEIGHT/3)*3);
		add(title);
	}

}

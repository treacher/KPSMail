package gui;

import kpsMail.KPS;
/**
 * Class to represent main
 * @author treachmich
 *
 */
public class Main {
	/**
	 * Main method to run kps mail system
	 * @param args
	 */
	public static void main(String[] args){
		KPS kps = new KPS();
		new KPSMainFrame(kps);
	}

}

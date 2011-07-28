package kpsMail;

/**
 * Class for representing names of days in a week
 * @author CalvinKaye
 */
public enum DayNames {
	Monday("Monday"), Tuesday("Tuesday"), Wednesday("Wednesday"), Thursday("Thursday"),
	Friday("Friday"), Saturday("Saturday"), Sunday("Sunday");
	
	private String text;
	
	DayNames(String text){
		this.text = text;
	}
	
	/**
	 * returns a day from a string
	 * 
	 * @param text
	 * @return
	 */
	public static DayNames fromString(String text){

		if(text != null){
			for(DayNames d : DayNames.values()){
				if(text.equalsIgnoreCase(d.text)){
					return d;
				}
			}
		}
		return null;
	}

	/**
	 * returns String day name
	 * @return day name
	 */
	public String toString(){
		return text;
	}
}

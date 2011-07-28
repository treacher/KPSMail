package kpsMail;

/**
 * Class for representing days in a week
 * @author SeanAnderson & CalvinKaye
 */
public enum Day {
	Monday("Monday"), Tuesday("Tuesday"), Wednesday("Wednesday"), Thursday("Thursday"),
	Friday("Friday"), Saturday("Saturday"), Sunday("Sunday");

	private String text;

	/**
	 * creates a day from a string
	 * @param text
	 */
	Day(String text){
		this.text = text;
	}

	/**
	 * returns the string name of the day
	 * @return dayname
	 */
	public String getText(){
		return this.text;
	}

	/**
	 * returns day from a given string
	 * @param day name
	 * @return
	 */
	public static Day fromString(String text){
		//find Day that matches text
		if(text != null){
			for(Day d : Day.values()){
				if(text.equalsIgnoreCase(d.text)){
					return d;
				}
			}
		}
		return null;
	}
	
	
	/**
	 * Return the next day.
	 * @return
	 */
	public Day nextDay(){
		
		
		if(this == Sunday){
			return Day.Monday;
		}
		else{
			return Day.values()[this.ordinal()+1];
		}
		
		
	}
	
	/**
	 * Calculate the difference between 2 days in hours.
	 * @param d
	 * @return
	 */
	public int difference(Day d){
				
		if(this == d){
			return 0;
		}
		else{
			return 24 + this.nextDay().difference(d);
		}
	}
	
}


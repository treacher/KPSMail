package kpsMail;

/**
 * Represent priorities for mail
 * @author CalvinKaye
 *
 */
public enum Priority {
	
	InternationalAir("International Air"), InternationalStandard("International Standard"), 
	DomesticAir("Domestic Air"), DomesticStandard("Domestic Standard");
	
	private String text;
	
	/**
	 * Create a priority from a string
	 * @param text
	 */
	Priority(String text){
		this.text = text;
	}
	
	/**
	 * tells whether the priority is sent via air
	 * @return priority via air
	 */
	public boolean isAir(){
		return this == InternationalAir || this == DomesticAir;
	}
	
	/**
	 * get string representation of the priority
	 * @return priority name
	 */
	public String toString(){
		return this.text;
	}
	
	/**
	 * create a priority from a string
	 * @param text
	 * @return priority
	 */
	public static Priority fromString(String text){
		
		if(text != null){
			for(Priority p : Priority.values()){
				if(text.equalsIgnoreCase(p.text)){
					return p;
				}
			}
		}
		return null;
	}
}


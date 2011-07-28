package kpsMail;

/**
 * 
 * @author kayedami
 *
 */
public enum Type {
	Air("Air"), Land("Land"), Sea("Sea");
	
	
	
	private String text;
	
	Type(String text){
		this.text = text;
	}
	
	/**
	 * Getter method, returns the string of the enum
	 * @return String
	 */
	
	public String getText(){
		return this.text;
	}
	
	/**
	 * Checks to see if this enum is air or not.
	 * 
	 * @return boolean, representing if it is air or not.
	 */
	public boolean isAir(){
		
		return this == Air;
		
	}
	
	/**
	 * 
	 * Works out the type from the text
	 * 
	 * @param text , string representation of the type
	 * @return Type , representing the type from the text
	 */
	public static Type fromString(String text){
		
		if(text != null){
			for(Type t : Type.values()){
				if(text.equalsIgnoreCase(t.text)){
					return t;
				}
			}
		}
		return null;
	}
}

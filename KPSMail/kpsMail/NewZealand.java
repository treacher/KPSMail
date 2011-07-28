package kpsMail;


/**
 * Places in New Zealand
 * @author CalvinKaye
 */
public enum NewZealand {
	
	Auckland("Auckland"),
	Bay_of_Islands("Bay of Islands"),
	Blenheim("Blenheim"),
	Christchurch("Christchurch"),
	Dunedin("Dunedin"),
	Gisborne("Gisborne"),
	Hamilton("Hamilton"),
	Hokitika("Hokitika"),
	Invercargill("Invercargill"),
	Kaitaia("Kaitaia"),
	Kapiti_Coast("Kapiti Coast"),
	Kerikeri("Kerikeri"),
	Marlborough("Marlborough"),
	Masterton("Masterton"),
	Napier("Napier"),
	Nelson("Nelson"),
	New_Plymouth("New Plymouth"),
	Palmerston_North("Palmerston North"),
	Paraparaumu("Paraparaumu"),
	Queenstown("Queenstown"),
	Rotorua("Rotorua"),
	Taupo("Taupo"),
	Tauranga("Tauranga"),
	Timaru("Timaru"),
	Wanaka("Wanaka"),
	Wanganui("Wanganui"),
	Wellington("Wellington"),
	Westport("Westport"),
	Whakatane("Whakatane"),
	Whangarei("Whangarei");
	
	private String name;
	
	/**
	 * create NewZealand city from a string
	 * @param city name
	 */
	NewZealand(String name){
		this.name = name;
	}
	
	/**
	 * get string representation of New Zealand City
	 * @return city name
	 */
	public String toString(){
		return name;
	}
	
	/**
	 * check if a city is in New Zealand
	 * @param cityName
	 * @return
	 */
	public static boolean contains(String cityName){
		for(NewZealand city : NewZealand.values()){
			if(city.toString().equals(cityName)){
				return true;
			}
		}	
		return false;	
	}
}

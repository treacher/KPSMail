package tests;

import kpsMail.Day;
import kpsMail.DeliveredPackage;
import kpsMail.MailDelivery;
import kpsMail.Priority;
import kpsMail.Route;
import kpsMail.TransportCostUpdate;
import kpsMail.TransportDiscontinued;
import kpsMail.TransportFirm;
import kpsMail.Type;

public class TestObjects {

	String city1 = "Auckland";
	String city2 = "Wellington";

	public Route createRoute1(){

		String origin = city2;
		String destination = city1;
		Priority p = Priority.DomesticStandard;
		Double wCost = 5.0;
		Double vCost = 4.0;

		return new Route(origin, destination, p, wCost, vCost);

	}

	public Route createRoute2(){

		String origin = city2;
		String destination = city1;
		Priority p = Priority.DomesticAir;
		Double wCost = 6.0;
		Double vCost = 3.0;

		return new Route(origin, destination, p, wCost, vCost);

	}
	
	public Route createRoute3(){

		String origin = city1;
		String destination = city2;
		Priority p = Priority.InternationalStandard;
		Double wCost = 6.0;
		Double vCost = 3.0;

		return new Route(origin, destination, p, wCost, vCost);

	}
	
	public MailDelivery createMD1(){
		String destination = "Auckland";
		String origin = "Wellington";
		Priority p = Priority.DomesticAir;
		double weight = 5.0;
		double volume = 2.0;
		String day = "Thursday";
		
		return new MailDelivery(destination, origin, p, weight, volume, day);
	}
	
	public MailDelivery createMD2(){
		
		String destination = "Auckland";
		String origin = "Wellington";
		Priority p = Priority.DomesticStandard;
		double weight = 15.0;
		double volume = 12.0;
		String day = "Tuesday";
		
		return new MailDelivery(destination, origin, p, weight, volume, day);
		
	}
	
	public MailDelivery createMD3(){
		
		String destination = "Auckland";
		String origin = "Wellington";
		Priority p = Priority.DomesticAir;
		double weight = 1.0;
		double volume = 1.0;
		String day = "Thursday";
		
		return new MailDelivery(destination, origin, p, weight, volume, day);
		
	}
	
	
	public MailDelivery createMD4(){
		
		String destination = "Sydney";
		String origin = "Wellington";
		Priority p = Priority.DomesticAir;
		double weight = 1.0;
		double volume = 1.0;
		String day = "Thursday";
		
		return new MailDelivery(destination, origin, p, weight, volume, day);
		
	}

	public TransportFirm createTransportFirm1() {
		
		String name = "mike";
		double weightC = 5.0;
		double volC = 5.0;
		double maxW = 10.0;
		double maxV = 15.0;
		int dura = 5;
		int freq = 3;
		Day d = Day.Thursday;
		Route r = createRoute2();
		Type type = Type.Air;
						
		return new TransportFirm(name,weightC,volC,maxW,maxV,dura,freq,d,r,type);
	}
	
	public DeliveredPackage createDeliveredPackage1(){
		
		MailDelivery m = createMD1();
		createRoute3();
		double price = 5.0;
		double cost = 5.0;
		double delTime = 10.0;
		
		return new DeliveredPackage(m,price,cost,delTime);
		
	}
	
	public DeliveredPackage createDeliveredPackage2(){
		
		MailDelivery m = createMD2();
		createRoute3();
		double price = 20.0;
		double cost = 5.0;
		double delTime = 10.0;
		
		return new DeliveredPackage(m,price,cost,delTime);
		
	}
	
	public TransportCostUpdate createTransportCostUpdate1(){
		
		String company = "mike";
		String dest = "Auckland";
		String orig = "Wellington";
		Type t = Type.Air;
		double wCost = 5.0;
		double maxW = 10.0;
		double vCost = 5.0;
		double maxV = 15.0;
		int dur = 5;
		int freq = 3;
		Day d = Day.Thursday;
		
		return new TransportCostUpdate(company,dest,orig,t,wCost,maxW,vCost,maxV,dur,freq,d);
	}
	
	public TransportDiscontinued createTransportDiscontinued(){
		
		String tf ="mike";
		Type t = Type.Air;
		String orig = "Wellington";
		String dest = "Auckland";		
		return new TransportDiscontinued(tf,t,orig,dest);
		
	}
}

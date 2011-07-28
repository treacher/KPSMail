package tests;

import static org.junit.Assert.*;
import kpsMail.DeliveredPackage;
import kpsMail.MailDelivery;
import kpsMail.Route;
import kpsMail.TransportCostUpdate;
import kpsMail.TransportFirm;

import org.junit.*;

public class EventTests  {

	TestObjects t = new TestObjects();
	
	
	
	@Test
	public void testEqualsValid(){
		assertTrue(t.createRoute1().equals(t.createRoute1()));
	}
	
	@Test
	public void testEqualsInvalid(){
		assertFalse(t.createRoute1().equals(t.createRoute2()));
		assertFalse(t.createRoute2().equals(t.createRoute3()));
	}
	
	@Test 
	public void testAddTransportFirm(){
		Route r = t.createRoute1();		
		r.addTransportFirm(t.createTransportFirm1());
		
		assertTrue(r.addTransportFirm(t.createTransportFirm1()));
		
	}
	
	//Valid
	
	@Test 
	public void testGetTransportFirmValid(){
		
		Route r = t.createRoute1();		
		TransportFirm tf = t.createTransportFirm1();
		r.addTransportFirm(tf);
		MailDelivery md = t.createMD1();
		
		assertTrue(r.getTransportFirm(md).equals(tf));
				
	}
	
	//invalid
	@Test
	public void testGetTransportFirmInvalid(){
		
		Route r = t.createRoute1();			
		MailDelivery md = t.createMD2();
		TransportFirm tf = t.createTransportFirm1();
		r.addTransportFirm(tf);
		
		assertTrue(r.getTransportFirm(md) == null);
		
		
	}

	
	@Test
	public void testTransportFirmCompareTo(){
		
		TransportFirm tf1 = t.createTransportFirm1();
		TransportFirm tf2 = t.createTransportFirm1();
		
		assertTrue(tf1.compareTo(tf2) == 0);
		
	}
	
	@Test
	public void testTransportFirmEquals(){
		
		TransportFirm tf1 = t.createTransportFirm1();
		TransportFirm tf2 = t.createTransportFirm1();
		
		assertTrue(tf1.equals(tf2));
	}

	//testing transportCostUpdate
	
	@Test
	public void testAcceptPackageValid(){
		
		DeliveredPackage dp = t.createDeliveredPackage1();
		TransportCostUpdate tcu = t.createTransportCostUpdate1();
		
		assertTrue(tcu.acceptPackage(dp));
		
	}
	
	
	@Test
	public void testAcceptPackageInvalid(){
		
		DeliveredPackage dp = t.createDeliveredPackage2();
		TransportCostUpdate tcu = t.createTransportCostUpdate1();
		
		assertFalse(tcu.acceptPackage(dp));
		
	}
	
	@Test
	public void testPackageCost(){
		
		DeliveredPackage dp = t.createDeliveredPackage2();
		TransportCostUpdate tcu = t.createTransportCostUpdate1();
		
		assertTrue(tcu.packageCost(dp) == (dp.getWeight() * tcu.weightCost) + (dp.getVolume() * tcu.volumeCost));
	}
	
	
	
}

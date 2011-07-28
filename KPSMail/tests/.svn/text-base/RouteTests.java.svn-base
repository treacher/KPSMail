package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import kpsMail.MailDelivery;
import kpsMail.Route;
import kpsMail.TransportFirm;

import org.junit.Test;

public class RouteTests {

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
	public void testProcessPackage(){
		
		Route r = t.createRoute1();			
		r.processPackage(5, 2, 3, 10, 5);
		
		assertTrue(r.getTotalExpenditure() == 10);
		
	}
	
	@Test
	public void testCalculateCosts(){
		
		Route r = t.createRoute1();			
		double cost = r.calculatePrice(5, 5);		
		assertTrue(cost == 45);
		
		
	}
	
	//valid
	@Test
	public void testIsCritical1(){
		
		Route r = t.createRoute2();
		r.processPackage(5, 6, 32, 25, 8);
		assertTrue(r.isCritical());
		
		
	}
	//invalid
	
	@Test
	public void testIsCritical2(){
		
		Route r = t.createRoute2();
		r.processPackage(5, 6, 7, 3, 8);
		assertFalse(r.isCritical());
			
	}
	
	
	
}

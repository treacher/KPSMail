package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import kpsMail.Day;
import kpsMail.MailDelivery;
import kpsMail.Priority;
import kpsMail.Route;
import kpsMail.RouteManager;
import kpsMail.TransportCostUpdate;
import kpsMail.TransportDiscontinued;
import kpsMail.TransportFirm;
import kpsMail.Type;


public class RouteManagerTests {
	
	public RouteManager buildRouteManager(){
		
		RouteManager rm = new RouteManager();
		
		Route r1 = new Route("Wellington", "Auckland", Priority.DomesticAir, 10, 10);		
		Route r2 = new Route("Auckland", "Sydney", Priority.InternationalAir, 20, 20);
		Route r3 = new Route("Sydney", "Perth", Priority.DomesticAir, 30 , 30);
		Route r4 = new Route("Perth", "Mumbai", Priority.InternationalAir, 40, 40);
		Route r5 = new Route("Perth", "Singapore", Priority.InternationalAir, 40, 40);
		Route r6 = new Route("Sydney", "Singapore", Priority.InternationalAir, 60, 60);
		Route r7 = new Route("Auckland", "Perth", Priority.InternationalAir, 100, 100); 
		Route r8 = new Route("Singapore","Rome", Priority.InternationalAir, 150,150);
		Route r9 = new Route("Singapore","Mumbai", Priority.InternationalAir, 100,100);
		Route r10 = new Route("Mumbai" , "Rome", Priority.InternationalAir,50,50);
		Route r11 = new Route("Perth", "Rome", Priority.InternationalAir, 150,150);
		Route r12 = new Route("Rome" , "Paris", Priority.DomesticAir, 50,50 );
		Route r13 = new Route("Mumbai", "Paris",Priority.InternationalAir, 100,100);
		Route r14 = new Route("Wellington" , "Sydney", Priority.InternationalAir, 15,15);
		
		/*
		TransportFirm tf1 = new TransportFirm("",0,0,0,0,5,6,Day.Thursday,null, Type.Air);
		TransportFirm tf2 = new TransportFirm("",0,0,0,0,10,4,Day.Saturday,null, Type.Air);
		TransportFirm tf3 = new TransportFirm("",0,0,0,0,3,6,Day.Tuesday,null, Type.Air);
		TransportFirm tf4 = new TransportFirm("",0,0,0,0,15,8,Day.Wednesday,null, Type.Air);
		TransportFirm tf5 = new TransportFirm("",0,0,0,0,4,12,Day.Friday,null, Type.Air);
		TransportFirm tf6 = new TransportFirm("",0,0,0,0,10,3,Day.Sunday,null, Type.Air);
		*/
		
		rm.getRoutes().add(r1);
		rm.getRoutes().add(r2);
		rm.getRoutes().add(r3);
		rm.getRoutes().add(r4);
		rm.getRoutes().add(r5);
		rm.getRoutes().add(r6);
		rm.getRoutes().add(r7);		
		rm.getRoutes().add(r8);
		rm.getRoutes().add(r9);
		rm.getRoutes().add(r10);
		rm.getRoutes().add(r11);
		rm.getRoutes().add(r12);
		rm.getRoutes().add(r13);
		rm.getRoutes().add(r14);
		
		
		return rm;
		
		
	}
	
	
	
	//valid
	@Test
	public void testTranslateTransportType1(){
		
		RouteManager rm = new RouteManager();
		
		String origin = "Wellington";
		String dest = "Auckland";
		Type t = Type.Air;
		
		assertEquals(rm.translateTransportType(origin,dest,t) , Priority.DomesticAir);

	}
	//valid
	@Test
	public void testTranslateTransportType2(){
		
		RouteManager rm = new RouteManager();
		
		String origin = "Wellington";
		String dest = "Sydney";
		Type t = Type.Air;
		
		assertEquals(rm.translateTransportType(origin,dest,t) , Priority.InternationalAir);

	}
	
	@Test
	public void testTranslateTransportType3(){
		
		RouteManager rm = new RouteManager();
		
		String origin = "Wellington";
		String dest = "Auckland";
		Type t = Type.Land;
		
		assertEquals(rm.translateTransportType(origin,dest,t) , Priority.DomesticStandard);

	}
	
	@Test
	public void testTranslateTransportType4(){
		
		RouteManager rm = new RouteManager();
		
		String origin = "Wellington";
		String dest = "Sydney";
		Type t = Type.Land;
		
		assertEquals(rm.translateTransportType(origin,dest,t) , Priority.InternationalStandard);

	}
	
	//worked out times manually on paper first 
	@Test
	public void testCalculateDeliveryTime(){
		RouteManager rm = new RouteManager();
		
		TransportFirm t1 = new TransportFirm("" , 0.0 , 0.0 , 0.0 , 0.0 , 10  , 6 , Day.Thursday , null , Type.Air);
		TransportFirm t2 = new TransportFirm("" , 0.0 , 0.0 , 0.0 , 0.0 , 12 , 4 , Day.Friday , null , Type.Air);
		TransportFirm t3 = new TransportFirm("" , 0.0 , 0.0 , 0.0 , 0.0 , 6 , 3 , Day.Sunday , null , Type.Air);
		List<TransportFirm> ts = new ArrayList<TransportFirm>();
		ts.add(t1);
		ts.add(t2);
		ts.add(t3);
		MailDelivery md = new MailDelivery("", "", Priority.DomesticAir, 0.0,
				0.0, "Tuesday");
		int deliveryTime = rm.calculateDeliveryTime(md , ts);
		
		assertTrue(deliveryTime == 134);
		
	}
	
	@Test 
	public void testCalculateDeliveryTime2(){
		RouteManager rm = new RouteManager();
		ArrayList<TransportFirm> firms = new ArrayList<TransportFirm>();
	
		TransportFirm tf1 = new TransportFirm("",0,0,0,0,5,6,Day.Thursday,null, Type.Air);
		TransportFirm tf2 = new TransportFirm("",0,0,0,0,10,4,Day.Saturday,null, Type.Air);
		TransportFirm tf3 = new TransportFirm("",0,0,0,0,3,6,Day.Tuesday,null, Type.Air);
		TransportFirm tf4 = new TransportFirm("",0,0,0,0,15,8,Day.Wednesday,null, Type.Air);
		TransportFirm tf5 = new TransportFirm("",0,0,0,0,4,12,Day.Friday,null, Type.Air);
		TransportFirm tf6 = new TransportFirm("",0,0,0,0,10,3,Day.Sunday,null, Type.Air);
		
		firms.add(tf1);
		firms.add(tf2);
		firms.add(tf3);
		firms.add(tf4);
		firms.add(tf5);
		firms.add(tf6);
		
		MailDelivery md = new MailDelivery("", "", Priority.DomesticAir, 0.0,
				0.0, "Tuesday");
		int deliveryTime = rm.calculateDeliveryTime(md , firms);
		
		assertTrue(deliveryTime == 306);

	}
	
	
	@Test
	public void testAddTransportFirm(){
		
		RouteManager rm = buildRouteManager();
		TestObjects to = new TestObjects();
		
		TransportCostUpdate tcu = to.createTransportCostUpdate1();
		
		rm.addTransportFirm(tcu);
		
		Route routeSelected = rm.getRoutes().get(0);
		
		assertTrue(routeSelected.getTransportFirms().size() == 1);
		
	}
	
	@Test
	public void testRemoveTransportFirm(){
		
		RouteManager rm = buildRouteManager();
		TestObjects to = new TestObjects();
		TransportDiscontinued td = to.createTransportDiscontinued();	
		TransportCostUpdate tcu = to.createTransportCostUpdate1();		
		rm.addTransportFirm(tcu);		
		rm.removeTransportFirm(td);
		Route routeSelected = rm.getRoutes().get(0);
		assertTrue(routeSelected.getTransportFirms().size() == 0);
		
		
	}
	
	
	@Test
	public void testGetTransportFirm(){
		
		TestObjects to = new TestObjects();
		MailDelivery md = to.createMD3();
		
		RouteManager rm = buildRouteManager();
		
		TransportFirm tf1 = to.createTransportFirm1();
			
		TransportCostUpdate tcu = to.createTransportCostUpdate1();		
		rm.addTransportFirm(tcu);		
		
		TransportFirm tf = rm.findTransportFirm(rm.getRoutes(), md);
		
		assertTrue(tf.equals(tf1));
		
		
	}
	

}
	
	
	
	

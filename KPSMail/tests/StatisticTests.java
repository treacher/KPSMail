package tests;

import static org.junit.Assert.*;

import java.util.List;

import kpsMail.KPS;
import kpsMail.Priority;
import kpsMail.Route;
import kpsMail.RouteManager;
import kpsMail.Statistics;

import org.junit.Test;

public class StatisticTests {
	
	@Test
	public void testCriticalRoutes(){
		KPS kps = new KPS();
		
		
		
		Route r1 = new Route("Wellington", "Auckland", Priority.DomesticAir, 10, 10);		
		Route r2 = new Route("Auckland", "Sydney", Priority.InternationalAir, 20, 20);
		Route r3 = new Route("Sydney", "Perth", Priority.DomesticAir, 30 , 30);
		Route r4 = new Route("Perth", "Mumbai", Priority.InternationalAir, 40, 40);

		r1.processPackage(5, 5, 25, 22, 5);
		r2.processPackage(6,5,50,33,8);
		r3.processPackage(8,11,15,5,12);
		r4.processPackage(40,50,11,3,10);
		
		RouteManager rm = new RouteManager();
		rm.getRoutes().add(r1);
		rm.getRoutes().add(r2);
		rm.getRoutes().add(r3);
		rm.getRoutes().add(r4);
		kps.setRouteManager(rm);
		Statistics stats = new Statistics(kps);
		List<Route> list = stats.calculateCriticalRoutes();
		
		assertTrue(list.size() == 2);
		
	}


}

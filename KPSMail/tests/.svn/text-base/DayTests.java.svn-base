package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import kpsMail.Day;

public class DayTests {



	@Test
	public void dayEqualsTest(){

		Day d1 =  Day.Monday;
		Day d2 = Day.Monday;	
		assertEquals(d1,d2);
	}

	@Test
	public void dayDifferenceTest1(){
		Day d1 =  Day.Monday;
		Day d2 = Day.Monday;

		assertEquals(d1.difference(d2), 0);
	}

	@Test
	public void dayDifferenceTest2(){
		Day d1 =  Day.Monday;
		Day d2 =  Day.Sunday;

		assertEquals(d1.difference(d2), 6 * 24);
	}

	@Test
	public void dayDifferenceTest4(){
		Day d1 =  Day.Tuesday;
		Day d2 =  Day.Monday;
		Day d3 =  Day.Friday;

		assertEquals(d1.difference(d2), 6 * 24);

		int total = 0;

		total += d1.difference(d2);
		total += d2.difference(d3);

		assertEquals(total, 10 * 24);

	}


	@Test
	public void scenario1Test2(){
		Day d1 = Day.Monday;		
		Day d2 = Day.Thursday;			
		Day d3 = Day.Friday;	
		Day d4 = Day.Sunday;		

		int total = 0;

		total += d1.difference(d2);
		total += d2.difference(d3);
		total += d3.difference(d4);

		assertEquals(total, 24 * 6);
		assertEquals(total, d1.difference(d4));
	}





}

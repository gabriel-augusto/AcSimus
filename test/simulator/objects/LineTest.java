package simulator.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineTest {
	
	private double precision;
	
	@Before
	public void setup(){
		precision = 0.00001;
	}
	
	@Test
	public void getSlopeTest() {
		Line line1 = Line.getLine(new Location(0,0), new Location(1,1));
		Line line2 = Line.getLine(new Location(0,0), new Location(1,-1));
		Line line3 = Line.getLine(new Location(-1,-1), new Location(0,0));
		Line line4 = Line.getLine(new Location(-1,1), new Location(0,0));		
		
		assertEquals(1, line1.getSlope(), .0000001);
		assertEquals(-1, line2.getSlope(), .0000001);
		assertEquals(1, line3.getSlope(), .0000001);
		assertEquals(-1, line4.getSlope(), .0000001);
	}
	
	@Test
	public void getInfinitySlope(){
		Line line1 = Line.getLine(new Location(0,0), new Location(0,1));
		Line line2 = Line.getLine(new Location(0,0), new Location(0,-1));
		
		assertEquals(Double.POSITIVE_INFINITY, line1.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, line2.getSlope(), .0000001);		
	}
	
	@Test
	public void getSlopeBySomeDirectionTest(){
		Line line1 = Line.getLine(new Location(0,0), 0);
		Line line2 = Line.getLine(new Location(0,0), 45);
		Line line3 = Line.getLine(new Location(0,0), 90);
		Line line4 = Line.getLine(new Location(0,0), 180);
		Line line5 = Line.getLine(new Location(0,0), 270);
		Line line6 = Line.getLine(new Location(0,0), 135);
		Line line7 = Line.getLine(new Location(0,0), 225);
		
		assertEquals(0, line1.getSlope(), .0000001);
		assertEquals(1, line2.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, line3.getSlope(), .0000001);
		assertEquals(0, line4.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, line5.getSlope(), .0000001);
		assertEquals(-1, line6.getSlope(), .0000001);
		assertEquals(1, line7.getSlope(), .0000001);
	}
	
	@Test
	public void searchIntersectionOfFiniteLinesTest(){
		Line line1 = Line.getLine(new Location(0,0), new Location(2,2));
		Line line2 = Line.getLine(new Location(0,2), new Location(2,0));
		
		Line line3 = Line.getLine(new Location(-2,0), new Location(2,0));
		Line line4 = Line.getLine(new Location(1,-2), new Location(1,2));
		
		Location intersectionPoint1 = line1.searchIntersectionPoint(line2);
		Location intersectionPoint2 = line3.searchIntersectionPoint(line4);
		
		assertTrue(intersectionPoint1.equals(new Location(1, 1), precision));
		assertTrue(intersectionPoint2.equals(new Location(1, 0), precision));
	}

	@Test
	public void intersectionPointBetweenAFinitAndAnInfinityLineTest(){
		Line line1 = Line.getLine(new Location(0,0), new Location(2,2));
		Line line2 = Line.getLine(new Location(2,0), 135);
		
		Line line3 = Line.getLine(new Location(-1,0), new Location(1,0));
		Line line4 = Line.getLine(new Location(1,-1), 90);
		
		Line line5 = Line.getLine(new Location(0,0), new Location(0,100));
		Line line6 = Line.getLine(new Location(50,50), 180);
		
		Line line7 = Line.getLine(new Location(0,0), new Location(0,100));
		Line line8 = Line.getLine(new Location(10,0), new Location(20,0));
		
		Line line9 = Line.getLine(new Location(0,10), new Location(0,20));
		Line line10 = Line.getLine(new Location(0,0), new Location(10,0));
		
		Location intersectionPoint1 = line1.searchIntersectionPoint(line2);
		Location intersectionPoint2 = line3.searchIntersectionPoint(line4);
		Location intersectionPoint3 = line5.searchIntersectionPoint(line6);
		Location intersectionPoint4 = line7.searchIntersectionPoint(line8);
		Location intersectionPoint5 = line9.searchIntersectionPoint(line10);
		
		assertTrue(intersectionPoint1.equals(new Location(1, 1), precision));
		assertTrue(intersectionPoint2.equals(new Location(1, 0), precision));
		assertTrue(intersectionPoint3.equals(new Location(0,50), precision));
		assertEquals(intersectionPoint4, null);
		assertEquals(intersectionPoint5, null);
	}
	
	@Test
	public void nonExistingIntersectionPointTest(){
		Line line1 = Line.getLine(new Location(10,0), new Location(0,0));
		Line line2 = Line.getLine(new Location(5,10), 315);
		
		Line line3 = Line.getLine(new Location(0,0), new Location(0,10));
		Line line4 = Line.getLine(new Location(2,-1), 90);
		
		
		Location intersectionPoint1 = line1.searchIntersectionPoint(line2);
		Location intersectionPoint2 = line3.searchIntersectionPoint(line4);
		
		assertNull(intersectionPoint1);
		assertNull(intersectionPoint2);
	}
}

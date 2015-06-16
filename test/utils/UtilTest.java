package utils;


import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {
	private final double TOLERANCE = .0000000000001;

	@Test
	public void normalizeAngleTest() {
		assertEquals(Util.normalizeAngle(370), 10, 0.000001);
		assertEquals(Util.normalizeAngle(-10), 350, 0.000001);
	}
	
	@Test
	public void calculateXTest(){
		assertEquals(Util.calculateX(45, 2), Math.sqrt(2), TOLERANCE);
	}
	
	@Test
	public void calculateYTest(){
		assertEquals(Util.calculateY(45, 2), Math.sqrt(2), TOLERANCE);
	}
}

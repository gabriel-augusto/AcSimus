package utils;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTest {

	@Test
	public void normalizeAngleTest() {
		assertEquals(Util.normalizeAngle(370), 10, 0.000001);
		assertEquals(Util.normalizeAngle(-10), 350, 0.000001);
	}
}

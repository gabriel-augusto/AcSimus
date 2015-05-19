package simulator.objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ObstacleTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createObstacleTest() {
		Line line = Line.getLine(new Location(0,0), new Location(1,1));
		Obstacle.createObstacle("o1", line, 10);
		assertEquals(Obstacle.getObstacles().get("o1").getAbsortionRate(), 10, .0000001);
		assertEquals(Obstacle.getObstacles().get("o1").getLine(), line);
	}
}

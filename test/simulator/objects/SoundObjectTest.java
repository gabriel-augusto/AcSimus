package simulator.objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SoundObjectTest {
	
	private final double TOLERANCE = .0000000000001;

	@Before
	public void setUp() throws Exception {
		Obstacle.createObstacle("ob1", Line.getLine(new Location(0,0), new Location(0,10)), 10);
		Obstacle.createObstacle("ob2", Line.getLine(new Location(0,10), new Location(10,10)), 10);
		Obstacle.createObstacle("ob3", Line.getLine(new Location(10,10), new Location(10,0)), 10);
		Obstacle.createObstacle("ob4", Line.getLine(new Location(10,0), new Location(0,0)), 10);
		Obstacle.createObstacle("ob5", Line.getLine(new Location(2,3), new Location(2,7)), 10);
		Obstacle.createObstacle("ob6", Line.getLine(new Location(5,3), new Location(5,7)), 10);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testUpdate(){
		SoundObject sound = new SoundObject(new Location(8, 5), 0, 100, 90, null, null, null);
		sound.findNextObstacle();
		sound.update();
		assertEquals(sound.getActualState(), sound.getState());
		sound.update();
		assertEquals(5, sound.getCollisionPoint().getX(), TOLERANCE);
	}

	@Test
	public void testFindNextObstacle() {		
		SoundObject sound1 = new SoundObject(new Location(5, 5), 0, 100, 90, null, null, null);
		SoundObject sound2 = new SoundObject(new Location(5, 5), 90, 100, 90, null, null, null);
		SoundObject sound3 = new SoundObject(new Location(5, 5), 180, 100, 90, null, null, null);
		SoundObject sound4 = new SoundObject(new Location(5, 5), 270, 100, 90, null, null, null);
		
		sound1.findNextObstacle();
		sound2.findNextObstacle();
		sound3.findNextObstacle();
		sound4.findNextObstacle();
		
		assertEquals(sound1.getCollisionPoint().getX(), 10, TOLERANCE);
		assertEquals(sound1.getCollisionPoint().getY(), 5, TOLERANCE);
		assertTrue(sound1.getCollisionPoint().equals(new Location(10,5),TOLERANCE));
		
		assertEquals(sound2.getCollisionPoint().getX(), 5, TOLERANCE);
		assertEquals(sound2.getCollisionPoint().getY(), 10, TOLERANCE);
		assertTrue(sound2.getCollisionPoint().equals(new Location(5,10),TOLERANCE));

		assertEquals(sound3.getCollisionPoint().getX(), 2, TOLERANCE);
		assertEquals(sound3.getCollisionPoint().getY(), 5, TOLERANCE);
		assertTrue(sound3.getCollisionPoint().equals(new Location(2,5),TOLERANCE));
		
		assertEquals(sound4.getCollisionPoint().getX(), 5, TOLERANCE);
		assertEquals(sound4.getCollisionPoint().getY(), 0, TOLERANCE);
		assertTrue(sound4.getCollisionPoint().equals(new Location(5,0),TOLERANCE));	
	}
	
	@Test
	public void testIsColisionPoint() {
		SoundObject sound1 = new SoundObject(new Location(0, 5), 180, 100, 90, null, null, null);
		SoundObject sound2 = new SoundObject(new Location(5, 5), 180, 100, 90, null, null, null);
		
		sound1.setCollisionPoint(new Location(0,5));		
		sound2.setCollisionPoint(new Location(0,5));
		
		assertTrue(sound1.isCollisionPoint());
		assertFalse(sound2.isCollisionPoint());
	}
	
	@Test
	public void testCalculateIntencityAfterColisionPoint(){
		SoundObject sound = new SoundObject(new Location(5, 5), 180, 100, 90, null, null, null);
		sound.setIntensity(sound.calculateIntencityAfterColisionPoint(10));
		
		assertEquals(sound.getIntensity(), 90, TOLERANCE);
	}
	
	@Test
	public void testCalculateNewDirection(){
		SoundObject sound = new SoundObject(new Location(0, 5), 135, 100, 90, null, null, null);
		
		sound.setCollisionObstacle(Obstacle.getObstacles().get("ob1"));
		sound.setDirection(sound.calculateNewDirection());		
		assertEquals(sound.getDirection(), 45, TOLERANCE);
		
		sound.setCollisionObstacle(Obstacle.getObstacles().get("ob2"));
		sound.setDirection(sound.calculateNewDirection());		
		assertEquals(sound.getDirection(), 315, TOLERANCE);
		
		sound.setCollisionObstacle(Obstacle.getObstacles().get("ob3"));
		sound.setDirection(sound.calculateNewDirection());		
		assertEquals(sound.getDirection(), 225, TOLERANCE);
	}
	
	@Test
	public void testCalculateIntensityBySoundPropagation(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		double result = sound.calculateIntensityBySoundPropagation(sound.getIntensity(), sound.getOpening(), 1);
		assertEquals(63.661977237, result, .0000001);
	}
	
	@Test
	public void testUpdateParameters(){
		SoundObject sound = new SoundObject(new Location(0, 5), 180, 100, 90, null, null, null);
		sound.setDistanceOfPreviousColisionPoint(20);
		sound.setCollisionObstacle(Obstacle.getObstacles().get("ob1"));
		sound.setCollisionPoint(new Location(0,5));
		
		sound.updateParameters();
		
		assertEquals(0, sound.getDistanceOfPreviousColisionPoint());
		assertTrue(sound.getInitialLocation().equals(new Location(0,5), TOLERANCE));
		assertEquals(0, sound.getDirection(), TOLERANCE);
		assertEquals(90, sound.getIntensity(), TOLERANCE);
		assertEquals(0, sound.getRote().getDirection(), TOLERANCE);
		assertTrue(sound.getRote().getInitialPoint().equals(new Location(0,5), TOLERANCE));
	}
	
	@Test
	public void testUpdateLocation() {
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		sound.setDistanceOfPreviousColisionPoint(5);
		sound.updateLocation();
		assertEquals(5, sound.getActualLocation().getX(), TOLERANCE);
		assertEquals(5, sound.getActualLocation().getY(), TOLERANCE);
	}
	
	@Test
	public void testGetActualState(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		String response = "\nintensity: 100.0\ndirection: 0.0 degrees\ndistance of origin: 0\ninitial location: (x: 0.0; y: 5.0)\nlocation: (x: 0.0; y: 5.0)";
		assertEquals(response, sound.getActualState());
	}
	
	@Test
	public void testIdentifier(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		sound.setIdentifier("sound1");
		assertEquals("sound1",sound.getIdentifier());
	}
	
	@Test
	public void testActualLocation(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		sound.setActualLocation(new Location(5,0));
		assertEquals(5, sound.getActualLocation().getX(), TOLERANCE);
		assertEquals(0, sound.getActualLocation().getY(), TOLERANCE);
	}
	
	@Test
	public void testPower(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		sound.setPower(50);
		assertEquals(50, sound.getPower(), TOLERANCE);
	}
	
	@Test
	public void testOpening(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		sound.setOpening(50);
		assertEquals(50, sound.getOpening(), TOLERANCE);
	}
	
	@Test
	public void testDistanceTraveled(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		sound.setDistanceTraveled(10);
		assertEquals(10, sound.getDistanceTraveled());
	}
	
	@Test
	public void testState(){
		SoundObject sound = new SoundObject(new Location(0, 5), 0, 100, 90, null, null, null);
		sound.setState("asdf");
		assertEquals("asdf", sound.getState());
	}
}

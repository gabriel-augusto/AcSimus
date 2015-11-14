package utils;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import jade.core.AID;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class UtilTest {
	private final double TOLERANCE = .0000000000001;
	
	@Before
	public void setUp() throws Exception {
		Util.initiateJadeRma();
	}

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

	@Test
	public void gettersAndSetters(){
		AID ambient = new AID("ambient", true);
		Util.setAmbientAID(ambient);
		assertEquals("ambient", Util.getAmbientAID().getLocalName());
		
		AgentController ambientController = null;
		Util.setAmbient(ambientController);
		assertNull(Util.getAmbient());
		
		AgentContainer container = new AgentContainer(null, null, "container");
		Util.setSimulatorContainer(container);
		assertEquals("container", Util.getSimulatorContainer().getName());	
		
		assertTrue(Util.getMainContainer().getName().endsWith("/JADE"));
	}
}

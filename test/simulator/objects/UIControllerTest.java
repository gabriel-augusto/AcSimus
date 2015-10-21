package simulator.objects;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UIControllerTest {
	private UIController controller;

	@Before
	public void setUp() throws Exception {
		controller = UIController.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void addNewEventTest() {
		controller.addNewEvent("RUN");
		assertEquals("RUN", controller.getEvents().poll());
	}
	
	@Test
	public void isRunningTest() {
		controller.setRunning(true);
		assertTrue(controller.isRunning());
	}
	
	@Test
	public void setEventsTest() {
		controller.setEvents(new LinkedList<String>());
		assertEquals(0, controller.getEvents().size());
	}

}

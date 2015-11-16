package simulator.objects;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.AID;
import jade.wrapper.ControllerException;
import utils.Util;

public class AmbientObjectTest {
	
	private AmbientObject ambient;

	@Before
	public void setUp() throws Exception {
		Util.initiateJadeRma();
        Util.initiateAmbient();
        ambient = AmbientObject.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void getNextIdTest() {
		assertEquals("GUI_Agent_controler1", ambient.getNextId(ambient.GUI));
		assertEquals("sound_source1", ambient.getNextId(ambient.SOUNDSOURCE));
	}

	@Test
	public void defineSoundSourceTest(){
		Location location = new Location(5,5);
		int agents = 10;
		int opening = 50;
		int direction = 0;
		String id = "SS";
		Object[] parameters = {location, agents, opening, direction, id};
		ambient.setSoundSourceParameters(parameters);
		ambient.defineSoundSource();
		
		assertNotNull(ambient.getSoundSources().get("SS"));
	}
	
	@Test
	public void gettersTest() throws ControllerException{
		assertEquals("Ambient", ambient.getAmbientAID().getLocalName());
		assertEquals("Container-1", ambient.getCc().getContainerName());
	}
	
	@Test
	public void setSoundSourcesTest(){
		ambient.setSoundSources(new HashMap <String, AID>());
		assertEquals(0, ambient.getSoundSources().size());
	}
}

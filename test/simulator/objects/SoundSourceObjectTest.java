package simulator.objects;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.AID;
import jade.wrapper.ControllerException;
import languagesAndMessages.Message;

public class SoundSourceObjectTest {
	
	private SoundSourceObject soundSource = null;
	private AmbientObject ambient = null;
	private Location location = null;
	private final double TOLERANCE = 0.0000000001;

	@Before
	public void setUp() throws Exception {
		ambient = AmbientObject.getInstance();
		location = new Location(5,5);
		AID ss = new AID();
		ss.setLocalName("sound_source");
		SoundSourceObject.createSoundSource("SS1", ambient.getAmbientAID(), location, 10, 10, 0);
		soundSource = SoundSourceObject.getSoundSources().get("SS1");
		soundSource.setContainer(ambient.getContainer());
		soundSource.setCc(ambient.getCc());
		soundSource.setSounds(new HashMap <String, AID>());
		soundSource.setSelfAID(ss);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createSoundSourceTest() {		
		assertNotNull(soundSource);
	}
	
	@Test
	public void gettersAndSettersTest() throws ControllerException{
		assertEquals("Ambient", soundSource.getAmbient().getLocalName());
		assertEquals(5, soundSource.getLocation().getX(), TOLERANCE);
		assertEquals(10, soundSource.getOpening());
		assertEquals(Math.pow(10, -7), soundSource.getPower(), TOLERANCE);
		assertEquals(0, soundSource.getDirection());
		assertEquals("Container-1", soundSource.getCc().getContainerName());
		assertEquals(0, soundSource.getSounds().size());
		assertEquals("sound_source", soundSource.getSelfAID().getLocalName());
	}
	
	@Test
	public void emitSoundPulseTest(){
		soundSource.emitSoundPulse();
		assertEquals("Sound_1", soundSource.getSounds().get("Sound_1").getLocalName());
	}
	
	@Test
	public void suspendAllSoundsTest(){
		assertEquals(Message.PAUSE, soundSource.suspendAllSounds().getContent());
	}
	
	@Test
	public void resumeAllSoundsTest(){
		assertFalse(soundSource.resumeAllSounds());
	}
	
	@Test
	public void stopSimulationTest(){
		assertEquals(Message.STOP_RESUMED, soundSource.stopSimulation(Message.STOP_RESUMED).getContent());
		assertEquals(Message.ALREADY_STOPED, soundSource.stopSimulation("OTHER STATUS").getContent());
	}
}

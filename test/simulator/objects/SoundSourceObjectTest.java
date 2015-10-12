package simulator.objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.AID;

public class SoundSourceObjectTest {
	
	private SoundSourceObject soundSource = null;
	private AID ambient = null;
	private Location location = null;
	private final double TOLERANCE = 0.0000000001;

	@Before
	public void setUp() throws Exception {
		ambient = new AID("ambient", true);
		location = new Location(5,5);
		SoundSourceObject.createSoundSource("SS1", ambient, location, 90, 100, 0);
		soundSource = SoundSourceObject.getSoundSources().get("SS1");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void createSoundSourceTest() {		
		assertNotNull(soundSource);
	}
	
	@Test
	public void gettersAndSettersTest(){
		assertEquals("ambient", soundSource.getAmbient().getLocalName());
		assertEquals(5, soundSource.getLocation().getX(), TOLERANCE);
		assertEquals(90, soundSource.getOpening());
		assertEquals(100, soundSource.getPower(), TOLERANCE);
		assertEquals(0, soundSource.getDirection());
	}

}

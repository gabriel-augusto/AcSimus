package simulator.objects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SimulationStatusTest {

	double precision;
	
	@Before
	public void setup(){
		precision = 0.00001;
	}
	
	@Test
	public void getInstanceTest() {
		assertNotNull(SimulationStatus.getInstance());
	}
	
	@Test
	public void decibelTest(){
		SimulationStatus.getInstance().setDecibel(10);
		assertEquals(10, SimulationStatus.getInstance().getDecibel(), precision);
	}
	
	@Test
	public void reverberationTimeTest(){
		SimulationStatus.getInstance().setReverberationTime(100);
		assertEquals(100, SimulationStatus.getInstance().getReverberationTime(), precision);
	}
}

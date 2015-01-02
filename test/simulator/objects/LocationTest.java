package simulator.objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simulator.objects.Location;

public class LocationTest {
	
	private double TOLERANCIA;
	
	@Before
	public void setUp() throws Exception {
		TOLERANCIA = 0.0000001;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void distanciaTeste(){
		Location location = new Location(0,0);
		double distancia = location.distance(new Location(1,1));
		assertEquals(1.414213,distancia,.000001);
	}
	
	@Test 
	public void toStringTeste(){
		Location location = new Location(20, 30);
		assertEquals("[x: 20.0; y: 30.0]", location.toString());
	}

	@Test
	public void equalsTest(){
		Location localizacao1 = new Location(20, 30);
		Location localizacao2 = new Location(20, 30);
		Location localizacao3 = new Location(30, 20);
		
		assertTrue(localizacao1.equals(localizacao2,TOLERANCIA));
		assertTrue(localizacao2.equals(localizacao1,TOLERANCIA));
		assertFalse(localizacao1.equals(localizacao3,TOLERANCIA));
	}
	
	@Test
	public void valueOfTeste() {
		Location localizacao1 = new Location(-20.45, 30.29);
		Location localizacao2 = Location.valueOf("[x: -20.45; y: 30.29]");
		
		Location localizacao3 = new Location(20.0, 30.0);
		Location localizacao4 = Location.valueOf(
						"[x: 20; y: 30]");
		
		Location localizacao5 = new Location(20, 30);
		Location localizacao6 = Location.valueOf("[x: 20.0; y: 30.0]");
		
		assertTrue(localizacao1.equals(localizacao2,TOLERANCIA));
		assertTrue(localizacao3.equals(localizacao4,TOLERANCIA));
		assertTrue(localizacao5.equals(localizacao6,TOLERANCIA));
	}
}

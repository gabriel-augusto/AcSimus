package simulator.objects;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import simulator.objects.Localization;

public class LocalizationTest {
	
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
		Localization localization = new Localization(0,0);
		double distancia = localization.distance(new Localization(1,1));
		assertEquals(1.414213,distancia,.000001);
	}
	
	@Test 
	public void toStringTeste(){
		Localization localization = new Localization(20, 30);
		assertEquals("[x: 20.0; y: 30.0]", localization.toString());
	}

	@Test
	public void equalsTest(){
		Localization localizacao1 = new Localization(20, 30);
		Localization localizacao2 = new Localization(20, 30);
		Localization localizacao3 = new Localization(30, 20);
		
		assertTrue(localizacao1.equals(localizacao2,TOLERANCIA));
		assertTrue(localizacao2.equals(localizacao1,TOLERANCIA));
		assertFalse(localizacao1.equals(localizacao3,TOLERANCIA));
	}
	
	@Test
	public void valueOfTeste() {
		Localization localizacao1 = new Localization(-20.45, 30.29);
		Localization localizacao2 = Localization.valueOf("[x: -20.45; y: 30.29]");
		
		Localization localizacao3 = new Localization(20.0, 30.0);
		Localization localizacao4 = Localization.valueOf(
						"[x: 20; y: 30]");
		
		Localization localizacao5 = new Localization(20, 30);
		Localization localizacao6 = Localization.valueOf("[x: 20.0; y: 30.0]");
		
		assertTrue(localizacao1.equals(localizacao2,TOLERANCIA));
		assertTrue(localizacao3.equals(localizacao4,TOLERANCIA));
		assertTrue(localizacao5.equals(localizacao6,TOLERANCIA));
	}
}

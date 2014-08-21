package objetos;

import static org.junit.Assert.*;
import objetos.Localizacao;

import org.junit.Test;

public class LocalizacaoTest {
	
	@Test
	public void distanciaTeste(){
		Localizacao localizacao = new Localizacao(0,0);
		double distancia = localizacao.distancia(new Localizacao(1,1));
		assertEquals(1.414213,distancia,.000001);
	}
	
	@Test 
	public void toStringTeste(){
		Localizacao localizacao = new Localizacao(20, 30);
		assertEquals("[x: 20.0; y: 30.0]", localizacao.toString());
	}

	@Test
	public void equalsTest(){
		Localizacao localizacao1 = new Localizacao(20, 30);
		Localizacao localizacao2 = new Localizacao(20, 30);
		Localizacao localizacao3 = new Localizacao(30, 20);
		
		assertTrue(localizacao1.equals(localizacao2));
		assertTrue(localizacao2.equals(localizacao1));
		assertFalse(localizacao1.equals(localizacao3));
	}
	
	@Test
	public void valueOfTeste() {
		Localizacao localizacao1 = new Localizacao(-20.45, 30.29);
		Localizacao localizacao2 = Localizacao.valueOf("[x: -20.45; y: 30.29]");
		
		Localizacao localizacao3 = new Localizacao(20.0, 30.0);
		Localizacao localizacao4 = Localizacao.valueOf("[x: 20; y: 30]");
		
		Localizacao localizacao5 = new Localizacao(20, 30);
		Localizacao localizacao6 = Localizacao.valueOf("[x: 20.0; y: 30.0]");
		
		assertTrue(localizacao1.equals(localizacao2));
		assertTrue(localizacao3.equals(localizacao4));
		assertTrue(localizacao5.equals(localizacao6));
	}
}

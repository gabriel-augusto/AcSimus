package simulador;

import static org.junit.Assert.*;

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
		assertEquals("[x: 20; y: 30]", localizacao.toString());
	}

	@Test
	public void valueOfTeste() {
		Localizacao localizacao1 = new Localizacao(20, 30);
		Localizacao localizacao2 = Localizacao.valueOf("[x: 20; y: 30]");
		
		assertEquals(localizacao1.getX(), localizacao2.getX());
		assertEquals(localizacao1.getY(), localizacao2.getY());
	}

}

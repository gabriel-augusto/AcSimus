package objetos;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinhaTest {

	@Test
	public void calcularInclinacaoTest() {
		Linha linha1 = Linha.getLinha(new Localizacao(0,0), new Localizacao(1,1));
		Linha linha2 = Linha.getLinha(new Localizacao(0,0), new Localizacao(1,-1));
		Linha linha3 = Linha.getLinha(new Localizacao(-1,-1), new Localizacao(0,0));
		Linha linha4 = Linha.getLinha(new Localizacao(-1,1), new Localizacao(0,0));
		
		
		assertEquals(1, linha1.getInclinacao(), .0000001);
		assertEquals(-1, linha2.getInclinacao(), .0000001);
		assertEquals(1, linha3.getInclinacao(), .0000001);
		assertEquals(-1, linha4.getInclinacao(), .0000001);
	}
	
	@Test
	public void calcularInclinacaoInfinita(){
		Linha linha1 = Linha.getLinha(new Localizacao(0,0), new Localizacao(0,1));
		Linha linha2 = Linha.getLinha(new Localizacao(0,0), new Localizacao(0,-1));
		
		assertEquals(Double.POSITIVE_INFINITY, linha1.getInclinacao(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, linha2.getInclinacao(), .0000001);		
	}
	
	@Test
	public void calcularInclinacaoAPartirDeUmaDirecao(){
		Linha linha1 = Linha.getLinha(new Localizacao(0,0), 0);
		Linha linha2 = Linha.getLinha(new Localizacao(0,0), 45);
		Linha linha3 = Linha.getLinha(new Localizacao(0,0), 90);
		Linha linha4 = Linha.getLinha(new Localizacao(0,0), 180);
		Linha linha5 = Linha.getLinha(new Localizacao(0,0), 270);
		Linha linha6 = Linha.getLinha(new Localizacao(0,0), 135);
		Linha linha7 = Linha.getLinha(new Localizacao(0,0), 225);
		
		assertEquals(0, linha1.getInclinacao(), .0000001);
		assertEquals(1, linha2.getInclinacao(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, linha3.getInclinacao(), .0000001);
		assertEquals(0, linha4.getInclinacao(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, linha5.getInclinacao(), .0000001);
		assertEquals(-1, linha6.getInclinacao(), .0000001);
		assertEquals(1, linha7.getInclinacao(), .0000001);
	}
	
	@Test
	public void procurarPontoDeInterseccaoEntreLinhasFinitasTest(){
		Linha linha1 = Linha.getLinha(new Localizacao(0,0), new Localizacao(2,2));
		Linha linha2 = Linha.getLinha(new Localizacao(0,2), new Localizacao(2,0));
		
		Linha linha3 = Linha.getLinha(new Localizacao(-2,0), new Localizacao(2,0));
		Linha linha4 = Linha.getLinha(new Localizacao(1,-2), new Localizacao(1,2));
		
		Localizacao pontoDeInterseccao1 = linha1.procurarPontoDeInterseccao(linha2);
		Localizacao pontoDeInterseccao2 = linha3.procurarPontoDeInterseccao(linha4);
		
		assertTrue(pontoDeInterseccao1.equals(new Localizacao(1,1)));
		assertTrue(pontoDeInterseccao2.equals(new Localizacao(1,0)));
		/*
		assertEquals(1, pontoDeInterseccao1.getX(), .00001);
		assertEquals(1, pontoDeInterseccao1.getY(), .00001);

		assertEquals(1, pontoDeInterseccao2.getX(), .00001);
		assertEquals(0, pontoDeInterseccao2.getY(), .00001);
		*/
	}

	@Test
	public void procurarPontoDeInterseccaoEntreUmaLinhaFinitaEOutraInfinitaTest(){
		Linha linha1 = Linha.getLinha(new Localizacao(0,0), new Localizacao(2,2));
		Linha linha2 = Linha.getLinha(new Localizacao(2,0), 135);
		
		Linha linha3 = Linha.getLinha(new Localizacao(-1,0), new Localizacao(1,0));
		Linha linha4 = Linha.getLinha(new Localizacao(1,-1), 90);
		
		
		Localizacao pontoDeInterseccao1 = linha1.procurarPontoDeInterseccao(linha2);
		Localizacao pontoDeInterseccao2 = linha3.procurarPontoDeInterseccao(linha4);
		
		assertTrue(pontoDeInterseccao1.equals(new Localizacao(1,1)));
		assertTrue(pontoDeInterseccao2.equals(new Localizacao(1,0)));
		
		/*
		assertEquals(1, pontoDeInterseccao1.getX(), .00001);
		assertEquals(1, pontoDeInterseccao1.getY(), .00001);
		
		assertEquals(1, pontoDeInterseccao2.getX(), .00001);
		assertEquals(0, pontoDeInterseccao2.getY(), .00001);
		*/
	}
}

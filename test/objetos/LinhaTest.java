package objetos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinhaTest {
	
	private double precisao;
	
	@Before
	public void setup(){
		precisao = 0.00001;
	}
	
	@Test
	public void calcularInclinacaoTest() {	
		Linha linha1 = Linha.getLine(new Localizacao(0,0), new Localizacao(1,1));
		Linha linha2 = Linha.getLine(new Localizacao(0,0), new Localizacao(1,-1));
		Linha linha3 = Linha.getLine(new Localizacao(-1,-1), new Localizacao(0,0));
		Linha linha4 = Linha.getLine(new Localizacao(-1,1), new Localizacao(0,0));		
		
		assertEquals(1, linha1.getSlope(), .0000001);
		assertEquals(-1, linha2.getSlope(), .0000001);
		assertEquals(1, linha3.getSlope(), .0000001);
		assertEquals(-1, linha4.getSlope(), .0000001);
	}
	
	@Test
	public void calcularInclinacaoInfinita(){
		Linha linha1 = Linha.getLine(new Localizacao(0,0), new Localizacao(0,1));
		Linha linha2 = Linha.getLine(new Localizacao(0,0), new Localizacao(0,-1));
		
		assertEquals(Double.POSITIVE_INFINITY, linha1.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, linha2.getSlope(), .0000001);		
	}
	
	@Test
	public void calcularInclinacaoAPartirDeUmaDirecao(){
		Linha linha1 = Linha.getLine(new Localizacao(0,0), 0);
		Linha linha2 = Linha.getLine(new Localizacao(0,0), 45);
		Linha linha3 = Linha.getLine(new Localizacao(0,0), 90);
		Linha linha4 = Linha.getLine(new Localizacao(0,0), 180);
		Linha linha5 = Linha.getLine(new Localizacao(0,0), 270);
		Linha linha6 = Linha.getLine(new Localizacao(0,0), 135);
		Linha linha7 = Linha.getLine(new Localizacao(0,0), 225);
		
		assertEquals(0, linha1.getSlope(), .0000001);
		assertEquals(1, linha2.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, linha3.getSlope(), .0000001);
		assertEquals(0, linha4.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, linha5.getSlope(), .0000001);
		assertEquals(-1, linha6.getSlope(), .0000001);
		assertEquals(1, linha7.getSlope(), .0000001);
	}
	
	@Test
	public void procurarPontoDeInterseccaoEntreLinhasFinitasTest(){
		Linha linha1 = Linha.getLine(new Localizacao(0,0), new Localizacao(2,2));
		Linha linha2 = Linha.getLine(new Localizacao(0,2), new Localizacao(2,0));
		
		Linha linha3 = Linha.getLine(new Localizacao(-2,0), new Localizacao(2,0));
		Linha linha4 = Linha.getLine(new Localizacao(1,-2), new Localizacao(1,2));
		
		Localizacao pontoDeInterseccao1 = linha1.searchSlopePoint(linha2);
		Localizacao pontoDeInterseccao2 = linha3.searchSlopePoint(linha4);
		
		assertTrue(pontoDeInterseccao1.equals(new Localizacao(1,1),precisao));
		assertTrue(pontoDeInterseccao2.equals(new Localizacao(1,0),precisao));
		/*
		assertEquals(1, pontoDeInterseccao1.getX(), precisao);
		assertEquals(1, pontoDeInterseccao1.getY(), precisao);

		assertEquals(1, pontoDeInterseccao2.getX(), precisao);
		assertEquals(0, pontoDeInterseccao2.getY(), precisao);
		*/
	}

	@Test
	public void procurarPontoDeInterseccaoEntreUmaLinhaFinitaEOutraInfinitaTest(){
		Linha linha1 = Linha.getLine(new Localizacao(0,0), new Localizacao(2,2));
		Linha linha2 = Linha.getLine(new Localizacao(2,0), 135);
		
		Linha linha3 = Linha.getLine(new Localizacao(-1,0), new Localizacao(1,0));
		Linha linha4 = Linha.getLine(new Localizacao(1,-1), 90);
		
		
		Localizacao pontoDeInterseccao1 = linha1.searchSlopePoint(linha2);
		Localizacao pontoDeInterseccao2 = linha3.searchSlopePoint(linha4);
		
		assertTrue(pontoDeInterseccao1.equals(new Localizacao(1,1),precisao));
		assertTrue(pontoDeInterseccao2.equals(new Localizacao(1,0),precisao));
		
		/*
		assertEquals(1, pontoDeInterseccao1.getX(), precisao);
		assertEquals(1, pontoDeInterseccao1.getY(), precisao);
		
		assertEquals(1, pontoDeInterseccao2.getX(), precisao);
		assertEquals(0, pontoDeInterseccao2.getY(), precisao);
		*/
	}
	
	@Test
	public void procurarPontoDeInterseccaoNaoExistenteTest(){
		Linha linha1 = Linha.getLine(new Localizacao(10,0), new Localizacao(0,0));
		Linha linha2 = Linha.getLine(new Localizacao(5,10), 315);
		
		Linha linha3 = Linha.getLine(new Localizacao(0,0), new Localizacao(0,10));
		Linha linha4 = Linha.getLine(new Localizacao(2,-1), 90);
		
		
		Localizacao pontoDeInterseccao1 = linha1.searchSlopePoint(linha2);
		Localizacao pontoDeInterseccao2 = linha3.searchSlopePoint(linha4);
		
		assertNull(pontoDeInterseccao1);
		assertNull(pontoDeInterseccao2);
		
	}
}

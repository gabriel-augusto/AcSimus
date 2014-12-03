package oo;

import static org.junit.Assert.*;
import oo.Line;
import oo.Localization;

import org.junit.Before;
import org.junit.Test;

public class LineTest {
	
	private double precisao;
	
	@Before
	public void setup(){
		precisao = 0.00001;
	}
	
	@Test
	public void calcularInclinacaoTest() {	
		Line line1 = Line.getLine(new Localization(0,0), new Localization(1,1));
		Line line2 = Line.getLine(new Localization(0,0), new Localization(1,-1));
		Line line3 = Line.getLine(new Localization(-1,-1), new Localization(0,0));
		Line line4 = Line.getLine(new Localization(-1,1), new Localization(0,0));		
		
		assertEquals(1, line1.getSlope(), .0000001);
		assertEquals(-1, line2.getSlope(), .0000001);
		assertEquals(1, line3.getSlope(), .0000001);
		assertEquals(-1, line4.getSlope(), .0000001);
	}
	
	@Test
	public void calcularInclinacaoInfinita(){
		Line line1 = Line.getLine(new Localization(0,0), new Localization(0,1));
		Line line2 = Line.getLine(new Localization(0,0), new Localization(0,-1));
		
		assertEquals(Double.POSITIVE_INFINITY, line1.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, line2.getSlope(), .0000001);		
	}
	
	@Test
	public void calcularInclinacaoAPartirDeUmaDirecao(){
		Line line1 = Line.getLine(new Localization(0,0), 0);
		Line line2 = Line.getLine(new Localization(0,0), 45);
		Line line3 = Line.getLine(new Localization(0,0), 90);
		Line line4 = Line.getLine(new Localization(0,0), 180);
		Line line5 = Line.getLine(new Localization(0,0), 270);
		Line line6 = Line.getLine(new Localization(0,0), 135);
		Line line7 = Line.getLine(new Localization(0,0), 225);
		
		assertEquals(0, line1.getSlope(), .0000001);
		assertEquals(1, line2.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, 
				line3.getSlope(), .0000001);
		assertEquals(0, line4.getSlope(), .0000001);
		assertEquals(Double.POSITIVE_INFINITY, line5.getSlope(), .0000001);
		assertEquals(-1, line6.getSlope(), .0000001);
		assertEquals(1, line7.getSlope(), .0000001);
	}
	
	@Test
	public void 
	procurarPontoDeInterseccaoEntreLinhasFinitasTest(){
		Line line1 = Line.getLine(new Localization(0,0), new Localization(2,2));
		Line line2 = Line.getLine(new Localization(0,2), new Localization(2,0));
		
		Line line3 = Line.getLine(new Localization(-2,0), new Localization(2,0));
		Line line4 = Line.getLine(new Localization(1,-2), new Localization(1,2));
		
		Localization pontoDeInterseccao1 = line1.searchSlopePoint(line2);
		Localization pontoDeInterseccao2 = line3.searchSlopePoint(line4);
		
		assertTrue(
				pontoDeInterseccao1.equals(new Localization(1,1), precisao));
		assertTrue(pontoDeInterseccao2.equals(new Localization(1,0), precisao));
	}

	@Test
	public void 
	procurarPontoDeInterseccaoEntreUmaLinhaFinitaEOutraInfinitaTest(){
		Line line1 = Line.getLine(new Localization(0,0), new Localization(2,2));
		Line line2 = Line.getLine(new Localization(2,0), 135);
		
		Line line3 = Line.getLine(new Localization(-1,0), new Localization(1,0));
		Line line4 = Line.getLine(new Localization(1,-1), 90);
		
		
		Localization pontoDeInterseccao1 = 	line1.searchSlopePoint(line2);
		Localization pontoDeInterseccao2 = line3.searchSlopePoint(line4);
		
		assertTrue(pontoDeInterseccao1.equals(new Localization(1,1),precisao));
		assertTrue(pontoDeInterseccao2.equals(new Localization(1,0),precisao));
	}
	
	@Test
	public void procurarPontoDeInterseccaoNaoExistenteTest(){
		Line line1 = Line.getLine(new Localization(10,0), 
				new Localization(0,0));
		Line line2 = Line.getLine(new Localization(5,10), 315);
		
		Line line3 = Line.getLine(new Localization(0,0), 
				new Localization(0,10));
		Line line4 = Line.getLine(new Localization(2,-1), 90);
		
		
		Localization pontoDeInterseccao1 = line1.searchSlopePoint(line2);
		Localization pontoDeInterseccao2 = line3.searchSlopePoint(line4);
		
		assertNull(pontoDeInterseccao1);
		assertNull(pontoDeInterseccao2);		
	}
}

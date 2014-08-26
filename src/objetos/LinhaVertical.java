package objetos;

import utils.Util;

public class LinhaVertical extends Linha{
	
	private static final double TOLERANCIA = 0.000001;
	
	protected LinhaVertical(Localizacao pontoInicial, Localizacao pontoFinal) {
		super(pontoInicial, pontoFinal);
	}
	
	protected LinhaVertical(Localizacao pontoInicial, double direcao){
		super(pontoInicial, direcao);
	}
	
	public Localizacao procurarPontoDeInterseccao(LinhaVertical linha){		
		return null;
	}
	
	public Localizacao procurarPontoDeInterseccao(LinhaNormal linha){
		double y;
		y = linha.getY(this.getConstante());
		
		if(this.pertenceAoIntervalo(y))
			return new Localizacao(this.getConstante(), y);		
		return null;
	}
	
	public Localizacao procurarPontoDeInterseccao(Linha linha){
		if(linha instanceof LinhaNormal)
			return procurarPontoDeInterseccao((LinhaNormal)linha);
		return procurarPontoDeInterseccao((LinhaVertical) linha);
	}
	
	protected boolean pertenceAoIntervalo(double y){
		if(this.getPontoFinal() != null){
			if(y <= Math.max(this.getPontoInicial().getY(), this.getPontoFinal().getY()) 
					&& y >= Math.min(this.getPontoInicial().getY(), this.getPontoFinal().getY())){
				return true;
			}
		}else{
			if(Util.compararDouble(this.getDirecao(), 270, TOLERANCIA) && y <= this.getPontoInicial().getY())
				return true;
			
			if(Util.compararDouble(this.getDirecao(), 90, TOLERANCIA) && y >= this.getPontoInicial().getY())
				return true;				
		}
		
		return false;
	}
	
	public double getInclinacao() {
		return Double.POSITIVE_INFINITY;
	}
	
	public double getConstante() {
		return super.getPontoInicial().getX();
	}

}

package objetos;

public class LinhaVertical extends Linha{
	
	public LinhaVertical(Localizacao pontoInicial, Localizacao pontoFinal) {
		super(pontoInicial, pontoFinal);
	}
	
	public LinhaVertical(Localizacao pontoInicial, double direcao){
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
			if(this.getDirecao() == 270 && y <= this.getPontoInicial().getY())
				return true;
			
			if(this.getDirecao() == 90 && y >= this.getPontoInicial().getY())
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

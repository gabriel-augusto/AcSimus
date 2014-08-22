package objetos;

public class LinhaNormal extends Linha{
	
	public LinhaNormal(Localizacao pontoInicial, Localizacao pontoFinal) {
		super(pontoInicial, pontoFinal);
	}
	
	public LinhaNormal(Localizacao pontoInicial, double direcao){
		super(pontoInicial, direcao);
	}
	
	public Localizacao procurarPontoDeInterseccao(LinhaNormal linha){
		Localizacao pontoDeInterseccao = null;
		double x;
		double y;
		
		if((this.getInclinacao() - linha.getInclinacao()) != 0){
			x = (linha.getConstante() - this.getConstante())/(this.getInclinacao() - linha.getInclinacao());
			y = linha.getInclinacao() * x + linha.getConstante();
			pontoDeInterseccao = new Localizacao(x,y);
		}			

		if(pontoDeInterseccao != null && pertenceAoIntervalo(pontoDeInterseccao.getX()))		
			return pontoDeInterseccao;
		
		return null;
	}
	
	public Localizacao procurarPontoDeInterseccao(LinhaVertical linha){
		double y;
		y = this.getY(linha.getConstante());
		
		if(linha.pertenceAoIntervalo(y))
			return new Localizacao(linha.getConstante(), y);		
		return null;
	}
	
	public Localizacao procurarPontoDeInterseccao(Linha linha){
		if(linha instanceof LinhaNormal)
			return procurarPontoDeInterseccao((LinhaNormal)linha);
		return procurarPontoDeInterseccao((LinhaVertical) linha);
	}
	
	protected boolean pertenceAoIntervalo(double x){
		if(this.getPontoFinal() != null){
			if(x <= Math.max(this.getPontoInicial().getX(), this.getPontoFinal().getX()) 
					&& x >= Math.min(this.getPontoInicial().getX(), this.getPontoFinal().getX())){
				return true;
			}
		}else{
			if(this.getDirecao() > 90 && this.getDirecao() < 270 && x <= this.getPontoInicial().getX()){
				return true;
			}else{
				if(x >= this.getPontoInicial().getX()){
					return true;
				}
			}
		}
		
		return false;
	}
	
	public double getInclinacao() {
		if(this.getPontoFinal() != null){
			double deltaX = this.getPontoFinal().getX() - this.getPontoInicial().getX();
			double deltaY = this.getPontoFinal().getY() - this.getPontoInicial().getY();
			return deltaY/deltaX;
		}
		return Math.tan(Math.toRadians(this.getDirecao()));
	}
	
	public double getConstante() {
		return this.getPontoInicial().getY() - this.getInclinacao() * this.getPontoInicial().getX();
	}
	
	public Double getY(double x){
		return this.getInclinacao() * x + this.getConstante();
	}
}

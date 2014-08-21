package objetos;


public class Linha {

	private Localizacao pontoInicial;
	private Localizacao pontoFinal;
	private double inclinacao;
	private double direcao; // ângulo de direção.
	
	public Linha(Localizacao pontoInicial, Localizacao pontoFinal){
		this.setPontoInicial(pontoInicial);
		this.setPontoFinal(pontoFinal);
		this.setInclinacao(calcularInclinacao(pontoInicial, pontoFinal));
	}
	
	public Linha(Localizacao pontoInicial, double direcao){
		this.setPontoInicial(pontoInicial);
		this.setPontoFinal(null);
		this.setDirecao(direcao);
		this.setInclinacao(calcularInclinacao(direcao));
	}
	
	private double calcularInclinacao(Localizacao pontoInicial, Localizacao pontoFinal) {
		double deltaX = pontoFinal.getX() - pontoInicial.getX();
		double deltaY = pontoFinal.getY() - pontoInicial.getY();
		return deltaY/deltaX;
	}
	
	private double calcularInclinacao(double direcao){
		if(direcao == 90)
			return Double.POSITIVE_INFINITY;
		if(direcao == 270)
			return Double.NEGATIVE_INFINITY;
		
		return Math.tan(Math.toRadians(direcao));
	}
	
	public Localizacao procurarPontoDeInterseccao(Linha linha){
		Localizacao pontoDeInterseccao = null;
		double x;
		double y;
		
		if(this.ehVertical() && !linha.ehVertical()){
			x = this.getConstante();
			y = linha.getY(x);
			pontoDeInterseccao = new Localizacao(x,y);
		}
		
		if(linha.ehVertical() && !this.ehVertical()){
			x = linha.getConstante();
			y = this.getY(x);
			pontoDeInterseccao = new Localizacao(x,y);
		}		
		
		if(((this.getInclinacao() - linha.getInclinacao()) != 0) 
				&& !this.ehVertical() 
				&& !linha.ehVertical()){
			x = (linha.getConstante() - this.getConstante())/(this.getInclinacao() - linha.getInclinacao());
			y = linha.getInclinacao() * x + linha.getConstante();
			pontoDeInterseccao = new Localizacao(x,y);
		}
			

		if(pertenceAoIntervalo(pontoDeInterseccao.getX()))		
			return pontoDeInterseccao;
		
		return null;
	}
	
	private boolean pertenceAoIntervalo(double x){
		if(pontoFinal != null){
			if(x <= Math.max(this.getPontoInicial().getX(), this.getPontoFinal().getX()) 
					&& x >= Math.min(this.getPontoInicial().getX(), this.getPontoFinal().getX())){
				return true;
			}
		}else if((x >= this.getPontoInicial().getX()))
			return true;		
		return false;
	}
	
	public boolean ehVertical(){
		if(this.inclinacao == Double.POSITIVE_INFINITY 
				|| this.inclinacao == Double.NEGATIVE_INFINITY)
			return true;
		return false;
	}
	
	public double getConstante() {
		if(this.ehVertical())
			return pontoInicial.getX();
		return pontoInicial.getY() - inclinacao * pontoInicial.getX();
	}
	
	public Double getY(double x){
		if(!this.ehVertical())
			return this.getInclinacao() * x + this.getConstante();
		return null;
	}
	
	public Localizacao getPontoInicial() {
		return pontoInicial;
	}
	
	public void setPontoInicial(Localizacao pontoInicial) {
		this.pontoInicial = pontoInicial;
	}

	public Localizacao getPontoFinal() {
		return pontoFinal;
	}

	public void setPontoFinal(Localizacao pontoFinal) {
		this.pontoFinal = pontoFinal;
	}

	public double getInclinacao() {
		return inclinacao;
	}

	public void setInclinacao(double inclinacao) {
		this.inclinacao = inclinacao;
	}

	public double getDirecao() {
		return direcao;
	}

	public void setDirecao(double direcao) {
		this.direcao = direcao;
	}	
}

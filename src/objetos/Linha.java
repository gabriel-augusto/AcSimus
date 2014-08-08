package objetos;


public class Linha {

	private Localizacao pontoInicial;
	private Localizacao pontoFinal;
	private double inclinacao;
	
	public Linha(Localizacao pontoInicial, Localizacao pontoFinal){
		this.setPontoInicial(pontoInicial);
		this.setPontoFinal(pontoFinal);
		this.setInclinacao(calcularInclinacao());
	}
	
	public Linha(Localizacao pontoInicial, double inclinacao){
		this.setPontoInicial(pontoInicial);
		this.setInclinacao(inclinacao);
		this.setPontoFinal(null);
	}
	
	private double calcularInclinacao() {
		double x = pontoFinal.getX() - pontoInicial.getX();
		double y = pontoFinal.getY() - pontoInicial.getY();
		double m = y/x;
		return m;
	}

	private double calcularConstante() {
		return pontoInicial.getY() - inclinacao * pontoInicial.getX();
	}
	
	public Localizacao procurarPontoDeInterseccao(Linha linha){
		double x = (linha.getConstante() - this.getConstante())/(this.getInclinacao() - linha.getInclinacao());
		double y = this.getInclinacao() * x + this.getConstante();
		
		Localizacao pontoDeInterseccao = new Localizacao(x,y);
		
		if(pertenceAoIntervalo(pontoDeInterseccao))
			return pontoDeInterseccao;
		
		return null;
	}
	
	private boolean pertenceAoIntervalo(Localizacao localizacao){
		if(pontoFinal != null){
			if(localizacao.getX() <= Math.max(this.getPontoInicial().getX(), this.getPontoFinal().getX()) 
					&& localizacao.getX() >= Math.min(this.getPontoInicial().getX(), this.getPontoFinal().getX())
					&& localizacao.getY() <= Math.max(this.getPontoInicial().getY(), this.getPontoFinal().getY())
					&& localizacao.getY() >= Math.min(this.getPontoInicial().getY(), this.getPontoFinal().getY())){
				return true;
			}
		}else if((localizacao.getX() >= this.getPontoInicial().getX()) && (localizacao.getY() >= this.getPontoInicial().getY()))
			return true;		
		return false;
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
		return this.calcularInclinacao();
	}

	public void setInclinacao(double inclinacao) {
		this.inclinacao = inclinacao;
	}

	public double getConstante() {
		return this.calcularConstante();
	}
	
}

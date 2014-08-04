package simulador;

public class ObstaculoObject {

	private Localizacao pontoInicial;
	private Localizacao pontoFinal;
	private double indiceDeAbsorcao;
	private double inclinacao;
	private double constante;
	
	
	public ObstaculoObject(Localizacao pontoInicial, Localizacao pontoFinal, double indiceDeAbsorcao){
		this.pontoInicial = pontoInicial;
		this.pontoFinal = pontoFinal;
		this.indiceDeAbsorcao = indiceDeAbsorcao;
		this.inclinacao = calcularInclinacao();
		this.setConstante(calcularConstante());
	}

	private double calcularInclinacao() {
		// TODO Auto-generated method stub
		return 0;
	}

	private double calcularConstante() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public double getIndiceDeAbsorcao() {
		return indiceDeAbsorcao;
	}
	
	public void setIndiceDeAbsorcao(double indiceDeAbsorcao) {
		this.indiceDeAbsorcao = indiceDeAbsorcao;
	}
	
	public double getInclinacao() {
		return inclinacao;
	}
	
	public void setInclinacao(double inclinacao) {
		this.inclinacao = inclinacao;
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

	public double getConstante() {
		return constante;
	}

	public void setConstante(double constante) {
		this.constante = constante;
	}
	
}

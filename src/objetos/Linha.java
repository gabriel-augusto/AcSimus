package objetos;


public abstract class Linha {

	private Localizacao pontoInicial = null;
	private Localizacao pontoFinal = null;
	private double direcao = 0; // angulo de direcao.
	
	protected Linha(Localizacao pontoInicial, Localizacao pontoFinal){
		this.pontoInicial = pontoInicial;
		this.pontoFinal = pontoFinal;
	}
	
	protected Linha(Localizacao pontoInicial, double direcao){
		this.pontoInicial = pontoInicial;
		this.direcao = direcao;
	}
	
	public static Linha getLinha(Localizacao pontoInicial, Localizacao pontoFinal){
		if(ehVertical(pontoInicial, pontoFinal)){
			return new LinhaVertical(pontoInicial, pontoFinal);
		}
		return new LinhaNormal(pontoInicial, pontoFinal);
	}
	
	public static Linha getLinha(Localizacao pontoInicial, double direcao){
		if(ehVertical(direcao)){
			return new LinhaVertical(pontoInicial, direcao);
		}
		return new LinhaNormal(pontoInicial, direcao);
	}
	
	private static boolean ehVertical(Localizacao pontoInicial, Localizacao pontoFinal){
		if(pontoInicial.getX() == pontoFinal.getX())
			return true;
		return false;
	}
	
	public static boolean ehVertical(double direcao){
		if(direcao == 90 || direcao == 270)
			return true;
		return false;
	}
	
	public abstract double getInclinacao();
	
	public Localizacao procurarPontoDeInterseccao(Linha linha){
		if(linha instanceof LinhaNormal)
			return procurarPontoDeInterseccao((LinhaNormal)linha);
		return procurarPontoDeInterseccao((LinhaVertical) linha);
	}
	
	public abstract Localizacao procurarPontoDeInterseccao(LinhaVertical linha);
	
	public abstract Localizacao procurarPontoDeInterseccao(LinhaNormal linha);
	
	public double getConstante() {
		return 0;

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

	public double getDirecao() {
		return direcao;
	}

	public void setDirecao(double direcao) {
		this.direcao = direcao;
	}	
}

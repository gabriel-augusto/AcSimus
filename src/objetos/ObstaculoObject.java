package objetos;


public class ObstaculoObject {

	private Linha linha;
	private double indiceDeAbsorcao;
	
	
	public ObstaculoObject(Linha linha, double indiceDeAbsorcao){
		this.setLinha(linha);
		this.indiceDeAbsorcao = indiceDeAbsorcao;
	}
	
	public double getIndiceDeAbsorcao() {
		return indiceDeAbsorcao;
	}
	
	public void setIndiceDeAbsorcao(double indiceDeAbsorcao) {
		this.indiceDeAbsorcao = indiceDeAbsorcao;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}	
}

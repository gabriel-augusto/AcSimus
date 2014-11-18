package simulador;

import java.util.ArrayList;
import java.util.List;
import objetos.Linha;
import objetos.Localizacao;
import objetos.ObstaculoObject;
import utils.Util;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;


public class Som extends Agent{

	private static final long serialVersionUID = 1L;
	
	private static final int INTERVALO_DE_ATUALIZACAO = 20; //Intervalo de atualizacao do som em ms.
	private static final int TAMANHO_DO_PASSO = 1;
	private static final double ERRO = 1;//TAMANHO_DO_PASSO/2;
	
	private static List <ObstaculoObject> obstaculos;
	
	private Linha rota;
	private Localizacao pontoDeColisao;
	private double distanciaDeColisao;
	private ObstaculoObject obstaculoDeColisao;
	
	Localizacao localizacaoInicial;
	Localizacao localizacaoAtual;
	double direcao;
	double potencia;
	//private AID ambiente;
	//private AID fonteSonora;
	int distancia = 0;
	double indice;
	
	@Override
	protected void setup() {
		receberParametros();
		localizarProximoObstaculo();
		registrarSom();
		adicionarComportamentos();
	}

	private void adicionarComportamentos() {
		addBehaviour(new AtualizarSomBehaviour(this, INTERVALO_DE_ATUALIZACAO));
	}

	private void registrarSom(){
		try{
			DFAgentDescription som = new DFAgentDescription();
			som.setName(getAID());
			DFService.register(this, som);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void receberParametros() {
		Object[] args = getArguments();
		localizacaoAtual = new Localizacao((Localizacao) args[0]);
		localizacaoInicial = new Localizacao((Localizacao) args[0]);
		System.out.println("\nLocalizacao Inicial: " + localizacaoInicial + "\nLocalizacao Atual: " + localizacaoAtual);
		direcao = (double) args[1];
		potencia = (double) args[2];
		//ambiente = (AID) args[3];
		//fonteSonora = (AID) args[4];
		obstaculos = (ArrayList<ObstaculoObject>) args[5];
		//localizacaoInicial = definirNovaLocalizacaoInicial(localizacaoAtual.getX(), localizacaoAtual.getY());
		rota = Linha.getLinha(localizacaoInicial, direcao);
	}
	
	private void localizarProximoObstaculo(){
		pontoDeColisao = null;
		obstaculoDeColisao = null;
		Localizacao pontoDeInterseccao = null;
		for(ObstaculoObject obstaculo : obstaculos){
			pontoDeInterseccao = rota.procurarPontoDeInterseccao(obstaculo.getLinha());
			if(pontoDeInterseccao != null && !localizacaoAtual.equals(pontoDeInterseccao, ERRO)){
				if(pontoDeColisao == null || localizacaoAtual.distancia(pontoDeInterseccao) < localizacaoAtual.distancia(pontoDeColisao)){
					obstaculoDeColisao = obstaculo;
					pontoDeColisao = pontoDeInterseccao;
					distanciaDeColisao = localizacaoAtual.distancia(pontoDeColisao);
					System.out.println("Obstaculo encontrado: \nindice: " + obstaculo.getIndiceDeAbsorcao() + "\nponto de colisao: " + pontoDeColisao.toString());
				}
			}
		}
	}
	
	private void atualizar(){
		distancia = distancia + TAMANHO_DO_PASSO;
		atualizarLocalizacao();
		
		if(ehPontoDeColisao()){
			atualizarParametros();
			System.out.println("\nCOLIDIU!!!!!");
			System.out.println(this.escreverEstadoAtual());
			if(potencia < 5){
				finalizarSom();
				return;
			}
			localizarProximoObstaculo();
		}else
			System.out.println("\n"+this.escreverEstadoAtual());
	}

	private void atualizarLocalizacao() {
		localizacaoAtual.setX(calculaX(direcao, distancia) + localizacaoInicial.getX());
		localizacaoAtual.setY(calculaY(direcao, distancia) + localizacaoInicial.getY());
	}

	private boolean ehPontoDeColisao() {
		if(localizacaoAtual.equals(pontoDeColisao,ERRO))
			return true;
		return false;
	}
	
	private void finalizarSom() {
		doDelete();
		System.out.println("FIM DO SOM!!!");
	}
	
	private void atualizarParametros(){
		distancia = 0;
		localizacaoInicial = pontoDeColisao;
		calcularNovaDirecao();
		calcularPotencia(obstaculoDeColisao.getIndiceDeAbsorcao());
		rota = Linha.getLinha(localizacaoInicial, direcao);
	}

	private void calcularPotencia(double indice) {
		potencia = potencia - (potencia*(indice/100));
	}

	private void calcularNovaDirecao() {		
		double anguloDeInclinacaoDoObstaculo = Math.toDegrees(Math.atan(obstaculoDeColisao.getLinha().getInclinacao()));		  
		double novaDirecao = 2 * anguloDeInclinacaoDoObstaculo - direcao;			
		direcao = Util.padronizarAngulo(novaDirecao);
	}
	
	public double calculaX(double angulo, int hipotenusa){
		return Math.cos(Math.toRadians(angulo)) * hipotenusa;
	}
	
	public double calculaY(double angulo, int hipotenusa){
		return Math.sin(Math.toRadians(angulo)) * hipotenusa;
	}
	
	private String escreverEstadoAtual(){
		return this.getAID().getName() + ":" + "\npotencia: " + potencia + "\ndirecao: " + direcao + " graus" + "\ndistancia da origem: " + distancia + "\nlocalizacao inicial: " 
	+ localizacaoInicial + "\nlocalizacao: " + localizacaoAtual;
	}
	
	private static int idDisponivel = 0;

	public static String proximoId() {
		return "Som_" + (++idDisponivel);
	}
	
	/*------------------------------------------  COMPORTAMENTOS ------------------------------------- */
	
	private class AtualizarSomBehaviour extends TickerBehaviour {

		private static final long serialVersionUID = 5631501784835798992L;

		public AtualizarSomBehaviour(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			atualizar();
		}
		
	}	
}

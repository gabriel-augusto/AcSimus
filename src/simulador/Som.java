package simulador;

import java.util.ArrayList;
import java.util.List;

import objetos.Linha;
import objetos.Localizacao;
import objetos.ObstaculoObject;
import utils.Util;
//import jade.core.AID;
import jade.core.Agent;
//import jade.core.behaviours.CyclicBehaviour;
//import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
//import jade.lang.acl.ACLMessage;
//import linguagensEmensagens.Linguagem;
//import linguagensEmensagens.Mensagem;

public class Som extends Agent{

	private static final long serialVersionUID = 1L;
	
	private static final double TOLERANCIA = 0.000001;
	private long intervaloDeAtualizacao = 500; //Intervalo de atualizacao do som em ms.
	
	//private static List <String> pontosDeColisao = new ArrayList<>();
	private static List <ObstaculoObject> obstaculos;
	
	private Linha rota;
	private Localizacao pontoDeColisao;
	private ObstaculoObject obstaculoDeColisao;
	
	//double x0;
	//double y0;
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
		//addBehaviour(new SolicitarObstaculosBehaviour(this));
		addBehaviour(new AtualizarSomBehaviour(this, intervaloDeAtualizacao));
		//addBehaviour(new ReceberMensagemBehaviour(this));
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
		localizacaoAtual = (Localizacao) args[0];
		direcao = (double) args[1];
		potencia = (double) args[2];
		//ambiente = (AID) args[3];
		//fonteSonora = (AID) args[4];
		obstaculos = (ArrayList<ObstaculoObject>) args[5];
		localizacaoInicial = definirNovaLocalizacaoInicial(localizacaoAtual.getX(), localizacaoAtual.getY());
		rota = Linha.getLinha(localizacaoInicial, direcao);
	}
	
	private void localizarProximoObstaculo(){
		for(ObstaculoObject obstaculo : obstaculos){
			Localizacao pontoDeInterseccao = rota.procurarPontoDeInterseccao(obstaculo.getLinha());
			if(pontoDeInterseccao != null && !localizacaoAtual.equals(pontoDeInterseccao, 0.5)){
				obstaculoDeColisao = obstaculo;
				pontoDeColisao = rota.procurarPontoDeInterseccao(obstaculo.getLinha());
				System.out.println("Obstaculo encontrado: \nindice: " + obstaculo.getIndiceDeAbsorcao() + "\nponto de colisao: " + pontoDeColisao.toString());
				return;
			}
		}
		//pontoDeColisao = null;
		//obstaculoDeColisao = null;
	}
	
	private void atualizar(){
		if(potencia < 5){
			finalizarSom();
			System.out.println("Fim do SOM!!!");
			//return;
		}
		distancia++;
		atualizarLocalizacao();
		
		if(ehPontoDeColisao(localizacaoAtual)){
			//solicitarIndice(localizacaoAtual.toString());	
			System.out.println("COLIDIU!!!!!");
			atualizarParametros();
			//localizarProximoObstaculo();
		}
		System.out.println(this.escreverEstadoAtual());
		if(ehPontoDeColisao(localizacaoAtual)){
			//solicitarIndice(localizacaoAtual.toString());		
			//atualizarParametros();
			localizarProximoObstaculo();
		}
	}

	private void atualizarLocalizacao() {
		localizacaoAtual.setX(calculaX(direcao, distancia) + localizacaoInicial.getX());
		localizacaoAtual.setY(calculaY(direcao, distancia) + localizacaoInicial.getY());
	}

	private boolean ehPontoDeColisao(Localizacao localizacao) {
		/*
		for(String pontoDeColisao : pontosDeColisao){
			if(localizacao.toString().equals(pontoDeColisao)){
				return true;				
			}
		}
		return false;
		*/
		if(Math.abs(localizacao.getX() - pontoDeColisao.getX()) < 0.5 
				|| Math.abs(localizacao.getY() - pontoDeColisao.getY()) < 0.5)
			return true;
		return false;
	}
	
	private void finalizarSom() {
		doDelete();
		System.out.println("Fim do som.");
	}
	
	private void atualizarParametros(){
		distancia = 0;
		localizacaoInicial = pontoDeColisao;//definirNovaLocalizacaoInicial(localizacaoAtual.getX(), localizacaoAtual.getY());
		localizacaoAtual = localizacaoInicial;
		//localizacaoAtual = pontoDeColisao;
		calcularNovaDirecao();
		calcularPotencia(obstaculoDeColisao.getIndiceDeAbsorcao());
		rota = Linha.getLinha(localizacaoInicial, direcao);
	}

	private void calcularPotencia(double indice) {
		potencia = potencia - (potencia*((double)indice/100));
	}

	private Localizacao definirNovaLocalizacaoInicial(double x, double y) {
		//localizacaoInicial.setX(localizacaoAtual.getX());
		//localizacaoInicial.setY(localizacaoAtual.getY());
		return new Localizacao(x,y);
	}

	private void calcularNovaDirecao() {
		
		double angulo = 2 * Math.toDegrees(Math.atan(obstaculoDeColisao.getLinha().getInclinacao()));
		
		while(angulo < 360)
			angulo = angulo + 360;
		  
		double novaDirecao = angulo - direcao;
		  
		while(novaDirecao > 360)
			novaDirecao = novaDirecao - 360;
			
		direcao = novaDirecao;
		 
		/*
		direcao = 540 - direcao;
		while(direcao>360)
			direcao = direcao - 360;
			*/
	}
	/*
	private void solicitarIndice(String pontoDeColisao){
		ACLMessage msg = Mensagem.prepararMensagem(ACLMessage.REQUEST, 
				Linguagem.INDICE, pontoDeColisao, ambiente,  fonteSonora);
		send(msg);
		System.out.println("Som: indice do obstaculo solicitado.");
	}
	*/
	public double calculaX(double angulo, int hipotenusa){
		double x = Math.cos(angulo * Math.PI/180) * hipotenusa;
		return x;
	}
	
	public double calculaY(double angulo, int hipotenusa){
		double y = Math.sin(angulo * Math.PI/180) * hipotenusa;
		return y;
	}

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
	/*
	private class SolicitarObstaculosBehaviour extends OneShotBehaviour {

		private static final long serialVersionUID = 1L;

		public SolicitarObstaculosBehaviour(Agent agent) {
			super(agent);
		}

		@Override
		public void action() {			
			ACLMessage mensagem = Mensagem.prepararMensagem(ACLMessage.REQUEST, null, Mensagem.QUAL_LOCALIZACAO, ambiente, fonteSonora);
			send(mensagem);
			System.out.println("Som: localizacao dos obstaculos solicitada.");
		}
	}
	
	private class ReceberMensagemBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = 1L;

		public ReceberMensagemBehaviour(Agent agent) {
			super(agent);
		}
		
		@Override
		public void action() {
			ACLMessage mensagem = receive();

			if (mensagem != null) {
				AID remetente = mensagem.getSender();
				responder(mensagem, remetente);
			}

		}

		private void responder(ACLMessage mensagem, AID destino) {
			if (mensagem.getPerformative() == ACLMessage.REQUEST && 
					mensagem.getContent().equals(Mensagem.QUAL_LOCALIZACAO)) {
				responderLocalizacao(destino);
			}

			else if(mensagem.getPerformative() == ACLMessage.INFORM && 
					(mensagem.getLanguage().equals(Linguagem.LOCALIZACAO))){
				System.out.println("Som: Pontos de colisao recebidos.");
				pontosDeColisao.add(mensagem.getContent());
			}
			
			else if(mensagem.getPerformative() == ACLMessage.INFORM && 
					mensagem.getLanguage().equals(Linguagem.INDICE)){
				System.out.println("Som: Indice recebido!");
				indice = Integer.valueOf(mensagem.getContent());
				atualizarParametros(indice);
				System.out.println("Indice: " + indice + "\n");
			}
			else {
				responderMensagemNaoCompreendida(destino);
			}
		}
		
		private void responderLocalizacao(AID destino){
			ACLMessage resposta = Mensagem.prepararMensagem(ACLMessage.INFORM, null, localizacaoAtual.toString(), destino);
			send(resposta);
		}

		private void responderMensagemNaoCompreendida(AID destino) {
			ACLMessage resposta = Mensagem.getRespostaDeMensagemNaoCompreendida(destino);
			send(resposta);
		}
	}
		*/
	private String escreverEstadoAtual(){
		return "\npotencia: " + potencia + "\ndirecao: " + direcao + " graus \nlocalizacao inicial: " 
	+ localizacaoInicial.toString() + "\nlocalizacao: " + localizacaoAtual;
	}
	
	private static int idDisponivel = 0;

	public static String proximoId() {
		return "som_" + (++idDisponivel);
	}
}

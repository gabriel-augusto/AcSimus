package simulador;

import java.util.ArrayList;
import java.util.List;

import objetos.Localizacao;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import linguagensEmensagens.Linguagem;
import linguagensEmensagens.Mensagem;

public class Som extends Agent{

	private static final long serialVersionUID = 1L;
	
	private static List <String> pontosDeColisao = new ArrayList<>();
	
	private long intervaloDeAtualizacao = 500; //Intervalo de atualizacao do som em ms.
	
	double x0;
	double y0;
	Localizacao localizacao;
	double direcao;
	double potencia;
	private AID ambiente;
	private AID fonteSonora;
	int distancia = 0;
	int indice;
	
	@Override
	protected void setup() {
		receberParametros();
		registrarSom();
		adicionarComportamentos();
	}

	private void adicionarComportamentos() {
		addBehaviour(new SolicitarObstaculosBehaviour(this));
		addBehaviour(new AtualizarSomBehaviour(this, intervaloDeAtualizacao));
		addBehaviour(new ReceberMensagemBehaviour(this));
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

	private void receberParametros() {
		Object[] args = getArguments();
		localizacao = (Localizacao) args[0];
		direcao = (double) args[1];
		potencia = (double) args[2];
		ambiente = (AID) args[3];
		fonteSonora = (AID) args[4];
		definirNovaLocalizacaoInicial();
	}
	
	private void atualizar(){
		System.out.println(this.escreverEstadoAtual());
		if(potencia < 5){
			finalizarSom();
			return;
		}
		distancia++;
		atualizarLocalizacao();
		
		if(ehPontoDeColisao(localizacao))
			solicitarIndice(localizacao.toString());		
	}

	private void atualizarLocalizacao() {
		localizacao.setX(calculaX(direcao, distancia) + x0);
		localizacao.setY(calculaY(direcao, distancia) + y0);
	}

	private boolean ehPontoDeColisao(Localizacao localizacao) {
		for(String pontoDeColisao : pontosDeColisao){
			if(localizacao.toString().equals(pontoDeColisao)){
				return true;				
			}
		}
		return false;
	}
	
	private void finalizarSom() {
		doDelete();
		System.out.println("Fim do som.");
	}
	
	private void atualizarParametros(int indice){
		distancia = 0;
		definirNovaLocalizacaoInicial();
		calcularNovaDirecao();
		calcularPotencia(indice);
	}

	private void calcularPotencia(int indice) {
		potencia = potencia - (potencia*((double)indice/100));
	}

	private void definirNovaLocalizacaoInicial() {
		x0 = localizacao.getX();
		y0 = localizacao.getY();
	}

	private void calcularNovaDirecao() {
		/*
		int angulo = 2 * anguloDoObstaculo;
		while(angulo < 360)
			angulo = angulo + 360;
		  
		int novaDirecao = angulo - direcao;
		  
		while(direcao > 360)
			novaDirecao = novaDirecao - 360;
			
		return novaDirecao;
		 */
		direcao = 540 - direcao;
		while(direcao>360)
			direcao = direcao - 360;
	}
	
	
	
	private void solicitarIndice(String pontoDeColisao){
		ACLMessage msg = Mensagem.prepararMensagem(ACLMessage.REQUEST, 
				Linguagem.INDICE, pontoDeColisao, ambiente,  fonteSonora);
		send(msg);
		System.out.println("Som: indice do obstaculo solicitado.");
	}
	
	public int calculaX(double angulo, int hipotenusa){
		int x = (int) Math.cos(angulo * Math.PI/180) * hipotenusa;
		return x;
	}
	
	public int calculaY(double angulo, int hipotenusa){
		int y = (int) Math.sin(angulo * Math.PI/180) * hipotenusa;
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
			ACLMessage resposta = Mensagem.prepararMensagem(ACLMessage.INFORM, null, localizacao.toString(), destino);
			send(resposta);
		}

		private void responderMensagemNaoCompreendida(AID destino) {
			ACLMessage resposta = Mensagem.getRespostaDeMensagemNaoCompreendida(destino);
			send(resposta);
		}
	}
		
	private String escreverEstadoAtual(){
		return "localizacao: " + localizacao + "\npotencia: " + potencia 
				+ "\ndirecao: " + direcao + " graus";
	}
	
	private static int idDisponivel = 0;

	public static String proximoId() {
		return "som_" + (++idDisponivel);
	}
}

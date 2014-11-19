package simulador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import objetos.Linha;
import objetos.Localizacao;
import objetos.Obstacle;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.PlatformController;
import linguagensEmensagens.Linguagem;
import linguagensEmensagens.Mensagem;
import utils.Util;

public class Ambiente extends Agent{

	private static final long serialVersionUID = 1L;
	
	private final String SOUNDSOURCE = "FonteSonora";
	private final String OBSTACLE = "Obstaculo";
	
	private List <Obstacle> obstaclesObjects = new ArrayList<>();
	private List <AID> obstacle = new ArrayList<>();
	private List <AID> soundSources = new ArrayList<>();

	//private static Queue<AID> filaParaReceberLocalizaca = new LinkedList<AID>();
	//private static Queue<AID> filaParaReceberIndice = new LinkedList<AID>();

	@Override
	protected void setup() {
		defineAmbient();
	}
	
	public void defineAmbient() {
		PlatformController container = getContainerController();
		
		Obstacle wall1 = new Obstacle(Linha.getLine(new Localizacao(0,0), new Localizacao(0,100)), 10);
		Obstacle wall2 = new Obstacle(Linha.getLine(new Localizacao(0,100), new Localizacao(100,100)), 20);
		Obstacle wall3 = new Obstacle(Linha.getLine(new Localizacao(100,100), new Localizacao(100,0)), 30);
		Obstacle wall4 = new Obstacle(Linha.getLine(new Localizacao(100,0), new Localizacao(0,0)), 40);
		Obstacle wall5 = new Obstacle(Linha.getLine(new Localizacao(50,30), new Localizacao(50,70)), 50);
		
		obstaclesObjects.add(wall1);
		obstaclesObjects.add(wall2);
		obstaclesObjects.add(wall3);
		obstaclesObjects.add(wall4);
		obstaclesObjects.add(wall5);
		/* Obstaculo */
		/*
		Object[] argsObstaculo = {new Localizacao(5,5), 20};
		obstaculos.add(criarObjeto(argsObstaculo, container, this.OBSTACULO));
		*/
		/* fonte sonora */
		Object[] argsSoundSource = {new Localizacao(0,50), this.getAID(), 15, obstaclesObjects};		
		soundSources.add(createObject(argsSoundSource, container,this.SOUNDSOURCE));
		
	//	addBehaviour(new ReceberMensagemBehaviour(this));
	}

	private AID createObject(Object[] args, PlatformController container, String type) {
		String id = getNextId(type);		
		Util.initAgent(container, args, "simulador."+type, id);
		System.out.println(type + " criado(a) em: " + args[0]);
		return new AID(id, AID.ISLOCALNAME);
	}

	public String getNextId(String type) {
		
		return FonteSonora.nextId();
		
	}
/*	
	private class ReceberMensagemBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = 1L;

		public ReceberMensagemBehaviour(Agent agent) {
			super(agent);
		}

		@Override
		public void action() {
			ACLMessage mensagem = receive();

			if (mensagem == null) {
				return;
			}

			switch (mensagem.getPerformative()) {

			case ACLMessage.REQUEST:
				receberRequisicao(mensagem);
				break;

			case ACLMessage.INFORM:
				receberInforamcao(mensagem);
				break;
				
			case ACLMessage.NOT_UNDERSTOOD:
				System.out.println(mensagem);
				break;
			}
		}

		private void receberInforamcao(ACLMessage mensagem) {
			if(mensagem.getLanguage().equals(Linguagem.LOCALIZACAO)){
				System.out.println("Ambiente: localizacao dos obstaculos recebida.");
				responderLocalizacao(mensagem.getContent(), filaParaReceberLocalizacao.remove());	
			}
			
			if(mensagem.getLanguage().equals(Linguagem.INDICE)){
				System.out.println("Ambiente: indice do obstaculo recebido");
				responderIndice(mensagem.getContent(), filaParaReceberIndice.remove());				
			}
		}
		
		private void receberRequisicao(ACLMessage mensagem) {
			if(mensagem.getContent().equals(Mensagem.QUAL_LOCALIZACAO)){
				filaParaReceberLocalizacao.add(mensagem.getSender());
				solicitarLocalizacao(obstacle);
			}	
			else if(mensagem.getLanguage().equals(Linguagem.INDICE)){
				filaParaReceberIndice.add(mensagem.getSender());
				solicitarIndice(mensagem.getContent(), obstacle);
			}
		}
		
		private void responderIndice(String conteudo, AID destino) {
			ACLMessage msg = Mensagem.prepararMensagem(ACLMessage.INFORM, 
					Linguagem.INDICE, conteudo, destino);
			send(msg);
		}

		private void responderLocalizacao(String conteudo, AID destino) {
			ACLMessage msg = Mensagem.prepararMensagem(ACLMessage.INFORM, 
					Linguagem.LOCALIZACAO, conteudo, destino);
			send(msg);
		}

		private void solicitarIndice(String conteudo, List <AID> destinos) {
			ACLMessage msg = Mensagem.prepararMensagem(ACLMessage.REQUEST,
					Linguagem.INDICE, conteudo, obstacle);
			send(msg);
			System.out.println("Ambiente: indice do obstaculo solicitado.");
		}

		private void solicitarLocalizacao(List <AID> destinos) {
			ACLMessage msg = Mensagem.prepararMensagem(ACLMessage.REQUEST,
					null, Mensagem.QUAL_LOCALIZACAO, obstacle);
			send(msg);
			System.out.println("Ambiente: localizacao dos obstaculos solicitada.");
		} 
	}*/
}

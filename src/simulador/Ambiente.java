package simulador;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
	
	private final String FONTESONORA = "FonteSonora";
	private final String OBSTACULO = "Obstaculo";
	
	private List <AID> obstaculos = new ArrayList<>();
	private List <AID> fontesSonoras = new ArrayList<>();

	private static Queue<AID> filaParaReceberLocalizacao = new LinkedList<AID>();
	private static Queue<AID> filaParaReceberIndice = new LinkedList<AID>();

	@Override
	protected void setup() {
		definirAmbiente();
	}
	
	public void definirAmbiente() {
		PlatformController container = getContainerController();
		
		/* Obstaculo */
		Object[] argsObstaculo = {new Localizacao(5,5), 20};
		obstaculos.add(criarObjeto(argsObstaculo, container, this.OBSTACULO));
	
		/* fonte sonora */
		Object[] argsFonteSonora = {new Localizacao(0,5), this.getAID(), 15};		
		fontesSonoras.add(criarObjeto(argsFonteSonora, container,this.FONTESONORA));
		
		addBehaviour(new ReceberMensagemBehaviour(this));
	}

	private AID criarObjeto(Object[] args, PlatformController container, String tipo) {
		String id = solicitarProximoId(tipo);		
		Util.inicializarAgente(container, args, "simulador."+tipo, id);
		System.out.println(tipo + " criado(a) em: " + args[0]);
		return new AID(id, AID.ISLOCALNAME);
	}

	public String solicitarProximoId(String tipo) {
		String id = null;
		switch(tipo){
		case FONTESONORA:
			id = FonteSonora.proximoId();
			break;
		case OBSTACULO:
			id = Obstaculo.proximoId();
			break;
		}
		return id;
	}
	
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
				System.out.println("Sala: localizacao dos obstaculos recebida.");
				responderLocalizacao(mensagem.getContent(), filaParaReceberLocalizacao.remove());	
			}
			
			if(mensagem.getLanguage().equals(Linguagem.INDICE)){
				System.out.println("Sala: indice do obstaculo recebido");
				responderIndice(mensagem.getContent(), filaParaReceberIndice.remove());				
			}
		}
		
		private void receberRequisicao(ACLMessage mensagem) {
			if(mensagem.getContent().equals(Mensagem.QUAL_LOCALIZACAO)){
				filaParaReceberLocalizacao.add(mensagem.getSender());
				solicitarLocalizacao(obstaculos);
			}	
			else if(mensagem.getLanguage().equals(Linguagem.INDICE)){
				filaParaReceberIndice.add(mensagem.getSender());
				solicitarIndice(mensagem.getContent(), obstaculos);
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
					Linguagem.INDICE, conteudo, obstaculos);
			send(msg);
			System.out.println("Ambiente: indice do obstaculo solicitado.");
		}

		private void solicitarLocalizacao(List <AID> destinos) {
			ACLMessage msg = Mensagem.prepararMensagem(ACLMessage.REQUEST,
					null, Mensagem.QUAL_LOCALIZACAO, obstaculos);
			send(msg);
			System.out.println("Ambiente: localizacao dos obstaculos solicitada.");
		}
	}
}

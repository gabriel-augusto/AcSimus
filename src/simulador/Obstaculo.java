package simulador;

import simulador.Localizacao;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import linguagensEmensagens.*;

public class Obstaculo extends Agent{
	
	private Localizacao localizacao;
	private int indiceAbsorcao;

	private static final long serialVersionUID = 1L;

	public Obstaculo() {
		
	}
	
	@Override
	protected void setup() {
			receberParametros();
			registrarObstaculo();
			addBehaviour(new ReceberMensagemBehaviour(this));
	}

	private void receberParametros() {
		Object[] args = getArguments();
		localizacao =((Localizacao) args[0]);
		indiceAbsorcao = ((int) args[1]);
	}

	private void registrarObstaculo(){
		try{
			DFAgentDescription obstaculo = new DFAgentDescription();
			obstaculo.setName(getAID());
			DFService.register(this, obstaculo);
		}catch (FIPAException e) {
			e.printStackTrace();
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
				this.responder(mensagem, remetente);
			}

		}

		private void responder(ACLMessage mensagem, AID destino) {
			if (mensagem.getPerformative() == ACLMessage.REQUEST && 
					mensagem.getContent().equals(Mensagem.QUAL_LOCALIZACAO)){
				System.out.println("Obstaculo: enviando localizacao...");
				this.responderLocalizacao(destino);
			}
			
			else if (mensagem.getPerformative() == ACLMessage.REQUEST && 
					mensagem.getLanguage().equals(Linguagem.INDICE) && 
					mensagem.getContent().equals(localizacao.toString())){
				System.out.println("OB: " + localizacao.toString());
				this.responderIndice(destino);
			}
			
			else {
				this.responderMensagemNaoCompreendida(destino);
			}
		}
		
		private void responderLocalizacao(AID destino){
			ACLMessage mensagem = Mensagem.prepararMensagem(ACLMessage.INFORM, 
					Linguagem.LOCALIZACAO, localizacao.toString(), destino);
			send(mensagem);
			System.out.println("Obstaculo: Localizacao enviada.");
		}

		private void responderIndice(AID destino){		
			ACLMessage mensagem = Mensagem.prepararMensagem(ACLMessage.INFORM, 
					Linguagem.INDICE, Integer.toString(indiceAbsorcao), destino);
			send(mensagem);
			System.out.println("Obstaculo: Indice enviado.");
		}

		private void responderMensagemNaoCompreendida(AID destino){
			ACLMessage resposta = Mensagem.getRespostaDeMensagemNaoCompreendida(destino);
			send(resposta);
		}
	}

	private static int idDisponivel = 0;

	public static String proximoId() {
		return "obstaculo_" + (++idDisponivel);
	}
}

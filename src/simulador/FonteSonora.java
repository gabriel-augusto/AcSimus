package simulador;

import java.util.ArrayList;
import java.util.List;

import objetos.Localizacao;
import objetos.ObstaculoObject;
import utils.Util;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.PlatformController;
import linguagensEmensagens.Linguagem;
import linguagensEmensagens.Mensagem;

public class FonteSonora extends Agent{

	private static final long serialVersionUID = 1L;
	
	PlatformController container;
	
	private int intervaloAtualizacao = 10000;
	
	private static List <ObstaculoObject> obstaculos;
	
	private AID ambiente;
	private AID fonteSonora;
	private AID som;
	
	private Localizacao localizacao;
	private int indiceAbsorcao;
	//private double direcao;
	//private double potencia;

	@Override
	protected void setup() {
			receberParametros(); 
			registrarFonteSonora();		
			adicionarComportamentos();			
			
			lancarPulso(0,60,80);
	}
	
	private void lancarPulso(double direcao, double abertura, double potencia){
		double angulo;
		criarSom(localizacao, direcao, potencia);
		for(int i = 1; i<=abertura/2; i++){
			angulo = padronizarAngulo(direcao+i);			
			criarSom(localizacao,angulo,potencia);
			angulo = padronizarAngulo(direcao-1);
			criarSom(localizacao,angulo,potencia);
		}
	}
	
	private double padronizarAngulo(double angulo){
		while(angulo < 0 || angulo > 360){
			if(angulo<0)
				angulo = angulo+360;
			if(angulo>360)
				angulo = angulo-360;
		}
		return angulo;
	}

	private void adicionarComportamentos() {		
			//addBehaviour(new AtualizarSomBehaviour(this, intervaloAtualizacao));
			addBehaviour(new ReceberMensagemBehaviour(this));
	}

	private void registrarFonteSonora(){
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void receberParametros() {
		fonteSonora = this.getAID();
		Object[] args = getArguments();
		localizacao = (Localizacao) args[0];
		ambiente = (AID) args[1];
		indiceAbsorcao = (int) args[2];
		obstaculos = (ArrayList<ObstaculoObject>) args[3];
	}

	private void criarSom(Localizacao localizacao, double direcao, double potencia){
		Object[] args = {new Localizacao(localizacao), direcao, potencia, ambiente, fonteSonora, obstaculos};
		final String id = Som.proximoId();
		som = new AID(id, AID.ISLOCALNAME);	
		
		container = getContainerController();
		Util.inicializarAgente(container, args, "simulador.Som", id);
		
		System.out.println(id + " criado em: "+localizacao);
	}
	
	private class AtualizarSomBehaviour extends TickerBehaviour {

		private static final long serialVersionUID = 1L;

		public AtualizarSomBehaviour(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			criarSom(localizacao, 0, 60);
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

				if (mensagem.getPerformative() == ACLMessage.REQUEST && 
						mensagem.getContent().equals(Mensagem.QUAL_LOCALIZACAO)){
					responderLocalizacao(remetente);
					System.out.println("Fonte Sonora: Localizacao enviada.");
				}
				else if (mensagem.getPerformative() == ACLMessage.REQUEST && 
						mensagem.getLanguage().equals(Linguagem.INDICE) && 
						mensagem.getContent().equals(localizacao.toString())){
					System.out.println("FS: " + localizacao.toString());
					responderIndice(remetente);
				}
				else {
					send(Mensagem.getRespostaDeMensagemNaoCompreendida(remetente));					
				}
			}

		}
		
		public void responderIndice(AID destino){
			ACLMessage mensagem = Mensagem.prepararMensagem(ACLMessage.INFORM, 
					Linguagem.INDICE, Integer.toString(indiceAbsorcao), destino);
			send(mensagem);
			System.out.println("Fonte Sonora: Indice enviado.");
		}
		
		public void responderLocalizacao(AID destino){
			ACLMessage mensagem = Mensagem.prepararMensagem(ACLMessage.INFORM, 
					Linguagem.LOCALIZACAO, localizacao.toString(), destino);
			send(mensagem);
		}
	}
	
	private static int idDisponivel = 0;

	public static String proximoId() {
		return "fonte_sonora_" + (++idDisponivel);
	}
}

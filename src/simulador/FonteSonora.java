package simulador;

import java.util.ArrayList;
import java.util.List;

import objetos.Localizacao;
import objetos.Obstacle;
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
	
	private int updateTime = 10000;
	
	private static List <Obstacle> obstacles;
	
	private AID ambient;
	private AID soundSource;
	private AID sound;
	
	private Localizacao location;
	private int absorptionRate;
	//private double direcao;
	//private double potencia;

	@Override
	protected void setup() {
			getParameters();
			registerSoundSource();		
			addBehavior();			
			//criarSom(localizacao,0,80);
			emitSoundPulse(0,90,80);
	}
	
	private void emitSoundPulse(double direction, double opening, double potency){
		double angle;
		createSound(location, direction, potency);
		for(double i = 1; i<=opening/2; i++){
			angle = Util.normalizeAngle(direction+i);			
			createSound(location,angle,potency);
			angle = Util.normalizeAngle(direction-i);
			createSound(location,angle,potency);
		}
	}

	private void addBehavior() {		
			//addBehaviour(new AtualizarSomBehaviour(this, intervaloAtualizacao));
			addBehaviour(new GetMessageBehavior(this));
	}

	private void registerSoundSource(){
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			DFService.register(this, dfd);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private void getParameters() {
		soundSource = this.getAID();
		Object[] args = getArguments();
		location = (Localizacao) args[0];
		ambient = (AID) args[1];
		absorptionRate = (int) args[2];
		obstacles = (ArrayList<Obstacle>) args[3];
	}

	private void createSound(Localizacao location, double direction, double potency){
		Object[] args = {new Localizacao(location), direction, potency, ambient, soundSource, obstacles};
		final String id = Som.nextId();
		sound = new AID(id, AID.ISLOCALNAME);	
		
		container = getContainerController();
		Util.initAgent(container, args, "simulador.Som", id);
		
		System.out.println(id + " created at: "+location);
	}
	
	private class UpdateSoundBehavior extends TickerBehaviour {

		private static final long serialVersionUID = 1L;

		public UpdateSoundBehavior(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			createSound(location, 0, 60);
		}		
	}
	
	private class GetMessageBehavior extends CyclicBehaviour {
		
		private static final long serialVersionUID = 1L;

		public GetMessageBehavior(Agent agent) {
			super(agent);
		}
		
		@Override
		public void action() {
			ACLMessage message = receive();

			if (message != null) {
				AID sender = message.getSender();

				if (message.getPerformative() == ACLMessage.REQUEST && 
						message.getContent().equals(Mensagem.WHAT_IS_THE_LOCATION)){
					responseLocalization(sender);
					System.out.println("Sound Source: Location sent.");
				}
				else if (message.getPerformative() == ACLMessage.REQUEST && 
						message.getLanguage().equals(Linguagem.INDEX) && 
						message.getContent().equals(location.toString())){
					System.out.println("SS: " + location.toString());
					indexAnswer(sender);
				}
				else {
					send(Mensagem.getAnswerOfANotUnderstoodMessage(sender));					
				}
			}

		}
		
		public void indexAnswer(AID destination){
			ACLMessage message = Mensagem.prepareMessage(ACLMessage.INFORM, 
					Linguagem.INDEX, Integer.toString(absorptionRate), destination);
			send(message);
			System.out.println("Sound Source: Index sent.");
		}
		
		public void responseLocalization(AID destination){
			ACLMessage message = Mensagem.prepareMessage(ACLMessage.INFORM, 
					Linguagem.LOCATION, location.toString(), destination);
			send(message);
		}
	}
	
	private static int idDisponivel = 0;

	public static String nextId() {
		return "fonte_sonora_" + (++idDisponivel);
	}
}

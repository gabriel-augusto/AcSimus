package simulator.agents;

import java.util.ArrayList;
import java.util.List;

import simulator.objects.Localization;
import simulator.objects.Obstacle;
import utils.Util;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
//import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.PlatformController;
import languagesAndMessages.Language;
import languagesAndMessages.Message;

public class SoundSource extends Agent{

	private static final long serialVersionUID = 1L;
	
	PlatformController container;
	
	//private int updateTime = 10000; //Uncomment if the UpdateSoundBehaviour is activated.
	
	private static List <Obstacle> obstacles;
	
	private AID ambient;
	private AID soundSource;
	private Localization location;
	private int absorptionRate;
	private double soundSeparation = 1;

	@Override
	protected void setup() {
			getParameters();
			registerSoundSource();		
			addBehavior();			
			emitSoundPulse(0,90,80);
	}
	
	private void emitSoundPulse(double direction, double opening, double potency){
		double angle;
		createSound(location, direction, potency);
		for(double i = soundSeparation; i<=opening/2; i=i+soundSeparation){
			angle = Util.normalizeAngle(direction+i);			
			createSound(location,angle,potency);
			angle = Util.normalizeAngle(direction-i);
			createSound(location,angle,potency);
		}
	}

	private void addBehavior() {
		//addBehaviour(new UpdateSoundBehaviour(this, updateTime)); //Uncomment to add this behaviour
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
		location = (Localization) args[0];
		ambient = (AID) args[1];
		absorptionRate = (int) args[2];
		obstacles = (ArrayList<Obstacle>) args[3];
	}

	private void createSound(Localization location, double direction, double potency){
		Object[] args = {new Localization(location), direction, potency, ambient, soundSource, obstacles};
		final String id = Sound.nextId();
		new AID(id, AID.ISLOCALNAME);	
		
		container = getContainerController();
		Util.initAgent(container, args, 
				"simulator.agents.Sound", id);
		
		System.out.println(id + " created at: " + location);
	}
//Uncomment to add this behaviour.
/*	
	private class UpdateSoundBehaviour extends TickerBehaviour {

		private static final long serialVersionUID = 1L;

		public UpdateSoundBehaviour(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			createSound(location, 0, 60);
		}		
	}
*/
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

				if (message.getPerformative() == ACLMessage.REQUEST && message.getContent().equals(Message.WHAT_IS_THE_LOCATION)){
					responseLocalization(sender);
					System.out.println("Sound Source: Location sent.");
				}
				else if (message.getPerformative() == ACLMessage.REQUEST && message.getLanguage().equals(Language.INDEX) && message.getContent().equals(location.toString())){
					System.out.println("SS: " + location.toString());
					indexAnswer(sender);
				}
				else {
					send(Message.getAnswerOfANotUnderstoodMessage(
							sender));					
				}
			}

		}
		
		public void indexAnswer(AID destination){
			ACLMessage message = Message.prepareMessage(ACLMessage.INFORM, Language.INDEX, Integer.toString(absorptionRate), destination);
			send(message);
			System.out.println("Sound Source: Index sent.");
		}
		
		public void responseLocalization(AID destination){
			ACLMessage message = Message.prepareMessage(ACLMessage.INFORM, Language.LOCATION, location.toString(), destination);
			send(message);
		}
	}
	
	private static int id = 0;

	public static String nextId() {
		return "sound_source" + (++id);
	}
}

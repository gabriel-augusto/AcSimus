package simulator.agents;

import java.util.HashMap;

import simulator.objects.Location;
import simulator.objects.Obstacle;
import utils.Util;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import languagesAndMessages.Language;
import languagesAndMessages.Message;

public class SoundSource extends Agent{

	private static final long serialVersionUID = 1L;
	
	PlatformController container;
	
	//private int updateTime = 10000; //Uncomment if the UpdateSoundBehaviour is activated.
	
	private static HashMap <String, Obstacle> obstacles;
	private HashMap <String, AID> sounds = new HashMap<>();
	
	private AID ambient;
	private AID soundSource;
	private Location location;
	private static double soundSeparation = 1;
	private int opening;
	private int power;
	private int direction;

	@Override
	protected void setup() {
			getParameters();
			registerSoundSource();		
			addBehaviours();
	}
	
	private void emitSoundPulse(double direction, int opening, double potency){
		double angle;
		createSound(location, direction, potency, opening);
		for(double i = soundSeparation; i<=opening/2; i=i+soundSeparation){
			angle = Util.normalizeAngle(direction+i);			
			createSound(location,angle,potency, opening);
			angle = Util.normalizeAngle(direction-i);
			createSound(location,angle,potency, opening);
		}
	}

	private void addBehaviours() {
		//addBehaviour(new UpdateSoundBehaviour(this, updateTime)); //Uncomment to add this behaviour
		addBehaviour(new GetMessageBehaviour(this));
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
		location = (Location) args[0];
		power = (int) args[1];
		opening = (int) args[2];
		direction = (int) args[3];
		ambient = (AID) args[4];
		obstacles = (HashMap<String, Obstacle>) args[5];
	}

	private AID createSound (Location location, double direction, double potency, int opening){
		final String id = Sound.nextId();
		Object[] args = {new Location(location), direction, potency, opening, ambient, soundSource, obstacles, id, this.getAID()};
		
		container = getContainerController();
		Util.initAgent(container, args, "simulator.agents.Sound", id);
		
		System.out.println(id + " created at: " + location);
		AID sound = new AID(id, AID.ISLOCALNAME);
		sounds.put(id, sound);
		return sound;
	}
	
	/*--------------------------  COMPORTAMENTS ------------------------ */
	/* Uncomment to implement this behaviour
	private class UpdateSoundBehaviour extends TickerBehaviour {

		private static final long serialVersionUID = 1L;

		public UpdateSoundBehaviour(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			emitSoundPulse(direction, opening, power);
		}		
	}
*/
	
	private class GetMessageBehaviour extends CyclicBehaviour {
		
		private static final long serialVersionUID = 1L;

		public GetMessageBehaviour(Agent agent) {
			super(agent);
		}
		
		@Override
		public void action() {
			ACLMessage message = receive();

			if (message != null && message.getPerformative() == ACLMessage.INFORM) {
				AID sender = message.getSender();

				if (message.getContent().equals(Message.STOP_RESUMED)){
					stopSimulation(Message.STOP_RESUMED);
					System.out.println("Simulation stoped.");
				}
                else if (message.getContent().equals(Message.STOP_PAUSED)){
					stopSimulation(Message.STOP_PAUSED);
					System.out.println("Simulation stoped.");
				}
				else if(message.getContent().equals(Message.PAUSE)){
					suspendAllSounds();
				}
				else if(message.getContent().equals(Message.RESUME)){
					resumeAllSounds();
				}
				else if(message.getContent().equals(Message.RUN)){
					emitSoundPulse(direction,opening,power);
				}
				else if(message.getLanguage().equals(Language.FINISH)){
					sounds.remove(message.getContent());
					if(sounds.isEmpty()){
						send(Message.prepareMessage(ACLMessage.INFORM, null, Message.FINISH_SIMULATION, ambient));
					}
				}
				else {
					send(Message.getAnswerOfANotUnderstoodMessage(sender));					
				}
			}

		}
	}
	
	private static int id = 0;

	public static String nextId() {
		return "sound_source" + (++id);
	}
	
	public void suspendAllSounds() {
		send(Message.prepareMessage(ACLMessage.INFORM, null, Message.PAUSE, sounds));		
	}

	public void resumeAllSounds() {
		ContainerController cc = getContainerController();
		AgentController ac;
		for(AID sound : sounds.values()){
			try {
				ac = cc.getAgent(sound.getLocalName());
				ac.activate();
			} catch (ControllerException e) {
				e.printStackTrace();
			}
		}
	}

	private void stopSimulation(String status){
		
                if(status.equals(Message.STOP_RESUMED))
                        send(Message.prepareMessage(ACLMessage.INFORM, null, Message.STOP_RESUMED, sounds));
                else{
                        ContainerController cc = getContainerController();
                        AgentController ac;
                        for(AID sound : sounds.values()){
                                try {
                                        ac = cc.getAgent(sound.getLocalName());
                                        ac.kill();
                                } catch (ControllerException e) {
                                        e.printStackTrace();
                                }
                        }
                }
	}
}

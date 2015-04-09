package simulator.agents;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.PlatformController;
import languagesAndMessages.Message;
import simulator.objects.Obstacle;
import utils.Util;
import view.UIController;

public class Ambient extends Agent{

	private static final long serialVersionUID = 3849717343310509053L;
	
//Declaration of object variables
	private static Object[] soundSourceParameters;
//End of object variables declaration
		
//Declaration of agent variables
	private final String SOUNDSOURCE = "SoundSource";
	private static List <Obstacle> obstacles = new ArrayList<>();
	private static List <AID> soundSources = new ArrayList<>();
	private PlatformController container = null;
//End of agent variables declaration
	
	@Override
	protected void setup() {
		defineAmbient();
		addBehaviour(new GetEventBehaviour(this));
	}
	
	public void defineAmbient() {
		container = getContainerController();
	}

	private void defineSoundSource(){
		Object[] argsSoundSource = {getSoundSourceParameters()[0], getSoundSourceParameters()[1], getSoundSourceParameters()[2],getSoundSourceParameters()[3], getAID(), getObstacles()};		
		soundSources.add(createAgent(argsSoundSource, container, this.SOUNDSOURCE));
	}
	
	private AID createAgent(Object[] args, PlatformController container, String type) {
		String id = getNextId(type);		
		Util.initAgent(container, args, "simulator.agents."+type, id);
		System.out.println(type + " criado(a) em: " + args[0]);
		return new AID(id, AID.ISLOCALNAME);
	}

	public String getNextId(String type) {		
		String id = new String();
		switch(type){
			case SOUNDSOURCE:
				id = SoundSource.nextId();
				break;
		}
		return id;
	}

	public static Object[] getSoundSourceParameters() {
		return soundSourceParameters;
	}

	public static void setSoundSourceParameters(Object[] soundSourceParameters) {
		Ambient.soundSourceParameters = soundSourceParameters;
	}

	public static List <Obstacle> getObstacles() {
		return obstacles;
	}

	public static void setObstacles(List <Obstacle> obstacles) {
		Ambient.obstacles = obstacles;
	}

	/*--------------------------  COMPORTAMENTS ------------------------ */
	private class GetEventBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = 6944311014873873811L;
		
		public GetEventBehaviour(Agent agent){
			super(agent);
		}
		@Override
		public void action() {
			
			if(UIController.getInstance().getEvents().peek() != null){
				switch(UIController.getInstance().getEvents().poll()){
				case Message.STOP_RESUMED:
					stopSimulation(Message.STOP_RESUMED);
					break;
                                case Message.STOP_PAUSED:
                                        stopSimulation(Message.STOP_PAUSED);
                                        break;
				case Message.PAUSE:
					pauseSimulation();
					break;
				case Message.RESUME:
					resumeSimulation();
					break;
				case Message.RUN:
					runSimulation();
					break;
				case Message.CREATE_SOUND_SOURCE:
					defineSoundSource();
					break;
				default:
					break;
				}
			}
		}
		private void runSimulation(){
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RUN, soundSources));
		}
		
		private void stopSimulation(String status) {
			send(Message.prepareMessage(ACLMessage.INFORM, null, status, soundSources));
		}
		
		private void pauseSimulation(){
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.PAUSE, soundSources));
		}
		
		private void resumeSimulation() {
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RESUME, soundSources));
		}
	}
}
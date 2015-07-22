package simulator.agents;

import java.util.HashMap;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import languagesAndMessages.Message;
import simulator.objects.Location;
import simulator.objects.SoundSourceObject;
import utils.Util;
import view.HomeFrame;
import view.UIController;

public class Ambient extends Agent{

	private static final long serialVersionUID = 3849717343310509053L;
	
//Declaration of object variables
	private static Object[] soundSourceParameters;
//End of object variables declaration
		
//Declaration of agent variables
	private final String SOUNDSOURCE = "SoundSource";
	private static HashMap <String, AID> soundSources = new HashMap<>();
//End of agent variables declaration
	
	private static ContainerController cc = null;
	private PlatformController container = null;
	
	private int countSoundSourcesFinished = 0;
	
	@Override
	protected void setup() {
		defineAmbient();
		addBehaviour(new GetMessageBehaviour(this));
		addBehaviour(new GetEventBehaviour(this));
	}
	
	public void defineAmbient() {
		container = getContainerController();
		cc = (ContainerController) container;
	}

	private void defineSoundSource(){
		Location location = (Location) getSoundSourceParameters()[0];
		int power = (int) getSoundSourceParameters()[1];
		int opening = (int) getSoundSourceParameters()[2];
		int direction = (int) getSoundSourceParameters()[3];
		String id = (String)getSoundSourceParameters()[4];
		
		SoundSourceObject.createSoundSource(id, this.getAID(), location, opening, power, direction);
		Object[] argsSoundSource = {SoundSourceObject.getSoundSources().get(id)};
		
		getSoundSources().put(id, createAgent(argsSoundSource, container, this.SOUNDSOURCE));
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

	public static void killSoundSource(String id){
		AgentController ac;
        try {
        	ac = cc.getAgent(soundSources.get(id).getLocalName());
            ac.kill();
        } catch (ControllerException e) {
            e.printStackTrace();
        }
	}

	public static HashMap <String, AID> getSoundSources() {
		return soundSources;
	}

	public static void setSoundSources(HashMap <String, AID> soundSources) {
		Ambient.soundSources = soundSources;
	}



	/*--------------------------  COMPORTAMENTS ------------------------ */
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

				if (message.getContent().equals(Message.FINISH_SIMULATION)){
					countSoundSourcesFinished++;
					if(countSoundSourcesFinished==soundSources.size()){
						finishSimulation();
						System.out.println("Simulation finished.");
						countSoundSourcesFinished=0;
					}
				}
				else {
					send(Message.getAnswerOfANotUnderstoodMessage(sender));					
				}
			}
		}
		private void finishSimulation() {
			HomeFrame.getHomeFrame().stopSimulation();
		}
	}
	
	
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
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RUN, getSoundSources()));
		}
		
		private void stopSimulation(String status) {
			send(Message.prepareMessage(ACLMessage.INFORM, null, status, getSoundSources()));
		}
		
		private void pauseSimulation(){
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.PAUSE, getSoundSources()));
		}
		
		private void resumeSimulation() {
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RESUME, getSoundSources()));
		}
	}
}
package simulator.agents;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.PlatformController;
import languagesAndMessages.Message;
import simulator.objects.Line;
import simulator.objects.Location;
import simulator.objects.Obstacle;
import utils.Util;
import view.UIController;

public class Ambient extends Agent{

	private static final long serialVersionUID = 3849717343310509053L;
	
//Declaration of object variables
	private static int width;
	private static int lenght;
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
	
	private void defineObstacles(){
		Obstacle wall1 = new Obstacle(Line.getLine(new Location(0,0), new Location(0,width)), 10);
		Obstacle wall2 = new Obstacle(Line.getLine(new Location(0,width), new Location(lenght,width)),	20);
		Obstacle wall3 = new Obstacle(Line.getLine(new Location(lenght,width), new Location(lenght,0)), 30);
		Obstacle wall4 = new Obstacle(Line.getLine(new Location(lenght,0), new Location(0,0)), 40);
		//Obstacle wall5 = new Obstacle(Line.getLine(new Location(50,30), new Location(50,70)), 50);
		
		getObstacles().add(wall1);
		getObstacles().add(wall2);
		getObstacles().add(wall3);
		getObstacles().add(wall4);
		//obstacles.add(wall5);
	}

	private void defineSoundSource(){
		Object[] argsSoundSource = {getSoundSourceParameters()[0], getSoundSourceParameters()[1], getSoundSourceParameters()[2],getSoundSourceParameters()[3], getSoundSourceParameters()[4], getAID(), getObstacles()};		
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

	public int getWidth() {
		return width;
	}

	public static void setWidth(int width) {
		Ambient.width = width;
	}

	public static int getLenght() {
		return lenght;
	}

	public static void setLenght(int lenght) {
		Ambient.lenght = lenght;
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
				case Message.STOP:
					stopSimulation();
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
				case Message.CREATE_SOUND_SOUCE:
					defineSoundSource();
					break;
				case Message.DEFINE_OBSTACLES:
					defineObstacles();
					break;
				default:
					break;
				}
			}
		}
		private void runSimulation(){
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RUN, soundSources));
		}
		
		private void stopSimulation() {
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.STOP, soundSources));
		}
		
		private void pauseSimulation(){
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.PAUSE, soundSources));
		}
		
		private void resumeSimulation() {
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RESUME, soundSources));
		}
	}
}
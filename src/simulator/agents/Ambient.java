package simulator.agents;

import java.util.ArrayList;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.wrapper.PlatformController;
import simulator.objects.Line;
import simulator.objects.Location;
import simulator.objects.Obstacle;
import utils.Util;

public class Ambient extends Agent{

	private static final long serialVersionUID = 1L;
	
//Declaration of object variables
	private static int width;
	private static int lenght;
	private static Object[] soundSourceParameters;
//End of object variables declaration
		
//Declaration of agent variables
	private final String SOUNDSOURCE = "SoundSource";
	private List <Obstacle> obstacles = new ArrayList<>();
	private List <AID> soundSources = new ArrayList<>();
//End of agent variables declaration
	
	@Override
	protected void setup() {
		defineAmbient();
	}
	
	public void defineAmbient() {
		PlatformController container = getContainerController();
		
		Obstacle wall1 = new Obstacle(Line.getLine(new Location(0,0), new Location(0,width)), 10);
		Obstacle wall2 = new Obstacle(Line.getLine(new Location(0,width), new Location(lenght,width)),	20);
		Obstacle wall3 = new Obstacle(Line.getLine(new Location(lenght,width), new Location(lenght,0)), 30);
		Obstacle wall4 = new Obstacle(Line.getLine(new Location(lenght,0), new Location(0,0)), 40);
		//Obstacle wall5 = new Obstacle(Line.getLine(new Location(50,30), new Location(50,70)), 50);
		
		obstacles.add(wall1);
		obstacles.add(wall2);
		obstacles.add(wall3);
		obstacles.add(wall4);
		//obstacles.add(wall5);

		/* sound source */
		Object[] argsSoundSource = {getSoundSourceParameters()[0], getSoundSourceParameters()[1], getSoundSourceParameters()[2],getSoundSourceParameters()[3], getSoundSourceParameters()[4], this.getAID(), obstacles};		
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
}
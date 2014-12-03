package simulator;

import java.util.ArrayList;
import java.util.List;
import oo.Line;
import oo.Localization;
import oo.Obstacle;
import jade.core.AID;
import jade.core.Agent;
import jade.wrapper.PlatformController;
import utils.Util;

public class Ambient extends Agent{

	private static final long serialVersionUID = 1L;
	
	private final String SOUNDSOURCE = "SoundSource";
	private List <Obstacle> obstacles = new ArrayList<>();
	private List <AID> soundSources = new ArrayList<>();

	@Override
	protected void setup() {
		defineAmbient();
	}
	
	public void defineAmbient() {
		PlatformController container = getContainerController();
		
		Obstacle wall1 = new Obstacle(Line.getLine(new Localization(0,0), new Localization(0,100)), 10);
		Obstacle wall2 = new Obstacle(Line.getLine(new Localization(0,100), new Localization(100,100)),	20);
		Obstacle wall3 = new Obstacle(Line.getLine(new Localization(100,100), new Localization(100,0)), 30);
		Obstacle wall4 = new Obstacle(Line.getLine(new Localization(100,0), new Localization(0,0)), 40);
		Obstacle wall5 = new Obstacle(Line.getLine(new Localization(50,30), new Localization(50,70)), 50);
		
		obstacles.add(wall1);
		obstacles.add(wall2);
		obstacles.add(wall3);
		obstacles.add(wall4);
		obstacles.add(wall5);

		/* sound source */
		Object[] argsSoundSource = {new Localization(0,50), this.getAID(), 15, obstacles};		
		soundSources.add(createObject(argsSoundSource, container, this.SOUNDSOURCE));
	}

	private AID createObject(Object[] args, PlatformController container, String type) {
		String id = getNextId(type);		
		Util.initAgent(container, args, "simulator."+type, id);
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
}
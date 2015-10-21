package simulator.objects;

import java.util.HashMap;

import jade.core.AID;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import simulator.agents.GUIAgentController;
import simulator.agents.SoundSource;
import utils.Util;

public class AmbientObject {

	private static AmbientObject ambient = null;
	
	public final String SOUNDSOURCE = "SoundSource";
	public final String GUI = "GUIAgentController";
	
	private AID ambientAID;
	private Object[] soundSourceParameters;			
	private HashMap <String, AID> soundSources = new HashMap<>();	
	private ContainerController cc = null;
	private PlatformController container = null;
	
	private AmbientObject(){

	}
	
	public static AmbientObject getInstance(){
		if(ambient == null){
			ambient = new AmbientObject();
		}
		return ambient;
	}
	
	public void defineSoundSource(){
		Location location = (Location) getSoundSourceParameters()[0];
        double power = (double) getSoundSourceParameters()[1];
		int opening = (int) getSoundSourceParameters()[2];
		int direction = (int) getSoundSourceParameters()[3];
		String id = (String)getSoundSourceParameters()[4];
		
		SoundSourceObject.createSoundSource(id, ambientAID, location, opening, power, direction);
		Object[] argsSoundSource = {SoundSourceObject.getSoundSources().get(id)};
		
		getSoundSources().put(id, createAgent(argsSoundSource, getContainer(), this.SOUNDSOURCE));
	}
	
	public AID createAgent(Object[] args, PlatformController container, String type) {
		String id = getNextId(type);		
		Util.initAgent(container, args, "simulator.agents."+type, id);
		if(type.equals(this.SOUNDSOURCE)){
			System.out.println(type + " criado(a) em: " + args[0]);
		}
		return new AID(id, AID.ISLOCALNAME);
	}
	
	public String getNextId(String type) {		
		String id = new String();
		switch(type){
			case SOUNDSOURCE:
				id = SoundSource.nextId();
				break;
			case GUI:
				id = GUIAgentController.nextId();
				break;
		}
		return id;
	}

	public void killSoundSource(String id){
		AgentController ac;
        try {
        	ac = getCc().getAgent(soundSources.get(id).getLocalName());
            ac.kill();
        } catch (ControllerException e) {
            e.printStackTrace();
        }
	}

	public HashMap <String, AID> getSoundSources() {
		return soundSources;
	}

	public void setSoundSources(HashMap <String, AID> soundSources) {
		this.soundSources = soundSources;
	}

	public AID getAmbientAID() {
		return ambientAID;
	}

	public void setAmbientAID(AID ambientAID) {
		this.ambientAID = ambientAID;
	}

	public PlatformController getContainer() {
		return container;
	}

	public void setContainer(PlatformController container) {
		this.container = container;
	}

	public ContainerController getCc() {
		return cc;
	}

	public void setCc(ContainerController cc) {
		this.cc = cc;
	}

	public Object[] getSoundSourceParameters() {
		return soundSourceParameters;
	}

	public void setSoundSourceParameters(Object[] soundSourceParameters) {
		this.soundSourceParameters = soundSourceParameters;
	}
}

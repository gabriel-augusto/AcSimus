package simulator.objects;

import java.util.HashMap;

import jade.core.AID;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;
import languagesAndMessages.Message;
import simulator.agents.Sound;
import utils.Util;

public class SoundSourceObject {
	public static final double SOUND_SEPARATION = 5;
	
	private AID ambientAID;
	private AID selfAID;
	private Location location;
	private int opening;
	private double power;
	private int direction;
	private PlatformController container;
	private ContainerController cc;
	
	private static HashMap <String, SoundSourceObject> soundSources = new HashMap<>();
	private HashMap <String, AID> sounds = new HashMap<>();
		
	public static void createSoundSource(String id, AID ambient, Location location, int opening, double power, int direction){
		getSoundSources().put(id, new SoundSourceObject(ambient, location, opening, power, direction));
	}
	
	private SoundSourceObject(AID ambient, Location location, int opening, double power, int direction){
		this.setAmbient(ambient);
		this.setLocation(location);
		this.setOpening(opening);
		this.setPower(power);
		this.setDirection(direction);
	}
	
	public void emitSoundPulse() {
		double angle;
		createSound(location, direction, power, opening);
		for(double i = SOUND_SEPARATION; i<=opening/2; i+=SOUND_SEPARATION) {
			angle = Util.normalizeAngle(direction+i);			
			createSound(this.getLocation(), angle, power, opening);
			angle = Util.normalizeAngle(direction-i);
			createSound(this.getLocation(), angle, power, opening);
		}
	}
	
	public AID createSound (Location location, double direction, double potency, int opening){
		final String identifier = Sound.nextId();
		SoundObject.createSound(new Location(location), direction, potency, opening, this.getAmbient(), this.getSelfAID(), identifier);
		Object[] args = {SoundObject.getSounds().get(identifier)};
		Util.initAgent(getContainer(), args, "simulator.agents.Sound", identifier);
		
		System.out.println(identifier + " created at: " + location);
		AID sound = new AID(identifier, AID.ISLOCALNAME);
		getSounds().put(identifier, sound);
		return sound;
	}
	
	public ACLMessage suspendAllSounds() {
		return Message.prepareMessage(ACLMessage.INFORM, null, Message.PAUSE, this.getSounds());		
	}

	public boolean resumeAllSounds() {
		AgentController ac;
		boolean resumed = false;
		for(AID sound : this.getSounds().values()){
			try {
				ac = cc.getAgent(sound.getLocalName());
				ac.activate();
				resumed = true;
			} catch (ControllerException e) {
			}
		}
		return resumed;
	}

	public ACLMessage stopSimulation(String status) {
		ACLMessage message = null;
		if(status.equals(Message.STOP_RESUMED)) {
			message = Message.prepareMessage(ACLMessage.INFORM, null, Message.STOP_RESUMED, this.getSounds());
		} else {
            AgentController ac;
            for(AID sound : this.getSounds().values()){
            	try {
            		ac = cc.getAgent(sound.getLocalName());
                    ac.kill();
                } catch (ControllerException e) {
                }
            }
            message = Message.prepareMessage(ACLMessage.INFORM, null, Message.ALREADY_STOPED, this.getSounds());
        }
		return message;
	}
	
	public static HashMap <String, SoundSourceObject> getSoundSources() {
		return soundSources;
	}

	public AID getAmbient() {
		return ambientAID;
	}

	public void setAmbient(AID ambient) {
		this.ambientAID = ambient;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getOpening() {
		return opening;
	}

	public void setOpening(int opening) {
		this.opening = opening;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public AID getSelfAID() {
		return selfAID;
	}

	public void setSelfAID(AID selfAID) {
		this.selfAID = selfAID;
	}

	public PlatformController getContainer() {
		return container;
	}

	public void setContainer(PlatformController container) {
		this.container = container;
	}

	public HashMap <String, AID> getSounds() {
		return sounds;
	}

	public void setSounds(HashMap <String, AID> sounds) {
		this.sounds = sounds;
	}

	public ContainerController getCc() {
		return cc;
	}

	public void setCc(ContainerController cc) {
		this.cc = cc;
	}
}

package simulator.objects;

import java.util.HashMap;

import jade.core.AID;

public class SoundSourceObject {
	private AID ambient;
	private Location location;
	private int opening;
	private double power;
	private int direction;
	
	private static HashMap <String, SoundSourceObject> soundSources = new HashMap<>();
	
	public static HashMap <String, SoundSourceObject> getSoundSources() {
		return soundSources;
	}
	
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

	public AID getAmbient() {
		return ambient;
	}

	public void setAmbient(AID ambient) {
		this.ambient = ambient;
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
}

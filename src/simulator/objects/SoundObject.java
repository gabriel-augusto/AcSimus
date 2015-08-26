package simulator.objects;

import java.util.HashMap;

import utils.Util;
import jade.core.AID;

public class SoundObject {
	
	private static final int SIZE_OF_STEP = 1;
	private static final double ERROR = 1;
	
	private String identifier;
	private String state;
	private AID soundSource;
	
	private Line rote;
	private Location collisionPoint;
	private Obstacle collisionObstacle;
	
	private Location initialLocation;
	private Location actualLocation;
	private double direction;
	private double power;
	private double actualVirtualSoundSourcePower;
	private int opening;
	private double intensity;
	private int distanceOfPreviousColisionPoint = 0;
	private int distanceTraveled = 0;
	
	private static HashMap <String, SoundObject> sounds = new HashMap<>();
	
	public static HashMap <String, SoundObject> getSounds() {
		return sounds;
	}
	
	public static void createSound(Location location, double direction, double power, int opening, AID ambient, AID soundSource, String id){
		getSounds().put(id, new SoundObject(location, direction, power, opening, ambient, soundSource, id));
	}
	
	private SoundObject(Location location, double direction, double power, int opening, AID ambient, AID soundSource, String id){
		this.initialLocation = new Location(location);
		this.actualLocation = new Location(location);
		System.out.println("\nInitial Location: " + initialLocation + "\nActual Location: " + actualLocation);
		this.direction = direction;
		this.power = power;
		this.setActualVirtualSoundSourcePower(power);
		this.intensity = power;
		this.opening = opening;
		this.soundSource = soundSource;
		this.identifier = id;
		this.rote = Line.getLine(this.getInitialLocation(), direction);
	}
	
	public void findNextObstacle(){
		this.setCollisionPoint(null);
		this.setCollisionObstacle(null);
		Location intersectionPoint = null;
		for(Obstacle obstacle : Obstacle.getObstacles().values()){
			intersectionPoint = this.getRote().searchIntersectionPoint(obstacle.getLine());
			if(intersectionPoint != null && !this.getActualLocation().equals(intersectionPoint, ERROR)){
				if(this.getCollisionPoint() == null || this.getActualLocation().distance(intersectionPoint) < this.getActualLocation().distance(this.getCollisionPoint())){
					this.setCollisionObstacle(obstacle);
					this.setCollisionPoint(intersectionPoint);
					System.out.println("Obstacle found in "+this.getActualLocation().distance(this.getCollisionPoint())+" meters:: \nindex: " + obstacle.getAbsortionRate() + "\ncollision point: " + this.getCollisionPoint().toString());
				}
			}
		}
	}
	
	public void update(){
		this.setDistanceOfPreviousColisionPoint(this.getDistanceOfPreviousColisionPoint() + SIZE_OF_STEP);
		this.setDistanceTraveled(this.getDistanceTraveled() + SIZE_OF_STEP);
		this.setIntensity(calculateIntensityBySoundPropagation(this.getActualVirtualSoundSourcePower(), this.getOpening(), this.getDistanceTraveled()));
		updateLocation();
		
		if(isCollisionPoint()){
			updateParameters();
			System.out.println("\nCOLLIDED!!!!!");
			findNextObstacle();
		}
		this.setState(this.getActualState());
	}
	
	public void updateLocation() {
		this.getActualLocation().setX(Util.calculateX(this.getDirection(), this.getDistanceOfPreviousColisionPoint()) + this.getInitialLocation().getX());
		this.getActualLocation().setY(Util.calculateY(this.getDirection(), this.getDistanceOfPreviousColisionPoint()) + this.getInitialLocation().getY());
	}
	
	public double calculateIntensityBySoundPropagation(double power, double angle, double radius){
		return (power / (4 * Math.PI * radius * radius)) * (360 / angle);
	}
	
	public boolean isCollisionPoint() {
		return this.getActualLocation().equals(this.getCollisionPoint(), ERROR);
	}
	
	public void updateParameters(){
		this.setDistanceOfPreviousColisionPoint(0);
		this.setInitialLocation(this.getCollisionPoint());
		this.setDirection(calculateNewDirection());
		this.setIntensity(calculateIntencityAfterColisionPoint(this.getCollisionObstacle().getAbsortionRate()));
		this.setActualVirtualSoundSourcePower(this.calculateNewVirtualSoundSourcePower(this.getIntensity(), this.getDistanceTraveled(), this.getOpening()));
		this.setRote(Line.getLine(this.getInitialLocation(), this.getDirection()));
	}

	public double calculateNewVirtualSoundSourcePower(double intensity, double distance, double angle){
		return (intensity * angle * 4 * Math.PI * distance * distance)/360;
	}
	
	public double calculateIntencityAfterColisionPoint(double absorptionRate) {
		return this.getIntensity() - (this.getIntensity()*(absorptionRate/100));
	}

	public double calculateNewDirection() {		
		double obstacleSlopeAngle = Math.toDegrees(Math.atan(this.getCollisionObstacle().getLine().getSlope()));		  
		double newDirection = 2 * obstacleSlopeAngle - this.getDirection();			
		return Util.normalizeAngle(newDirection);
	}
	
	public String getActualState(){
		return "\ndecibel: " + this.getDecibel() 
				+ "\ndirection: " + this.getDirection() + " degrees" + "\ndistance of origin: " 
				+ this.getDistanceOfPreviousColisionPoint() + "\ninitial location: " 
				+ this.getInitialLocation() + "\nlocation: " + this.getActualLocation();
	}
	
	/* ------------------------ Gets and Setters ------------------------- */
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public AID getSoundSource() {
		return soundSource;
	}
	public void setSoundSource(AID soundSource) {
		this.soundSource = soundSource;
	}

	public Line getRote() {
		return rote;
	}

	public void setRote(Line rote) {
		this.rote = rote;
	}

	public Location getCollisionPoint() {
		return collisionPoint;
	}

	public void setCollisionPoint(Location collisionPoint) {
		this.collisionPoint = collisionPoint;
	}

	public Obstacle getCollisionObstacle() {
		return collisionObstacle;
	}

	public void setCollisionObstacle(Obstacle collisionObstacle) {
		this.collisionObstacle = collisionObstacle;
	}

	public Location getInitialLocation() {
		return initialLocation;
	}

	public void setInitialLocation(Location initialLocation) {
		this.initialLocation = new Location(initialLocation);
	}

	public Location getActualLocation() {
		return actualLocation;
	}

	public void setActualLocation(Location actualLocation) {
		this.actualLocation = new Location(actualLocation);
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public double getPower() {
		return power;
	}

	public void setPower(double power) {
		this.power = power;
	}

	public int getOpening() {
		return opening;
	}

	public void setOpening(int opening) {
		this.opening = opening;
	}

	public double getIntensity() {
		return intensity;
	}

	public void setIntensity(double intensity) {
		this.intensity = intensity;
	}

	public int getDistanceOfPreviousColisionPoint() {
		return distanceOfPreviousColisionPoint;
	}

	public void setDistanceOfPreviousColisionPoint(
			int distanceOfPreviousColisionPoint) {
		this.distanceOfPreviousColisionPoint = distanceOfPreviousColisionPoint;
	}

	public int getDistanceTraveled() {
		return distanceTraveled;
	}

	public void setDistanceTraveled(int distanceTraveled) {
		this.distanceTraveled = distanceTraveled;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public double getActualVirtualSoundSourcePower() {
		return actualVirtualSoundSourcePower;
	}

	public void setActualVirtualSoundSourcePower(double actualVirtualSoundSourcePower) {
		this.actualVirtualSoundSourcePower = actualVirtualSoundSourcePower;
	}
	
	public double getDecibel(){
		return 10 * Math.log10(this.getIntensity() / Math.pow(10.0, -12.0));
	}
	
	public double getReverberationTime(){
		return this.distanceTraveled * (1/0.34029);
	}
}

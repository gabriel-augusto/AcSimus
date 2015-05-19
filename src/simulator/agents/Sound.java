package simulator.agents;

import settings.ProjectSettings;
import simulator.objects.Line;
import simulator.objects.Location;
import simulator.objects.Obstacle;
import utils.Util;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import languagesAndMessages.Language;
import languagesAndMessages.Message;


public class Sound extends Agent{

	private static final long serialVersionUID = 1L;
	
	//private static final int UPDATE_PERIOD = 3; //Time of atualization of the sound in ms.
	private static final int SIZE_OF_STEP = 1;
	private static final double ERROR = 1; //SIZE_OF_STEP/2;
	
	private String identifier;
	private AID soundSource;
	
	private Line rote;
	private Location collisionPoint;
	private Obstacle collisionObstacle;
	
	Location initialLocation;
	Location actualLocation;
	double direction;
	double power;
	int opening;
	double intensity;
	int distanceOfPreviousColisionPoint = 0;
	int distanceTraveled = 0;
	double index;
	
	@Override
	protected void setup() {
		getParameters();
		soundRegister();
		findNextObstacle();
		addBehavior();
	}

	private void addBehavior() {
		addBehaviour(new UpdateSoundBehavior(this, ProjectSettings.getProjectSettings().getSimulationSpeed()));
		addBehaviour(new GetMessageBehaviour(this));
	}

	private void soundRegister(){
		try{
			DFAgentDescription sound = new DFAgentDescription();
			sound.setName(getAID());
			DFService.register(this, sound);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	private void getParameters() {
		Object[] args = getArguments();
		actualLocation = new Location((Location) args[0]);
		initialLocation = new Location((Location) args[0]);
		System.out.println("\nInitial Location: " + initialLocation + "\nActual Location: " + actualLocation);
		direction = (double) args[1];
		power = (double) args[2];
		intensity = power;
		opening = (int) args[3];
		identifier = (String) args[6];
		soundSource = (AID) args[7];
		rote = Line.getLine(initialLocation, direction);		
	}
	
	private void findNextObstacle(){
		collisionPoint = null;
		collisionObstacle = null;
		Location intersectionPoint = null;
		for(Obstacle obstacle : Obstacle.getObstacles().values()){
			intersectionPoint = rote.searchIntersectionPoint(obstacle.getLine());
			if(intersectionPoint != null && !actualLocation.equals(intersectionPoint, ERROR)){
				if(collisionPoint == null || actualLocation.distance(intersectionPoint) < actualLocation.distance(collisionPoint)){
					collisionObstacle = obstacle;
					collisionPoint = intersectionPoint;
					actualLocation.distance(collisionPoint);
					System.out.println("Obstacle found: \nindex: " + obstacle.getAbsortionRate() + "\ncollision point: " + collisionPoint.toString());
				}
			}
		}
	}
	
	private void update(){
		distanceOfPreviousColisionPoint = distanceOfPreviousColisionPoint + SIZE_OF_STEP;
		distanceTraveled = distanceTraveled + SIZE_OF_STEP;
		intensity = calculateIntensityBySoundPropagation(power, opening, distanceTraveled);
		updateLocation();
		
		if(isCollisionPoint()){
			updateParameters();
			System.out.println("\nCOLLIDED!!!!!");
			System.out.println(this.writeActualState());
			if(intensity < 5){
				killSound();
				return;
			}
			findNextObstacle();
		}else
			System.out.println("\n"+this.writeActualState());
	}

	private void updateLocation() {
		actualLocation.setX(calculateX(direction, distanceOfPreviousColisionPoint) + initialLocation.getX());
		actualLocation.setY(calculateY(direction, distanceOfPreviousColisionPoint) + initialLocation.getY());
	}
	
	private double calculateIntensityBySoundPropagation(double actualIntensity, double angle, double radius){
		double archLenght = angle * Math.PI * radius / 180;
		return actualIntensity/archLenght;
	}

	private boolean isCollisionPoint() {
		return actualLocation.equals(collisionPoint, ERROR);
	}
	
	private void killSound() {
		send(Message.prepareMessage(ACLMessage.INFORM, Language.FINISH, identifier, soundSource));
		this.doDelete();
		System.out.println("END OF SOUND!!!");
	}
	
	private void updateParameters(){
		distanceOfPreviousColisionPoint = 0;
		initialLocation = collisionPoint;
		calculateNewDirection();
		calculateIntencityAfterColisionPoint(collisionObstacle.getAbsortionRate());
		rote = Line.getLine(initialLocation, direction);
	}

	private void calculateIntencityAfterColisionPoint(double absorptionRate) {
		intensity = intensity - (intensity*(absorptionRate/100));
	}

	private void calculateNewDirection() {		
		double obstacleSlopeAngle = Math.toDegrees(Math.atan(collisionObstacle.getLine().getSlope()));		  
		double newDirection = 2 * obstacleSlopeAngle - direction;			
		direction = Util.normalizeAngle(newDirection);
	}
	
	public double calculateX(double angle, int hypotenuse){
		return Math.cos(Math.toRadians(angle)) * hypotenuse;
	}
	
	public double calculateY(double angle, int hypotenuse){
		return Math.sin(Math.toRadians(angle)) * hypotenuse;
	}
	
	private String writeActualState(){
		return this.getAID().getName() + ":" + "\nintensity: " + intensity 
				+ "\ndirection: " + direction + " degrees" + "\ndistance of origin: " 
				+ distanceOfPreviousColisionPoint + "\ninitial location: " 
				+ initialLocation + "\nlocation: " + actualLocation;
	}
	
	private static int id = 0;

	public static String nextId() {
		return "Sound_" + (++id);
	}
	
	/*--------------------------  COMPORTAMENTS ------------------------ */
	
	private class UpdateSoundBehavior extends TickerBehaviour {

		private static final long serialVersionUID = 5631501784835798992L;

		public UpdateSoundBehavior(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			update();
		}
		
	}
	
	private class GetMessageBehaviour extends CyclicBehaviour {
		
		private static final long serialVersionUID = 1L;

		public GetMessageBehaviour(Agent agent) {
			super(agent);
		}
		
		@Override
		public void action() {
			ACLMessage message = receive();

			if (message != null && message.getPerformative() == ACLMessage.INFORM) {
				if (message.getContent().equals(Message.STOP_RESUMED)){
					doDelete();
					System.out.println("Sound Source: Sounds and SoundSource destroyed successfuly!");
				}
				else if(message.getContent().equals(Message.PAUSE)){
					doSuspend();
				}
			}
		}
	}
}

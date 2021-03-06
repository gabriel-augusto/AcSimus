package simulator.objects;

import java.util.HashMap;


public class Obstacle {

	private Line line;
	private double absorptionRate;
	private static HashMap <String, Obstacle> obstacles = new HashMap<>();
	
	private Obstacle(Line line, double absortionRate){
		this.setLine(line);
		this.absorptionRate = absortionRate;
	}
	
	public static void createObstacle(String id, Line line, double absorptionRate){
		getObstacles().put(id, new Obstacle(line, absorptionRate));
	}
	
	public double getAbsortionRate() {
		return absorptionRate;
	}
	
	public void setAbsortionRate(double absortionRate) {
		this.absorptionRate = absortionRate;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
	
	public double getSize(){
		return this.getLine().getInitialPoint().distance(this.getLine().getFinalPoint());
	}

	public static HashMap <String, Obstacle> getObstacles() {
		return obstacles;
	}
}

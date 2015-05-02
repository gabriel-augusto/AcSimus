package simulator.objects;


public class Obstacle {

	private Line line;
	private double absortionRate;
	
	
	public Obstacle(Line line, double absortionRate){
		this.setLine(line);
		this.absortionRate = absortionRate;
	}
	
	public double getAbsortionRate() {
		return absortionRate;
	}
	
	public void setAbsortionRate(double absortionRate) {
		this.absortionRate = absortionRate;
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		this.line = line;
	}
}

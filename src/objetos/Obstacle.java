package objetos;


public class Obstacle {

	private Linha line;
	private double absortionRate;
	
	
	public Obstacle(Linha line, double absortionRate){
		this.setLine(line);
		this.absortionRate = absortionRate;
	}
	
	public double getAbsortionRate() {
		return absortionRate;
	}
	
	public void setAbsortionRate(double absortionRate) {
		this.absortionRate = absortionRate;
	}

	public Linha getLine() {
		return line;
	}

	public void setLine(Linha line) {
		this.line = line;
	}	
}

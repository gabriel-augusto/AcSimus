package settings;

import simulator.objects.Obstacle;

public class ProjectSettings {

	private static ProjectSettings settings = null;
	public final String PROJECT_NAME = "Acoustic Simulator";
    private int simulationSpeed = 100; //Simulation speed in milliseconds.
    private double length;
    private double width;
    private double height;
    private double projectAbsorption;

    private ProjectSettings(){
    	
    }
    
    public static ProjectSettings getProjectSettings(){
    	if(settings == null)
    		settings = new ProjectSettings();
    	return settings;
    }
    
	public int getSimulationSpeed() {
		return simulationSpeed;
	}

	public void setSimulationSpeed(int simulationSpeed) {
		this.simulationSpeed = simulationSpeed;
	}

	public double getLenght() {
		return length;
	}

	public void setLenght(double lenght) {
		this.length = lenght;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public double getProjectAbsorption() {
		return projectAbsorption;
	}

	public void setProjectAbsorption(double projectAbsorption) {
		this.projectAbsorption = projectAbsorption;
	}

	public double getVolume(){
		return height*length*width;
	}
	
	public double getArea(){
		return 2*((height*length)+(height*width)+(length*width));
	}
	
	public double getAbsorptionSurface(){
		double absorptionSurface = 0;
        absorptionSurface = Obstacle.getObstacles().values().stream().map((obstacle) -> obstacle.getSize()*this.getHeight()*(obstacle.getAbsortionRate()/100)).reduce(absorptionSurface, (accumulator, _item) -> accumulator + _item);
		absorptionSurface += 2 * length * width * (projectAbsorption/100);
        return absorptionSurface;
	}
	
	public double getRT(){
		return 0.161 * 1000 * this.getVolume()/this.getAbsorptionSurface();
	}
}

package settings;

/**
 *
 * @author Gabriel
 */
public class ProjectSettings {

	private static ProjectSettings settings = null;
	private final String projectName = "Acoustic Simulator";
    private int simulationSpeed = 200; //Simulation speed in milliseconds.

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

	public String getProjectName() {
		return projectName;
	}
}

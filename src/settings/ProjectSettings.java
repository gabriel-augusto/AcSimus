package settings;

/**
 *
 * @author Gabriel
 */
public class ProjectSettings {

	private static ProjectSettings settings = null;
	public static final String PROJECT_NAME = "Acoustic Simulator";
    private static int simulationSpeed = 200; //Simulation speed in milliseconds.

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
		ProjectSettings.simulationSpeed = simulationSpeed;
	}
}

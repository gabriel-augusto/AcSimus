package settings;

/**
 *
 * @author Gabriel
 */
public class ProjectSettings {

	private static ProjectSettings settings = null;
	public final String PROJECT_NAME = "Acoustic Simulator";
    private int simulationSpeed = 100; //Simulation speed in milliseconds.

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
}

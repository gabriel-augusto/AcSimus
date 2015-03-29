package settings;

/**
 *
 * @author Gabriel
 */
public class ProjectSettings {
    private static final String projectName = "Acoustic Simulator";
    
    private static int simulationSpeed = 200; //Simulation speed in milliseconds.

    public static String getProjectName() {
        return projectName;
    }

	public static int getSimulationSpeed() {
		return simulationSpeed;
	}

	public static void setSimulationSpeed(int simulationSpeed) {
		ProjectSettings.simulationSpeed = simulationSpeed;
	}
}

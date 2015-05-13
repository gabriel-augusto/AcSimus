package settings;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectSettingsTest {

	@Test
	public void testSimulationSpeed() {
		ProjectSettings.getProjectSettings().setSimulationSpeed(500);
		assertEquals(ProjectSettings.getProjectSettings().getSimulationSpeed(), 500);
	}

}

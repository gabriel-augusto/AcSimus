package settings;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProjectSettingsTest {

	@Test
	public void testSimulationSpeed() {
		ProjectSettings.getProjectSettings().setSimulationSpeed(500);
		assertEquals(ProjectSettings.getProjectSettings().getSimulationSpeed(), 500);
	}
	
	@Test
	public void testGetters() {
		ProjectSettings settings = ProjectSettings.getProjectSettings();
		settings.setHeight(10);
		settings.setLenght(10);
		settings.setWidth(10);
		settings.setProjectAbsorption(10);
		
		assertEquals(settings.getArea(), 600, .0001);
		//assertEquals(settings.getAbsorptionSurface(), 60, .0001);
		assertEquals(settings.getVolume(), 1000, .0001);
		//assertEquals(settings.getRT(), 2683.33, 0.1);
		
	}

}

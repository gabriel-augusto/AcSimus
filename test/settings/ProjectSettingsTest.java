package settings;

import static org.junit.Assert.*;

import org.junit.Test;

import simulator.objects.Line;
import simulator.objects.Location;
import simulator.objects.Obstacle;

public class ProjectSettingsTest {

	@Test
	public void testSimulationSpeed() {
		ProjectSettings.getProjectSettings().setSimulationSpeed(500);
		assertEquals(ProjectSettings.getProjectSettings().getSimulationSpeed(), 500);
	}
	
	@Test
	public void testGetters() {
		Line line1 = Line.getLine(new Location(0,0), new Location(0,10));
		Obstacle.createObstacle("o1", line1, 10);
		
		Line line2 = Line.getLine(new Location(0,10), new Location(10,10));
		Obstacle.createObstacle("o2", line2, 10);
		
		Line line3 = Line.getLine(new Location(10,10), new Location(10,0));
		Obstacle.createObstacle("o3", line3, 10);
		
		Line line4 = Line.getLine(new Location(10,0), new Location(0,0));
		Obstacle.createObstacle("o4", line4, 10);
		
		ProjectSettings settings = ProjectSettings.getProjectSettings();
		settings.setHeight(10);
		settings.setLenght(10);
		settings.setWidth(10);
		settings.setProjectAbsorption(10);
		
		assertEquals(settings.getArea(), 600, .0001);
		assertEquals(settings.getAbsorptionSurface(), 60, .0001);
		assertEquals(settings.getVolume(), 1000, .0001);
		assertEquals(settings.getRT(), 2683.33, 0.1);
		
	}

}

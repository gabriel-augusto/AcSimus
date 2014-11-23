package utils;

import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;

public class Util{
	
	public static void initAgent(PlatformController container, Object[] args, String path, String id){
		final AgentController agentController;
		try {
			agentController = container.createNewAgent(id, path, args);
			agentController.start();			
		} catch (ControllerException e ) {
			e.printStackTrace();
		}
	}	
	
	public static boolean compareDouble(double double1, double double2, double tolerance){
		if(Math.abs(double1 - double2) < tolerance)
			return true;
		return false;
	}
	
	public static double normalizeAngle(double angle){
		while(angle < 0 || angle > 360){
			if(angle<0)
				angle = angle+360;
			if(angle>360)
				angle = angle-360;
		}
		return angle;
	}
}

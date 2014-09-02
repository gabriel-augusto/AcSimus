package utils;

import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.PlatformController;

public class Util{
	
	public static void inicializarAgente(PlatformController container, Object[] args, String caminho, String id){
		final AgentController agentController;
		try {
			agentController = container.createNewAgent(id, 
					caminho, args);
			agentController.start();			
		} catch (ControllerException e ) {
			e.printStackTrace();
		}
	}	
	
	public static boolean compararDouble(double double1, double double2, double tolerancia){
		if(Math.abs(double1 - double2) < tolerancia)
			return true;
		return false;
	}
	
	public static double padronizarAngulo(double angulo){
		while(angulo < 0 || angulo > 360){
			if(angulo<0)
				angulo = angulo+360;
			if(angulo>360)
				angulo = angulo-360;
		}
		return angulo;
	}
}

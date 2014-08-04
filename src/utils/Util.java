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
}

package main;

import jade.core.*;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class Main {

	
	private static final Runtime rt = Runtime.instance(); // Get a hold on JADE runtime
	private static final Profile profile = new ProfileImpl(null, 1200, null); // Create a default profile
	private static final jade.wrapper.AgentContainer mainContainer = rt.createMainContainer(profile);
	private static final ProfileImpl pContainer = new ProfileImpl(null, 1200, null); // now set the default Profile to start a container
	private static final jade.wrapper.AgentContainer simulatorContainer = rt.createAgentContainer(pContainer);
	private static AgentController rma;
	private static AgentController ambient;

	public static void main(String[] args) {		

		// Exit the JVM when there are no more containers around
		rt.setCloseVM(true);
		
		try {
			rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
			rma.start();
			
			ambient = simulatorContainer.createNewAgent("Ambient", "simulator.Ambient", new Object[0]);
			ambient.start();
		} catch (StaleProxyException e1) {
			e1.printStackTrace();
		}
	}

	public static jade.wrapper.AgentContainer getSimulatorContainer() {
		return simulatorContainer;
	}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.agents;

import settings.ProjectSettings;
import view.GraphicGenerator;
import view.UIController;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

/**
 *
 * @author Gabriel
 */
public class GUIAgentController extends Agent{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274242402523089774L;

	@Override
	protected void setup() {
		addBehaviour(new UpdateGUIBehavior(this, ProjectSettings.getProjectSettings().getSimulationSpeed()));
	}
	
	private class UpdateGUIBehavior extends TickerBehaviour {

		private static final long serialVersionUID = 5631501784835798992L;

		public UpdateGUIBehavior(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			if(UIController.getInstance().isRunning())
				GraphicGenerator.getInstance().updateSounds();
		}
		
	}
    
	private static int id = 0;
	
	public static String nextId(){
		return "GUI_Agent_controler" + (++id);
	}
}

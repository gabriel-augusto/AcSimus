/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.agents;

import simulator.objects.SimulationStatus;
import simulator.objects.SoundObject;
import simulator.objects.UIController;
import view.GraphicGenerator;
import view.HomeFrame;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

import java.text.DecimalFormat;

/**
 *
 * @author Gabriel
 */
public class GUIAgentController extends Agent{
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -5274242402523089774L;
	
	private final int FPS = 40;
	private final int PERIOD = 1000/FPS;
	private SimulationStatus simulation = SimulationStatus.getInstance();

	@Override
	protected void setup() {
		addBehaviour(new UpdateGUIBehavior(this, this.PERIOD));
	}
	
	private class UpdateGUIBehavior extends TickerBehaviour {

		private static final long serialVersionUID = 5631501784835798992L;

		public UpdateGUIBehavior(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			simulation.setDecibel(0);
			if(UIController.getInstance().isRunning())
				GraphicGenerator.getInstance().updateSounds();
			for(SoundObject sound : SoundObject.getSounds().values()){
				if(sound.getDecibel() > simulation.getDecibel())
					simulation.setDecibel(sound.getDecibel());
				if(sound.getReverberationTime() > simulation.getReverberationTime())
					simulation.setReverberationTime(sound.getReverberationTime());
			}
			HomeFrame.jLabelNivel.setText("Sound intensity level: " + new DecimalFormat("0").format(simulation.getDecibel()) + " dB");
			HomeFrame.jLabelReverberacao.setText("Reverberation time: " + new DecimalFormat("0").format(simulation.getReverberationTime()) + "ms");
			
		}
		
	}
    
	private static int id = 0;
	
	public static String nextId(){
		return "GUI_Agent_controler" + (++id);
	}
}

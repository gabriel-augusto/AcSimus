/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulator.agents;

import settings.ProjectSettings;
import simulator.objects.SoundObject;
import view.GraphicGenerator;
import view.HomeFrame;
import view.UIController;
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
	
	private static double decibel = 0;
	private static double reverberationTime = 0;

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
			setDecibel(0);
			if(UIController.getInstance().isRunning())
				GraphicGenerator.getInstance().updateSounds();
			for(SoundObject sound : SoundObject.getSounds().values()){
				if(sound.getDecibel() > getDecibel())
					setDecibel(sound.getDecibel());
				if(sound.getReverberationTime() > getReverberationTime())
					setReverberationTime(sound.getReverberationTime());
			}
			HomeFrame.jLabelNivel.setText("Sound intensity level: " + new DecimalFormat("0").format(getDecibel()) + " dB");
			HomeFrame.jLabelReverberacao.setText("Reverberation time: " + new DecimalFormat("0").format(getReverberationTime()) + "ms");
			
		}
		
	}
    
	private static int id = 0;
	
	public static String nextId(){
		return "GUI_Agent_controler" + (++id);
	}

	public static double getDecibel() {
		return decibel;
	}

	public static void setDecibel(double decibel) {
		GUIAgentController.decibel = decibel;
	}

	public static double getReverberationTime() {
		return reverberationTime;
	}

	public static void setReverberationTime(double reverberationTime) {
		GUIAgentController.reverberationTime = reverberationTime;
	}
}

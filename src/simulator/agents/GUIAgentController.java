package simulator.agents;

import simulator.objects.SimulationStatus;
import simulator.objects.SoundObject;
import simulator.objects.UIController;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import presentation.GraphicGenerator;
import presentation.HomeFrame;
import settings.ProjectSettings;

import java.text.DecimalFormat;

public class GUIAgentController extends Agent{

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
			double decibel = 0;
			if(UIController.getInstance().isRunning())
				GraphicGenerator.getInstance().updateSounds();
			for(SoundObject sound : SoundObject.getSounds().values()){
				if(sound.getDecibel() > decibel){
					decibel = sound.getDecibel();
				}
				if(sound.getReverberationTime() > simulation.getReverberationTime()){
					simulation.setReverberationTime(sound.getReverberationTime());
				}
			}
			if(decibel != 0){
				simulation.setDecibel(decibel);
			}
			HomeFrame.jLabelNivel.setText("Sound intensity level: " + new DecimalFormat("0").format(simulation.getDecibel()) + " dB");
			if(simulation.getDecibel() <= 0.5 && simulation.getDecibel() != 0 && UIController.getInstance().isRunning()){
				HomeFrame.jLabelReverberacao.setText("Reverberation time: " + new DecimalFormat("0").format(ProjectSettings.getProjectSettings().getRT()) + "ms");
			}else if(simulation.getDecibel() > 1){
				HomeFrame.jLabelReverberacao.setText("Reverberation time: --");
			}
		}
		
	}
    
	private static int id = 0;
	
	public static String nextId(){
		return "GUI_Agent_controler" + (++id);
	}
}

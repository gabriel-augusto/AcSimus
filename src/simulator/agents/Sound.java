package simulator.agents;

import settings.ProjectSettings;
import simulator.objects.SoundObject;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import languagesAndMessages.Language;
import languagesAndMessages.Message;


public class Sound extends Agent{

	private static final long serialVersionUID = 3789435534023352353L;
	private SoundObject sound;

	@Override
	protected void setup() {
		getParameters();
		soundRegister();
		sound.findNextObstacle();
		addBehavior();
	}

	private void addBehavior() {
		addBehaviour(new UpdateSoundBehavior(this, ProjectSettings.getProjectSettings().getSimulationSpeed()));
		addBehaviour(new GetMessageBehaviour(this));
	}

	private void soundRegister(){
		try{
			DFAgentDescription sound = new DFAgentDescription();
			sound.setName(getAID());
			DFService.register(this, sound);
		} catch (FIPAException e) {
			e.printStackTrace();
		}
	}

	private void getParameters() {
		Object[] args = getArguments();
		sound = (SoundObject) args[0];
	}

	private void killSound() {
		send(Message.prepareMessage(ACLMessage.INFORM, Language.FINISH, sound.getIdentifier(), sound.getSoundSource()));
		this.doDelete();
		System.out.println("END OF SOUND!!!");
	}

	private String getActualState(){
		return this.getAID().getName() + ":" + sound.getState();
	}
	
	private static int id = 0;

	public static String nextId() {
		return "Sound_" + (++id);
	}
	
	/*--------------------------  COMPORTAMENTS ------------------------ */
	
	private class UpdateSoundBehavior extends TickerBehaviour {

		private static final long serialVersionUID = 5631501784835798992L;

		public UpdateSoundBehavior(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			try{
				sound.update();
			}catch(NullPointerException e){
				killSound();
			}
			System.out.println("\n"+getActualState());
			if(sound.getIntensity() < 5){
				killSound();
			}
		}
		
	}
	
	private class GetMessageBehaviour extends CyclicBehaviour {
		
		private static final long serialVersionUID = 1L;

		public GetMessageBehaviour(Agent agent) {
			super(agent);
		}
		
		@Override
		public void action() {
			ACLMessage message = receive();

			if (message != null && message.getPerformative() == ACLMessage.INFORM) {
				if (message.getContent().equals(Message.STOP_RESUMED)){
					doDelete();
					System.out.println("Sound Source: Sounds and SoundSource destroyed successfuly!");
				}
				else if(message.getContent().equals(Message.PAUSE)){
					doSuspend();
				}
			}
		}
	}
}

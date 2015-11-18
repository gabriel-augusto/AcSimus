package simulator.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ContainerController;
import languagesAndMessages.Message;
import presentation.HomeFrame;
import simulator.objects.AmbientObject;
import simulator.objects.UIController;

public class Ambient extends Agent{

	private static final long serialVersionUID = 3849717343310509053L;

	private AmbientObject ambient = AmbientObject.getInstance();
	
	private int countSoundSourcesFinished = 0;
	
	@Override
	protected void setup() {
		defineAmbient();
		addBehaviour(new GetMessageBehaviour(this));
		addBehaviour(new GetEventBehaviour(this));
		ambient.createAgent(null, ambient.getContainer(), ambient.GUI);
	}
	
	public void defineAmbient() {
		ambient.setAmbientAID(this.getAID());
		ambient.setContainer(getContainerController());
		ambient.setCc((ContainerController) ambient.getContainer());
	}	

	/*--------------------------  COMPORTAMENTS ------------------------ */
	private class GetMessageBehaviour extends CyclicBehaviour {
		
		private static final long serialVersionUID = 1L;

		public GetMessageBehaviour(Agent agent) {
			super(agent);
		}
		
		@Override
		public void action() {
			ACLMessage message = receive();

			if (message != null && message.getPerformative() == ACLMessage.INFORM) {
				AID sender = message.getSender();

				if (message.getContent().equals(Message.FINISH_SIMULATION)){
					countSoundSourcesFinished++;
					if(countSoundSourcesFinished==ambient.getSoundSources().size()){
						finishSimulation();
						System.out.println("Simulation finished.");
						countSoundSourcesFinished=0;
					}
				}
				else {
					send(Message.getAnswerOfANotUnderstoodMessage(sender));					
				}
			}
		}
		private void finishSimulation() {
			HomeFrame.getHomeFrame().stopSimulation();
		}
	}
	
	
	private class GetEventBehaviour extends CyclicBehaviour {

		private static final long serialVersionUID = 6944311014873873811L;
		
		public GetEventBehaviour(Agent agent){
			super(agent);
		}
		@Override
		public void action() {
			
			if(UIController.getInstance().getEvents().peek() != null){
				switch(UIController.getInstance().getEvents().poll()){
				case Message.STOP_RESUMED:
					stopSimulation(Message.STOP_RESUMED);
					break;
                case Message.STOP_PAUSED:
                	stopSimulation(Message.STOP_PAUSED);
                    break;
				case Message.PAUSE:
					pauseSimulation();
					break;
				case Message.RESUME:
					resumeSimulation();
					break;
				case Message.RUN:
					runSimulation();
					break;
				case Message.CREATE_SOUND_SOURCE:
					ambient.defineSoundSource();
					break;
				default:
					break;
				}
			}
		}
		private void runSimulation(){
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RUN, ambient.getSoundSources()));
		}
		
		private void stopSimulation(String status) {
			send(Message.prepareMessage(ACLMessage.INFORM, null, status, ambient.getSoundSources()));
		}
		
		private void pauseSimulation(){
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.PAUSE, ambient.getSoundSources()));
		}
		
		private void resumeSimulation() {
			send(Message.prepareMessage(ACLMessage.INFORM, null, Message.RESUME, ambient.getSoundSources()));
		}
	}
}
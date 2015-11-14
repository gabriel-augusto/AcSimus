package simulator.agents;

import simulator.objects.SoundSourceObject;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.lang.acl.ACLMessage;
import languagesAndMessages.Language;
import languagesAndMessages.Message;

public class SoundSource extends Agent{

	private static final long serialVersionUID = 3414148236747846279L;

	private SoundSourceObject soundSource;

	@Override
	protected void setup() {
			getParameters();
			registerSoundSource();
			soundSource.setSelfAID(getAID());
			soundSource.setContainer(getContainerController());
			soundSource.setCc(getContainerController());
			addBehaviours();
	}

	private void addBehaviours() {
		addBehaviour(new GetMessageBehaviour(this));
	}

	private void registerSoundSource(){
		try {
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			DFService.register(this, dfd);
		} catch (FIPAException e) {
		}
	}

	private void getParameters() {
		Object[] args = getArguments();
		soundSource = (SoundSourceObject) args[0];
	}

	/*--------------------------  COMPORTAMENTS ------------------------*/

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

				if (message.getContent().equals(Message.STOP_RESUMED)){
					send(soundSource.stopSimulation(Message.STOP_RESUMED));
					System.out.println("Simulation stoped.");
				}
                else if (message.getContent().equals(Message.STOP_PAUSED)){
					send(soundSource.stopSimulation(Message.STOP_PAUSED));
					System.out.println("Simulation stoped.");
				}
				else if(message.getContent().equals(Message.PAUSE)){
					send(soundSource.suspendAllSounds());
				}
				else if(message.getContent().equals(Message.RESUME)){
					soundSource.resumeAllSounds();
				}
				else if(message.getContent().equals(Message.RUN)){
					soundSource.emitSoundPulse();
				}
				else if(message.getLanguage().equals(Language.FINISH)){
					soundSource.getSounds().remove(message.getContent());
					if(soundSource.getSounds().isEmpty()){
						send(Message.prepareMessage(ACLMessage.INFORM, null, Message.FINISH_SIMULATION, soundSource.getAmbient()));
					}
				}
				else {
					send(Message.getAnswerOfANotUnderstoodMessage(sender));					
				}
			}

		}
	}
	
	private static int id = 0;

	public static String nextId() {
		return "sound_source" + (++id);
	}
}

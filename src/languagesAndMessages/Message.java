package languagesAndMessages;

import java.util.List;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class Message {
	public static String WHAT_IS_THE_LOCATION = "What is the location?";
	public static String WHAT_IS_THE_INDEX = "What is the index?";
	public static final String MSG_NOT_UNDERSTOOD =	"Message not understood.";
	public static final String PAUSE = "pause";
	public static final String RESUME = "resume";
	public static final String STOP = "stop simulation";
	public static final String RUN = "run";
	public static final String CREATE_SOUND_SOUCE = "create sound source";
	public static final String DEFINE_OBSTACLES = "define obstacles";
	
	
	public static ACLMessage prepareMessage(int type, String language, String content, AID target){
		ACLMessage message = new ACLMessage(type);
		message.setLanguage(language);
		message.addReceiver(target);
		message.setContent(content);
		
		return message;
	}
	
	public static ACLMessage prepareMessage(int type, String language, String content, AID target1, AID target2){
		ACLMessage message = new ACLMessage(type);
		message.setLanguage(language);
		message.addReceiver(target1);
		message.addReceiver(target2);
		message.setContent(content);
		
		return message;
	}
	
	public static ACLMessage prepareMessage(int type, String language, String content, List <AID> targets){
		ACLMessage message = new ACLMessage(type);
		message.setLanguage(language);
		for(AID target : targets)
			message.addReceiver(target);
		message.setContent(content);
		
		return message;
	}
	
	public static ACLMessage 
	getAnswerOfANotUnderstoodMessage(AID target) {
		ACLMessage answer = new ACLMessage(ACLMessage.NOT_UNDERSTOOD);
		answer.addReceiver(target);
		answer.setContent(Message.MSG_NOT_UNDERSTOOD);
		return answer;
	}
}

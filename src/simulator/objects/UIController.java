package simulator.objects;

import java.util.LinkedList;
import java.util.Queue;

public class UIController {
	
	private Queue<String> events = new LinkedList<String>();
	private boolean running = false;
	private static UIController uIController = null;
	
	private UIController(){
		
	}

	public static UIController getInstance(){
		if(uIController == null)
			uIController = new UIController();
		return uIController;
	}
	
	public void addNewEvent(String event){
		events.add(event);
	}
	
	public Queue<String> getEvents() {
		return events;
	}

	public void setEvents(Queue<String> events) {
		this.events = events;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}	
}

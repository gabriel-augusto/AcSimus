package view;

import java.util.LinkedList;
import java.util.Queue;

public class UIController {
	
	private Queue<String> events = new LinkedList<String>();
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
}

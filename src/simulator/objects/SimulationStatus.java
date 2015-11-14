package simulator.objects;

public class SimulationStatus {
	private static SimulationStatus simulationStatus = null;
	private double decibel = 0;
	private double reverberationTime = 0;
	
	private SimulationStatus(){
		
	}
	
	public static SimulationStatus getInstance(){
		if(simulationStatus == null){
			simulationStatus = new SimulationStatus();
		}
		return simulationStatus;
	}

	public double getDecibel() {
		return decibel;
	}

	public void setDecibel(double decibel) {
		this.decibel = decibel;
	}

	public double getReverberationTime() {
		return reverberationTime;
	}

	public void setReverberationTime(double reverberationTime) {
		this.reverberationTime = reverberationTime;
	}
	
	
}

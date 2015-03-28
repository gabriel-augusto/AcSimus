package simulator.objects;


public abstract class Line {

	private Location initialPoint = null;
	private Location finalPoint = null;
	private double directionAngle = 0;
	
	protected Line(Location initialPoint, Location finalPoint){
		this.initialPoint = initialPoint;
		this.finalPoint = finalPoint;
	}
	
	protected Line(Location initialPoint, double direction){
		this.initialPoint = initialPoint;
		this.directionAngle = direction;
	}
	
	public static Line getLine(Location initialPoint, Location finalPoint){
		if(isVertical(initialPoint, finalPoint)){
			return new VerticalLine(initialPoint, 
					finalPoint);
		}
		return new NormalLine(initialPoint, 
				finalPoint);
	}
	
	public static Line getLine(Location initialPoint, double direction){
		if(isVertical(direction)){
			return new VerticalLine(initialPoint, direction);
		}
		return new NormalLine(initialPoint, direction);
	}
	
	private static boolean isVertical(Location initialPoint, Location finalPoint){
		if(initialPoint.getX() == finalPoint.getX())
			return true;
		return false;
	}
	
	public static boolean isVertical(double direction){
		if(direction == 90 || direction == 270)
			return true;
		return false;
	}
	
	public abstract double getSlope();
	
	public Location searchSlopePoint(Line line){
		if(line instanceof NormalLine)
			return searchIntersectionPoint((NormalLine)line);
		return searchIntersectionPoint((VerticalLine) line);
	}
	
	public abstract Location searchIntersectionPoint(VerticalLine line);
	
	public abstract Location searchIntersectionPoint(NormalLine line);
	
	public double getConstant() {
		return 0;
	}
	
	public Location getInitialPoint() {
		return initialPoint;
	}
	
	public void setInitialPoint(Location initialPoint) {
		this.initialPoint = initialPoint;
	}

	public Location getFinalPoint() {
		return finalPoint;
	}

	public void setFinalPoint(Location finalPoint) {
		this.finalPoint = finalPoint;
	}

	public double getDirection() {
		return directionAngle;
	}

	public void setDirection(double direction) {
		this.directionAngle = direction;
	}	
}

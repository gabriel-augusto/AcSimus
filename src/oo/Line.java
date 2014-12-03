package oo;


public abstract class Line {

	private Localization initialPoint = null;
	private Localization finalPoint = null;
	private double directionAngle = 0;
	
	protected Line(Localization initialPoint, Localization finalPoint){
		this.initialPoint = initialPoint;
		this.finalPoint = finalPoint;
	}
	
	protected Line(Localization initialPoint, double direction){
		this.initialPoint = initialPoint;
		this.directionAngle = direction;
	}
	
	public static Line getLine(Localization initialPoint, Localization finalPoint){
		if(isVertical(initialPoint, finalPoint)){
			return new VerticalLine(initialPoint, 
					finalPoint);
		}
		return new NormalLine(initialPoint, 
				finalPoint);
	}
	
	public static Line getLine(Localization initialPoint, double direction){
		if(isVertical(direction)){
			return new VerticalLine(initialPoint, direction);
		}
		return new NormalLine(initialPoint, direction);
	}
	
	private static boolean isVertical(Localization initialPoint, Localization finalPoint){
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
	
	public Localization searchSlopePoint(Line line){
		if(line instanceof NormalLine)
			return searchIntersectionPoint((NormalLine)line);
		return searchIntersectionPoint((VerticalLine) line);
	}
	
	public abstract Localization searchIntersectionPoint(
			VerticalLine line);
	
	public abstract Localization searchIntersectionPoint(NormalLine line);
	
	public double getConstant() {
		return 0;
	}
	
	public Localization getInitialPoint() {
		return initialPoint;
	}
	
	public void setInitialPoint(Localization initialPoint) {
		this.initialPoint = initialPoint;
	}

	public Localization getFinalPoint() {
		return finalPoint;
	}

	public void setFinalPoint(Localization finalPoint) {
		this.finalPoint = finalPoint;
	}

	public double getDirection() {
		return directionAngle;
	}

	public void setDirection(double direction) {
		this.directionAngle = direction;
	}	
}

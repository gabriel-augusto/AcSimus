package simulator.objects;


public abstract class Line {

	private Location initialPoint = null;
	private Location finalPoint = null;
	private double directionAngle = 0;
	private int type; // 0 para normal e 1 para vertical
	public static final int VERTICAL = 1;
	public static final int NORMAL = 0;
	
	//Constructors methods
	
	protected Line(Location initialPoint, Location finalPoint, int tipo){
		this.initialPoint = initialPoint;
		this.finalPoint = finalPoint;
		this.type = tipo;
	}
	
	protected Line(Location initialPoint, double direction, int tipo){
		this.initialPoint = initialPoint;
		this.directionAngle = direction;
		this.type = tipo;
	}
	
	//Static methods
	
	public static Line getLine(Location initialPoint, Location finalPoint){
		if(isVertical(initialPoint, finalPoint)){
			return new VerticalLine(initialPoint, finalPoint);
		}
		return new NormalLine(initialPoint, finalPoint);
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
	
	//Abstract methods
	
	protected abstract double getConstant();
	public abstract double getSlope();
	public abstract Location searchIntersectionPoint(NormalLine line);	
	public abstract Location searchIntersectionPoint(VerticalLine line);

	//Public methods
	
	public Location searchIntersectionPoint(Line line){
		Location intersectionPoint = null;
		switch(line.getType()){
		case 0:
			intersectionPoint = searchIntersectionPoint((NormalLine)line);
			break;
		case 1:
			intersectionPoint = searchIntersectionPoint((VerticalLine)line);
			break;
		default:
			System.out.println("Unknown line type!");
		}
		return intersectionPoint;
	}
	
	//Gets and Sets
	
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

	public int getType() {
		return type;
	}

	public void setType(int vertical) {
		this.type = vertical;
	}	
}

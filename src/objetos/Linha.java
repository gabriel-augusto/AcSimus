package objetos;


public abstract class Linha {

	private Localizacao initialPoint = null;
	private Localizacao finalPoint = null;
	private double direction = 0; // angulo de direcao.
	
	protected Linha(Localizacao initialPoint, Localizacao finalPoint){
		this.initialPoint = initialPoint;
		this.finalPoint = finalPoint;
	}
	
	protected Linha(Localizacao initialPoint, double direction){
		this.initialPoint = initialPoint;
		this.direction = direction;
	}
	
	public static Linha getLine(Localizacao initialPoint, Localizacao finalPoint){
		if(isVertical(initialPoint, finalPoint)){
			return new LinhaVertical(initialPoint, finalPoint);
		}
		return new LinhaNormal(initialPoint, finalPoint);
	}
	
	public static Linha getLine(Localizacao initialPoint, double direction){
		if(isVertical(direction)){
			return new LinhaVertical(initialPoint, direction);
		}
		return new LinhaNormal(initialPoint, direction);
	}
	
	private static boolean isVertical(Localizacao initialPoint, Localizacao finalPoint){
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
	
	public Localizacao searchSlopePoint(Linha line){
		if(line instanceof LinhaNormal)
			return searchIntersectionPoint((LinhaNormal)line);
		return searchIntersectionPoint((LinhaVertical) line);
	}
	
	public abstract Localizacao searchIntersectionPoint(LinhaVertical line);
	
	public abstract Localizacao searchIntersectionPoint(LinhaNormal line);
	
	public double getConstant() {
		return 0;

	}
	
	public Localizacao getInitialPoint() {
		return initialPoint;
	}
	
	public void setInitialPoint(Localizacao initialPoint) {
		this.initialPoint = initialPoint;
	}

	public Localizacao getFinalPoint() {
		return finalPoint;
	}

	public void setFinalPoint(Localizacao finalPoint) {
		this.finalPoint = finalPoint;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}	
}

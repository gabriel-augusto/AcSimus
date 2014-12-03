package simulator.objects;

public class NormalLine extends Line{
	
	protected NormalLine(Localization initialPoint, Localization finalPoint) {
		super(initialPoint, finalPoint);
	}
	
	protected NormalLine(Localization initialPoint, double direction){
		super(initialPoint, direction);
	}
	
	public Localization searchIntersectionPoint(NormalLine line){
		Localization intersectionPoint;
		double x;
		double y;
		
		if((this.getSlope() - line.getSlope()) != 0){
			x = (line.getConstant() - this.getConstant())/(this.getSlope() - line.getSlope());
			y = line.getSlope() * x + line.getConstant();
			intersectionPoint = new Localization(x,y);
			if(intersectionPoint != null && this.belongsToTheInterval(intersectionPoint.getX()) && line.belongsToTheInterval(intersectionPoint.getX()))		
				return intersectionPoint;
		}					
		return null;
	}
	
	public Localization searchIntersectionPoint(VerticalLine line){
		double y;
		y = this.getY(line.getConstant());
		
		if(line.belongsToTheInterval(y))
			return new Localization(line.getConstant(), y);		
		return null;
	}
	
	public Localization searchSlopePoint(Line line){
		if(line instanceof NormalLine)
			return searchIntersectionPoint((NormalLine)line);
		return searchIntersectionPoint((VerticalLine) line);
	}
	
	protected boolean belongsToTheInterval(double x){
		if(this.getFinalPoint() != null){
			if(x <= Math.max(this.getInitialPoint().getX(), this.getFinalPoint().getX()) && x >= Math.min(this.getInitialPoint().getX(), this.getFinalPoint().getX())){
				return true;
			}
		}else{
			if(this.getDirection() > 90 
					&& this.getDirection() < 270 && x <= this.getInitialPoint().getX()){
				return true;
			}else{
				if(x >= this.getInitialPoint().getX()){
					return true;
				}
			}
		}		
		return false;
	}
	
	public double getSlope() {
		if(this.getFinalPoint() != null){
			double deltaX = this.getFinalPoint().getX() - this.getInitialPoint().getX();
			double deltaY = this.getFinalPoint().getY() - this.getInitialPoint().getY();
			return deltaY/deltaX;
		}
		return Math.tan(
				Math.toRadians(this.getDirection()));
	}
	
	public double getConstant() {
		return this.getInitialPoint().getY() - this.getSlope() 
				* this.getInitialPoint().getX();
	}
	
	public Double getY(double x){
		return this.getSlope() * x + this.getConstant();
	}
}

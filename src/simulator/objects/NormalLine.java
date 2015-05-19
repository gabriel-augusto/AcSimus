package simulator.objects;

public class NormalLine extends Line{
	
	protected NormalLine(Location initialPoint, Location finalPoint) {
		super(initialPoint, finalPoint, Line.NORMAL);
	}

	protected NormalLine(Location initialPoint, double direction){
		super(initialPoint, direction, Line.NORMAL);
	}
	
	public Location searchIntersectionPoint(NormalLine line){
		Location intersectionPoint;
		double x;
		double y;
		
		if((this.getSlope() - line.getSlope()) != 0){
			x = (line.getConstant() - this.getConstant())/(this.getSlope() - line.getSlope());
			y = line.getSlope() * x + line.getConstant();
			intersectionPoint = new Location(x,y);
			if(intersectionPoint != null && this.belongsToTheInterval(intersectionPoint.getX()) && line.belongsToTheInterval(intersectionPoint.getX()))		
				return intersectionPoint;
		}					
		return null;
	}
	
	public Location searchIntersectionPoint(VerticalLine line){
		double y;
		y = this.getY(line.getConstant());
		
		if(line.belongsToTheInterval(y) && this.belongsToTheInterval(line.getConstant()))
			return new Location(line.getConstant(), y);		
		return null;
	}
	
	protected boolean belongsToTheInterval(double x){
		if(this.getFinalPoint() != null){
			if(x <= Math.max(this.getInitialPoint().getX(), this.getFinalPoint().getX()) && x >= Math.min(this.getInitialPoint().getX(), this.getFinalPoint().getX())){
				return true;
			}
		}else{
			if(this.getDirection() > 90 && this.getDirection() < 270 && x <= this.getInitialPoint().getX()){
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
	
	protected double getConstant() {
		return this.getInitialPoint().getY() - this.getSlope() * this.getInitialPoint().getX();
	}
	
	protected Double getY(double x){
		return this.getSlope() * x + this.getConstant();
	}
}

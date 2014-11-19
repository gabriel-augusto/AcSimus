package objetos;

public class LinhaNormal extends Linha{
	
	protected LinhaNormal(Localizacao initialPoint, Localizacao finalPoint) {
		super(initialPoint, finalPoint);
	}
	
	protected LinhaNormal(Localizacao initialPoint, double direction){
		super(initialPoint, direction);
	}
	
	public Localizacao searchIntersectionPoint(LinhaNormal line){
		Localizacao intersectionPoint;
		double x;
		double y;
		
		if((this.getSlope() - line.getSlope()) != 0){
			x = (line.getConstant() - this.getConstant())/(this.getSlope() - line.getSlope());
			y = line.getSlope() * x + line.getConstant();
			intersectionPoint = new Localizacao(x,y);
			if(intersectionPoint != null && this.belongsToTheInterval(intersectionPoint.getX()) && line.belongsToTheInterval(intersectionPoint.getX()))		
				return intersectionPoint;
		}					
		return null;
	}
	
	public Localizacao searchIntersectionPoint(LinhaVertical line){
		double y;
		y = this.getY(line.getConstant());
		
		if(line.belongsToTheInterval(y))
			return new Localizacao(line.getConstant(), y);		
		return null;
	}
	
	public Localizacao searchSlopePoint(Linha line){
		if(line instanceof LinhaNormal)
			return searchIntersectionPoint((LinhaNormal)line);
		return searchIntersectionPoint((LinhaVertical) line);
	}
	
	protected boolean belongsToTheInterval(double x){
		if(this.getFinalPoint() != null){
			if(x <= Math.max(this.getInitialPoint().getX(), this.getFinalPoint().getX()) 
					&& x >= Math.min(this.getInitialPoint().getX(), this.getFinalPoint().getX())){
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
		return Math.tan(Math.toRadians(this.getDirection()));
	}
	
	public double getConstant() {
		return this.getInitialPoint().getY() - this.getSlope() * this.getInitialPoint().getX();
	}
	
	public Double getY(double x){
		return this.getSlope() * x + this.getConstant();
	}
}

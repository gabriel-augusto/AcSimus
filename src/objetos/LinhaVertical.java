package objetos;

import utils.Util;

public class LinhaVertical extends Linha{
	
	private static final double TOLERANCE = 0.000001;
	
	protected LinhaVertical(Localizacao initialPoint, Localizacao finalPoint) {
		super(initialPoint, finalPoint);
	}
	
	protected LinhaVertical(Localizacao initialPoint, double direction){
		super(initialPoint, direction);
	}
	
	public Localizacao searchIntersectionPoint(LinhaVertical line){		
		return null;
	}
	
	public Localizacao searchIntersectionPoint(LinhaNormal line){
		double y;
		y = line.getY(this.getConstant());
		
		if(this.belongsToTheInterval(y))
			return new Localizacao(this.getConstant(), y);		
		return null;
	}
	
	public Localizacao searchSlopePoint(Linha line){
		if(line instanceof LinhaNormal)
			return searchIntersectionPoint((LinhaNormal)line);
		return searchIntersectionPoint((LinhaVertical) line);
	}
	
	protected boolean belongsToTheInterval(double y){
		if(this.getFinalPoint() != null){
			if(y <= Math.max(this.getInitialPoint().getY(), this.getFinalPoint().getY()) 
					&& y >= Math.min(this.getInitialPoint().getY(), this.getFinalPoint().getY())){
				return true;
			}
		}else{
			if(Util.compareDouble(this.getDirection(), 270, TOLERANCE) && y <= this.getInitialPoint().getY())
				return true;
			
			if(Util.compareDouble(this.getDirection(), 90, TOLERANCE) && y >= this.getInitialPoint().getY())
				return true;				
		}
		
		return false;
	}
	
	public double getSlope() {
		return Double.POSITIVE_INFINITY;
	}
	
	public double getConstant() {
		return super.getInitialPoint().getX();
	}

}

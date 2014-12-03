package oo;

import utils.Util;

public class VerticalLine extends Line{
	
	private static final double TOLERANCE = 0.000001;
	
	protected VerticalLine(Localization initialPoint, Localization finalPoint) {
		super(initialPoint, finalPoint);
	}
	
	protected VerticalLine(Localization initialPoint, double direction){
		super(initialPoint, direction);
	}
	
	public Localization searchIntersectionPoint(VerticalLine line){		
		return null;
	}
	
	public Localization searchIntersectionPoint(NormalLine line){
		double y;
		y = line.getY(this.getConstant());
		
		if(this.belongsToTheInterval(y))
			return new Localization(this.getConstant(), y);		
		return null;
	}
	
	public Localization searchSlopePoint(Line line){
		if(line instanceof NormalLine)
			return searchIntersectionPoint((NormalLine)line);
		return searchIntersectionPoint((VerticalLine) line);
	}
	
	protected boolean belongsToTheInterval(double y){
		if(this.getFinalPoint() != null){
			if(y <= Math.max(this.getInitialPoint().getY(), this.getFinalPoint().getY()) && y >= Math.min(this.getInitialPoint().getY(), this.getFinalPoint().getY())){
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

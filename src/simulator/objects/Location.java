package simulator.objects;

public class Location {

	private double x;	
	private double y;

	public Location(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Location(Location l) {
		this(l.x,l.y);
	}
	
	public Location() {
		this(-1,-1);
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public double distance(Location location) {
		double dx = Math.abs(this.getX() - location.getX());
		double dy = Math.abs(this.getY() - location.getY());
		return Math.sqrt((dx*dx) + (dy*dy));
	}

	@Override
	public String toString() {
		return "(x: "+x+"; y: "+y+")";
	}
	
	public boolean equals(Location location, double precision){
		if(this.distance(location) < precision)
			return true;
		return false;
	}
	
	public static Location valueOf(String loc){
		StringBuilder x = new StringBuilder();
		StringBuilder y = new StringBuilder();
		char coordinate = 'x';
		boolean foundANumber = false; 
		
		for(int i = 0; i < loc.length(); i++){
			if((Character.isDigit(loc.charAt(i)) || loc.charAt(i) == '.' || loc.charAt(i) == '-') && coordinate=='x'){
				foundANumber = true;
				x.append(loc.charAt(i));
			}
			
			if((Character.isDigit(loc.charAt(i)) || loc.charAt(i) == '.' || loc.charAt(i) == '-') && coordinate=='y'){
				y.append(loc.charAt(i));
			}
			
			if(Character.isLetter(loc.charAt(i)) && foundANumber){
				coordinate = 'y';
			}
		}		
		return new Location(Double.valueOf(x.toString()), Double.valueOf(y.toString()));
	}
}

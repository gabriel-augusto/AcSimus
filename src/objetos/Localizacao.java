package objetos;

public class Localizacao {

	private double x;	
	private double y;
	private static final double TOLERANCIA = 0.0000001;

	public Localizacao(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Localizacao(Localizacao l) {
		this(l.x,l.y);
	}
	
	public Localizacao() {
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
	
	public double distancia(Localizacao l) {
		double dx = Math.abs(this.x - l.x);
		double dy = Math.abs(this.y - l.y);
		return Math.sqrt(dx*dx + dy*dy);
	}

	@Override
	public String toString() {
		return "[x: "+x+"; y: "+y+"]";
	}
	
	public boolean equals(Localizacao localizacao){
		if(Math.abs(this.getX() - localizacao.getX()) < TOLERANCIA && Math.abs(this.getY() - localizacao.getY()) < TOLERANCIA)
		//if(this.getX() == localizacao.getX() && this.getY() == localizacao.getY())
			return true;
		return false;
	}
	
	public static Localizacao valueOf(String loc){
		StringBuilder x = new StringBuilder();
		StringBuilder y = new StringBuilder();
		char variavel = 'x';
		boolean encontrouAlgumNumero = false; 
		
		for(int i = 0; i < loc.length(); i++){
			if((Character.isDigit(loc.charAt(i)) 
					|| loc.charAt(i) == '.' 
					|| loc.charAt(i) == '-') 
					&& variavel=='x'){
				encontrouAlgumNumero = true;
				x.append(loc.charAt(i));
			}
			
			if((Character.isDigit(loc.charAt(i)) 
					|| loc.charAt(i) == '.' 
					|| loc.charAt(i) == '-') 
					&& variavel=='y'){
				y.append(loc.charAt(i));
			}
			
			if(Character.isLetter(loc.charAt(i)) && encontrouAlgumNumero){
				variavel = 'y';
			}
		}		
		return new Localizacao(Double.valueOf(x.toString()), 
				Double.valueOf(y.toString()));
	}
}

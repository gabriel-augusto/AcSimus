package simulador;

public class Localizacao {

	int x;
	
	int y;

	public Localizacao(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Localizacao(Localizacao l) {
		this(l.x,l.y);
	}
	
	public Localizacao() {
		this(-1,-1);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public double distancia(Localizacao l) {
		int dx = Math.abs(this.x - l.x);
		int dy = Math.abs(this.y - l.y);
		return Math.sqrt(dx*dx + dy*dy);
	}

	@Override
	public String toString() {
		return "[x: "+x+"; y: "+y+"]";
	}
	
	public static Localizacao valueOf(String loc){
		StringBuilder x = new StringBuilder();
		StringBuilder y = new StringBuilder();
		char variavel = 'x';
		boolean encontrouAlgumNumero = false; 
		
		for(int i = 0; i < loc.length(); i++){
			
			if(Character.isDigit(loc.charAt(i)) && variavel=='x'){
				encontrouAlgumNumero = true;
				x.append(loc.charAt(i));
			}
			
			if(Character.isDigit(loc.charAt(i)) && variavel=='y'){
				y.append(loc.charAt(i));
			}
			
			if(Character.isLetter(loc.charAt(i)) && encontrouAlgumNumero){
				variavel = 'y';
			}
		}		
		return new Localizacao(Integer.valueOf(x.toString()), 
				Integer.valueOf(y.toString()));
	}
}

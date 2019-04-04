
public class Node {
	private String type = null;
	private double X = 0;
	private double Y = 0;
	private double Z = 0;
	private double E = 0;
	private double F = 0;
	
	private Node node;
	private Node last;
	private Node next;
	
	public String getType(){
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public double getX() {
		return X;
	}	
	public void setX(double X) {
		this.X = X;
	}
	
	public double getY() {
		return Y;
	}
	public void setY(double Y) {
		this.Y = Y;
	}
	
	public double getZ() {
		return Z;
	}
	public void setZ(double Z) {
		this.Z = Z;
	}
	
	public double getE() {
		return E;
	}
	public void setE(double E) {
		this.E = E;
	}
	
	public double getF() {
		return F;
	}
	public void setF(double F) {
		this.F = F;
	}
	
	public String toStart() {
		return this.type + ' ' + 'E' + this.E;
	}
	public String toStartEF() {
		return this.type + ' ' + 'E' + this.E + ' ' + 'F' + this.F;
	}
	public String toStartFE() {
		return this.type + ' ' + 'F' + this.F + ' ' + 'E' + this.E;
	}
	public String toMoveEF() {
		return this.type + ' ' + 'X' + this.X + ' ' + 'Y' + this.Y + ' ' + 'E' + this.E + ' ' + 'F' + this.F;
	}	
	public String toMoveE() {
		return this.type + ' ' + 'X' + this.X + ' ' + 'Y' + this.Y + ' ' + 'E' + this.E;
	}
	public String toMoveF() {
		return this.type + ' ' + 'X' + this.X + ' ' + 'Y' + this.Y + ' ' + 'F' + this.F;
	}
	public String toMoveZ() {
		return this.type + ' ' + 'Z' + this.Z + ' ' + 'F' + this.F;
	}
	

}

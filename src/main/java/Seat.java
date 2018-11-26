
public class Seat {
	String row;
	String number;
	boolean isFree = true;
	public Seat(String row, String number ) {
		super();
		this.row = row;
		this.number = number;
		
	}
	public String[] toArray() {
		String[] s = {this.row,this.number,this.isFree+""};
		return s;
	}
	
}

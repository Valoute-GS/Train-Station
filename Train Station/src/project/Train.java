package project;

public class Train extends Thread{
	public int id;
	public int seats;
	public int freeSeats;
	public Station destination;
	
	public Train(int id,int seats) {
		this.id = id;
		this.seats = seats;
		this.freeSeats = seats;
	}
	
	public boolean reserveSeat() {
		if(freeSeats > 0) {
			freeSeats--;
			return true;
		}else {
			return false;
		}
	}
	
	public String freeSeats(){
		return "Train n°" + id + " -> " + this.freeSeats + "places restantes";
	}
	
}

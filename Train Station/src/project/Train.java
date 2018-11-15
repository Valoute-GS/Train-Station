package project;

public class Train extends Thread{
	public int id;
	public int seats;
	public int freeSeats;
	public boolean flushed;
	
	public Station destination;
	
	
	public Train(int id,int seats, Station destination) {
		this.id = id;
		this.seats = seats;
		this.freeSeats = seats;
		this.destination = destination;
		this.flushed = false;
	}
	
	@Override
	public void run(){
		System.out.println("I'm running and I an the Train : " + id);
	}
	
	public void leave(){
		
	}
	
	public boolean reserveSeat() {
		if(freeSeats > 0) {
			freeSeats--;
			return true;
		}else {
			return false;
		}
	}
	
	public void flush(){ //a l'arrivée en gare 
		if(!flushed){
			freeSeats = seats;
			flushed = true;
		}
	}
	
	public void freeSeat(){
		if(seats>0){
			freeSeats++;
		}
	}
	
	public void setDestination(Station d){
		this.destination = d;
	}
	
	public String freeSeats(){
		return "Train n°" + id + " -> " + this.freeSeats + "places restantes";
	}
	
	public String toString(){
		return "" + id;
	}
	
}

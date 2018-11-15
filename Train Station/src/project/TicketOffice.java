package project;

public class TicketOffice extends Thread{
	public Station location;

	public TicketOffice(Station location) {
		this.location = location;
	}
	
	public Passenger createPassenger(int id, Train train) { //a terme remplacer le train par une destination et remplir alors le train le plus plein en premier allant a cette destination
		//System.out.println(train.freeSeats());
		if(train.reserveSeat()) {
			return new Passenger(id);
		}else{
			//System.out.println("Impossible de réserver pour le train n°" );
			return null;
		}
	}
	
	
	public void run(){
		
	}
}

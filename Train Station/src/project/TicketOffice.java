package project;

public class TicketOffice {
	public Station location;

	public TicketOffice(Station location) {
		this.location = location;
	}
	
	public Passenger createPassenger(int id, Train train) {
		System.out.println(train.freeSeats());
		if(train.reserveSeat()) {
			return new Passenger(id,train);
		}else{
			System.out.println("Impossible de réserver pour le train n°" );
			return null;
		}
	}
}

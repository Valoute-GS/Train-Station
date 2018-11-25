package project;

public class TicketOffice extends Thread{
	private Station location;
	private Network n;

	public TicketOffice(Station location, Network n) {
		this.location = location;
		this.n = n;
	}

	public Passenger createPassenger(int id) {
		Station myDestination = n.randomDestination(location);
		if(n.whichTrainFromTo(location, myDestination) == null){
			return null;
		}
		Passenger p = new Passenger(id, n, location, myDestination);
		return p;
	}

	public void waitPassenger(Passenger p){
		while(p.getPState() == 0){
			Sleep.millis(1); // Without this sleep the program stucks (problem with thread ?)
		}
		return;
	}

	
	// create valid passengers 
	public void run(){
		int i = 0;
		while(true){
			Passenger p = createPassenger(i);
			if(p != null){
				//System.out.println("Monsieur " + p.getPId() + " est OK");
				p.start();
				waitPassenger(p);
			}
			i++;
		}
	}
}

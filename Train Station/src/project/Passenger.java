package project;

import java.time.Duration;
import java.time.Instant;

public class Passenger extends Thread{
	// Current state
	private final int NO_TICKET = 0;
	private final int TICKETED = 1;
	private final int READY_TO_EMBARK = 2;
	// not used yet ||| private final int ON_BOARD = 3;
	
	// Link to the network to communicate with
	private Network n;

	// Attributes
	private int id;
	private int state;
	private Station location;
	private Station destination;
	
	Instant lastStateDate;


	public Passenger(int id, Network n, Station location, Station destination) {
		this.id = id;
		this.n = n;
		this.state = NO_TICKET;
		this.lastStateDate = Instant.now();
		
		this.location = location;
		this.destination = destination;
	}

	public boolean isTicketed(){
		return state > NO_TICKET;
	}
	
	public int getPId(){
		return this.id;
	}
	
	public int getPState(){
		return this.state;
	}

	// Timing gestion, and following a basic "life"
	public void run(){
		Instant now = Instant.now();
		long delta;

		//buying a ticket
		while(state == NO_TICKET){
			now = Instant.now();
			delta = Duration.between(lastStateDate, now).toMillis();
			if(delta > 500){
				lastStateDate = now;
				state = TICKETED; //ticket in the pocket !
				//System.out.println("Je suis " + id + " et j'ai mon ticket !");
			}
		}
				
		//walking to platforms
		while (state == TICKETED){
			now = Instant.now();
			delta = Duration.between(lastStateDate, now).toMillis();
			if(delta > 2000){
				lastStateDate = now;
				state = READY_TO_EMBARK; //ticket in the pocket !
				//System.out.println("Je suis " + id + " et je suis pret a embarquer !");
			}
		}
		
		//waiting for the train
		while (state == READY_TO_EMBARK){
			Train myTrain = n.whichTrainFromTo(location, destination);
			if(myTrain==null){
				//wait
			}else{
			myTrain.reserveSeat();
			state++;
			}
		}
		
		//Adieu monde cruel
		this.interrupt();
	}

}

package project;

import java.util.LinkedList;
import java.util.List;

public class TrainSimulation {

	public static void main(String[] args) {
		
		// Aviable train stations
		Station A = new Station("A", 2);
		Station B = new Station("B", 2);
		
		// Trains
		Train t001 = new Train(1, 50);
		Train t002 = new Train(2, 50);
		Train t003 = new Train(3, 50);
		Train t004 = new Train(4, 50);
		
		// Ticket Office
		TicketOffice ticketOffice = new TicketOffice(A);
		
		// Passengers
		List <Passenger> passengers = new LinkedList<Passenger>();
		
		for(int i = 0; i < 60; i++) {
			passengers.add(ticketOffice.createPassenger(i, t001));
		}
		
		
	}

}

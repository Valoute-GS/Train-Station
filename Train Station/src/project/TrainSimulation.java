package project;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TrainSimulation {

	public static void main(String[] args) throws InterruptedException {
		
		// Aviable train stations
		Station A = new Station("A", 4);
		Station B = new Station("B", 2);
		Station C = new Station("C", 2);
		
		// Trains itinéraires
		LinkedList<Station> p001 = new LinkedList<Station>();
		p001.add(A);
		p001.add(B);
		p001.add(C);
		p001.add(A);
		p001.add(C);
		LinkedList<Station> p002 = new LinkedList<Station>();
		p002.add(A);
		p002.add(B);
		LinkedList<Station> p003 = new LinkedList<Station>();
		p003.add(A);
		p003.add(C);
		
		// Trains
		Train t001 = new Train(1, 100, p001);
		Train t002 = new Train(2, 10, p002);
		Train t003 = new Train(3, 10, p003);
		
		List<Train> allTrain = new LinkedList<Train>();
		allTrain.add(t001);
		allTrain.add(t002);
		allTrain.add(t003);
		
		/*Train t004 = new Train(4, 50, B);*/
		
		// Ticket Office
		TicketOffice ticketOfficeA = new TicketOffice(A);
		
		// Passengers
		//Queue <Passenger> passengers = new LinkedList<Passenger>();


		t001.start();
		t002.start();
		t003.start();
		A.start();
		B.start();
		C.start();
		ticketOfficeA.start();
		
		
		A.addTrainInQueue(t001);
		A.addTrainInQueue(t002);
		A.addTrainInQueue(t003);
		


		
		int i = 0;
		while(true){

			sleep.millis(500);
			t001.reserveSeat();
			int r = new Random().nextInt(3);
			ticketOfficeA.createPassenger(i, allTrain.get(r));

			System.out.println(A.toString());
			System.out.println(B.toString());
			System.out.println(C.toString());
			
			i++;
		}
	}

}

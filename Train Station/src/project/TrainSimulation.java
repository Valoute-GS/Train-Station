package project;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TrainSimulation {

	public static void main(String[] args) throws InterruptedException {
		
		// Aviable train stations
		Station A = new Station("A", 4);
		Station B = new Station("B", 2);
		
		// Trains
		Train t001 = new Train(1, 10, B);
		Train t002 = new Train(2, 10, B);
		Train t003 = new Train(3, 10, B);
		
		List<Train> allTrain = new LinkedList<Train>();
		allTrain.add(t001);
		allTrain.add(t002);
		allTrain.add(t003);
		
		/*Train t004 = new Train(4, 50, B);*/
		
		// Ticket Office
		TicketOffice ticketOfficeA = new TicketOffice(A);
		
		// Passengers
		Queue <Passenger> passengers = new LinkedList<Passenger>();


		t001.start();
		t002.start();
		t003.start();
		A.start();
		B.start();
		ticketOfficeA.start();
		
		
		A.addTrainInQueue(t001);
		A.addTrainInQueue(t002);
		A.addTrainInQueue(t003);
		


		
		int i = 0;
		while(true){

			sleep.millis(1000);
			t001.reserveSeat();
			int r = new Random().nextInt(3);
			ticketOfficeA.createPassenger(i, allTrain.get(r));

			System.out.println(A.toString());
			System.out.println(B.toString());
			
			i++;
		}
	}

}

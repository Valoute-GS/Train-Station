package project;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Network extends Thread{

	private List<Station> stations;
	private List<Train> trains;
	private HashMap<KeyStations, Integer> durations;
	TicketOffice ticketOfficeA;
	TicketOffice ticketOfficeB;
	TicketOffice ticketOfficeC;

	//base de donnee avec toutes les infos sur le reseau
	public Network(){
		stations = new LinkedList<>();
		trains = new LinkedList<>();
		durations = new HashMap<>();
		// Stations ===================================================
		Station A = new Station("A", 4);
		Station B = new Station("B", 2);
		Station C = new Station("C", 2);

		stations.add(A);
		stations.add(B);
		stations.add(C);

		A.start();
		B.start();
		C.start();

		// Trains path =================================================
		LinkedList<Station> p001 = new LinkedList<Station>();
		p001.add(getStation("A"));
		p001.add(getStation("B"));
		p001.add(getStation("C"));
		p001.add(getStation("A"));
		p001.add(getStation("B"));
		LinkedList<Station> p002 = new LinkedList<Station>();
		p002.add(getStation("A"));
		p002.add(getStation("B"));
		LinkedList<Station> p003 = new LinkedList<Station>();
		p003.add(getStation("A"));
		p003.add(getStation("C"));

		// Trains ======================================================
		Train t001 = new Train(1, 10, p001);
		Train t002 = new Train(2, 10, p002);
		Train t003 = new Train(3, 10, p003);

		trains.add(t001);
		trains.add(t002);
		trains.add(t003);

		t001.start();
		t002.start();
		t003.start();

		// Duree des trajets entre deux station ========================
		durations.put(new KeyStations(A, B), 10000);
		durations.put(new KeyStations(B, A), 10000);

		durations.put(new KeyStations(A, C), 15000);
		durations.put(new KeyStations(C, A), 15000);

		durations.put(new KeyStations(C, B), 7000);
		durations.put(new KeyStations(B, C), 7000);


		// Ticket Office
		ticketOfficeA = new TicketOffice(A, this);
		ticketOfficeB = new TicketOffice(B, this);
		ticketOfficeC = new TicketOffice(C, this);
		
		Sleep.millis(100);
		ticketOfficeA.start();
		ticketOfficeB.start();
		ticketOfficeC.start();

		A.addTrainInQueue(t001);
		A.addTrainInQueue(t002);
		A.addTrainInQueue(t003);
	}

	// Get une station random qui n'est pas celle sur laquelle l'appelant n'est pas
	public Station randomDestination(Station myLocation) {
		int r = new Random().nextInt(stations.size());
		Station tmp = stations.get(r);
		while(tmp == myLocation){
			r = new Random().nextInt(stations.size());
			tmp = stations.get(r);
		}
		
		return tmp;
	}
	
	public Station getStation(String name){
		for(Station s : stations){
			if(s.getStationName() == name){
				return s;
			}
		}
		// si la station n'existe pas
		return null;
	}

	public Train getTrain(int id){	
		for(Train t : trains){
			if(t.getTrainId() == id){
				return t;
			}
		}
		// si le train n'existe pas
		System.err.println("Le train " + id + " n'existe pas");
		return null;
	}

	public int getDurationBetween(Station from, Station to){
		return durations.get(new KeyStations(from, to));
	}

	public Train whichTrainFromTo(Station from, Station to){
		for(Train t : trains){
			if(t.getCurrentStation() == from && t.getPath().contains(to)){
				return t;
			}
		}
		
		
		return null;
	}


	public void run(){
		while(true){
			Sleep.millis(200);
			for(Station s : stations){
				System.out.println(s.toString());
			}
		}

	}

	private class KeyStations{
		@SuppressWarnings("unused")
		private Station from;
		@SuppressWarnings("unused")
		private Station to;

		protected KeyStations(Station from, Station to){
			this.from = from;
			this.to= to;
		}

	}

}

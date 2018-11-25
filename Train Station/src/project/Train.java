package project;

import java.util.Queue;
import java.util.Random;

public class Train extends Thread{
	
	// Attributes
	private int id;
	private int seats;
	private int freeSeats;
	private boolean flushed;

	private Station currentStation;
	private Queue<Station> path;


	public Train(int id,int seats, Queue<Station> path) {
		this.id = id;
		this.seats = seats;
		this.freeSeats = seats;
		this.path = path;
		this.currentStation = this.path.poll();
		this.flushed = false;
	}

	@Override
	public void run(){
		System.out.println("I'm running and I an the Train : " + id);
		//TODO timing gestion
	}
	
	
	public boolean reserveSeat() {
		if(freeSeats > 0) {
			freeSeats--;
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isOver(){
		return path.isEmpty();
	}

	public void flush(){ 
		if(!flushed){
			freeSeats = seats;
			flushed = true;
		}
	}
	
	public boolean isFlushed(){ 
		return flushed;
	}
	
	public void setFlushed(boolean f){
		flushed = f;
	}
	
	
	public void flushSomeRandom(){
		if(!flushed){
			int r = new Random().nextInt(seats-freeSeats-1);
			freeSeats = r;
			flushed = true;
		}
	}

	public void freeOneSeat(){
		if(seats>0){
			freeSeats++;
		}
	}

	public void nextDestination() {
		if(!path.isEmpty()) {
			this.currentStation = path.poll();			
		}
	}

	public String freeSeats(){
		return "Train " + id + " -> " + this.freeSeats + "places restantes";
	}
	
	public int getFreeSeats(){
		return this.freeSeats;
	}

	public String toString(){
		String myPath = " | ";
		for(Station s : path){
			myPath +=  " --> " + s.getStationName();
		}
		
		return "\n 	Train : " + this.id + " | " + (seats-freeSeats) + "/" + seats + myPath;
	}
	
	public int getTrainId(){
		return this.id;
	}
	
	
	public Station getCurrentStation(){
		return this.currentStation;
	}
	
	public Queue<Station> getPath(){
		return this.path;
	}

}

package project;

import java.util.Queue;

public class Train extends Thread{
	public int id;
	public int seats;
	public int freeSeats;
	public boolean flushed;

	public Station nextStation;
	public Queue<Station> path;


	public Train(int id,int seats, Queue<Station> path) {
		this.id = id;
		this.seats = seats;
		this.freeSeats = seats;
		this.path = path;
		this.nextStation = this.path.poll();
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

	public void flush(){ //a l'arriv�e en gare 
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

	public void nextDestination() {
		if(!path.isEmpty()) {
			this.nextStation = path.poll();			
		}
	}

	public String freeSeats(){
		return "Train n�" + id + " -> " + this.freeSeats + "places restantes";
	}

	public String toString(){
		return "" + id;
	}

}

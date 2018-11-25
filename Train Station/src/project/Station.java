package project;

import java.util.LinkedList;
import java.util.Queue;

public class Station extends Thread {
	// Attributes
	private String name;
	private int capacity; //max nb of trains at the same time

	private Train[] platforms;
	private Queue<Train> queue;


	public Station (String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		platforms = new Train[capacity];
		queue = new LinkedList<>();
	}

	// Adding a train to queue
	public void addTrainInQueue(Train t){
		queue.add(t);
	}

	// Adding a train on a platform if ossible
	// Then flushing it
	private void managePlatform (){
		for(int p = 0; p < capacity; p++){
			// adding train if ppossible
			if(platforms[p] == null){
				platforms[p] = queue.poll();	
				
			}
			// flushing concerned trains
			if(platforms[p] != null && !platforms[p].isFlushed()){
				platforms[p].flush();
				
				if(platforms[p].isOver()){
					platforms[p] = null;
				}
			}
		}

	}
	
	private void checkTrain(){
		for(int p = 0; p < capacity; p++){ //checking each platform
			Train tmp = platforms[p];
			if(tmp != null && tmp.isFlushed()){
				if(tmp.getFreeSeats() == 0){ // if train is full then it go away to the next station

					tmp.nextDestination(); // update path and destination
					tmp.getCurrentStation().addTrainInQueue(tmp);
					tmp.setFlushed(false);
					platforms[p] = null;
										
				}
			}
		}
	}
	
	public String toString(){
		String s = "";
		for(Train t : platforms){
			if(t==null){
				s += " \n	EMPTY | ";
			}else{
				s += t.toString();
			}
		}
		return "\n\nStation : " + name + " => " + s;
	}

	public String getStationName(){
		return this.name;
	}
	
	@Override
 	public void run(){
		while(true){
			Sleep.millis(10);
			//System.out.println(this.toString());
			// TODO REGROUPER LES OPERATIONS ET/OU LES FAIRE SEULEMENT SI NECESSAIRE POUR LIMITER LE NOMBRE D'APPELS
			
			// on verifie si un train n'attend pas son entree en gare et on vide les trains qui ont besoin
			managePlatform();
			// si un train est plein on le fait partir
			checkTrain();
			
			
		}
	}


}

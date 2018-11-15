package project;

import java.util.LinkedList;
import java.util.Queue;

public class Station extends Thread {
	public String name;
	private int capacity; //max nb of trains at the same time

	private Train[] platforms;
	private Queue<Train> queue;


	public Station (String name, int capacity) {
		this.name = name;
		this.capacity = capacity;
		platforms = new Train[capacity];
		queue = new LinkedList<>();
	}

	//ajoute un train en file d'attente
	public void addTrainInQueue(Train t){
		queue.add(t);
	}

	// l'ajoute sur un quai si il y en a un de dispo
	private void addTrainInPlatform (){
		for(int p = 0; p < capacity; p++){
			if(platforms[p] == null){
				platforms[p] = queue.poll();
			}
		}

	}
	
	
	private void checkTrain(){
		for(int p = 0; p < capacity; p++){
			Train tmp = platforms[p];		
			if(tmp != null && name == "A"){
				if(tmp.freeSeats == 0){
					tmp.destination.addTrainInQueue(tmp);
					platforms[p] = null;
					
				}
			}
		}
	}
	
	public String toString(){
		String s = "";
		for(Train t : platforms){
			if(t==null){
				s += " \n	VIDE | ";
			}else{
				s += "\n 	Train : " + t.id + " - FS : " + t.freeSeats +" | ";
			}
		}
		return "\n\nGare : " + name + " => " + s;
	}

	
	@Override
	public void run(){
		while(true){
			sleep.millis(00);
			// on verifie si un train n'attend pas son entree en gare
			addTrainInPlatform();
			// si un train est plein on le fait partir
			checkTrain();
			
			
		}
	}


}

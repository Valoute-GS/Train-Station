package project;

import java.util.concurrent.TimeUnit;

public class sleep {
	
	public static void millis(int i){
		try {
			TimeUnit.MILLISECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

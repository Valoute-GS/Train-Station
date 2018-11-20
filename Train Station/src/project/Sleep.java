package project;

import java.util.concurrent.TimeUnit;

public class Sleep {
	
	public static void millis(int i){
		try {
			TimeUnit.MILLISECONDS.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

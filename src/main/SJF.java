package main;

/*
 * SJF (Non-Preemptive shortest-Job-First Scheduling)
 */

public class SJF{
	
	private static Process[] processOriginal;
	private int processnumber;
	
	public SJF(int n){	
		//create process list based on input number (length)
		processOriginal = new Process[n];
		createProcessSJF(n);
		processnumber = n;
	}
	

	//create random process with id, burst, arrival and priority
	private static void createProcessSJF(int number) {
		for(int i = 0; i < number; i++) {
			int[] bArray = {(int)(Math.random() * 60 + 1)};
			processOriginal[i] = new Process(i,bArray);
		}
	}

	
	public void println() {	
		for(int i = 0; i < processnumber; i++) {
			int[] b = processOriginal[i].getCPUBurstList();			
			System.out.println("ID: " + processOriginal[i].getPid() + 
					" Brust: " + b[0]);
		}
	}
}



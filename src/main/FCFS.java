package main;

/*
 * FCFS (Non-preemptive First-Come, First-Served Scheduling)
 */

public class FCFS {
	
	private static Process[] processOriginal;
	private int processnumber;
	
	private int totalWaitTime;
	private int totalTurnaroundTime;
	
	public FCFS(int n) {
		// create process list based on input number (length)
		processOriginal = new Process[n];
		createProcessFCFS(n);
		processnumber = n;

		FCFSProcess();
	}

	
	private void FCFSProcess() {
		System.out.println("=================Using FCFS(NP)=================");
		waitTime();
		finishTime();
		println();
	}
  
	//calculate the wait time for each process
	private void waitTime() {
		int temp;
		totalWaitTime = 0;
		for (int i = 0; i < processnumber; i++) {
			temp = 0;
			if(i != 0) {
				for (int j = i-1; j >= 0; j--) {
					temp += processOriginal[j].getCPUBurstList()[0];		
				}
			}
			processOriginal[i].setWaitTime(temp);
			totalWaitTime += temp;
		}
	}
	
	//calculate finishTime
	private void finishTime() {
		int temp;
		totalTurnaroundTime = 0;
		for (int i = 0; i < processnumber; i++) {
			temp = 0;
			for (int j = i; j >= 0; j--) {
				temp += processOriginal[j].getCPUBurstList()[0];
			}
			processOriginal[i].setFinishTime(temp);
			int turnAroundT = temp - processOriginal[i].getStartTime();
			processOriginal[i].setTurnAroundTime(turnAroundT);
			totalTurnaroundTime += turnAroundT;
		}
	}

	// create random process with id, burst, arrival and priority
	private static void createProcessFCFS(int number) {
		for (int i = 0; i < number; i++) {
			int[] bArray = { (int) (Math.random() * 60 + 1) };
			processOriginal[i] = new Process(i, bArray);
		}
	}

	//print all the information
	public void println() {
		for (int i = 0; i < processnumber; i++) {
			System.out.println("ID: " + processOriginal[i].getPid() + 
					" Brust: " + processOriginal[i].getCPUBurstList()[0] + 
					" WaitTime: " + processOriginal[i].getWaitTime() +
					" TurnAroundTime: " + 
					(processOriginal[i].getFinishTime() - processOriginal[i].getStartTime())
					);
		}
		
		double s = ((double)totalWaitTime) / (double)processnumber ;
		double t = ((double)totalTurnaroundTime) / (double)processnumber;
		System.out.printf("Average waiting time = %f \n", s); 
        System.out.printf("Average turn around time = %f \n", t); 
	}
  
}

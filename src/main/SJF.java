package main;

/*
 * SJF (Non-Preemptive shortest-Job-First Scheduling)
 */

public class SJF {

	private static Process[] processOriginal;
	private int processnumber;
	
	private int totalWaitTime;
	private int totalTurnaroundTime;
	
	public SJF(int n) {
		// create process list based on input number (length)
		processOriginal = new Process[n];
		createProcessSJF(n);
		processnumber = n;

		SJFProcess();
	}

	// method to run other methods inside this class in the right order.
	private void SJFProcess() {
		waitTime();
		finishTime();
		println();
		System.out.println("===============After SJF(NP) Sort===============");
		sort();
		waitTime();
		finishTime();
		println();
	}

	//sort the list in SJF NonPreemptive
	private void sort() {
		for (int i = 0; i < processnumber; i++) {
			for (int j = i + 1; j < processnumber; j++) {
				if (processOriginal[i].getCPUBurstList()[0] > processOriginal[j].getCPUBurstList()[0]) {
					Process temp = processOriginal[i];
					processOriginal[i] = processOriginal[j];
					processOriginal[j] = temp;
				}
			}
		}
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
	private static void createProcessSJF(int number) {
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

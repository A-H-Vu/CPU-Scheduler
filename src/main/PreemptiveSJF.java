package main;

public class PreemptiveSJF {
	private static Process[]process;
	private int processnumber;
	private int totalWaitTime;
	private int totalTurnaroundTime;

	//Constructor
	public PreemptiveSJF(int n) {
		process = new Process[n];
		creatPreemptiveSJF(n);
		processnumber = n;
		PreemptiveSJFProcess();
	}

	//Execute the whole class methods; ComputeFirst-Come,First-Served(FCFS) Scheduling;Compute PreemptiveSJFProcess Scheduling;
	private void PreemptiveSJFProcess() {
		waitTime();
		finishTime();
		println();
		System.out.println("===============After NonpreemptivePriority Sort===============");
		sort();
		PreemptivewaitTime();
		PreemptivefinishTime();
		println();
	}

	//Create random process[] with id, burst, arrival and priority
	private static void creatPreemptiveSJF(int num) {
		for (int i = 0; i < num; i++) {
			int[] bArray = { (int) (Math.random() * 60 + 1) };
			process[i] = new Process(i, bArray);
		}
	}

	private void sort() {
		for (int i = 0; i < processnumber; i++) {
			for (int j = i + 1; j < processnumber; j++) {
				if (process[i].getCPUBurstList()[0] > process[j].getCPUBurstList()[0]) {
					Process temp = process[i];
					process[i] = process[j];
					process[j] = temp;
				}
			}
		}
	}
	//Compute the wait time and totalWaitTime
	private void waitTime() {
		int temp;
		totalWaitTime = 0;
		for (int i = 0; i < processnumber; i++) {
			temp = 0;
			if(i != 0) {
				for (int j = i-1; j >= 0; j--) {
					temp += process[j].getCPUBurstList()[0];		
				}
			}
			process[i].setWaitTime(temp);
			totalWaitTime += temp;
		}
	}

	//Compute the turn around time and totalTurnaroundTime
	private void finishTime() {
		int temp;
		totalTurnaroundTime = 0;
		for (int i = 0; i < processnumber; i++) {
			temp = 0;
			for (int j = i; j >= 0; j--) {
				temp += process[j].getCPUBurstList()[0];
			}
			process[i].setFinishTime(temp);
			int turnAroundT = temp - process[i].getStartTime();
			process[i].setTurnAroundTime(turnAroundT);
			totalTurnaroundTime += turnAroundT;
		}
	}
	
	//compute the Preemptive wait time and totalWaitTime
	private void PreemptivewaitTime() {
		int bt[] = new int[processnumber];
		for(int i=0;i<processnumber;i++) {
			bt[i]=process[i].getCPUBurstList()[0];
		}
		
		int count=0,t=0,min=Integer.MAX_VALUE;
		int shortest=0, finishTime;
		boolean check=false;
		
		while(count!=processnumber) {
			for(int j=0;j<processnumber;j++) {
				if((process[j].getArrivalTime()<=t)&&(bt[j]<min)&& bt[j]>0) {
					min = bt[j];
					shortest=j;
					check=true;
				}
			}
			
			if(check==false) {
				t++;
				continue;
			}
			
			bt[shortest]--;
			min = bt[shortest];
			if(min==0) {
				min=Integer.MAX_VALUE;
			}
			if(bt[shortest]==0) {
				count++;
				check=false;
				finishTime = t+1;
				int s=finishTime-process[shortest].getCPUBurstList()[0]-process[shortest].getArrivalTime();
				process[shortest].setWaitTime(s);
				totalWaitTime += s;
				if(process[shortest].getWaitTime() <0) {
					process[shortest].setWaitTime(0);
				}
			}
			t++;
		}
		
	}
	
	//compute the Preemptive turn around time and totalTurnaroundTime
	private void PreemptivefinishTime() {
		for(int i=0;i<processnumber;i++) {
			int temp = process[i].getCPUBurstList()[0]+process[i].getWaitTime();
			process[i].setTurnAroundTime(temp);
			totalTurnaroundTime += temp;
		}
	}

	//Print all the information and compute the average wait time, average turn around time
	public void println() {
		for (int i = 0; i < processnumber; i++) {
			System.out.println("ID: " + process[i].getPid() + 
					" Brust: " + process[i].getCPUBurstList()[0] + 
					" WaitTime: " + process[i].getWaitTime() +
					" TurnAroundTime: " + 
					(process[i].getFinishTime() - process[i].getStartTime())
					);
		}

		double s = ((double)totalWaitTime) / (double)processnumber ;
		double t = ((double)totalTurnaroundTime) / (double)processnumber;
		System.out.printf("Average waiting time = %f \n", s); 
        System.out.printf("Average turn around time = %f \n", t); 
	}

}

	


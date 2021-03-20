package main;

import java.util.*;
import java.util.Scanner;

public class NonpreemptivePriority {
	private static Process[]process;
	private int processnumber;
	private int totalWaitTime;
	private int totalTurnaroundTime;

	//Constructor
	public NonpreemptivePriority(int n) {
		process = new Process[n];
		createNonpreemptivePriority(n);
		processnumber = n;
    NonpreemptivePriorityProcess();
	}

	//Execute the whole class methods; ComputeFirst-Come,First-Served(FCFS) Scheduling;Compute NonpreemptivePriority Scheduling;
	private void NonpreemptivePriorityProcess() {
		waitTime();
		finishTime();
		println();
		System.out.println("===============After NonpreemptivePriority Sort===============");
		setNonpreemptivePriority();
		waitTime();
		finishTime();
		println();
	}

	//Create random process[] with id, burst, arrival and priority
	private static void createNonpreemptivePriority(int num) {
		for (int i = 0; i < num; i++) {
			int[] bArray = { (int) (Math.random() * 60 + 1) };
			process[i] = new Process(i, bArray);
		}
	}

	//Set priority for the process[]
	public void setNonpreemptivePriority() {
		int[]priority = new int[processnumber];
		int length = process.length;
		System.out.println("");
		System.out.println("Enter the priority for process");
		Scanner scan=new Scanner(System.in);
		for(int i=0;i<length;i++) {
			priority[i]=scan.nextInt();
		}
		scan.close();

		int temp1;
		Process temp2;
		for(int i=0;i<processnumber-1;i++) {
			for(int j=0;j<processnumber-1;j++) {
				if(priority[j]>priority[j+1]) {
					temp1=priority[j];
					priority[j]=priority[j+1];
					priority[j+1]=temp1;
					
					temp2 =process[j];
					process[j]=process[j+1];
					process[j+1]=temp2;
					
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

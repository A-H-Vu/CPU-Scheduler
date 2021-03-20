package main;

import java.util.*;
import java.util.Scanner;

public class NonpreemptivePriority {
	private static List<Process> process= new ArrayList<Process>();
	private int processnumber;
	private int totalWaitTime;
	private int totalTurnaroundTime;
	
	public NonpreemptivePriority(int n) {
		createNonpreemptivePriority(n);
		processnumber = n;
		NonpreemptivePriorityProcess();
	}
	
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
	
	private static void createNonpreemptivePriority(int num) {
		for (int i = 0; i < num; i++) {
			int[] bArray = { (int) (Math.random() * 60 + 1) };
			process.add(new Process(i, bArray));
		}
	}
	
	public void setNonpreemptivePriority() {
		
		int size = process.size();
		int[]priority = new int[size];
		System.out.println("");
		System.out.println("Enter the priority for process(start with zero)");
		Scanner scan=new Scanner(System.in);
		for(int i=0;i<size;i++) {
			priority[i]=scan.nextInt();
		}
		scan.close();
		
		List<Process> comp= new ArrayList<Process>();
		for(int i=0;i<size;i++) {
			Process temp1=process.get(priority[i]);
			comp.add(temp1);
		}
		process = comp;
		
	}
	
	private void waitTime() {
		int temp;
		totalWaitTime = 0;
		for (int i = 0; i < processnumber; i++) {
			temp = 0;
			if(i != 0) {
				for (int j = i-1; j >= 0; j--) {
					temp += process.get(i).getCPUBurstList()[0];		
				}
			}
			process.get(i).setWaitTime(temp);
			totalWaitTime += temp;
		}
	}
	
	private void finishTime() {
		int temp;
		totalTurnaroundTime = 0;
		for (int i = 0; i < processnumber; i++) {
			temp = 0;
			for (int j = i; j >= 0; j--) {
				temp += process.get(j).getCPUBurstList()[0];
			}
			process.get(i).setFinishTime(temp);
			int turnAroundT = temp - process.get(i).getStartTime();
			process.get(i).setTurnAroundTime(turnAroundT);
			totalTurnaroundTime += turnAroundT;
		}
	}
	
	public void println() {
		for (int i = 0; i < processnumber; i++) {
			System.out.println("ID: " + process.get(i).getPid() + 
					" Brust: " + process.get(i).getCPUBurstList()[0] + 
					" WaitTime: " + process.get(i).getWaitTime() +
					" TurnAroundTime: " + 
					(process.get(i).getFinishTime() - process.get(i).getStartTime())
					);
		}
		
		double s = ((double)totalWaitTime) / (double)processnumber ;
		double t = ((double)totalTurnaroundTime) / (double)processnumber;
		System.out.printf("Average waiting time = %f \n", s); 
        System.out.printf("Average turn around time = %f \n", t); 
	}

}

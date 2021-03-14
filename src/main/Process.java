package main;

public class Process {
	private int pid;
	private int burstTime;
	private int arrivalTime;
	private int priority;
	
	public Process(int pid, int burstTime) {
		this.pid = pid;
		this.burstTime = burstTime;
	}
	
	public Process(int pid, int burstTime, int arrivalTime, int priority) {
		this(pid, burstTime);
		this.arrivalTime = arrivalTime;
		this.priority = priority;
	}
	
	public void printLine() {
		System.out.println("ID:" + pid + " BurstTime:" + burstTime + 
				" ArrivalTime:" + arrivalTime + " Priority:" + priority);
	}
}

package main;

import java.util.ArrayList;

public class Process {
	private int pid;
	private int arrivalTime;
	private int priority;
	private int[] CPUBurstList;
	private int[] IOBurstList;

	private int startTime;
	private int finishTime;
	private int waitTime;
	private int ioWairTime;

	public Process(int pid, int[] CPUBurstList) {
		this.pid = pid;
		this.CPUBurstList = CPUBurstList;
	}
	
	public Process(int pid, int arrivalTime, int priority, int[] CPUBurstList,
			int[] IOBurstList) {
		super();
		this.pid = pid;
		this.arrivalTime = arrivalTime;
		this.priority = priority;
		this.CPUBurstList = CPUBurstList;
		this.IOBurstList = IOBurstList;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int[] getCPUBurstList() {
		return CPUBurstList;
	}

	public void setCPUBurstList(int[] cPUBurstList) {
		CPUBurstList = cPUBurstList;
	}

	public int[] getIOBurstList() {
		return IOBurstList;
	}

	public void setIOBurstList(int[] iOBurstList) {
		IOBurstList = iOBurstList;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getIoWairTime() {
		return ioWairTime;
	}

	public void setIoWairTime(int ioWairTime) {
		this.ioWairTime = ioWairTime;
	}


	
}

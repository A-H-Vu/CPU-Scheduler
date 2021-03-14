package main;

import java.util.Scanner;

public class Scheduler {
	static Scanner s = new Scanner(System.in);
	static Process[] processOriginal;
	
	public static void main(String[] args) {
		//program start, print welcome message
		printWelcome();
		int number = s.nextInt();
		//create process list based on input number (length)
		processOriginal = new Process[number];
		
		//create different process
		randomProcess(number);
		
		//print all the algorithm, then select One of the Algorithm and use it 
		printSelection();
		int SdSelection = s.nextInt();
		selectAlgorithem(SdSelection);
		
		s.close();
	
	}
	
	private static void selectAlgorithem(int SdSelection) {
		switch(SdSelection) {
		case 1:
			//FCFS
			break;
		case 2:
			//SJF (NonPreemptive)
			break;
		case 3:
			//SJF (Preemptive)
			break;
		case 4:
			//Priority (NonPreemptive)
			break;
		case 5:
			//Priority (Preemptive)
			break;
		case 6:
			//RR (NPreemptive)
			break;		
		default:
			System.out.println("Not program choied to execute!");
			break;
	}
	}
	
	//create random process with id, burst, arrival and priority
	private static void randomProcess(int number) {
		for(int i = 0; i < number; i++) {
			int brust = (int)(Math.random() * 60 + 1);
			int arrival = (int)(Math.random() * 9 + 1);
			int priority = (int)(Math.random() * 9 + 1);
			processOriginal[i] = new Process(i, brust, arrival, priority);
			processOriginal[i].printLine();
		}
	}

	//print welcome message
	private static void printWelcome() {
		System.out.println("============Welcome to CPU Scheduler!============");
		System.out.println("Before you pick which one of the CPU scheduler,");
		System.out.println("you will need to choose the size of the processed data");
		System.out.println("Please enter the integer number now: ");
	}
	
	//print selection message
	private static void printSelection() {
		System.out.println("============Select The CPU Scheduler!============");
		System.out.println("1. FCFS First Come First Served (NonPreemptive)");
		System.out.println("2. SJF Shortest-Job-First (NonPreemptive)");
		System.out.println("3. SJF Shortest-Job-First (Preemptive)");
		System.out.println("4. Priority (NonPreemptive)");
		System.out.println("5. Priority (Preemptive)");
		System.out.println("6. RR Round-Robin (Preemptive)");
	}
}

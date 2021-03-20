package main;

import java.util.Scanner;

public class Scheduler {
	//create new scanner
	static Scanner s = new Scanner(System.in);

	//number of process
	static int processnumber;
	
	public static void main(String[] args) {
		//program start, print welcome message
		printWelcome();
		processnumber = s.nextInt();

		
		//print all the algorithm, then select One of the Algorithm and use it 
		printSelection();
		int SdSelection = s.nextInt();	
	
		//create different process and run
		selectAlgorithem(SdSelection);
		
		s.close();
	
	}
	
	private static void selectAlgorithem(int SdSelection) {
		switch(SdSelection) {
		case 1:
			//FCFS
			break;
		case 2:
			//create the SJF, create process and print result
			SJF sjf = new SJF(processnumber);
		
			break;
		case 3:
			//SJF (Preemptive)
			break;
		case 4:
			//Priority (NonPreemptive)
			NonpreemptivePriority NP = new NonpreemptivePriority(processnumber);
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

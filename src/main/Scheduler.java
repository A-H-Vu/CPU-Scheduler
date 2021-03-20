package main;

import java.util.*;

public class Scheduler {
	//create new scanner
	static Scanner s = new Scanner(System.in);

	//number of process
	static int processnumber;
	
	public static void main(String[] args) {
		//program start, print welcome message
		printWelcome();
		//check player input
		boolean finish = false;
		while(!finish) {
		    try{
		    	processnumber = Integer.parseInt(s.nextLine());
		    	if(processnumber <= 0 || processnumber > 2147483647)
		    		throw new NumberFormatException() ;
		    	finish = true;
		    }catch (NumberFormatException e){
		        System.out.println("Invalid input! You have to enter a positive integer!");
		    }
		}
 
		
		//print all the algorithm, then select One of the Algorithm and use it 
		printSelection();
		//check player input
		finish = false;
		int SdSelection = 0;	
		while(!finish) {
		    try{
		    	SdSelection = Integer.parseInt(s.nextLine());
		    	if(SdSelection <= 0 || processnumber > 6)
		    		throw new NumberFormatException() ;
		    	finish = true;
		    }catch (NumberFormatException e){
		        System.out.println("Invalid input! You have to enter 1 to 6!");
		    }
		}
		
		
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
		System.out.println("Please enter a positive integer number now: ");
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

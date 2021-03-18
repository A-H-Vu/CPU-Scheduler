package EECS2011;

import java.util.*;
import java.lang.Math;

public class NonPreemptivePriority {

    static Comparator<Process> cmp1 = Comparator.comparingInt(o -> o.return_time);

    static Comparator<Process> cmp2 = Comparator.comparingInt(o -> o.priority);

    public static void schedule(LinkedList<Process> processes, int num_IO) {
        PriorityQueue<Process> waitingProcessQueue = new PriorityQueue<>(cmp1);
        PriorityQueue<Process> readyProcessQueue = new PriorityQueue<>(cmp2);
        LinkedList<Process> terminatedProcessQueue = new LinkedList<>();
        int[] IOFinishTime = new int[num_IO];
        Arrays.fill(IOFinishTime, 0);
        waitingProcessQueue.addAll(processes);
        if (Debug.Debug)
            for (Process p : processes) {
                System.out.println("process " + p.pid + " arrive at " + p.arrive_time);
            }

        int timeNow = 0;
        while (!waitingProcessQueue.isEmpty() || !readyProcessQueue.isEmpty()) {
            if (Debug.Debug) System.out.println("time now: " + timeNow);
            while (!waitingProcessQueue.isEmpty() && waitingProcessQueue.peek().return_time <= timeNow) {
                if (Debug.Debug) System.out.println("process " + waitingProcessQueue.peek().pid + " enter ready queue");
                readyProcessQueue.add(waitingProcessQueue.poll());
            }
            if (Debug.Debug) System.out.println("enter finished");
            if (readyProcessQueue.isEmpty() && !waitingProcessQueue.isEmpty()) {  // ReadyQueue is empty, so we need to move time to the next return process
                timeNow = waitingProcessQueue.peek().return_time;
                continue;
            }
            Process curProcess = readyProcessQueue.poll();  //from ready_queue to running
            assert curProcess != null;
            curProcess.wait_time += timeNow - curProcess.return_time;  //cal Waiting time
            if (curProcess.isFinished()) {  // already finished, correct the wait time
                curProcess.wait_time -= timeNow - curProcess.return_time;
                curProcess.terminate_time = curProcess.return_time;
                terminatedProcessQueue.add(curProcess);
                if (Debug.Debug) System.out.println("process " + curProcess.pid + " terminated");
                continue;
            }

            timeNow += curProcess.getNextBurstTime();
            if (Debug.Debug) System.out.println("process " + curProcess.pid + " runs for " + curProcess.getNextBurstTime());
            int ioId = curProcess.getNextIOId();
            int ioTime = curProcess.getNextIOTime();
            if (ioTime != -1) {
                IOFinishTime[ioId] = Math.max(IOFinishTime[ioId], timeNow) + ioTime;
                //here if IOFinishTime is larger, we have to wait, else we just execute io
                curProcess.return_time = IOFinishTime[ioId]; // when then process finished its io, it return to the ready queue
            } else {
                curProcess.return_time = timeNow;
            }
            if (Debug.Debug) System.out.println("process " + curProcess.pid + " next run at " + curProcess.return_time);
            curProcess.moveToNextRequirement();
            waitingProcessQueue.add(curProcess);
        }

        int waiting_time = 0, turnaround_time = 0;
        for (Process p : terminatedProcessQueue) {
            waiting_time += p.wait_time;
            turnaround_time += p.terminate_time - p.arrive_time;
            if (Debug.Debug) System.out.println("pid: " + p.pid +" wait time:" + p.wait_time + " turnaround time: " + (p.terminate_time-p.arrive_time));
        }
        System.out.println("Scheduling algorithm: Nonpreemptive Priority Scheduling");
        System.out.println("average waiting time: " + (double)waiting_time / terminatedProcessQueue.size());
        System.out.println("average turnaround time: " + (double)turnaround_time / terminatedProcessQueue.size());
    }
}

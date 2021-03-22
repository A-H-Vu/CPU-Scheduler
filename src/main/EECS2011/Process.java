package EECS2011;

import java.util.LinkedList;

public class Process {

    public static class Requirement {
        int burst_time;
        int io_time;
        int io_id;

        Requirement(int burst_time) {
            this.burst_time = burst_time;
            this.io_time = -1;
            this.io_id = -1;
        }

        public Requirement(int burst_time, int io_time, int io_id) {
            this.burst_time = burst_time;
            this.io_time = io_time;
            this.io_id = io_id;
        }
    }

    int pid;
    int priority;
    int arrive_time;  //arrive time of this process
    int return_time;  // time when this process return to the ready queue
    int terminate_time;  // time when process terminated
    int wait_time;  // total wait time in ready queue
    LinkedList<Requirement> requirements;  // list of execution requirements
    int next_requirement;  // index of next requirement

    public Process(int pid, int arrive_time, LinkedList<Requirement> requirements, int priority) {
        this.pid = pid;
        this.priority = priority;
        this.arrive_time = arrive_time;
        this.return_time = arrive_time;
        this.wait_time = 0;
        this.terminate_time = -1;
        this.requirements = requirements;
        this.next_requirement = 0;
    }

    boolean isFinished(){
        return this.next_requirement == requirements.size();
    }

    public Requirement getNextRequirement() {
        if (this.isFinished()) {
            return null;
        }
        return this.requirements.get(next_requirement);
    }

    int getNextBurstTime() {
        if (this.isFinished()) {
            return -1;
        }
        return this.requirements.get(next_requirement).burst_time;
    }

    boolean updateNextBurstTime(int increment) {
        if (this.isFinished()) {
            return false;
        }
        this.requirements.get(next_requirement).burst_time += increment;
        return true;
    }

    int getNextIOTime() {
        if (this.isFinished()) {
            return -1;
        }
        return this.requirements.get(next_requirement).io_time;
    }
    int getNextIOId() {
        if (this.isFinished()) {
            return -1;
        }
        return this.requirements.get(next_requirement).io_id;
    }
    boolean moveToNextRequirement() {
        this.next_requirement += 1;
        return true;
    }
}

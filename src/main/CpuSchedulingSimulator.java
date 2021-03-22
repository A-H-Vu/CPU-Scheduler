import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import EECS2011.Process;
import EECS2011.*;

public class CpuSchedulingSimulator {
    static LinkedList<Process> readJobs(String filename) throws IOException{
        if (filename.equals(""))
            filename = "jobs.txt";
        Scanner fd = null;
        LinkedList<Process> processes = new LinkedList<>();
        try {
            fd = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        while(fd.hasNextLine()) {
            LinkedList<Process.Requirement> requirements = new LinkedList<>();
            String s = fd.nextLine().replace(" ", "");
            if (s.equals("")){
                continue;
            }
            int pid = Integer.parseInt(s.split(":")[0]);
            int arrive_time = Integer.parseInt(s.split(":")[1]);
            int priority = Integer.parseInt(s.split(":")[3]);
            if (pid < 0)
                throw new IOException("pid should be non-negative");
            if (arrive_time < 0)
                throw new IOException("arrival time should be non-negative");
            if (priority < 0)
                throw new IOException("priority should be non-negative");
            s = s.split(":")[2];
            String[] array = s.split(";");
            int cpu_time, io_time, io_id;
            for (String r : array) {
                cpu_time = Integer.parseInt(r.split(",")[0]);
                io_time = Integer.parseInt(r.split(",")[1]);
                io_id = Integer.parseInt(r.split(",")[2]);
                if(cpu_time <= 0)
                    throw new IOException("Cpu burst time should be positive");
                if (io_time <= 0 && io_id >= 0 || io_time > 0 && io_id == -1)
                    throw new IOException("IO time error");
                requirements.add(new Process.Requirement(cpu_time, io_time, io_id));
            }
            processes.add(new Process(pid, arrive_time, requirements, priority));
        }
        return processes;
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("illegal arguments!");
            System.out.println("Usage: java CpuSchedulingSimulator <algorithm_name> <jobs file> <number of io devices> [quantum]");
            System.exit(-1);
        }
        int quantum = 2;
        if (args[0].equals("RR")) {
            if (args.length < 4) {
                System.err.println("illegal arguments number, quantum is needed when algorithm is Nonpreemptive Round-Robin");
                System.exit(-1);
            }
            quantum = Integer.parseInt(args[3]);
            if (quantum <= 0) {
                System.err.println("quantum should be positive!");
                System.exit(-1);
            }
        }
        List<String> algorithm_available = Arrays.asList("FCFS", "NSJF", "SJF", "NP", "P", "RR");
        if (!algorithm_available.contains(args[0])) {
            System.err.println("no such algorithm called " + args[0]);
            System.exit(-1);
        }
        LinkedList<Process> processes = null;
        try {
            processes = readJobs(args[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (processes == null) {
            System.err.println("fail to read process");
            System.exit(-1);
        }
        int num_io = Integer.parseInt(args[2]);
        if (num_io < 0) {
            System.err.println("number of io devices should be positive");
            System.exit(-1);
        }
        switch (args[0]) {
            case "FCFS":
                FCFS.schedule(processes, num_io); break;
            case "NSJF":
                NonPreemptiveSJF.schedule(processes, num_io); break;
            case "SJF":
                PreemptiveSJF.schedule(processes, num_io); break;
            case "NP":
                NonPreemptivePriority.schedule(processes, num_io); break;
            case "P":
                PreemptivePriority.schedule(processes, num_io); break;
            case "RR":
                PreemptiveRR.schedule(processes, num_io, quantum); break;
            default:
                FCFS.schedule(processes, num_io);
        }
    }
}

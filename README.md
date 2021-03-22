# CPU-Scheduler


**For the FCFS(Non-Preemptive)**

Run class "Scheduler" and follow what is printed on the console.


First option is to choose the data size by typing a positive integer.
Then choose the first algorithm to be used for the cpu algorithm by typing '1'.

**For the SJF(Noun-Preemptive)**

Run class "Scheduler", follow what is printed on the consule.


First option is to choose the data size, type a positive integer.
Then choose the second algorithem will be used for the cpu algorithem, type '2'.

**For the SJF(Preemptive)**

Run class "Scheduler", follow what is printed on the consule.


First option is to choose the data size, type a positive integer.
Then choose the second algorithem will be used for the cpu algorithem, type '3'.

**For the Priority Scheduling(Noun-Preemptive)**

Run class "Scheduler", follow what is printed on the consule.


First option is to choose the data size, type a positive integer.
Then choose the second algorithem will be used for the cpu algorithem, type '4'.
This class also request to set the priority for the process,type a postive integer.

**For the Preemptive Priority Scheduling**

Compile the class "CpuSchedulingSimulator", then run the class by **java CpuSchedulingSilulator \<algorithm> \<jobs file> \<number of io devices>**.
The algorithm for the Preemptive Priority Scheduling is **P**.
Users can edit jobs.txt to any values following the rules by **pid : arrival time : <CPU_time, IO_time, IO_device; CPU_time, IO_time, IO_devices
...> : priority>**.
I/O devices should be entered as 2 if using default jobs.txt.

**For the Preemptive Round-Robin (RR) Scheduling**

Compile the class "CpuSchedulingSimulator", then run the class by **java CpuSchedulingSilulator \<algorithm> \<jobs file> \<number of io devices> [quantam]**.
The algorithm for the Preemptive Priority Scheduling is **RR**.
Users can edit jobs.txt to any values following the rules by **pid : arrival time : <CPU_time, IO_time, IO_device; CPU_time, IO_time, IO_devices
...> : priority>**.
I/O devices should be entered as 2 if using default jobs.txt.

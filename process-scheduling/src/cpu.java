
import java.util.*;


class CPU
{
	final static int MAX_PROCESSES = 10;
	public static void main(String[] args)
	{
		AbstractCollection<ProcessGenerator.Process> processes = ProcessGenerator.makeProcesses(CPU.MAX_PROCESSES);
		FCFS FcfsScheduler = new FCFS(processes);
		SJF SjfScheduler = new SJF(processes);
		CPU cpu = new CPU(FcfsScheduler);
		cpu.runFor(CPU.MAX_PROCESSES);
		
		cpu = new CPU(SjfScheduler);
		cpu.runFor(CPU.MAX_PROCESSES);
		
	}
	
	private void runFor(int maxProcesses) {
		for(int i=0; (i++<maxProcesses); this.cycle());
	}

	public static int MAX = 5;
	public static int RATE = 1;
	private Scheduler scheduler;
	private boolean verbose;

	public CPU(Scheduler s)
	{
		this.scheduler = s;
	}

	public CPU(Scheduler s, boolean _verbosity)
	{
		this.scheduler = s;
		this.verbose = _verbosity;
	}

	public ProcessGenerator.Process cycle()
	{
		float cycleTime = RATE;
		ProcessGenerator.Process process = this.scheduler.next();
		System.out.println("Processing job ID " + process.verbose());
		process._proceed(cycleTime);
		return process;
	}

	public String ps()
	{
		String result = new String("\tID\tPRIORITY\tQUANTA\n");

		int i=0;
		for (ProcessGenerator.Process p : this.scheduler.getAllProcesses())
		{
			result += i++ + ".\t" + p.toString();
		}
		result += "IP @ " + this.scheduler.getIP();
		return result;
	}
	
	public boolean toggleVerbosity()
	{
		return this.verbose = !this.verbose;
	}
	
}



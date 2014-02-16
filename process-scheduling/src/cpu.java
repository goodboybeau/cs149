
import java.util.Random;
import java.util.ArrayList;


class ProcessGenerator
{
	private static int ID_DIFFERENTIATOR = 0; 
	public class Process
	{
		public double quanta;
		public Integer priority;
		public int start_time;
		public int id;

		public Process(double q, int p, int s_t)
		{
			this.start_time = s_t;
			this.quanta = q;
			this.priority = p;
			this.id = ID() + this.priority;
		};

		public double _proceed(int TIME)
		{
			this.quanta = ( this.quanta > TIME ? this.quanta - TIME : 0 );
			return this.quanta;
		}

		public double _wait()
		{
			return this.quanta;
		}

		public String toString()
		{
			String output =
			this.id + "\t\t" + this.priority + "\t\t" + this.quanta + "\n";
			return output;
		};
	}

	public static int ID()
	{
		return 10 * ID_DIFFERENTIATOR++;
	}

	public Process getProcess()
	{
		Random random = new Random();
		double r_q = random.nextInt(10) + random.nextDouble();
		Integer r_p = new Integer(random.nextInt());
		return new Process(r_q, r_p, 5);
	}

	public static ArrayList<Process> getProcesses(int count)
	{
		ArrayList<Process> processes = new ArrayList<Process>(count);
		return processes;
	}
}

class CPU
{
	public static int MAX = 5;
	public static int RATE = 5;
	private Scheduler scheduler;

	public CPU(Scheduler s)
	{
		this.scheduler = s;
	}

	public String ps()
	{
		String result = new String("ID\t\tPRIORITY\t\tQUANTA");

		for (ProcessGenerator.Process p : this.scheduler.getAllProcesses())
		{
			result += p.toString();
		}

		return result;
	}

	public ProcessGenerator.Process cycle()
	{
		ProcessGenerator.Process p = this.scheduler.next();
		p._proceed(RATE);
		return p;
	}

	public abstract class Scheduler
	{
		protected ArrayList<ProcessGenerator.Process> processes = new ArrayList<ProcessGenerator.Process>();

		public Scheduler()
		{
			this.processes = ProcessGenerator.getProcesses(1);
			this.schedule();
		}

		public Scheduler(ArrayList<ProcessGenerator.Process> p)
		{
			this.processes = p;
			this.schedule();
		}

		public void setProcesses(ArrayList<ProcessGenerator.Process> ps)
		{
			this.processes = ps;
		}

		public ArrayList<ProcessGenerator.Process> getAllProcesses()
		{
			return this.processes;
		}

		public abstract ProcessGenerator.Process next();
		public abstract void schedule();
	};
}

class FCFS extends CPU.Scheduler
{
	public static void main(String [] args)
	{
		ArrayList<ProcessGenerator.Process> processes = ProcessGenerator.getProcesses(10);
		FCFS scheduler = new FCFS(processes);
		CPU cpu = new CPU(scheduler);

		System.out.println(cpu.ps());
	}

	private int IP;
	public FCFS(ArrayList<ProcessGenerator.Process> p)
	{
		super(p);
		this.IP = 0;
	}

	public ProcessGenerator.Process next()
	{
		// first come, first serve... wait until it's done...
		if(this.processes.At(this.IP).quanta > 0)
		{
			return this.processes.At(this.IP);
		}
		else
		{
			this.processes.At(this.IP) = new ProcessGenerator.getProcess();
			this.IP = (++this.IP == this.processes.size()) ? 0 : this.IP;
			return this.processes.at(this.IP);
		}
	}

	public void schedule()
	{
		this.IP = 0;
		for(int i=1; i<this.processes.size(); i++)
		{
			if(this.processes.at(i) < this.processes.at(this.IP).start_time)
			{
				this.IP = i;
			}
		}
	}
}
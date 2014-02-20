import java.util.AbstractCollection;
import java.util.Comparator;
import java.util.PriorityQueue;

public abstract class Scheduler 
{
	

	// Comparator to be used for First Come First Serve 
	public static Comparator<ProcessGenerator.Process> FCFSComparator 
		= new Comparator<ProcessGenerator.Process>()
	{
		/**
		 * Only worried about the start time of the Processes
		 * @param A
		 * @param B
		 * @return
		 */
		@Override
		public int compare(ProcessGenerator.Process A, ProcessGenerator.Process B) {
			float diff = (A.start_time - B.start_time);
			if(diff >= 0)
			{
				return (int) Math.ceil(diff);
			}
			else
			{
				return (int) Math.floor(diff);
			}		
		}
	};
	
	// Comparator to be used for Shortest Job First 
		public static Comparator<ProcessGenerator.Process> SJFComparator 
			= new Comparator<ProcessGenerator.Process>()
		{
			/**
			 * Should be in the same range, before or after the CPU clock time
			 *  to worry about their total time to finish (start_quanta).
			 * @param A
			 * @param B
			 * @return
			 */
			@Override
			public int compare(ProcessGenerator.Process A, ProcessGenerator.Process B) {
				float diff = (A.start_quanta - B.start_quanta);
				if(diff >= 0)
				{
					return (int) Math.ceil(diff);
				}
				else
				{
					return (int) Math.floor(diff);
				}
			}
		};
		
		
		// Comparator to be used for Highest Priority First 
		public static Comparator<ProcessGenerator.Process> HPFComparator 
			= new Comparator<ProcessGenerator.Process>()
		{
			/**
			 * Should be in the same range, before or after the CPU clock,
			 *  to worry about priority... otherwise
			 * @param A
			 * @param B
			 * @return
			 */
			@Override
			public int compare(ProcessGenerator.Process A, ProcessGenerator.Process B) {
				if((A.start_time > Scheduler.current_time 
						&& B.start_time > Scheduler.current_time)
					|| A.start_time < Scheduler.current_time
						&& B.start_time < Scheduler.current_time)
				{
					if(A.priority == B.priority)
					{
						return (int) Math.ceil(B.start_time - A.start_time);
					}
					else
					{
						return (int) Math.ceil(A.priority - B.priority);
					}
				}
				else
				{
					return (int) Math.ceil(B.start_time - A.start_time);
				}
			}
		};
		
	// instruction pointer
	protected int readyListPointer;
	
	// current time of CPU clock
	protected static float current_time;
	
	public Scheduler()
	{
		this.readyListPointer = 0;
		current_time = 0;
	}

	public abstract ProcessGenerator.Process next();
	public abstract void schedule();
	public abstract AbstractCollection<ProcessGenerator.Process> getAllProcesses(); 
	public abstract String getAllProcessStatus();
	
	public int getIP() 
	{ 
		return this.readyListPointer; 
	}
};


class SJF extends Scheduler
{
	private PriorityQueue<ProcessGenerator.Process> processes;
	
	public SJF(AbstractCollection<ProcessGenerator.Process> _processes)
	{
		this.processes = new PriorityQueue<ProcessGenerator.Process>(_processes.size(), SJFComparator);
		this.processes.addAll(_processes);
	}
	
	@Override
	public ProcessGenerator.Process next() {
		if(this.processes.peek().quanta > 0)
		{
			return this.processes.peek();
		}
		else
		{
			return this.processes.poll();
		}
	}

	@Override
	public void schedule() {
		// Should be taken care of...
	}

	@Override
	public AbstractCollection<ProcessGenerator.Process> getAllProcesses() {
		// TODO Auto-generated method stub
		return this.processes;
	}

	@Override
	public String getAllProcessStatus() {
		String statuses = "Total jobs: " + this.processes.size() + "\n";
		for (ProcessGenerator.Process p : this.processes)
		{
			statuses += p.toString();
		}
		return statuses;
	}
}

class FCFS extends Scheduler
{
	PriorityQueue<ProcessGenerator.Process> processes;
	
	public FCFS(AbstractCollection<ProcessGenerator.Process> _processes)
	{
		super();
		this.processes = new PriorityQueue<ProcessGenerator.Process>(_processes.size(), FCFSComparator);
		this.processes.addAll(_processes);
	}

	public ProcessGenerator.Process next()
	{
		// first come, first serve... wait until it's done...
		if(this.processes.peek().quanta > 0)
		{
			return this.processes.remove();
		}
		else
		{
			System.out.println("Finished process id" + this.processes.remove().id);
			this.processes.add(ProcessGenerator.getProcess());
			return this.processes.remove();
		}
	}

	public void schedule()
	{
		this.readyListPointer = 0;
	}

	@Override
	public AbstractCollection<ProcessGenerator.Process> getAllProcesses() {
		return this.processes;
	}

	@Override
	public String getAllProcessStatus() {
		String statuses = "Total jobs: " + this.processes.size() + "\n";
		for (ProcessGenerator.Process p : this.processes)
		{
			statuses += p.toString();
		}
		return statuses;
	}
	
}

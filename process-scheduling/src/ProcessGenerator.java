import java.text.DecimalFormat;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Random;


class ProcessGenerator extends Random
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int ID_DIFFERENTIATOR = 0;
	
	public class Process
	{
		// format floats
	    private final DecimalFormat form = new DecimalFormat("0.00");  

	    // quanta at process creation
		public float start_quanta;
		// time at process creation
		public float start_time;
		// quanta left for process to finish
		public float quanta;
		// priority of process
		public int priority;
		// process id
		public int id;
		
		public Process(float s_t, float q, int p, int id)
		{
			this.start_time = s_t;
			this.quanta = this.start_quanta = q;
			this.priority = p;
			this.id = id;
		};
		
		public float _proceed(float TIME)
		{
			float timeSpent;
			
			if(this.quanta > TIME)
			{
				timeSpent = TIME;
				this.quanta -= TIME;
			}
			else
			{
				timeSpent = this.quanta;
				this.quanta = 0;
			}
			
			return timeSpent;
		}

		public float _wait()
		{
			return this.quanta;
		}

		public String toString()
		{
			String output =
					"ID: " + this.id + "\t" + this.priority + "\t" 
					+ form.format(this.quanta) + "\t" 
					+ "started with: " + form.format(this.start_quanta) 
					+ " at " + form.format(this.start_time) + "\n";
			return output;
		}
		
		public String verbose()
		{
			return
					"ID: " + this.id + "\tPriority: " + this.priority 
					+ "\tQuanta: " + this.quanta + "\tStart Time: " + form.format(this.start_time)
					+ "\tStarting Quanta " + form.format(this.start_quanta);
		}
	}
	
	public static int ID()
	{
		return 10 * ID_DIFFERENTIATOR++;
	}

	public static ProcessGenerator.Process getProcess()
	{
		ProcessGenerator procGen = new ProcessGenerator();
		// start time
		float s_t = procGen.nextFloat() * 99;
		// quanta
		float q = procGen.nextFloat() * 10;
		// priority
		int p = procGen.nextInt(4);
		// id
		int id = ProcessGenerator.ID() + p;
		// new process
		return procGen.new Process(s_t, q, p, id);
	}

	public static AbstractCollection<ProcessGenerator.Process> makeProcesses(int count)
	{
		ArrayList<ProcessGenerator.Process> processes = new ArrayList<ProcessGenerator.Process>(count);
		
		for(int i=0;i<count;i++)
		{
			processes.add(i, ProcessGenerator.getProcess());
		}

		return processes;
	}
}

import java.util.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zohaib
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        PriorityQueue<Process> q = createQueue();
        
        /*
        for(int j = 0; j < 20; j++)
        {
            System.out.println(q.poll().getArrivalTime());
        }
        System.out.println("queue was made");*/
        
        FCFS fcfs = new FCFS();
        
        fcfs.runAlg(q);
        System.out.println("Average response time: " + fcfs.aveRespTime());
        System.out.println("Average wait time: " + fcfs.aveWaitTime());
        System.out.println("Average Troughput per quanta: " + fcfs.aveTroughput());
        System.out.println("Average turn around time: " + fcfs.aveTurnTime());
        
    }
    
    public static Comparator<Process> arrivalTimeComparator = new Comparator<Process>(){
		
		@Override
		public int compare(Process p1, Process p2) 
                {
                    if (p1.getArrivalTime() > p2.getArrivalTime())
                    {
                        return +1;
                    }
                    else if (p1.getArrivalTime() < p2.getArrivalTime())
                    {
                        return -1;
                    }
                    else 
                    {  // equal
                        return 0;
                    }
                }
	};
    
    /**
     *
     * @return
     */
    public static PriorityQueue<Process> createQueue()
    {
        PriorityQueue<Process> processPriorityQueue = new PriorityQueue<>(26, arrivalTimeComparator);
        
        for(int i = 0; i < 26; i++)
        {
            //Process p = new Process();
            processPriorityQueue.add(new Process(i));
        }
        
        return processPriorityQueue;
    }
}

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
        System.out.println("queue was made");
    }
    
    public static Comparator<Process> idComparator = new Comparator<Process>(){
		
		@Override
		public int compare(Process p1, Process p2) {
            return (int) (p1.getArrivalTime() - p2.getArrivalTime());
        }
	};
    
    /**
     *
     * @return
     */
    public static PriorityQueue<Process> createQueue()
    {
        PriorityQueue<Process> processPriorityQueue = new PriorityQueue<>(100, idComparator);
        
        for(int i = 0; i < 100; i++)
        {
            Process p = new Process();
            processPriorityQueue.add(p);
        }
        
        return processPriorityQueue;
    }
}

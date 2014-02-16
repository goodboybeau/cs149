
import java.util.PriorityQueue;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zohaib
 */
public class FCFS implements AlgorithmsInt
{
    //System.out.println(AlgorithmsInt.quanta);
    
    int quanta = 100;
    Process nextProc;
    Process runProc;
    float nextArTime;
    float runArTime;
    float nextRunTime;
    float runRunTime;
    float procCounter;
    float totalTurnTime;
    float totalWaitTime;
    
    
    @Override
    public void runAlg(PriorityQueue<Process> pq) 
    {
        runProc = pq.poll();
        runArTime = runProc.arrivalTime;
        runRunTime = runProc.runTime;
        procCounter = 1;
        totalTurnTime = runRunTime;
        totalWaitTime = 0;
        int i;
        for(i = 0; i < quanta; i++)
        {
            /*if (nextArTime < i) // also check condition is previous process is done..need to do come back to this
            {
                runProc = nextProc;
                runArTime = nextArTime;
                runRunTime = nextRunTime;
            }*/
            System.out.println(i);
            System.out.println("Current process running: " + runProc.name);
            if (runArTime < quanta)
            {
                if (runRunTime >= 0)
                {
                    runRunTime = runRunTime - 1;
                }
                else
                {
                    runProc = pq.poll();
                    runArTime = runProc.arrivalTime;
                    runRunTime = runProc.runTime;
                    procCounter++;
                    totalWaitTime = totalWaitTime + (float)(i - runArTime);
                    totalTurnTime = totalTurnTime + (float)(i - runArTime) + runRunTime;
                }
            }
        }
        
        if (runRunTime > 0)
        {
            totalTurnTime = totalTurnTime + runRunTime;
        }
        
        while(runRunTime > 0)
        {
            i++;
            System.out.println(i);
            System.out.println("Current process running: " + runProc.name);
            runRunTime = runRunTime - 1;
            
        }
    }

    @Override
    public double aveTurnTime() 
    {
        return totalTurnTime/procCounter;
    }

    @Override
    public double aveWaitTime() 
    {
        return totalWaitTime/procCounter;
    }

    @Override
    public double aveRespTime() 
    {
        return totalWaitTime/procCounter;
    }

    @Override
    public double aveTroughput() 
    {
        return procCounter/100;
    }
    
}

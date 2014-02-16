import java.util.Random;
import java.util.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Zohaib
 */
public class Process 
{
    char name;              //Name of process
    float arrivalTime;      //Simulated process arrival time between 0-99 quanta
    float runTime;          //Simulated process run time 0.1 - 10 quanta
    int priority;           //Simulated process arrival time 1-4 with 1 being highest.
    Random randomGen = new Random();
    
    public Process(int i) 
    {
        this.name = (char)(i + 'a');
        this.arrivalTime = 0f;      
        this.runTime = 0f;          
        this.priority = 0;     
        
        this.randomGenerator();
    }
   
   public void randomGenerator()
   {
       priority = randomGen.nextInt(4) + 1;
       arrivalTime = randomGen.nextFloat() * 99;
       runTime = (float)((float)(randomGen.nextFloat() * 9.9) + 0.1);
   }
   
   public float getArrivalTime()
   {
       return arrivalTime;
   }
   
   public float getRunTime()
   {
       return runTime;
   }
   
   public int getPriority()
   {
       return priority;
   }
}


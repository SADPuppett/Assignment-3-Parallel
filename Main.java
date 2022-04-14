import java.io.InterruptedIOException;
import java.util.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.sun.jdi.InternalException;

class Main {

  private static int PRESENTS_NUM = 500000;
  private static int NUM_THREADS = 4;
  private static ArrayList<Integer> unorderedGifts = new ArrayList<Integer>();
  private static AtomicInteger addIndex = new AtomicInteger(0);
  private static AtomicInteger removeIndex = new AtomicInteger(0);
  private static ConList<Integer> orderedGifts;
  private static boolean ahalt = true;
  private static boolean bhalt = true;

  public static class servant extends Thread
  {
    
    public void run()
    {
      Random rand = new Random();

      int n = 1;
      
      while(ahalt||bhalt) // until all gifts and thank yous processed 
      {
        //n = rand.nextInt(3);
        n = ((n+1)%2);
        
        switch(n){
            
          case 0://add

            if(!ahalt)
              break;
  
            int a = addIndex.getAndIncrement();
            if(a < PRESENTS_NUM)
            {
              //System.out.println("ADD: "+getName()+"  "+a);
              orderedGifts.add(unorderedGifts.get(a));
            }
            else
              ahalt=false;
            break;


          case 1:
            //remove
            if(!bhalt)
              break;
            
            
            int r = removeIndex.get();
	    //System.out.println("REM: "+getName()+"  "+r);
            if(r < PRESENTS_NUM)
            {
              if(orderedGifts.remove())
                removeIndex.getAndIncrement();
                
            }
            else
              bhalt=false;
            break;

          case 2://search
            int s = rand.nextInt(PRESENTS_NUM)+1;
            orderedGifts.search(s);
            break;
            
          }
      }

      
      }
    }
  
  public static void main(String[] args) {

    // make then shuffle a list of ints from 1-500000
    for (int i = 1; i <= PRESENTS_NUM; i++) {
      unorderedGifts.add(i);
    }
    Collections.shuffle(unorderedGifts); 

    // initialize linked list
    orderedGifts = new ConList<Integer>(PRESENTS_NUM);
    
    Thread[] threadList = new Thread[NUM_THREADS];
    
    for (int i = 0; i < NUM_THREADS; i++) 
    {
      threadList[i] = new Thread(new servant());
      threadList[i].start();
    }

    // wait for threads to end before printing results
    for(int i = 0; i < NUM_THREADS; i++)
    {
       try{threadList[i].join();       
         }
      catch(InterruptedException e){}
    }
    
    System.out.println("Things added: "+ (addIndex.get()));
    System.out.println("Things removed: "+ removeIndex);
    System.out.println("Linked List Size: "+ orderedGifts.length());
  }
  
}

public class CheckerEvent implements Event {
	//private int[] tractime=new int[12];
	//private int[] expresstractime=new int[10];
	
	public void run()//this function is used to determine whether we can check out a new customer ,and compute the total customer they have finished serving,and the total customers they have checked. 
	{    for(int i=0;i<ShopperSim.checkerlist.length;i++)  
	     {    if(ShopperSim.checkerlist[i].getEvents().length()!=0)
	              {    if(ShopperSim.checkerlist[i].gettransactiontime()>1)
	            	        ShopperSim.checkerlist[i].reducetractiontime();
	                   else
	                   {    if(ShopperSim.checkerlist[i].gettransactiontime()==1)
	                        {
	                	    ShopperSim.checkerlist[i].reducetractiontime();
	                        ShopperSim.numremoved=ShopperSim.numremoved+1;
	                	    ShopperSim.checkerlist[i].remove();
	                	    
	                        System.out.println("now we have finished serving "+ShopperSim.numremoved+" customers,and the recent customer was served in normal checkout lane "+i);
	                        
	                        ShopperSim.checkerlist[i].resettransactiontime();
	                        }
	                       
	                      
	                   }
	    	               
	              }
		
	     }
	
	    for(int i=0;i<ShopperSim.expresscheck.length;i++)
	    {    if(ShopperSim.expresscheck[i].getEvents().length()!=0){
	    	    if(ShopperSim.expresscheck[i].gettransactiontime()>1)
	    	         ShopperSim.expresscheck[i].reducetractiontime();
	            else{
	            	      if(ShopperSim.expresscheck[i].gettransactiontime()==1)
	            	      {    ShopperSim.expresscheck[i].reducetractiontime();
	            	           ShopperSim.numremoved=ShopperSim.numremoved+1;
	            	           ShopperSim.expresscheck[i].remove();
	            	           System.out.println("now we have finished serving "+ShopperSim.numremoved +" customers,and the recent customer was served in the express checkout lane "+i);
	            	           ShopperSim.expresscheck[i].resetExpresstractime();
	            	      }
                        	            	
	            	     
	            }
	         }
	    	
	    	
	    	
	    }
	}

}

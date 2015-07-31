
public class ShopperMaker implements Event{
   private  int[] randomitemnumber={10,10,10,20,20,20,20,30,30,30,30,30,40,40,40,40,50,50,50,50
		                   ,60,60,60,70,70,70,80,80,90,100};
   private Stack1 stack;
   
   public ShopperMaker()
   {
        stack=new Stack1();	   
   }
   
   public Stack1 getstack()
   {
	   return stack;
   }
   
   private Shopper createnewshopper(double a)
   {    Shopper newshopper=new Shopper();
	    newshopper.numitems=getrandomnumber();
	    newshopper.arrivalinterval=a;
	    newshopper.customerbaggingtime=9*newshopper.numitems;
	    newshopper.employeebaggingtime=5*newshopper.numitems;
	    return newshopper;
	   
   }
	
	
	public  void run()//this function is used to create a new customer,and add the new customer in an appropriate normal lane or the express lane.
	{   Shopper newshopper=createnewshopper(ShopperSim.randomintervaltime);
		 stack.push(newshopper);
		 int minsize=ShopperSim.checkerlist[0].getsize();
		 int minindex=0;
		 boolean checkfull=true;
		for(int i=0;i<ShopperSim.checkerlist.length;i++)
		{  if((minsize>=ShopperSim.checkerlist[i].getsize())&&ShopperSim.checkerlist[i].getsize()<12)
		    {   minsize=ShopperSim.checkerlist[i].getsize();
			    minindex=i;
			    checkfull=false;
		        
		    }
		    
		}
		if (checkfull==true){
			ShopperSim.usingexpresslane=true;
			minsize=ShopperSim.expresscheck[0].getsize();
			for(int i=0;i<ShopperSim.expresscheck.length;i++)
			{       if((minsize>=ShopperSim.expresscheck[i].getsize())&&ShopperSim.expresscheck[i].getsize()<10)
			        {     minsize=ShopperSim.expresscheck[i].getsize();
			              minindex=i;
				            checkfull=false;
			        }
			}
			
			if(checkfull==true)
			throw new IllegalStateException("we don't have enough positions for new customer ");
			else
			{   ShopperSim.expresscheck[minindex].getEvents().add(newshopper);
			    
			System.out.println("the customer "+ShopperSim.totalcustomer+ " walked in the No."+(minindex+1)+" express checkout lanes");
			     if(ShopperSim.expresscheck[minindex].gettransactiontime()==0)
			    	 ShopperSim.expresscheck[minindex].resetExpresstractime();
			}
		}
	    else
		   { ShopperSim.checkerlist[minindex].getEvents().add(newshopper);
		         System.out.println("the customer "+ShopperSim.totalcustomer+ " walked in the No."+(minindex+1)+" normal checkout lanes");
		          if(ShopperSim.checkerlist[minindex].gettransactiontime()==0)
		        	  ShopperSim.checkerlist[minindex].resettransactiontime();
		   }
		
	}


    public  int getrandomnumber()
    {   int random=(int)(Math.random()*30);
    	return randomitemnumber[random];
    }
    
    
    public  double getarrivalinterval()
    {
    	//int newtimearrival=0;
    	int random=(int)(Math.random()*100)+1;
    	if(random<=10)
    	   return 30+0.75*30;
    	else if(random<=25)
    		return 30+0.5*30;
    	else if(random<=45)
    		return 30+0.2*30;
    	else if(random<=55)
    		return 30;
    	else if(random<=75)
    		return 30-0.2*30;
    	else if (random<=90)
    		return 30-0.5*30;
    	else 
    		return 30-0.75*30;
    }

}

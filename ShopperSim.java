
public class ShopperSim {
	public static Checker[] checkerlist=new Checker[10];//we can change the size of the normal lanes over here to simulate different situation.
	public static Checker[] expresscheck=new Checker[10];
	public static double randomintervaltime;
	public static int numremoved=0;
	 public static int totalcustomer;
	 public static boolean usingexpresslane=false;
	 public static double simulatedtime=0;
	//public static PQ agenda;
	
	public static void main(String[] args)
	{    for(int i=0;i<checkerlist.length;i++)
          {  
		       checkerlist[i]=new Checker();
	      }
	
	    for(int i=0;i<expresscheck.length;i++)
	    {
	    	expresscheck[i]=new Checker();
	    }
	    
	     CheckerEvent checkevent1=new CheckerEvent();
		//I will use main function to simulate the grocery check 
		//firstly,before start I will add 3 new customers in the checklist,notice that we only have 10 checkouts.
		
	    ShopperMaker newshoppermaker=new ShopperMaker();
	    randomintervaltime=getarrivalinterval();
	    newshoppermaker.run();
	    totalcustomer++;
	    randomintervaltime=getarrivalinterval();
	    newshoppermaker.run();
	    totalcustomer++;
	    randomintervaltime=getarrivalinterval();
	    newshoppermaker.run();
	    totalcustomer++;
	    
	    
	    for(int i=0;i<checkerlist.length;i++)
	    	checkerlist[i].resettransactiontime();
	    for(int i=0;i<expresscheck.length;i++)
	    	expresscheck[i].resetExpresstractime();
	    
	    //Then I will simulate the check event in the grocery in 5 hours;
	    
	   // double arrivalintervaltime=0;
	    int hours=5;//this used to set how many hours should I simulate,we can change the value of hours over here when we want to simulate different hours.
	    long totalsimulationtime=hours*60*60;//this one represents the total time we want to simulate
	    
	    randomintervaltime=getarrivalinterval();
	    for(double i=0;i<totalsimulationtime;i=i+randomintervaltime)
	    {    // newshoppermaker.run();
	          
	          //Shopper   shopper=(Shopper)newshoppermaker.getstack().top();
	         
	    	
	    	  for(int j=0;j<=randomintervaltime;j++)
	    	  {
	    		    checkevent1.run();  
	    		    simulatedtime++;
	    	  }//this for loop is used to simulate the circumstances in the checkout lanes before a new customer comes in. 
	    	  
	    	  totalcustomer++;
	    	  
	    	  newshoppermaker.run();
	    	  //System.out.println("now we add the No."+totalcustomer+" into the grocesry checkouts");
	    	  randomintervaltime=getarrivalinterval();
	    	
	    		
	    }
	    System.out.println();
	    System.out.println("finally ,we need to serve total "+totalcustomer+ " customers if the grocery is open only for 5 hours");
	    System.out.println("but now we have finished serving "+numremoved+" customers in "+hours+" hours");
	    System.out.println("in this simulation,we set the number of the normal lanes as "+checkerlist.length);
	    if(usingexpresslane==false)
	    { System.out.println("in this simulation,we don't use express lanes");
	    
	    }
	    else
	    	System.out.println("yes, in this simulation,we used the express lanes");
	  
		   
	  
	}
	
	
	 public  static double getarrivalinterval()
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

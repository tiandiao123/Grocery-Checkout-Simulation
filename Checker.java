
public class Checker {
    //private int size;
    private Q1 q;
    private int transactiontime;
    
    public Checker()
    {
    	//size=0;
    	q=new Q1();
    	transactiontime=0;
    }
    
    
    public int getsize()
    {    
    	return q.length();
    }
    
    public Q1 getEvents() {
        return q;
    }
    
    public void resettransactiontime()
    {
    	if(q.length()==0)
    		transactiontime=0;
    	else
    		transactiontime=((Shopper)q.getfront()).customerbaggingtime;//over here,we can change customerbaggingtime into employeebaggingtime to set the transaction time of normal checkouts
    	
    }
    
    public void resetExpresstractime()
    {
    	if(q.length()==0)
    		transactiontime=0;
    	else
    		transactiontime=((Shopper)q.getfront()).employeebaggingtime;//notice that we can change emloyeebaggingtime into customerbaggingtime to set the transaction time to simulate the express checkouts' conditions.
    }
    
    
    public void reducetractiontime(){
    transactiontime--;}
    
    public int gettransactiontime()
    {
    	return transactiontime;
    }
    
    public void remove()
    {
    	q.remove();
    }


}

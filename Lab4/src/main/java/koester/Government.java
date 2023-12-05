//Government.java

package koester;

public class Government implements Comparable<Government>, AddOnceCounter<Government>{
    private String govName;
    private int govCounter;
    
    public Government(String name)
    {
        govName = name;
        govCounter = 1;
    }
    
    @Override
    public String toString()
    {
        return govName;
    }
    
    @Override
    public int compareTo(Government gov)
    {
        if(govName.compareTo(gov.govName) > 0)
	{
		return 1;
	}
	else if(govName.compareTo(gov.govName) == 0)
	{
		return 0;
	}
	else
	{
		return -1;
	}
    }
    
    @Override
    public void incrementCounter()
    {
        govCounter++;
    }
    
    @Override
    public int getCounter()
    {
        return govCounter;
    }
}

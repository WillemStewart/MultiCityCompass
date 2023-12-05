//Government.java

package koester;

public class Government implements Comparable<Government> {
    private String govName;
    
    public Government(String name)
    {
        govName = name;
    }
    
    public String toString()
    {
        return govName;
    }
    
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
}

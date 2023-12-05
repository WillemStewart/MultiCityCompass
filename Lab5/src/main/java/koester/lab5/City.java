//City.java

package koester.lab5;

public class City implements Comparable<City>{
    private String cName;
    private String sName;
    private int zipcode;
    private double latitude;
    private double longitude;
    private int timezone;
    private boolean yesDaylight;
    
    public City(int zip, String city, String state, double lat, double lon, int zone, boolean daylight)
    {
        zipcode = zip;
        cName = city;
        sName = state;
        latitude = lat;
        longitude = lon;
        timezone = zone;
        yesDaylight = daylight;
    }
    
    public int getZip()
    {
        return zipcode;
    }
    
    public int getDistance(City c)
    {
        double PI = 3.14159265358979323846;
        double cLat = c.latitude;  
        double cLong = c.longitude; 
        double theta = longitude - cLong;
        double dist = Math.sin((latitude)*PI/180) * Math.sin((cLat)*PI/180) + 
            (Math.cos((latitude)*PI/180) * Math.cos((cLat)*PI/180) * Math.cos((theta)*PI/180)); 
        dist = Math.acos(dist);  
        dist = (dist*180/PI);  
        dist = dist * 60 * 1.1515;
        int value = (int)Math.round(dist);
        return value;
    }
    
    @Override
    public int compareTo(City c)
    {
        if(cName.compareTo(c.cName) > 0)
	{
		return 1;
	}
	else if(cName.compareTo(c.cName) == 0)
	{
		return 0;
	}
	else
	{
		return -1;
	}
    }
 
    @Override   
    public String toString()
    {
        return cName + ", " + sName;
    }
    
}

//City.java

package koester;

public class City extends Government{
    private int zipcode;
    private double latitude;
    private double longitude;
    private int timezone;
    private boolean yesDaylight;
    
    public City(int zip, String cName, double lat, double lon, int zone, boolean daylight)
    {
        super(cName);
        zipcode = zip;
        latitude = lat;
        longitude = lon;
        timezone = zone;
        yesDaylight = daylight;
    }
    
    public int getZip()
    {
        return zipcode;
    }
    
    public int getDistance()
    {
        double PI = 3.14159265358979323846;
        double fortWorthLat = 32.75736;  
        double fortWorthLon = -97.3332; 
        double theta = longitude - fortWorthLon;
        double dist = Math.sin((latitude)*PI/180) * Math.sin((fortWorthLat)*PI/180) +  
            (Math.cos((latitude)*PI/180) * Math.cos((fortWorthLat)*PI/180) * Math.cos((theta)*PI/180)); 
        dist = Math.acos(dist);  
        dist = (dist*180/PI);  
        dist = dist * 60 * 1.1515;
        int value = (int)Math.round(dist);
        return value;
    }
}

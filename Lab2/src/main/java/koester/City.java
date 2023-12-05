// City.java

package koester;

// Find out how to implement comparable city
public class City implements Comparable<City> {
    private int zipcode;
    private String cityName;
    private String stateName;
    private double latitude;
    private double longitude;
    private int timezone;
    private boolean yesDaylight;
    
    public City(int zip, String city, String state, double lat, 
            double lon, int tz, boolean yd) 
    {
        zipcode = zip;
        cityName = city;
        stateName = state;
        latitude = lat;
        longitude = lon;
        timezone = tz;
        yesDaylight = yd;
    }

    
    @Override
    public int compareTo(City c)
    {
        return cityName.compareTo(c.cityName);
    }
    
    @Override
    public String toString() 
    {
        return cityName;
    }
    
    public String getState() 
    {
        return stateName;
    }
    
    public String getZip() 
    {
        return "" + zipcode;
    }
    
    public String getTz() 
    {
        String tzString;
        
        switch (timezone) {
            case -4:
            if (yesDaylight == true) {	
                tzString = "ADT";
	    }
            else{
                tzString = "AST";
            }
                break;
            case -5:
            if (yesDaylight == true) {	
                tzString = "EDT";
	    }
            else{
                tzString = "EST";
            }
                break;
            case -6:
            if (yesDaylight == true) {	
                tzString = "CDT";
	    }
            else{
                tzString = "CST";
            }
                break;
            case -7:
            if (yesDaylight == true) {	
                tzString = "MDT";
	    }
            else{
                tzString = "MST";
            }
                break;
            case -8:
            if (yesDaylight == true) {	
                tzString = "PDT";
	    }
            else{
                tzString = "PST";
            }
                break;
            case -9:
            if (yesDaylight == true) {	
                tzString = "AKDT";
	    }
            else{
                tzString = "AKST";
            }
                break;
            case -10:
            if (yesDaylight == true) {	
                tzString = "HDT";
	    }
            else{
                tzString = "HST";
            }
                break;
            default:
                tzString = "N/A";
                break;
        }
        return tzString;
    }
}

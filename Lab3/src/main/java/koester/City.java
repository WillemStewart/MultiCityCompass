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
    
    public String getZip()
    {
        return "" + zipcode;
    }
    
    public String getTimeZone()
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

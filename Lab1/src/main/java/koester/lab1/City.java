//City.java
package koester.lab1;
public class City implements Comparable<City>
{
      private int zipcode;
      private String cityName;
      private String state;
      private double latitude;
      private double longitude;
      private int timezone;
      private boolean yesDaylight;

      public City(int zip, String city, String st, double lat, double longi, int tz, boolean yd)
      {
         zipcode = zip;
         cityName = city;
         state = st;
         latitude = lat;
         longitude = longi;
         timezone = tz;
         yesDaylight = yd;
      }
      
      @Override
      public String toString()
      {
         String concName = (cityName + ", " + state);
	 return concName;
      }
      
      @Override
      public int compareTo(City otherCity)
      {
	if (otherCity.longitude == longitude)
             return 0;
         else if (longitude < otherCity.longitude)
             return 1;
         else
             return -1;
      }
      
      public int compareLat(City otherCity)
      {
	if (otherCity.latitude == latitude)
             return 0;
         else if (latitude < otherCity.latitude)
             return 1;
         else
             return -1;
      }
}


//CityGroup.java

public class CityGroup
{

     private City[] group;
     private int cityCount;

     public CityGroup()
     {
         group = new City[100];
         cityCount = 0;
     }
     
     public City getCity(int counter)
      {
         return group[counter];
      }

     public void addCity(City newCity)
     {
         group[cityCount] = newCity;
         cityCount++;
     }

     public City findWestMost()
     {
         int result;
         City western = group[0];
         for (int i = 1; i < cityCount; i++)
         {
             result = group[i].compareTo(western);
             if (result == 1)
             {
                 western = group[i];
             }
         }
         return western;
     }
     
     public City findEastMost()
     {
         int result;
         City eastern = group[0];
         for (int i = 1; i < cityCount; i++)
         {
             result = group[i].compareTo(eastern);
             if (result == -1)
             {
                 eastern = group[i];
             }
         }
         return eastern;
     }
     
     public City findNorthMost()
     {
         int result;
         City northern = group[0];
         for (int i = 1; i < cityCount; i++)
         {
             result = group[i].compareLat(northern);
             if (result == -1)
             {
                 northern = group[i];
             }
         }
         return northern;
     }
     
     public City findSouthMost()
     {
         int result;
         City southern = group[0];
         for (int i = 1; i < cityCount; i++)
         {
             result = group[i].compareLat(southern);
             if (result == 1)
             {
                 southern = group[i];
             }
         }
         return southern;
     }

}

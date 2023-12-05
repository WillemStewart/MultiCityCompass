//CityGroup.java
package koester;

public class CityGroup
{
    private int cityCount;
    private City[] group;

    public CityGroup()
    {
        group = new City[100];
        cityCount = 0;
    }
     
    public City getCity(int counter)
    {
        return group[counter];
    }
    
    public City[] getGroup()
    {
        return group;
    }

    public int getCityCount()
    {
        return cityCount;     
    }

    public void addCity(City newCity)
    {
        group[cityCount] = newCity;
        cityCount++;
    }

    public static void mergeCities(City cityArr[], int l, int m, int r)
    {
	// create subarrays
	int n1 = m - l + 1;
	int n2 = r - m;

	// create temp arrays
	City L[] = new City[n1];
	City R[] = new City[n2];
        
	// store data into temp arrays
	for (int i = 0; i < n1; ++i)
            L[i] = cityArr[l + i];
	for (int j = 0; j < n2; ++j)
            R[j] = cityArr[m + 1 + j];

	int i = 0,  j = 0;
	
	int k = l;
        
	while (i < n1 && j < n2)
	{
            if (L[i].compareTo(R[j]) <= 0)
            {
                cityArr[k] = L[i];
                i++;
            }

            else 
            {
                cityArr[k] = R[j];
                j++;
            }
            k++;
	}
	while (i < n1) 
	{
            cityArr[k] = L[i];
            i++;
            k++;
	}
	
	while(j < n2)
	{
            cityArr[k] = R[j];
            j++;
            k++;
	}	
    }

    public static void sortCities(City group[], int l, int r)
    {
        if (l < r)
        {
            int m = l + (r - l) / 2;
            {
                sortCities(group, l, m);
                sortCities(group, m + 1, r);
                mergeCities(group, l, m, r);
            }
        }
    }
}

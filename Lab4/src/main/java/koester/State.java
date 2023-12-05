//State.java

package koester;

public class State extends Government{
    private OrderedAddOnce<City> cityTree;
    private City[] cityList;
    private int count = 0;
    
    public State(String name)
    {
        super(name);
        cityTree = new OrderedAddOnce<>();
        cityList = new City[5000];
    }
    
    public OrderedAddOnce<City> getCityTree()
    {
        return cityTree;
    }
    
    public City[] getCityList()
    {
        return cityList;
    }
    
    public int getCityLength()
    {
        return cityList.length;
    }
    
    public City getSpecCity(int i)
    {
        return cityList[i];
    }
    
    public void arrayAdd(City Item)
    {
        for (int j = 0; j < cityList.length; j++)
        {
            if (cityList[j] != null)
            {
                if (Item.toString().compareTo(cityList[j].toString()) == 0)
                {
                    return;
                }
                else if (Item.getCounter() > cityList[j].getCounter())
                {
                    City temp = cityList[j];
                    cityList[j] = Item;
                    cityList[j + 1] = temp;
                    return;
                }
            }
            else
            {
                cityList[j] = Item;
                return;
            }
        }
        
    }
    
    public int partition(City arr[], int low, int high)
    {
        City pivot = arr[high];
        int i = (low-1);
        for (int j=low; j<high; j++)
        {
            if (arr[j].getCounter() >= pivot.getCounter())
            {
                i++;
 
                City temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        City temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
 
        return i+1;
    }
    
    void sort(City arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);
            sort(arr, low, pi-1);
            sort(arr, pi+1, high);
        }
    }
    
    public void cityCounter()
    {
        count++;
    }
    
    public int getCityTreeCount()
    {   
        return count;
    }
}

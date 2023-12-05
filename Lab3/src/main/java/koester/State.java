//State.java

package koester;

public class State extends Government{
    private OrderedAddOnce<City> cityList;
    
    public State(String name)
    {
        super(name);
        cityList = new OrderedAddOnce<>();
    }
    
    public OrderedAddOnce<City> getCityList()
    {
        return cityList;
    }
    
    /*public int getCityListCount()
    {   
        int count = 0;
        return count;
    }*/
}

package dataprovider;

import org.testng.annotations.DataProvider;

import java.util.*;

public class TemperatureCityDataProvider{


    @DataProvider(name = "temp-data-provider")
    public Iterator<Object[]> createDataforTest3() {
        List<Map<String,String>> lom = new ArrayList<Map<String,String>>();
        Map<String,String> map1 = new HashMap<String,String>();
        Map<String,String> map2 = new HashMap<String,String>();
        Map<String,String> map3 = new HashMap<String,String>();

        map1.put("CityName","Hyderabad");
        map1.put("SelectCityName","Hyderabad, Telangana, IN");

        map2.put("CityName","Delhi");
        map2.put("SelectCityName","Delhi, Delhi, IN");

        map3.put("CityName","Bengaluru");
        map3.put("SelectCityName","Bengaluru, Karnataka, IN");

        lom.add(map1);
        lom.add(map2);
        lom.add(map3);
        Collection<Object[]> dp = new ArrayList<Object[]>();
        for(Map<String,String> map:lom){
            dp.add(new Object[]{map});
        }
        return dp.iterator();
    }
}



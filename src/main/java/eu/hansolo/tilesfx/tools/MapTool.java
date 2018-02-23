package main.java.eu.hansolo.tilesfx.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class MapTool {

    //--------------------------------Find Single Product Value in Map ------------------------------------------------
    public double getCurrentSingleValue(String product, HashMap<String,Integer> map)
    {
         double toReturn = 0;

        ArrayList<String> keyList = new ArrayList<>(map.keySet());
        ArrayList<Integer> valueList = new ArrayList<>(map.values());

        if(keyList.contains(product))
        {
            int index = keyList.indexOf(product);
            toReturn = valueList.get(index);
        }else{
            toReturn = 0;
        }

        return toReturn;
    }

    //--------------------------------Find Multiple Products and Add for Graph -------------------------------------------
    public double getCurrentGroupValue(ArrayList<String> productList,HashMap<String,Integer> map)
    {
        double toReturn = 0;

        ArrayList<String> keyList = new ArrayList<>(map.keySet());
        ArrayList<Integer> valueList = new ArrayList<>(map.values());

        for(int i = 0 ;i<productList.size();i++)
        {
            if(keyList.contains(productList.get(i)))
            {
                int index = keyList.indexOf(productList.get(i));
                toReturn = toReturn + valueList.get(index);
            }
        }

        return toReturn;
    }
//    public ArrayList<String> getAllLineValues(String line, HashMap<String,Integer> map)
//    {
//
//    }
}

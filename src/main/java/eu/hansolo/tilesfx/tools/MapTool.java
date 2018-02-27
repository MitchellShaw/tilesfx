package main.java.eu.hansolo.tilesfx.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class MapTool {

    private ArrayList<String> keyList;
    private ArrayList<Integer> valueList;
    private double toReturn;
    private int index;
    //--------------------------------Find Single Product Value in Map ------------------------------------------------
    public double getCurrentSingleValue(String product, HashMap<String,Integer> map)
    {
         toReturn = 0;

         keyList = new ArrayList<>(map.keySet());
         valueList = new ArrayList<>(map.values());

        if(keyList.contains(product))
        {
            index = keyList.indexOf(product);
            toReturn = valueList.get(index);
        }else{
            toReturn = 0;
        }

        return toReturn;
    }

    //--------------------------------Find Multiple Products and Add for Graph -------------------------------------------
    public double getCurrentGroupValue(ArrayList<String> productList,HashMap<String,Integer> map)
    {
        toReturn = 0;

        keyList = new ArrayList<>(map.keySet());
        valueList = new ArrayList<>(map.values());

        for(int i = 0 ;i<productList.size();i++)
        {
            if(keyList.contains(productList.get(i)))
            {
                index = keyList.indexOf(productList.get(i));
                toReturn = toReturn + valueList.get(index);
            }
        }

        return toReturn;
    }
}

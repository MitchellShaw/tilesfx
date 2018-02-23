package main.java.eu.hansolo.tilesfx.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class GoalTool {

    public double getPercentTotal(double total, double quantity)
    {
        double toReturn = 0.0;

        if(total != 0 && quantity !=0) {
            toReturn = total / quantity;
        }
        return toReturn *100;
    }

    public double getGoal(HashMap<String,Integer> inputMap, String product)
    {
        double toReturn = 0;
        ArrayList<String> productList = new ArrayList<>(inputMap.keySet());

        if(productList.contains(product))
        {
            toReturn = inputMap.get(product);
        }

        return toReturn;
    }
    public double getListGoal(HashMap<String,Integer> inputMap,ArrayList<String> prodList)
    {
        double toReturn = 0;

        ArrayList<String> keyList = new ArrayList<>(inputMap.keySet());

        for(int i = 0;i<prodList.size();i++)
        {
            if (keyList.contains(prodList.get(i)))
            {
                toReturn = toReturn + inputMap.get(prodList.get(i));
            } else {
                System.out.println("Did not find ' " + prodList.get(i) + " ' in info file. Returning zero.");
            }
        }
        return toReturn;
    }
}

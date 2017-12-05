package eu.hansolo.tilesfx;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Mitchell on 12/4/2017.
 */
public class Tool {

    HashMap<String,Integer> hospMap;
    HashMap<String,Integer> retailMap;
    HashMap<String,Integer> serversMap;
    HashMap<String,Integer> periphMap;
    HashMap<String,Integer> opticMap;

    ArrayList<HashMap<String,Integer>> returnList;


    public ArrayList<HashMap<String,Integer>> documentReader(String hosp, String retail, String servers, String periph, String optic) throws IOException, SAXException, ParserConfigurationException
    {
        //--------------------------------Variables for Maps------------------------------------------------
        hospMap = new HashMap<>();
        retailMap = new HashMap<>();
        serversMap = new HashMap<>();
        periphMap = new HashMap<>();
        opticMap = new HashMap<>();

        returnList = new ArrayList<>();


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document document = db.parse(new File("\\\\SUSMID8000\\D\\Metrics Dashboard\\info.xml"));


        //---------------------------------Hosp Reader----------------------------------------------------
        NodeList hospNodeList = document.getElementsByTagName("hosp");

        for (int x = 0, size = hospNodeList.getLength(); x < size; x++)
        {
            String hospProduct = hospNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String hospGoal = hospNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            hospMap.put(hospProduct,Integer.parseInt(hospGoal));
        }

        //---------------------------------Retail Reader----------------------------------------------------
        NodeList retailNodeList = document.getElementsByTagName("retail");

        for (int x = 0, size = hospNodeList.getLength(); x < size; x++)
        {
            String retailProduct = retailNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String retailGoal = retailNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            retailMap.put(retailProduct,Integer.parseInt(retailGoal));
        }

        //---------------------------------Server Reader----------------------------------------------------
        NodeList serverNodeList = document.getElementsByTagName("servers");

        for (int x = 0, size = serverNodeList.getLength(); x < size; x++)
        {
            String serverProduct = serverNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String serverGoal = serverNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            serversMap.put(serverProduct,Integer.parseInt(serverGoal));
        }

        //---------------------------------Periph Reader----------------------------------------------------
        NodeList periphNodeList = document.getElementsByTagName("periph");

        for (int x = 0, size = periphNodeList.getLength(); x < size; x++)
        {
            String periphProduct = periphNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String periphGoal = periphNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            periphMap.put(periphProduct,Integer.parseInt(periphGoal));
        }

        //---------------------------------Optic Reader----------------------------------------------------
        NodeList opticNodeList = document.getElementsByTagName("optic");

        for (int x = 0, size = opticNodeList.getLength(); x < size; x++)
        {
            String opticProduct = opticNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String opticGoal = opticNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            opticMap.put(opticProduct,Integer.parseInt(opticGoal));
        }

        //---------------------------------Add Maps to Return List----------------------------------------------------
        returnList.add(hospMap);
        returnList.add(serversMap);
        returnList.add(opticMap);
        returnList.add(periphMap);
        returnList.add(retailMap);

        return returnList;
    }
}

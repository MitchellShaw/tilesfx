package main.java.eu.hansolo.tilesfx.tools;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import main.java.eu.hansolo.tilesfx.Controllers.timeOrb;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by Mitchell on 12/4/2017.
 */
public class Tool {

    HashMap<String,Integer> displayMap;
    HashMap<String,Integer> ng1Map;
    HashMap<String,Integer> ng2Map;
    HashMap<String,Integer> ng3Map;
    HashMap<String,Integer> ng4Map;
    HashMap<String,Integer> ng5Map;
    HashMap<String,Integer> optic1Map;
    HashMap<String,Integer> optic2Map;
    HashMap<String,Integer> optic3Map;
    HashMap<String,Integer> optic4Map;
    HashMap<String,Integer> edAMap;
    HashMap<String,Integer> edBMap;
    HashMap<String,Integer> posAMap;
    HashMap<String,Integer> posBMap;
    HashMap<String,Integer> posCMap;
    HashMap<String,Integer> posDMap;
    HashMap<String,Integer> posEMap;
    HashMap<String,Integer> questMap;
    HashMap<String,Integer> t1000Map;
    HashMap<String,Integer> servers1Map;
    HashMap<String,Integer> servers2Map;

    timeOrb display;
    timeOrb edA;
    timeOrb edB;
    timeOrb posA;
    timeOrb posB;
    timeOrb posD;
    timeOrb posE;
    timeOrb quest;
    timeOrb t1000;
    timeOrb ng1;
    timeOrb ng2;
    timeOrb ng3;
    timeOrb ng4;
    timeOrb ng5;
    timeOrb optic1;
    timeOrb optic2;
    timeOrb optic3;
    timeOrb optic4;
    timeOrb servers1;
    timeOrb servers2;

    HashMap<String,Integer> hospMap;
    HashMap<String,Integer> retailMap;
    HashMap<String,Integer> serversMap;
    HashMap<String,Integer> periphMap;
    HashMap<String,Integer> opticMap;
    HashMap<String,Integer> hospStageMap;
    HashMap<String,Integer> retailStageMap;
    HashMap<String,Integer> serversStageMap;
    HashMap<String,Integer> periphStageMap;
    HashMap<String,Integer> opticStageMap;

    HashMap<String,Integer> returnMap;
    HashMap<String,Integer> lineMap;
    ArrayList<String> readMe;
    ArrayList<String> strings;
    ArrayList<String> itemIDS;
    ArrayList<Integer> count;
    HashMap<String, ArrayList<String>> dummyMap;

    ArrayList<HashMap<String,Integer>> returnList;
    ArrayList<HashMap<String,Integer>> returnStageList;

    ArrayList<HashMap<String,Integer>> mapList;
    ArrayList<timeOrb> orbList;

    public void tableSetUp()
    {
        mapList = new ArrayList<>();
        orbList = new ArrayList<>();

        displayMap = new HashMap<>();
        ng1Map = new HashMap<>();
        ng2Map = new HashMap<>();
        ng3Map = new HashMap<>();
        ng4Map = new HashMap<>();
        ng5Map = new HashMap<>();
        optic1Map = new HashMap<>();
        optic2Map = new HashMap<>();
        optic3Map = new HashMap<>();
        optic4Map = new HashMap<>();
        edAMap = new HashMap<>();
        edBMap = new HashMap<>();
        posAMap = new HashMap<>();
        posBMap = new HashMap<>();
        posCMap = new HashMap<>();
        posDMap = new HashMap<>();
        posEMap = new HashMap<>();
        questMap = new HashMap<>();
        t1000Map = new HashMap<>();
        servers1Map = new HashMap<>();
        servers2Map = new HashMap<>();

        mapList.add(displayMap);
        mapList.add(ng1Map);
        mapList.add(ng2Map);
        mapList.add(ng3Map);
        mapList.add(ng4Map);
        mapList.add(ng5Map);
        mapList.add(optic1Map);
        mapList.add(optic2Map);
        mapList.add(optic3Map);
        mapList.add(optic4Map);
        mapList.add(edAMap);
        mapList.add(edBMap);
        mapList.add(posAMap);
        mapList.add(posBMap);
        mapList.add(posCMap);
        mapList.add(posDMap);
        mapList.add(posEMap);
        mapList.add(questMap);
        mapList.add(t1000Map);
        mapList.add(servers1Map);
        mapList.add(servers2Map);

        for(int i = 0; i<mapList.size();i++)
        {
            mapList.get(i).put("7",0);
            mapList.get(i).put("8",0);
            mapList.get(i).put("9",0);
            mapList.get(i).put("10",0);
            mapList.get(i).put("11",0);
            mapList.get(i).put("12",0);
            mapList.get(i).put("1",0);
            mapList.get(i).put("2",0);
            mapList.get(i).put("3",0);
            mapList.get(i).put("4",0);
            mapList.get(i).put("5",0);
        }

        display = new timeOrb("MIDLAND.DISPLAY_NG1",displayMap);
        edA = new timeOrb("MIDLAND.ED_A",edAMap);
        edB = new timeOrb("MIDLAND.ED_B",edBMap);
        posA = new timeOrb("MIDLAND.POS_A",posAMap);
        posB = new timeOrb("MIDLAND.POS_B",posBMap);
        posD = new timeOrb("MIDLAND.POS_D",posDMap);
        posE = new timeOrb("MIDLAND.POS_E",posEMap);
        quest = new timeOrb("MIDLAND.QUEST",questMap);
        t1000 = new timeOrb("MIDLAND.T1000",t1000Map);
        ng1 = new timeOrb("MIDLAND.POS_NG1",ng1Map);
        ng2 = new timeOrb("MIDLAND.POS_NG2",ng2Map);
        ng3 = new timeOrb("MIDLAND.POS_NG3",ng3Map);
        ng4 = new timeOrb("MIDLAND.POS_NG4",ng4Map);
        ng5 = new timeOrb("MIDLAND.POS_NG5",ng5Map);
        optic1 = new timeOrb("MIDLAND.OPTIC_1",optic1Map);
        optic2 = new timeOrb("MIDLAND.OPTIC_2",optic2Map);
        optic3 = new timeOrb("MIDLAND.OPTIC_3",optic3Map);
        optic4 = new timeOrb("MIDLAND.OPTIC_4",optic4Map);
        servers1 = new timeOrb("MIDLAND.SERVERS",servers1Map);
        servers2 = new timeOrb("MIDLAND.SERVERS_2",servers2Map);

        orbList.add(display);
        orbList.add(edA);
        orbList.add(edB );
        orbList.add(posA);
        orbList.add(posB);
        orbList.add(posD);
        orbList.add(posE);
        orbList.add(quest);
        orbList.add(t1000 );
        orbList.add(ng1 );
        orbList.add(ng2 );
        orbList.add(ng3 );
        orbList.add(ng4 );
        orbList.add(ng5 );
        orbList.add(optic1);
        orbList.add(optic2);
        orbList.add(optic3);
        orbList.add(optic4);
        orbList.add(servers1);
        orbList.add(servers2);

    }

    DocumentBuilderFactory dbf;
    DocumentBuilder db;
    Document document;
    NodeList nodeList;

    String product;

    public String incidentReader() throws IOException, SAXException, ParserConfigurationException
    {
        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();
        //\\\\SUSMID8000\D\Metrics Dashboard\info.xml
        document = db.parse(new File("\\\\SUSMID8000\\\\D\\\\Metrics Dashboard\\\\info2.xml"));
        //Document document = db.parse(new File("C:\\Users\\ms185594\\Documents\\tilesfx\\tilesfx\\src\\main\\resources\\info2.xml"));
        nodeList = document.getElementsByTagName("safety");

        product = nodeList.item(0).getAttributes().getNamedItem("incident").getNodeValue();

        return product;
    }


    NodeList hospNodeList;
    NodeList retailNodeList;
    NodeList periphNodeList;
    NodeList serverNodeList;
    NodeList opticNodeList;
    String hospProduct;
    String hospGoal;
    String retailProduct;
    String retailGoal;
    String periphProduct;
    String periphGoal;
    String serverProduct;
    String serverGoal;
    String opticProduct;
    String opticGoal;
    public ArrayList<HashMap<String,Integer>> documentReader() throws IOException, SAXException, ParserConfigurationException
    {
        //--------------------------------Variables for Maps------------------------------------------------
        hospMap = new HashMap<>();
        retailMap = new HashMap<>();
        serversMap = new HashMap<>();
        periphMap = new HashMap<>();
        opticMap = new HashMap<>();

        returnList = new ArrayList<>();


        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();

        document = db.parse(new File("\\\\SUSMID8000\\\\D\\\\Metrics Dashboard\\\\info2.xml"));
        //Document document = db.parse(new File("C:\\Users\\ms185594\\Documents\\tilesfx\\tilesfx\\src\\main\\resources\\info2.xml"));


        //---------------------------------Hosp Reader----------------------------------------------------
        hospNodeList = document.getElementsByTagName("hosp");

        for (int x = 0, size = hospNodeList.getLength(); x < size; x++)
        {
            hospProduct = hospNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            hospGoal = hospNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            hospMap.put(hospProduct,Integer.parseInt(hospGoal));
        }

        //---------------------------------Retail Reader----------------------------------------------------
        retailNodeList = document.getElementsByTagName("retail");

        for (int x = 0, size = retailNodeList.getLength(); x < size; x++)
        {
            retailProduct = retailNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            retailGoal = retailNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            retailMap.put(retailProduct,Integer.parseInt(retailGoal));
        }

        //---------------------------------Server Reader----------------------------------------------------
        serverNodeList = document.getElementsByTagName("server");

        for (int x = 0, size = serverNodeList.getLength(); x < size; x++)
        {
            serverProduct = serverNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            serverGoal = serverNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            serversMap.put(serverProduct,Integer.parseInt(serverGoal));
        }

        //---------------------------------Periph Reader----------------------------------------------------
        periphNodeList = document.getElementsByTagName("periph");

        for (int x = 0, size = periphNodeList.getLength(); x < size; x++)
        {
            periphProduct = periphNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            periphGoal = periphNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            periphMap.put(periphProduct,Integer.parseInt(periphGoal));
        }

        //---------------------------------Optic Reader----------------------------------------------------
        opticNodeList = document.getElementsByTagName("optic");

        for (int x = 0, size = opticNodeList.getLength(); x < size; x++)
        {
            opticProduct = opticNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            opticGoal = opticNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

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

    public ArrayList<HashMap<String,Integer>> stageDocumentReader() throws IOException, SAXException, ParserConfigurationException
    {
        //--------------------------------Variables for Maps------------------------------------------------
        hospStageMap = new HashMap<>();
        retailStageMap = new HashMap<>();
        serversStageMap = new HashMap<>();
        periphStageMap = new HashMap<>();

        returnStageList = new ArrayList<>();


        dbf = DocumentBuilderFactory.newInstance();
        db = dbf.newDocumentBuilder();

        document = db.parse(new File("\\\\SUSMID8000\\\\D\\\\Metrics Dashboard\\\\info2.xml"));
        //Document document = db.parse(new File("C:\\Users\\ms185594\\Documents\\tilesfx\\tilesfx\\src\\main\\resources\\info2.xml"));


        //---------------------------------Hosp Reader----------------------------------------------------
        hospNodeList = document.getElementsByTagName("hospStage");

        for (int x = 0, size = hospNodeList.getLength(); x < size; x++)
        {
            hospProduct = hospNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            hospGoal = hospNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            hospStageMap.put(hospProduct,Integer.parseInt(hospGoal));
        }

        //---------------------------------Retail Reader----------------------------------------------------
        retailNodeList = document.getElementsByTagName("retailStage");

        for (int x = 0, size = retailNodeList.getLength(); x < size; x++)
        {
            retailProduct = retailNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            retailGoal = retailNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            retailStageMap.put(retailProduct,Integer.parseInt(retailGoal));
        }

        //---------------------------------Server Reader----------------------------------------------------
        serverNodeList = document.getElementsByTagName("serverStage");

        for (int x = 0, size = serverNodeList.getLength(); x < size; x++)
        {
            serverProduct = serverNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            serverGoal = serverNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            serversStageMap.put(serverProduct,Integer.parseInt(serverGoal));
        }

        //---------------------------------Periph Reader----------------------------------------------------
        periphNodeList = document.getElementsByTagName("periphStage");

        for (int x = 0, size = periphNodeList.getLength(); x < size; x++)
        {
            periphProduct = periphNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            periphGoal = periphNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            periphStageMap.put(periphProduct,Integer.parseInt(periphGoal));
        }

        //---------------------------------Add Maps to Return List----------------------------------------------------

        returnStageList.add(hospStageMap);
        returnStageList.add(serversStageMap);
        returnStageList.add(periphStageMap);
        returnStageList.add(retailStageMap);

        return returnStageList;
    }


    Connection conn;

    Statement statement;
    ResultSet resultSet;
    String URL;
    String User;
    String Pass;
    String model;

    String sub;

    String query;
    String tempString;
    int tempValue;

    //---------------------------------Database Queries----------------------------------------------------------------
    public HashMap<String,Integer> buildQuery() throws SQLException
    {
        strings = new ArrayList<>();
        returnMap = new HashMap<>();

        conn = null;

        statement = null;
        resultSet = null;

        query = "SELECT * FROM [ERP].[dbo].[BuildQuery]";


        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {

                model = resultSet.getString("ItemID");

                sub = model.substring(0, model.indexOf('-'));

                strings.add(sub);
            }
        }
        catch (Exception e)
        {
            //--- do nothing ---//
        }
        finally {
            try {
                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            if (strings.isEmpty()) {
                returnMap.put("7745", 0);
                returnMap.put("7734", 0);
                returnMap.put("7743", 0);
                returnMap.put("7761", 0);
                returnMap.put("7791", 0);
                returnMap.put("7744", 0);
                returnMap.put("6001", 0);
                returnMap.put("6002", 0);
                returnMap.put("6003", 0);
                returnMap.put("5931", 0);
                returnMap.put("5933", 0);
                returnMap.put("5934", 0);
                returnMap.put("5937", 0);
                returnMap.put("5938", 0);
                returnMap.put("5943", 0);
                returnMap.put("5967", 0);
                returnMap.put("1635", 0);
                returnMap.put("1640", 0);
                returnMap.put("1641", 0);
                returnMap.put("1642", 0);
                returnMap.put("1924", 0);
                returnMap.put("1646", 0);
                returnMap.put("1650", 0);
                returnMap.put("1651", 0);
                returnMap.put("7701", 0);
                returnMap.put("7702", 0);
                returnMap.put("7703", 0);
                returnMap.put("5968", 0);
                returnMap.put("5985", 0);
                returnMap.put("1656", 0);
                returnMap.put("1930", 0);
                returnMap.put("1657", 0);
                returnMap.put("1611", 0);
                returnMap.put("1612", 0);
            } else {
                for (int i = 0; i < strings.size(); i++) {
                    tempString = strings.get(0);
                    tempValue = Collections.frequency(strings, strings.get(0));
                    strings.removeAll(Collections.singleton(strings.get(0)));
                    returnMap.put(tempString, tempValue);
                }
            }
        }
        return returnMap;
    }

    ArrayList<String> lineProduct;
    ArrayList<String> lineLoc;
    String line;

    public HashMap<String,Integer> buildLineQuery() throws ClassNotFoundException, SQLException
    {
        lineMap = new HashMap<>();

        lineProduct = new ArrayList<>();
        lineLoc = new ArrayList<>();

        conn = null;

        statement = null;
        resultSet = null;

        query = "SELECT * FROM [ERP].[dbo].[BuildQuery] ORDER BY Line";


        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                 model = resultSet.getString("ItemID");
                 line = resultSet.getString("Line");


                //String sub = model.substring(0, model.indexOf('-'));
                lineLoc.add(line);

            }
        }finally
        {
            try
            {
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            if (lineLoc.isEmpty())
            {
                lineMap.put("MIDLAND.DISPLAY_NG1",0);
                lineMap.put("MIDLAND.ED_A",0);
                lineMap.put("MIDLAND.ED_B",0);
                lineMap.put("MIDLAND.POS_A",0);
                lineMap.put("MIDLAND.POS_B",0);
                lineMap.put("MIDLAND.POS_D",0);
                lineMap.put("MIDLAND.POS_E",0);
                lineMap.put("MIDLAND.QUEST",0);
                lineMap.put("MIDLAND.T1000",0);
                lineMap.put("MIDLAND.POS_NG1",0);
                lineMap.put("MIDLAND.POS_NG2",0);
                lineMap.put("MIDLAND.POS_NG3",0);
                lineMap.put("MIDLAND.POS_NG4",0);
                lineMap.put("MIDLAND.POS_NG5",0);
                lineMap.put("MIDLAND.OPTIC_1",0);
                lineMap.put("MIDLAND.OPTIC_2",0);
                lineMap.put("MIDLAND.OPTIC_3",0);
                lineMap.put("MIDLAND.OPTIC_4",0);
                lineMap.put("MIDLAND.SERVERS",0);
                lineMap.put("MIDLAND.SERVERS_2",0);
            }
            else
            {
                for (int i = 0; i < lineLoc.size();i++)
                {
                    tempString = lineLoc.get(0);
                    tempValue = Collections.frequency(lineLoc, lineLoc.get(0));
                    lineLoc.removeAll(Collections.singleton(lineLoc.get(0)));
                    lineMap.put(tempString, tempValue);
                }
            }

        }
        return lineMap;
    }


    String time;
    String checkLine;
    public ArrayList<timeOrb> buildTimeQuery() throws ClassNotFoundException, SQLException
    {

        tableSetUp();
        conn = null;

        statement = null;
        resultSet = null;

        query = "SELECT * FROM [ERP].[dbo].[BuildQuery]\n" +
                "ORDER BY [Line],[time] ASC";


        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next())
            {
                line = resultSet.getString("Line");
                time = resultSet.getString("time");

                for(int i=0;i<orbList.size();i++)
                {
                    checkLine = orbList.get(i).getLine();
                    if(line.equalsIgnoreCase(checkLine))
                    {
                        if(time.equals("1") || time.equals("2") || time.equals("3") || time.equals("4") || time.equals("5") || time.equals("6"))
                        {
                            break;
                        }
                        if(time.equals("13"))
                        {
                            time = "1";
                        }
                        if(time.equals("14"))
                        {
                            time = "2";
                        }
                        if(time.equals("15"))
                        {
                            time = "3";
                        }
                        if(time.equals("16"))
                        {
                            time = "4";
                        }
                        if(time.equals("17"))
                        {
                            time = "5";
                        }
                        orbList.get(i).getTimeMap().put(time,orbList.get(i).getTimeMap().get(time)+1);
                        break;
                    }
                }
            }
        }finally
        {
            try
            {
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                System.out.println(e);
                e.printStackTrace();
            }
        }
        return orbList;
    }

    public HashMap<String,Integer> hospTestDatabaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT * FROM [ERP].[dbo].[TestQuery]\n" +
                "\n" +
                "WHERE Model LIKE '7734'\n" +
                "OR Model LIKE '7745'\n" +
                "OR Model LIKE '7761'\n" +
                "OR Model LIKE '7743'\n" +
                "OR Model LIKE '7744'\n" +
                "OR Model LIKE '7746'\n" +
                "OR Model LIKE '7791'\n" +
                "OR Model LIKE '7792'";

        conn = null;

        statement = null;
        resultSet = null;


        try { Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);


            while (resultSet.next()) {
                model = resultSet.getString("Model");

                user = resultSet.getString("UserID");

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {
                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }

        //returnMap);
        return returnMap;
    }

    String staging;

    //---------------------------------Hosp Test Query-------------------------------------------------------------------
    public HashMap<String,Integer> hospTestDataBase() throws ClassNotFoundException, SQLException
    {
        readMe = new ArrayList<>();
        returnMap = new HashMap<>();

        query = "SELECT DISTINCT [Assembly],[ScheduleNo]  ,[MfgPassDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                 \n" +
                "                  Where Cast(MfgPassDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                 \n" +
                "                  AND (ut.[Assembly] LIKE '7745MCC[0-9]%' \n" +
                "                 \n" +
                "                  OR ut.[Assembly] LIKE '7734MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7743MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7761MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7791MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7744MCC[0-9]%') \n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("7745",0);
            returnMap.put("7734",0);
            returnMap.put("7743",0);
            returnMap.put("7761",0);
            returnMap.put("7791",0);
            returnMap.put("7744",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    //---------------------------------Hosp Staging Query----------------------------------------------------------------
    public HashMap<String,Integer> hospStageDataBase() throws ClassNotFoundException, SQLException
    {
        readMe = new ArrayList<>();
        returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        query = "SELECT [Assembly] ,[CompletionDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                 \n" +
                "                  WHERE CompletedQuantity = '1' \n" +
                "                 \n" +
                "                  AND Cast(CompletionDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                 \n" +
                "                  AND (ut.[Assembly] LIKE '7745MC[0-9]%' \n" +
                "                 \n" +
                "                  OR ut.[Assembly] LIKE '7734MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7743MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7761MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7791MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7744MC[0-9]%') \n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("7745",0);
            returnMap.put("7734",0);
            returnMap.put("7743",0);
            returnMap.put("7761",0);
            returnMap.put("7791",0);
            returnMap.put("7744",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    String user;
    ArrayList<String> temp;
    ArrayList<String> tempList;
    ArrayList<String> users;

    public HashMap<String,Integer> hospStageDataBaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT DISTINCT UnitRQSID, u.UserID, i.ItemID \n" +
                "FROM UnitTest AS ut\n" +
                "                JOIN Unit AS unit ON unit.RQSID = ut.UnitRQSID\n" +
                "                JOIN Item AS i ON i.RQSID = unit.ItemRQSID\n" +
                "                JOIN Location AS l ON l.RQSID = ut.EntryLocationRQSID\n" +
                "                JOIN Location AS f ON f.RQSID = l.FacilityRQSID\n" +
                "                JOIN [User] AS u ON u.RQSID = ut.EntryUserRQSID\n" +
                "                WHERE cast(ut.TestDate AS DATE) >= cast(GETDATE() AS DATE)\n" +
                "\t\t\t\t--Remove Second Shift:\n" +
                "                --where cast(ut.TestDate as smalldatetime) \n" +
                "\t\t\t\t--between DATEADD(HOUR, 5, cast(cast(GETDATE() as date) as smalldatetime))\n" +
                "\t\t\t\t--and DATEADD(HOUR, 5, DATEADD(DAY, 1, cast(cast(GETDATE() as date) as smalldatetime)))\n" +
                "\t\t\t\t--where ut.TestDate > dateadd(day, datediff(day, 0, getdate()),0)\n" +
                "                AND f.LocationID = 'MIDLAND'\n" +
                "                AND ut.Type = 'Checkout'\n" +
                "\t\t\t\tAND ut.IsPass = '1'\n" +
                "\t\t\t\tAND(i.ItemID LIKE '1611-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '1612-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '7745-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '7734-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '7743-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '7761-%')\n" +
                "\n" +
                "\t\t\t\tORDER BY UserID,ItemID";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            User = "rqs_read_only";
            Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next()) {
                staging = resultSet.getString("ItemID");

                user = resultSet.getString("UserID");

                sub = staging.substring(0, staging.indexOf('-'));

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {
                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }
        return returnMap;
    }

    boolean flag;
    double percentages;
    public double hospFTTDataBase() throws ClassNotFoundException, SQLException
    {


        itemIDS = new ArrayList<>();
        count = new ArrayList<>();
        percentages = 0.0;



        query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        conn = null;

        statement = null;
        resultSet = null;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
        User = "MidlandMFG";
        Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                    model = resultSet.getString("name");
                    if(model.equals("Hospitality FTT"))
                    {
                        percentages = resultSet.getDouble("value");
                    }

            }
            try
            {
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        return percentages;

    }

    //---------------------------------Retail Test Query-----------------------------------------------------------------
    public HashMap<String,Integer> retailTestDatabaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT * FROM [ERP].[dbo].[TestQuery]\n" +
                "\n" +
                "WHERE Model LIKE '7702'\n" +
                "OR Model LIKE '7701'\n" +
                "OR Model LIKE '7703'\n" +
                "OR Model LIKE '5968'\n" +
                "OR Model LIKE '5985'\n";

        conn = null;

        statement = null;
        resultSet = null;


        try { Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next()) {
                model = resultSet.getString("Model");

                user = resultSet.getString("UserID");

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {
                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }

        //returnMap);
        return returnMap;
    }

    public HashMap<String,Integer> retailTestDataBase() throws ClassNotFoundException, SQLException
    {

        readMe = new ArrayList<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT DISTINCT [Assembly] ,[ScheduleNo] , [MfgPassDate]\n" +
                "\n" +
                "  FROM [ERP].[dbo].[MidlandERP] AS ut\n" +
                "\n" +
                "  Where Cast(MfgPassDate AS DATE) >= Cast(GetDate() AS DATE)\n" +
                "\n" +
                "  AND (ut.[Assembly] LIKE '770[0-9]MC%'\n" +
                "\n" +
                "  OR ut.[Assembly] LIKE '5968%'\n" +
                "\n" +
                "  OR ut.[Assembly] LIKE '5985%')\n" +
                "\n" +
                "  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("7701",0);
            returnMap.put("7702",0);
            returnMap.put("7703",0);
            returnMap.put("5968",0);
            returnMap.put("5985",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;

    }

    //---------------------------------Retail Stage Query----------------------------------------------------------------
    public HashMap<String,Integer> retailStageDataBase() throws ClassNotFoundException, SQLException
    {
        readMe = new ArrayList<>();
        returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        query = "SELECT [Assembly] ,[CompletionDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                 \n" +
                "                  WHERE CompletedQuantity = '1' \n" +
                "                 \n" +
                "                  AND Cast(CompletionDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                 \n" +
                "                  AND (ut.[Assembly] LIKE '7701MC[0-9]%' \n" +
                "                 \n" +
                "                  OR ut.[Assembly] LIKE '7702MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '7703MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5968MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5985MC[0-9]%') \n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));



                readMe.add(sub);
            }
        }finally
        {
            try
            {

                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("7701",0);
            returnMap.put("7702",0);
            returnMap.put("7703",0);
            returnMap.put("5968",0);
            returnMap.put("5985",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    public double retailFTTDataBase() throws ClassNotFoundException, SQLException
    {

        percentages = 0.0;



        query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        conn = null;

        statement = null;
        resultSet = null;



            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                model = resultSet.getString("name");
                if(model.equals("Retail FTT"))
                {
                    percentages = resultSet.getDouble("value");
                }

            }
            try{
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }


        return percentages;

    }

    //---------------------------------Retail User Stage Query----------------------------------------------------------------
    public HashMap<String,Integer> retailStageDataBaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT DISTINCT UnitRQSID, u.UserID, i.ItemID \n" +
                "                                \n" +
                "                                FROM UnitTest AS ut  \n" +
                "                                                JOIN Unit AS unit ON unit.RQSID = ut.UnitRQSID  \n" +
                "                                                JOIN Item AS i ON i.RQSID = unit.ItemRQSID  \n" +
                "                                                JOIN Location AS l ON l.RQSID = ut.EntryLocationRQSID  \n" +
                "                                                JOIN Location AS f ON f.RQSID = l.FacilityRQSID  \n" +
                "                                                JOIN [User] AS u ON u.RQSID = ut.EntryUserRQSID  \n" +
                "                                                WHERE cast(ut.TestDate AS DATE) >= cast(GETDATE() AS DATE)  \n" +
                "                                --Remove Second Shift:  \n" +
                "                                                --where cast(ut.TestDate as smalldatetime)   \n" +
                "                                --between DATEADD(HOUR, 5, cast(cast(GETDATE() as date) as smalldatetime))  \n" +
                "                                --and DATEADD(HOUR, 5, DATEADD(DAY, 1, cast(cast(GETDATE() as date) as smalldatetime)))  \n" +
                "                                --where ut.TestDate > dateadd(day, datediff(day, 0, getdate()),0)  \n" +
                "                                                AND f.LocationID = 'MIDLAND'  \n" +
                "                                                AND ut.Type = 'Checkout'  \n" +
                "                                AND ut.IsPass = '1'  \n" +
                "                                        AND (i.ItemID LIKE '7701-%'  \n" +
                "                                OR i.ItemID LIKE '7702-%'  \n" +
                "                                OR i.ItemID LIKE '7703-%')-- Next Gen  \n" +
                "                                  \n" +
                "                                ORDER BY UserID,ItemID";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            User = "rqs_read_only";
            Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next()) {
                staging = resultSet.getString("ItemID");

                user = resultSet.getString("UserID");

                sub = staging.substring(0, staging.indexOf('-'));

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {

                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }
        return returnMap;
    }
    //---------------------------------Periph Test Query----------------------------------------------------------------
    public HashMap<String,Integer> periphTestDatabaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT * FROM [ERP].[dbo].[TestQuery]\n" +
                "\n" +
                "WHERE Model LIKE '1635'\n" +
                "OR Model LIKE '1642'\n" +
                "OR Model LIKE '1924'\n" +
                "OR Model LIKE '1646'\n" +
                "OR Model LIKE '1651'\n";

        conn = null;

        statement = null;
        resultSet = null;


        try { Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next()) {
                model = resultSet.getString("Model");

                user = resultSet.getString("UserID");

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {

                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }

        //returnMap);
        return returnMap;
    }

    public HashMap<String,Integer> periphTestDataBase() throws ClassNotFoundException, SQLException
    {

        readMe = new ArrayList<>();
        returnMap = new HashMap<>();


        query = "SELECT DISTINCT [Assembly] ,[ScheduleNo]  ,[MfgPassDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                  Where Cast(MfgPassDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                 \n" +
                "                  AND (ut.[Assembly] LIKE '5931MCC[0-9]%' \n" +
                "                 \n" +
                "                  OR ut.[Assembly] LIKE '5933MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5934MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5937MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5938MCC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5943MCC[0-9]%' \n" +
                "                 \n" +
                "                  OR ut.[Assembly] LIKE '5967MCC[0-9]%'\n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1635MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1640MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1641MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1642MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1924MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1646MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1650MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1651MCC[0-9]%' ) \n" +
                "\n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));

                readMe.add(sub);
            }
        }finally
        {
            try
            {

                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("5931",0);
            returnMap.put("5933",0);
            returnMap.put("5934",0);
            returnMap.put("5937",0);
            returnMap.put("5938",0);
            returnMap.put("5943",0);
            returnMap.put("5967",0);
            returnMap.put("1635",0);
            returnMap.put("1640",0);
            returnMap.put("1641",0);
            returnMap.put("1642",0);
            returnMap.put("1924",0);
            returnMap.put("1646",0);
            returnMap.put("1650",0);
            returnMap.put("1651",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    //---------------------------------Periph Stage Query----------------------------------------------------------------
    public HashMap<String,Integer> periphStageDataBase() throws ClassNotFoundException, SQLException
    {

        readMe = new ArrayList<>();
        returnMap = new HashMap<>();


        query = "SELECT [Assembly] ,[CompletionDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                 \n" +
                "                  WHERE CompletedQuantity = '1' \n" +
                "                 \n" +
                "                  AND Cast(CompletionDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                 \n" +
                "                  AND (ut.[Assembly] LIKE '5931MC[0-9]%' \n" +
                "                 \n" +
                "                  OR ut.[Assembly] LIKE '5933MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5934MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5937MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5938MC[0-9]%' \n" +
                "\n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '5943MC[0-9]%' \n" +
                "                 \n" +
                "                  OR ut.[Assembly] LIKE '5967MC[0-9]%'\n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1635MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1640MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1641MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1642MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1924MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1646MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1650MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1651MC[0-9]%' ) \n" +
                "\n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));

                readMe.add(sub);
            }
        }finally
        {
            try
            {

                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("5931",0);
            returnMap.put("5933",0);
            returnMap.put("5934",0);
            returnMap.put("5937",0);
            returnMap.put("5938",0);
            returnMap.put("5943",0);
            returnMap.put("5967",0);
            returnMap.put("1635",0);
            returnMap.put("1640",0);
            returnMap.put("1641",0);
            returnMap.put("1642",0);
            returnMap.put("1924",0);
            returnMap.put("1646",0);
            returnMap.put("1650",0);
            returnMap.put("1651",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
    }

    public HashMap<String,Integer> periphStageDataBaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT DISTINCT UnitRQSID, u.UserID, i.ItemID\n" +
                "                FROM UnitTest AS ut \n" +
                "\n" +
                "                                JOIN Unit AS unit ON unit.RQSID = ut.UnitRQSID \n" +
                "                                JOIN Item AS i ON i.RQSID = unit.ItemRQSID \n" +
                "                                JOIN Location AS l ON l.RQSID = ut.EntryLocationRQSID \n" +
                "                                JOIN Location AS f ON f.RQSID = l.FacilityRQSID \n" +
                "                                JOIN [User] AS u ON u.RQSID = ut.EntryUserRQSID \n" +
                "                                WHERE cast(ut.TestDate AS DATE) >= cast(GETDATE() AS DATE) \n" +
                "                                AND f.LocationID = 'MIDLAND' \n" +
                "                                AND ut.Type = 'Checkout' \n" +
                "\n" +
                "\n" +
                "                AND ut.IsPass = '1' \n" +
                "                AND (i.ItemID LIKE '5943-%' \n" +
                "                OR i.ItemID LIKE '5967-%' \n" +
                "                OR i.ItemID LIKE '1635-%' \n" +
                "                OR i.ItemID LIKE '1640-%' \n" +
                "                OR i.ItemID LIKE '1641-%' \n" +
                "                OR i.ItemID LIKE '1642-%' \n" +
                "                OR i.ItemID LIKE '1924-%' \n" +
                "                OR i.ItemID LIKE '1646-%' \n" +
                "                OR i.ItemID LIKE '1650-%' \n" +
                "                OR i.ItemID LIKE '1651-%')\n" +
                "                 \n" +
                "                ORDER BY UserID,ItemID";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            User = "rqs_read_only";
            Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next()) {
                staging = resultSet.getString("ItemID");

                user = resultSet.getString("UserID");

                sub = staging.substring(0, staging.indexOf('-'));

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {

                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }
        return returnMap;
    }

    public double periphFTTDataBase() throws ClassNotFoundException, SQLException
    {
        itemIDS = new ArrayList<>();
        count = new ArrayList<>();
        percentages = 0.0;



        query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        conn = null;

        statement = null;
        resultSet = null;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                model = resultSet.getString("name");
                if(model.equals("Peripherals FTT"))
                {
                    percentages = resultSet.getDouble("value");
                }
            }
            try{
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        return percentages;
    }
    //---------------------------------Servers Test Query----------------------------------------------------------------
    public HashMap<String,Integer> serversTestDatabaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT * FROM [ERP].[dbo].[TestQuery]\n" +
                "\n" +
                "WHERE Model LIKE '1611'\n" +
                "OR Model LIKE '1612'\n" +
                "OR Model LIKE '1656'\n" +
                "OR Model LIKE '1657'\n";

        conn = null;

        statement = null;
        resultSet = null;


        try { Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next()) {
                model = resultSet.getString("Model");

                user = resultSet.getString("UserID");

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {

                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }

        //returnMap);
        return returnMap;
    }

    public HashMap<String,Integer> serversTestDataBase() throws ClassNotFoundException, SQLException
    {
        readMe = new ArrayList<>();
        returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        query = "SELECT DISTINCT [Assembly] ,[ScheduleNo]  ,[MfgPassDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                  Where Cast(MfgPassDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                  And(ut.[Assembly] LIKE '1656MCC[0-9]%'\n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1930MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1657MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1611MCC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1612MCC[0-9]%' ) \n" +
                "\n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {

                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("1656",0);
            returnMap.put("1930",0);
            returnMap.put("1657",0);
            returnMap.put("1611",0);
            returnMap.put("1612",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }
    //---------------------------------Servers Stage Query----------------------------------------------------------------
    public HashMap<String,Integer> serversStageDataBase() throws ClassNotFoundException, SQLException
    {
        readMe = new ArrayList<>();
        returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        query = "SELECT [Assembly] ,[CompletionDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                 \n" +
                "                  WHERE CompletedQuantity = '1' \n" +
                "                 \n" +
                "                  AND Cast(CompletionDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                  And(ut.[Assembly] LIKE '1656MC[0-9]%'\n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1930MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1657MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1611MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '1612MC[0-9]%' ) \n" +
                "\n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {

                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("1656",0);
            returnMap.put("1930",0);
            returnMap.put("1657",0);
            returnMap.put("1611",0);
            returnMap.put("1612",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
    }

    public double serversFTTDataBase() throws ClassNotFoundException, SQLException
    {


        itemIDS = new ArrayList<>();
        count = new ArrayList<>();
        percentages = 0.0;



        String query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        conn = null;

        statement = null;
        resultSet = null;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                model = resultSet.getString("name");
                if(model.equals("Server FTT"))
                {
                    percentages = resultSet.getDouble("value");
                }

            }
            try
            {
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        return percentages;
    }
    //---------------------------------Optic Test Query----------------------------------------------------------------
    public HashMap<String,Integer> opticTestDatabaseUsers() throws ClassNotFoundException, SQLException
    {
        dummyMap = new HashMap<>();
        returnMap = new HashMap<>();

        ////EDIT COLUMN

        query = "SELECT * FROM [ERP].[dbo].[TestQuery]\n" +
                "\n" +
                "WHERE Model LIKE '6001'\n" +
                "OR Model LIKE '6002'\n";

        conn = null;

        statement = null;
        resultSet = null;


        try { Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next()) {
                model = resultSet.getString("Model");

                user = resultSet.getString("UserID");

                if (dummyMap.isEmpty()) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    temp = dummyMap.get(user);
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    temp = new ArrayList<>();
                    temp.add(model);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {

                resultSet.close();
                statement.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }

        //returnMap);
        return returnMap;
    }

    public HashMap<String,Integer> opticTestDataBase() throws ClassNotFoundException, SQLException
    {
        readMe = new ArrayList<>();
        returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        query = "SELECT DISTINCT [Assembly] ,[ScheduleNo]   ,[MfgPassDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                 \n" +
                "                  Where Cast(MfgPassDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  AND (ut.[Assembly] LIKE '6002MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '6003MC[0-9]%') \n" +
                "                 \n" +
                "                  ORDER BY [Assembly] ASC";

        conn = null;

        statement = null;
        resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            User = "MidlandMFG";
            Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);



            while (resultSet.next())
            {
                staging = resultSet.getString("Assembly");

                sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {

                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        }

        if(readMe.isEmpty())
        {
            returnMap.put("6001",0);
            returnMap.put("6002",0);
            returnMap.put("6003",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                tempString = readMe.get(0);
                tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    public double opticFTTDataBase() throws ClassNotFoundException, SQLException
    {

        percentages = 0.0;

        query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        conn = null;

        statement = null;
        resultSet = null;

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
        User = "MidlandMFG";
        Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                model = resultSet.getString("name");
                if(model.equals("Optic FTT"))
                {
                    percentages = resultSet.getDouble("value");
                }

            }
            try{
                resultSet.close();
                statement.close();
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        return percentages;

    }
}

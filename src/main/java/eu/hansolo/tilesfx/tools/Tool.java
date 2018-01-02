package eu.hansolo.tilesfx.tools;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


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

    ArrayList<HashMap<String,Integer>> returnList;
    ArrayList<HashMap<String,Integer>> returnStageList;

    public String incidentReader() throws IOException, SAXException, ParserConfigurationException
    {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        //\\\\SUSMID8000\D\Metrics Dashboard\info.xml
        Document document = db.parse(new File("\\\\SUSMID8000\\\\D\\\\Metrics Dashboard\\\\info2.xml"));
        //Document document = db.parse(new File("C:\\Users\\ms185594\\Documents\\tilesfx\\tilesfx\\src\\main\\resources\\info2.xml"));
        NodeList nodeList = document.getElementsByTagName("safety");

        String product = nodeList.item(0).getAttributes().getNamedItem("incident").getNodeValue();

        return product;
    }


    public ArrayList<HashMap<String,Integer>> documentReader() throws IOException, SAXException, ParserConfigurationException
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

        Document document = db.parse(new File("\\\\SUSMID8000\\\\D\\\\Metrics Dashboard\\\\info2.xml"));
        //Document document = db.parse(new File("C:\\Users\\ms185594\\Documents\\tilesfx\\tilesfx\\src\\main\\resources\\info2.xml"));


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

        for (int x = 0, size = retailNodeList.getLength(); x < size; x++)
        {
            String retailProduct = retailNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String retailGoal = retailNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            retailMap.put(retailProduct,Integer.parseInt(retailGoal));
        }

        //---------------------------------Server Reader----------------------------------------------------
        NodeList serverNodeList = document.getElementsByTagName("server");

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

    public ArrayList<HashMap<String,Integer>> stageDocumentReader() throws IOException, SAXException, ParserConfigurationException
    {
        //--------------------------------Variables for Maps------------------------------------------------
        hospStageMap = new HashMap<>();
        retailStageMap = new HashMap<>();
        serversStageMap = new HashMap<>();
        periphStageMap = new HashMap<>();

        returnStageList = new ArrayList<>();


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document document = db.parse(new File("\\\\SUSMID8000\\\\D\\\\Metrics Dashboard\\\\info2.xml"));
        //Document document = db.parse(new File("C:\\Users\\ms185594\\Documents\\tilesfx\\tilesfx\\src\\main\\resources\\info2.xml"));


        //---------------------------------Hosp Reader----------------------------------------------------
        NodeList hospNodeList = document.getElementsByTagName("hospStage");

        for (int x = 0, size = hospNodeList.getLength(); x < size; x++)
        {
            String hospProduct = hospNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String hospGoal = hospNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            hospStageMap.put(hospProduct,Integer.parseInt(hospGoal));
        }

        //---------------------------------Retail Reader----------------------------------------------------
        NodeList retailNodeList = document.getElementsByTagName("retailStage");

        for (int x = 0, size = retailNodeList.getLength(); x < size; x++)
        {
            String retailProduct = retailNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String retailGoal = retailNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            retailStageMap.put(retailProduct,Integer.parseInt(retailGoal));
        }

        //---------------------------------Server Reader----------------------------------------------------
        NodeList serverNodeList = document.getElementsByTagName("serverStage");

        for (int x = 0, size = serverNodeList.getLength(); x < size; x++)
        {
            String serverProduct = serverNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String serverGoal = serverNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            serversStageMap.put(serverProduct,Integer.parseInt(serverGoal));
        }

        //---------------------------------Periph Reader----------------------------------------------------
        NodeList periphNodeList = document.getElementsByTagName("periphStage");

        for (int x = 0, size = periphNodeList.getLength(); x < size; x++)
        {
            String periphProduct = periphNodeList.item(x).getAttributes().getNamedItem("model").getNodeValue();
            String periphGoal = periphNodeList.item(x).getAttributes().getNamedItem("goal").getNodeValue();

            periphStageMap.put(periphProduct,Integer.parseInt(periphGoal));
        }

        //---------------------------------Add Maps to Return List----------------------------------------------------

        returnStageList.add(hospStageMap);
        returnStageList.add(serversStageMap);
        returnStageList.add(periphStageMap);
        returnStageList.add(retailStageMap);

        return returnStageList;
    }


    //---------------------------------Database Queries----------------------------------------------------------------



    //---------------------------------Hosp Build Query----------------------------------------------------------------
    public HashMap<String,Integer> hospBuildDataBase() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        ArrayList<String> strings = new ArrayList<>();

        HashMap<String,Integer> returnMap = new HashMap<>();



        String query = "SELECT DISTINCT Item.ItemID, Parent.Serial\n" +
                "\n" +
                "From Unit as Parent\n" +
                "\n" +
                "JOIN Unit as Child on Parent.RQSID = Child.ParentRQSID\n" +
                "JOIN Item on Item.RQSID = Parent.ItemRQSID\n" +
                "JOIN Item as ChildItem on ChildItem.RQSID = Child.ItemRQSID\n" +
                "Join Location as LineLocation on LineLocation.RQSID = Child.EntryLocationRQSID\n" +
                "\n" +
                "WHERE Cast(child.CreateDate as Date) >= Cast(GetDate() as Date)\n" +
                "\n" +
                "AND LineLocation.FacilityRQSID = '3421'\n" +
                "AND (Item.ItemID Like '7734%' or Item.ItemID LIKE '774[3-5]%' or Item.ItemID Like '7761%' or Item.ItemID Like '7791%' or Item.ItemID Like '7792%')\n" +
                "\n" +
                "\tORDER BY ItemID";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");
                while (resultSet.next()) {

                    String model = resultSet.getString("ItemID");

                    String sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);

                }
        }finally{
            try{
                System.out.println("Closing connection");
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(strings.isEmpty())
        {
            returnMap.put("7745",0);
            returnMap.put("7734",0);
            returnMap.put("7743",0);
            returnMap.put("7761",0);
            returnMap.put("7791",0);
            returnMap.put("7744",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(0);
                int tempValue = Collections.frequency(strings, strings.get(0));
                strings.removeAll(Collections.singleton(strings.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
    }

    //---------------------------------Hosp Test Query-------------------------------------------------------------------
    public HashMap<String,Integer> hospTestDataBase() throws ClassNotFoundException, SQLException
    {
        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        String query = "SELECT [Assembly] ,[MfgPassDate] \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    //---------------------------------Hosp Staging Query----------------------------------------------------------------
    public HashMap<String,Integer> hospStageDataBase() throws ClassNotFoundException, SQLException
    {
        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        String query = "SELECT [Assembly] ,[CompletionDate] \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    public HashMap<String,Integer> hospStageDataBaseUsers() throws ClassNotFoundException, SQLException
    {
        HashMap<String, ArrayList<String>> dummyMap = new HashMap<>();
        HashMap<String, Integer> returnMap = new HashMap<>();

        ////EDIT COLUMN

        String query = "SELECT DISTINCT UnitRQSID, u.UserID, i.ItemID, \n" +
                "\t\t\t\tdatepart(HH, TestDate)+1 AS TestDate\n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next()) {
                String staging = resultSet.getString("ItemID");

                String user = resultSet.getString("UserID");

                String sub = staging.substring(0, staging.indexOf('-'));

                if (dummyMap.isEmpty()) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    ArrayList<String> temp = dummyMap.get(user);
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {
                System.out.println("Closing connection");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            ArrayList<String> users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                ArrayList<String> tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                int tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }
        return returnMap;
    }

    public double hospFTTDataBase() throws ClassNotFoundException, SQLException
    {


        ArrayList<String> itemIDS = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        double percentages = 0.0;
        boolean flag;



        String query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                    String model = resultSet.getString("name");
                    if(model.equals("HospitalityFTT"))
                    {
                        percentages = resultSet.getDouble("value");
                    }

            }
            try{
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        return percentages;

    }

    //---------------------------------Retail Build Query----------------------------------------------------------------
    public HashMap<String,Integer> retailBuildDataBase() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        ArrayList<String> strings = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();


        String query ="SELECT DISTINCT Item.ItemID, Parent.Serial\n" +
                "\n" +
                "FROM Unit AS Parent\n" +
                "\n" +
                "JOIN Unit AS Child ON Parent.RQSID = Child.ParentRQSID\n" +
                "JOIN Item ON Item.RQSID = Parent.ItemRQSID\n" +
                "JOIN Item AS ChildItem ON ChildItem.RQSID = Child.ItemRQSID\n" +
                "JOIN Location AS LineLocation ON LineLocation.RQSID = Child.EntryLocationRQSID\n" +
                "\n" +
                "WHERE Cast(child.CreateDate AS DATE) >= Cast(GetDate() AS DATE)\n" +
                "\n" +
                "AND LineLocation.FacilityRQSID = '3421'\n" +
                "AND (Item.ItemID LIKE '770[1-3]%'\n" +
                "\tOR Item.ItemID LIKE '59[6-8][5-8]%')\n" +
                "\n" +
                "\tORDER BY ItemID";


        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        int counter = 0;
        int currentIndexofStringKey = 0;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

                while (resultSet.next()) {

                    String model = resultSet.getString("ItemID");

                    String sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
        }finally{
            try{
                System.out.println("Closing connection");
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(strings.isEmpty())
        {
            returnMap.put("7701",0);
            returnMap.put("7702",0);
            returnMap.put("7703",0);
            returnMap.put("5968",0);
            returnMap.put("5985",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(0);
                int tempValue = Collections.frequency(strings, strings.get(0));
                strings.removeAll(Collections.singleton(strings.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    //---------------------------------Retail Test Query-----------------------------------------------------------------
    public HashMap<String,Integer> retailTestDataBase() throws ClassNotFoundException, SQLException
    {

        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();

        ////EDIT COLUMN

        String query = "SELECT [Assembly] ,[MfgPassDate]\n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;

    }

    //---------------------------------Retail Stage Query----------------------------------------------------------------
    public HashMap<String,Integer> retailStageDataBase() throws ClassNotFoundException, SQLException
    {
        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        String query = "SELECT [Assembly] ,[CompletionDate] \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));



                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    public double retailFTTDataBase() throws ClassNotFoundException, SQLException
    {

        double percentages = 0.0;
        boolean flag;



        String query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;



            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                String model = resultSet.getString("name");
                if(model.equals("RetailFTT"))
                {
                    percentages = resultSet.getDouble("value");
                }

            }
            try{
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
        HashMap<String, ArrayList<String>> dummyMap = new HashMap<>();
        HashMap<String, Integer> returnMap = new HashMap<>();

        ////EDIT COLUMN

        String query = "SELECT DISTINCT UnitRQSID, u.UserID, i.ItemID, \n" +
                "                                datepart(HH, TestDate)+1 AS TestDate  \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next()) {
                String staging = resultSet.getString("ItemID");

                String user = resultSet.getString("UserID");

                String sub = staging.substring(0, staging.indexOf('-'));

                if (dummyMap.isEmpty()) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    ArrayList<String> temp = dummyMap.get(user);
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {
                System.out.println("Closing connection");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            ArrayList<String> users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                ArrayList<String> tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                int tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }
        return returnMap;
    }


    //---------------------------------Periph Build Query----------------------------------------------------------------
    public HashMap<String,Integer> periphBuildDataBase() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        ArrayList<String> strings = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();


        String query = "SELECT DISTINCT Item.ItemID, Parent.Serial" +
                "\n"+
                "From Unit as Parent\n" +
                "\n" +
                "JOIN Unit as Child on Parent.RQSID = Child.ParentRQSID\n" +
                "JOIN Item on Item.RQSID = Parent.ItemRQSID\n" +
                "JOIN Item as ChildItem on ChildItem.RQSID = Child.ItemRQSID\n" +
                "Join Location as LineLocation on LineLocation.RQSID = Child.EntryLocationRQSID\n" +
                "\n" +
                "WHERE Cast(child.CreateDate as Date) >= Cast(GetDate() as Date)\n" +
                "\n" +
                "AND LineLocation.FacilityRQSID = '3421'\n" +
                "                and (Item.ItemID like '5938%' or Item.ItemID like '5943%' or Item.ItemID like '5967%' or Item.ItemID like '1635%' or Item.ItemID like '1640%' or Item.ItemID like '1641%' \n" +
                "                or Item.ItemID like '1642%' or Item.ItemID like '1924%' or childitem.ItemID = '497-0510398') \n" +
                "\n" +
                "\t\t\t\t--case \n" +
                "\t\t\t\t--when ItemID like '7752%' or ItemID like '7753%' or ItemID like '7754%' or ItemID like '7756%' \n" +
                "\t\t\t\t--then \n" +
                "                 \n" +
                "                --Only return POS \n" +
                "                --and (Item.ItemID like '77%-0000' \n" +
                "                --or Item.ItemID like '7743-8037-8801') \n" +
                "                 \n" +
                "                Order by ItemID asc";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        int counter = 0;
        int currentIndexofStringKey = 0;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");
                while (resultSet.next()) {

                    String model = resultSet.getString("ItemID");

                    String sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
        }finally{
            try{
                System.out.println("Closing connection");
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(strings.isEmpty())
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
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(0);
                int tempValue = Collections.frequency(strings, strings.get(0));
                strings.removeAll(Collections.singleton(strings.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    //---------------------------------Periph Test Query----------------------------------------------------------------
    public HashMap<String,Integer> periphTestDataBase() throws ClassNotFoundException, SQLException
    {

        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();


        String query = "SELECT [Assembly] ,[MfgPassDate] \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));

                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    //---------------------------------Periph Stage Query----------------------------------------------------------------
    public HashMap<String,Integer> periphStageDataBase() throws ClassNotFoundException, SQLException
    {

        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();


        String query = "SELECT [Assembly] ,[CompletionDate] \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));

                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
    }

    public HashMap<String,Integer> periphStageDataBaseUsers() throws ClassNotFoundException, SQLException
    {
        HashMap<String, ArrayList<String>> dummyMap = new HashMap<>();
        HashMap<String, Integer> returnMap = new HashMap<>();

        ////EDIT COLUMN

        String query = "SELECT DISTINCT UnitRQSID, u.UserID, i.ItemID,TestDate\n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next()) {
                String staging = resultSet.getString("ItemID");

                String user = resultSet.getString("UserID");

                String sub = staging.substring(0, staging.indexOf('-'));

                if (dummyMap.isEmpty()) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    ArrayList<String> temp = dummyMap.get(user);
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {
                System.out.println("Closing connection");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            ArrayList<String> users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                ArrayList<String> tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                int tempValue = 0;

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


        ArrayList<String> itemIDS = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        double percentages = 0.0;
        boolean flag;



        String query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                String model = resultSet.getString("name");
                if(model.equals("PeripheralsFTT"))
                {
                    percentages = resultSet.getDouble("value");
                }
            }
            try{
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }

        return percentages;
    }

    //---------------------------------Servers Build Query----------------------------------------------------------------
    public HashMap<String,Integer> serversBuildDataBase() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        ArrayList<String> strings = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();


        String query ="SELECT DISTINCT Item.ItemID, Parent.Serial"+
                "\n" +
                "From Unit as Parent\n" +
                "\n" +
                "JOIN Unit as Child on Parent.RQSID = Child.ParentRQSID\n" +
                "JOIN Item on Item.RQSID = Parent.ItemRQSID\n" +
                "JOIN Item as ChildItem on ChildItem.RQSID = Child.ItemRQSID\n" +
                "Join Location as LineLocation on LineLocation.RQSID = Child.EntryLocationRQSID\n" +
                "\n" +
                "WHERE Cast(child.CreateDate as Date) >= Cast(GetDate() as Date)\n" +
                "\n" +
                "AND LineLocation.FacilityRQSID = '3421'\n" +
                "                AND (Item.ItemID like '1611%' OR Item.ItemID like '1612%' OR Item.ItemID like '1656%' OR Item.ItemID like '1657%' OR Item.ItemID like '1930%') \n" +
                "                 \n" +
                "                --Only return POS \n" +
                "                --and (Item.ItemID like '77%-0000' \n" +
                "                --or Item.ItemID like '7743-8037-8801') \n" +
                "                 \n" +
                "                ORDER BY ItemID ASC";


        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;



        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");
                while (resultSet.next())
                {

                    String model = resultSet.getString("ItemID");

                    String sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
        }finally{
            try{
                System.out.println("Closing connection");
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(strings.isEmpty())
        {
            returnMap.put("1656",0);
            returnMap.put("1930",0);
            returnMap.put("1657",0);
            returnMap.put("1611",0);
            returnMap.put("1612",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(0);
                int tempValue = Collections.frequency(strings, strings.get(0));
                strings.removeAll(Collections.singleton(strings.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
    }

    //---------------------------------Servers Test Query----------------------------------------------------------------
    public HashMap<String,Integer> serversTestDataBase() throws ClassNotFoundException, SQLException
    {
        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        String query = "SELECT [Assembly] ,[MfgPassDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                  Where Cast(MfgPassDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                  And(ut.[Assembly] LIKE '1656MC[0-9]%'\n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }
    //---------------------------------Servers Stage Query----------------------------------------------------------------
    public HashMap<String,Integer> serversStageDataBase() throws ClassNotFoundException, SQLException
    {
        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        String query = "SELECT [Assembly] ,[CompletionDate] \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
    }

    public HashMap<String,Integer> serverStageDataBaseUsers() throws ClassNotFoundException, SQLException
    {
        HashMap<String, ArrayList<String>> dummyMap = new HashMap<>();
        HashMap<String, Integer> returnMap = new HashMap<>();

        ////EDIT COLUMN

        String query = "SELECT DISTINCT UnitRQSID, u.UserID, i.ItemID, \n" +
                "\t\t\t\tdatepart(HH, TestDate)+1 AS TestDate\n" +
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
                "\t\t\t\tAND (i.ItemID LIKE '1656-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '1930-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '1657-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '1611-%'\n" +
                "\t\t\t\tOR i.ItemID LIKE '1612-%')\n" +
                "\n" +
                "\t\t\t\tORDER BY UserID,ItemID";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next()) {
                String staging = resultSet.getString("ItemID");

                String user = resultSet.getString("UserID");

                String sub = staging.substring(0, staging.indexOf('-'));

                if (dummyMap.isEmpty()) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (dummyMap.containsKey(user)) {
                    ArrayList<String> temp = dummyMap.get(user);
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
                if (!dummyMap.containsKey(user)) {
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(sub);
                    dummyMap.put(user, temp);
                }
            }
        } finally {
            try {
                System.out.println("Closing connection");
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }


        if (dummyMap.isEmpty()) {
            returnMap.put("", 0);
        } else {
            ArrayList<String> users = new ArrayList<>(dummyMap.keySet());

            for (int i = 0; i < users.size(); i++) {
                ArrayList<String> tempList = new ArrayList<>(dummyMap.get(users.get(i)));

                int tempValue = 0;

                for (int x = 0; x < tempList.size(); x++) {
                    tempValue = tempValue + Collections.frequency(tempList, tempList.get(0));
                    tempList.removeAll(Collections.singleton(tempList.get(0)));
                }
                returnMap.put(users.get(i), tempValue);
            }
        }
        return returnMap;
    }

    public double serversFTTDataBase() throws ClassNotFoundException, SQLException
    {


        ArrayList<String> itemIDS = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        double percentages = 0.0;
        boolean flag;



        String query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                String model = resultSet.getString("name");
                if(model.equals("HospitalityFTT"))
                {
                    percentages = resultSet.getDouble("value");
                }

            }
            try{
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        return percentages;
    }

    //---------------------------------Optic Build Query----------------------------------------------------------------
    public HashMap<String,Integer> opticBuildDataBase() throws SQLException, IllegalAccessException, InstantiationException, ClassNotFoundException {

        ArrayList<String> strings = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();



        String query = "SELECT DISTINCT Item.ItemID, Parent.Serial\n" +
                "\n" +
                "FROM Unit AS Parent\n" +
                "\n" +
                "JOIN Unit AS Child ON Parent.RQSID = Child.ParentRQSID\n" +
                "JOIN Item ON Item.RQSID = Parent.ItemRQSID\n" +
                "JOIN Item AS childitem ON childitem.RQSID = child.ItemRQSID\n" +
                "JOIN Location AS LineLoc ON LineLoc.RQSID = Child.EntryLocationRQSID\n" +
                "\n" +
                "\n" +
                "\n" +
                "--Only returns units that were done today\n" +
                "WHERE Cast(Child.CreateDate AS DATE) >= Cast(GetDate() AS DATE)\n" +
                "\n" +
                "AND LineLoc.FacilityRQSID = '3421'\n" +
                "--Optic 5\n" +
                "AND (Item.ItemID LIKE '6001%' \n" +
                "--Optic 12\n" +
                "OR Item.ItemID LIKE '6002%'" +
                "" +
                "OR Item.ItemID LIKE '6003%')\n" +
                "                 \n" +
                "                \n" +
                "ORDER BY ItemID ASC";


        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

                while (resultSet.next())
                {

                    String model = resultSet.getString("ItemID");

                    String sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
        }finally{
            try{
                System.out.println("Closing connection");
                conn.close();
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }

        if(strings.isEmpty())
        {
            returnMap.put("6001",0);
            returnMap.put("6002",0);
            returnMap.put("6003",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(0);
                int tempValue = Collections.frequency(strings, strings.get(0));
                strings.removeAll(Collections.singleton(strings.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    //---------------------------------Optic Test Query----------------------------------------------------------------
    public HashMap<String,Integer> opticTestDataBase() throws ClassNotFoundException, SQLException
    {
        ArrayList<String> readMe = new ArrayList<>();
        HashMap<String,Integer> returnMap = new HashMap<>();

        ////EDIT THE COLUMN NAME

        String query = "SELECT [Assembly] ,[MfgPassDate] \n" +
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

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://WUSMS185594-8PO\\SUSMID8001;database=ERP;encrypt=false";
            String User = "MidlandMFG";
            String Pass = "Midland";
            conn = DriverManager.getConnection(URL, User, Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            System.out.println("Connection established.");

            while (resultSet.next())
            {
                String staging = resultSet.getString("Assembly");

                String sub = staging.substring(0, staging.indexOf('M'));


                readMe.add(sub);
            }
        }finally
        {
            try
            {
                System.out.println("Closing connection");
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
                String tempString = readMe.get(0);
                int tempValue = Collections.frequency(readMe, readMe.get(0));
                readMe.removeAll(Collections.singleton(readMe.get(0)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }

    public double opticFTTDataBase() throws ClassNotFoundException, SQLException
    {


        ArrayList<String> itemIDS = new ArrayList<>();
        ArrayList<Integer> count = new ArrayList<>();
        double percentages = 0.0;
        boolean flag;



        String query ="SELECT * FROM [ERP].[dbo].[QueryService]";

        Connection conn = null;

        Statement statement = null;
        ResultSet resultSet = null;

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://SUSDAY5277\\RQS_ODS;database=RQS;encrypt=false";
            String User = "rqs_read_only";
            String Pass = "rqsr3qadonly";
            conn = DriverManager.getConnection(URL,User,Pass);

            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next())
            {
                String model = resultSet.getString("name");
                if(model.equals("HospitalityFTT"))
                {
                    percentages = resultSet.getDouble("value");
                }

            }
            try{
                conn.close();
                System.out.println("Closing connection");
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        return percentages;

    }
}

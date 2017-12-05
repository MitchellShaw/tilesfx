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

    ArrayList<HashMap<String,Integer>> returnList;


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
                "AND (Item.ItemID Like '7734-8___-0000' or Item.ItemID LIKE '774[3-5]-8___-0000' or Item.ItemID Like '7761-8___-0000' or Item.ItemID Like '7791-8___-0000')\n" +
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

            if (!resultSet.next()) {
                //do something
                System.out.println("Nothing to see here.");

            } else {
                String model = resultSet.getString("ItemID");

                String sub = model.substring(0, model.indexOf('-'));

                strings.add(sub);

                while (resultSet.next()) {

                    model = resultSet.getString("ItemID");

                    sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
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
            System.out.println("Return Map is Empty.");
            returnMap.put("7745",0);
            returnMap.put("7734",0);
            returnMap.put("7743",0);
            returnMap.put("7761",0);
            returnMap.put("7791",0);
            returnMap.put("7744",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(i);
                int tempValue = Collections.frequency(strings, strings.get(i));
                strings.removeAll(Collections.singleton(strings.get(i)));
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
            System.out.println("Return Map is Empty.");
            returnMap.put("7745",0);
            returnMap.put("7734",0);
            returnMap.put("7743",0);
            returnMap.put("7761",0);
            returnMap.put("7791",0);
            returnMap.put("7744",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
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
            System.out.println("Return Map is Empty.");
            returnMap.put("7745",0);
            returnMap.put("7734",0);
            returnMap.put("7743",0);
            returnMap.put("7761",0);
            returnMap.put("7791",0);
            returnMap.put("7744",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
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

            if (!resultSet.next()) {
                //do something
                System.out.println("Nothing to see here.");

            } else {
                String model = resultSet.getString("ItemID");

                String sub = model.substring(0, model.indexOf('-'));

                strings.add(sub);
                while (resultSet.next()) {

                    model = resultSet.getString("ItemID");

                    sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
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
            System.out.println("Return Map is Empty.");
            returnMap.put("7701",0);
            returnMap.put("7702",0);
            returnMap.put("7703",0);
            returnMap.put("5968",0);
            returnMap.put("5985",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(i);
                int tempValue = Collections.frequency(strings, strings.get(i));
                strings.removeAll(Collections.singleton(strings.get(i)));
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

        String query = "SELECT [Assembly] ,[CompletionDate]\n" +
                "\n" +
                "  FROM [ERP].[dbo].[MidlandERP] AS ut\n" +
                "\n" +
                "  WHERE CompletedQuantity = '1'\n" +
                "\n" +
                "  AND Cast(CompletionDate AS DATE) >= Cast(GetDate() AS DATE)\n" +
                "\n" +
                "  AND (ut.[Assembly] LIKE '770[0-9]MC%'\n" +
                "\n" +
                "  OR ut.[Assembly] LIKE '5968MC[0-9]%'\n" +
                "\n" +
                "  OR ut.[Assembly] LIKE '5985MC[0-9]%')\n" +
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
            System.out.println("Return Map is Empty.");
            returnMap.put("7701",0);
            returnMap.put("7702",0);
            returnMap.put("7703",0);
            returnMap.put("5968",0);
            returnMap.put("5985",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
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

        ////EDIT COLUMN

        String query = "SELECT [Assembly] ,[CompletionDate]\n" +
                "\n" +
                "  FROM [ERP].[dbo].[MidlandERP] AS ut\n" +
                "\n" +
                "  WHERE CompletedQuantity = '1'\n" +
                "\n" +
                "  AND Cast(CompletionDate AS DATE) >= Cast(GetDate() AS DATE)\n" +
                "\n" +
                "  AND (ut.[Assembly] LIKE '770[0-9]MC%'\n" +
                "\n" +
                "  OR ut.[Assembly] LIKE '5968MC[0-9]%'\n" +
                "\n" +
                "  OR ut.[Assembly] LIKE '5985MC[0-9]%')\n" +
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
            System.out.println("Return Map is Empty.");
            returnMap.put("7701",0);
            returnMap.put("7702",0);
            returnMap.put("7703",0);
            returnMap.put("5968",0);
            returnMap.put("5985",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
                returnMap.put(tempString, tempValue);
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
                "                and (Item.ItemID like '5938-8___-0000' or Item.ItemID like '5943-8___-0000' or Item.ItemID like '5967-8___-0000' or Item.ItemID like '1635-8___-0000' or Item.ItemID like '1640-8___-0000' or Item.ItemID like '1641-8___-0000' \n" +
                "                or Item.ItemID like '1642-8___-0000' or Item.ItemID like '1924-8___-0000' or childitem.ItemID = '497-0510398') \n" +
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

            if (!resultSet.next()) {
                //do something
                System.out.println("Nothing to see here.");

            } else {
                String model = resultSet.getString("ItemID");

                String sub = model.substring(0, model.indexOf('-'));

                strings.add(sub);
                while (resultSet.next()) {

                    model = resultSet.getString("ItemID");

                    sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
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
            System.out.println("Return Map is Empty.");
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
                String tempString = strings.get(i);
                int tempValue = Collections.frequency(strings, strings.get(i));
                strings.removeAll(Collections.singleton(strings.get(i)));
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
            System.out.println("Return Map is Empty.");
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
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
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
            System.out.println("Return Map is Empty.");
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
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
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

            if (!resultSet.next()) {
                //do something
                System.out.println("Nothing to see here.");

            } else {
                String model = resultSet.getString("ItemID");

                String sub = model.substring(0, model.indexOf('-'));

                strings.add(sub);
                while (resultSet.next()) {

                    model = resultSet.getString("ItemID");

                    sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
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
            System.out.println("Return Map is Empty.");
            returnMap.put("1656",0);
            returnMap.put("1930",0);
            returnMap.put("1657",0);
            returnMap.put("1611",0);
            returnMap.put("1612",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(i);
                int tempValue = Collections.frequency(strings, strings.get(i));
                strings.removeAll(Collections.singleton(strings.get(i)));
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
            System.out.println("Return Map is Empty.");
            returnMap.put("1656",0);
            returnMap.put("1930",0);
            returnMap.put("1657",0);
            returnMap.put("1611",0);
            returnMap.put("1612",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
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
            System.out.println("Return Map is Empty.");
            returnMap.put("1656",0);
            returnMap.put("1930",0);
            returnMap.put("1657",0);
            returnMap.put("1611",0);
            returnMap.put("1612",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
                returnMap.put(tempString, tempValue);
            }
        }


        return returnMap;
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
                "OR Item.ItemID LIKE '6002%')\n" +
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

            if (!resultSet.next()) {
                //do something
                System.out.println("Nothing to see here.");

            } else {
                String model = resultSet.getString("ItemID");

                String sub = model.substring(0, model.indexOf('-'));

                strings.add(sub);
                while (resultSet.next()) {

                    model = resultSet.getString("ItemID");

                    sub = model.substring(0, model.indexOf('-'));

                    strings.add(sub);
                }
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
            System.out.println("Return Map is Empty.");
            returnMap.put("6001",0);
            returnMap.put("6002",0);
            returnMap.put("6003",0);
        }else {
            for (int i = 0; i < strings.size(); i++) {
                String tempString = strings.get(i);
                int tempValue = Collections.frequency(strings, strings.get(i));
                strings.removeAll(Collections.singleton(strings.get(i)));
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

        String query = "SELECT [Assembly] ,[CompletionDate] \n" +
                "                 \n" +
                "                  FROM [ERP].[dbo].[MidlandERP] AS ut \n" +
                "                 \n" +
                "                  WHERE CompletedQuantity = '1' \n" +
                "                 \n" +
                "                  AND Cast(CompletionDate AS DATE) >= Cast(GetDate() AS DATE) \n" +
                "                  And(ut.[Assembly] LIKE '1656MC[0-9]%'\n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '6001MC[0-9]%' \n" +
                "\t\t\t\t  \n" +
                "\t\t\t\t  OR ut.[Assembly] LIKE '6002MC[0-9]%' \n" +
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
            System.out.println("Return map is empty.");
            returnMap.put("6001",0);
            returnMap.put("6002",0);
            returnMap.put("6003",0);
        }else {
            for (int i = 0; i < readMe.size(); i++) {
                String tempString = readMe.get(i);
                int tempValue = Collections.frequency(readMe, readMe.get(i));
                readMe.removeAll(Collections.singleton(readMe.get(i)));
                returnMap.put(tempString, tempValue);
            }
        }

        return returnMap;
    }
}

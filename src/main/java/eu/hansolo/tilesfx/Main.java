package eu.hansolo.tilesfx;

import eu.hansolo.medusa.*;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * Created by Ramon Johnson & Mitchell Shaw
 * 2017-11-22.
 */
public class Main extends Application
{
    Tile pos;
    Tile servers;
    Tile peripherals;
    Tile optic;
    Tile retail;
    Tile misc;

    //---------------------------------Variables for Query Data (POS)-----------------------------------------
    double p1x30Current;
    double p1x30Goal;
    double p1x35Current;
    double p1x35Goal;
    double p1532Current;
    double p1532Goal;
    double p1520Current;
    double p1520Goal;

    //---------------------------------Variables for Query Data (Retail)-----------------------------------------
    double xr7Current;
    double xr7Goal;
    double xr7PlusCurrent;
    double xr7PlusGoal;
    double xr5Current;
    double xr5Goal;
    double nextGenDisplayCurrent;
    double nextGenDisplayGoals;

    //---------------------------------Variables for Query Data (Servers)-----------------------------------------
    double serversCurrent;
    double serversGoal;
    double mediaPlayerCurrent;
    double mediaPlayerGoal;
    double t1000sCurrent;
    double t1000sGoal;
    double questsCurrent;
    double questsGoal;

    //---------------------------------Variables for Query Data (Peripherals)-------------------------------------
    double kiwi4sCurrent;
    double kiwi4sGoal;
    double kiwi25sCurrent;
    double kiwi25sGoal;
    double bumpBarsCurrent;
    double bumpBarsGoal;
    double pantherEPC4sCurrent;
    double pantherEPC4sGoal;

    //---------------------------------Variables for Query Data (Optic)--------------------------------------------
    double optic5sCurrent;
    double optic5sGoal;
    double optic12sCurrent;
    double optic12sGoal;
    double cubCurrent;
    double cubGoal;
    double printerCurrent;
    double printerGoal;

    //---------------------------------Variables for Map Creation for POS Database Call----------------------------
    HashMap<String,Integer> posBuildMap;
    HashMap<String,Integer> posTestMap;
    HashMap<String,Integer> posStageMap;

    //---------------------------------Variables for Map Creation for Retail Database Call-------------------------
    HashMap<String,Integer> retailBuildMap;
    HashMap<String,Integer> retailTestMap;
    HashMap<String,Integer> retailStageMap;

    //---------------------------------Variables for Map Creation for Servers Database Call------------------------
    HashMap<String,Integer> serversBuildMap;
    HashMap<String,Integer> serversTestMap;
    HashMap<String,Integer> serversStageMap;

    //---------------------------------Variables for Map Creation for Peripherals Database Call--------------------
    HashMap<String,Integer> periphBuildMap;
    HashMap<String,Integer> periphTestMap;
    HashMap<String,Integer> periphStageMap;

    //---------------------------------Variables for Map Creation for Optic Database Call----------------------------
    HashMap<String,Integer> opticBuildMap;
    HashMap<String,Integer> opticTestMap;

    //---------------------------------Variables for Map Creation for Document Reader---------------------------------
    ArrayList<HashMap<String,Integer>> mapList;

    public static void main(String[] args)
    {
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     * <p>
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded in
     *                     the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //---------------------------------Variables for Tiles----------------------------------------------------
        FlowPane flowPane = new FlowPane();


        //---------------------------------Scheduled Executors for Updating Variables---------------------------------
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(24);
        Tool dataBaseTool = new Tool();


        //---------------------------------Scheduled Executors for Build Variables-------------------------------------
        final CountDownLatch buildLatch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() ->
        {
            try
            {
                posBuildMap = dataBaseTool.hospBuildDataBase();
                retailBuildMap = dataBaseTool.retailBuildDataBase();
                serversBuildMap = dataBaseTool.serversBuildDataBase();
                periphBuildMap = dataBaseTool.periphBuildDataBase();
                opticBuildMap = dataBaseTool.opticBuildDataBase();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }, 0, 0, TimeUnit.SECONDS);
        buildLatch.await();

        //---------------------------------Scheduled Executors for Test Variables-------------------------------------
        final CountDownLatch testLatch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() ->
        {
            try
            {
                posTestMap = dataBaseTool.hospTestDataBase();
                retailTestMap = dataBaseTool.retailTestDataBase();
                serversTestMap = dataBaseTool.serversTestDataBase();
                periphTestMap = dataBaseTool.periphTestDataBase();
                opticTestMap = dataBaseTool.opticTestDataBase();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }, 0, 0, TimeUnit.SECONDS);
        testLatch.await();

        //---------------------------------Scheduled Executors for Stage Variables------------------------------------
        final CountDownLatch stageLatch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() ->
        {
            try
            {
                posStageMap = dataBaseTool.hospStageDataBase();
                retailStageMap = dataBaseTool.retailStageDataBase();
                serversStageMap = dataBaseTool.serversStageDataBase();
                periphStageMap = dataBaseTool.periphStageDataBase();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }, 0, 0, TimeUnit.SECONDS);
        stageLatch.await();

        //---------------------------------Scheduled Executors for Document Variables------------------------------------
        final CountDownLatch documentLatch = new CountDownLatch(1);
        executorService.scheduleAtFixedRate(() ->
        {
            try {
                mapList = dataBaseTool.documentReader("hosp","retail","servers","periph","optic");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            }

        }, 0, 0, TimeUnit.SECONDS);
        documentLatch.await();




        //---------------------------------Creating the Chart Data for the graphs-------------------------------------


        //---------------------------------Creating the Bar Chart Items for POS---------------------------------------
        BarChartItem p1x35Data = new BarChartItem("P1X35", 0, Tile.RED);
        BarChartItem p1x35DataGoal = new BarChartItem("P1X35 Goal", 100, Tile.RED);


        BarChartItem p132Data = new BarChartItem("P1532", 0, Tile.BLUE);
        BarChartItem p132DataGoal = new BarChartItem("P1532 Goal", 100, Tile.BLUE);


        BarChartItem p1x30Data = new BarChartItem("P1X30", 0, Tile.GREEN);
        BarChartItem p1x30DataGoal = new BarChartItem("P1X30 Goal", 100, Tile.GREEN);


        BarChartItem p1520Data = new BarChartItem("1520's", 0, Tile.YELLOW);
        BarChartItem p1520DataGoal = new BarChartItem("1520's Goal", 100, Tile.YELLOW);


        //---------------------------------Creating the Bar Chart Items for Servers---------------------------------
        BarChartItem serverData = new BarChartItem("161X", 0, Tile.RED);
        BarChartItem serverGoal = new BarChartItem("161X Goal", 100, Tile.RED);


        BarChartItem mediaPlayer = new BarChartItem("Media Player/N3000", 0, Tile.BLUE);
        BarChartItem mediaGoal = new BarChartItem("Media Player/N3000 Goal", 100, Tile.BLUE);


        BarChartItem t1000Data = new BarChartItem("T1000", 0, Tile.GREEN);
        BarChartItem t1000Goal = new BarChartItem("T1000 Goal", 100, Tile.GREEN);


        BarChartItem questData = new BarChartItem("Quest", 0, Tile.YELLOW);
        BarChartItem questGoal = new BarChartItem("Quest Goal", 100, Tile.YELLOW);


        //---------------------------------Creating the Bar Chart Items for Peripherals-----------------------------
        BarChartItem kiwi4Data = new BarChartItem("Kiwi 4", 0, Tile.RED);
        BarChartItem kiwi4Goal = new BarChartItem("Kiwi 4 Goal", 100, Tile.RED);


        BarChartItem kiwi25Data = new BarChartItem("Kiwi 2.5", 0, Tile.BLUE);
        BarChartItem kiwi25Goal = new BarChartItem("Kiwi 2.5 Goal", 100, Tile.BLUE);


        BarChartItem bumpBarData = new BarChartItem("Bumpbar", 0, Tile.GREEN);
        BarChartItem bumpBarGoal = new BarChartItem("Bumpbar Goal", 100, Tile.GREEN);


        BarChartItem pantherEPC4Data = new BarChartItem("Panther/EPC4", 0, Tile.YELLOW);
        BarChartItem pantherEPC4Goal = new BarChartItem("Panther/EPC4 Goal", 100, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart Items for Optic-----------------------------------
        BarChartItem optic12Data = new BarChartItem("Optic 12", 0, Tile.RED);
        BarChartItem optic12Goal = new BarChartItem("Optic 12 Goal", 100, Tile.RED);


        BarChartItem optic5Data = new BarChartItem("Optic 5", 0, Tile.BLUE);
        BarChartItem optic5Goal = new BarChartItem("Optic 5 Goal", 100, Tile.BLUE);


        BarChartItem cubs = new BarChartItem("Cubs", 0, Tile.GREEN);
        BarChartItem cubsGoal = new BarChartItem("Cubs Goal", 100, Tile.GREEN);


        BarChartItem opticPrinters = new BarChartItem("Optic Printers", 0, Tile.YELLOW);
        BarChartItem opticPrintersGoal = new BarChartItem("Optic Printers Goal", 100, Tile.YELLOW);


        //---------------------------------Creating the Bar Chart Items for Retail-----------------------------------
        BarChartItem xr7Data = new BarChartItem("7702", 0, Tile.RED);
        BarChartItem xr7DataGoal = new BarChartItem("7702 Goal", 100, Tile.RED);


        BarChartItem xr7PlusData = new BarChartItem("7703", 0, Tile.BLUE);
        BarChartItem xr7PlusDataGoal = new BarChartItem("7703 Goal", 100, Tile.BLUE);


        BarChartItem xr5Data = new BarChartItem("7701", 0, Tile.GREEN);
        BarChartItem xr5DataGoal = new BarChartItem("7701 Goal", 100, Tile.GREEN);


        BarChartItem nextGenDisplays = new BarChartItem("Next Gen Displays", 0, Tile.YELLOW);
        BarChartItem nextGenDisplaysGoal = new BarChartItem("Next Gen Displays Goal", 100, Tile.YELLOW);


        //---------------------------------Creating the Bar Chart Items for Retail-----------------------------------
        BarChartItem kittingData = new BarChartItem("Kitting", 30, Tile.RED);
        BarChartItem printersData = new BarChartItem("Printers", 200, Tile.BLUE);
        BarChartItem retailDisplaysData = new BarChartItem("Retail Displays", 40, Tile.GREEN);
        BarChartItem tabletData = new BarChartItem("Tablets", 15, Tile.YELLOW);

        //---------------------------------Creating the Bar Chart for POS-----------------------------------
        pos = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Point of Sales Build")
                .roundedCorners(false)
                .prefSize(384, 640)
                .barChartItems(p1x30Data, p1x30DataGoal, p1x35Data, p1x35DataGoal, p132Data, p132DataGoal, p1520Data, p1520DataGoal)
                .decimals(0)
                .build();

        Gauge posGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
                //.skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("POS")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile posPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(false)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(posGauge)
                .build();


        //---------------------------------Creating the Bar Chart Items for Servers-----------------------------------
        servers = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers Build")
                .roundedCorners(false)
                .prefSize(384, 640)
                .barChartItems(serverData, serverGoal, mediaPlayer, mediaGoal, t1000Data, t1000Goal, questData, questGoal)
                .decimals(0)
                .build();

        Gauge serversGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Servers")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile serversPercent = TileBuilder.create()
                .prefSize(384,440 )
                .skinType(Tile.SkinType.CUSTOM)
                .roundedCorners(false)
                .text("Percentage to Goal")
                .graphic(serversGauge)
                .build();


        //---------------------------------Creating the Bar Chart for Peripherals-----------------------------------
        peripherals = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals Build")
                .roundedCorners(false)
                .prefSize(384, 640)
                .barChartItems(kiwi4Data, kiwi4Goal, kiwi25Data,kiwi25Goal, bumpBarData, bumpBarGoal, pantherEPC4Data,pantherEPC4Goal)
                .decimals(0)
                .build();

        Gauge periphGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Peripherals")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile periphPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(false)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(periphGauge)
                .build();


        //---------------------------------Creating the Bar Chart for Optic-----------------------------------
        optic = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic Build")
                .roundedCorners(false)
                .prefSize(384, 640)
                .barChartItems(optic5Data, optic5Goal, optic12Data, optic12Goal, opticPrinters, opticPrintersGoal, cubs, cubsGoal)
                .decimals(0)
                .build();

        Gauge opticGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Optic")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile opticPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(false)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(opticGauge)
                .build();


        //---------------------------------Creating the Bar Chart Items for Retail-----------------------------------
        retail = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .roundedCorners(false)
                .title("Retail Build")
                .prefSize(384, 640)
                .barChartItems(xr5Data, xr5DataGoal, xr7Data,xr7DataGoal, xr7PlusData, xr7PlusDataGoal, nextGenDisplays, nextGenDisplaysGoal)
                .decimals(0)
                .build();

        Gauge retailGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(100)
                .unit("Retail")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        Tile retailPercent = TileBuilder.create()
                .prefSize(384,440 )
                .roundedCorners(false)
                .skinType(Tile.SkinType.CUSTOM)
                .text("Percentage to Goal")
                .graphic(retailGauge)
                .build();


        //---------------------------------Creating the Bar Chart Items for Misc.-----------------------------------
        misc = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Kitting/Printers/Displays/Tablets")
                .text("Today's Production")
                .prefSize(480, 270)
                .barChartItems(kittingData, printersData, retailDisplaysData, tabletData)
                .decimals(0)
                .build();


        //---------------------------------Creating Animations for Graphs------------------------------------------
        pos.setAnimated(true);
        pos.setAnimationDuration(2);
        servers.setAnimated(true);
        servers.setAnimationDuration(2);
        retail.setAnimated(true);
        retail.setAnimationDuration(2);
        peripherals.setAnimated(true);
        peripherals.setAnimationDuration(2);
        optic.setAnimated(true);
        optic.setAnimationDuration(2);
        misc.setAnimated(true);
        misc.setAnimationDuration(2);

        //---------------------------------------------------------------------------------------------------------

        flowPane.getChildren().addAll(pos, retail, servers, peripherals, optic, posPercent, retailPercent,serversPercent,periphPercent,opticPercent);

        Scene scene = new Scene(flowPane);

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode() == KeyCode.F5)
                {
                    primaryStage.setFullScreen(true);
                }
            }
        });


        primaryStage.setMaximized(true);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

        /*
        radialChart = TileBuilder.create().skinType(Tile.SkinType.RADIAL_CHART)
                //.prefSize(250,250)
                .title("BarChart")
                .text("Production")
                .prefSize(384,270)
                .chartData(hospitalityData, retailData, pcrData, peripheralsData)
                .decimals(0)
                .build();

        radialChart.setAnimated(true);
        radialChart.setAnimationDuration(2);

        donutChart = TileBuilder.create().skinType(Tile.SkinType.DONUT_CHART)
                //.prefSize(250,250)
                .title("BarChart")
                .text("Production")
                .prefSize(384,270)
                .chartData(hospitalityData, retailData, pcrData, peripheralsData)
                .decimals(0)
                .maxValue(700)
                .minValue(120)
                .build();

        donutChart.setAnimated(true);
        donutChart.setAnimationDuration(2);

        mccChart = TileBuilder.create()
                .skinType(Tile.SkinType.RADIAL_CHART)
                .title("MCC Production")
                .prefSize(384, 270)
                .chartData(mccOpen, mccTested, mccClosed)
                .maxValue(400)
                .minValue(100)
                .decimals(0)
                .text("Today's Production")
                .build();

        mccChart.setAnimated(true);
        mccChart.setAnimationDuration(2);*/




        /*Tile stockTile;

        stockTile = TileBuilder.create()
                .skinType(Tile.SkinType.STOCK)
                .prefSize(384,270)
                .title("NCR Stock")
                .minValue(20)
                .maxValue(40)
                .value(30)
                .unit("$")
                .averagingPeriod(100)
                .build();

        stockTile.setAnimated(true);
        stockTile.setAnimationDuration(2);

        Gauge pastDueGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(700)
                .unit("MCC's")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Tile.RED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        //slimGauge.setSections(new Section(1,250, "UA" ,Tile.BLUE), new Section(251,500,"A", Tile.RED));

        Tile medusaDashboard = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.CUSTOM)
                .title("Raw Material")
                .text("MCC's Older than 30 days")
                .graphic(pastDueGauge)
                .build();

        Tile safetyDashboard = TileBuilder.create().skinType(Tile.SkinType.NUMBER)
                .prefSize(384,270)
                .title("Safety")
                .description("\n# of days since last incident")
                .textVisible(true)
                .maxValue(200)
                .decimals(0)
                .value(150)
                .text("Safety Safety Safety")
                .build();

        Tile assignedJobs = TileBuilder.create()
                .skinType(Tile.SkinType.NUMBER)
                .prefSize(384,270)
                .title("Unassigned Jobs")
                .description("jobs not assigned to an order")
                .textVisible(true)
                .value(50)
                .maxValue(2000)
                .decimals(0)
                .text("Alert scheduling if number greater than 0")
                .build();

        Clock slimClock = ClockBuilder.create()
                .prefSize(384, 270)
                .skinType(Clock.ClockSkinType.SLIM)
                .secondColor(Tile.FOREGROUND)
                .minuteColor(Tile.BLUE)
                .hourColor(Tile.FOREGROUND)
                .dateColor(Tile.FOREGROUND)
                .running(true)
                .build();

        Tile slimClockTile = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.CUSTOM)
                .title("Time")
                .graphic(slimClock)
                .textVisible(false)
                .build();

        Tile quoteTile = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.TEXT)
                .title("Quote of the Week")
                .description("\nI see only my objective,\n\nall obstacles must give way")
                .text("Napoleon Bonaparte")
                .textVisible(true)
                .build();

        Gauge slimGauge = GaugeBuilder.create()
                .skinType(Gauge.SkinType.SLIM)
//                .skinType(Gauge.SkinType.SIMPLE_SECTION)
                .prefSize(384, 270)
                .valueColor(Tile.FOREGROUND)
                .titleColor(Tile.FOREGROUND)
                .unitColor(Tile.FOREGROUND)
                .value(250)
                .unit("past dues")
                .maxValue(1000)
                .animated(true)
                .decimals(0)
                .barColor(Color.DARKRED)
                .needleColor(Tile.FOREGROUND)
                .barBackgroundColor(Tile.BACKGROUND.darker())
                .tickLabelColor(Tile.FOREGROUND)
                .majorTickMarkColor(Tile.FOREGROUND)
                .minorTickMarkColor(Tile.FOREGROUND)
                .mediumTickMarkColor(Tile.FOREGROUND)
                .build();

        //slimGauge.setSections(new Section(1,250, "UA" ,Tile.BLUE), new Section(251,500,"A", Tile.RED));

        Tile slimTile = TileBuilder.create()
                .prefSize(384, 270)
                .skinType(Tile.SkinType.CUSTOM)
                .title("Medusa Slim")
                .text("Past Dues")
                .graphic(slimGauge)
                .build();*/


//flowPane.getChildren().addAll(pos, retail, servers, peripherals, optic, misc, donutChart, slimTile, medusaDashboard, slimClockTile, safetyDashboard, radialChart, quoteTile, mccChart, assignedJobs);

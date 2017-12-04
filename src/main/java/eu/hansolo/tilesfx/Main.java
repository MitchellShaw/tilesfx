package eu.hansolo.tilesfx;

import eu.hansolo.medusa.*;
import eu.hansolo.tilesfx.Tile;
import eu.hansolo.tilesfx.TileBuilder;
import eu.hansolo.tilesfx.chart.ChartData;
import eu.hansolo.tilesfx.skins.BarChartItem;
import eu.hansolo.tilesfx.skins.GaugeTileSkin;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Created by Ramon Johnson
 * 2017-11-22.
 */
public class Main extends Application
{
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
        FlowPane flowPane = new FlowPane();

        Tile radialChart;
        Tile donutChart;
        Tile donutChart2;
        Tile donutChart3;
        Tile mccChart;

        Tile pos;
        Tile servers;
        Tile peripherals;
        Tile optic;
        Tile retail;
        Tile misc;

        ChartData hospitalityData = new ChartData("Hospitality", 462, Tile.RED);
        ChartData retailData = new ChartData("Retail", 250, Tile.BLUE);
        ChartData pcrData = new ChartData("PCR", 120, Tile.DARK_BLUE);
        ChartData peripheralsData = new ChartData("Peripherals", 700, Tile.GREEN);

        ChartData mccClosed = new ChartData("Closed", 220, Tile.GREEN);
        ChartData mccOpen = new ChartData("Open", 400, Tile.RED);
        ChartData mccTested = new ChartData("Tested", 100, Tile.BLUE);

        BarChartItem p1x35Data = new BarChartItem("P1X35", 25, Tile.RED);
        BarChartItem p132Data = new BarChartItem("P1532", 50, Tile.BLUE);
        BarChartItem p1x30Data = new BarChartItem("P1X30", 5, Tile.GREEN);
        BarChartItem p1520Data = new BarChartItem("1520's", 1, Tile.YELLOW);

        BarChartItem serverData = new BarChartItem("161X", 15, Tile.RED);
        BarChartItem mediaPlayer = new BarChartItem("Media Player/N3000", 90, Tile.BLUE);
        BarChartItem t1000Data = new BarChartItem("T1000", 40, Tile.GREEN);
        BarChartItem questData = new BarChartItem("Quest", 8, Tile.YELLOW);

        BarChartItem kiwi4Data = new BarChartItem("Kiwi 4", 200, Tile.RED);
        BarChartItem kiwi25Data = new BarChartItem("Kiwi 2.5", 120, Tile.BLUE);
        BarChartItem bumpBarData = new BarChartItem("Bumpbar", 320, Tile.GREEN);
        BarChartItem pantherEPC4Data = new BarChartItem("Panther/EPC4", 80, Tile.YELLOW);

        BarChartItem optic12Data = new BarChartItem("Optic 12", 200, Tile.RED);
        BarChartItem optic5Data = new BarChartItem("Optic 5", 384, Tile.BLUE);
        BarChartItem cubs = new BarChartItem("Cubs", 600, Tile.GREEN);
        BarChartItem opticPrinters = new BarChartItem("Optic Printers", 300, Tile.YELLOW);

        BarChartItem xr7Data = new BarChartItem("7702", 175, Tile.RED);
        BarChartItem xr7PlusData = new BarChartItem("7703", 23, Tile.BLUE);
        BarChartItem xr5Data = new BarChartItem("7701", 176, Tile.GREEN);
        BarChartItem nextGenDisplays = new BarChartItem("Next Gen Displays", 40, Tile.YELLOW);

        BarChartItem kittingData = new BarChartItem("Kitting", 30, Tile.RED);
        BarChartItem printersData = new BarChartItem("Printers", 200, Tile.BLUE);
        BarChartItem retailDisplaysData = new BarChartItem("Retail Displays", 40, Tile.GREEN);
        BarChartItem tabletData = new BarChartItem("Tablets", 15, Tile.YELLOW);


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
        mccChart.setAnimationDuration(2);

        /************
        *
        *  BarCharts
        *
        *************/

        pos = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Point of Sales")
                .text("Today's Production")
                //.prefSize(320, 270)
                //--- .maxValue(50) ---//
                .minValue(1)
                .barChartItems(p1x30Data, p1x35Data, p132Data, p1520Data)
                .decimals(0)
                .build();

        servers = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Servers")
                .text("Today's Production")
                .prefSize(320, 270)
                .barChartItems(serverData, mediaPlayer, t1000Data, questData)
                .decimals(0)
                .build();

        peripherals = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Peripherals")
                .text("Today's Production")
                .prefSize(320, 270)
                .barChartItems(kiwi4Data, kiwi25Data, bumpBarData, pantherEPC4Data)
                .decimals(0)
                .build();

        optic = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Optic")
                .text("Today's Production")
                .prefSize(320, 270)
                .barChartItems(optic5Data, optic12Data, opticPrinters, cubs)
                .decimals(0)
                .build();

        retail = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Retail")
                .text("Today's Production")
                .prefSize(320, 270)
                .barChartItems(xr5Data, xr7Data, xr7PlusData, nextGenDisplays)
                .decimals(0)
                .build();

        misc = TileBuilder.create()
                .skinType(Tile.SkinType.BAR_CHART)
                .title("Kitting/Printers/Displays/Tablets")
                .text("Today's Production")
                .prefSize(320, 270)
                .barChartItems(kittingData, printersData, retailDisplaysData, tabletData)
                .decimals(0)
                .build();

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

        /************
         *
         *  end BarCharts
         *
         *************/

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
        stockTile.setAnimationDuration(2);*/

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
                .build();


        flowPane.getChildren().addAll(pos, retail, servers, peripherals, optic, misc, donutChart, slimTile, medusaDashboard, slimClockTile, safetyDashboard, radialChart, quoteTile, mccChart, assignedJobs);

        primaryStage.setMaximized(true);

        primaryStage.setScene(new Scene(flowPane));
        primaryStage.show();
    }
}

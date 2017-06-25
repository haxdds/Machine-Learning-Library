package plot;

import javafx.scene.chart.Chart;
import learning.knn.KNN;
import learning.regression.Regression;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * Created by Lagrange on 6/24/2017.
 */
public class Plot{


    XYSeriesCollection collection = new XYSeriesCollection();
    JFreeChart chart;
    ChartFrame frame;


    public void scatter(String seriesName, float[] x, float[] y) {
        XYDataset collection = createDataSet(seriesName, x, y);
        chart = ChartFactory.createScatterPlot("X v Y", "X", "Y", collection);
    }

    public void addLine(double x1, double y1, double x2, double y2){
        if(chart == null) throw new IllegalArgumentException("Chart must be filled with data first");
        XYLineAnnotation a = new XYLineAnnotation(x1, y1, x2, y2);
        chart.getXYPlot().addAnnotation(a);
    }

    public void scatter(String seriesName, float[][] data) {
        XYDataset collection = createDataSet(seriesName, data);
        chart = ChartFactory.createScatterPlot("X v Y", "X", "Y", collection);
    }






    public void show(){
        frame = new ChartFrame("Plot", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void scatter(Regression regression){
        scatter("Scatter",regression.getX_data(), regression.getY_data());
        addLine(regression.getX_data()[0],regression.predict(regression.getY_data()[0]), regression.getX_data()[regression.getX_data().length-1],regression.predict(regression.getY_data()[regression.getX_data().length-1]));
    }

    public void plotKNN(KNN knn){
        for(String s: knn.getDictionary().keySet()){
            if(collection == null){
                scatter(s, knn.getDictionary().get(s));
            }else{
                addSeries(s, knn.getDictionary().get(s));
            }
        }
        chart = ChartFactory.createScatterPlot("KNN", "X", "Y", collection);
        for(float[] f: knn.getNn()){
            XYLineAnnotation a = new XYLineAnnotation(knn.getPredict()[0], knn.getPredict()[1], f[0], f[1]);
            chart.getXYPlot().addAnnotation(a);
        }

    }





    public  XYDataset createDataSet(String seriesName, float[] x, float[] y){
        XYSeries series = new XYSeries(seriesName);
        for(int k = 0; k < x.length; k++) {
            series.add(x[k], y[k]);
        }
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return collection;
    }
    public  XYDataset createDataSet(String seriesName, float[][] data){
        XYSeries series = new XYSeries(seriesName);
        for(int k = 0; k < data.length; k++) {
            series.add(data[k][0], data[k][1]);
        }
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return collection;
    }


    public void addSeries(String seriesName, float[] x, float[] y){
        XYSeries series = new XYSeries(seriesName);
        for(int k = 0; k < x.length; k++) {
            series.add(x[k], y[k]);
        }
        collection.addSeries(series);

    }

    public void addSeries(String seriesName, float[][] data){
        XYSeries series = new XYSeries(seriesName);
        for(int k = 0; k < data.length; k++) {
            series.add(data[k][0], data[k][1]);
        }
        collection.addSeries(series);

    }

    public void addSeries(String seriesName, float x, float y){
        XYSeries series = new XYSeries(seriesName);
        series.add(x , y);
        collection.addSeries(series);
    }






}

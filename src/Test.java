
import learning.knn.KNN;
import learning.regression.SimpleLinearRegression;
import plot.Plot;

import java.util.HashMap;

/**
 * Created by Lagrange on 6/24/2017.
 */
public class Test {

    public static void main(String[] args){
        HashMap<String, float[][]> dict = new HashMap<>();
        float[][] red = {{1,2},{2,2},{1,3}};
        float[][] blue = {{7,8},{8,6},{9,7}};
        dict.put("Red", red);
        dict.put("Blue", blue);
        KNN knn = new KNN(dict);
        float[] p = {6,5};
        System.out.print(knn.predict(p,3));
        Plot plot = new Plot();
        plot.plotKNN(knn);
        plot.addSeries("predict",p[0], p[1]);
        plot.show();

    }

}

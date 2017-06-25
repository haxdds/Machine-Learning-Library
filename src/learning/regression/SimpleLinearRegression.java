package learning.regression;

import mathlib.mathUtils.*;
import org.jfree.util.ArrayUtilities;

/**
 * Created by Lagrange on 6/24/2017.
 */
public class SimpleLinearRegression extends Regression {

    private float m;
    private float b;


    public SimpleLinearRegression(float[] x_data, float[] y_data){
        this.x_data = x_data;
        this.y_data = y_data;
        this.coefficients = new float[2];
    }

    @Override
    public void fit() {
        m = ((mean(x_data)*mean(y_data)) - mean(dot(x_data, y_data)))/((mean(x_data)*mean(x_data))-mean(pow(x_data,2)));
        b = mean(y_data) - m * mean(x_data);
        System.out.print(m + " "+ b);
        coefficients[0] = b;
        coefficients[1] = m;
    }

    @Override
    public float predict(float x) {
        //if(m == 0 || b ==0) throw new IllegalArgumentException("Data hasn't been fitted yet.");
        return (m * x)+ b;
    }


    public float[] predict(float[] x) {
        float[] a = new float[x.length];
        for(int k = 0; k < x.length; k++)
            a[k] = predict(x[k]);
        return a;
    }

    private float mean(float[] arr){
        return MathUtils.mean(arr);
    }

    private float[] dot(float[] a, float[] b){
        return MathUtils.dot(a,b);
    }

    private float[] pow(float[] a , int n){
        return MathUtils.power(a,n);
    }

}

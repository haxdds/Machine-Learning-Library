package learning.regression;

/**
 * Created by Lagrange on 6/24/2017.
 */
public abstract class Regression {

    protected float[] x_data;
    protected float[] y_data;
    protected float[] coefficients;

    public abstract void fit();
    public abstract float predict(float x);
    public abstract float[] predict(float[] x);


    public float[] getX_data() {
        return x_data;
    }

    public float[] getY_data() {
        return y_data;
    }
}

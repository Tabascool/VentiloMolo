package com.cassone.devmalin.ventilomolo;

import android.graphics.DashPathEffect;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.util.Log;

import com.cassone.devmalin.ventilomolo.models.Temperature;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;


public class MainActivity extends AppCompatActivity {


    private XYPlot plot;

    private static final String TAG = "MainActivity";
    private final static int INTERVAL = 5000;
    Handler mHandler =new Handler();

    private List<Temperature> tabTemp;

    List<Number> series1Numbers;
    List<Number> series2Numbers;
    List<Number> series3Numbers;

    XYSeries series1;
    XYSeries series2;
    XYSeries series3;

    LineAndPointFormatter series1Format;
    LineAndPointFormatter series2Format;
    LineAndPointFormatter series3Format;

    protected int countTemp;
    protected int displayRemaining;

    protected String motorState;
    protected double maxVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plot = (XYPlot) findViewById(R.id.plot);

        tabTemp = new ArrayList<>();
        series1Numbers = new ArrayList<>();
        series2Numbers = new ArrayList<>();
        series3Numbers = new ArrayList<>();

        mhandlerTask.run();
    }

    Runnable mhandlerTask = new Runnable()
    {

        @Override
        public void run() {

            TemperatureService temperatureService = SetupTempService.setupTempService();
            Call<List<Temperature>> call = temperatureService.getAllTemperature(TemperatureService.API_KEY);
            call.enqueue(new Callback<List<Temperature>>() {
                @Override
                public void onResponse(Call<List<Temperature>> call, Response<List<Temperature>> response) {
                    if (!response.isSuccess()) {
                        Log.d(TAG, "error");
                    } else {
                        Log.d(TAG, "success");
                        tabTemp = response.body();
                        updatePlot();
                    }
                }

                @Override
                public void onFailure(Call<List<Temperature>> call, Throwable t) {
                    Log.d(TAG, "Failure" + t.toString());
                }
            });

            mHandler.postDelayed(mhandlerTask, INTERVAL);
        }
    };


    public void updatePlot() {

        if (tabTemp != null && tabTemp.size() > 0) {
            series1Numbers = new ArrayList<>();
            series2Numbers = new ArrayList<>();
            series3Numbers = new ArrayList<>();

            countTemp = tabTemp.size();
            if (countTemp <= 10)
                displayRemaining = countTemp;
            else
                displayRemaining = 10;

            while (displayRemaining > 0) {
                series1Numbers.add(tabTemp.get(countTemp - displayRemaining).getCelcius());
                series2Numbers.add(tabTemp.get(countTemp - displayRemaining).getHumidity());

                if (maxVal < tabTemp.get(countTemp - displayRemaining).getCelcius())
                    maxVal = tabTemp.get(countTemp - displayRemaining).getCelcius();

                if (maxVal < tabTemp.get(countTemp - displayRemaining).getHumidity())
                    maxVal = tabTemp.get(countTemp - displayRemaining).getHumidity();

                displayRemaining--;
            }

            if (countTemp <= 10)
                displayRemaining = countTemp;
            else
                displayRemaining = 10;

            while (displayRemaining > 0) {

                if (tabTemp.get(countTemp - displayRemaining).isMotorActived() == 0)
                    series3Numbers.add(0);
                else
                    series3Numbers.add(maxVal);

                displayRemaining--;
            }

            if (tabTemp.get(countTemp - 1).isMotorActived() == 1)
                motorState = "Ventilateur : Activé";
            else
                motorState = "Ventilateur : Désactivé";

            // create formatters to use for drawing a series using LineAndPointRenderer
            // and configure them from xml:
            series1Format = new LineAndPointFormatter();
            series1Format.setPointLabelFormatter(new PointLabelFormatter());
            series1Format.configure(getApplicationContext(),
                    R.xml.line_point_formatter_with_labels);

            series2Format = new LineAndPointFormatter();
            series2Format.setPointLabelFormatter(new PointLabelFormatter());
            series2Format.configure(getApplicationContext(),
                    R.xml.line_point_formatter_with_labels_2);

            // add an "dash" effect to the series2 line:
            series2Format.getLinePaint().setPathEffect(
                    new DashPathEffect(new float[]{

                            // always use DP when specifying pixel sizes, to keep things consistent across devices:
                            PixelUtils.dpToPix(20),
                            PixelUtils.dpToPix(15)}, 0));

            series3Format = new LineAndPointFormatter();
            series3Format.setPointLabelFormatter(new PointLabelFormatter());
            series3Format.configure(getApplicationContext(),
                    R.xml.line_point_formatter_with_labels_3);

            series3Format.getLinePaint().setPathEffect(
                    new DashPathEffect(new float[]{

                            // always use DP when specifying pixel sizes, to keep things consistent across devices:
                            PixelUtils.dpToPix(5),
                            PixelUtils.dpToPix(5)}, 5));

            // just for fun, add some smoothing to the lines:
            // see: http://androidplot.com/smooth-curves-and-androidplot/
            series1Format.setInterpolationParams(
                    new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

            series2Format.setInterpolationParams(
                    new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

            series3Format.setInterpolationParams(
                    new CatmullRomInterpolator.Params(10, CatmullRomInterpolator.Type.Centripetal));

            // add a new series' to the xyplot:
            plot.removeSeries(series1);
            plot.removeSeries(series2);
            plot.removeSeries(series3);

            // turn the above arrays into XYSeries':
            // (Y_VALS_ONLY means use the element index as the x value)
            series1 = new SimpleXYSeries(series1Numbers,
                    SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Température");

            series2 = new SimpleXYSeries(series2Numbers,
                    SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Humidité");

            series3 = new SimpleXYSeries(series3Numbers,
                    SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Etat moteur");

            plot.addSeries(series1, series1Format);
            plot.addSeries(series2, series2Format);
            plot.addSeries(series3, series3Format);

            plot.setDomainLabel(motorState);

            // reduce the number of range labels
            plot.setTicksPerRangeLabel(3);

            // rotate domain labels 45 degrees to make them more compact horizontally:
            plot.getGraphWidget().setDomainLabelOrientation(-45);

            plot.invalidate();
        }

    }
}

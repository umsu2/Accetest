package com.scuta.accelerometer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;



public class MainActivity extends Activity implements SensorEventListener {

	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private TextView axisX, axisY, axisZ;
	public TextView timenano,timearray; 
	//double timeStamp = 0;
	//double timeStampTemp=0;
	public dataSample[] array_10 = new dataSample[10];
	int i = 0;
//	int m = 0;
//	public ArrayList array_10 = new ArrayList();
	//double[][] samples = new double[10][4];
	//double[] onesample = new double[4];
	
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		axisX = (TextView) findViewById(R.id.acc_x);
		axisY = (TextView) findViewById(R.id.acc_y);
		axisZ = (TextView) findViewById(R.id.acc_z);
		timenano = (TextView) findViewById(R.id.timenano);
		timearray = (TextView) findViewById(R.id.timearray);
		
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		 // Do something here if sensor accuracy changes.
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	// 	ArrayList<dataSample> array_10 = new ArrayList<dataSample>(10);
		
		TimeStamp timer_value = new TimeStamp();
		dataSample first_data = new dataSample(event.values[0], event.values[1], event.values[2], timer_value.returntime());
		array_10[i] = first_data;
//		double[] onesample = new double[4];
//		
//
//	    onesample[0] = event.values[0];
//	    onesample[1] = event.values[1];
//	    onesample[2] = event.values[2];   
//	    onesample[3] = timer1.returntime();
//	    samples[i] = onesample;
//	    
//	    
	    axisX.setText("X: "+array_10[i].xaxis);
	    axisY.setText("Y: "+array_10[i].yaxis);
	    axisZ.setText("Z: "+array_10[i].zaxis);

        timenano.setText("Time: "+ array_10[i].timestamp);

	    
	    i++;
	    if (i == 10)
	    	{
	    	i = 0;
	    	}
	    	
	   
	    
	}
	
	@Override
	  protected void onResume() {
	    super.onResume();
	    mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
	  }

	  @Override
	  protected void onPause() {
	    super.onPause();
	    mSensorManager.unregisterListener(this);
	  }


}

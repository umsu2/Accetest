package com.scuta.accelerometer;

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
	double timeStamp = 0;
	double timeStampTemp=0;
	
	int i = 0;
	double[][] samples = new double[10][4];
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
		
		TimeStamp timer1 = new TimeStamp();
		double[] onesample = new double[4];
		
//		float x = event.values[0];
//		float y = event.values[1];
//	    float z = event.values[2];
//	    timeStamp = System.currentTimeMillis();
//	    axisX.setText("X: "+x);
//	    axisY.setText("Y: "+y);
//	    axisZ.setText("Z: "+z);
//	    timenano.setText("Time: "+ timeStamp);
	    onesample[0] = event.values[0];
	    onesample[1] = event.values[1];
	    onesample[2] = event.values[2];   
	    onesample[3] = timer1.returntime();
	    samples[i] = onesample;
	    
	    
	    axisX.setText("X: "+onesample[0]);
	    axisY.setText("Y: "+onesample[1]);
	    axisZ.setText("Z: "+onesample[2]);
	    timenano.setText("Time: "+ samples[i][3]);
//	    double value = samples[1][3]-samples[5][3];
	    
//	    timearray.setText("timearray: " + samples[i][3]);
	    i++;
	    if (i == 10)
	    	{i = 0;}
	    	
	   
	    
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

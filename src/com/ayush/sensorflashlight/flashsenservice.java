package com.ayush.sensorflashlight;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.Camera.Parameters;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class flashsenservice extends Service implements SensorEventListener{
	SensorManager sm;
	Sensor sensor;
	float j;
	Camera camera;
	Context context = this;
@Override
public IBinder onBind(Intent arg0) {
	// TODO Auto-generated method stub
	return null;
}
@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
Toast.makeText(this,"Service Activated",Toast.LENGTH_LONG).show();
sm=(SensorManager)getSystemService(SENSOR_SERVICE);
sensor=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
j=sensor.getMaximumRange();
 camera = Camera.open();


}
@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Toast.makeText(this,"Service Closed",Toast.LENGTH_LONG).show();	
		sm.unregisterListener(this,sensor);
		if (camera != null) {
			   camera.release();
		}
		
		
	
}
@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		sm.registerListener(this, sensor, sm.SENSOR_DELAY_FASTEST);
	}
@Override
public void onAccuracyChanged(Sensor arg0, int arg1) {
	// TODO Auto-generated method stub
	
	
}
@Override
public void onSensorChanged(SensorEvent arg0) {
	// TODO Auto-generated method stub
	float f=arg0.values[0];
	//int j=(int)f;
	PackageManager pm=context.getPackageManager();
	Parameters p = camera.getParameters();
	  	if(f!=j)
	{
		//im.setBackgroundResource(R.drawable.pic2);

	    //Log.i("info", "torch is turn on!");
	    p.setFlashMode(Parameters.FLASH_MODE_TORCH);
	    camera.setParameters(p);
	    camera.startPreview();
	}
	else
	{
		//im.setBackgroundResource(R.drawable.pic3);
		//Log.i("info", "torch is turn off!");
	    p.setFlashMode(Parameters.FLASH_MODE_OFF);
	    camera.setParameters(p);
	    camera.stopPreview();
	   
	}

}
private boolean isFlashSupported(PackageManager packageManager){ 
	  // if device support camera flash?
	  if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
	   return true;
	  } 
	  return false;
	 }

	  private boolean isCameraSupported(PackageManager packageManager){
	  // if device support camera?
	  if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
	   return true;
	  } 
	  return false;
	 }
	

}


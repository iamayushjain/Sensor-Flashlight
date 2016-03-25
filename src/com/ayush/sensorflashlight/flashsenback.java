package com.ayush.sensorflashlight;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

	import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


	public class flashsenback extends Activity {
		Intent intent;
		//private InterstitialAd mInterstitialAd;

		Button button,button1;
		Camera cam;
		Context context=this;
		SensorManager sm;
		ImageView iv;
		
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_main);
		iv=(ImageView)findViewById(R.id.imageView1);
		intent=new Intent(flashsenback.this,flashsenservice.class);
		PackageManager pm = context.getPackageManager();
		if(!isFlashSupported(pm))
		{
			
				
				Toast.makeText(getApplicationContext(), "FLASH NOT SUPPORTED", 5000).show();
				finish();
				
		}	
			
					
		else{
		
		NotificationManager nm=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
		Notification n=new Notification(R.drawable.ligonic2,"Service Activated",System.currentTimeMillis());
		Intent i=new Intent(flashsenback.this,flashnotify.class);
		PendingIntent pi=PendingIntent.getActivity(flashsenback.this,0,i,0);
		CharSequence msg="FLASHLIGHT ON";
		CharSequence msg1="TAP TO OFF";
		n.setLatestEventInfo(flashsenback.this,msg,msg1,pi);
			n.flags |=Notification.FLAG_NO_CLEAR;
						
			nm.notify(0,n);
			
						 //Toast.makeText(getApplicationContext(), "Notification Open",3000).show();
						startService(intent);
						finish();
		}

		
	}

	private boolean isFlashSupported(PackageManager packageManager){ 
		  // if device support camera flash?
		  if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
		   return true;
		  } 
		  return false;
		 }
	


	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_my_main, menu);
		return true;
	}

}

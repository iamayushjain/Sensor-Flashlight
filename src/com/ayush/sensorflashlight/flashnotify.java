package com.ayush.sensorflashlight;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class flashnotify extends Activity {
	private InterstitialAd mInterstitialAd;

@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if (Context.NOTIFICATION_SERVICE!=null) {
			
	        String ns = Context.NOTIFICATION_SERVICE;

	            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);

	            nMgr.cancel(0);
	            //Toast.makeText(getApplicationContext(), "Notification Closed",3000).show();
	            //Toast.makeText(getApplicationContext(), "Service Closed",3000).show();
	            
	            Intent intent=new Intent(flashnotify.this,flashsenservice.class);
	            stopService(intent);
	        	mInterstitialAd = new InterstitialAd(this);
	            mInterstitialAd.setAdUnitId("ca-app-pub-5666077083567257/8214943328");
	            AdRequest adRequest1 = new AdRequest.Builder().build();
	            mInterstitialAd.loadAd(adRequest1);
	            mInterstitialAd.setAdListener(new AdListener(){
	                public void onAdLoaded(){
	                    
	                                        // if (mInterstitialAd.isLoaded()) {
	                             mInterstitialAd.show();
	                         //}
	                     
	                }
	      });
	    	
	            finish();
	           
}}}




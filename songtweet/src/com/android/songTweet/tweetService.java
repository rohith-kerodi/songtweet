package com.android.songTweet;

import android.app.Service;
import android.os.IBinder;
import android.os.Binder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;


public class tweetService extends Service {
	
	private final IBinder mBinder = new tweetBinder();
	@Override
	public IBinder onBind(Intent arg0) {
		return mBinder;
	}
	
	public class tweetBinder extends Binder {
		public tweetService getService() {
			return tweetService.this;
		}
	}
	
	 @Override
    public void onCreate() {
    	Log.d("service:oncreate():","onCreate called.");
    	
    	IntentFilter iF = new IntentFilter();
        iF.addAction("com.android.music.metachanged");
        iF.addAction("com.android.music.playstatechanged");
        iF.addAction("com.android.music.playbackcomplete");
        iF.addAction("com.android.music.queuechanged");
        registerReceiver(mReceiver, iF);
      
    }
	 private BroadcastReceiver mReceiver = new BroadcastReceiver() {

	    	@Override
	    	public void onReceive(Context context, Intent intent)
	    	{
	    		String action = intent.getAction();
	    		String cmd = intent.getStringExtra("command");
	    		Log.d("mIntentReceiver.onReceive ", action + " / " + cmd);
	    		String artist = intent.getStringExtra("artist");
	    		String album = intent.getStringExtra("album");
	    		String track = intent.getStringExtra("track");
	    		Log.d("Music",artist+":"+album+":"+track);
	            CharSequence song=(track);
	        }
	     };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        
        // Tell the user we stopped.
        Toast.makeText(this, "Service stopped.", Toast.LENGTH_SHORT).show();
    }
}
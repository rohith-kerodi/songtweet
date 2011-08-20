package com.android.songTweet;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import com.android.songTweet.tweetService;


public class songTweet extends Activity {
    /** Called when the activity is first created. */
	private tweetService tweet;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //stop service!!
        startService(new Intent(this,songTweet.class));
        
        //
        doBindService();
        
        }
     
     private ServiceConnection mConnection = new ServiceConnection() {

 		public void onServiceConnected(ComponentName className, IBinder binder) {
 			tweet = ((tweetService.tweetBinder) binder).getService();
 			Toast.makeText(songTweet.this, "Connected",
 					Toast.LENGTH_SHORT).show();
 		}

 		public void onServiceDisconnected(ComponentName className) {
 			tweet = null;
 		}
 	};
     boolean doBindService() {
 		return bindService(new Intent(this, tweetService.class), mConnection,
 				Context.BIND_AUTO_CREATE);
 		
 }
}



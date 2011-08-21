//Start-stop needs to be fixed.
package com.android.songTweet;

import java.io.UnsupportedEncodingException;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import android.app.Service;
import android.os.IBinder;
import android.os.Binder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.widget.Toast;
import com.android.songTweet.auth;

public class tweetService extends Service {

	private final String CONSUMERKEY = "W3KTVMnQIakfnWeYe1Dw";
	private final String CONSUMERSECRET="bznG6f4dE0I3vdTfDSp2Z5upiayRxZOztq75SbSMs";
	
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
    	
    	//Register for media player broadcasts.
    	IntentFilter iF = new IntentFilter();
        iF.addAction("com.android.music.metachanged");
        iF.addAction("com.android.music.playstatechanged");
        iF.addAction("com.android.music.playbackcomplete");
        iF.addAction("com.android.music.queuechanged");
        registerReceiver(mReceiver, iF);
        
        //Read the twitter OAuth tokens.
        
        //
      
	 }
	 
	 public void startOneTimeAuthorization(){
		 
		 try {
			auth authorize = new auth(CONSUMERKEY,CONSUMERSECRET);
			authorize.configure(CONSUMERKEY, CONSUMERSECRET);
			String requestToken = authorize.getRequestToken();
			
			
		} catch (UnsupportedEncodingException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthMessageSignerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthNotAuthorizedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OAuthExpectationFailedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( OAuthCommunicationException e){
			e.printStackTrace();
		}
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
	            //Start a thread for tweet.
	        }
	     };
//test
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
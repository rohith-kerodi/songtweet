package com.android.songTweet;

import java.io.UnsupportedEncodingException;
import oauth.signpost.*;
import oauth.signpost.commonshttp.*;
import oauth.signpost.exception.*;

class auth{
	private OAuthConsumer mConsumer;
	private OAuthProvider mProvider;
	private String REQUESTTOKEN = "https://api.twitter.com/oauth/request_token";
	private String ACCESSTOKEN = "http://twitter.com/oauth/access_token";
	private String AUTHORIZETOKEN = "http://twitter.com/oauth/authorize";
	private String mConsumerKey,mConsumerSecret;
	
	public auth (){
		//default constructotr
	}
	
	public void configure(String consumerKey, String consumerSecret){
		mConsumerKey=consumerKey;
		mConsumerSecret=consumerSecret;
		mConsumer = new CommonsHttpOAuthConsumer(mConsumerKey, mConsumerSecret);
		mProvider = new CommonsHttpOAuthProvider(REQUESTTOKEN,ACCESSTOKEN,AUTHORIZETOKEN);
	}
	
	public auth(String consumerKey, String consumerSecret)
		throws UnsupportedEncodingException {
		configure(consumerKey,consumerSecret);		
	}
	
	public String getRequestToken()
	throws OAuthMessageSignerException, OAuthNotAuthorizedException,
	OAuthExpectationFailedException, OAuthCommunicationException {
	    String authUrl = mProvider.retrieveRequestToken(mConsumer, OAuth.OUT_OF_BAND);
	    return authUrl;
	    //Need to launch browser with this Url.
	}
	
	public String[] getAccessToken(String pin)
		throws OAuthMessageSignerException, OAuthNotAuthorizedException,
			OAuthExpectationFailedException, OAuthCommunicationException {
		mProvider.retrieveAccessToken(mConsumer, pin);
	
	    return new String[] {
	    		mConsumer.getToken(), mConsumer.getTokenSecret()
	    };
	}


}

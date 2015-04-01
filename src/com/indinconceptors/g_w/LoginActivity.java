package com.indinconceptors.g_w;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;


import com.indinconceptors.g_w.R;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;
import com.facebook.widget.LoginButton.UserInfoChangedCallback;


import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {
	private LoginButton loginBtn;
	private TextView name,id,email;
	private UiLifecycleHelper uiHelper;
	String fbuid,fbname,fbemail;
	HttpPost httppost;
	Boolean internetactive;
	HttpClient httpclient;
	ProgressDialog dialog; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);				
		uiHelper = new UiLifecycleHelper(this, statusCallback);
		uiHelper.onCreate(savedInstanceState);				
		setContentView(R.layout.activity_login);
						
		SharedPreferences shpref = getSharedPreferences("IPL",MODE_PRIVATE);
		if (shpref.getBoolean("login", false) == true) 
		{
			startActivity(new Intent(LoginActivity.this, MoreResultActivity.class));
			finish();
		} else {
			
			String msg= " my is " +shpref.getBoolean("login", false);
//			 Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
			
		}
		
		loginBtn = (LoginButton) findViewById(R.id.authButton);
		loginBtn.setReadPermissions(Arrays.asList("email"));
		
		
		
		
		loginBtn.setUserInfoChangedCallback(new UserInfoChangedCallback() {
			@Override
			public void onUserInfoFetched(GraphUser user) {  
				
				httppost = new HttpPost(HttpUrls.facebookrequest);
				if (user != null) {
					
					fbuid=user.getId();
					fbname=user.getName();
					fbemail= user.getProperty("email").toString();
					
//					Toast.makeText(LoginActivity.this,"Internet Not Connected" +fbuid+fbuid+fbemail,Toast.LENGTH_SHORT).show();
					
//                           name.setText("You are currently logged in as " + user.getName());
//                           id.setText("You facebook id"+ user.getId());
//                           email.setText("You facebook email id"+ user.getProperty("email").toString());
// 
				} 
				else
				{
					internetactive = isNetworkAvailable();
//					name.setText("You are not logged in.");
				}
				if (internetactive) 
				{					
				 new getresult().execute();
				}
				else 
			     {
				    Toast.makeText(LoginActivity.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
			    	}				
						
				
			}
		});
	}
	
	
	
	protected Boolean isNetworkAvailable()
	{
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null&& activeNetworkInfo.isConnectedOrConnecting();
	}


	private Session.StatusCallback statusCallback = new Session.StatusCallback() {
		@Override
		public void call(Session session, SessionState state,
				Exception exception) {
			if (state.isOpened()) {
				 Intent intent=new Intent(LoginActivity.this, MoreResultActivity.class);
				 startActivity(intent);
				Log.d("LoginActivity", "Facebook session opened.");
			} else if (state.isClosed()) {
				Log.d("LoginActivity", "Facebook session closed.");
			}
		}
	};
	
	@Override
	public void onResume() {
		super.onResume();
		uiHelper.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
		uiHelper.onPause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		uiHelper.onDestroy();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		uiHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onSaveInstanceState(Bundle savedState) {
		super.onSaveInstanceState(savedState);
		uiHelper.onSaveInstanceState(savedState);
	}
	
	
	
	
	
	public class getresult extends AsyncTask<Void, Void, Void> {
      String result=null;
    
  
     
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			try {
				HttpParams params = new BasicHttpParams();				
				params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
				httpclient = new DefaultHttpClient(params);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("fbuserid", fbuid));
				nameValuePairs.add(new BasicNameValuePair("name", fbname));
				nameValuePairs.add(new BasicNameValuePair("email", fbemail));
				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result = httpclient.execute(httppost, new BasicResponseHandler());
				
				
			} 
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;			
		}
    	
		 @Override
		 protected void onPostExecute(Void result1)
		 {
			 super.onPostExecute(result1);
			 
			 
			 try 
			 {		
				 JSONObject jsonObj = new JSONObject(result);	
			 
			     String msg= ""+jsonObj.get("Message");
			     String access_token = "" + jsonObj.get("AccessToken");
			     String type=""+jsonObj.get("LoginStatus");
			     if(type.equals("true"))
			     {
			    			
						SharedPreferences shpref = getSharedPreferences("IPL", MODE_PRIVATE);
						SharedPreferences.Editor editor = shpref.edit();
						editor.putString("fbuserid", fbuid);
						editor.putString("name", fbname);
						editor.putString("email", fbemail);
						editor.putString("access_token", access_token);
						editor.putBoolean("login", true);
						editor.commit(); 
						 Intent intent=new Intent(LoginActivity.this, MoreResultActivity.class);
						 startActivity(intent);
						finish();				
			     }else 
			     {
//				   Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
			     }			     				  				    				 
				 
			} catch (Exception e) {
				// TODO: handle exception
				
				//Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
			 
		 }	
	 }
	
}	

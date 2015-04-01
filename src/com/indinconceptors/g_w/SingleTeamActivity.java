package com.indinconceptors.g_w;

import java.util.ArrayList;
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
import org.json.JSONObject;

import com.indinconceptors.g_w.R;
import com.indinconceptors.g_w.BetActivity.getresult;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SingleTeamActivity extends ActionBarActivity{
	
	String matchid,betmatchidfortest;
	SingleItem adapter;
	private Activity context;
	private int betmatchid;
	HttpPost httppostgetsinglerecord;
	Boolean internetactive;
	HttpClient httpclient;
	String accesstoken="1",strbetmatchid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_team);
		
		
		httppostgetsinglerecord = new HttpPost(HttpUrls.Httpgetmatchdetailbymatchid);
		
		strbetmatchid = getIntent().getExtras().getString("matchid","defaultKey");

		Toast.makeText(SingleTeamActivity.this,strbetmatchid , Toast.LENGTH_LONG).show(); 
		
		
		try{
       	         	     		     	     		
			adapter=new SingleItem(context, matchid);
//			Toast.makeText(SingleTeamActivity.this,matchid.toString() , Toast.LENGTH_LONG).show();
			
      		
        }
        catch (Exception e) {
			e.printStackTrace();
		}
		
		internetactive = isNetworkAvailable();
		if(internetactive)
		{
			
			new getresult().execute();
//			Toast.makeText(SingleTeamActivity.this,"getresult" , Toast.LENGTH_LONG).show(); 
		}
		else 
	     {
		    Toast.makeText(SingleTeamActivity.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
	    	}
		
		
		
	}

	private Boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null&& activeNetworkInfo.isConnectedOrConnecting();
	}
	
	
	public class getresult extends AsyncTask<Void, Void, Void>
	{
		String result=null;

		@Override
		protected Void doInBackground(Void... params) {			try {
				
				
				Toast.makeText(SingleTeamActivity.this,"start" , Toast.LENGTH_LONG).show(); 
				HttpParams param = new BasicHttpParams();				
				param.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
				httpclient = new DefaultHttpClient(param);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("accesstoken", accesstoken));
				nameValuePairs.add(new BasicNameValuePair("matchid", strbetmatchid));
				
				httppostgetsinglerecord.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result = httpclient.execute(httppostgetsinglerecord, new BasicResponseHandler());
				 Toast.makeText(SingleTeamActivity.this, "tokean:-"+accesstoken+matchid +result, Toast.LENGTH_SHORT).show();
//				Toast.makeText(SingleTeamActivity.this,"start" , Toast.LENGTH_LONG).show(); 
			} catch (Exception e)
			{
//				Toast.makeText(SingleTeamActivity.this,"error start" , Toast.LENGTH_LONG).show(); 
				// TODO: handle exception
			}
			// TODO Auto-generated method stub
			
			return null;
		}
		
		 @Override
		 protected void onPostExecute(Void result1)
		 {
			 super.onPostExecute(result1);
			 try {
//				 Toast.makeText(SingleTeamActivity.this," onpost" , Toast.LENGTH_LONG).show();
				
			} catch (Exception e) {
//				Toast.makeText(SingleTeamActivity.this,"error onpost" , Toast.LENGTH_LONG).show();
				// TODO: handle exception
			}
		 }
		
	}
	
//	public class getresult extends AsyncTask<Void, Void, Void> {
//	      String result=null;
//	     
//	     
//	     
//	     
//			@Override
//			protected Void doInBackground(Void... arg0) {
//				// TODO Auto-generated method stub
//				
//				try {
//					
//					 Toast.makeText(SingleTeamActivity.this,"start" , Toast.LENGTH_LONG).show(); 
//					HttpParams params = new BasicHttpParams();				
//					params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
//					httpclient = new DefaultHttpClient(params);				
//					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//					nameValuePairs.add(new BasicNameValuePair("accesstoken", accesstoken));
//					nameValuePairs.add(new BasicNameValuePair("matchid", strbetmatchid));
//					
//					httppostgetsinglerecord.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
//					result = httpclient.execute(httppostgetsinglerecord, new BasicResponseHandler());
//					 Toast.makeText(SingleTeamActivity.this, "tokean:-"+accesstoken+matchid +result, Toast.LENGTH_SHORT).show();
//					
//				} 
//				catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;			
//			}
	    	
//			 @Override
//			 protected void onPostExecute(Void result1)
//			 {
//				 super.onPostExecute(result1);
//				 
//				 
//				 try 
//				 {		
//					 JSONObject jsonObj = new JSONObject(result);	
//					 Toast.makeText(SingleTeamActivity.this, "hii", Toast.LENGTH_SHORT).show();
//					 Toast.makeText(SingleTeamActivity.this,"Data:-"+ result.toString(), Toast.LENGTH_SHORT).show();
//					 
//					 
////				     String msg= ""+jsonObj.get("Message");
////				     String access_token = "" + jsonObj.get("AccessToken");
////				     String type=""+jsonObj.get("LoginStatus");
////				     if(type.equals("true"))
////				     {
////				    			
////							
////				     }else 
////				     {
//////					   Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
////				     }			     				  				    				 
//					 
//				} catch (Exception e) {
//					// TODO: handle exception
//					
//					//Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//				}
//				 
//			 }	
	//	 }
	
	

	
	
	
}

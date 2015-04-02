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
import org.json.JSONArray;
import org.json.JSONObject;

import com.androidquery.AQuery;
import com.indinconceptors.g_w.R;
import com.indinconceptors.g_w.BetActivity.getresult;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleTeamActivity extends ActionBarActivity{
	
	String matchid,betmatchidfortest;
	SingleItem adapter;
	private Activity context;
	private int betmatchid;
	HttpPost httppostgetsinglerecord;
	Boolean internetactive;
	HttpClient httpclient;
	String accesstoken="1",strbetmatchid,firstteam,secondteam,firstteamLogo,secondteamLogo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_team);
		
		
		httppostgetsinglerecord = new HttpPost(HttpUrls.Httpgetmatchdetailbymatchid);
		
		strbetmatchid = getIntent().getExtras().getString("matchid","defaultKey");

		Toast.makeText(SingleTeamActivity.this,strbetmatchid , Toast.LENGTH_LONG).show(); 
		
		

		
		internetactive = isNetworkAvailable();
		if(internetactive)
		{
			
			new getresult().execute();
			Toast.makeText(SingleTeamActivity.this,"getresult" , Toast.LENGTH_LONG).show(); 
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
		protected Void doInBackground(Void... args) {			
			
			try {
				
				
				//Toast.makeText(SingleTeamActivity.this,"start" , Toast.LENGTH_LONG).show(); 
				
				HttpParams params = new BasicHttpParams();				
				params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
				httpclient = new DefaultHttpClient(params);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				nameValuePairs.add(new BasicNameValuePair("accesstoken", "1"));
				nameValuePairs.add(new BasicNameValuePair("matchid", strbetmatchid));
				
				httppostgetsinglerecord.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result = httpclient.execute(httppostgetsinglerecord, new BasicResponseHandler());
				// Toast.makeText(SingleTeamActivity.this, "tokean:-"+accesstoken+matchid +result, Toast.LENGTH_SHORT).show();

			} catch (Exception e)
			{     // TODO: handle exception
				Toast.makeText(SingleTeamActivity.this,"error start" , Toast.LENGTH_LONG).show(); 
				
			}
			// TODO Auto-generated method stub
			
			return null;
		}
		
		
		
		 @Override
		 protected void onPostExecute(Void result1)
		 {
			 super.onPostExecute(result1);
			 
			 Toast.makeText(SingleTeamActivity.this, "hii", Toast.LENGTH_SHORT).show();
			 try 
			 {		JSONObject Jobject =  	new JSONObject(result);	
			 
			 if(Jobject!=null)
			 {
				// String access_token = Jobject.getString("MatchId");
				 
			      firstteam= Jobject.getString("FirstTeam");
			      secondteam= Jobject.getString("SecondTeam");

			      firstteamLogo= Jobject.getString("FirstTeamLogo");
			      secondteamLogo= Jobject.getString("SecondTeamLogo");
			      
			    
					
					ImageView imageView = (ImageView) findViewById(R.id.imgoneInsingleteam);			
					ImageView imageViewsecond = (ImageView) findViewById(R.id.imgsecondInsingleteam);
								
					AQuery aq = new AQuery(context);
					aq.id(imageView).image(firstteamLogo);						
					aq.id(imageViewsecond).image(secondteamLogo);
					
					
					TextView tvfirstteamname = (TextView) findViewById(R.id.textfirstteamInsingleteam);
					tvfirstteamname.setText(firstteam);
					tvfirstteamname.setTextColor(Color.parseColor("#FFFFFF"));
										
					TextView tvsecondteamname = (TextView) findViewById(R.id.textsecondteamInsingleteam);
					tvsecondteamname.setText(secondteam);
					tvsecondteamname.setTextColor(Color.parseColor("#FFFFFF"));
					
			      Toast.makeText(SingleTeamActivity.this, firstteam+secondteam, Toast.LENGTH_LONG).show();
			      
			      
			 }
			
			     			     			    			    			        
										 
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(SingleTeamActivity.this, "error", Toast.LENGTH_SHORT).show();
				//Toast.makeText(SingleTeamActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
			 
		 }	
	 }
		
	
	
	
	
	
	}
		
	
		 


	
	
	


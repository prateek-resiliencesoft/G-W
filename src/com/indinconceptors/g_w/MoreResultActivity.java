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
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import com.indinconceptors.g_w.R;
import com.indinconceptors.g_w.BetActivity.getresult;


import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MoreResultActivity extends ActionBarActivity {
	ArrayList<String> matchid, firstteam, secondteam, amount, eventdate,streventdate,firstteamlogo,secondteamlogo,matchresult,firstteampoint,secondteampoint;
	CustomList adapter;
	ListView lvmoreresult;
	Boolean internetactive;
	HttpPost HttpGetMatch;
	HttpClient httpclient;
	ProgressDialog dialog;
	SingleItem singleitem;
	SharedPreferences shpref;
	
	protected Activity context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more_result);
		
		HttpGetMatch=new HttpPost(HttpUrls.Httpgetmatchdetails);
		
		
		try {
		
			//singleitem=new SingleItem(null, null);
			firstteam = new ArrayList<String>();
      		secondteam = new ArrayList<String>();
        	firstteamlogo = new ArrayList<String>();
        	secondteamlogo = new ArrayList<String>();     		
      		amount = new ArrayList<String>();
      		eventdate = new ArrayList<String>();      		
      		streventdate = new ArrayList<String>();      		
      		matchid = new ArrayList<String>();
      		matchresult = new ArrayList<String>();
      		firstteampoint = new ArrayList<String>();
      		secondteampoint = new ArrayList<String>();    
      		
//      		shpref = getSharedPreferences("IPL", MODE_PRIVATE);
//      		if (shpref.getBoolean("login", false) == true) 
//			{
//				startActivity(new Intent(MoreResultActivity.this, LoginActivity.class));
//				finish();
//			} else {
//			}
//			
     
      		lvmoreresult=(ListView)findViewById(R.id.listMoreResult);
      		adapter = new CustomList(this,matchid, firstteam, secondteam, firstteamlogo, secondteamlogo, amount,  matchresult, firstteampoint, secondteampoint, streventdate, eventdate);
//      		Toast.makeText(MoreResultActivity.this,"hello" , Toast.LENGTH_LONG).show();
      		lvmoreresult.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
					// TODO Auto-generated method stub
					int ps=position;
					String s=Integer.toString(ps);
//					Toast.makeText(MoreResultActivity.this,"hello"+s , Toast.LENGTH_LONG).show();
					Object selection = (Object) parent.getItemAtPosition(position);
					final String item = (String) parent.getItemAtPosition(position);
					
					for(int i=0;i>adapter.getCount();i++)
					{
//						Toast.makeText(MoreResultActivity.this,adapter.getItem(i) , Toast.LENGTH_LONG).show(); 
					}
					
					 TextView tv=(TextView)view.findViewById(R.id.textmatchid);
					 
					 					
					
					
      			// Toast.makeText(MoreResultActivity.this,parent.getItemAtPosition(position).toString() , Toast.LENGTH_LONG).show();
//					Toast.makeText(MoreResultActivity.this,"More"+tv.getText().toString()  , Toast.LENGTH_LONG).show();
					
					
					Intent intent=new Intent(MoreResultActivity.this, SingleTeamActivity.class);
					
					intent.putExtra("matchid", tv.getText().toString());
					
					startActivity(intent);
				}
			});
     		lvmoreresult.setAdapter(adapter);
			internetactive = isNetworkAvailable();
			if (internetactive) {
				new getresult().execute();
			} else {
				//Toast.makeText(this, "Internet Not Connected", Toast.LENGTH_SHORT).show();

			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
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
      protected void onPreExecute() { 
           dialog = new ProgressDialog(MoreResultActivity.this); 
           dialog.setMessage("Loading...."); //Instead of searching you can write loading also or else
           dialog.setIndeterminate(true); 
           dialog.setCancelable(true); 
           dialog.show(); //runuithread can be call
           
        } 
     
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			try {
				HttpParams params = new BasicHttpParams();				
				params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
				httpclient = new DefaultHttpClient(params);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
				
				HttpGetMatch.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result = httpclient.execute(HttpGetMatch, new BasicResponseHandler());
				
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
			 
			// Toast.makeText(BetActivity.this,result2, Toast.LENGTH_LONG).show();
			 try 
			 {				 				
				 JSONArray array=  	new JSONArray(result);							 
				 for(int i=0;i<array.length();i++)
				 {					
				        JSONObject jsonObject = array.getJSONObject(i);		
				       			  				        
				        matchid.add(jsonObject.getString("MatchId"));
				        firstteam.add(jsonObject.getString("FirstTeam"));
				        secondteam.add(jsonObject.getString("SecondTeam"));
				        eventdate.add(jsonObject.getString("EventDate"));
				        streventdate.add(jsonObject.getString("strEventDate"));
				        firstteamlogo.add(jsonObject.getString("FirstTeamLogo"));
				        secondteamlogo.add(jsonObject.getString("SecondTeamLogo"));
				        matchresult.add(jsonObject.getString("MatchResult"));
				        firstteampoint.add(jsonObject.getString("FirstTeamPoint"));
				        secondteampoint.add(jsonObject.getString("SecondTeamPoint"));
			                
		             		               		     				  				    
				 }
				 adapter.notifyDataSetChanged();
				 
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
			 dialog.dismiss();

			   
		 }
		 
              
    }
	
	
	
	
	
	
	
	
}

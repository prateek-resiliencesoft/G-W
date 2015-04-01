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
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Dashboard extends ActionBarActivity implements OnClickListener {
TextView textnewbet;
ListView listuserdetails;
HttpPost httppost,HttpGetUserdetails;
Boolean internetactive;
HttpClient httpclient;
Dialog  dialog;
ArrayList<String> username,userpoint,userimage;
UserDetails adapter;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);
		textnewbet=(TextView)findViewById(R.id.txtnewbet);
		textnewbet.setOnClickListener(this);
		
		
		HttpGetUserdetails=new HttpPost(HttpUrls.Httpuserdetails);
		
		
		try {
			
			username = new ArrayList<String>();
			userpoint = new ArrayList<String>();
			userimage = new ArrayList<String>();
			listuserdetails=(ListView)findViewById(R.id.listuserdetails);
			adapter=new UserDetails(this, username, userpoint, userimage);
			listuserdetails.setAdapter(adapter);
			
			internetactive = isNetworkAvailable();
			if (internetactive) {
				new getresult().execute();
			} else {
				Toast.makeText(this, "Internet Not Connected", Toast.LENGTH_SHORT).show();

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
           dialog = new ProgressDialog(Dashboard.this);           
           dialog.setCancelable(true); 
           dialog.show(); 
           
        } 
     
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
			try {
				HttpParams params = new BasicHttpParams();				
				params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
				httpclient = new DefaultHttpClient(params);				
				List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);				
				HttpGetUserdetails.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result = httpclient.execute(HttpGetUserdetails, new BasicResponseHandler());
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
				 JSONArray array=  	new JSONArray(result);							 
				 for(int i=0;i<array.length();i++)
				 {					
				        JSONObject jsonObject = array.getJSONObject(i);		
				        
				        
				        
				        username.add(jsonObject.getString("Name"));
				        userpoint.add(jsonObject.getString("Point"));
				        userimage.add(jsonObject.getString("ImageUrl"));				       
				       
				       				                    		                		                		               	               		               		     				  				    
				 }
				 adapter.notifyDataSetChanged();
				 
			} catch (Exception e) {
				// TODO: handle exception
			}
			 
			 dialog.dismiss();

			   
		 }
		 
              
    }
	

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try {
			
			
			switch (v.getId()) {
			case R.id.txtnewbet:
				Intent intent=new Intent(Dashboard.this,BetActivity.class);
				startActivity(intent);
				
				break;

//			default:
//				break;
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}

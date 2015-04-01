package com.example.ipl;



import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;




import android.support.v7.app.ActionBarActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

import android.widget.ProgressBar;
import android.widget.Toast;

public class DesignActivity extends ActionBarActivity {
ProgressBar progress;
ProgressDialog dialog;
private int mProgressStatus = 0;


Boolean internetactive;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_design);
		
//		progress=(ProgressBar)findViewById(R.id.progressmain);
		
		
		internetactive = isNetworkAvailable();
		if (internetactive) 
		{	
			
		 new getresult().execute();
		}
		else 
	     {
		    Toast.makeText(DesignActivity.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
	    	}				
			
		
		try 
		{  			
			

			// in first request it will display this activity for some time and at the back time it will not be displayed 
			new Handler().postDelayed(new Runnable() {
				
				
				@Override
				public void run() {
					
					// TODO Auto-generated method stub
					
					SharedPreferences shpref = getSharedPreferences("IPL",MODE_PRIVATE);
					if (shpref.getBoolean("login", false) == true) 
					{
						startActivity(new Intent(DesignActivity.this, MoreResultActivity.class));
						finish();
					} else {
						
						String msg= " my is " +shpref.getBoolean("login", false);
//						 Toast.makeText(DesignActivity.this, msg, Toast.LENGTH_SHORT).show();
						 Intent intent=new Intent(DesignActivity.this, LoginActivity.class);
							DesignActivity.this.startActivity(intent);
							DesignActivity.this.finish(); //finish this activity in second request onwards
						
					}
					

					
					 	
				}
			}, 3000);//wait for 3 seconds
			
			
				
			
		} 
		catch (Exception e)
		{
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
	      
	 
	      @Override 
	      protected void onPreExecute() { 
	    	   	
	    	   dialog = new ProgressDialog(DesignActivity.this); 
	           dialog.setMessage("Loading...."); //Instead of searching you can write loading also or else
	           dialog.setIndeterminate(true); 
	           dialog.setCancelable(true); 
	           dialog.show();
	        } 
			@Override
			protected Void doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				
				try {
					HttpParams params = new BasicHttpParams();				
					params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);					
				
					
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

		 
				    dialog.dismiss();// it will destroy the dialog
				    
			 }
	    }

}

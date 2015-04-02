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
import android.text.Editable;
import android.text.TextWatcher;
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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleTeamActivity extends ActionBarActivity {
	
	String matchid,betmatchidfortest,edpoint,propsedpoint,seletedteam;
	SingleItem adapter;
	private Activity context;
	private int betmatchid;
	HttpPost httppostgetsinglerecord,httppridectmatch;
	Boolean internetactive;
	HttpClient httpclient;
	String accesstoken="1",strbetmatchid,firstteam,secondteam,firstteamLogo,secondteamLogo,firstteampoint,secondteampoint;
	TextView textselectedpoint;
	EditText edtextpoint;
	SharedPreferences shpref;
	Button btnbet;
	CheckBox cbfirstteam,cbsecondteam;
	Float selectedpoint;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_team);
		
		 shpref = getSharedPreferences("IPL",MODE_PRIVATE);
		
		httppostgetsinglerecord = new HttpPost(HttpUrls.Httpgetmatchdetailbymatchid);
		
		strbetmatchid = getIntent().getExtras().getString("matchid","defaultKey");
		
		cbfirstteam=(CheckBox)findViewById(R.id.checkfirstteam);
		cbsecondteam=(CheckBox)findViewById(R.id.checksecondteam);

		Toast.makeText(SingleTeamActivity.this,strbetmatchid , Toast.LENGTH_LONG).show(); 
		
		setbet();
	
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
		
		
		
		
		edtextpoint=(EditText)findViewById(R.id.edpointinsingle);
		
		edtextpoint.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				// TODO Auto-generated method stub
				textselectedpoint=(TextView)findViewById(R.id.textselectedteampoint);
				propsedpoint=edtextpoint.getText().toString();
				 Toast.makeText(SingleTeamActivity.this,"selectedpoint"+selectedpoint,Toast.LENGTH_SHORT).show();
				textselectedpoint.setTextColor(Color.parseColor("#FFFFFF"));
				if(arg0.length()>0)
				{
				Float total=Float.parseFloat(arg0.toString()) * selectedpoint;
				
				textselectedpoint.setText(total.toString());
				}
				else{
					textselectedpoint.setText("");
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
//				CharSequence cs=
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	public void setbet()
	{
		
		httppridectmatch = new HttpPost(HttpUrls.Httppredictteam);
		btnbet=(Button)findViewById(R.id.buttonbet);
		btnbet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    edpoint=edtextpoint.getText().toString();
				
				if(cbfirstteam.isChecked())
				{
					selectedpoint=Float.parseFloat(firstteampoint);
					Float total=Float.parseFloat(firstteampoint) * Integer.parseInt(edtextpoint.getText().toString());
					String nettotal=Float.toString(total);
					Toast.makeText(getApplicationContext(), "clicked -"+nettotal, Toast.LENGTH_SHORT).show();
				}
				
				if(cbsecondteam.isChecked())
				{  
					selectedpoint=Float.parseFloat(secondteampoint);
					Float total=Float.parseFloat(secondteampoint) * Integer.parseInt(edtextpoint.getText().toString());
					String nettotal=Float.toString(total);
					Toast.makeText(getApplicationContext(), "clicked-"+nettotal, Toast.LENGTH_SHORT).show();
				}
				
//				Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
//				internetactive = isNetworkAvailable();
//				if(internetactive)
//				{
//					String token=shpref.getString("access_token", "defaultKey");
//					new savematchpoint().execute();
//					Toast.makeText(SingleTeamActivity.this,"getresult"+token , Toast.LENGTH_LONG).show(); 
//					Log.d("MyToken:-", token);
//				}
//				else 
//			     {
//				    Toast.makeText(SingleTeamActivity.this,"Internet Not Connected",Toast.LENGTH_SHORT).show();
//			    	}	
				
			}
		});	
		
	}
	
	
	public void onCheckboxClicked(View view) {

        switch(view.getId()) {
       
            case R.id.checkfirstteam:

            	cbsecondteam.setChecked(false);
            	 selectedpoint=Float.parseFloat(firstteampoint);
            	 seletedteam=firstteam;
                break;

            case R.id.checksecondteam:

            	cbfirstteam.setChecked(false);
            	selectedpoint=Float.parseFloat(secondteampoint);
            	seletedteam=secondteam;
                break;

           
        }
        
      
//    	if(edpoint.length()>0)
//		{
//		Float total=Float.parseFloat(edpoint) * selectedpoint;
//		
//		textselectedpoint.setText(total.toString());
//		}
//		else{
//			textselectedpoint.setText("");
//		}
    }
	
	

	
	
	
	
	private Boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null&& activeNetworkInfo.isConnectedOrConnecting();
	}
	
	
	public class getresult extends AsyncTask<Void, Void, Void>
	{
		String result=null;
		String pridectmatch=null;

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
				
				// To place a point
			
				
				
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
			      firstteampoint= Jobject.getString("FirstTeamPoint");
			      secondteampoint= Jobject.getString("SecondTeamPoint");
			      
			      
			    
					
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
					
					TextView tvfirstteampoint=(TextView)findViewById(R.id.textfirstteampointInsingleteam);
					tvfirstteampoint.setText(firstteampoint);
					tvfirstteampoint.setTextColor(Color.parseColor("#FFFFFF"));
					
					
					TextView tvsecondteampoint=(TextView) findViewById(R.id.textsecondteampointInsingleteam);
					tvsecondteampoint.setText(secondteampoint);
					tvsecondteampoint.setTextColor(Color.parseColor("#FFFFFF"));
					
					
					
			      Toast.makeText(SingleTeamActivity.this, firstteam+secondteam, Toast.LENGTH_LONG).show();
			      
			      
			 }
			 
			     			     			    			    			        
										 
			} catch (Exception e) {
				// TODO: handle exception
				Toast.makeText(SingleTeamActivity.this, "error", Toast.LENGTH_SHORT).show();
				//Toast.makeText(SingleTeamActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
			}
			 
		 }	
	 }
		
	public class savematchpoint extends AsyncTask<Void, Void, Void>
	{
		String pridectmatch=null;

		@Override
		protected Void doInBackground(Void... args) {
			try {
				HttpParams params = new BasicHttpParams();				
				params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
				httpclient = new DefaultHttpClient(params);	
				List<NameValuePair> matchpridectargument = new ArrayList<NameValuePair>(2);
				matchpridectargument.add(new BasicNameValuePair("accesstoken",shpref.getString("access_token", "defaultKey") ));
				matchpridectargument.add(new BasicNameValuePair("matchid", strbetmatchid));
				matchpridectargument.add(new BasicNameValuePair("answerid", seletedteam));
				matchpridectargument.add(new BasicNameValuePair("point", propsedpoint));
				matchpridectargument.add(new BasicNameValuePair("matchtype", "1"));
				httppridectmatch.setEntity(new UrlEncodedFormEntity(matchpridectargument,"utf-8"));
				pridectmatch = httpclient.execute(httppridectmatch, new BasicResponseHandler());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			// TODO Auto-generated method stub
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			 Toast.makeText(SingleTeamActivity.this, "data:-"+pridectmatch, Toast.LENGTH_SHORT).show();
		}
	}
	
	
	
	
	}
		
	
		 


	
	
	


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

import com.indinconceptors.g_w.R;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BetActivity extends ActionBarActivity implements OnClickListener  {
	TextView FirstTeam,SecondTeam;
	Button btnbet;
	ListView lvmatchdetail;
	EditText edfirstteam;
	String point,access_token,showmatch;
	HttpPost httppost,HttpGetMatch;
	Boolean internetactive;
	HttpClient httpclient;
	ProgressDialog dialog;
	 TextView txtfirstteam,txtsecondteam; 
	
	
	ArrayList<String> matchid, firstteam, secondteam, amount, eventdate,streventdate,firstteamlogo,secondteamlogo,matchresult,firstteampoint,secondteampoint;
	CustomList adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bet);
					
        httppost = new HttpPost(HttpUrls.HttpPutpoint);
  		HttpGetMatch=new HttpPost(HttpUrls.Httpgetmatchdetails);
       
        try{
        	 
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
      		
      		lvmatchdetail=(ListView)findViewById(R.id.listmatch);
     		adapter = new CustomList(this, firstteam, secondteam, firstteamlogo, secondteamlogo, amount, matchid, matchresult, firstteampoint, secondteampoint, streventdate, eventdate);
      		lvmatchdetail.setAdapter(adapter);
			internetactive = isNetworkAvailable();
			if (internetactive) {
				new getresult().execute();
			} else {
				Toast.makeText(this, "Internet Not Connected", Toast.LENGTH_SHORT).show();

			}
      		
      		
        }
        catch (Exception e) {
			e.printStackTrace();
		}
        
        
        lvmatchdetail.setOnItemClickListener(new OnItemClickListener() {

        	
        	
			@Override
			public void onItemClick(AdapterView<?> arg0,  View arg1, int arg2,
					long arg3) {
			//	String TAG = null;
				// TODO Auto-generated method stub
//				Log.i(TAG, "onListItemClick: " + arg2);
//				Toast.makeText(BetActivity.this,
//						"List View Clicked:" + arg2, Toast.LENGTH_LONG)
//						.show();				
//			//	String itemText = arg0[arg2];
//				final Dialog firstdialog = new Dialog(BetActivity.this);
//				firstdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//				firstdialog.setContentView(R.layout.layoutforbet);
//				firstdialog.show();
//			
//			    final EditText editfirstteammatch=(EditText)firstdialog.findViewById(R.id.edmatch1);
//			    showmatch = editfirstteammatch.getText().toString();
//				firstdialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
//					
//					@Override
//					public void onClick(View arg0) {
//						// TODO Auto-generated method stub
//						firstdialog.dismiss();
//						
//						txtfirstteam.setText("firstteam" );
//						int amount=  Integer.parseInt(firstteampoint.get(0)) * Integer.parseInt(showmatch.toString()) ;
//						
//					}
//				});	
				
				
				try {
					
					
					
					switch (arg0.getId()) {
					case R.id.imgone:
						
//						final Dialog firstdialog = new Dialog(BetActivity.this);
//						firstdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//						firstdialog.setContentView(R.layout.layoutforbet);
//						firstdialog.show();
//					
//					    final EditText editfirstteammatch=(EditText)firstdialog.findViewById(R.id.edmatch1);
//					    showmatch = editfirstteammatch.getText().toString();
//						firstdialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
//							
//							@Override
//							public void onClick(View arg0) {
//								// TODO Auto-generated method stub
//								firstdialog.dismiss();
//								txtfirstteam.setText("firstteam");
//								int amount=  Integer.parseInt(firstteampoint.get(0)) * Integer.parseInt(showmatch.toString()) ;
//								
//							}
//						});																
				break;

				
					case R.id.textsecondteam:
						final Dialog seconddialog = new Dialog(BetActivity.this);
						seconddialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
						seconddialog.setContentView(R.layout.layoutforbet);
						seconddialog.show();
						final EditText editsecondteammatch=(EditText)seconddialog.findViewById(R.id.edmatch1);
					    showmatch = editsecondteammatch.getText().toString();
					   
					    seconddialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								seconddialog.dismiss();
								txtsecondteam.setText("secondteam");
								int amount=  Integer.parseInt(secondteampoint.get(0)) * Integer.parseInt(showmatch.toString()) ;
							}
						});
				
					    break;
					    
					default:
						break;
					}
					
					
				} 
				
				
				catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
        
//       Dialog d= new Dialog(BetActivity.this);
//        d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        d.setContentView(R.layout.show);
//        d.show();
        
        
        txtfirstteam=(TextView) findViewById(R.id.textfirstteam);
        txtfirstteam.setOnClickListener(this);
        txtsecondteam=(TextView) findViewById(R.id.textsecondteam);       
       txtsecondteam.setOnClickListener(this);
       
	}

	

	
	protected Boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		return activeNetworkInfo != null&& activeNetworkInfo.isConnectedOrConnecting();
	}
	
	
	
	
	
	public class getresult extends AsyncTask<Void, Void, Void>
    {
      String result=null;
      String result2= null;
  
      @Override 
      protected void onPreExecute() { 
           dialog = new ProgressDialog(BetActivity.this); 
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
//				nameValuePairs.add(new BasicNameValuePair("accesstoken", point));
//				nameValuePairs.add(new BasicNameValuePair("amount", point));
//				httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
//				result = httpclient.execute(httppost, new BasicResponseHandler());
				
				HttpGetMatch.setEntity(new UrlEncodedFormEntity(nameValuePairs,"utf-8"));
				result2 = httpclient.execute(HttpGetMatch, new BasicResponseHandler());
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
				 JSONArray array=  	new JSONArray(result2);							 
				 for(int i=0;i<array.length();i++)
				 {					
				        JSONObject jsonObject = array.getJSONObject(i);		
				        
				   // 	ArrayList<String> matchid, firstteam, secondteam, amount, eventdate,streventdate,firstteamlogo,secondteamlogo,matchresult,firstteampoint,secondteampoint;
				        
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
				       
				        
				    //    Toast.makeText(BetActivity.this,jsonObject.getString("FirstTeam"), Toast.LENGTH_LONG).show();
		              //  Toast.makeText(BetActivity.this, matchid+firstteam+secondteam, Toast.LENGTH_LONG).show();		                 
		                
		                
		                
		               
		               		     				  				    
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
			case R.id.textfirstteam:
				
				final Dialog firstdialog = new Dialog(BetActivity.this);
				firstdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				firstdialog.setContentView(R.layout.layoutforbet);
				firstdialog.show();
			    final EditText editfirstteammatch=(EditText)firstdialog.findViewById(R.id.edmatch1);
			    showmatch = editfirstteammatch.getText().toString();
				firstdialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						firstdialog.dismiss();
						txtfirstteam.setText("firstteam");
						int amount=  Integer.parseInt(firstteampoint.get(0)) * Integer.parseInt(showmatch.toString()) ;
					}
				});																
		break;

		
			case R.id.textsecondteam:
				final Dialog seconddialog = new Dialog(BetActivity.this);
				seconddialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				seconddialog.setContentView(R.layout.layoutforbet);
				seconddialog.show();
				final EditText editsecondteammatch=(EditText)seconddialog.findViewById(R.id.edmatch1);
			    showmatch = editsecondteammatch.getText().toString();
			   
			    seconddialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						seconddialog.dismiss();
						txtsecondteam.setText("secondteam");
						int amount=  Integer.parseInt(secondteampoint.get(0)) * Integer.parseInt(showmatch.toString()) ;
					}
				});
		
			    break;
			    
			default:
				break;
			}
			
			
		} 
		
		
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}


}

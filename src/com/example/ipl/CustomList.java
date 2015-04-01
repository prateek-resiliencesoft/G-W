package com.example.ipl;

import java.util.ArrayList;
import com.androidquery.AQuery;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class CustomList  extends ArrayAdapter<String> implements OnClickListener {
	String bidamount,setamount,finalamount;
	 int itemposition ;
	private final Activity context;
	private final ArrayList<String> FirstTeam;
	private final ArrayList<String> SecondTeam;	
	private final ArrayList<String> firstteamlogo;
	private final ArrayList<String> secondteamlogo;
	private final ArrayList<String> amount;	
	private final ArrayList<String> Firstteampoint;
	private final ArrayList<String> Secondteampoint;
	
	private final ArrayList<String> Eventdate;
	private final ArrayList<String> Streventdate;
	private final ArrayList<String> Matchid;
	private final ArrayList<String> Matchresult;

	public CustomList(Activity context, 
			ArrayList<String> matchid,
			ArrayList<String> firstteam,
			ArrayList<String> secondteam,
			ArrayList<String> firstteamlogo,
			ArrayList<String> secondteamlogo,
			ArrayList<String> amount,			
			
			ArrayList<String> matchresult,
			ArrayList<String> firstteampoint,
			ArrayList<String> secondteampoint,
			
			ArrayList<String>streventdate,
			ArrayList<String> eventdate)
	{
		super(context, R.layout.show, firstteam);
		this.Matchid=matchid;
		this.context = context;
		this.FirstTeam = firstteam;
		this.SecondTeam = secondteam;
		this.firstteamlogo = firstteamlogo;
		this.secondteamlogo = secondteamlogo;
		this.Matchresult = matchresult;
		this.Firstteampoint = firstteampoint;
		this.Secondteampoint = secondteampoint;
		this.Streventdate=streventdate;
		this.amount = amount;
		
		this.Eventdate=eventdate;
	}

	 @Override
	public View getView(int position, View view, ViewGroup parent) {
		 
		LayoutInflater inflater = context.getLayoutInflater();
		View rowView = inflater.inflate(R.layout.show, null, true);
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.imgone);
		imageView.setOnClickListener(this);

		
		ImageView imageViewsecond = (ImageView) rowView.findViewById(R.id.imgsecond);
		imageViewsecond.setOnClickListener(this);
		
		AQuery aq = new AQuery(context);
		aq.id(imageView).image(firstteamlogo.get(position));
		aq.id(imageViewsecond).image(secondteamlogo.get(position));
		
		
		TextView tvfirstteamname = (TextView) rowView.findViewById(R.id.textfirstteam);
		tvfirstteamname.setText(FirstTeam.get(position));
		tvfirstteamname.setTextColor(Color.parseColor("#FFFFFF"));

		
		
		
		TextView tvsecondteamname = (TextView) rowView.findViewById(R.id.textsecondteam);
		tvsecondteamname.setText(SecondTeam.get(position));
		tvsecondteamname.setTextColor(Color.parseColor("#FFFFFF"));
				
//		TextView tvamount = (TextView) rowView.findViewById(R.id.textamount);
//		tvamount.setText(amount.get(position));
		
		
		
		
		
		TextView matchid = (TextView) rowView.findViewById(R.id.textmatchid);
		matchid.setText(Matchid.get(position));
				
//		TextView matchresult = (TextView) rowView.findViewById(R.id.textmatchresult);
//		matchresult.setText(Matchresult.get(position));
		

//		TextView firstteampoint = (TextView) rowView.findViewById(R.id.textfirstteampoint);
//		firstteampoint.setText(Firstteampoint.get(position));
//		
//		TextView secondteampoint = (TextView) rowView.findViewById(R.id.textsecondteampoint);
//		secondteampoint.setText(Secondteampoint.get(position));
//		
//		TextView streventdate = (TextView) rowView.findViewById(R.id.textstreventdate);
//		streventdate.setText(Streventdate.get(position));
		
//		TextView eventdate = (TextView) rowView.findViewById(R.id.texteventdate);
//		eventdate.setText(Eventdate.get(position));
		
		
		
		
		
//		try {
//			
//			String tag=null;
//			View parentRow = (View) view.getParent();
//			ListView listView = (ListView) parentRow.getParent();
//			 itemposition = listView.getPositionForView(parentRow);
//			Log.i(tag,"Position:-" +position);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			Toast.makeText(context, e.toString(),Toast.LENGTH_LONG).show();
//		}
		
		return rowView;
	}
	 
	 
	 
	  

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
		
		try {
			
		//	 String s =(String)v.getTag();
			
//			
//			String tag=null;
//			View parentRow = (View) v.getParent();
//			ListView listView = (ListView) parentRow.getParent();
//		   itemposition = listView.getPositionForView(parentRow);
//			Log.i(tag,"Position:-" +itemposition);
			
			// Toast.makeText(context,"meta Tag ==>"+ s,Toast.LENGTH_LONG).show();
			switch (v.getId()) {
			
			     case R.id.imgone:
												 
					final Dialog firstdialog = new Dialog(getContext());
					firstdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
					firstdialog.setContentView(R.layout.layoutforbet);
					firstdialog.show();
					
				    final TextView txtfirstteam=(TextView)firstdialog.findViewById(R.id.txtselectedteam);
				    final TextView txtfirstteamamount=(TextView)firstdialog.findViewById(R.id.txtshowmsginlayout);
				    final EditText editfirstteammatch=(EditText)firstdialog.findViewById(R.id.edmatch1);
				    bidamount = editfirstteammatch.getText().toString();
				    editfirstteammatch.addTextChangedListener(new TextWatcher() {
						
						@Override
						public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
							// TODO Auto-generated method stub
							final TextView txtfirstteamtotal=(TextView)firstdialog.findViewById(R.id.textshowamount);
							txtfirstteamtotal.setTag(arg0);
						}
						
						@Override
						public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
								int arg3) {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void afterTextChanged(Editable arg0) {
							// TODO Auto-generated method stub
							
						}
					});
				    
				    Float finalamountteamone=Float.parseFloat(Firstteampoint.get(0));
					String netamount=Float.toString(finalamountteamone);
					txtfirstteam.setText(FirstTeam.get(0));
					txtfirstteamamount.setText(netamount);
					
				    
					firstdialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							firstdialog.dismiss();
							String tag=null;
//							txtfirstteam.setText(FirstTeam.get(0));
							Float finalamount=Float.parseFloat(Firstteampoint.get(0)) * Integer.parseInt(editfirstteammatch.getText().toString());
							String netamount=Float.toString(finalamount);
							Log.i(tag,"Netamount:-" +netamount);
							txtfirstteamamount.setText(netamount);
							Toast.makeText(context,"Your Amount is ==>" +netamount.toString(),Toast.LENGTH_LONG).show();
						}
					});	
					
					
				break;
			    case R.id.imgsecond:
				final Dialog seconddialog = new Dialog(getContext());
				seconddialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				seconddialog.setContentView(R.layout.layoutforbet);
				seconddialog.show();
				
				
					
				
				final TextView txtsecondteam=(TextView)seconddialog.findViewById(R.id.txtselectedteam);
			    final TextView txtsecondteamamount=(TextView)seconddialog.findViewById(R.id.txtshowmsginlayout);
				final EditText editsecondteammatch=(EditText)seconddialog.findViewById(R.id.edmatch1);
				bidamount = editsecondteammatch.getText().toString();
				
				editsecondteammatch.addTextChangedListener(new TextWatcher() {
					
					@Override
					public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
						// TODO Auto-generated method stub
						TextView txtsecondteamtotal=(TextView) seconddialog.findViewById(R.id.textshowamount);
						txtsecondteamtotal.setText(arg0);
						
					}
					
					@Override
					public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
							int arg3) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void afterTextChanged(Editable arg0) {
						// TODO Auto-generated method stub
						
					}
				});
				
				Float finalamount=Float.parseFloat(Secondteampoint.get(0));
				String netamountforteamsecond=Float.toString(finalamount);
				txtsecondteamamount.setText(netamountforteamsecond);
				txtsecondteam.setText(SecondTeam.get(0));
				
			    seconddialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						seconddialog.dismiss();
						String tag=null;
						Log.i(tag, Secondteampoint.get(0))	;	
						Log.i(tag, Secondteampoint.get(1))	;						
						
						Float finalamount=Float.parseFloat(Secondteampoint.get(0)) * Integer.parseInt(editsecondteammatch.getText().toString());
						
//						String amount=  "100";
//						Log.i(tag,"bidamount:-" +bidamount+ editsecondteammatch.getText().toString());
						Log.i(tag,"Finalamount:-" +finalamount);
						String netamount=Float.toString(finalamount);
						Log.i(tag,"Netamount:-" +netamount);						
						txtsecondteamamount.setText(netamount);
						Toast.makeText(context,"Your Amount is ==>" +netamount.toString(),Toast.LENGTH_LONG).show();
					}
				});
		
			    break;
			default:
				break;
			}
			
		} catch (Exception e) {
//			Toast.makeText(context, e.toString(),Toast.LENGTH_LONG).show();
			
			
		}
		
	}
	
	
}

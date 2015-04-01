package com.example.ipl;


import java.util.ArrayList;
import com.androidquery.AQuery;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class UserDetails extends ArrayAdapter<String> {
	
	
	private final ArrayList<String> UserName;
	private final ArrayList<String> UserPoint;
	private final ArrayList<String> UserImage;
	private Activity context;
	
	
	public UserDetails(Activity context, 
			ArrayList<String> username,
			ArrayList<String> userpoint,
			ArrayList<String> userimage)
	
	{
		super(context, R.layout.userinfo, username);
		this.context = context;
		this.UserName=username;
		this.UserPoint=userpoint;
		this.UserImage=userimage;
	}
	
	public View getView(int position, View view, ViewGroup parent)
	{
		LayoutInflater inflater = context.getLayoutInflater();
		View showDetails = inflater.inflate(R.layout.userinfo, null, true);
		
		ImageView userimage = (ImageView) showDetails.findViewById(R.id.userimage);
		
		AQuery aq = new AQuery(context);
		aq.id(userimage).image(UserImage.get(position));
		
		
		TextView  username=(TextView) showDetails.findViewById(R.id.txtusername) ;
		username.setText(UserName.get(position));
		
		TextView userpoint=(TextView) showDetails.findViewById(R.id.txtuserpoint);
		userpoint.setText(UserPoint.get(position));
		
		
		
		return showDetails;
	}

}

package com.example.ipl;

import java.util.ArrayList;

import android.app.Activity;
import android.widget.ArrayAdapter;

public class SingleItem  extends ArrayAdapter<String>{
	
	
	
	
	
	
	
	private final Activity context;
	
	private final String Matchid;
	

	public SingleItem(Activity context, String matchid)
			
	{
		super(context, R.layout.activity_single_team);
		this.Matchid=matchid;
		this.context = context;
		
	}


}

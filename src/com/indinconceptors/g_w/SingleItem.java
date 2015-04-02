package com.indinconceptors.g_w;

import java.util.ArrayList;

import com.androidquery.AQuery;
import com.indinconceptors.g_w.R;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

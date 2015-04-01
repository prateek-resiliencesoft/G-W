package com.indinconceptors.g_w;



import com.indinconceptors.g_w.R;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class TeamActivity extends ActionBarActivity implements OnClickListener {
ImageView imgone,imgtwo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_team);
		imgone=(ImageView)findViewById(R.id.imgonetest);
		imgone.setOnClickListener(this);
		imgtwo=(ImageView)findViewById(R.id.imgsecondtest);
		imgtwo.setOnClickListener(this);
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		try {
			
			switch (v.getId()) {
			case R.id.imgonetest:
				
				final Dialog firstdialog = new Dialog(TeamActivity.this);
				firstdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
				firstdialog.setContentView(R.layout.layoutforbet);
				firstdialog.show();
				firstdialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						Toast.makeText(TeamActivity.this, "Fields are empty",Toast.LENGTH_SHORT).show();
					}
				});
				break;
				
       case R.id.imgsecondtest:
    	    final Dialog dialog = new Dialog(TeamActivity.this);
    	    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    	    dialog.setContentView(R.layout.layoutforbet);
    	    dialog.show();
    	    dialog.findViewById(R.id.btnbet).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Toast.makeText(TeamActivity.this, "Fields are empty",Toast.LENGTH_SHORT).show();
				}
			});
				break;

			default:
				break;
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

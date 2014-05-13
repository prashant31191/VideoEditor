package com.main.videoeditor.thumbnails;

import android.app.Activity;
import android.net.Uri;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.main.videoeditor.R;

public class Thumb extends ImageView implements View.OnClickListener {
	String moviePath;
    Activity activity;
    VideoView videoView;
	public Thumb(Activity act){
		super(act.getApplicationContext());
        activity = act;
        videoView = (VideoView)activity.findViewById(R.id.videoPlayer);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 2, 0);
        this.setLayoutParams(lp);
	}
	public void setMoviePath(String path){
		moviePath = path;
	}
	public String getMoviePath(){
		return moviePath;
	}
    public void onClick(View v) {
     //   ((HorizontalScrollView) findViewById(R.id.HorizontalScrollTopMenu)).setVisibility(View.GONE);
        setVideo();
    }
    public void setVideo(){
        Uri uri = Uri.parse(this.getMoviePath());
        videoView.setVideoURI(uri);
        videoView.pause();
        videoView.seekTo(1);
    }
}

package com.main.videoeditor.thumbnails;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
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
	}
	public void setMoviePath(String path){
		moviePath = path;
	}
	public String getMoviePath(){
		return moviePath;
	}
    public void onClick(View v) {
        startThisVideo();
    }
    public void startThisVideo(){
        Uri uri = Uri.parse(this.getMoviePath());
      //  ((HorizontalScrollView)activity.findViewById(R.id.HorizontalScrollTopMenu)).setVisibility(View.GONE);
        videoView.setVideoURI(uri);
        videoView.pause();
        videoView.seekTo(1);
    }
}

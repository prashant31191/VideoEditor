package com.main.videoeditor.thumbnails;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

import com.main.videoeditor.R;

public class Thumb extends ImageView implements View.OnClickListener {
	private String moviePath;
    private String movieInfo = "";
    private Activity activity;
    private VideoView videoView;
    private String fileName;
    private int width;
    private int height;
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
        fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
        setMovieInfo();
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
    public String getMovieInfo() {
        return movieInfo;
    }

    private void setMovieInfo() {
        MediaMetadataRetriever info = new MediaMetadataRetriever();
        info.setDataSource(moviePath);
        width = Integer.parseInt(info.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_WIDTH));
        height = Integer.parseInt(info.extractMetadata(MediaMetadataRetriever.METADATA_KEY_VIDEO_HEIGHT));
        movieInfo = "name: " + fileName;
        movieInfo += "\n type: " + info.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE);
        movieInfo += "\n resolution: " + Integer.toString(width) + "x" + Integer.toString(height);
        movieInfo += "\n size: " + Uri.parse(moviePath).get;
    }
}

package com.main.videoeditor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.main.videoeditor.thumbnails.Thumb;
import com.main.videoeditor.thumbnails.Thumbs;


public class MainActivity extends Activity {
	static final int REQUEST_VIDEO_CAPTURE = 1;
	static final int REQUEST_VIDEO_CHOOSE = 2;
    Thumbs thumbs = new Thumbs();
	Activity tt = this;
	int olo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        addThumbs();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public void trimVideo(View view) {
        Intent intent = new Intent(this, TrimVideo.class);
        startActivity(intent);
    }



    public void takeVideo(MenuItem item) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    public void dispatchTakeVideoIntent(View view) {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	olo = 2;
    	if (requestCode == REQUEST_VIDEO_CAPTURE) {
    		requestCode = 1;
    	}
    	else if(requestCode == REQUEST_VIDEO_CHOOSE) {
 //   		 setContentView(R.layout.main);
            ((HorizontalScrollView)findViewById(R.id.HorizontalScrollTopMenu)).setVisibility(View.GONE);
    		    VideoView videoView = (VideoView)findViewById(R.id.videoPlayer);
    		    MediaController mediaController = new MediaController(this);
    		    mediaController.setAnchorView(videoView);        
    		    
    		    videoView.setMediaController(mediaController);
    		    videoView.setVideoURI(data.getData());        
    		    videoView.requestFocus();

    		    videoView.start();
    	/*	
    		Uri selectedVideo = data.getData();
    		MediaPlayer mediaPlayer = new MediaPlayer();
    		try
    		{
    			mediaPlayer.setDataSource(this, selectedVideo);
    			mediaPlayer.start();
    			
    			
    		}catch(Exception e){
    			e.getMessage();
    		}
    		requestCode = 1;*/
    	}
    }
     int t = 0;
    public void addThumbs()
    {
        thumbs.addThumbs(this);
/*
    	String[] STAR = { "*" };
    	
        Cursor cursor = managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                , STAR, null, null, null);

        LinearLayout my_menu = (LinearLayout) findViewById(R.id.topMenu);
        Thumb myImg;// = new ImageView(this);
       // myImg.setImageResource(R.drawable.ic_launcher);
        
        Bitmap bMap;
        if (cursor != null) 
        {
            if (cursor.moveToFirst()) 
            {
                do 
                {
                    String path = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Video.Media.DATA));
                    bMap = ThumbnailUtils.createVideoThumbnail(path,
                    		MediaStore.Video.Thumbnails.MICRO_KIND);
                    
                    myImg = new Thumb(this);
                    myImg.setImageBitmap(bMap);
                    myImg.setMoviePath(path);
                    myImg.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                        	Thumb oo = (Thumb) v;
                        	
                        	VideoView videoView = (VideoView)findViewById(R.id.videoPlayer);
                        	
                        	if(t == 0){
                        	MediaController mediaController = new MediaController(tt);
                		    mediaController.setAnchorView(videoView);        
                		    
                		    videoView.setMediaController(mediaController);
                		    t = 1;
                        	}
                		    Uri uri = Uri.parse(oo.getMoviePath());
                            ((HorizontalScrollView)findViewById(R.id.HorizontalScrollTopMenu)).setVisibility(View.GONE);
                		    	videoView.setVideoURI(uri);
	                		    videoView.start();
                		    
                        }
                    });
                    my_menu.addView(myImg);
                    Log.i("Path",path);
                }while (cursor.moveToNext());

            }
         //   cursor.close();
        }*/
    }
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        LinearLayout my_menu = (LinearLayout) findViewById(R.id.topMenu);
        my_menu.removeAllViews();
        addThumbs();
    }
    public void info(MenuItem item){
        Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
    }
    public void setThumbsVisibility(View view)
    {
        HorizontalScrollView o = (HorizontalScrollView) findViewById(R.id.HorizontalScrollTopMenu);
        if(o.getVisibility() == View.GONE){
            o.setVisibility(View.VISIBLE);
        }
        else {
            o.setVisibility(View.GONE);
        }
    }

    public void viewVideos(View view) {

    	Intent intent = new Intent();
    	intent.setType("video/*");
    	intent.setAction(Intent.ACTION_GET_CONTENT);
    	startActivityForResult(Intent.createChooser(intent, "Select Video"), REQUEST_VIDEO_CHOOSE);
    }
    
}

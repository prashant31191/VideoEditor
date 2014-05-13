package com.main.videoeditor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
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


public class MainActivity extends Activity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener{
	static final int REQUEST_VIDEO_CAPTURE = 1;
	static final int REQUEST_VIDEO_CHOOSE = 2;
    private GestureDetectorCompat mDetector;
    Thumbs thumbs;
	Activity tt = this;
	int olo = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        thumbs = new Thumbs(this);
        addThumbs();

        mDetector = new GestureDetectorCompat(this,this);
        // Set the gesture detector as the double tap
        // listener.
        mDetector.setOnDoubleTapListener(this);
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

     int t = 0;
    public void addThumbs()
    {
        thumbs.addThumbs();
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
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float v, float v2) {
        if (v > 0){ // left
            thumbs.playPrevious();
        }
        else if (v < 0){ //right
            thumbs.playNext();
        }
        return false;
    }
}

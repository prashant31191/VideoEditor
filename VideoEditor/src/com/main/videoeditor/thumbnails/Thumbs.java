package com.main.videoeditor.thumbnails;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.VideoView;

import com.main.videoeditor.R;

/**
 * Created by admin on 2014-05-13.
 */
public class Thumbs {
    int t = 0;
    public void addThumbs(final Activity activity)
    {

        String[] STAR = { "*" };

        Cursor cursor = activity.managedQuery(MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                , STAR, null, null, null);

        LinearLayout my_menu = (LinearLayout)  activity.findViewById(R.id.topMenu);
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

                    myImg = new Thumb(activity.getApplicationContext());
                    myImg.setImageBitmap(bMap);
                    myImg.setMoviePath(path);
                    myImg.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            Thumb oo = (Thumb) v;

                            VideoView videoView = (VideoView)activity.findViewById(R.id.videoPlayer);

                            if(t == 0){
                                MediaController mediaController = new MediaController(activity);
                                mediaController.setAnchorView(videoView);

                                videoView.setMediaController(mediaController);
                                t = 1;
                            }
                            Uri uri = Uri.parse(oo.getMoviePath());
                            ((HorizontalScrollView)activity.findViewById(R.id.HorizontalScrollTopMenu)).setVisibility(View.GONE);
                            videoView.setVideoURI(uri);
                            videoView.start();

                        }
                    });
                    my_menu.addView(myImg);
                    Log.i("Path", path);
                }while (cursor.moveToNext());

            }
            //   cursor.close();
        }
    }
}

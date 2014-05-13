package com.main.videoeditor.thumbnails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Thumb extends ImageView{
	String moviePath;
	public Thumb(Context savedInstanceState) {
		super(savedInstanceState);
	}
	public void setMoviePath(String path){
		moviePath = path;
	}
	public String getMoviePath(){
		return moviePath;
	}
}

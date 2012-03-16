package cs310w10.MoleFinder.View;

import java.util.Date;

import android.net.Uri;


public class PictureController {
	Picture picture;
	
	public PictureController(Picture picture){
		this.picture = picture;
	}
	
	public String getDateAsString(){
		Date date = picture.getDate();
		return date.toGMTString();
	}
	
	public long getDateAslong(){
		Date date = picture.getDate();
		return date.getTime();
	}
	
	
	public String getUriAsString(){
		Uri uri = picture.getImageData();
		return uri.toString();
	}
}

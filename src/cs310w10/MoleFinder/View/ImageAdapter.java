package cs310w10.MoleFinder.View;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import cs310w10.MoleFinder.Controller.PictureController;
import cs310w10.MoleFinder.Model.Picture;

/**
 * @author Claire Semple
 * @see Adapted from Sai Geetha's blog: http://saigeethamn.blogspot
 *      .ca/2010/05/gallery-view-android-developer-tutorial.html
 */
public class ImageAdapter extends BaseAdapter {
	private final List<Picture> list;
	private final Context context;

	public ImageAdapter(Context context, List<Integer> listids) {
		list = new ArrayList<Picture>();
		PictureController controller = new PictureController(context);
		for (Integer i : listids) {
			list.add(controller.getPictureFromId(i));
		}
		this.context = context;
	}

	public int getCount() {
		return list.size();
	}

	public Picture getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return list.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageview = new ImageView(context);
		imageview.setImageURI(list.get(position).getImageData());
		imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageview.setLayoutParams(new Gallery.LayoutParams(320, 320));
		return imageview;

	}

}

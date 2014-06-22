package ua.dev.vk.photo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.dev.vk.photo.app.R;
import ua.dev.vk.photo.app.model.Photo;

public class PhotoAdapter extends ArrayAdapter<Photo> {
    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Photo> photos;

    public PhotoAdapter(Context context, int resource, ArrayList<Photo> objects) {
        super(context, resource, objects);
        this.context = context;
        this.photos = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_photo_items, null);
        }

        final ViewHolder viewHolder = new ViewHolder(convertView);
        Photo photo = getItem(position);
        Picasso.with(context).load(photo.getUrl())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .fit()
                .into(viewHolder.photo, new Callback() {
                    @Override
                    public void onSuccess() {
                        viewHolder.photo.setVisibility(View.VISIBLE);
                        viewHolder.bar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        viewHolder.photo.setVisibility(View.GONE);
                        viewHolder.bar.setVisibility(View.VISIBLE);
                    }
                });

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.pb_photo_loading)
        ProgressBar bar;
        @InjectView(R.id.image_album_photo)
        ImageView photo;

        ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}

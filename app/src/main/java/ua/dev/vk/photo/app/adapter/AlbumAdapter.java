package ua.dev.vk.photo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.dev.vk.photo.app.R;
import ua.dev.vk.photo.app.model.PhotoAlbum;

/**
 * Created by 1 on 21.06.2014.
 */
public class AlbumAdapter extends ArrayAdapter<PhotoAlbum> {
    private Context context;
    private ArrayList<PhotoAlbum> albums;
    private LayoutInflater layoutInflater;

    public AlbumAdapter(Context context, int resource, ArrayList<PhotoAlbum> objects) {
        super(context, resource, objects);
        this.context = context;
        this.albums = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_album_items, null);
        }
        ViewHolder holder = new ViewHolder(convertView);

        PhotoAlbum album = getItem(position);
        holder.albumTitle.setText(album.getTitle());
        Picasso.with(context).load(album.getThumbnailUrl()).into(holder.icon);

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.images)
        ImageView icon;
        @InjectView(R.id.text_album_name)
        TextView albumTitle;

        ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}

package ua.dev.vk.photo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ua.dev.vk.photo.app.R;
import ua.dev.vk.photo.app.model.PhotoAlbum;

public class AlbumAdapter extends ArrayAdapter<PhotoAlbum> {
    private final Context context;
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
        final ViewHolder holder = new ViewHolder(convertView);

        final PhotoAlbum album = getItem(position);
        holder.albumTitle.setText(album.getTitle());
        Picasso.with(context).load(album.getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .fit()
                .into(holder.icon, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.icon.setVisibility(View.VISIBLE);
                        holder.bar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        holder.bar.setVisibility(View.VISIBLE);
                        holder.icon.setVisibility(View.GONE);
                    }
                });

        return convertView;
    }

    class ViewHolder {
        @InjectView(R.id.images)
        ImageView icon;
        @InjectView(R.id.text_album_name)
        TextView albumTitle;
        @InjectView(R.id.pb_loading)
        ProgressBar bar;

        ViewHolder(View view){
            ButterKnife.inject(this, view);
        }
    }
}

package com.piticlistudio.androidavengers.comics.ui.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.piticlistudio.androidavengers.R;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * RecyclerView adapter for comics.
 * Created by jorge.garcia on 13/07/2016.
 */
public class ComicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Picasso picasso;
    private List<Comic> data;
    private IComicListAdapterClickListener listener;

    @Inject
    public ComicListAdapter(Picasso picasso) {
        this.picasso = picasso;
    }

    public List<Comic> getData() {
        return data;
    }

    public void setData(List<Comic> data) {
        this.data = data;
    }

    public void setListener(IComicListAdapterClickListener listener) {
        this.listener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.comic_list_adapter_cell, parent, false);
        ObjectViewHolder viewHolder = new ObjectViewHolder(v);
        viewHolder.itemView.setOnClickListener(v1 -> {
            int adapterPos = viewHolder.getAdapterPosition();
            if (adapterPos != RecyclerView.NO_POSITION) {
                onItemClick(v1, adapterPos);
            }
        });
        return viewHolder;
    }

    private void onItemClick(View v, int position) {
        Comic item = data.get(position);
        if (listener != null) {
            listener.onItemClick(item, position, v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ObjectViewHolder viewHolder = (ObjectViewHolder) holder;
        Comic comic = data.get(position);
        viewHolder.title.setText(comic.getTitle());
        picasso.load(comic.getThumbnailUri())
                .fit()
                .centerInside()
                .into(viewHolder.image);
    }

    @Override
    public int getItemCount() {
        if (data == null || data.isEmpty())
            return 0;
        return data.size();
    }

    public interface IComicListAdapterClickListener {

        /**
         * Callback whenever a comic item has been clicked.
         *
         * @param item     the item clicked.
         * @param position the position within the data.
         * @param v        the view touched.
         */
        void onItemClick(Comic item, int position, View v);
    }

    /**
     * Holder for data items
     */
    private static class ObjectViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView image;

        ObjectViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            image = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}

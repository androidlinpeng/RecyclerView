package com.msgcopy.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by liang on 2017/3/3.
 */
public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private Context context;

    private List<String> mlist;

    private LayoutInflater mInflater;

    public GalleryAdapter(Context context, List<String> mlist) {
        this.context = context;
        this.mlist = mlist;
        mInflater = LayoutInflater.from(this.context);
    }

    @Override
    public GalleryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.item_gallery_recycler, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(view, (int) view.getTag(),1);
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemClickListener.onItemClick(view, (int) view.getTag(),2);
                return true;
            }
        });
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String text = mlist.get(position).toString();
        holder.textView.setText(text);
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.item_tv);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view,int position,int flag);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void addItem(String text,int position){
        mlist.add(position,text);
        notifyItemInserted(position);
    }

    public void removeItem(int position){
        mlist.remove(position);
        notifyItemRemoved(position);
    }

}


















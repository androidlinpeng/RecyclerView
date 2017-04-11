package com.msgcopy.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liang on 2017/3/3.
 */
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;

    private List<String> mlist;

    private LayoutInflater mInflater;

    //普通ITEM
    private static final int ITEM_VIEW = 1;
    //FOOT ITEM
    private static final int FOOT_VIEW = 2;

    public Adapter(Context context, List<String> mlist) {
        this.context = context;
        this.mlist = mlist;
        mInflater = LayoutInflater.from(this.context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW) {
            View view = mInflater.inflate(R.layout.advance_item_layout, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == FOOT_VIEW) {
            View view = mInflater.inflate(R.layout.instance_load_more_layout, parent, false);
            return new FootViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            final String text = mlist.get(position).toString();
            ((ItemViewHolder) holder).textView.setText(text);
            ((ItemViewHolder) holder).textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(text);
                }
            });
        }else if (holder instanceof FootViewHolder){

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position+1==getItemCount()) {
            return FOOT_VIEW;
        }else {
            return ITEM_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return mlist.size()+1;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView2);
        }
    }
    public static class FootViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout fool_Layout;

        public FootViewHolder(View view) {
            super(view);
            fool_Layout = (LinearLayout) view.findViewById(R.id.fool_Layout);
        }
    }

    public interface OnItemClickListener{
        public void onItemClick(String text);
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public void addRefreshBeans(List<String> list){
        List<String> temp = new ArrayList<String>();
        temp.addAll(list);
        temp.addAll(mlist);
        mlist.removeAll(mlist);
        mlist.addAll(temp);
        notifyDataSetChanged();
    }

    public void addMoreBeans(List<String> list) {
        mlist.addAll(list);
        notifyDataSetChanged();
    }

}


















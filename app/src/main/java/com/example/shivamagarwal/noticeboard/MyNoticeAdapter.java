package com.example.shivamagarwal.noticeboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shivamagarwal.noticeboard.R;

import java.util.ArrayList;

public class MyNoticeAdapter extends RecyclerView.Adapter<MyNoticeAdapter.ViewHolder>{
    ArrayList<String> headings,postedons,postedbys,messages;
    static Context context;
    ViewHolder vh;
    View view;
    public MyNoticeAdapter(Context context, ArrayList<String> headings, ArrayList<String> postedons, ArrayList<String> postedbys, ArrayList<String> messages){
        this.headings=headings;
        this.postedons=postedons;
        this.postedbys=postedbys;
        this.messages=messages;
        this.context=context;
    }

    @NonNull
    @Override
    public MyNoticeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= LayoutInflater.from(context).inflate(R.layout.notice_card_view,parent,false);
        vh=new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.heading.setText(headings.get(position));
        holder.postedon.setText(postedons.get(position));
        holder.postedby.setText(postedbys.get(position));
        holder.message.setText(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return headings.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView heading,postedon,postedby,message;
        public ViewHolder(View v){
            super(v);
            heading=v.findViewById(R.id.sa_tv1);
            postedon=v.findViewById(R.id.sa_tv2);
            postedby=v.findViewById(R.id.sa_tv3);
            message=v.findViewById(R.id.sa_tv4);
        }
    }
}
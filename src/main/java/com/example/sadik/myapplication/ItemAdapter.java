package com.example.sadik.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<Item> mItem;
private OnItemClickListener mListener;

    public void setOnItemClickListener(MainActivity mainActivity) {
        mListener=mainActivity;
    }

    public interface OnItemClickListener{

        void onItemClick(int position);
    }
    public void SetOnItemClickListener(OnItemClickListener listener){
        mListener=listener;



    }

    public ItemAdapter(Context context,ArrayList<Item> Item){
        mContext=context;
        mItem=Item;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.cardview_item,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

            Item currentItem=mItem.get(position);

            String imageUrl=currentItem.getmImageUrl();
            String creatorName=currentItem.getmCreator();
            String content=currentItem.getmContent();
            holder.mTextViewCreator.setText(creatorName);
            holder.mTextViewContent.setText(content);
           Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mItem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewContent;


        public MyViewHolder(View itemView) {
            super(itemView);

            mImageView=itemView.findViewById(R.id.newsimage);
            mTextViewCreator=itemView.findViewById(R.id.recyclerviewtitle);
            mTextViewContent=itemView.findViewById(R.id.recyclerviewcontent);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   if (mListener!=null){int position=getAdapterPosition();
                   if (position!=RecyclerView.NO_POSITION){mListener.onItemClick(position);}}
               }
           });
        }
    }
}

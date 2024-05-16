package com.syariahrooms;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.HashMap;


public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public ImageViewAdapter(Context context, ArrayList<HashMap<String, String>> list_data) {
        this.context = context;
        this.list_data = list_data;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listimage, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(list_data.get(position).get("gambar"))
                .into(holder.imghape);
        holder.imghape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
//                        View mView = LayoutInflater.from(context).inflate(R.layout.dialog_custom_layout, null);
//                        PhotoView photoView = mView.findViewById(R.id.imageView);
//                        photoView.setImageResource(R.drawable.logo);
//                        mBuilder.setView(mView);
//                        AlertDialog mDialog = mBuilder.create();
//                        mDialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imghape, imghotel;

        public ViewHolder(View itemView) {
            super(itemView);
            imghape = (ImageView) itemView.findViewById(R.id.imagehoteldetail);

        }
    }
}
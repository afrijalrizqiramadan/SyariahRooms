package com.syariahrooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.syariahrooms.ui.home.FilterHotelActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class RVArticelAdapter extends RecyclerView.Adapter<RVArticelAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public RVArticelAdapter(Context context, ArrayList<HashMap<String, String>> list_data) {
        this.context = context;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_article_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(list_data.get(position).get("gambar"))
                .into(holder.imghape);
        holder.txthape.setText(list_data.get(position).get("nama"));

        holder.linarticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, articleActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("judul", list_data.get(position).get("nama"));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape;
        ImageView imghape;
LinearLayout linarticle;
        public ViewHolder(View itemView) {
            super(itemView);
            txthape = (TextView) itemView.findViewById(R.id.titlearticle);
            imghape = (ImageView) itemView.findViewById(R.id.image_article_list);
            linarticle=(LinearLayout) itemView.findViewById(R.id.lin_article_list);
        }
    }
}
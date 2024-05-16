package com.syariahrooms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public RecyclerViewAdapter(Context context, ArrayList<HashMap<String, String>> list_data) {
        this.context = context;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listfasilitas, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(list_data.get(position).get("gambar"))
                .into(holder.imghape);
        holder.txthape.setText(list_data.get(position).get("nama"));
    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape;
        ImageView imghape;

        public ViewHolder(View itemView) {
            super(itemView);

            txthape = (TextView) itemView.findViewById(R.id.tv_namafasilitas);
            imghape = (ImageView) itemView.findViewById(R.id.tv_gambarfasilitas);
        }
    }
}
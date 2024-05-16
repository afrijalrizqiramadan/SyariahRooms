package com.syariahrooms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;


public class RVCheckoutAdapter extends RecyclerView.Adapter<RVCheckoutAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public RVCheckoutAdapter(Context context, ArrayList<HashMap<String, String>> list_data) {
        this.context = context;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_checkout_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txthape.setText(list_data.get(position).get("nama"));
        String keterangan = list_data.get(position).get("hargaasli") + " @" + list_data.get(position).get("night") + " Malam x " + list_data.get(position).get("jumlahkamar") + " Kamar";
        holder.keterangan.setText(keterangan);
        holder.hargatotal.setText(list_data.get(position).get("hargatotal"));

    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape, keterangan, hargatotal;
        Button buttonlihat;

        public ViewHolder(View itemView) {
            super(itemView);
            txthape = (TextView) itemView.findViewById(R.id.namatipe);
            keterangan = (TextView) itemView.findViewById(R.id.keterangan);
            hargatotal = (TextView) itemView.findViewById(R.id.hargatotalperkamar);


        }
    }
}
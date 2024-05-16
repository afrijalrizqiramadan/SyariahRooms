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
import com.syariahrooms.ui.home.FilterHotelActivity;

import java.util.ArrayList;
import java.util.HashMap;


public class RVHotelAdapter extends RecyclerView.Adapter<RVHotelAdapter.ViewHolder> {

    Context context;
    ArrayList<HashMap<String, String>> list_data;

    public RVHotelAdapter(Context context, ArrayList<HashMap<String, String>> list_data) {
        this.context = context;
        this.list_data = list_data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_hotel_list, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(list_data.get(position).get("gambar"))
                .into(holder.imghape);
        holder.txthape.setText(list_data.get(position).get("nama"));
        holder.tvkota.setText("Lowokwaru, Malang");

        holder.tvhargaasli.setText(list_data.get(position).get("hargaasli"));
        holder.buttonlihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HotelDetailActivity.class);
                Bundle a=new Bundle();
                a.putString("checkin", list_data.get(position).get("checkin"));
                a.putString("checkout", list_data.get(position).get("checkout"));
                a.putString("tamu",String.valueOf(list_data.get(position).get("tamu")));
                a.putString("kamar",String.valueOf(list_data.get(position).get("kamar")));
                a.putString("hargamulai", String.valueOf(list_data.get(position).get("hargaasli")));
                a.putString("totalmalam", String.valueOf(list_data.get(position).get("totalmalam")));
                a.putString("keyhotel", String.valueOf(list_data.get(position).get("keyhotel")));
                intent.putExtras(a);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape, tvhargaasli,tvkota;
        ImageView imghape;
        Button buttonlihat;
        public ViewHolder(View itemView) {
            super(itemView);
            txthape = (TextView) itemView.findViewById(R.id.text_view_hotel_name);
            imghape = (ImageView) itemView.findViewById(R.id.image_hotel_list);
            tvhargaasli = (TextView) itemView.findViewById(R.id.labelhargaasli);
            buttonlihat = (Button) itemView.findViewById(R.id.btlihat);
            tvkota = (TextView) itemView.findViewById(R.id.text_view_hotel_city);


        }
    }
}
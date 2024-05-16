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
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.syariahrooms.RoomListActivity.evenNumbers;


public class RVRoomAdapter extends RecyclerView.Adapter<RVRoomAdapter.ViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<Item> itemList;
    List<arraycheckout> checkout = new ArrayList<>();
    private Context context;
    int counter = 0;

    public RVRoomAdapter(Context context, List<Item> itemList) {
        this.itemList = itemList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_room_list, null);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item item = itemList.get(position);

        Glide.with(context)
                .load(item.getGambar())
                .into(holder.imghape);
        holder.txthape.setText(item.getNama());
        holder.kapasitas.setText(item.getkapasitas());
        holder.sisakamar.setText(item.getSisakamar());
        holder.tvhargaasli.setText(item.getHargapromo());

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rvfasilitaskamar.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );

        layoutManager.setInitialPrefetchItemCount(item.getSubItemList().size());

        FasilitasKamarAdapter subItemAdapter = new FasilitasKamarAdapter(context, item.getSubItemList());

        holder.rvfasilitaskamar.setLayoutManager(layoutManager);
        holder.rvfasilitaskamar.setAdapter(subItemAdapter);
        holder.rvfasilitaskamar.setRecycledViewPool(viewPool);
        holder.btcount.setText("" + counter);
        holder.btplus.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String text = holder.btcount.getText().toString();
                int icounter = Integer.parseInt(text);
                if (icounter != Integer.parseInt(item.getSisakamar())) {
                    icounter++;
                    holder.btcount.setText("" + icounter);
                    evenNumbers.put(item.getTipe(), icounter);
//                    arraycheckout varcheckout = new arraycheckout(item.getTipe(),String.valueOf(icounter));
//                    checkout.add(varcheckout);

                }
            }
        });

        holder.btmin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String text = holder.btcount.getText().toString();
                int icounter = Integer.parseInt(text);
                if (icounter != 0) {
                    icounter--;
                    holder.btcount.setText("" + icounter);
                    evenNumbers.put(item.getTipe(), icounter);
//                    arraycheckout varcheckout = new arraycheckout(item.getTipe(),String.valueOf(icounter));
//                    checkout.add(varcheckout);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape, tvhargaasli, kapasitas, sisakamar;
        ImageView imghape;
        RecyclerView rvfasilitaskamar;
        Button btcount, btmin, btplus;

        public ViewHolder(View itemView) {
            super(itemView);
            txthape = (TextView) itemView.findViewById(R.id.text_view_room_name);
            imghape = (ImageView) itemView.findViewById(R.id.image_view_room_picture);
            tvhargaasli = (TextView) itemView.findViewById(R.id.labelhargaasli);
            kapasitas = (TextView) itemView.findViewById(R.id.kapasitasorang);
            sisakamar = (TextView) itemView.findViewById(R.id.sisakamar);
            btcount = (Button) itemView.findViewById(R.id.btnumber);
            btmin = (Button) itemView.findViewById(R.id.btmin);
            btplus = (Button) itemView.findViewById(R.id.btplus);
            rvfasilitaskamar = (RecyclerView) itemView.findViewById(R.id.rvfasilitaskamar);

        }
    }
}
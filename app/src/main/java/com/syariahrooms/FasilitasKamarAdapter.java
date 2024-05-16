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

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FasilitasKamarAdapter extends RecyclerView.Adapter<FasilitasKamarAdapter.ViewHolder> {

    Context context;
    private List<SubItem> subItemList;

    FasilitasKamarAdapter(Context context, List<SubItem> subItemList) {
        this.subItemList = subItemList;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listfasilitaskamar, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubItem subItem = subItemList.get(position);
        holder.txthape.setText(subItem.getSubItemTitle());

        Glide.with(context)
                .load(subItem.getSubItemDesc())
                .into(holder.imghape);
    }

    @Override
    public int getItemCount() {
        return subItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txthape;
        ImageView imghape;

        public ViewHolder(View itemView) {
            super(itemView);
            txthape = (TextView) itemView.findViewById(R.id.textfasilitaskamar);
            imghape = (ImageView) itemView.findViewById(R.id.gambarfasilitaskamar);

        }
    }
}
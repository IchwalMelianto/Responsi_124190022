package com.responsi.covidjawabarat.Hospital;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.responsi.covidjawabarat.Hospital.DataItem;
import com.responsi.covidjawabarat.R;

import java.util.ArrayList;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private ArrayList<DataItem> dataItems = new ArrayList<>();
    private Context context;

    public HospitalAdapter(Context context, List<DataItem> dataItems) {
        this.context = context;
        this.dataItems = (ArrayList<DataItem>) dataItems;
    }

    public void setData(ArrayList<DataItem> items){
        dataItems.clear();
        dataItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public HospitalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_hospital,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HospitalAdapter.ViewHolder holder, int position) {
        holder.tvRS.setText(dataItems.get(position).getNama());
        holder.tvAlamat.setText(dataItems.get(position).getAlamat());
        holder.bMaps.setOnClickListener(view -> {
            String address = String.format("geo: 0, 0?q= %s", dataItems.get(position).getNama());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
            intent.setPackage("com.google.android.apps.maps");
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return dataItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRS, tvAlamat;
        private Button bMaps;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvRS = itemView.findViewById(R.id.tv_rs);
            tvAlamat = itemView.findViewById(R.id.tv_allalamat);
            bMaps = itemView.findViewById(R.id.btn_maps);
        }
    }
}

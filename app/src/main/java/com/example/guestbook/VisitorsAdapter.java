package com.example.guestbook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VisitorsAdapter extends RecyclerView.Adapter<VisitorsAdapter.ViewHolder> {

    private final ArrayList<Visitors> listVisitors;

    public VisitorsAdapter(ArrayList<Visitors> listVisitors) {
        this.listVisitors = listVisitors;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Visitors visitors = listVisitors.get(position);
        viewHolder.nama.setText(visitors.getNama());
        //viewHolder.provinsi.setText(visitors.getProvinsi());
        //viewHolder.kehadiran.setText(visitors.getKehadiran());
    }

    @Override
    public int getItemCount() {
        return listVisitors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView nama,provinsi,kehadiran;
        public ViewHolder(@NonNull View view) {
            super(view);

            nama = view.findViewById(R.id.name_Visitors);
            provinsi = view.findViewById(R.id.provinsi_Visitors);
            kehadiran = view.findViewById(R.id.kehadiran_Visitors);
        }
    }
}

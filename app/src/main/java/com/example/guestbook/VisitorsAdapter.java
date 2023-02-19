package com.example.guestbook;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.guestbook.databinding.ItemDataBinding;
import com.example.guestbook.model.VisitorResultsItem;

import java.util.ArrayList;
import java.util.List;

public class VisitorsAdapter extends RecyclerView.Adapter<VisitorsAdapter.ViewHolder> {

    private final ArrayList<VisitorResultsItem> listVisitor = new ArrayList<>();

    public void setModel(List<VisitorResultsItem> visitor){
        listVisitor.addAll(visitor);
    }
//    public VisitorsAdapter(ArrayList<DataItem> listVisitor){
//        this.listVisitor = listVisitor;
//    }

    @NonNull
    @Override
    public VisitorsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemDataBinding binding = ItemDataBinding.inflate(LayoutInflater.from(viewGroup.getContext()), viewGroup, false);
        return new ViewHolder(binding);
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_data, viewGroup, false);
//        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VisitorsAdapter.ViewHolder holder, int position) {
        VisitorResultsItem model = listVisitor.get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount() {
        return listVisitor.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemDataBinding binding;

        public ViewHolder(ItemDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
        void bind(VisitorResultsItem model) {
            binding.nameVisitors.setText(model.getNama());
            binding.provinsiVisitors.setText(model.getProvinsi());
            binding.kehadiranVisitors.setText(model.getKehadiran());
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_VISITOR, model);
                itemView.getContext().startActivity(intent);
            });
        }
    }
}

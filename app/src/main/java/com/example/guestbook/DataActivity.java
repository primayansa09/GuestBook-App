package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.guestbook.databinding.ActivityDataBinding;
import com.example.guestbook.model.VisitorResultsItem;

import java.util.ArrayList;

public class DataActivity extends AppCompatActivity {

    public static final String TAG = DataActivity.class.getSimpleName();
    private ActivityDataBinding binding;
    private VisitorViewModel visitorViewModel;
    private VisitorsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        binding = ActivityDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, layoutManager.getOrientation());
//        binding.lytBackgroudData.rvAllData.addItemDecoration(itemDecoration);

        visitorViewModel = new ViewModelProvider(this).get(VisitorViewModel.class);
        adapter = new VisitorsAdapter();
        visitorViewModel.getVisitor().observe(this, visitor -> {
            if (visitor != null){
                setVisitorData(visitor);
                adapter.setModel(visitor);
                showLoading(false);
                showNoData(false);
            }
        });
        binding.lytToolbarData.back.setOnClickListener(view -> {
            finish();
        });

        showRecyclerlist();
        showLoading(true);
        showNoData(true);


//        visitorViewModel.isLoading().observe(this, isLoading ->{
//            showLoading(isLoading);
//        });
//
//        visitorViewModel.isNoData().observe(this, isNoData ->{
//            showNoData(isNoData);
//        });

    }
    private void setVisitorData(java.util.List<VisitorResultsItem> dataItems){
        ArrayList<VisitorResultsItem> listItem = new ArrayList<>();
        for (VisitorResultsItem response : dataItems){
            VisitorResultsItem item = new VisitorResultsItem(
                    response.getNama(),
                    response.getNoHp(),
                    response.getEmail(),
                    response.getAlamat(),
                    response.getProvinsi(),
                    response.getKotaKabupaten(),
                    response.getKecamatan(),
                    response.getKelurahan(),
                    response.getKodePos(),
                    response.getKehadiran());
            listItem.add(item);

        }
        binding.lytBackgroudData.rvAllData.setAdapter(adapter);
    }

    private void showRecyclerlist() {
        binding.lytBackgroudData.rvAllData.setHasFixedSize(true);
        binding.lytBackgroudData.rvAllData.setLayoutManager(new LinearLayoutManager(this));

    }

    private void showLoading(Boolean state) {
        if (state){
            binding.lytBackgroudData.progressBar.setVisibility(View.VISIBLE);
        }else {
            binding.lytBackgroudData.progressBar.setVisibility(View.INVISIBLE);
        }
    }
    private void showNoData(Boolean state){
        if ((state)){
            binding.lytBackgroudData.imgNoDataId.setVisibility(View.VISIBLE);
            binding.lytBackgroudData.txtNoData.setVisibility(View.VISIBLE);
        }else {
            binding.lytBackgroudData.imgNoDataId.setVisibility(View.INVISIBLE);
            binding.lytBackgroudData.txtNoData.setVisibility(View.INVISIBLE);
        }
    }
}
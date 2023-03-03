package com.example.guestbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.guestbook.API.APIConfigProvinsi;
import com.example.guestbook.API.ApiConfig;
import com.example.guestbook.databinding.ActivityHomeBinding;
import com.example.guestbook.model.KecamatanItem;
import com.example.guestbook.model.KecamatanResponse;
import com.example.guestbook.model.KelurahanItem;
import com.example.guestbook.model.KelurahanResponse;
import com.example.guestbook.model.KotaKabupatenItem;
import com.example.guestbook.model.KotaResponse;
import com.example.guestbook.model.ProvinsiItem;
import com.example.guestbook.model.VisitorResponse;
import com.example.guestbook.model.VisitorResultsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity implements View.OnClickListener {
    private ActivityHomeBinding binding;
    private static final String TAG = MainActivity.class.getSimpleName();
    private ProvinsiViewModel provinsiViewModel;
    NotificatiionCounter notificatiionCounter;
//    private ProgressDialog progressDialog;

    ArrayList<Integer> listIdProvinsi = new ArrayList<>();
    ArrayList<String> listNamaProvinsi = new ArrayList<>();
    ArrayList<Integer> listIdKota = new ArrayList<>();
    ArrayList<String> listNamaKota = new ArrayList<>();
    ArrayList<Integer> listIdKecamatan = new ArrayList<>();
    ArrayList<String> listNamaKecamatan = new ArrayList<>();
    ArrayList<Integer> listIdKelurahan = new ArrayList<>();
    ArrayList<String> listNamaKelurahan = new ArrayList<>();


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        binding.toolbar.imgDocument.setOnClickListener(this);
        binding.btnSubmit.setOnClickListener(this);

        notificatiionCounter = new NotificatiionCounter(findViewById(R.id.notificationNumber));

        provinsiViewModel = new ViewModelProvider(this).get(ProvinsiViewModel.class);
        provinsiViewModel.getProvinsi().observe(this, provinsi ->{
            if (provinsi != null){
                setProvinsi(provinsi);
            }
        });
        showLoading(false);
        showNotification(false);
    }

    private void postData(VisitorResultsItem visitorResultsItem) {
        showLoading(true);
       Call<VisitorResponse> client = ApiConfig.getApiService().postVisitors(visitorResultsItem);
       client.enqueue(new Callback<VisitorResponse>() {
           @Override
           public void onResponse(Call<VisitorResponse> call, Response<VisitorResponse> response) {
               showLoading(false);
                if (response.isSuccessful()){
                    if (response.body() != null){
                        Toast toast = Toast.makeText(Home.this, "Save Berhasil", Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL,0,0);
                        toast.show();
                    }
                }
           }

           @Override
           public void onFailure(Call<VisitorResponse> call, Throwable t) {
               showLoading(false);
               Log.e(TAG, "onFailure: " + t.getMessage());
//               Toast toast = Toast.makeText(Home.this, "Save Gagal", Toast.LENGTH_SHORT);
//               toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL,0,0);
//               toast.show();
           }
       });
    }

    private void removeField() {
        binding.txtInputEditTextNama.setText("");
        binding.txtInputEditTextNoHp.setText("");
        binding.txtInputEditTextEmail.setText("");
        binding.txtInputEditTextAlamat.setText("");
        binding.dropdownProvinsi.setText("");
        binding.dropdownKotaKabupaten.setText("");
        binding.dropdownKecamatan.setText("");
        binding.dropdownKelurahan.setText("");
        binding.txtInputEditTextKodePos.setText("");
    }


    private VisitorResultsItem createVisitor(){
        VisitorResultsItem resultsItem = new VisitorResultsItem();
        int kodePos = Integer.parseInt(binding.txtInputEditTextKodePos.getText().toString().trim());
        resultsItem.setNama(binding.txtInputEditTextNama.getText().toString().trim());
        resultsItem.setNoHp(binding.txtInputEditTextNoHp.getText().toString().trim());
        resultsItem.setEmail(binding.txtInputEditTextEmail.getText().toString().trim());
        resultsItem.setAlamat(binding.txtInputEditTextAlamat.getText().toString().trim());
        resultsItem.setProvinsi(binding.dropdownProvinsi.getText().toString().trim());
        resultsItem.setKotaKabupaten(binding.dropdownKotaKabupaten.getText().toString().trim());
        resultsItem.setKecamatan(binding.dropdownKecamatan.getText().toString().trim());
        resultsItem.setKelurahan(binding.dropdownKelurahan.getText().toString().trim());
        resultsItem.setKodePos(kodePos);

        return resultsItem;
    }

    private void setProvinsi(java.util.List<ProvinsiItem> provinsi) {
        listNamaProvinsi.clear();
        listIdProvinsi.clear();
        for (ProvinsiItem response : provinsi){
            listIdProvinsi.add(response.getId());
            listNamaProvinsi.add(response.getNama());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listNamaProvinsi);
        binding.dropdownProvinsi.setAdapter(arrayAdapter);

        binding.dropdownProvinsi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showDataKota(listIdProvinsi.get(position));
            }
        });
    }

    private void showDataKota(int id) {
        Call<KotaResponse> client = APIConfigProvinsi.getApiService().getKota_Kabupaten(id);
        client.enqueue(new Callback<KotaResponse>() {
            @Override
            public void onResponse(Call<KotaResponse> call, retrofit2.Response<KotaResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        setKotaKabupaten(response.body().getKotaKabupaten());

                    }
                }else {
                    if (response.body() != null){
                        Log.e(TAG, "onFailure: " + response.body().getKotaKabupaten());
                    }
                }
            }

            @Override
            public void onFailure(Call<KotaResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setKotaKabupaten(List<KotaKabupatenItem> kotaKabupaten) {
        listNamaKota.clear();
        listIdKota.clear();
        for (KotaKabupatenItem response:kotaKabupaten) {
            listIdKota.add(response.getId());
            listNamaKota.add(response.getNama());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listNamaKota);
        binding.dropdownKotaKabupaten.setAdapter(arrayAdapter);

        binding.dropdownKotaKabupaten.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showDataKecamatan(listIdKota.get(position));
            }
        });
    }

    private void showDataKecamatan(int id) {
        Call<KecamatanResponse> client = APIConfigProvinsi.getApiService().getKecamatan(id);
        client.enqueue(new Callback<KecamatanResponse>() {
            @Override
            public void onResponse(Call<KecamatanResponse> call, retrofit2.Response<KecamatanResponse> response) {
                if (response.isSuccessful()){
                    if(response.body() != null){
                        setKecamatan(response.body().getKecamatan());
                    }
                }else {
                    if (response.body() != null){
                        Log.e(TAG, "onFailure: " + response.body().getKecamatan());
                    }
                }
            }

            @Override
            public void onFailure(Call<KecamatanResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setKecamatan(List<KecamatanItem> kecamatan) {
        listIdKecamatan.clear();
        listNamaKecamatan.clear();
        for (KecamatanItem response:kecamatan) {
            listIdKecamatan.add(response.getId());
            listNamaKecamatan.add(response.getNama());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listNamaKecamatan);
        binding.dropdownKecamatan.setAdapter(arrayAdapter);

        binding.dropdownKecamatan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                showDataKelurahan(listIdKecamatan.get(position));
            }
        });
    }

    private void showDataKelurahan(int id) {
        Call<KelurahanResponse> client = APIConfigProvinsi.getApiService().getKelurahan(id);
        client.enqueue(new Callback<KelurahanResponse>() {
            @Override
            public void onResponse(Call<KelurahanResponse> call, retrofit2.Response<KelurahanResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        setKelurahan(response.body().getKelurahan());
                    }
                }else {
                    if (response.body() != null){
                        Log.e(TAG, "onFailure: " + response.body().getKelurahan());
                    }
                }
            }

            @Override
            public void onFailure(Call<KelurahanResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    private void setKelurahan(List<KelurahanItem> kelurahan) {
        listIdKelurahan.clear();
        listNamaKelurahan.clear();
        for (KelurahanItem response:kelurahan) {
            listIdKelurahan.add((int) response.getId());
            listNamaKelurahan.add(response.getNama());
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listNamaKelurahan);
        binding.dropdownKelurahan.setAdapter(arrayAdapter);

        binding.dropdownKelurahan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_document:
                Intent intent = new Intent(Home.this, DataActivity.class);
                startActivity(intent);
                showNotification(false);
                break;
            case R.id.btn_submit:
                boolean isEmptyFields = false;

                if (TextUtils.isEmpty(binding.txtInputEditTextNama.getText().toString()) ||
                        TextUtils.isEmpty(binding.txtInputEditTextNoHp.getText().toString()) ||
                        TextUtils.isEmpty(binding.txtInputEditTextEmail.getText().toString()) ||
                        TextUtils.isEmpty(binding.txtInputEditTextAlamat.getText().toString()) ||
                        TextUtils.isEmpty(binding.dropdownProvinsi.getText().toString()) ||
                        TextUtils.isEmpty(binding.dropdownKotaKabupaten.getText().toString()) ||
                        TextUtils.isEmpty(binding.dropdownKecamatan.getText().toString()) ||
                        TextUtils.isEmpty(binding.dropdownKelurahan.getText().toString()) ||
                        TextUtils.isEmpty(binding.txtInputEditTextKodePos.getText().toString())){
                    isEmptyFields = true;
                    Toast toast = Toast.makeText(Home.this, "Field tidak boleh kosong", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL,0,0);
                    toast.show();
                }else if (!isEmptyFields){
                    postData(createVisitor());
                    removeField();
                    showNotification(true);
                }
                break;
        }
    }

    private void showLoading(Boolean state) {
        if (state){
//            binding.progressBarHome.setVisibility(View.VISIBLE);
            binding.progressDialog.progressDialog.setVisibility(View.VISIBLE);
        }else {
//            binding.progressBarHome.setVisibility(View.GONE);
            binding.progressDialog.progressDialog.setVisibility(View.GONE);
        }
    }

    private void showNotification(boolean state) {
        if (state){
            binding.toolbar.notificationNumber.setVisibility(View.VISIBLE);
            notificatiionCounter.increaseNumber();
        }else {
            binding.toolbar.notificationNumber.setVisibility(View.GONE);
            notificatiionCounter.increaseNumber();
        }
    }
}